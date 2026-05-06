package com.example.kairosapplication.widget

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.widget.data.WidgetConfigRepository
import com.example.kairosapplication.widget.data.WidgetRefreshStrategy
import com.example.kairosapplication.widget.data.WidgetSize
import kotlinx.coroutines.runBlocking

object WidgetAlarmScheduler {
    private const val REQ_WIDGET_ALARM = 71001

    private fun alarmPendingIntent(context: Context): PendingIntent {
        val app = context.applicationContext
        val intent = Intent(app, WidgetAlarmReceiver::class.java)
        val immutable =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        return PendingIntent.getBroadcast(app, REQ_WIDGET_ALARM, intent, immutable)
    }

    fun cancel(context: Context) {
        val app = context.applicationContext
        (app.getSystemService(Context.ALARM_SERVICE) as AlarmManager).cancel(alarmPendingIntent(app))
    }

    /** Pick the tightest refresh interval across all saved widget sizes and reschedule. */
    fun rescheduleAll(context: Context) {
        val app = context.applicationContext
        cancel(app)
        val strategy = runBlocking {
            val repo = WidgetConfigRepository(DataStoreManager(app))
            val strategies = WidgetSize.entries.map { repo.getConfig(it).refreshStrategy }
            when {
                strategies.any { it == WidgetRefreshStrategy.HOURLY } -> WidgetRefreshStrategy.HOURLY
                strategies.any { it == WidgetRefreshStrategy.DAILY } -> WidgetRefreshStrategy.DAILY
                else -> WidgetRefreshStrategy.ON_APP_OPEN
            }
        }
        if (strategy == WidgetRefreshStrategy.ON_APP_OPEN) return
        val intervalMs = when (strategy) {
            WidgetRefreshStrategy.HOURLY -> 60L * 60L * 1000L
            WidgetRefreshStrategy.DAILY -> 24L * 60L * 60L * 1000L
            WidgetRefreshStrategy.ON_APP_OPEN -> return
        }
        val alarmMgr = app.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val trigger = SystemClock.elapsedRealtime() + intervalMs
        alarmMgr.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME,
            trigger,
            intervalMs,
            alarmPendingIntent(app)
        )
    }
}
