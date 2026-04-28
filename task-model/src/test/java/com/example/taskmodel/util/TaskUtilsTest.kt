package com.example.taskmodel.util

import androidx.compose.ui.graphics.Color
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class TaskUtilsTest {

    @Test
    fun getUrgencyColor_returnsExpectedColor() {
        assertEquals(Color(0xFFFF4444), TaskUtils.getUrgencyColor(TaskConstants.URGENCY_URGENT))
        assertEquals(Color(0xFFFF9800), TaskUtils.getUrgencyColor(TaskConstants.URGENCY_HIGH))
        assertEquals(Color(0xFFFFFC3A), TaskUtils.getUrgencyColor(TaskConstants.URGENCY_NORMAL))
        assertEquals(Color(0xFF9E9E9E), TaskUtils.getUrgencyColor(TaskConstants.URGENCY_LOW))
        assertEquals(Color(0xFF9E9E9E), TaskUtils.getUrgencyColor(999))
    }

    @Test
    fun getTimeBlockColor_returnsExpectedColor() {
        assertEquals(Color(0xFFF2EEE6), TaskUtils.getTimeBlockColor(TaskConstants.TIME_BLOCK_ANYTIME))
        assertEquals(Color(0xFFFFF8E6), TaskUtils.getTimeBlockColor(TaskConstants.TIME_BLOCK_MORNING))
        assertEquals(Color(0xFFFBE1D6), TaskUtils.getTimeBlockColor(TaskConstants.TIME_BLOCK_AFTERNOON))
        assertEquals(Color(0xFFECE7FF), TaskUtils.getTimeBlockColor(TaskConstants.TIME_BLOCK_EVENING))
        assertEquals(Color(0xFFF2EEE6), TaskUtils.getTimeBlockColor("UNKNOWN"))
    }

    @Test
    fun sortTasks_movesCompletedToBottom_andKeepsHigherUrgencyFirst() {
        val date = LocalDate.of(2026, 4, 28)
        val source = listOf(
            Task(id = 3, title = "C", timeBlock = TaskConstants.TIME_BLOCK_ANYTIME, urgency = 2, isCompleted = true, taskDate = date),
            Task(id = 4, title = "D", timeBlock = TaskConstants.TIME_BLOCK_ANYTIME, urgency = 1, isCompleted = false, taskDate = date),
            Task(id = 2, title = "B", timeBlock = TaskConstants.TIME_BLOCK_ANYTIME, urgency = 0, isCompleted = false, taskDate = date),
            Task(id = 1, title = "A", timeBlock = TaskConstants.TIME_BLOCK_ANYTIME, urgency = 3, isCompleted = true, taskDate = date)
        )

        val sorted = TaskUtils.sortTasks(source)

        assertEquals(listOf(2, 4, 3, 1), sorted.map { it.id })
        assertFalse(sorted[0].isCompleted)
        assertFalse(sorted[1].isCompleted)
        assertTrue(sorted[2].isCompleted)
        assertTrue(sorted[3].isCompleted)
    }

    @Test
    fun closeAllOccurrences_marksSameSeriesCompleted() {
        val tasks = listOf(
            Task(1, "Workout", timeBlock = TaskConstants.TIME_BLOCK_MORNING, urgency = 1, repeatRule = "WEEKLY_MON"),
            Task(2, "Workout", timeBlock = TaskConstants.TIME_BLOCK_MORNING, urgency = 1, repeatRule = "WEEKLY_MON"),
            Task(3, "Read", timeBlock = TaskConstants.TIME_BLOCK_EVENING, urgency = 2, repeatRule = TaskConstants.REPEAT_RULE_NONE)
        )
        val updated = TaskUtils.closeAllOccurrences(tasks, tasks[0])
        assertTrue(updated[0].isCompleted)
        assertTrue(updated[1].isCompleted)
        assertFalse(updated[2].isCompleted)
    }

    @Test
    fun stopRepeat_clearsSeriesRepeatRule() {
        val today = LocalDate.of(2026, 4, 29)
        val tomorrow = today.plusDays(1)
        val tasks = listOf(
            Task(1, "Workout", timeBlock = TaskConstants.TIME_BLOCK_MORNING, urgency = 1, repeatRule = "WEEKLY_MON", taskDate = today),
            Task(2, "Workout", timeBlock = TaskConstants.TIME_BLOCK_MORNING, urgency = 1, repeatRule = "WEEKLY_MON", taskDate = tomorrow)
        )
        val updated = TaskUtils.stopRepeat(tasks, tasks[0])
        assertEquals(TaskConstants.REPEAT_RULE_NONE, updated[0].repeatRule)
        assertEquals(1, updated.size)
    }

}
