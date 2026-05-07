package com.example.kairosapplication.ui.mine.records

/** Selectable metrics on Mine → All records (persisted). */
enum class MineRecordsMetric {
    COMPLETION_DAYS,
    COMPLETED_ITEMS,
    TODAY_INCOMPLETE,
    NOTES,
    TASKS,
    COMPLETION_RATE,
    STREAK_RECORD,
    STREAK_ALL_TASKS_DONE,
    STREAK_BOTH_MODULES,
    MAX_STREAK_RECORD,
    LONGEST_ALL_TASKS_STREAK,
    LONGEST_BOTH_STREAK,
    PEAK_CONTINUITY_STREAK,
    MONTH_ACTIVE_DAYS,
    TOTAL_CHARS,
    MAX_NOTE_CHARS,
    AVG_DAILY_TASKS,
    AVG_DAILY_NOTES,
    ;

    /** Key for [com.example.kairosapplication.i18n.LocalizedStrings.get]. */
    fun labelKey(): String = when (this) {
        COMPLETION_DAYS -> "mine_records_completion_days"
        COMPLETED_ITEMS -> "mine_records_completed_items"
        TODAY_INCOMPLETE -> "mine_records_unfinished_today"
        NOTES -> "view_metric_notes"
        TASKS -> "view_metric_tasks"
        COMPLETION_RATE -> "view_metric_completion_rate"
        STREAK_RECORD -> "mine_metric_current_streak_record"
        STREAK_ALL_TASKS_DONE -> "view_metric_streak_all_tasks"
        STREAK_BOTH_MODULES -> "view_metric_streak_both"
        MAX_STREAK_RECORD -> "mine_metric_longest_has_record"
        LONGEST_ALL_TASKS_STREAK -> "mine_metric_longest_all_done"
        LONGEST_BOTH_STREAK -> "mine_metric_longest_both"
        PEAK_CONTINUITY_STREAK -> "mine_metric_peak_continuity"
        MONTH_ACTIVE_DAYS -> "mine_metric_month_active_days"
        TOTAL_CHARS -> "view_metric_total_chars"
        MAX_NOTE_CHARS -> "view_metric_max_chars"
        AVG_DAILY_TASKS -> "view_metric_avg_tasks"
        AVG_DAILY_NOTES -> "view_metric_avg_notes"
    }

    companion object {
        fun defaultSelection(): List<MineRecordsMetric> =
            listOf(NOTES, COMPLETION_RATE, STREAK_RECORD, TOTAL_CHARS, MAX_NOTE_CHARS, AVG_DAILY_TASKS)

        fun parseStored(raw: String?): List<MineRecordsMetric> {
            if (raw.isNullOrBlank()) return defaultSelection()
            val parsed = raw.split(',')
                .mapNotNull { runCatching { valueOf(it.trim()) }.getOrNull() }
                .distinct()
                .take(8)
            return parsed.ifEmpty { defaultSelection() }
        }

        fun encode(list: List<MineRecordsMetric>): String =
            list.distinct().joinToString(",") { it.name }
    }
}
