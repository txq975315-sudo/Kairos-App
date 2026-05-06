package com.example.kairosapplication.widget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.runBlocking

class WidgetAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        runBlocking {
            WidgetManager.refreshWidgets(context.applicationContext)
        }
    }
}
