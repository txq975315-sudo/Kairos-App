package com.example.kairosapplication.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.kairosapplication.data.DataStoreManager
import kotlinx.coroutines.runBlocking

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) return
        val appCtx = context.applicationContext
        runBlocking {
            val ds = DataStoreManager(appCtx)
            val helper = NotificationHelper(appCtx)
            helper.createNotificationChannels()
            if (ds.getDailyReminderSync()) {
                val (h, m) = ds.getDailyReminderTimeSync().splitTime()
                helper.scheduleDailyReminder(h, m)
            } else {
                helper.cancelDailyReminder()
            }
            if (ds.getDailyReflectionSync()) {
                val (h, m) = ds.getDailyReflectionTimeSync().splitTime()
                helper.scheduleDailyReflection(h, m)
            } else {
                helper.cancelDailyReflection()
            }
        }
    }
}

private fun String.splitTime(): Pair<Int, Int> {
    val p = split(':')
    val h = p.getOrNull(0)?.toIntOrNull()?.coerceIn(0, 23) ?: 21
    val m = p.getOrNull(1)?.toIntOrNull()?.coerceIn(0, 59) ?: 0
    return h to m
}
