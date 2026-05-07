package com.example.kairosapplication.ui.mine.records

import java.util.Locale

data class MineRecordsOverview(
    val completionDaysDistinct: Int = 0,
    val completedTaskCount: Int = 0,
    val todayIncomplete: Int = 0,
    val notesCount: Int = 0,
    val tasksTotalCount: Int = 0,
    val completionRate: Int = 0,
    val streakRecord: Int = 0,
    val streakAllTasksDone: Int = 0,
    val streakBothModules: Int = 0,
    val maxStreakRecord: Int = 0,
    /** Longest run of days with all tasks completed (when day had tasks). */
    val longestAllTasksStreak: Int = 0,
    /** Longest run of days with essay + all tasks done. */
    val longestBothStreak: Int = 0,
    /** Max of [maxStreakRecord], [longestAllTasksStreak], [longestBothStreak]. */
    val peakContinuityStreak: Int = 0,
    /** Days in current calendar month (today's month) with task or note, intersected with period. */
    val monthActiveDays: Int = 0,
    val totalChars: Long = 0L,
    val maxNoteChars: Int = 0,
    val avgDailyTasks: Float = 0f,
    val avgDailyNotes: Float = 0f,
) {
    fun rawDisplay(metric: MineRecordsMetric): String = when (metric) {
        MineRecordsMetric.COMPLETION_DAYS -> completionDaysDistinct.toString()
        MineRecordsMetric.COMPLETED_ITEMS -> completedTaskCount.toString()
        MineRecordsMetric.TODAY_INCOMPLETE -> todayIncomplete.toString()
        MineRecordsMetric.NOTES -> notesCount.toString()
        MineRecordsMetric.TASKS -> tasksTotalCount.toString()
        MineRecordsMetric.COMPLETION_RATE -> "$completionRate%"
        MineRecordsMetric.STREAK_RECORD -> streakRecord.toString()
        MineRecordsMetric.STREAK_ALL_TASKS_DONE -> streakAllTasksDone.toString()
        MineRecordsMetric.STREAK_BOTH_MODULES -> streakBothModules.toString()
        MineRecordsMetric.MAX_STREAK_RECORD -> maxStreakRecord.toString()
        MineRecordsMetric.TOTAL_CHARS -> totalChars.toString()
        MineRecordsMetric.MAX_NOTE_CHARS -> maxNoteChars.toString()
        MineRecordsMetric.AVG_DAILY_TASKS ->
            String.format(Locale.US, "%.1f", avgDailyTasks)
        MineRecordsMetric.AVG_DAILY_NOTES ->
            String.format(Locale.US, "%.1f", avgDailyNotes)
        MineRecordsMetric.LONGEST_ALL_TASKS_STREAK -> longestAllTasksStreak.toString()
        MineRecordsMetric.LONGEST_BOTH_STREAK -> longestBothStreak.toString()
        MineRecordsMetric.PEAK_CONTINUITY_STREAK -> peakContinuityStreak.toString()
        MineRecordsMetric.MONTH_ACTIVE_DAYS -> monthActiveDays.toString()
    }
}
