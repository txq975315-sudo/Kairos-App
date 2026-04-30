package com.example.taskmodel.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.taskmodel.model.Task
import com.example.taskmodel.repository.TaskRepository
import com.example.taskmodel.store.TaskCreationBus
import com.example.taskmodel.util.TaskUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val onboardingHandled: Boolean = false
)

class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    private val refreshingFromBus = MutableStateFlow(0)

    val uiState: StateFlow<TaskUiState> = combine(
        repository.tasksFlow,
        repository.onboardingHandledFlow,
        refreshingFromBus
    ) { tasks, onboardingHandled, _ ->
        TaskUiState(
            tasks = tasks,
            onboardingHandled = onboardingHandled
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

    companion object {
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
