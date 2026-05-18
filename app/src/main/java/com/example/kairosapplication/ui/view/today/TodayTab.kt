package com.example.kairosapplication.ui.view.today

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.view.LocalViewChrome
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.view.ViewUiState
import com.example.kairosapplication.ui.view.viewClickable
import com.example.taskmodel.model.Task
import com.example.taskmodel.util.TaskUtils
import java.time.LocalDate

private const val TodayTabMaxTaskLines = 7
private const val TodayTabMaxNotePreview = 7

@Composable
fun TodayTab(
    focusedDate: LocalDate,
    uiState: ViewUiState,
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
    val chrome = LocalViewChrome.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scroll),
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            TodayAgendaSectionHeader(
                title = "${LocalizedStrings.get("view_todo_prefix")} ($completedCount/$totalCount)",
            )
            if (tasks.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = if (isCalendarToday) {
                            LocalizedStrings.get("view_empty_tasks_today")
                        } else {
                            LocalizedStrings.get("view_empty_tasks_other")
                        },
                        color = chrome.muted,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            } else {
                TodayTodoAgendaTimeline(
                    sortedTasks = sortedTasks,
                    maxVisible = TodayTabMaxTaskLines,
                    onToggleTaskComplete = onToggleTaskComplete,
                )
                if (tasks.size > TodayTabMaxTaskLines) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = LocalizedStrings.get(
                            "view_see_all_more",
                            tasks.size - TodayTabMaxTaskLines,
                        ),
                        color = chrome.muted,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .viewClickable(onRequestOpenToday)
                            .padding(vertical = 2.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            TodayAgendaSectionHeader(
                title = "${LocalizedStrings.get("view_notes_section")} (${notes.size})",
            )
            if (notes.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = LocalizedStrings.get("view_empty_notes"),
                        color = chrome.muted,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            } else {
                TodayNotesAgendaTimeline(
                    notes = notes,
                    maxVisible = TodayTabMaxNotePreview,
                    expandedNoteId = expandedNoteId,
                    onToggleExpand = onToggleExpand,
                    onPublishedChangeTopic = onPublishedChangeTopic,
                    onPublishedChangeProject = onPublishedChangeProject,
                    onPublishedContinueCreate = onPublishedContinueCreate,
                    onPublishedDelete = onPublishedDelete,
                    projectsById = projectsById,
                )
                if (notes.size > TodayTabMaxNotePreview) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = LocalizedStrings.get("view_see_all"),
                        color = chrome.muted,
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
        Spacer(modifier = Modifier.height(20.dp))
    }
}
