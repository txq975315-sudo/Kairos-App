package com.example.kairosapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.example.taskmodel.model.Note
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun NoteCardTimeline(
    note: Note,
    onNoteClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    projectsById: Map<Long, String> = emptyMap()
) {
    val zone = ZoneId.systemDefault()
    val timeStr = remember(note.createdAt) {
        val z = Instant.ofEpochMilli(note.createdAt).atZone(zone)
        DateTimeFormatter.ofPattern("HH:mm").format(z)
    }
    val categoryColor = remember(note.primaryCategory) {
        NoteCardConstants.categoryColor(note.primaryCategory)
    }
    val categoryLabel = remember(note.primaryCategory) {
        NoteCardConstants.primaryCategoryLabel(note.primaryCategory)
    }
    val secondaryLine = remember(note.secondaryCategory) {
        note.secondaryCategory.ifBlank { "" }
    }
    val tagsPreview = remember(note.sceneTags) {
        note.sceneTags.take(3).joinToString(" · ")
    }
    val projectFirstName = remember(note.projectIds, projectsById) {
        note.projectIds.firstOrNull()?.let { projectsById[it] }
    }
    val projectCount = note.projectIds.size

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onNoteClick(note.id) },
        verticalAlignment = Alignment.Top
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(40.dp)
        ) {
            Text(
                text = timeStr,
                fontSize = 12.sp,
                color = AppColors.HintText
            )
            Spacer(Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(100.dp)
                    .background(AppColors.TimelineLine)
            )
        }
        Spacer(Modifier.width(12.dp))
        Card(
            modifier = Modifier
                .weight(1f)
                .shadow(4.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = AppColors.CardBackground),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppSpacing.CardHorizontal, AppSpacing.CardVertical)
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(4.dp)
                            .height(44.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(categoryColor)
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = categoryLabel,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AppColors.PrimaryText,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (secondaryLine.isNotBlank()) {
                            Text(
                                text = secondaryLine,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = AppColors.SecondaryText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    text = note.behaviorSummary.ifBlank { "—" },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.PrimaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = note.body.ifBlank { " " },
                    fontSize = 14.sp,
                    color = AppColors.SecondaryText,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 20.sp
                )
                if (note.imageUris.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    NoteImageRow(imageUris = note.imageUris, maxImages = 4)
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        MoodOrEmoji(note.moodIcon, tint = AppColors.SecondaryText)
                        if (tagsPreview.isNotBlank()) {
                            Text(
                                text = tagsPreview,
                                fontSize = 12.sp,
                                color = AppColors.HintText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f, fill = false)
                            )
                        }
                    }
                }
                if (projectCount > 0) {
                    Spacer(Modifier.height(4.dp))
                    val assoc = buildString {
                        append(projectFirstName ?: "Project")
                        append(" · ")
                        append(projectCount)
                        append(" linked")
                    }
                    Text(
                        text = assoc,
                        fontSize = 12.sp,
                        color = AppColors.HintText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
internal fun MoodOrEmoji(
    moodIcon: String?,
    tint: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    if (moodIcon.isNullOrBlank()) return
    val key = moodIcon.lowercase(Locale.US)
    val icon = NoteCardConstants.MOOD_ICON_MAP[key]
    if (icon != null) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = modifier.size(18.dp)
        )
    } else {
        Text(
            text = moodIcon,
            fontSize = 16.sp,
            modifier = modifier
        )
    }
}
