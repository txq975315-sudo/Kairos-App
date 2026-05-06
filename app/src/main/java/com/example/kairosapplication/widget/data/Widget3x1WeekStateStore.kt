package com.example.kairosapplication.widget.data

import android.content.Context
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

/**
 * Per–3×1-widget week navigation: offset from "current" calendar week, and selected day (epoch day).
 */
object Widget3x1WeekStateStore {
    private const val PREF = "kairos_widget_3x1_week_state"

    private fun keyOff(id: Int) = "off_$id"
    private fun keySel(id: Int) = "sel_$id"

    fun load(context: Context, appWidgetId: Int): Pair<Int, Long> {
        val p = context.applicationContext.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val off = p.getInt(keyOff(appWidgetId), 0)
        val sel = p.getLong(keySel(appWidgetId), LocalDate.now().toEpochDay())
        return off to sel
    }

    fun save(context: Context, appWidgetId: Int, weekOffset: Int, selectedEpochDay: Long) {
        context.applicationContext.getSharedPreferences(PREF, Context.MODE_PRIVATE).edit()
            .putInt(keyOff(appWidgetId), weekOffset)
            .putLong(keySel(appWidgetId), selectedEpochDay)
            .apply()
    }

    fun mondayForOffset(weekOffset: Int): LocalDate {
        val base = LocalDate.now().plusWeeks(weekOffset.toLong())
        return base.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
    }

    fun coerceSelectedToWeek(weekOffset: Int, selectedEpochDay: Long): Long {
        val start = mondayForOffset(weekOffset)
        val end = start.plusDays(6)
        val d = LocalDate.ofEpochDay(selectedEpochDay)
        return when {
            d.isBefore(start) -> start.toEpochDay()
            d.isAfter(end) -> end.toEpochDay()
            else -> selectedEpochDay
        }
    }
}
