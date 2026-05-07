package com.example.kairosapplication.ui.mine.records

private val NOTES_CYCLE = listOf(
    MineRecordsMetric.NOTES,
    MineRecordsMetric.TOTAL_CHARS,
    MineRecordsMetric.MAX_NOTE_CHARS,
    MineRecordsMetric.AVG_DAILY_NOTES,
)

private val TASKS_CYCLE = listOf(
    MineRecordsMetric.TASKS,
    MineRecordsMetric.COMPLETION_RATE,
    MineRecordsMetric.AVG_DAILY_TASKS,
)

private val CONTINUITY_CYCLE = listOf(
    MineRecordsMetric.MAX_STREAK_RECORD,
    MineRecordsMetric.LONGEST_ALL_TASKS_STREAK,
    MineRecordsMetric.LONGEST_BOTH_STREAK,
    MineRecordsMetric.PEAK_CONTINUITY_STREAK,
    MineRecordsMetric.STREAK_ALL_TASKS_DONE,
    MineRecordsMetric.STREAK_BOTH_MODULES,
)

private val BLENDED_CYCLE = listOf(
    MineRecordsMetric.STREAK_RECORD,
    MineRecordsMetric.MONTH_ACTIVE_DAYS,
)

private val LEGACY_CYCLE = listOf(
    MineRecordsMetric.COMPLETION_DAYS,
    MineRecordsMetric.COMPLETED_ITEMS,
    MineRecordsMetric.TODAY_INCOMPLETE,
)

private val ALL_POOLS = listOf(
    NOTES_CYCLE,
    TASKS_CYCLE,
    CONTINUITY_CYCLE,
    BLENDED_CYCLE,
    LEGACY_CYCLE,
)

/** Next metric when user taps a card (cycle within the same pool). */
fun MineRecordsMetric.nextByTapCycle(): MineRecordsMetric {
    val pool = ALL_POOLS.firstOrNull { it.contains(this) } ?: return this
    val i = pool.indexOf(this)
    return pool[(i + 1) % pool.size]
}
