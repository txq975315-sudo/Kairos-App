package com.example.kairosapplication.ui.mine.records

import com.example.taskmodel.model.Task
import java.time.LocalDate

/** Past dates: combined task + essay (published) completion tone. */
enum class PastCheckInDisplay {
    PERFECT, // 🎉
    EXCELLENT, // 👏
    CHEER, // ✊
    SEEDLING, // 🌱
    WRITER, // ✏️
    EMPTY, // ⚪
    ;

    fun emoji(): String = when (this) {
        PERFECT -> "\uD83C\uDF89"
        EXCELLENT -> "\uD83D\uDC4F"
        CHEER -> "\u270A"
        SEEDLING -> "\uD83C\uDF31"
        WRITER -> "\u270F\uFE0F"
        EMPTY -> "\u26AA"
    }
}

/** Future / today+1: planned tasks preview style. */
enum class FutureCheckInDisplay {
    SCHEDULED, // 📋
    NONE_DOT, // ·
    ;

    fun symbol(): String = when (this) {
        SCHEDULED -> "\uD83D\uDCCB"
        NONE_DOT -> "·"
    }
}

data class CalendarDayVisual(
    val past: PastCheckInDisplay?,
    val future: FutureCheckInDisplay?,
    val useMutedFutureStyle: Boolean,
)

/**
 * @param essayCount published notes count on that calendar day (body recorded that day).
 */
fun calendarDayVisual(
    date: LocalDate,
    today: LocalDate,
    dayTasks: List<Task>,
    essayCount: Int,
): CalendarDayVisual {
    if (date.isAfter(today)) {
        val hasTasks = dayTasks.isNotEmpty()
        return CalendarDayVisual(
            past = null,
            future = if (hasTasks) FutureCheckInDisplay.SCHEDULED else FutureCheckInDisplay.NONE_DOT,
            useMutedFutureStyle = true,
        )
    }

    val taskCount = dayTasks.size
    val done = dayTasks.count { it.isCompleted }
    val hasEssay = essayCount > 0

    if (taskCount == 0) {
        return CalendarDayVisual(
            past = if (hasEssay) PastCheckInDisplay.WRITER else PastCheckInDisplay.EMPTY,
            future = null,
            useMutedFutureStyle = false,
        )
    }

    val allDone = done == taskCount
    if (allDone) {
        return CalendarDayVisual(
            past = if (hasEssay) PastCheckInDisplay.PERFECT else PastCheckInDisplay.EXCELLENT,
            future = null,
            useMutedFutureStyle = false,
        )
    }

    val ratio = done.toFloat() / taskCount.toFloat()
    val past = when {
        ratio >= 0.5f || done > 0 || hasEssay -> PastCheckInDisplay.CHEER
        else -> PastCheckInDisplay.SEEDLING
    }
    return CalendarDayVisual(past = past, future = null, useMutedFutureStyle = false)
}

fun pastTierCountsInMonth(
    yearMonth: java.time.YearMonth,
    today: LocalDate,
    tasksByDate: Map<LocalDate, List<Task>>,
    essayCountByDate: Map<LocalDate, Int>,
): Map<PastCheckInDisplay, Int> {
    val map = PastCheckInDisplay.entries.associateWith { 0 }.toMutableMap()
    val last = yearMonth.lengthOfMonth()
    for (d in 1..last) {
        val date = yearMonth.atDay(d)
        if (date.isAfter(today)) continue
        val v = calendarDayVisual(
            date = date,
            today = today,
            dayTasks = tasksByDate[date].orEmpty(),
            essayCount = essayCountByDate[date] ?: 0,
        ).past ?: continue
        map[v] = (map[v] ?: 0) + 1
    }
    return map
}
