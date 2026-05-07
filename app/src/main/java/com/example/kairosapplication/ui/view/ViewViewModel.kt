package com.example.kairosapplication.ui.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.view.month.MonthOverviewMetric
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Project
import com.example.taskmodel.model.Task
import com.example.taskmodel.repository.NoteRepository
import com.example.taskmodel.repository.TaskRepository
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class DayCalendarData(
    val taskCount: Int,
    val taskCompletedCount: Int,
    val noteCount: Int,
    val dominantTaskTimeBlock: String?,
    val dominantTaskUrgency: Int?,
    val dominantNoteCategory: String?,
)

data class ViewUiState(
    val focusedDate: LocalDate = LocalDate.now(),
    val calendarYearMonth: YearMonth = YearMonth.now(),
    val todayTasks: List<Task> = emptyList(),
    val todayNotes: List<Note> = emptyList(),
    val weekTasks: Map<LocalDate, List<Task>> = emptyMap(),
    val weekNotes: Map<LocalDate, List<Note>> = emptyMap(),
    val monthOverview: Overview = Overview(),
    val monthOverviewMetrics: List<MonthOverviewMetric> = MonthOverviewMetric.defaultSelection(),
    val monthCalendarData: Map<LocalDate, DayCalendarData> = emptyMap(),
    val currentWeekRange: Pair<LocalDate, LocalDate> = weekRangeForOffset(0),
    val noteProjects: List<Project> = emptyList(),
    val viewingCurrentWeek: Boolean = true,
)

class ViewViewModel(
    private val application: Application,
    private val taskViewModel: TaskViewModel,
) : ViewModel() {

    private val taskRepository = TaskRepository(application)
    private val noteRepository = NoteRepository(application)
    private val dataStoreManager = DataStoreManager(application)
    private val currentWeekOffset = MutableStateFlow(0)
    private val focusedDateFlow = MutableStateFlow(LocalDate.now())
    private val calendarYearMonthFlow = MutableStateFlow(YearMonth.now())

    private val baseInputs = combine(
        taskRepository.tasksFlow,
        noteRepository.observePublishedNotes(),
        noteRepository.observeProjects(),
        currentWeekOffset,
    ) { tasks, publishedNotes, projects, weekOffset ->
        ViewBaseInputs(tasks, publishedNotes, projects, weekOffset)
    }

    private val monthOverviewMetricsFlow =
        dataStoreManager.getMonthOverviewMetricsEncoded().map { raw ->
            MonthOverviewMetric.parseStored(raw)
        }

    val uiState: StateFlow<ViewUiState> = combine(
        baseInputs,
        focusedDateFlow,
        calendarYearMonthFlow,
        monthOverviewMetricsFlow,
    ) { base, focusedDate, calYm, overviewMetrics ->
        buildViewUiState(
            base.tasks,
            base.publishedNotes,
            base.projects,
            base.weekOffset,
            focusedDate,
            calYm,
            overviewMetrics,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ViewUiState(),
    )

    fun setFocusedDate(date: LocalDate) {
        focusedDateFlow.value = date
    }

    fun shiftFocusedDateBy(deltaDays: Long) {
        focusedDateFlow.value = focusedDateFlow.value.plusDays(deltaDays)
    }

    fun setCalendarYearMonth(ym: YearMonth) {
        calendarYearMonthFlow.value = ym
    }

    fun shiftCalendarYearMonthBy(deltaMonths: Long) {
        calendarYearMonthFlow.value = calendarYearMonthFlow.value.plusMonths(deltaMonths)
    }

    fun switchToPreviousWeek() {
        currentWeekOffset.value = currentWeekOffset.value - 1
    }

    fun switchToNextWeek() {
        currentWeekOffset.value = currentWeekOffset.value + 1
    }

    fun resetToCurrentWeek() {
        currentWeekOffset.value = 0
    }

    fun setMonthOverviewMetrics(metrics: List<MonthOverviewMetric>) {
        val next = metrics.distinct().ifEmpty { MonthOverviewMetric.defaultSelection() }
        viewModelScope.launch {
            dataStoreManager.setMonthOverviewMetricsEncoded(MonthOverviewMetric.encode(next))
        }
    }

    fun updateNoteTopic(noteId: Long, primary: String, secondary: String) {
        val n = taskViewModel.uiState.value.notePublished.find { it.id == noteId } ?: return
        val topic = NotePrimaryCategory.isTopic(primary)
        val appCtx = application.applicationContext
        val lang = LocalizationManager.Language.fromCode(dataStoreManager.getLanguageSync())
        val newSummary = if (topic && n.behaviorSummary.isBlank()) {
            LocalizedStrings.stringFor(lang, "note_default_behavior_summary", appCtx)
        } else {
            n.behaviorSummary
        }
        val updated = n.copy(
            primaryCategory = primary,
            secondaryCategory = if (topic) secondary else "",
            behaviorSummary = newSummary,
            linkedCategories = if (topic) {
                n.linkedCategories.filter {
                    it != primary && it != NotePrimaryCategory.FREESTYLE
                }
            } else {
                emptyList()
            },
            updatedAt = System.currentTimeMillis(),
        )
        taskViewModel.updateRoomNote(updated)
    }

    fun deleteNote(noteId: Long) {
        taskViewModel.softDeleteRoomNote(noteId)
    }

    companion object {
        fun factory(application: Application, taskViewModel: TaskViewModel): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(ViewViewModel::class.java)) {
                        return ViewViewModel(application, taskViewModel) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
    }
}

private data class ViewBaseInputs(
    val tasks: List<Task>,
    val publishedNotes: List<Note>,
    val projects: List<Project>,
    val weekOffset: Int,
)

private val topicDominanceOrder = listOf(
    NotePrimaryCategory.SELF_AWARENESS,
    NotePrimaryCategory.INTERPERSONAL,
    NotePrimaryCategory.INTIMACY_FAMILY,
    NotePrimaryCategory.SOMATIC_ENERGY,
    NotePrimaryCategory.MEANING,
    NotePrimaryCategory.FREESTYLE,
)

private fun weekStartMonday(date: LocalDate): LocalDate =
    date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

private fun weekRangeForOffset(weekOffset: Int): Pair<LocalDate, LocalDate> {
    val base = LocalDate.now().plusWeeks(weekOffset.toLong())
    val start = weekStartMonday(base)
    val end = start.plusDays(6)
    return start to end
}

private fun daysInClosedRange(start: LocalDate, end: LocalDate): List<LocalDate> {
    val out = ArrayList<LocalDate>((end.toEpochDay() - start.toEpochDay()).toInt() + 1)
    var d = start
    while (!d.isAfter(end)) {
        out.add(d)
        d = d.plusDays(1)
    }
    return out
}

private fun sortTasksForView(list: List<Task>): List<Task> =
    list.sortedWith(
        compareBy<Task> { it.urgency }
            .thenBy { if (it.isCompleted) 1 else 0 }
            .thenByDescending { it.id },
    )

private fun publishedNotesForDate(notes: List<Note>, day: LocalDate): List<Note> =
    notes
        .filter { it.status == NoteStatus.PUBLISHED && it.recordedDate == day }
        .sortedByDescending { it.createdAt }

private fun buildDayCalendarData(
    day: LocalDate,
    tasks: List<Task>,
    publishedNotes: List<Note>,
): DayCalendarData {
    val dayTasks = tasks.filter { it.taskDate == day }
    val dayNotes = publishedNotes.filter { it.status == NoteStatus.PUBLISHED && it.recordedDate == day }
    val taskCount = dayTasks.size
    val taskCompletedCount = dayTasks.count { it.isCompleted }
    val noteCount = dayNotes.size
    val dominantTaskTimeBlock = if (dayTasks.isEmpty()) {
        null
    } else {
        val byBlock = dayTasks.groupingBy { it.timeBlock }.eachCount()
        val maxC = byBlock.values.maxOrNull() ?: 0
        TaskConstants.TIME_BLOCKS.firstOrNull { byBlock.getOrDefault(it, 0) == maxC }
    }
    val dominantTaskUrgency = if (dayTasks.isEmpty()) null else dayTasks.minOf { it.urgency }
    val dominantNoteCategory = if (dayNotes.isEmpty()) {
        null
    } else {
        val byCat = dayNotes
            .mapNotNull { n -> n.primaryCategory.takeIf { it.isNotBlank() } }
            .groupingBy { it }
            .eachCount()
        if (byCat.isEmpty()) {
            NotePrimaryCategory.FREESTYLE
        } else {
            val maxN = byCat.values.maxOrNull() ?: 0
            topicDominanceOrder.firstOrNull { byCat.getOrDefault(it, 0) == maxN }
                ?: byCat.keys.first()
        }
    }
    return DayCalendarData(
        taskCount = taskCount,
        taskCompletedCount = taskCompletedCount,
        noteCount = noteCount,
        dominantTaskTimeBlock = dominantTaskTimeBlock,
        dominantTaskUrgency = dominantTaskUrgency,
        dominantNoteCategory = dominantNoteCategory,
    )
}

private fun buildMonthCalendarData(
    ym: YearMonth,
    tasks: List<Task>,
    publishedNotes: List<Note>,
): Map<LocalDate, DayCalendarData> {
    val start = ym.atDay(1)
    val end = ym.atEndOfMonth()
    val out = LinkedHashMap<LocalDate, DayCalendarData>()
    var d = start
    while (!d.isAfter(end)) {
        out[d] = buildDayCalendarData(d, tasks, publishedNotes)
        d = d.plusDays(1)
    }
    return out
}

private fun buildViewUiState(
    tasks: List<Task>,
    publishedNotes: List<Note>,
    projects: List<Project>,
    weekOffset: Int,
    focusedDate: LocalDate,
    calYm: YearMonth,
    monthOverviewMetrics: List<MonthOverviewMetric>,
): ViewUiState {
    val todayTasks = sortTasksForView(tasks.filter { it.taskDate == focusedDate })
    val todayNotes = publishedNotesForDate(publishedNotes, focusedDate)

    val (weekStart, weekEnd) = weekRangeForOffset(weekOffset)
    val weekDays = daysInClosedRange(weekStart, weekEnd)
    val weekTasks = weekDays.associateWith { day ->
        sortTasksForView(tasks.filter { it.taskDate == day })
    }
    val weekNotes = weekDays.associateWith { day ->
        publishedNotes
            .filter { it.status == NoteStatus.PUBLISHED && it.recordedDate == day }
            .sortedByDescending { it.createdAt }
    }

    val monthStart = calYm.atDay(1)
    val monthEnd = calYm.atEndOfMonth()
    val tasksInMonth = tasks.filter { t -> !t.taskDate.isBefore(monthStart) && !t.taskDate.isAfter(monthEnd) }
    val notesInMonth = publishedNotes.filter { n ->
        n.status == NoteStatus.PUBLISHED &&
            !n.recordedDate.isBefore(monthStart) &&
            !n.recordedDate.isAfter(monthEnd)
    }
    val completed = tasksInMonth.count { it.isCompleted }
    val totalT = tasksInMonth.size
    val rate = if (totalT == 0) 0 else ((completed * 100f) / totalT).toInt().coerceIn(0, 100)
    val tasksByDateInMonth = tasksInMonth.groupBy { it.taskDate }
    val notesByDateInMonth = notesInMonth.groupBy { it.recordedDate }
    val anchor = monthOverviewAnchor(calYm)
    val dim = calYm.lengthOfMonth().coerceAtLeast(1)
    val charLens = notesInMonth.map { it.body.length + it.behaviorSummary.length }
    val totalChars = charLens.sumOf { it.toLong() }
    val maxChars = charLens.maxOrNull() ?: 0
    val overview = Overview(
        totalNotes = notesInMonth.size,
        totalTasks = totalT,
        completionRate = rate,
        consecutiveRecordDays = streakBackward(monthStart, anchor) { day ->
            tasksByDateInMonth[day].orEmpty().isNotEmpty() ||
                notesByDateInMonth[day].orEmpty().isNotEmpty()
        },
        consecutiveAllTasksDoneDays = streakBackward(monthStart, anchor) { day ->
            val dt = tasksByDateInMonth[day].orEmpty()
            dt.isNotEmpty() && dt.all { it.isCompleted }
        },
        consecutiveBothModulesDays = streakBackward(monthStart, anchor) { day ->
            tasksByDateInMonth[day].orEmpty().isNotEmpty() &&
                notesByDateInMonth[day].orEmpty().isNotEmpty()
        },
        totalDiaryChars = totalChars,
        maxSingleNoteChars = maxChars,
        avgDailyTasks = totalT.toFloat() / dim,
        avgDailyNotes = notesInMonth.size.toFloat() / dim,
    )
    val monthCalendarData = buildMonthCalendarData(calYm, tasks, publishedNotes)

    return ViewUiState(
        focusedDate = focusedDate,
        calendarYearMonth = calYm,
        todayTasks = todayTasks,
        todayNotes = todayNotes,
        weekTasks = weekTasks,
        weekNotes = weekNotes,
        monthOverview = overview,
        monthOverviewMetrics = monthOverviewMetrics.distinct().ifEmpty {
            MonthOverviewMetric.defaultSelection()
        },
        monthCalendarData = monthCalendarData,
        currentWeekRange = weekStart to weekEnd,
        noteProjects = projects,
        viewingCurrentWeek = weekOffset == 0,
    )
}

private fun monthOverviewAnchor(ym: YearMonth): LocalDate {
    val today = LocalDate.now()
    val start = ym.atDay(1)
    val end = ym.atEndOfMonth()
    return when {
        today.isBefore(start) -> end
        today.isAfter(end) -> end
        else -> today
    }
}

private fun streakBackward(
    monthStart: LocalDate,
    anchor: LocalDate,
    predicate: (LocalDate) -> Boolean,
): Int {
    if (anchor.isBefore(monthStart)) return 0
    var d = anchor
    var streak = 0
    while (!d.isBefore(monthStart)) {
        if (predicate(d)) {
            streak++
            d = d.minusDays(1)
        } else {
            break
        }
    }
    return streak
}
