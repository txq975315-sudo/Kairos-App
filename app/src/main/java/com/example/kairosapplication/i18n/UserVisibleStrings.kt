package com.example.kairosapplication.i18n

import android.content.Context

/** Maps ViewModel/repository snackbar payloads to localized text. */
object UserVisibleStrings {

    fun noteValidationSnackbar(
        message: String,
        language: LocalizationManager.Language,
        context: Context,
    ): String {
        if (message.startsWith("i18n|")) {
            val key = message.removePrefix("i18n|")
            return LocalizedStrings.stringFor(language, key, context)
        }
        return when (message) {
            "linked_categories cannot contain freestyle" ->
                LocalizedStrings.stringFor(language, "note_error_linked_freestyle", context)
            "linked_categories cannot duplicate primary_category" ->
                LocalizedStrings.stringFor(language, "note_error_linked_duplicate_primary", context)
            "Topic notes require behavior summary and secondary category" ->
                LocalizedStrings.stringFor(language, "note_error_topic_requires_fields", context)
            "Project name cannot be empty" ->
                LocalizedStrings.stringFor(language, "note_error_project_name_empty", context)
            else -> when {
                message.startsWith("Invalid primaryCategory:") ->
                    LocalizedStrings.stringFor(language, "note_error_invalid_primary", context)
                message.startsWith("Note not found:") ->
                    LocalizedStrings.stringFor(language, "note_error_note_not_found", context)
                else -> message
            }
        }
    }

    /** Relative “time ago” for timestamps (UI only). */
    fun relativeTimeAgo(
        updatedAt: Long,
        language: LocalizationManager.Language,
        context: Context,
    ): String {
        val now = System.currentTimeMillis()
        val diffMs = (now - updatedAt).coerceAtLeast(0L)
        val minutes = (diffMs / (60 * 1000)).toInt()
        val hours = (diffMs / (60 * 60 * 1000)).toInt()
        val days = (diffMs / (24 * 60 * 60 * 1000)).toInt()
        return when {
            days >= 1 -> LocalizedStrings.stringFor(language, "time_relative_days_ago", context, days)
            hours >= 1 -> LocalizedStrings.stringFor(language, "time_relative_hours_ago", context, hours)
            minutes >= 1 -> LocalizedStrings.stringFor(language, "time_relative_minutes_ago", context, minutes)
            else -> LocalizedStrings.stringFor(language, "time_relative_just_now", context)
        }
    }
}
