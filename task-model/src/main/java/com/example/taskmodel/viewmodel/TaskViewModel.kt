package com.example.taskmodel.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.taskmodel.model.Essay
import com.example.taskmodel.model.Task
import com.example.taskmodel.repository.TaskRepository
import com.example.taskmodel.store.TaskCreationBus
import com.example.taskmodel.util.TaskUtils
import java.time.LocalDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val onboardingHandled: Boolean = true,
    val essays: List<Essay> = emptyList(),
    val dailyQuoteEssayId: Long? = null
)

class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    private val refreshingFromBus = MutableStateFlow(0)
    private val deleteMutex = Mutex()
    private val lastDeletedForUndo = MutableStateFlow<Task?>(null)
    private val essayDeleteMutex = Mutex()
    private val lastDeletedEssayForUndo = MutableStateFlow<Essay?>(null)

    val uiState: StateFlow<TaskUiState> = combine(
        repository.tasksFlow,
        repository.onboardingHandledFlow,
        refreshingFromBus,
        repository.essaysFlow,
        repository.dailyQuoteEssayIdFlow
    ) { tasks, onboardingHandled, _, essays, dailyQuoteEssayId ->
        TaskUiState(
            tasks = tasks,
            onboardingHandled = onboardingHandled,
            essays = essays,
            dailyQuoteEssayId = dailyQuoteEssayId
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TaskUiState()
    )

    fun syncFromCreationBus() {
        viewModelScope.launch {
            val incoming = TaskCreationBus.consumeAll()
            if (incoming.isNotEmpty()) {
                repository.appendTasks(incoming)
                refreshingFromBus.value += 1
            }
        }
    }

    fun saveTasks(tasks: List<Task>) {
        viewModelScope.launch {
            repository.saveTasks(tasks)
        }
    }

    fun updateTask(updated: Task) {
        viewModelScope.launch {
            val current = uiState.value.tasks
            val newTasks = current.map { task ->
                if (task.id == updated.id) updated else task
            }
            repository.saveTasks(newTasks)
        }
    }

    fun toggleTaskComplete(task: Task) {
        val current = uiState.value.tasks
        val newTasks = current.map {
            if (it.id == task.id) it.copy(isCompleted = !it.isCompleted) else it
        }
        saveTasks(newTasks)
    }

    fun applyStopRepeat(task: Task) {
        val updated = TaskUtils.stopRepeat(uiState.value.tasks, task)
        saveTasks(updated)
    }

    fun markOnboardingChoice(loadSamples: Boolean) {
        viewModelScope.launch {
            if (loadSamples) {
                repository.loadSampleTasksIfNeeded()
            }
            repository.setOnboardingHandled(true)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteMutex.withLock {
                val current = repository.tasksFlow.first()
                lastDeletedForUndo.value = task
                repository.saveTasks(current.filter { it.id != task.id })
            }
        }
    }

    fun undoDeleteTask() {
        viewModelScope.launch {
            deleteMutex.withLock {
                val t = lastDeletedForUndo.value ?: return@launch
                lastDeletedForUndo.value = null
                repository.appendTasks(listOf(t))
            }
        }
    }

    fun clearDeleteUndo() {
        lastDeletedForUndo.value = null
    }

    fun saveEssay(essay: Essay) {
        viewModelScope.launch {
            val current = repository.essaysFlow.first()
            val without = current.filter { it.id != essay.id }
            repository.saveEssays(without + essay)
        }
    }

    fun deleteEssay(essay: Essay) {
        viewModelScope.launch {
            essayDeleteMutex.withLock {
                val current = repository.essaysFlow.first()
                lastDeletedEssayForUndo.value = essay
                val filtered = current.filter { it.id != essay.id }
                repository.saveEssays(filtered)
                val quoteId = repository.dailyQuoteEssayIdFlow.first()
                if (quoteId == essay.id) {
                    repository.setDailyQuoteEssayId(null)
                }
            }
        }
    }

    fun undoDeleteEssay() {
        viewModelScope.launch {
            essayDeleteMutex.withLock {
                val e = lastDeletedEssayForUndo.value ?: return@launch
                lastDeletedEssayForUndo.value = null
                val current = repository.essaysFlow.first()
                repository.saveEssays(current + e)
            }
        }
    }

    fun clearEssayDeleteUndo() {
        lastDeletedEssayForUndo.value = null
    }

    fun setDailyQuoteFromEssay(essayId: Long) {
        viewModelScope.launch {
            val current = repository.essaysFlow.first()
            val updated = current.map { it.copy(isDailyQuote = it.id == essayId) }
            repository.saveEssays(updated)
            repository.setDailyQuoteEssayId(essayId)
        }
    }

    fun clearDailyQuoteFromEssay() {
        viewModelScope.launch {
            val current = repository.essaysFlow.first()
            repository.saveEssays(current.map { it.copy(isDailyQuote = false) })
            repository.setDailyQuoteEssayId(null)
        }
    }

    /** 首页每日一句展示文案：来自选中的 Essay 首行，无则默认文案 */
    fun dailyQuoteDisplayText(defaultText: String): String {
        val id = uiState.value.dailyQuoteEssayId ?: return defaultText
        val essay = uiState.value.essays.find { it.id == id } ?: return defaultText
        val line = essay.body.trim().lineSequence().firstOrNull()?.trim().orEmpty()
        if (line.isEmpty()) return defaultText
        return if (line.length > 200) line.take(200) + "…" else line
    }

    fun pendingTaskCountForDate(date: LocalDate): Int =
        uiState.value.tasks.count { it.taskDate == date && !it.isCompleted }

    fun wouldExceedDailyPendingLimit(date: LocalDate, additionalIncomplete: Int): Boolean =
        pendingTaskCountForDate(date) + additionalIncomplete > DAILY_PENDING_LIMIT

    fun firstDateExceedingLimitIfAdded(newTasks: List<Task>): LocalDate? {
        val current = uiState.value.tasks
        val addsByDate = newTasks.filter { !it.isCompleted }.groupBy { it.taskDate }
        for ((date, adds) in addsByDate) {
            val n = current.count { it.taskDate == date && !it.isCompleted }
            if (n + adds.size > DAILY_PENDING_LIMIT) return date
        }
        return null
    }

    fun deleteAllTasksForDate(date: LocalDate) {
        viewModelScope.launch {
            deleteMutex.withLock {
                val current = repository.tasksFlow.first()
                repository.saveTasks(current.filter { it.taskDate != date })
            }
        }
    }

    companion object {
        const val DAILY_PENDING_LIMIT = 12

        fun factory(appContext: Context): ViewModelProvider.Factory {
            val repository = TaskRepository(appContext)
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
                        return TaskViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
        }
    }
}
