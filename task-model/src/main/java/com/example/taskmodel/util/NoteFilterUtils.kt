package com.example.taskmodel.util

import com.example.taskmodel.model.Note

/**
 * Utility functions for filtering and displaying notes in the quote settings screen.
 */
object NoteFilterUtils {

    /**
     * Filter notes by a search query. Matches against body, behaviorSummary,
     * primaryCategory, and secondaryCategory (case-insensitive).
     * Empty or blank query returns the full list.
     */
    fun filterNotesByQuery(notes: List<Note>, query: String): List<Note> {
        val trimmed = query.trim()
        if (trimmed.isEmpty()) return notes
        val lowerQuery = trimmed.lowercase()
        return notes.filter { note ->
            note.body.lowercase().contains(lowerQuery) ||
                note.behaviorSummary.lowercase().contains(lowerQuery) ||
                note.primaryCategory.lowercase().contains(lowerQuery) ||
                note.secondaryCategory.lowercase().contains(lowerQuery)
        }
    }

    /**
     * Get a preview text for a note: first line of body, truncated to 60 chars.
     * Falls back to behaviorSummary if body is empty, then "…" if both are empty.
     */
    fun getNotePreviewText(note: Note, maxLen: Int = 60): String {
        val line = note.body.trim().lineSequence().firstOrNull()?.trim().orEmpty()
        if (line.isNotEmpty()) {
            return if (line.length > maxLen) line.take(maxLen) + "…" else line
        }
        val summary = note.behaviorSummary.trim()
        if (summary.isNotEmpty()) return summary
        return "…"
    }
}
