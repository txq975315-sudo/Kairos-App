package com.example.kairosapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import android.net.Uri
import android.widget.ImageView
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task
import com.example.taskmodel.util.TaskUtils

@Composable
fun TaskItemCard(
    task: Task,
    onToggleComplete: () -> Unit,
    onOpenDetail: () -> Unit
) {
    val urgencyColor = TaskUtils.getUrgencyColor(task.urgency)
    val hasDescription = task.description.isNotBlank()
    val hasImage = !task.emojiImage.isNullOrBlank() || !task.localImageUri.isNullOrBlank()
    val isRepeating = task.repeatRule != TaskConstants.REPEAT_RULE_NONE
    val baseCardHeight = 48.dp
    val maxCardHeight = if (hasImage && hasDescription) 96.dp else baseCardHeight
    val imageSize = if (hasImage) maxCardHeight else 0.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.Black.copy(alpha = 0.2f),
                spotColor = Color.Black.copy(alpha = 0.2f)
            )
            .clickable { onOpenDetail() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = baseCardHeight, max = maxCardHeight)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(if (task.isCompleted) Color(0xFFE0E0E0) else Color.Transparent)
                    .border(
                        width = 2.dp,
                        color = if (task.isCompleted) Color(0xFFE0E0E0) else urgencyColor,
                        shape = CircleShape
                    )
                    .clickable { onToggleComplete() },
                contentAlignment = Alignment.Center
            ) {
                if (task.isCompleted) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Completed",
                        tint = Color(0xFF9E9E9E),
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = task.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (task.isCompleted) Color(0xFF9E9E9E) else Color(0xFF333333),
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
                if (hasDescription) {
                    Text(
                        text = task.description,
                        fontSize = 13.sp,
                        color = Color(0xFF757575),
                        textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    )
                }
                if (isRepeating) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Autorenew,
                            contentDescription = "Repeat task",
                            tint = Color(0xFF5C6BC0),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = formatRepeatRuleTag(task.repeatRule),
                            color = Color(0xFF5C6BC0),
                            fontSize = 12.sp
                        )
                    }
                }
            }

            if (hasImage) {
                Spacer(Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(imageSize)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE0E0E0)),
                    contentAlignment = Alignment.Center
                ) {
                    // 通过局部不可变变量进行空安全处理，避免属性智能转换失败。
                    val emojiImage = task.emojiImage
                    val localImageUri = task.localImageUri
                    if (!emojiImage.isNullOrBlank()) {
                        Text(text = emojiImage, fontSize = 34.sp)
                    } else if (!localImageUri.isNullOrBlank()) {
                        AndroidView(
                            factory = { ctx ->
                                ImageView(ctx).apply { scaleType = ImageView.ScaleType.CENTER_CROP }
                            },
                            update = { view ->
                                view.setImageURI(Uri.parse(localImageUri))
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

private fun formatRepeatRuleTag(repeatRule: String): String {
    return when {
        repeatRule.startsWith("WEEKLY_") -> "每周${repeatRule.removePrefix("WEEKLY_")}"
        repeatRule == TaskConstants.REPEAT_RULE_NONE -> "不重复"
        else -> repeatRule
    }
}
