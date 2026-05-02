package com.example.kairosapplication.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskmodel.model.Note
import java.time.LocalDate

enum class NoteCardVariant {
    TIMELINE,
    TOPIC,
    INBOX,
    PROJECT
}

sealed class NoteAction {
    data class Classify(val noteId: Long) : NoteAction()
    data class Tag(val noteId: Long) : NoteAction()
    data class LinkProject(val noteId: Long) : NoteAction()
}

@Composable
fun NoteCard(
    note: Note,
    variant: NoteCardVariant,
    onNoteClick: (Long) -> Unit,
    onQuickAction: (NoteAction) -> Unit = {},
    modifier: Modifier = Modifier,
    projectsById: Map<Long, String> = emptyMap(),
    today: LocalDate = LocalDate.now()
) {
    when (variant) {
        NoteCardVariant.TIMELINE -> NoteCardTimeline(
            note = note,
            onNoteClick = onNoteClick,
            modifier = modifier,
            projectsById = projectsById
        )
        NoteCardVariant.TOPIC -> NoteCardTopic(
            note = note,
            onNoteClick = onNoteClick,
            modifier = modifier
        )
        NoteCardVariant.INBOX -> NoteCardInbox(
            note = note,
            onNoteClick = onNoteClick,
            onQuickAction = onQuickAction,
            today = today,
            modifier = modifier
        )
        NoteCardVariant.PROJECT -> NoteCardProject(
            note = note,
            onNoteClick = onNoteClick,
            projectsById = projectsById,
            modifier = modifier
        )
    }
}
