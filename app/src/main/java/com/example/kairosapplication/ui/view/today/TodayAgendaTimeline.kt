package com.example.kairosapplication.ui.view.today

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.text.TaskUiLocalLabels
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.components.NoteCard
import com.example.kairosapplication.ui.components.NoteCardConstants
import com.example.kairosapplication.ui.components.NoteCardVariant
import com.example.kairosapplication.ui.components.PublishedNoteCardActions
import com.example.kairosapplication.ui.topic.TopicDisplayStrings
import com.example.kairosapplication.ui.view.viewClickable
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Task
import java.time.Instant
import java.time.ZoneId
import java.util.Locale

private val TimeColumnWidth = 72.dp
private val TimelineDotSize = 8.dp

@Composable
private fun twoEmGap(): androidx.compose.ui.unit.Dp =
    with(LocalDensity.current) { (12.sp * 2).toDp() }

@Composable
private fun timeBlockLabel(block: String): String {
    val ctx = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    return when (block.uppercase(Locale.US)) {
        TaskConstants.TIME_BLOCK_ANYTIME -> LocalizedStrings.stringFor(lang, "view_time_anytime", ctx)
        TaskConstants.TIME_BLOCK_MORNING -> LocalizedStrings.stringFor(lang, "view_time_morning", ctx)
        TaskConstants.TIME_BLOCK_AFTERNOON -> LocalizedStrings.stringFor(lang, "view_time_afternoon", ctx)
        TaskConstants.TIME_BLOCK_EVENING -> LocalizedStrings.stringFor(lang, "view_time_evening", ctx)
        else -> block.lowercase(Locale.getDefault()).replaceFirstChar { it.titlecase(Locale.getDefault()) }
    }
}

private fun inferNoteTimeBlock(note: Note): String {
    val hour = Instant.ofEpochMilli(note.createdAt)
        .atZone(ZoneId.systemDefault())
        .hour
    return when (hour) {
        in 5..11 -> TaskConstants.TIME_BLOCK_MORNING
        in 12..16 -> TaskConstants.TIME_BLOCK_AFTERNOON
        in 17..21 -> TaskConstants.TIME_BLOCK_EVENING
        else -> TaskConstants.TIME_BLOCK_ANYTIME
    }
}

@Composable
private fun urgencyDotColor(urgency: Int): Color = when (urgency.coerceIn(0, 3)) {
    0 -> AppColors.Urgent
    1 -> AppColors.High
    2 -> AppColors.Normal
    else -> AppColors.Low
}

/** Primary vertical section title (Todo / Notes) with trailing rule, agenda reference style. */
@Composable
fun TodayAgendaSectionHeader(title: String) {
    val lang = LocalCurrentLanguage.current.value
    val label = if (lang == LocalizationManager.Language.EN) title.uppercase(Locale.US) else title
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            color = AppColors.HintText,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.width(8.dp))
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 0.5.dp,
            color = AppColors.Divider,
        )
    }
}

@Composable
private fun AgendaTaskRow(
    task: Task,
    showTimeLabel: Boolean,
    timeLabel: String,
    onToggleComplete: () -> Unit,
) {
    val ctx = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val gap = twoEmGap()
    val subtitle = remember(task.label, lang, ctx) {
        val raw = task.label?.trim().orEmpty()
        if (raw.isEmpty() || raw == TaskConstants.LABEL_NONE) {
            null
        } else {
            TaskUiLocalLabels.labelDisplay(lang, ctx, raw, withHashPrefixForNonNone = false)
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .viewClickable(onToggleComplete),
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier.width(TimeColumnWidth),
            contentAlignment = Alignment.TopStart,
        ) {
            if (showTimeLabel) {
                Text(
                    text = timeLabel,
                    color = AppColors.SecondaryText,
                    fontSize = 11.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Spacer(modifier = Modifier.width(gap))
        Box(
            modifier = Modifier
                .width(TimelineDotSize)
                .padding(top = 5.dp),
            contentAlignment = Alignment.TopCenter,
        ) {
            Box(
                modifier = Modifier
                    .size(TimelineDotSize)
                    .background(urgencyDotColor(task.urgency), CircleShape),
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = task.title,
                color = if (task.isCompleted) AppColors.HintText else AppColors.PrimaryText,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            if (subtitle != null) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = if (lang == LocalizationManager.Language.EN) {
                        subtitle.uppercase(Locale.US)
                    } else {
                        subtitle
                    },
                    color = AppColors.HintText,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        UrgencyCircle(
            urgency = task.urgency,
            isCompleted = task.isCompleted,
            modifier = Modifier.padding(start = 4.dp, top = 2.dp),
        )
    }
}

@Composable
private fun AgendaNoteBlock(
    note: Note,
    showTimeLabel: Boolean,
    timeLabel: String,
    expanded: Boolean,
    onToggleExpand: () -> Unit,
    projectsById: Map<Long, String>,
    publishedActions: PublishedNoteCardActions?,
) {
    val ctx = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val gap = twoEmGap()
    val dotColor = remember(note.primaryCategory) { NoteCardConstants.categoryColor(note.primaryCategory) }
    val primaryTopic = remember(note.primaryCategory, lang, ctx) {
        TopicDisplayStrings.primaryLabel(note.primaryCategory, lang, ctx)
    }
    val headline = remember(note.behaviorSummary, note.body) {
        note.behaviorSummary.trim().ifBlank { note.body.trim() }.ifBlank { "—" }
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
                .viewClickable(onToggleExpand),
            verticalAlignment = Alignment.Top,
        ) {
            Box(
                modifier = Modifier.width(TimeColumnWidth),
                contentAlignment = Alignment.TopStart,
            ) {
                if (showTimeLabel) {
                    Text(
                        text = timeLabel,
                        color = AppColors.SecondaryText,
                        fontSize = 11.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Spacer(modifier = Modifier.width(gap))
            Box(
                modifier = Modifier
                    .width(TimelineDotSize)
                    .padding(top = 5.dp),
                contentAlignment = Alignment.TopCenter,
            ) {
                Box(
                    modifier = Modifier
                        .size(TimelineDotSize)
                        .background(dotColor, CircleShape),
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = headline,
                    color = AppColors.PrimaryText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = if (lang == LocalizationManager.Language.EN) {
                        primaryTopic.uppercase(Locale.US)
                    } else {
                        primaryTopic
                    },
                    color = AppColors.HintText,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        if (expanded) {
            NoteCard(
                note = note,
                variant = NoteCardVariant.TIMELINE,
                onNoteClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                projectsById = projectsById,
                expandable = true,
                expanded = true,
                onToggleExpand = onToggleExpand,
                publishedActions = publishedActions,
                timelineCompactThreeLines = true,
            )
        }
    }
}

@Composable
fun TodayTodoAgendaTimeline(
    sortedTasks: List<Task>,
    maxVisible: Int,
    onToggleTaskComplete: (Task) -> Unit,
    modifier: Modifier = Modifier,
) {
    var remaining = maxVisible
    Column(modifier = modifier) {
        TaskConstants.TIME_BLOCKS.forEach { block ->
            if (remaining <= 0) return@forEach
            val blockTasks = sortedTasks.filter { it.timeBlock == block }
            if (blockTasks.isEmpty()) return@forEach
            val label = timeBlockLabel(block)
            val visible = blockTasks.take(remaining)
            visible.forEachIndexed { index, task ->
                key(task.id) {
                    AgendaTaskRow(
                        task = task,
                        showTimeLabel = index == 0,
                        timeLabel = label,
                        onToggleComplete = { onToggleTaskComplete(task) },
                    )
                }
            }
            remaining -= visible.size
        }
        if (sortedTasks.size > maxVisible) {
            Text(
                text = LocalizedStrings.get(
                    "view_week_more_tasks",
                    sortedTasks.size - maxVisible,
                ),
                color = AppColors.HintText,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
    }
}

@Composable
fun TodayNotesAgendaTimeline(
    notes: List<Note>,
    maxVisible: Int,
    expandedNoteId: Long?,
    onToggleExpand: (Long) -> Unit,
    onPublishedChangeTopic: (Long) -> Unit,
    onPublishedChangeProject: (Long) -> Unit,
    onPublishedContinueCreate: (Long) -> Unit,
    onPublishedDelete: (Long) -> Unit,
    projectsById: Map<Long, String>,
    modifier: Modifier = Modifier,
) {
    val sorted = remember(notes) {
        notes.sortedByDescending { it.createdAt }
    }
    val byBlock = remember(sorted) {
        TaskConstants.TIME_BLOCKS.associateWith { block ->
            sorted.filter { inferNoteTimeBlock(it) == block }
        }
    }
    var remaining = maxVisible
    Column(modifier = modifier) {
        TaskConstants.TIME_BLOCKS.forEach { block ->
            if (remaining <= 0) return@forEach
            val blockNotes = byBlock[block].orEmpty()
            if (blockNotes.isEmpty()) return@forEach
            val label = timeBlockLabel(block)
            val visible = blockNotes.take(remaining)
            visible.forEachIndexed { index, note ->
                key(note.id) {
                    AgendaNoteBlock(
                        note = note,
                        showTimeLabel = index == 0,
                        timeLabel = label,
                        expanded = note.id == expandedNoteId,
                        onToggleExpand = { onToggleExpand(note.id) },
                        projectsById = projectsById,
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
            remaining -= visible.size
        }
    }
}
