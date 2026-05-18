package com.example.kairosapplication.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.kairosapplication.MainActivity
import com.example.kairosapplication.R
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import java.util.Calendar

class NotificationHelper(private val context: Context) {

    private val appCtx = context.applicationContext
    private val dataStoreManager by lazy { DataStoreManager(appCtx) }

    private fun appLanguage(): LocalizationManager.Language =
        LocalizationManager.Language.fromCode(dataStoreManager.getLanguageSync())
    private val alarmManager: AlarmManager
        get() = appCtx.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    companion object {
        const val CHANNEL_DAILY_REMINDER = "daily_reminder"
        const val CHANNEL_DAILY_REFLECTION = "daily_reflection"
        const val CHANNEL_TASK_REMINDER = "task_reminder"
        const val NOTIFICATION_ID_REMINDER = 1001
        const val NOTIFICATION_ID_REFLECTION = 1002
        private const val RC_REMINDER_ALARM = 91001
        private const val RC_REFLECTION_ALARM = 91002
    }

    fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val lang = appLanguage()
        val nm = appCtx.getSystemService(NotificationManager::class.java) ?: return
        val reminderName = LocalizedStrings.stringFor(lang, "notif_channel_reminder_name", appCtx)
        val reminderDesc = LocalizedStrings.stringFor(lang, "notif_channel_reminder_desc", appCtx)
        val chReminder = NotificationChannel(
            CHANNEL_DAILY_REMINDER,
            reminderName,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = reminderDesc
            lockscreenVisibility = android.app.Notification.VISIBILITY_PUBLIC
        }
        val reflectionName = LocalizedStrings.stringFor(lang, "notif_channel_reflection_name", appCtx)
        val reflectionDesc = LocalizedStrings.stringFor(lang, "notif_channel_reflection_desc", appCtx)
        val chReflection = NotificationChannel(
            CHANNEL_DAILY_REFLECTION,
            reflectionName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = reflectionDesc
        }
        val taskName = LocalizedStrings.stringFor(lang, "notif_channel_task_reminder_name", appCtx)
        val taskDesc = LocalizedStrings.stringFor(lang, "notif_channel_task_reminder_desc", appCtx)
        val chTask = NotificationChannel(
            CHANNEL_TASK_REMINDER,
            taskName,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = taskDesc
            lockscreenVisibility = android.app.Notification.VISIBILITY_PUBLIC
        }
        nm.createNotificationChannel(chReminder)
        nm.createNotificationChannel(chReflection)
        nm.createNotificationChannel(chTask)
    }

    fun showDailyReminderNotification() {
        val lang = appLanguage()
        val nm = appCtx.getSystemService(NotificationManager::class.java) ?: return
        val tap = PendingIntent.getActivity(
            appCtx,
            0,
            Intent(appCtx, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(appCtx, CHANNEL_DAILY_REMINDER)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(LocalizedStrings.stringFor(lang, "notif_reminder_title", appCtx))
            .setContentText(LocalizedStrings.stringFor(lang, "notif_reminder_body", appCtx))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(tap)
            .setAutoCancel(true)
            .build()
        nm.notify(NOTIFICATION_ID_REMINDER, notification)
    }

    fun showDailyReflectionNotification() {
        val lang = appLanguage()
        val nm = appCtx.getSystemService(NotificationManager::class.java) ?: return
        val tap = PendingIntent.getActivity(
            appCtx,
            1,
            Intent(appCtx, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(appCtx, CHANNEL_DAILY_REFLECTION)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(LocalizedStrings.stringFor(lang, "notif_reflection_title", appCtx))
            .setContentText(LocalizedStrings.stringFor(lang, "notif_reflection_body", appCtx))
            .setContentIntent(tap)
            .setAutoCancel(true)
            .build()
        nm.notify(NOTIFICATION_ID_REFLECTION, notification)
    }

    fun showTaskReminderNotification(taskId: Int, taskTitle: String) {
        val lang = appLanguage()
        val nm = appCtx.getSystemService(NotificationManager::class.java) ?: return
        val tap = PendingIntent.getActivity(
            appCtx,
            3,
            Intent(appCtx, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val body = LocalizedStrings.stringFor(lang, "notif_task_reminder_body", appCtx)
        val notification = NotificationCompat.Builder(appCtx, CHANNEL_TASK_REMINDER)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(taskTitle)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(tap)
            .setAutoCancel(true)
            .build()
        val nid = 28_000 + (taskId % 72_000)
        nm.notify(nid, notification)
    }

    fun canScheduleExactAlarms(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return true
        return alarmManager.canScheduleExactAlarms()
    }

    fun scheduleDailyReminder(hour: Int, minute: Int) {
        scheduleExactRtc(nextTriggerMillis(hour, minute), reminderAlarmPendingIntent())
    }

    fun scheduleDailyReflection(hour: Int, minute: Int) {
        scheduleExactRtc(nextTriggerMillis(hour, minute), reflectionAlarmPendingIntent())
    }

    private fun scheduleExactRtc(triggerAtMillis: Long, pendingIntent: PendingIntent) {
        val am = alarmManager
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
            } else {
                @Suppress("DEPRECATION")
                am.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
            }
        } catch (_: SecurityException) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
            } else {
                @Suppress("DEPRECATION")
                am.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
            }
        }
    }

    fun cancelDailyReminder() {
        val pi = reminderAlarmPendingIntent()
        alarmManager.cancel(pi)
        pi.cancel()
    }

    fun cancelDailyReflection() {
        val pi = reflectionAlarmPendingIntent()
        alarmManager.cancel(pi)
        pi.cancel()
    }

    private fun reminderAlarmPendingIntent(): PendingIntent {
        val intent = Intent(appCtx, NotificationReceiver::class.java).apply {
            action = NotificationReceiver.ACTION_SHOW_REMINDER
        }
        return PendingIntent.getBroadcast(
            appCtx,
            RC_REMINDER_ALARM,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun reflectionAlarmPendingIntent(): PendingIntent {
        val intent = Intent(appCtx, NotificationReceiver::class.java).apply {
            action = NotificationReceiver.ACTION_SHOW_REFLECTION
        }
        return PendingIntent.getBroadcast(
            appCtx,
            RC_REFLECTION_ALARM,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun nextTriggerMillis(hour: Int, minute: Int): Long {
        val cal = Calendar.getInstance().apply {
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        if (cal.timeInMillis <= System.currentTimeMillis()) {
            cal.add(Calendar.DAY_OF_YEAR, 1)
        }
        return cal.timeInMillis
    }
}
