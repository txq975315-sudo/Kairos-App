package com.example.taskmodel.repository

import com.example.taskmodel.database.NoteEntity
import com.example.taskmodel.database.ProjectEntity
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Project
import java.time.LocalDate

internal fun NoteEntity.toDomain(): Note =
    Note(
        id = id,
        primaryCategory = primaryCategory,
        secondaryCategory = secondaryCategory.orEmpty(),
        behaviorSummary = behaviorSummary.orEmpty(),
        body = body,
        sceneTags = sceneTags,
        moodIcon = moodIcon,
        linkedCategories = linkedCategories,
        projectIds = projectIds,
        imageUris = imageUris,
        recordedDate = LocalDate.ofEpochDay(recordedDateEpochDay),
        createdAt = createdAt,
        updatedAt = updatedAt,
        status = status,
        deadline = inboxDeadlineEpochDay?.let(LocalDate::ofEpochDay),
        needsManualClassification = needsManualClassification
    )

internal fun Note.toEntity(): NoteEntity =
    NoteEntity(
        id = id,
        primaryCategory = primaryCategory,
        secondaryCategory = secondaryCategory.ifBlank { null },
        behaviorSummary = behaviorSummary.ifBlank { null },
        body = body,
        sceneTags = sceneTags,
        moodIcon = moodIcon,
        linkedCategories = linkedCategories,
        projectIds = projectIds,
        imageUris = imageUris,
        recordedDateEpochDay = recordedDate.toEpochDay(),
        createdAt = createdAt,
        updatedAt = updatedAt,
        status = status,
        inboxDeadlineEpochDay = deadline?.toEpochDay(),
        needsManualClassification = needsManualClassification
    )

internal fun ProjectEntity.toDomain(): Project =
    Project(
        id = id,
        name = name,
        description = description,
        coverImageUri = coverImageUri,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
