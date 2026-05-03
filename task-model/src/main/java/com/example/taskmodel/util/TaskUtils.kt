package com.example.taskmodel.util

import androidx.compose.ui.graphics.Color
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task
import java.time.LocalDate

object TaskUtils {
    private val urgencyColorMap = mapOf(
        TaskConstants.URGENCY_URGENT to Color(0xFFFF4444),
        TaskConstants.URGENCY_HIGH to Color(0xFFFF9800),
        TaskConstants.URGENCY_NORMAL to Color(0xFFFFFC3A),
        TaskConstants.URGENCY_LOW to Color(0xFF9E9E9E)
    )

    private val timeBlockColorMap = mapOf(
        TaskConstants.TIME_BLOCK_ANYTIME to Color(0xFFF2EEE6),
        TaskConstants.TIME_BLOCK_MORNING to Color(0xFFFFF8E6),
        TaskConstants.TIME_BLOCK_AFTERNOON to Color(0xFFFBE1D6),
        TaskConstants.TIME_BLOCK_EVENING to Color(0xFFECE7FF)
    )

    private val timeBlockTitleColorMap = mapOf(
        TaskConstants.TIME_BLOCK_ANYTIME to Color(0xFF6C5C4A),
        TaskConstants.TIME_BLOCK_MORNING to Color(0xFF8A6E2F),
        TaskConstants.TIME_BLOCK_AFTERNOON to Color(0xFF9B5A40),
        TaskConstants.TIME_BLOCK_EVENING to Color(0xFF5C4F96)
    )

    fun getUrgencyColor(urgency: Int): Color {
        return urgencyColorMap[urgency] ?: urgencyColorMap.getValue(TaskConstants.URGENCY_LOW)
    }

    fun getTimeBlockColor(timeBlock: String): Color {
        return timeBlockColorMap[timeBlock] ?: timeBlockColorMap.getValue(TaskConstants.TIME_BLOCK_ANYTIME)
    }

    fun getTimeBlockTitleColor(timeBlock: String): Color {
        return timeBlockTitleColorMap[timeBlock]
            ?: timeBlockTitleColorMap.getValue(TaskConstants.TIME_BLOCK_ANYTIME)
    }

    fun sortTasks(tasks: List<Task>): List<Task> {
        return tasks.sortedWith(
            compareBy<Task> { it.isCompleted }
                .thenBy { it.urgency }
                .thenBy { it.id }
        )
    }

    /**
     * Complete only today's instance (recurring tasks "Complete today").
     */
    fun completeToday(tasks: List<Task>, target: Task): List<Task> {
        return tasks.map { task ->
            if (task.id == target.id) task.copy(isCompleted = true) else task
        }
    }

    /**
     * Complete every occurrence in the same recurring series (“Close all”).
     */
    fun closeAllOccurrences(tasks: List<Task>, target: Task): List<Task> {
        return tasks.map { task ->
            if (isSameSeries(task, target)) task.copy(isCompleted = true) else task
        }
    }

    /**
     * Stop repeating from the target task’s day onward:
     * 1) Remove later-dated tasks in the same series.
     * 2) Keep same-day and earlier; set that day’s row repeatRule to NONE.
     */
    fun stopRepeat(tasks: List<Task>, target: Task): List<Task> {
        val cutoffDate = target.taskDate
        return tasks.mapNotNull { task ->
            val sameTitleAndBlock =
                task.title == target.title &&
                    task.timeBlock == target.timeBlock

            if (!sameTitleAndBlock) {
                task
            } else {
                when {
                    task.taskDate.isAfter(cutoffDate) -> null
                    task.taskDate.isEqual(cutoffDate) || task.id == target.id ->
                        task.copy(repeatRule = TaskConstants.REPEAT_RULE_NONE)
                    else -> task
                }
            }
        }
    }

    private fun isSameSeries(task: Task, target: Task): Boolean {
        if (target.repeatRule == TaskConstants.REPEAT_RULE_NONE) {
            // Cross-day tasks without repeatRule: same series = same title + time block.
            return task.title == target.title &&
                task.timeBlock == target.timeBlock
        }
        return task.title == target.title &&
            task.timeBlock == target.timeBlock &&
            task.repeatRule == target.repeatRule
    }
}
