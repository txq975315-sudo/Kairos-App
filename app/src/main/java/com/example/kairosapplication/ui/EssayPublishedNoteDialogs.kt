package com.example.kairosapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.topic.EssayCategoryUi
import com.example.kairosapplication.ui.topic.rememberTopicPrimaryLabelWithConfig
import com.example.kairosapplication.ui.topic.rememberTopicSecondaryLabelWithConfig
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.model.EssayCategoryConfig
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Project
import com.example.taskmodel.viewmodel.TaskViewModel

@Composable
internal fun EssayChangeTopicDialog(
    note: Note,
    essayCategoryConfig: EssayCategoryConfig,
    onDismiss: () -> Unit,
    onConfirm: (primary: String, secondary: String) -> Unit
) {
    val primaryKeys = listOf(
        NotePrimaryCategory.FREESTYLE,
        NotePrimaryCategory.SELF_AWARENESS,
        NotePrimaryCategory.INTERPERSONAL,
        NotePrimaryCategory.INTIMACY_FAMILY,
        NotePrimaryCategory.SOMATIC_ENERGY,
        NotePrimaryCategory.MEANING
    )
    var selPrimary by remember(note.id) { mutableStateOf(note.primaryCategory) }
    var selSecondary by remember(note.id) { mutableStateOf(note.secondaryCategory) }
    val secondaries = remember(selPrimary, essayCategoryConfig) {
        EssayCategoryUi.mergedSecondaryIds(selPrimary, essayCategoryConfig)
    }
    LaunchedEffect(selPrimary) {
        if (NotePrimaryCategory.isTopic(selPrimary)) {
            if (secondaries.isNotEmpty() &&
                (selSecondary.isBlank() || selSecondary !in secondaries)
            ) {
                selSecondary = secondaries.first()
            }
        } else {
            selSecondary = ""
        }
    }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(LocalizedStrings.get("essay_dialog_change_topic_title"), color = PrimaryTextColor) },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 420.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(LocalizedStrings.get("essay_dialog_primary_topic_label"), fontSize = 12.sp, color = SecondaryTextColor)
                primaryKeys.forEach { key ->
                    val label = rememberTopicPrimaryLabelWithConfig(key, essayCategoryConfig)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = selPrimary == key,
                                onClick = { selPrimary = key },
                                role = Role.RadioButton
                            )
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selPrimary == key,
                            onClick = { selPrimary = key }
                        )
                        Text(label, fontSize = 14.sp, color = PrimaryTextColor)
                    }
                }
                if (NotePrimaryCategory.isTopic(selPrimary) && secondaries.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    Text(LocalizedStrings.get("essay_dialog_secondary_topic_label"), fontSize = 12.sp, color = SecondaryTextColor)
                    secondaries.forEach { sec ->
                        val secLabel = rememberTopicSecondaryLabelWithConfig(selPrimary, sec, essayCategoryConfig)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = selSecondary == sec,
                                    onClick = { selSecondary = sec },
                                    role = Role.RadioButton
                                )
                                .padding(vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selSecondary == sec,
                                onClick = { selSecondary = sec }
                            )
                            Text(secLabel, fontSize = 13.sp, color = PrimaryTextColor, maxLines = 2)
                        }
                    }
                }
            }
        },
        confirmButton = {
            val canConfirm =
                !NotePrimaryCategory.isTopic(selPrimary) || selSecondary.isNotBlank()
            TextButton(
                onClick = { onConfirm(selPrimary, selSecondary) },
                enabled = canConfirm
            ) {
                Text(LocalizedStrings.get("confirm"), color = PrimaryTextColor)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(LocalizedStrings.get("cancel"), color = SecondaryTextColor)
            }
        }
    )
}

@Composable
internal fun EssayChangeProjectDialog(
    note: Note,
    allProjects: List<Project>,
    onDismiss: () -> Unit,
    onConfirm: (List<Long>) -> Unit
) {
    var selected by remember(note.id) { mutableStateOf(note.projectIds.toSet()) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(LocalizedStrings.get("essay_dialog_change_project_title"), color = PrimaryTextColor) },
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 320.dp)
            ) {
                items(allProjects, key = { it.id }) { p ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = p.id in selected,
                            onCheckedChange = { checked ->
                                selected = if (checked) selected + p.id else selected - p.id
                            }
                        )
                        Text(
                            p.name,
                            fontSize = 14.sp,
                            color = PrimaryTextColor,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(selected.toList()) }) {
                Text(LocalizedStrings.get("essay_dialog_project_done"), color = PrimaryTextColor)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(LocalizedStrings.get("cancel"), color = SecondaryTextColor)
            }
        }
    )
}

@Composable
internal fun EssayContinueCreateProjectDialog(
    onDismiss: () -> Unit,
    onConfirmName: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(LocalizedStrings.get("essay_dialog_continue_title"), color = PrimaryTextColor) },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(LocalizedStrings.get("essay_dialog_new_project_name")) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val t = name.trim()
                    if (t.isNotEmpty()) onConfirmName(t)
                },
                enabled = name.trim().isNotEmpty()
            ) {
                Text(LocalizedStrings.get("essay_dialog_next"), color = PrimaryTextColor)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(LocalizedStrings.get("cancel"), color = SecondaryTextColor)
            }
        }
    )
}

@Composable
internal fun EssayDeletePublishedNoteDialog(
    onDismiss: () -> Unit,
    onConfirmDelete: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(LocalizedStrings.get("essay_note_delete_dialog_title"), color = PrimaryTextColor) },
        text = {
            Text(
                LocalizedStrings.get("essay_note_delete_body"),
                fontSize = 14.sp,
                color = SecondaryTextColor
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirmDelete) {
                Text(LocalizedStrings.get("essay_note_delete_confirm"), color = PrimaryTextColor)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(LocalizedStrings.get("cancel"), color = SecondaryTextColor)
            }
        }
    )
}

/**
 * Host for published-note dialogs: change topic, change project, continue, delete.
 * Pass [resolveNote] to look up the note from the current list or full published set, plus state setters.
 */
@Composable
fun PublishedNoteActionDialogsHost(
    resolveNote: (Long) -> Note?,
    noteProjects: List<Project>,
    essayCategoryConfig: EssayCategoryConfig,
    taskViewModel: TaskViewModel,
    onNavigateToNewNote: () -> Unit,
    changeTopicNoteId: Long?,
    onChangeTopicNoteId: (Long?) -> Unit,
    changeProjectNoteId: Long?,
    onChangeProjectNoteId: (Long?) -> Unit,
    continueCreateNoteId: Long?,
    onContinueCreateNoteId: (Long?) -> Unit,
    deleteConfirmNoteId: Long?,
    onDeleteConfirmNoteId: (Long?) -> Unit,
    onNoteDeleted: (Long) -> Unit = {}
) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val defaultBehaviorSummary = remember(lang, context) {
        LocalizedStrings.stringFor(lang, "note_default_behavior_summary", context)
    }
    changeTopicNoteId?.let { nid ->
        resolveNote(nid)?.let { n ->
            EssayChangeTopicDialog(
                note = n,
                essayCategoryConfig = essayCategoryConfig,
                onDismiss = { onChangeTopicNoteId(null) },
                onConfirm = { pri, sec ->
                    val topic = NotePrimaryCategory.isTopic(pri)
                    val newSummary = if (topic && n.behaviorSummary.isBlank()) {
                        defaultBehaviorSummary
                    } else {
                        n.behaviorSummary
                    }
                    val updated = n.copy(
                        primaryCategory = pri,
                        secondaryCategory = if (topic) sec else "",
                        behaviorSummary = newSummary,
                        linkedCategories = if (topic) {
                            n.linkedCategories.filter {
                                it != pri && it != NotePrimaryCategory.FREESTYLE
                            }
                        } else {
                            emptyList()
                        },
                        updatedAt = System.currentTimeMillis()
                    )
                    taskViewModel.updateRoomNote(updated)
                    onChangeTopicNoteId(null)
                }
            )
        }
    }
    changeProjectNoteId?.let { nid ->
        resolveNote(nid)?.let { n ->
            EssayChangeProjectDialog(
                note = n,
                allProjects = noteProjects,
                onDismiss = { onChangeProjectNoteId(null) },
                onConfirm = { ids ->
                    taskViewModel.updateRoomNote(
                        n.copy(projectIds = ids, updatedAt = System.currentTimeMillis())
                    )
                    onChangeProjectNoteId(null)
                }
            )
        }
    }
    continueCreateNoteId?.let { nid ->
        EssayContinueCreateProjectDialog(
            onDismiss = { onContinueCreateNoteId(null) },
            onConfirmName = { name ->
                taskViewModel.attachNewProjectToNoteAndPrepareEditor(nid, name) { pids ->
                    taskViewModel.setPendingNewNoteProjectIds(pids)
                    onNavigateToNewNote()
                }
                onContinueCreateNoteId(null)
            }
        )
    }
    deleteConfirmNoteId?.let { nid ->
        EssayDeletePublishedNoteDialog(
            onDismiss = { onDeleteConfirmNoteId(null) },
            onConfirmDelete = {
                taskViewModel.softDeleteRoomNote(nid)
                onNoteDeleted(nid)
                onDeleteConfirmNoteId(null)
            }
        )
    }
}
