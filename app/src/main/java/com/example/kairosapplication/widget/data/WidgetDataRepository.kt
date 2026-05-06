package com.example.kairosapplication.widget.data

import com.example.taskmodel.model.Task
import com.example.taskmodel.util.TaskUtils
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class WidgetTaskData(
    val completedCount: Int,
    val totalCount: Int,
    val tasks: List<Task>,
    val progressPercent: Float
)

data class WidgetQuoteData(
    val text: String,
    val date: LocalDate
)

class WidgetDataRepository(
    private val taskViewModel: TaskViewModel
) {
    private val dateFormatter = DateTimeFormatter.ofPattern("MM-dd")

    fun getTodayTaskData(): WidgetTaskData {
        val today = LocalDate.now()
        val uiState = taskViewModel.uiState.value
        val todayTasks = uiState.tasks.filter { it.taskDate == today }
        val sortedTasks = TaskUtils.sortTasks(todayTasks)
        val completed = todayTasks.count { it.isCompleted }
        val total = todayTasks.size
        val progress = if (total > 0) completed.toFloat() / total.toFloat() else 0f
        return WidgetTaskData(
            completedCount = completed,
            totalCount = total,
            tasks = sortedTasks.take(5),
            progressPercent = progress.coerceIn(0f, 1f)
        )
    }

    fun getTodayDateString(): String {
        return LocalDate.now().format(dateFormatter)
    }

    fun getDailyQuote(defaultText: String): String {
        return taskViewModel.dailyQuoteDisplayText(defaultText)
    }

    fun getAllTasks(): List<Task> = taskViewModel.uiState.value.tasks
}
