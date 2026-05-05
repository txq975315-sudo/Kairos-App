package com.example.kairosapplication.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.kairosapplication.data.DataStoreManager
import kotlinx.coroutines.runBlocking

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val appCtx = context.applicationContext
        val helper = NotificationHelper(appCtx)
        when (intent.action) {
            ACTION_SHOW_REMINDER -> {
                helper.showDailyReminderNotification()
                runBlocking {
                    val ds = DataStoreManager(appCtx)
                    if (ds.getDailyReminderSync()) {
                        val (h, m) = ds.getDailyReminderTimeSync().parseHourMinute()
                        helper.scheduleDailyReminder(h, m)
                    }
                }
            }
            ACTION_SHOW_REFLECTION -> {
                helper.showDailyReflectionNotification()
                runBlocking {
                    val ds = DataStoreManager(appCtx)
                    if (ds.getDailyReflectionSync()) {
                        val (h, m) = ds.getDailyReflectionTimeSync().parseHourMinute()
                        helper.scheduleDailyReflection(h, m)
                    }
                }
            }
        }
    }

    companion object {
        const val ACTION_SHOW_REMINDER = "com.example.kairosapplication.ACTION_SHOW_REMINDER"
        const val ACTION_SHOW_REFLECTION = "com.example.kairosapplication.ACTION_SHOW_REFLECTION"
    }
}

private fun String.parseHourMinute(): Pair<Int, Int> {
    val p = split(':')
    val h = p.getOrNull(0)?.toIntOrNull()?.coerceIn(0, 23) ?: 9
    val m = p.getOrNull(1)?.toIntOrNull()?.coerceIn(0, 59) ?: 0
    return h to m
}
