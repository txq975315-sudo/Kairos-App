package com.example.kairosapplication.ui.view.week

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.components.NoteCardConstants
import com.example.kairosapplication.ui.topic.rememberTopicPrimaryLabel
import com.example.kairosapplication.ui.topic.rememberTopicSecondaryLabel
import com.example.kairosapplication.ui.view.TimelineTaskCompactRow
import com.example.kairosapplication.ui.view.ViewUiState
import com.example.kairosapplication.ui.view.viewClickable
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Task
import com.example.taskmodel.util.TaskUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

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
    @Suppress("UNUSED_PARAMETER") projectsById: Map<Long, String>,
    modifier: Modifier = Modifier,
) {
    val weekDays = remember(uiState.currentWeekRange) {
        daysInClosedRange(uiState.currentWeekRange.first, uiState.currentWeekRange.second)
    }
    val lang = LocalCurrentLanguage.current.value
    val locale = if (lang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.US
    val dayFmt = remember(lang) {
        DateTimeFormatter.ofPattern("EEE M/d", locale)
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(0.dp),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp),
    ) {
        itemsIndexed(
            items = weekDays,
            key = { _, d -> d.toEpochDay() },
        ) { index, date ->
            if (index > 0) {
                HorizontalDivider(
                    modifier = Modifier.padding(
                        top = if (index == 1) 4.dp else 2.dp,
                        bottom = 2.dp,
                    ),
                    thickness = 0.5.dp,
                    color = AppColors.Divider.copy(alpha = 0.55f),
                )
            }
            WeekDayTimelineRow(
                date = date,
                dayLabel = date.format(dayFmt),
                tasks = uiState.weekTasks[date].orEmpty(),
                notes = uiState.weekNotes[date].orEmpty(),
                onToggleTaskComplete = onToggleTaskComplete,
                onAddTaskForDate = onAddTaskForDate,
                expandedNoteId = expandedNoteId,
                onToggleExpand = onToggleExpand,
                onPublishedChangeTopic = onPublishedChangeTopic,
                onPublishedDelete = onPublishedDelete,
            )
        }
    }
}

@Composable
private fun WeekDayTimelineRow(
    date: LocalDate,
    dayLabel: String,
    tasks: List<Task>,
    notes: List<Note>,
    onToggleTaskComplete: (Task) -> Unit,
    onAddTaskForDate: (LocalDate) -> Unit,
    expandedNoteId: Long?,
    onToggleExpand: (Long) -> Unit,
    onPublishedChangeTopic: (Long) -> Unit,
    onPublishedDelete: (Long) -> Unit,
) {
    val sortedTasks = remember(tasks) { TaskUtils.sortTasks(tasks) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 4.dp, end = 8.dp, top = 1.dp, bottom = 1.dp),
        ) {
            Text(
                text = dayLabel,
                color = AppColors.PrimaryText,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (sortedTasks.isEmpty()) {
                Text(
                    text = LocalizedStrings.get("view_week_no_tasks"),
                    color = AppColors.HintText,
                    fontSize = 12.sp,
                )
            } else {
                WeekUrgentTasksBlock(
                    sortedTasks = sortedTasks,
                    onToggleTaskComplete = onToggleTaskComplete,
                )
            }
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = LocalizedStrings.get("view_week_add_task"),
                color = AppColors.HintText,
                fontSize = 11.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .viewClickable { onAddTaskForDate(date) }
                    .padding(top = 1.dp, bottom = 1.dp),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(0.5.dp)
                .background(AppColors.Divider.copy(alpha = 0.45f)),
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 8.dp, end = 4.dp, top = 1.dp, bottom = 1.dp),
        ) {
            Text(
                text = dayLabel,
                color = AppColors.PrimaryText,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (notes.isEmpty()) {
                Text(
                    text = LocalizedStrings.get("view_week_no_notes"),
                    color = AppColors.HintText.copy(alpha = 0.85f),
                    fontSize = 12.sp,
                )
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    notes.forEach { note ->
                        key(note.id) {
                            WeekTimelineNoteBlock(
                                note = note,
                                expanded = note.id == expandedNoteId,
                                onToggleExpand = { onToggleExpand(note.id) },
                                onPublishedChangeTopic = { onPublishedChangeTopic(note.id) },
                                onPublishedDelete = { onPublishedDelete(note.id) },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WeekUrgentTasksBlock(
    sortedTasks: List<Task>,
    onToggleTaskComplete: (Task) -> Unit,
) {
    val overflowScroll = rememberScrollState()
    Column {
        sortedTasks.take(4).forEach { task ->
            key(task.id) {
                TimelineTaskCompactRow(
                    task = task,
                    onToggleComplete = { onToggleTaskComplete(task) },
                )
            }
        }
        if (sortedTasks.size > 4) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 132.dp)
                    .verticalScroll(overflowScroll),
            ) {
                sortedTasks.drop(4).forEach { task ->
                    key(task.id) {
                        TimelineTaskCompactRow(
                            task = task,
                            onToggleComplete = { onToggleTaskComplete(task) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WeekTimelineNoteBlock(
    note: Note,
    expanded: Boolean,
    onToggleExpand: () -> Unit,
    onPublishedChangeTopic: () -> Unit,
    onPublishedDelete: () -> Unit,
) {
    val barColor = NoteCardConstants.categoryColor(note.primaryCategory)
    val freestyleLoc = rememberTopicPrimaryLabel(NotePrimaryCategory.FREESTYLE)
    val primaryLoc = rememberTopicPrimaryLabel(note.primaryCategory)
    val secondaryLoc = rememberTopicSecondaryLabel(note.primaryCategory, note.secondaryCategory)
    val topicLine = when {
        note.primaryCategory == NotePrimaryCategory.FREESTYLE -> freestyleLoc
        secondaryLoc.isNotBlank() -> "$primaryLoc · $secondaryLoc"
        else -> primaryLoc
    }
    val summaryLine = note.behaviorSummary.trim().ifBlank { "—" }
    val bodyPreview = remember(note.body) {
        note.body.trim().lineSequence().firstOrNull()?.take(72)?.trim().orEmpty().ifBlank { "—" }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .viewClickable { onToggleExpand() },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
        ) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(40.dp)
                    .background(barColor, RoundedCornerShape(AppShapes.MicroRadius)),
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = topicLine,
                    style = TextStyle(
                        color = AppColors.PrimaryText,
                        fontSize = 12.sp,
                        lineHeight = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = summaryLine,
                    style = TextStyle(
                        color = AppColors.HintText,
                        fontSize = 11.sp,
                        lineHeight = 12.sp,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = bodyPreview,
                    style = TextStyle(
                        color = AppColors.HintText.copy(alpha = 0.9f),
                        fontSize = 11.sp,
                        lineHeight = 12.sp,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        if (expanded && note.status == NoteStatus.PUBLISHED) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 11.dp, top = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                TextButton(
                    onClick = onPublishedChangeTopic,
                    modifier = Modifier.padding(0.dp),
                ) {
                    Text(LocalizedStrings.get("topic_manage"), fontSize = 11.sp)
                }
                TextButton(onClick = onPublishedDelete, modifier = Modifier.padding(0.dp)) {
                    Text(LocalizedStrings.get("delete_irreversible_short"), fontSize = 11.sp)
                }
            }
        }
    }
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
