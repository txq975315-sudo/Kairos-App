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
                views.setTextColor(R.id.widget_2x2_month_title, primary)
                views.setTextColor(R.id.widget_2x2_month_grid, muted)
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
                        views.setTextColor(R.id.widget_yesterday_day, primary)
                        views.setTextColor(R.id.widget_tomorrow_day, primary)
                        views.setTextColor(R.id.widget_today_date_line, primary)
                        views.setTextColor(R.id.widget_today_overflow_hint, muted)
                        views.setTextColor(R.id.widget_yesterday_tasks, muted)
                        views.setTextColor(R.id.widget_tomorrow_tasks, muted)
                        (0..9).forEach { i ->
                            val mark = when (i) {
                                0 -> R.id.widget_today_cell_0_mark
                                1 -> R.id.widget_today_cell_1_mark
                                2 -> R.id.widget_today_cell_2_mark
                                3 -> R.id.widget_today_cell_3_mark
                                4 -> R.id.widget_today_cell_4_mark
                                5 -> R.id.widget_today_cell_5_mark
                                6 -> R.id.widget_today_cell_6_mark
                                7 -> R.id.widget_today_cell_7_mark
                                8 -> R.id.widget_today_cell_8_mark
                                else -> R.id.widget_today_cell_9_mark
                            }
                            val title = when (i) {
                                0 -> R.id.widget_today_cell_0_title
                                1 -> R.id.widget_today_cell_1_title
                                2 -> R.id.widget_today_cell_2_title
                                3 -> R.id.widget_today_cell_3_title
                                4 -> R.id.widget_today_cell_4_title
                                5 -> R.id.widget_today_cell_5_title
                                6 -> R.id.widget_today_cell_6_title
                                7 -> R.id.widget_today_cell_7_title
                                8 -> R.id.widget_today_cell_8_title
                                else -> R.id.widget_today_cell_9_title
                            }
                            views.setTextColor(mark, muted)
                            views.setTextColor(title, muted)
                        }
                    }
                }
            WidgetSize._3X3 -> {
                views.setTextColor(R.id.widget_month_title, primary)
                runCatching {
                    views.setTextColor(R.id.widget_month_prev, muted)
                    views.setTextColor(R.id.widget_month_next, muted)
                }
                intArrayOf(
                    R.id.widget_week_h0,
                    R.id.widget_week_h1,
                    R.id.widget_week_h2,
                    R.id.widget_week_h3,
                    R.id.widget_week_h4,
                    R.id.widget_week_h5,
                    R.id.widget_week_h6
                ).forEach { views.setTextColor(it, muted) }
                views.setTextColor(R.id.widget_daily_quote, muted)
            }
        }
    }

    private fun apply1x1b(config: WidgetConfig, views: RemoteViews, primary: Int, muted: Int) {
        if (config.layoutKind != WidgetLayoutKind._1B) return
        runCatching {
            views.setTextColor(R.id.widget_todo_stats_1b, muted)
            views.setTextColor(R.id.widget_date, primary)
            views.setTextColor(R.id.widget_todo_0_mark, muted)
            views.setTextColor(R.id.widget_todo_1_mark, muted)
            views.setTextColor(R.id.widget_todo_2_mark, muted)
        }
    }
}
