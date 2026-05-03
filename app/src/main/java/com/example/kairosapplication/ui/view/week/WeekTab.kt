package com.example.kairosapplication.ui.view.week

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.components.NoteCard
import com.example.kairosapplication.ui.components.NoteCardVariant
import com.example.kairosapplication.ui.components.PublishedNoteCardActions
import com.example.kairosapplication.ui.view.ViewUiState
import com.example.kairosapplication.ui.view.today.TaskRow
import com.example.kairosapplication.ui.view.viewClickable
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Task
import java.time.DayOfWeek
import java.time.LocalDate

private val WeekMuted = Color(0xFF9E9E9E)
private val WeekEmpty = Color(0xFFD0D0D0)
private val WeekDivider = Color(0xFFE8E5E0)

@Composable
fun WeekTab(
    uiState: ViewUiState,
    onToggleTaskComplete: (Task) -> Unit,
    onAddTaskForDate: (LocalDate) -> Unit,
    expandedNoteId: Long?,
    onToggleExpand: (Long) -> Unit,
    onPublishedChangeTopic: (Long) -> Unit,
    onPublishedChangeProject: (Long) -> Unit,
    onPublishedContinueCreate: (Long) -> Unit,
    onPublishedDelete: (Long) -> Unit,
    projectsById: Map<Long, String>,
    modifier: Modifier = Modifier,
) {
    val weekDays = remember(uiState.currentWeekRange) {
        daysInClosedRange(uiState.currentWeekRange.first, uiState.currentWeekRange.second)
    }
    // TODO: optional thin scrollbar (Compose scroll styling is limited)
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            items = weekDays,
            key = { it.toEpochDay() },
        ) { date ->
            WeekDaySplitRow(
                date = date,
                tasks = uiState.weekTasks[date].orEmpty(),
                notes = uiState.weekNotes[date].orEmpty(),
                onToggleTaskComplete = onToggleTaskComplete,
                onAddTaskForDate = onAddTaskForDate,
                expandedNoteId = expandedNoteId,
                onToggleExpand = onToggleExpand,
                onPublishedChangeTopic = onPublishedChangeTopic,
                onPublishedChangeProject = onPublishedChangeProject,
                onPublishedContinueCreate = onPublishedContinueCreate,
                onPublishedDelete = onPublishedDelete,
                projectsById = projectsById,
            )
        }
    }
}

@Composable
private fun WeekDaySplitRow(
    date: LocalDate,
    tasks: List<Task>,
    notes: List<Note>,
    onToggleTaskComplete: (Task) -> Unit,
    onAddTaskForDate: (LocalDate) -> Unit,
    expandedNoteId: Long?,
    onToggleExpand: (Long) -> Unit,
    onPublishedChangeTopic: (Long) -> Unit,
    onPublishedChangeProject: (Long) -> Unit,
    onPublishedContinueCreate: (Long) -> Unit,
    onPublishedDelete: (Long) -> Unit,
    projectsById: Map<Long, String>,
) {
    val taskScroll = rememberScrollState()
    val noteScroll = rememberScrollState()
    val expandedInNotes = notes.any { it.id == expandedNoteId }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
        ) {
            Text(
                text = formatWeekDayLabel(date),
                color = WeekMuted,
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Column(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .verticalScroll(taskScroll),
            ) {
                if (tasks.isEmpty()) {
                    Text(
                        text = "（无任务）",
                        color = WeekEmpty,
                        fontSize = 13.sp,
                    )
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        tasks.forEach { task ->
                            key(task.id) {
                                TaskRow(
                                    task = task,
                                    onToggleComplete = { onToggleTaskComplete(task) },
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "+ 添加任务",
                color = WeekMuted,
                fontSize = 11.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .viewClickable { onAddTaskForDate(date) }
                    .padding(vertical = 2.dp),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(0.5.dp)
                .background(WeekDivider),
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
        ) {
            Text(
                text = formatWeekDayLabel(date),
                color = WeekMuted,
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (expandedInNotes) {
                            Modifier
                        } else {
                            Modifier
                                .height(120.dp)
                                .verticalScroll(noteScroll)
                        },
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                if (notes.isEmpty()) {
                    Text(
                        text = "（无笔记）",
                        color = WeekEmpty,
                        fontSize = 13.sp,
                    )
                } else {
                    notes.forEach { note ->
                        key(note.id) {
                            NoteCard(
                                note = note,
                                variant = NoteCardVariant.TOPIC,
                                onNoteClick = { },
                                modifier = Modifier.fillMaxWidth(),
                                projectsById = projectsById,
                                expandable = true,
                                expanded = note.id == expandedNoteId,
                                topicPeekWhenCollapsed = true,
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
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun formatWeekDayLabel(date: LocalDate): String {
    val w = when (date.dayOfWeek) {
        DayOfWeek.MONDAY -> "Mon"
        DayOfWeek.TUESDAY -> "Tue"
        DayOfWeek.WEDNESDAY -> "Wed"
        DayOfWeek.THURSDAY -> "Thu"
        DayOfWeek.FRIDAY -> "Fri"
        DayOfWeek.SATURDAY -> "Sat"
        DayOfWeek.SUNDAY -> "Sun"
    }
    return "$w ${date.monthValue}/${date.dayOfMonth}"
}

private fun daysInClosedRange(start: LocalDate, end: LocalDate): List<LocalDate> {
    val out = ArrayList<LocalDate>((end.toEpochDay() - start.toEpochDay()).toInt() + 1)
    var d = start
    while (!d.isAfter(end)) {
        out.add(d)
        d = d.plusDays(1)
    }
    return out
}
