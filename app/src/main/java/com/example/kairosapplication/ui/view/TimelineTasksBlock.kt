package com.example.kairosapplication.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.ui.view.today.UrgencyCircle
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task

@Composable
private fun timelineTimeBlockLabel(block: String): String {
    val ctx = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    return when (block.uppercase()) {
        TaskConstants.TIME_BLOCK_ANYTIME -> LocalizedStrings.stringFor(lang, "view_time_anytime", ctx)
        TaskConstants.TIME_BLOCK_MORNING -> LocalizedStrings.stringFor(lang, "view_time_morning", ctx)
        TaskConstants.TIME_BLOCK_AFTERNOON -> LocalizedStrings.stringFor(lang, "view_time_afternoon", ctx)
        TaskConstants.TIME_BLOCK_EVENING -> LocalizedStrings.stringFor(lang, "view_time_evening", ctx)
        else -> block.lowercase().replaceFirstChar(Char::titlecaseChar)
    }
}

@Composable
fun TimelineTaskCompactRow(
    task: Task,
    onToggleComplete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val done = task.isCompleted
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(22.dp)
            .viewClickable(onToggleComplete),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = task.title,
            color = if (done) AppColors.HintText else AppColors.PrimaryText,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textDecoration = if (done) TextDecoration.LineThrough else null,
            modifier = Modifier.weight(1f),
        )
        Spacer(modifier = Modifier.width(4.dp))
        UrgencyCircle(
            urgency = task.urgency,
            isCompleted = done,
        )
    }
}

/**
 * Same ordering as week view: time-block headers, compact title + urgency rows, [maxVisible] cap.
 */
@Composable
fun TimelineTasksByTimeBlocks(
    sortedTasks: List<Task>,
    maxVisible: Int,
    onToggleTaskComplete: (Task) -> Unit,
    modifier: Modifier = Modifier,
) {
    val ctx = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    var remaining = maxVisible
    Column(modifier = modifier) {
        TaskConstants.TIME_BLOCKS.forEach { block ->
            if (remaining <= 0) return@forEach
            val blockTasks = sortedTasks.filter { it.timeBlock == block }
            if (blockTasks.isEmpty()) return@forEach
            Text(
                text = timelineTimeBlockLabel(block),
                color = AppColors.HintText,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 1.dp, bottom = 1.dp),
            )
            val visible = blockTasks.take(remaining)
            Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                visible.forEach { task ->
                    key(task.id) {
                        TimelineTaskCompactRow(
                            task = task,
                            onToggleComplete = { onToggleTaskComplete(task) },
                        )
                    }
                }
            }
            remaining -= visible.size
            if (remaining > 0) {
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
        if (sortedTasks.size > maxVisible) {
            Text(
                text = LocalizedStrings.stringFor(
                    lang,
                    "view_week_more_tasks",
                    ctx,
                    sortedTasks.size - maxVisible,
                ),
                color = AppColors.HintText,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 2.dp),
            )
        }
    }
}
