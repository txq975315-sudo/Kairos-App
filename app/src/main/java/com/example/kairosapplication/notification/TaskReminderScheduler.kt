package com.example.kairosapplication.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.taskmodel.model.Task
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * One exact alarm per task id: fires once at [Task.taskDate] + [Task.reminderTime] (local zone).
 * [TaskReminderReceiver] shows the notification; [syncAlarms] re-applies after data or boot changes.
 */
object TaskReminderScheduler {

    fun requestCodeForTask(taskId: Int): Int = 8_800_000 + (taskId and 0x7FFFFFFF)

    fun cancelAlarm(context: Context, taskId: Int) {
        val app = context.applicationContext
        val pi = pendingIntent(app, taskId, "", 0L)
        val am = app.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.cancel(pi)
        pi.cancel()
    }

    fun syncAlarms(context: Context, tasks: List<Task>) {
        val app = context.applicationContext
        val zone = ZoneId.systemDefault()
        val now = Instant.now()
        for (task in tasks) {
            val trigger = triggerInstant(task, zone)
            if (trigger == null) {
                cancelAlarm(app, task.id)
                continue
            }
            if (task.isCompleted || !trigger.isAfter(now)) {
                cancelAlarm(app, task.id)
                continue
            }
            scheduleExact(app, task, trigger.toEpochMilli())
        }
    }

    private fun scheduleExact(context: Context, task: Task, triggerMillis: Long) {
        val pi = pendingIntent(context, task.id, task.title, task.taskDate.toEpochDay())
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerMillis, pi)
        } else {
            @Suppress("DEPRECATION")
            am.setExact(AlarmManager.RTC_WAKEUP, triggerMillis, pi)
        }
    }

    private fun pendingIntent(context: Context, taskId: Int, title: String, epochDay: Long): PendingIntent {
        val intent = Intent(context, TaskReminderReceiver::class.java).apply {
            action = TaskReminderReceiver.ACTION_TASK_REMINDER
            putExtra(TaskReminderReceiver.EXTRA_TASK_ID, taskId)
            putExtra(TaskReminderReceiver.EXTRA_TITLE, title)
            putExtra(TaskReminderReceiver.EXTRA_TASK_DATE_EPOCH_DAY, epochDay)
        }
        return PendingIntent.getBroadcast(
            context,
            requestCodeForTask(taskId),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    private fun triggerInstant(task: Task, zone: ZoneId): Instant? {
        val hm = parseReminder(task.reminderTime) ?: return null
        return try {
            val lt = LocalTime.of(hm.first, hm.second)
            ZonedDateTime.of(task.taskDate, lt, zone).toInstant()
        } catch (_: Exception) {
            null
        }
    }

    fun parseReminder(raw: String?): Pair<Int, Int>? {
        if (raw.isNullOrBlank()) return null
        val p = raw.trim().split(':')
        if (p.size < 2) return null
        val h = p[0].toIntOrNull()?.coerceIn(0, 23) ?: return null
        val m = p[1].toIntOrNull()?.coerceIn(0, 59) ?: return null
        return h to m
    }
}
