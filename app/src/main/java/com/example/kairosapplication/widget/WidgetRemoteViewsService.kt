package com.example.kairosapplication.widget

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
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
            val done = row.done
            views.setTextViewText(R.id.task_status, if (done) "✓" else "○")
            views.setTextColor(R.id.task_status, WidgetTaskStyle.markColorArgb(done))
            val title = row.title.ifBlank { "—" }
            if (done) {
                val ssb = SpannableStringBuilder(title)
                ssb.setSpan(
                    StrikethroughSpan(),
                    0,
                    ssb.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                ssb.setSpan(
                    ForegroundColorSpan(WidgetTaskStyle.titleColorArgb(true)),
                    0,
                    ssb.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                views.setTextViewText(R.id.task_title, ssb)
            } else {
                views.setTextViewText(R.id.task_title, title)
                views.setTextColor(R.id.task_title, WidgetTaskStyle.titleColorArgb(false))
            }
            views.setInt(
                R.id.widget_task_row,
                "setBackgroundColor",
                WidgetTaskStyle.urgencyRowBackgroundArgb(row.urgency, done)
            )
            views.setTextViewText(R.id.task_urgency_dot, "●")
            views.setTextColor(
                R.id.task_urgency_dot,
                WidgetTaskStyle.urgencyPriorityDotArgb(row.urgency, done)
            )
        } else {
            views.setTextViewText(R.id.task_status, "")
            views.setTextViewText(R.id.task_title, "")
            views.setTextViewText(R.id.task_urgency_dot, "")
            views.setInt(R.id.widget_task_row, "setBackgroundColor", Color.TRANSPARENT)
        }
        val fillInIntent = Intent().apply {
            if (position in rows.indices && rows[position].taskId >= 0) {
                putExtra(WidgetClickHandler.EXTRA_TASK_ID, rows[position].taskId)
            }
        }
        views.setOnClickFillInIntent(R.id.widget_task_row, fillInIntent)
        views.setOnClickFillInIntent(R.id.task_status, fillInIntent)
        views.setOnClickFillInIntent(R.id.task_urgency_dot, fillInIntent)
        return views
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = false
}
