package com.example.kairosapplication.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskmodel.model.Note
import java.time.LocalDate

/** Actions shown when a published note card is expanded (topic / project or continue / delete). */
data class PublishedNoteCardActions(
    val onChangeTopic: () -> Unit,
    val onChangeProject: () -> Unit,
    val onContinueCreate: () -> Unit,
    val onDelete: () -> Unit
)

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
    today: LocalDate = LocalDate.now(),
    expandable: Boolean = false,
    expanded: Boolean = false,
    onToggleExpand: () -> Unit = {},
    /** When non-null for a published note, expanded area shows topic/project/delete instead of Edit. */
    publishedActions: PublishedNoteCardActions? = null
) {
    when (variant) {
        NoteCardVariant.TIMELINE -> NoteCardTimeline(
            note = note,
            onNoteClick = onNoteClick,
            modifier = modifier,
            projectsById = projectsById,
            expandable = expandable,
            expanded = expanded,
            onToggleExpand = onToggleExpand,
            publishedActions = publishedActions
        )
        NoteCardVariant.TOPIC -> NoteCardTopic(
            note = note,
            onNoteClick = onNoteClick,
            modifier = modifier,
            expandable = expandable,
            expanded = expanded,
            onToggleExpand = onToggleExpand,
            publishedActions = publishedActions
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
            modifier = modifier,
            expandable = expandable,
            expanded = expanded,
            onToggleExpand = onToggleExpand,
            publishedActions = publishedActions
        )
    }
}
