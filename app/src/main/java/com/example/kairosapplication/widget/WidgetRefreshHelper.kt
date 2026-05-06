package com.example.kairosapplication.widget

import android.content.Context

object WidgetRefreshHelper {
    fun notifyDataChanged(context: Context) {
        WidgetManager.refreshWidgetsAsync(context.applicationContext, null)
    }
}
