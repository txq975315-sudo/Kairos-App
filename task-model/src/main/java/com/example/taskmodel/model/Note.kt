package com.example.taskmodel.model

import java.time.LocalDate

/**
 * Domain model for Essay Room notes (design doc §3).
 */
data class Note(
    val id: Long = 0,
    val primaryCategory: String,
    val secondaryCategory: String,
    val behaviorSummary: String,
    val body: String,
    val sceneTags: List<String>,
    val moodIcon: String?,
    val linkedCategories: List<String>,
    val projectIds: List<Long>,
    /** Attachment image URIs as stored strings (content://, file://, …), max 4 in UI. */
    val imageUris: List<String> = emptyList(),
    val recordedDate: LocalDate,
    val createdAt: Long,
    val updatedAt: Long,
    val status: String,
    val deadline: LocalDate?,
    val needsManualClassification: Boolean
)
