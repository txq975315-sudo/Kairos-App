package com.example.kairosapplication.ui.mine.records

import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Task
import com.example.taskmodel.viewmodel.TaskUiState
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit

object MineRecordsStats {

    fun compute(ui: TaskUiState, scope: MineRecordsScope, calendarYear: Int = LocalDate.now().year): MineRecordsOverview {
        val today = LocalDate.now()
        val period = periodBounds(ui, scope, calendarYear) ?: return MineRecordsOverview(
            todayIncomplete = todayIncompleteAlways(ui, today),
        )
        val start = period.first
        val end = period.second
        val published = publishedNotes(ui)
        val tasksInPeriod = ui.tasks.filter { it.taskDate in start..end }
        val notesInPeriod = published.filter { it.recordedDate in start..end }

        val completedInPeriod = tasksInPeriod.count { it.isCompleted }
        val totalTasks = tasksInPeriod.size
        val rate = if (totalTasks == 0) 0 else ((completedInPeriod * 100f) / totalTasks).toInt().coerceIn(0, 100)

        val completionDistinctDays = tasksInPeriod
            .filter { it.isCompleted }
            .map { it.taskDate }
            .toSet()
            .size

        val tasksByDate = tasksInPeriod.groupBy { it.taskDate }
        val notesByDate = notesInPeriod.groupBy { it.recordedDate }

        val anchor = today.coerceIn(start, end)
        val dim = dayCountInclusive(start, end).coerceAtLeast(1)

        val charLens = notesInPeriod.map { it.body.length + it.behaviorSummary.length }
        val totalChars = charLens.sumOf { it.toLong() }
        val maxChars = charLens.maxOrNull() ?: 0

        val longestAllTasksStreak = maxConsecutiveStreak(start, end) { day ->
            val dt = tasksByDate[day].orEmpty()
            dt.isNotEmpty() && dt.all { it.isCompleted }
        }
        val longestBothStreak = maxConsecutiveStreak(start, end) { day ->
            val dt = tasksByDate[day].orEmpty()
            notesByDate[day].orEmpty().isNotEmpty() && dt.isNotEmpty() && dt.all { it.isCompleted }
        }
        val maxStreakRecord = maxConsecutiveStreak(start, end) { hasRecord(it, tasksByDate, notesByDate) }
        val peakContinuityStreak = maxOf(maxStreakRecord, longestAllTasksStreak, longestBothStreak)

        val ymNow = YearMonth.from(today)
        var monthActiveDays = 0
        for (dom in 1..ymNow.lengthOfMonth()) {
            val d = ymNow.atDay(dom)
            if (!d.isBefore(start) && !d.isAfter(end) && hasRecord(d, tasksByDate, notesByDate)) {
                monthActiveDays++
            }
        }

        return MineRecordsOverview(
            completionDaysDistinct = completionDistinctDays,
            completedTaskCount = completedInPeriod,
            todayIncomplete = todayIncompleteAlways(ui, today),
            notesCount = notesInPeriod.size,
            tasksTotalCount = totalTasks,
            completionRate = rate,
            streakRecord = streakBackward(anchor, start) { hasRecord(it, tasksByDate, notesByDate) },
            streakAllTasksDone = streakBackward(anchor, start) { day ->
                val dt = tasksByDate[day].orEmpty()
                dt.isNotEmpty() && dt.all { it.isCompleted }
            },
            streakBothModules = streakBackward(anchor, start) { day ->
                tasksByDate[day].orEmpty().isNotEmpty() &&
                    notesByDate[day].orEmpty().isNotEmpty() &&
                    tasksByDate[day].orEmpty().all { it.isCompleted }
            },
            maxStreakRecord = maxStreakRecord,
            longestAllTasksStreak = longestAllTasksStreak,
            longestBothStreak = longestBothStreak,
            peakContinuityStreak = peakContinuityStreak,
            monthActiveDays = monthActiveDays,
            totalChars = totalChars,
            maxNoteChars = maxChars,
            avgDailyTasks = totalTasks.toFloat() / dim,
            avgDailyNotes = notesInPeriod.size.toFloat() / dim,
        )
    }

    private fun todayIncompleteAlways(ui: TaskUiState, today: LocalDate): Int =
        ui.tasks.count { !it.isCompleted && it.taskDate == today }

    private fun publishedNotes(ui: TaskUiState): List<Note> =
        ui.notePublished.filter { it.status == NoteStatus.PUBLISHED }

    /** Inclusive period start/end, or null if no data. */
    private fun periodBounds(ui: TaskUiState, scope: MineRecordsScope, calendarYear: Int): Pair<LocalDate, LocalDate>? {
        val today = LocalDate.now()
        val published = publishedNotes(ui)
        val dates = mutableListOf<LocalDate>()
        ui.tasks.forEach { dates.add(it.taskDate) }
        published.forEach { dates.add(it.recordedDate) }
        val first = dates.minOrNull() ?: return null

        return when (scope) {
            MineRecordsScope.ALL_TIME -> first to today
            MineRecordsScope.THIS_YEAR -> {
                val yStart = LocalDate.of(calendarYear, 1, 1)
                val yEnd = LocalDate.of(calendarYear, 12, 31).coerceAtMost(today)
                if (yEnd.isBefore(yStart)) null else yStart to yEnd
            }
        }
    }

    private fun hasRecord(
        day: LocalDate,
        tasksByDate: Map<LocalDate, List<Task>>,
        notesByDate: Map<LocalDate, List<Note>>,
    ): Boolean =
        tasksByDate[day].orEmpty().isNotEmpty() || notesByDate[day].orEmpty().isNotEmpty()

    private fun streakBackward(anchor: LocalDate, periodStart: LocalDate, pred: (LocalDate) -> Boolean): Int {
        if (anchor.isBefore(periodStart)) return 0
        var d = anchor
        var c = 0
        while (!d.isBefore(periodStart)) {
            if (!pred(d)) break
            c++
            d = d.minusDays(1)
        }
        return c
    }

    private fun maxConsecutiveStreak(start: LocalDate, end: LocalDate, pred: (LocalDate) -> Boolean): Int {
        var max = 0
        var cur = 0
        var d = start
        while (!d.isAfter(end)) {
            if (pred(d)) {
                cur++
                max = maxOf(max, cur)
            } else {
                cur = 0
            }
            d = d.plusDays(1)
        }
        return max
    }

    private fun dayCountInclusive(start: LocalDate, end: LocalDate): Int =
        ChronoUnit.DAYS.between(start, end).toInt() + 1
}
