package com.example.taskmodel.repository

import com.example.taskmodel.constants.NoteSecondaryCategories
import com.example.taskmodel.database.NoteEntity
import com.example.taskmodel.database.ProjectEntity
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Project
import java.time.LocalDate

/** Avoids DateTimeException / crash when Room rows carry corrupt epoch-day values (e.g. after bad imports). */
private fun safeDateFromEpochDay(epochDay: Long): LocalDate {
    val minD = LocalDate.MIN.toEpochDay()
    val maxD = LocalDate.MAX.toEpochDay()
    val clamped = epochDay.coerceIn(minD, maxD)
    return runCatching { LocalDate.ofEpochDay(clamped) }.getOrElse { LocalDate.now() }
}

fun NoteEntity.toDomain(): Note =
    Note(
        id = id,
        primaryCategory = primaryCategory,
        secondaryCategory = NoteSecondaryCategories.canonicalSecondary(primaryCategory, secondaryCategory),
        behaviorSummary = behaviorSummary.orEmpty(),
        body = body,
        sceneTags = sceneTags,
        moodIcon = moodIcon,
        linkedCategories = linkedCategories,
        projectIds = projectIds,
        imageUris = imageUris,
        recordedDate = safeDateFromEpochDay(recordedDateEpochDay),
        createdAt = createdAt,
        updatedAt = updatedAt,
        status = status,
        deadline = inboxDeadlineEpochDay?.let(::safeDateFromEpochDay),
        needsManualClassification = needsManualClassification
    )

fun Note.toEntity(): NoteEntity =
    NoteEntity(
        id = id,
        primaryCategory = primaryCategory,
        secondaryCategory = NoteSecondaryCategories.canonicalSecondary(primaryCategory, secondaryCategory)
            .ifBlank { null },
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
