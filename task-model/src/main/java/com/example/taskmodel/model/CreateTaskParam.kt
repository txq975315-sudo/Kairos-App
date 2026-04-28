package com.example.taskmodel.model

import com.example.taskmodel.constants.TaskConstants
import java.time.LocalDate

data class CreateTaskParam(
    val title: String,
    val description: String = "",
    val timeBlock: String = TaskConstants.TIME_BLOCK_ANYTIME,
    val urgency: Int = TaskConstants.URGENCY_LOW,
    val label: String? = null,
    val taskDate: LocalDate = LocalDate.now(),
    val repeatRule: String = TaskConstants.REPEAT_RULE_NONE,
    val emojiImage: String? = null,
    val localImageUri: String? = null
) {
    fun toTask(id: Int): Task {
        return Task(
            id = id,
            title = title.trim(),
            description = description.trim(),
            timeBlock = timeBlock,
            urgency = urgency,
            label = label?.trim()?.ifBlank { null },
            taskDate = taskDate,
            repeatRule = repeatRule,
            isCompleted = false,
            emojiImage = emojiImage,
            localImageUri = localImageUri
        )
    }
}
