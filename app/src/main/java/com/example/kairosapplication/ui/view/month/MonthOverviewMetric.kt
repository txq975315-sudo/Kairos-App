package com.example.kairosapplication.ui.view.month

/** Selectable tiles for the month overview card (persisted). */
enum class MonthOverviewMetric {
    NOTES,
    TASKS,
    COMPLETION_RATE,
    STREAK_RECORD,
    STREAK_ALL_TASKS_DONE,
    STREAK_BOTH_MODULES,
    TOTAL_CHARS,
    MAX_NOTE_CHARS,
    AVG_DAILY_TASKS,
    AVG_DAILY_NOTES,
    ;

    /** Key for [com.example.kairosapplication.i18n.LocalizedStrings.get]. */
    fun labelKey(): String = when (this) {
        NOTES -> "view_metric_notes"
        TASKS -> "view_metric_tasks"
        COMPLETION_RATE -> "view_metric_completion_rate"
        STREAK_RECORD -> "view_metric_streak_record"
        STREAK_ALL_TASKS_DONE -> "view_metric_streak_all_tasks"
        STREAK_BOTH_MODULES -> "view_metric_streak_both"
        TOTAL_CHARS -> "view_metric_total_chars"
        MAX_NOTE_CHARS -> "view_metric_max_chars"
        AVG_DAILY_TASKS -> "view_metric_avg_tasks"
        AVG_DAILY_NOTES -> "view_metric_avg_notes"
    }

    companion object {
        fun defaultSelection(): List<MonthOverviewMetric> =
            listOf(NOTES, TASKS, COMPLETION_RATE, STREAK_RECORD)

        fun parseStored(raw: String?): List<MonthOverviewMetric> {
            if (raw.isNullOrBlank()) return defaultSelection()
            val parsed = raw.split(',')
                .mapNotNull { runCatching { valueOf(it.trim()) }.getOrNull() }
                .distinct()
            return parsed.ifEmpty { defaultSelection() }
        }

        fun encode(list: List<MonthOverviewMetric>): String =
            list.distinct().joinToString(",") { it.name }
    }
}
