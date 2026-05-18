package com.example.kairosapplication

import android.app.Application
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.i18n.LocaleHelper
import com.example.kairosapplication.notification.NotificationHelper
import com.example.kairosapplication.widget.WidgetAlarmScheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class KairosApplication : Application() {

    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        LocaleHelper.applyPersistedLocales(this)
        super.onCreate()
        appScope.launch { reschedulePersistedAlarms() }
    }

    private fun reschedulePersistedAlarms() {
        val ds = DataStoreManager(this)
        val helper = NotificationHelper(this)
        helper.createNotificationChannels()
        if (ds.getDailyReminderSync()) {
            val (h, m) = ds.getDailyReminderTimeSync().splitAlarmTime()
            helper.scheduleDailyReminder(h, m)
        } else {
            helper.cancelDailyReminder()
        }
        if (ds.getDailyReflectionSync()) {
            val (h, m) = ds.getDailyReflectionTimeSync().splitAlarmTime()
            helper.scheduleDailyReflection(h, m)
        } else {
            helper.cancelDailyReflection()
        }
        WidgetAlarmScheduler.rescheduleAll(this)
    }
}

private fun String.splitAlarmTime(): Pair<Int, Int> {
    val p = split(':')
    val h = p.getOrNull(0)?.toIntOrNull()?.coerceIn(0, 23) ?: 21
    val m = p.getOrNull(1)?.toIntOrNull()?.coerceIn(0, 59) ?: 0
    return h to m
}
