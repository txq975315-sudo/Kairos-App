package com.example.taskmodel.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "primary_category") val primaryCategory: String,
    @ColumnInfo(name = "secondary_category") val secondaryCategory: String?,
    @ColumnInfo(name = "behavior_summary") val behaviorSummary: String?,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "scene_tags") val sceneTags: List<String>,
    @ColumnInfo(name = "mood_icon") val moodIcon: String?,
    @ColumnInfo(name = "linked_categories") val linkedCategories: List<String>,
    @ColumnInfo(name = "project_ids") val projectIds: List<Long>,
    @ColumnInfo(name = "image_uris") val imageUris: List<String> = emptyList(),
    @ColumnInfo(name = "recorded_date_epoch_day") val recordedDateEpochDay: Long,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "updated_at") val updatedAt: Long,
    val status: String,
    @ColumnInfo(name = "inbox_deadline_epoch_day") val inboxDeadlineEpochDay: Long?,
    @ColumnInfo(name = "needs_manual_classification") val needsManualClassification: Boolean
)
