package com.example.kairosapplication.ui.view.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.components.NoteCard
import com.example.kairosapplication.ui.components.NoteCardVariant
import com.example.kairosapplication.ui.components.PublishedNoteCardActions
import com.example.kairosapplication.ui.view.TimelineTasksByTimeBlocks
import com.example.kairosapplication.ui.view.ViewUiState
import com.example.kairosapplication.ui.view.viewClickable
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Task
import com.example.taskmodel.util.TaskUtils
import java.time.LocalDate

private const val TodayTabMaxTaskLines = 7
private const val TodayTabMaxNotePreview = 7

@Composable
fun TodayTab(
    focusedDate: LocalDate,
    uiState: ViewUiState,
    onPreviousDay: () -> Unit,
    onNextDay: () -> Unit,
    onToggleTaskComplete: (Task) -> Unit,
    onRequestOpenToday: () -> Unit,
    onRequestOpenEssay: () -> Unit,
    expandedNoteId: Long?,
    onToggleExpand: (Long) -> Unit,
    onPublishedChangeTopic: (Long) -> Unit,
    onPublishedChangeProject: (Long) -> Unit,
    onPublishedContinueCreate: (Long) -> Unit,
    onPublishedDelete: (Long) -> Unit,
    projectsById: Map<Long, String>,
    modifier: Modifier = Modifier,
) {
    val scroll = rememberScrollState()
    val tasks = uiState.todayTasks
    val notes = uiState.todayNotes
    val sortedTasks = remember(tasks) { TaskUtils.sortTasks(tasks) }
    val completedCount = tasks.count { it.isCompleted }
    val totalCount = tasks.size
    val isCalendarToday = focusedDate == LocalDate.now()
    val previewNotes = remember(notes) { notes.take(TodayTabMaxNotePreview) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scroll),
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            DateSelector(
                focusedDate = focusedDate,
                onPreviousDay = onPreviousDay,
                onNextDay = onNextDay,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = AppColors.CardBackground,
                shadowElevation = 2.dp,
            ) {
                Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp)) {
                    Text(
                        text = "${LocalizedStrings.get("view_todo_prefix")} ($completedCount/$totalCount)",
                        color = AppColors.HintText,
                        fontSize = 12.sp,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    if (tasks.isNotEmpty()) {
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 0.5.dp,
                            color = AppColors.Divider,
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                    if (tasks.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = if (isCalendarToday) {
                                    LocalizedStrings.get("view_empty_tasks_today")
                                } else {
                                    LocalizedStrings.get("view_empty_tasks_other")
                                },
                                color = AppColors.HintText,
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center,
                            )
                        }
                    } else {
                        TimelineTasksByTimeBlocks(
                            sortedTasks = sortedTasks,
                            maxVisible = TodayTabMaxTaskLines,
                            onToggleTaskComplete = onToggleTaskComplete,
                        )
                        if (tasks.size > TodayTabMaxTaskLines) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = LocalizedStrings.get("view_see_all_more")
                                    .replace("{n}", (tasks.size - TodayTabMaxTaskLines).toString()),
                                color = AppColors.HintText,
                                fontSize = 12.sp,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .viewClickable(onRequestOpenToday)
                                    .padding(vertical = 2.dp),
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = AppColors.CardBackground,
                shadowElevation = 2.dp,
            ) {
                Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp)) {
                    Text(
                        text = "${LocalizedStrings.get("view_notes_section")} (${notes.size})",
                        color = AppColors.HintText,
                        fontSize = 12.sp,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    if (notes.isNotEmpty()) {
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 0.5.dp,
                            color = AppColors.Divider,
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                    if (notes.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = LocalizedStrings.get("view_empty_notes"),
                                color = AppColors.HintText,
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center,
                            )
                        }
                    } else {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            previewNotes.forEach { note ->
                                key(note.id) {
                                    NoteCard(
                                        note = note,
                                        variant = NoteCardVariant.TIMELINE,
                                        onNoteClick = { },
                                        modifier = Modifier.fillMaxWidth(),
                                        projectsById = projectsById,
                                        expandable = true,
                                        expanded = note.id == expandedNoteId,
                                        onToggleExpand = { onToggleExpand(note.id) },
                                        publishedActions = if (note.status == NoteStatus.PUBLISHED) {
                                            PublishedNoteCardActions(
                                                onChangeTopic = { onPublishedChangeTopic(note.id) },
                                                onChangeProject = { onPublishedChangeProject(note.id) },
                                                onContinueCreate = { onPublishedContinueCreate(note.id) },
                                                onDelete = { onPublishedDelete(note.id) },
                                            )
                                        } else {
                                            null
                                        },
                                        timelineCompactThreeLines = true,
                                    )
                                }
                            }
                        }
                        if (notes.size > TodayTabMaxNotePreview) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = LocalizedStrings.get("view_see_all"),
                                color = AppColors.HintText,
                                fontSize = 12.sp,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .viewClickable(onRequestOpenEssay)
                                    .padding(vertical = 2.dp),
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}
