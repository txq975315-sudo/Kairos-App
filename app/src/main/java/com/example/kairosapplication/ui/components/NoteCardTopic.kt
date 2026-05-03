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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Note
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun NoteCardTopic(
    note: Note,
    onNoteClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    expandable: Boolean = false,
    expanded: Boolean = false,
    onToggleExpand: () -> Unit = {},
    publishedActions: PublishedNoteCardActions? = null
) {
    val zone = ZoneId.systemDefault()
    val timeStr = remember(note.createdAt) {
        val z = Instant.ofEpochMilli(note.createdAt).atZone(zone)
        DateTimeFormatter.ofPattern("HH:mm").format(z)
    }
    val topicLabelLine = remember(note.primaryCategory, note.secondaryCategory) {
        val primary = NoteCardConstants.primaryCategoryLabel(note.primaryCategory)
        val secondary = note.secondaryCategory.trim()
        if (secondary.isNotBlank()) "$primary · $secondary" else primary
    }

    val bodyMaxLines = if (expandable && !expanded) 2 else Int.MAX_VALUE
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .clickable {
                if (expandable) onToggleExpand() else onNoteClick(note.id)
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.CardHorizontal, AppSpacing.CardVertical)
        ) {
            Text(
                text = topicLabelLine,
                fontSize = 12.sp,
                color = AppColors.HintText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            if (note.behaviorSummary.isNotBlank()) {
                Spacer(Modifier.height(4.dp))
                Text(
                    text = note.behaviorSummary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.PrimaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.height(if (note.behaviorSummary.isNotBlank()) 8.dp else 6.dp))
            Text(
                text = note.body.ifBlank { " " },
                fontSize = 14.sp,
                color = AppColors.SecondaryText,
                maxLines = bodyMaxLines,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp
            )
            if (note.imageUris.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                NoteImageRow(imageUris = note.imageUris, maxImages = 4)
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (note.sceneTags.isNotEmpty()) {
                    LazyRow(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(note.sceneTags) { _, tag ->
                            Text(
                                text = tag,
                                fontSize = 12.sp,
                                color = AppColors.HintText,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(AppColors.BottomBarSelectedContainer)
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                    Spacer(Modifier.width(8.dp))
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
                Text(
                    text = timeStr,
                    fontSize = 12.sp,
                    color = AppColors.HintText
                )
            }
            if (expandable && expanded) {
                Spacer(Modifier.height(8.dp))
                if (publishedActions != null && note.status == NoteStatus.PUBLISHED) {
                    PublishedNoteActionsRow(
                        actions = publishedActions,
                        hasProjects = note.projectIds.isNotEmpty()
                    )
                } else {
                    TextButton(
                        onClick = { onNoteClick(note.id) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Edit", color = AppColors.PrimaryText)
                    }
                }
            }
        }
    }
}
