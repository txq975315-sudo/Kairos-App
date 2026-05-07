package com.example.kairosapplication.widget.data

import android.content.Context

object Widget3x3CalendarStore {
    private const val PREF = "kairos_widget_3x3_calendar"

    private fun key(appWidgetId: Int) = "weeks_$appWidgetId"

    fun save(context: Context, appWidgetId: Int, json: String) {
        // commit() so RemoteViewsService reads consistent JSON immediately after updateAppWidget.
        context.applicationContext
            .getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .putString(key(appWidgetId), json)
            .commit()
    }

    fun load(context: Context, appWidgetId: Int): String? =
        context.applicationContext
            .getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .getString(key(appWidgetId), null)
}
