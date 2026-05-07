package com.example.kairosapplication.ui.mine.records

import com.example.taskmodel.model.Task

/**
 * Daily todo completion level for the check-in calendar.
 *
 * - [NO_TASKS]: No todos scheduled that day — neutral “rest” cell (not a failure).
 * - [ZERO_PROGRESS]: Has todos but none completed — “要努力”.
 * - [LOW] / [MID] / [HIGH]: Partial completion by ratio.
 * - [FULL]: All todos for that day completed.
 */
enum class DailyCheckInTier {
    NO_TASKS,
    ZERO_PROGRESS,
    LOW,
    MID,
    HIGH,
    FULL,
    ;

    fun emoji(): String = when (this) {
        NO_TASKS -> ""
        ZERO_PROGRESS -> "\uD83C\uDF31" // 🌱
        LOW -> "\uD83D\uDCCC" // 📌
        MID -> "\uD83D\uDCAA" // 💪
        HIGH -> "\uD83D\uDC4F" // 👏
        FULL -> "\uD83C\uDF89" // 🎉
    }
}

fun dailyCheckInTierForDay(dayTasks: List<Task>): DailyCheckInTier {
    if (dayTasks.isEmpty()) return DailyCheckInTier.NO_TASKS
    val total = dayTasks.size
    val done = dayTasks.count { it.isCompleted }
    if (done == 0) return DailyCheckInTier.ZERO_PROGRESS
    if (done == total) return DailyCheckInTier.FULL
    val ratio = done.toFloat() / total.toFloat()
    return when {
        ratio >= 0.75f -> DailyCheckInTier.HIGH
        ratio >= 0.5f -> DailyCheckInTier.MID
        else -> DailyCheckInTier.LOW
    }
}
