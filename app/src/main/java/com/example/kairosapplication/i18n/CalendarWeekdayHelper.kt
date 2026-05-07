package com.example.kairosapplication.i18n

import android.content.Context
import java.time.DayOfWeek

/**
 * **Monday-first (ISO 8601)** week model used app-wide: month grid, check-in calendar,
 * [com.example.kairosapplication.ui.view.ViewViewModel] week range, etc.
 */
val WeekdayOrderMondayFirst: List<DayOfWeek> = listOf(
    DayOfWeek.MONDAY,
    DayOfWeek.TUESDAY,
    DayOfWeek.WEDNESDAY,
    DayOfWeek.THURSDAY,
    DayOfWeek.FRIDAY,
    DayOfWeek.SATURDAY,
    DayOfWeek.SUNDAY,
)

private fun weekHeaderKey(dow: DayOfWeek): String = when (dow) {
    DayOfWeek.MONDAY -> "calendar_week_mon"
    DayOfWeek.TUESDAY -> "calendar_week_tue"
    DayOfWeek.WEDNESDAY -> "calendar_week_wed"
    DayOfWeek.THURSDAY -> "calendar_week_thu"
    DayOfWeek.FRIDAY -> "calendar_week_fri"
    DayOfWeek.SATURDAY -> "calendar_week_sat"
    DayOfWeek.SUNDAY -> "calendar_week_sun"
}

/** Short weekday labels for header row (Mon…Sun order). */
fun weekShortHeadersMondayFirst(context: Context, lang: LocalizationManager.Language): List<String> =
    WeekdayOrderMondayFirst.map { dow -> LocalizedStrings.stringFor(lang, weekHeaderKey(dow), context) }
