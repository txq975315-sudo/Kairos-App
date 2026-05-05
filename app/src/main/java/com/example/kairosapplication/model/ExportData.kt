package com.example.kairosapplication.model

import com.example.taskmodel.model.LocalProfile
import com.example.taskmodel.model.MoodRecord
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Task
import java.time.LocalDate

data class ExportData(
    val exportDate: String,
    val appVersion: String,
    val notes: List<NoteExportDto>,
    val tasks: List<TaskExportDto>,
    val moods: List<MoodRecord>,
    val profile: LocalProfile?
)

data class NoteExportDto(
    val id: Long,
    val title: String,
    val content: String,
    val primaryCategory: String,
    val linkedCategories: List<String>,
    val status: String,
    val recordedDate: String,
    val createdAt: Long,
    val secondaryCategory: String = "",
    val behaviorSummary: String = "",
    val sceneTags: List<String> = emptyList(),
    val moodIcon: String? = null,
    val projectIds: List<Long> = emptyList(),
    val imageUris: List<String> = emptyList(),
    val updatedAt: Long = createdAt,
    val deadline: String? = null,
    val needsManualClassification: Boolean = false
)

data class TaskExportDto(
    val id: Long,
    val title: String,
    val taskDate: String,
    val timeBlock: String,
    val isCompleted: Boolean,
    val urgency: Int,
    val createdAt: Long,
    val description: String = "",
    val label: String? = null,
    val repeatRule: String = "NONE",
    val emojiImage: String? = null,
    val localImageUri: String? = null
)

fun NoteExportDto.toNote(): Note = Note(
    id = id,
    primaryCategory = primaryCategory,
    secondaryCategory = secondaryCategory,
    behaviorSummary = behaviorSummary.ifBlank { title },
    body = content,
    sceneTags = sceneTags,
    moodIcon = moodIcon,
    linkedCategories = linkedCategories,
    projectIds = projectIds,
    imageUris = imageUris,
    recordedDate = LocalDate.parse(recordedDate),
    createdAt = createdAt,
    updatedAt = updatedAt,
    status = status,
    deadline = deadline?.let { LocalDate.parse(it) },
    needsManualClassification = needsManualClassification
)

fun TaskExportDto.toTask(): Task {
    val safeId = id.coerceIn(Int.MIN_VALUE.toLong(), Int.MAX_VALUE.toLong()).toInt()
    return Task(
        id = safeId,
        title = title,
        description = description,
        timeBlock = timeBlock,
        urgency = urgency,
        label = label,
        taskDate = LocalDate.parse(taskDate),
        repeatRule = repeatRule,
        isCompleted = isCompleted,
        emojiImage = emojiImage,
        localImageUri = localImageUri
    )
}

fun Note.toExportDto(): NoteExportDto = NoteExportDto(
    id = id,
    title = behaviorSummary.ifBlank { body.lineSequence().firstOrNull()?.take(120) ?: "" },
    content = body,
    primaryCategory = primaryCategory,
    linkedCategories = linkedCategories,
    status = status,
    recordedDate = recordedDate.toString(),
    createdAt = createdAt,
    secondaryCategory = secondaryCategory,
    behaviorSummary = behaviorSummary,
    sceneTags = sceneTags,
    moodIcon = moodIcon,
    projectIds = projectIds,
    imageUris = imageUris,
    updatedAt = updatedAt,
    deadline = deadline?.toString(),
    needsManualClassification = needsManualClassification
)

fun Task.toExportDto(): TaskExportDto = TaskExportDto(
    id = id.toLong(),
    title = title,
    taskDate = taskDate.toString(),
    timeBlock = timeBlock,
    isCompleted = isCompleted,
    urgency = urgency,
    createdAt = 0L,
    description = description,
    label = label,
    repeatRule = repeatRule,
    emojiImage = emojiImage,
    localImageUri = localImageUri
)
