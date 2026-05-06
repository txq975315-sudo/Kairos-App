package com.example.kairosapplication.widget

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.graphics.Color
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.kairosapplication.R
import com.example.kairosapplication.widget.data.WidgetTaskListStore
import com.example.kairosapplication.widget.data.WidgetTaskRow

class WidgetRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return WidgetRemoteViewsFactory(applicationContext, intent)
    }
}

class WidgetRemoteViewsFactory(
    private val context: android.content.Context,
    private val intent: Intent
) : RemoteViewsService.RemoteViewsFactory {

    private var appWidgetId: Int = AppWidgetManager.INVALID_APPWIDGET_ID
    private var rows = emptyList<WidgetTaskRow>()

    override fun onCreate() {
        appWidgetId = intent.getIntExtra(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        )
    }

    override fun onDataSetChanged() {
        if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
            rows = WidgetTaskListStore.load(context, appWidgetId)
        }
    }

    override fun onDestroy() {}

    override fun getCount(): Int = rows.size

    override fun getViewAt(position: Int): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_task_item)
        if (position in rows.indices) {
            val row = rows[position]
            views.setTextViewText(R.id.task_title, row.title.ifBlank { "—" })
            val done = row.done
            views.setTextViewText(R.id.task_status, if (done) "✔" else "○")
            views.setTextColor(
                R.id.task_status,
                if (done) Color.parseColor("#7B61FF") else Color.parseColor("#9E9E9E")
            )
        }
        val fillInIntent = Intent().apply {
            if (position in rows.indices && rows[position].taskId >= 0) {
                putExtra(WidgetClickHandler.EXTRA_TARGET_PAGE, WidgetClickHandler.TARGET_EDIT_TASK)
                putExtra(WidgetClickHandler.EXTRA_TASK_ID, rows[position].taskId)
            } else {
                putExtra(WidgetClickHandler.EXTRA_TARGET_PAGE, WidgetClickHandler.TARGET_TODAY)
            }
        }
        views.setOnClickFillInIntent(R.id.widget_task_row, fillInIntent)
        return views
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = false
}
