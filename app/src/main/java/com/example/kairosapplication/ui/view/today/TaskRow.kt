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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.view.viewClickable
import com.example.taskmodel.model.Task
import java.util.Locale

@Composable
fun TaskRow(
    task: Task,
    onToggleComplete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val done = task.isCompleted
    val titleColor = if (done) Color(0xFF9E9E9E) else Color(0xFF1A1A1A)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
            .viewClickable(onToggleComplete)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = task.timeBlock.lowercase(Locale.US),
            color = Color(0xFF9E9E9E),
            fontSize = 12.sp,
        )
        Text(
            text = " — ",
            color = Color(0xFF9E9E9E),
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
