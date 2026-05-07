package com.example.kairosapplication.ui.view

import com.example.kairosapplication.ui.view.month.MonthOverviewMetric
import java.util.Locale

data class Overview(
    val totalNotes: Int = 0,
    val totalTasks: Int = 0,
    val completionRate: Int = 0,
    /** Consecutive days (within viewed month) with a note or task, counting back from anchor day. */
    val consecutiveRecordDays: Int = 0,
    /** Consecutive days where every task that day is completed (and at least one task). */
    val consecutiveAllTasksDoneDays: Int = 0,
    /** Consecutive days with at least one note and one task. */
    val consecutiveBothModulesDays: Int = 0,
    val totalDiaryChars: Long = 0L,
    val maxSingleNoteChars: Int = 0,
    val avgDailyTasks: Float = 0f,
    val avgDailyNotes: Float = 0f,
) {
    fun displayValue(metric: MonthOverviewMetric): String = when (metric) {
        MonthOverviewMetric.NOTES -> totalNotes.toString()
        MonthOverviewMetric.TASKS -> totalTasks.toString()
        MonthOverviewMetric.COMPLETION_RATE -> "$completionRate%"
        MonthOverviewMetric.STREAK_RECORD -> consecutiveRecordDays.toString()
        MonthOverviewMetric.STREAK_ALL_TASKS_DONE -> consecutiveAllTasksDoneDays.toString()
        MonthOverviewMetric.STREAK_BOTH_MODULES -> consecutiveBothModulesDays.toString()
        MonthOverviewMetric.TOTAL_CHARS -> totalDiaryChars.toString()
        MonthOverviewMetric.MAX_NOTE_CHARS -> maxSingleNoteChars.toString()
        MonthOverviewMetric.AVG_DAILY_TASKS ->
            String.format(Locale.US, "%.1f", avgDailyTasks)
        MonthOverviewMetric.AVG_DAILY_NOTES ->
            String.format(Locale.US, "%.1f", avgDailyNotes)
    }
}
