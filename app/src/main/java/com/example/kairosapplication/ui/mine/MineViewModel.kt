package com.example.kairosapplication.ui.mine

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kairosapplication.data.DataStoreManager
import com.example.taskmodel.model.AllRecords
import com.example.taskmodel.model.LocalProfile
import com.example.taskmodel.model.MoodRecord
import com.example.taskmodel.model.WeeklyInsights
import com.example.taskmodel.viewmodel.TaskUiState
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MineViewModel(
    private val dataStoreManager: DataStoreManager,
    private val taskUiState: StateFlow<TaskUiState>
) : ViewModel() {

    val profileState: StateFlow<LocalProfile> = dataStoreManager.profileFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = LocalProfile()
        )

    val allMoods: StateFlow<List<MoodRecord>> = dataStoreManager.moodsFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val todayMood: StateFlow<MoodRecord?> = allMoods
        .map { list ->
            val t = LocalDate.now()
            list.find { it.date == t }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val weekMoods: StateFlow<List<MoodRecord>> = allMoods
        .map { list ->
            val monday = mondayOfWeekContaining(LocalDate.now())
            val inWeek = (0..6).map { monday.plusDays(it.toLong()) }.toSet()
            list.filter { it.date in inWeek }.sortedBy { it.date }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val recordDays: StateFlow<Int> = taskUiState
        .map { computeRecordDaysFromUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0
        )

    val allRecords: StateFlow<AllRecords> = taskUiState
        .map { ui ->
            val today = LocalDate.now()
            val tasks = ui.tasks
            AllRecords(
                completedTasks = tasks.count { it.isCompleted },
                uncompletedTasks = tasks.count { !it.isCompleted },
                todayCompletedTasks = tasks.count { it.taskDate == today && it.isCompleted }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AllRecords(0, 0, 0)
        )

    val weekDaysWithRecord: StateFlow<List<LocalDate>> = taskUiState
        .map { computeWeekDaysWithRecord(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val weeklyInsights: StateFlow<WeeklyInsights> = taskUiState
        .map { ui ->
            val days = computeWeekDaysWithRecord(ui)
            WeeklyInsights(
                consecutiveDays = computeConsecutiveInCurrentWeek(ui),
                daysWithRecord = days.size,
                totalRecordDays = 0
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WeeklyInsights(0, 0, 0)
        )

    val weeklyInsightsEnabled: StateFlow<Boolean> = dataStoreManager.weeklyInsightsEnabledFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = true
        )

    fun loadProfile() {
        viewModelScope.launch {
            dataStoreManager.getProfile()
        }
    }

    fun saveProfile(profile: LocalProfile) {
        viewModelScope.launch {
            dataStoreManager.saveProfile(profile)
        }
    }

    fun calculateRecordDays(): Int = computeRecordDaysFromUiState(taskUiState.value)

    fun toggleWeeklyInsights(enabled: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setWeeklyInsightsEnabled(enabled)
        }
    }

    fun saveMood(moodIcon: String, date: LocalDate) {
        viewModelScope.launch {
            val monday = mondayOfWeekContaining(LocalDate.now())
            val weekEnd = monday.plusDays(6)
            if (date.isBefore(monday) || date.isAfter(weekEnd)) return@launch
            val all = dataStoreManager.getMoods().toMutableList()
            all.removeAll { it.date == date }
            all.add(MoodRecord(date, moodIcon))
            val cutoff = LocalDate.now().minusDays(364)
            val trimmed = all.filter { !it.date.isBefore(cutoff) }.sortedBy { it.date }
            dataStoreManager.saveMoods(trimmed)
        }
    }

    fun monthMoodsSnapshot(ym: YearMonth): List<MoodRecord> {
        return allMoods.value.filter { YearMonth.from(it.date) == ym }
    }

    fun monthMoodStatsSnapshot(ym: YearMonth): Map<String, Int> {
        return monthMoodsSnapshot(ym).groupingBy { it.moodIcon }.eachCount()
    }

    companion object {
        fun factory(
            appContext: Context,
            taskUiState: StateFlow<TaskUiState>
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val ctx = appContext.applicationContext
                return MineViewModel(
                    DataStoreManager(ctx),
                    taskUiState
                ) as T
            }
        }

        fun mondayOfWeekContaining(date: LocalDate): LocalDate =
            date.minusDays((date.dayOfWeek.value - 1).toLong())

        fun hasRecordOnDay(day: LocalDate, ui: TaskUiState): Boolean {
            if (ui.tasks.any { it.taskDate == day }) return true
            if (ui.notePublished.any { it.recordedDate == day }) return true
            return false
        }

        fun computeWeekDaysWithRecord(ui: TaskUiState): List<LocalDate> {
            val monday = mondayOfWeekContaining(LocalDate.now())
            return (0..6).map { monday.plusDays(it.toLong()) }
                .filter { hasRecordOnDay(it, ui) }
        }

        fun computeConsecutiveInCurrentWeek(ui: TaskUiState): Int {
            val today = LocalDate.now()
            val monday = mondayOfWeekContaining(today)
            val weekEnd = monday.plusDays(6)
            var count = 0
            var d = today
            while (!d.isBefore(monday) && !d.isAfter(weekEnd)) {
                if (!hasRecordOnDay(d, ui)) break
                count++
                d = d.minusDays(1)
            }
            return count
        }

        fun computeRecordDaysFromUiState(ui: TaskUiState): Int {
            val dates = mutableListOf<LocalDate>()
            for (t in ui.tasks) {
                dates.add(t.taskDate)
            }
            for (n in ui.notePublished) {
                dates.add(n.recordedDate)
            }
            for (n in ui.noteInbox) {
                dates.add(n.recordedDate)
            }
            for (n in ui.noteTrash) {
                dates.add(n.recordedDate)
            }
            if (dates.isEmpty()) return 0
            val first = dates.minOrNull() ?: return 0
            val today = LocalDate.now()
            if (first.isAfter(today)) return 0
            return ChronoUnit.DAYS.between(first, today).toInt() + 1
        }
    }
}
