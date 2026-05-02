package com.example.kairosapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.taskmodel.model.Note
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun NoteCardInbox(
    note: Note,
    onNoteClick: (Long) -> Unit,
    onQuickAction: (NoteAction) -> Unit,
    today: LocalDate,
    modifier: Modifier = Modifier
) {
    val statusText = remember(note.needsManualClassification) {
        if (note.needsManualClassification) "需要分类" else ""
    }
    val deadlineText = remember(note.deadline, today, note.needsManualClassification) {
        inboxDeadlineLabel(note.deadline, today, note.needsManualClassification)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .clickable { onNoteClick(note.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.CardHorizontal, AppSpacing.CardVertical)
        ) {
            if (statusText.isNotBlank() || deadlineText.isNotBlank()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            AppColors.MorningBackground.copy(alpha = 0.5f),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (statusText.isNotBlank()) {
                            Icon(
                                imageVector = Icons.Default.WarningAmber,
                                contentDescription = null,
                                tint = AppColors.High,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(
                                text = statusText,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = AppColors.High
                            )
                        }
                    }
                    if (deadlineText.isNotBlank()) {
                        Text(
                            text = deadlineText,
                            fontSize = 12.sp,
                            color = AppColors.SecondaryText
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))
            }
            Text(
                text = note.behaviorSummary.ifBlank { "（无摘要）" },
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.PrimaryText,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = note.body.ifBlank { " " },
                fontSize = 14.sp,
                color = AppColors.SecondaryText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp
            )
            if (note.imageUris.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                NoteImageRow(imageUris = note.imageUris)
            }
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(onClick = {
                    onQuickAction(NoteAction.Classify(note.id))
                }) {
                    Text("分类", fontSize = 13.sp, color = AppColors.PrimaryText)
                }
                TextButton(onClick = {
                    onQuickAction(NoteAction.Tag(note.id))
                }) {
                    Text("标签", fontSize = 13.sp, color = AppColors.PrimaryText)
                }
                TextButton(onClick = {
                    onQuickAction(NoteAction.LinkProject(note.id))
                }) {
                    Text("项目", fontSize = 13.sp, color = AppColors.PrimaryText)
                }
            }
        }
    }
}

private fun inboxDeadlineLabel(
    deadline: LocalDate?,
    today: LocalDate,
    needsManual: Boolean
): String {
    if (deadline == null) {
        return if (needsManual) "待分类" else ""
    }
    val days = ChronoUnit.DAYS.between(today, deadline)
    return when {
        days > 0 -> "${days}天后"
        days == 0L -> "今天到期"
        else -> "已逾期 ${-days} 天"
    }
}
