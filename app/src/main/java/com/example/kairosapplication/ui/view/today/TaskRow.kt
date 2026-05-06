package com.example.kairosapplication.ui.view.today

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.view.viewClickable
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task
import com.example.taskmodel.util.TaskUtils

@Composable
private fun localizedTimeBlock(block: String): String {
    return when (block.uppercase()) {
        TaskConstants.TIME_BLOCK_ANYTIME -> LocalizedStrings.get("view_time_anytime")
        TaskConstants.TIME_BLOCK_MORNING -> LocalizedStrings.get("view_time_morning")
        TaskConstants.TIME_BLOCK_AFTERNOON -> LocalizedStrings.get("view_time_afternoon")
        TaskConstants.TIME_BLOCK_EVENING -> LocalizedStrings.get("view_time_evening")
        else -> block.lowercase().replaceFirstChar(Char::titlecaseChar)
    }
}

@Composable
fun TaskRow(
    task: Task,
    onToggleComplete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val done = task.isCompleted
    val titleColor = if (done) AppColors.HintText else AppColors.PrimaryText
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(36.dp)
            .viewClickable(onToggleComplete)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = localizedTimeBlock(task.timeBlock),
            color = AppColors.HintText,
            fontSize = 12.sp,
        )
        Text(
            text = " — ",
            color = AppColors.HintText,
            fontSize = 12.sp,
        )
        Text(
            text = task.title,
            color = titleColor,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textDecoration = if (done) TextDecoration.LineThrough else null,
            modifier = Modifier.weight(1f),
        )
        Spacer(modifier = Modifier.width(8.dp))
        UrgencyCircle(urgency = task.urgency, isCompleted = done)
    }
}
