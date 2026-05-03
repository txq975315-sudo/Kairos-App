package com.example.kairosapplication.ui.view.today

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.components.NoteCardConstants
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Project
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun NoteCardView(
    note: Note,
    projects: List<Project>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFreestyle = note.primaryCategory == NotePrimaryCategory.FREESTYLE
    val timeText = remember(note.id, note.createdAt) {
        Instant.ofEpochMilli(note.createdAt)
            .atZone(ZoneId.systemDefault())
            .toLocalTime()
            .format(DateTimeFormatter.ofPattern("HH:mm"))
    }
    val projectName = note.projectIds.firstOrNull()?.let { id ->
        projects.find { it.id == id }?.name
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(color = Color(0xFFF5F5F5), bounded = true, radius = 4.dp),
                onClick = onClick,
            )
            .padding(vertical = 2.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (!isFreestyle) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(16.dp)
                        .background(
                            NoteCardConstants.categoryColor(note.primaryCategory),
                            RoundedCornerShape(2.dp),
                        ),
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = if (isFreestyle) {
                    NoteCardConstants.primaryCategoryLabel(NotePrimaryCategory.FREESTYLE)
                } else {
                    val sec = note.secondaryCategory.trim()
                    val label = NoteCardConstants.primaryCategoryLabel(note.primaryCategory)
                    if (sec.isNotEmpty()) "$label · $sec" else label
                },
                color = Color(0xFF9E9E9E),
                fontSize = 12.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (isFreestyle) note.body.take(30) else note.behaviorSummary,
            color = Color(0xFF1A1A1A),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (!projectName.isNullOrBlank()) {
                Text(
                    text = "📖 $projectName",
                    color = Color(0xFF9E9E9E),
                    fontSize = 11.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
            Text(
                text = timeText,
                color = Color(0xFF9E9E9E),
                fontSize = 11.sp,
            )
        }
    }
}
