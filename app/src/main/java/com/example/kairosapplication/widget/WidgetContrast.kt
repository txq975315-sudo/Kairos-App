package com.example.kairosapplication.widget

import android.graphics.Color
import android.widget.RemoteViews
import com.example.kairosapplication.widget.data.WidgetBackgroundType
import com.example.kairosapplication.widget.data.WidgetConfig
import com.example.kairosapplication.widget.data.WidgetLayoutKind
import com.example.kairosapplication.widget.data.WidgetSize
import com.example.kairosapplication.R

object WidgetContrast {

    private val WD_IDS_3C = intArrayOf(
        R.id.widget_wd_0,
        R.id.widget_wd_1,
        R.id.widget_wd_2,
        R.id.widget_wd_3,
        R.id.widget_wd_4,
        R.id.widget_wd_5,
        R.id.widget_wd_6
    )

    private val WS_IDS_3C = intArrayOf(
        R.id.widget_ws_0,
        R.id.widget_ws_1,
        R.id.widget_ws_2,
        R.id.widget_ws_3,
        R.id.widget_ws_4,
        R.id.widget_ws_5,
        R.id.widget_ws_6
    )
    fun maybeApply(config: WidgetConfig, size: WidgetSize, views: RemoteViews, backdropArgb: Int) {
        val bg = config.background
        when (bg.type) {
            WidgetBackgroundType.WHITE -> return
            WidgetBackgroundType.SOLID_COLOR -> {
                if (backdropArgb == Color.WHITE) return
            }
            WidgetBackgroundType.IMAGE, WidgetBackgroundType.GRADIENT -> {}
        }
        val primary = WidgetBackgroundManager.calculateTextColor(backdropArgb)
        val muted =
            if (Color.red(primary) > 200) Color.parseColor("#616161") else Color.parseColor("#BDBDBD")
        when (size) {
            WidgetSize._1X1 -> {
                views.setTextColor(R.id.widget_date, primary)
                views.setTextColor(R.id.widget_daily_quote, muted)
                when (config.layoutKind) {
                    WidgetLayoutKind._1B -> apply1x1b(config, views, primary, muted)
                    else -> views.setTextColor(R.id.widget_progress_text, primary)
                }
            }
            WidgetSize._2X2 -> {
                views.setTextColor(R.id.widget_date, primary)
                views.setTextColor(R.id.widget_task_count, muted)
                views.setTextColor(R.id.widget_daily_quote, muted)
            }
            WidgetSize._3X1 ->
                when (config.layoutKind) {
                    WidgetLayoutKind._3C -> {
                        WD_IDS_3C.forEach { views.setTextColor(it, primary) }
                        WS_IDS_3C.forEach { views.setTextColor(it, muted) }
                    }
                    WidgetLayoutKind._3B -> {
                        views.setTextColor(R.id.widget_date, primary)
                        views.setTextColor(R.id.widget_day_of_week, primary)
                        views.setTextColor(R.id.widget_checkin_stats, muted)
                        runCatching { views.setTextColor(R.id.widget_task_list_empty, muted) }
                    }
                    else -> {
                        views.setTextColor(R.id.widget_date, primary)
                        views.setTextColor(R.id.widget_day_of_week, primary)
                        views.setTextColor(R.id.widget_progress_text, primary)
                        runCatching { views.setTextColor(R.id.widget_task_list_empty, muted) }
                    }
                }
            WidgetSize._3X3 -> {
                views.setTextColor(R.id.widget_month_title, primary)
                views.setTextColor(R.id.widget_week_header, muted)
                views.setTextColor(R.id.widget_calendar_grid, primary)
                views.setTextColor(R.id.widget_section_tasks, primary)
                views.setTextColor(R.id.widget_daily_quote, muted)
            }
        }
    }

    private fun apply1x1b(config: WidgetConfig, views: RemoteViews, primary: Int, muted: Int) {
        if (config.layoutKind != WidgetLayoutKind._1B) return
        runCatching {
            views.setTextColor(R.id.widget_todo_stats_1b, muted)
            views.setTextColor(R.id.widget_todo_line_0, primary)
            views.setTextColor(R.id.widget_todo_line_1, primary)
            views.setTextColor(R.id.widget_todo_line_2, primary)
        }
    }
}
