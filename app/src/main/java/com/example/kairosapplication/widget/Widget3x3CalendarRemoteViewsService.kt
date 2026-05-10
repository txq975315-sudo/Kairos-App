package com.example.kairosapplication.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.util.TypedValue
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.StrikethroughSpan
import android.view.View
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.kairosapplication.R
import com.example.kairosapplication.widget.data.Widget3x3CalendarStore
import org.json.JSONArray
import org.json.JSONObject

class Widget3x3CalendarRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory =
        Widget3x3CalendarRemoteViewsFactory(applicationContext, intent)
}

private class Widget3x3CalendarRemoteViewsFactory(
    private val context: Context,
    private val intent: Intent
) : RemoteViewsService.RemoteViewsFactory {

    private var appWidgetId: Int = AppWidgetManager.INVALID_APPWIDGET_ID
    private var weeks = JSONArray()

    private val dayIds = intArrayOf(
        R.id.widget_cal_col0_day,
        R.id.widget_cal_col1_day,
        R.id.widget_cal_col2_day,
        R.id.widget_cal_col3_day,
        R.id.widget_cal_col4_day,
        R.id.widget_cal_col5_day,
        R.id.widget_cal_col6_day
    )

    private val taskLineIds = arrayOf(
        intArrayOf(R.id.widget_cal_col0_l0, R.id.widget_cal_col0_l1, R.id.widget_cal_col0_l2),
        intArrayOf(R.id.widget_cal_col1_l0, R.id.widget_cal_col1_l1, R.id.widget_cal_col1_l2),
        intArrayOf(R.id.widget_cal_col2_l0, R.id.widget_cal_col2_l1, R.id.widget_cal_col2_l2),
        intArrayOf(R.id.widget_cal_col3_l0, R.id.widget_cal_col3_l1, R.id.widget_cal_col3_l2),
        intArrayOf(R.id.widget_cal_col4_l0, R.id.widget_cal_col4_l1, R.id.widget_cal_col4_l2),
        intArrayOf(R.id.widget_cal_col5_l0, R.id.widget_cal_col5_l1, R.id.widget_cal_col5_l2),
        intArrayOf(R.id.widget_cal_col6_l0, R.id.widget_cal_col6_l1, R.id.widget_cal_col6_l2)
    )

    override fun onCreate() {
        appWidgetId = intent.getIntExtra(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        )
    }

    override fun onDataSetChanged() {
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            weeks = JSONArray()
            return
        }
        val raw = Widget3x3CalendarStore.load(context, appWidgetId) ?: "[]"
        weeks = try {
            JSONArray(raw)
        } catch (_: Exception) {
            JSONArray()
        }
    }

    override fun onDestroy() {}

    override fun getCount(): Int {
        val n = weeks.length()
        return if (n > 0) n else 1
    }

    override fun getViewAt(position: Int): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_3x3_week_row)
        val weekObj = weeks.optJSONObject(position)
        val days = weekObj?.optJSONArray("days")
        for (c in 0 until 7) {
            val dayId = dayIds[c]
            val lineIds = taskLineIds[c]
            if (days == null || c >= days.length()) {
                views.setInt(dayId, "setBackgroundResource", R.drawable.widget_cal_day_clear)
                views.setTextViewText(dayId, "")
                lineIds.forEach { lid ->
                    views.setViewVisibility(lid, View.GONE)
                    views.setTextViewText(lid, "")
                    views.setInt(lid, "setBackgroundColor", Color.TRANSPARENT)
                }
                continue
            }
            val d = days.optJSONObject(c) ?: JSONObject()
            val dom = d.optInt("dom", 0)
            val inMonth = d.optBoolean("in", false)
            val isToday = d.optBoolean("today", false)
            if (isToday && inMonth && dom > 0) {
                views.setInt(dayId, "setBackgroundResource", R.drawable.widget_cal_today_fill)
                val numStr = dom.toString()
                val daySs = SpannableStringBuilder(numStr)
                daySs.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    daySs.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                daySs.setSpan(
                    ForegroundColorSpan(Color.WHITE),
                    0,
                    daySs.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                views.setTextViewText(dayId, daySs)
            } else {
                views.setInt(dayId, "setBackgroundResource", R.drawable.widget_cal_day_clear)
                views.setTextViewText(dayId, if (dom > 0) dom.toString() else "")
                when {
                    inMonth -> views.setTextColor(dayId, Color.parseColor("#1A1A1A"))
                    else -> views.setTextColor(dayId, Color.parseColor("#BDBDBD"))
                }
            }
            val arr = d.optJSONArray("lines")
            for (li in 0 until 3) {
                val lid = lineIds[li]
                val line = arr?.optJSONObject(li)
                if (line == null || line.optInt("i", -1) < 0) {
                    views.setViewVisibility(lid, View.GONE)
                    views.setTextViewText(lid, "")
                    views.setInt(lid, "setBackgroundColor", Color.TRANSPARENT)
                    views.setViewPadding(lid, 0, 0, 0, 0)
                    continue
                }
                val tid = line.optInt("i", -1)
                val title = WidgetTaskTitleClip.for3x3CalendarCell(line.optString("t", ""))
                val done = line.optBoolean("d", false)
                val urg = line.optInt("u", 3)
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
                    views.setTextViewText(lid, ssb)
                } else {
                    views.setTextViewText(lid, title)
                    views.setTextColor(lid, WidgetTaskStyle.titleColorArgb(false))
                }
                views.setInt(lid, "setBackgroundColor", WidgetTaskStyle.widget3x3TaskLineBackgroundArgb(urg, done))
                views.setBoolean(lid, "setIncludeFontPadding", false)
                views.setInt(lid, "setGravity", Gravity.CENTER)
                val pad = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    1f,
                    context.resources.displayMetrics
                ).toInt()
                views.setViewPadding(lid, pad, pad, pad, pad)
                views.setViewVisibility(lid, View.VISIBLE)
                views.setOnClickFillInIntent(
                    lid,
                    Intent().apply { putExtra(WidgetClickHandler.EXTRA_TASK_ID, tid) }
                )
            }
        }
        return views
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = true
}
