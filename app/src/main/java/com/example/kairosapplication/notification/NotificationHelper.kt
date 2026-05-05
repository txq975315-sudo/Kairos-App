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
import java.util.Calendar

class NotificationHelper(private val context: Context) {

    private val appCtx = context.applicationContext
    private val alarmManager: AlarmManager
        get() = appCtx.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    companion object {
        const val CHANNEL_DAILY_REMINDER = "daily_reminder"
        const val CHANNEL_DAILY_REFLECTION = "daily_reflection"
        const val NOTIFICATION_ID_REMINDER = 1001
        const val NOTIFICATION_ID_REFLECTION = 1002
        private const val RC_REMINDER_ALARM = 91001
        private const val RC_REFLECTION_ALARM = 91002
    }

    fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val nm = appCtx.getSystemService(NotificationManager::class.java)
        val chReminder = NotificationChannel(
            CHANNEL_DAILY_REMINDER,
            "每日待办提醒",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "每日待办提醒"
            lockscreenVisibility = android.app.Notification.VISIBILITY_PUBLIC
        }
        val chReflection = NotificationChannel(
            CHANNEL_DAILY_REFLECTION,
            "每日反思提醒",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "每日反思提醒"
        }
        nm.createNotificationChannel(chReminder)
        nm.createNotificationChannel(chReflection)
    }

    fun showDailyReminderNotification() {
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
            .setContentTitle("每日待办提醒")
            .setContentText("今天还有任务待完成，点击查看")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(tap)
            .setAutoCancel(true)
            .build()
        nm.notify(NOTIFICATION_ID_REMINDER, notification)
    }

    fun showDailyReflectionNotification() {
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
            .setContentTitle("每日反思提醒")
            .setContentText("今天记录了吗？点击写下今日反思")
            .setContentIntent(tap)
            .setAutoCancel(true)
            .build()
        nm.notify(NOTIFICATION_ID_REFLECTION, notification)
    }

    fun scheduleDailyReminder(hour: Int, minute: Int) {
        val trigger = nextTriggerMillis(hour, minute)
        val pi = reminderAlarmPendingIntent()
        val am = alarmManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, trigger, pi)
        } else {
            @Suppress("DEPRECATION")
            am.setExact(AlarmManager.RTC_WAKEUP, trigger, pi)
        }
    }

    fun scheduleDailyReflection(hour: Int, minute: Int) {
        val trigger = nextTriggerMillis(hour, minute)
        val pi = reflectionAlarmPendingIntent()
        val am = alarmManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, trigger, pi)
        } else {
            @Suppress("DEPRECATION")
            am.setExact(AlarmManager.RTC_WAKEUP, trigger, pi)
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
