package com.example.kairosapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.topic.rememberTopicPrimaryLabel
import com.example.kairosapplication.ui.topic.rememberTopicSecondaryLabel
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
    projectsById: Map<Long, String> = emptyMap(),
    expandable: Boolean = false,
    expanded: Boolean = false,
    onToggleExpand: () -> Unit = {},
    publishedActions: PublishedNoteCardActions? = null,
    /** When true and collapsed: one line each for topic (primary · secondary), summary, body; expand restores full layout. */
    timelineCompactThreeLines: Boolean = false,
    /** Optional custom card background color with opacity applied. */
    cardBackgroundOverride: Color? = null,
) {
    val zone = ZoneId.systemDefault()
    val timeStr = remember(note.createdAt) {
        val z = Instant.ofEpochMilli(note.createdAt).atZone(zone)
        DateTimeFormatter.ofPattern("HH:mm").format(z)
    }
    val categoryColor = remember(note.primaryCategory) {
        NoteCardConstants.categoryColor(note.primaryCategory)
    }
    val categoryLabel = rememberTopicPrimaryLabel(note.primaryCategory)
    val categoryEmoji = remember(note.primaryCategory) {
        NoteCardConstants.categoryEmoji(note.primaryCategory)
    }
    val secondaryLine = rememberTopicSecondaryLabel(note.primaryCategory, note.secondaryCategory)
    val tagsPreview = remember(note.sceneTags) {
        note.sceneTags.take(3).joinToString(" · ")
    }
    val projectFirstName = remember(note.projectIds, projectsById) {
        note.projectIds.firstOrNull()?.let { projectsById[it] }
    }
    val projectCount = note.projectIds.size

    val headlineTopic = remember(categoryLabel, secondaryLine) {
        if (secondaryLine.isNotBlank()) "$categoryLabel · $secondaryLine" else categoryLabel
    }

    val bodyMaxLines = when {
        timelineCompactThreeLines && !expanded -> 1
        expandable && !expanded -> 3
        else -> Int.MAX_VALUE
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (expandable) onToggleExpand() else onNoteClick(note.id)
            },
        verticalAlignment = Alignment.Top
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(40.dp)
                .fillMaxHeight()
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
                    .weight(1f)
                    .background(AppColors.TimelineLine)
            )
        }
        Spacer(Modifier.width(12.dp))
        Card(
            modifier = Modifier
                .weight(1f)
                .shadow(4.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = cardBackgroundOverride ?: AppColors.CardBackground),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val stripW = 4.dp.toPx()
                        drawRect(
                            color = categoryColor,
                            topLeft = Offset.Zero,
                            size = Size(stripW, size.height)
                        )
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 4.dp + AppSpacing.CardHorizontal,
                            end = AppSpacing.CardHorizontal,
                            top = AppSpacing.CardVertical,
                            bottom = AppSpacing.CardVertical
                        )
                ) {
                if (timelineCompactThreeLines && !expanded) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TimelineCardCategoryEmoji(emoji = categoryEmoji, accent = categoryColor)
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = headlineTopic,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = AppColors.PrimaryText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = note.behaviorSummary.ifBlank { "—" },
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = AppColors.PrimaryText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = note.body.ifBlank { " " },
                                fontSize = 13.sp,
                                color = AppColors.SecondaryText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                lineHeight = 15.sp
                            )
                        }
                    }
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TimelineCardCategoryEmoji(emoji = categoryEmoji, accent = categoryColor)
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
                    val (mainBody, parsedReview) = NoteCardConstants.splitReviewFromBody(note.body)
                    Text(
                        text = mainBody.ifBlank { " " },
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
                    if (parsedReview != null) {
                        Spacer(Modifier.height(8.dp))
                        NoteReviewSection(
                            review = parsedReview,
                            expanded = expanded || !expandable,
                            collapsedMaxLines = if (expandable && !expanded) 2 else Int.MAX_VALUE,
                            textColor = AppColors.HintText,
                            timestampColor = AppColors.HintText,
                        )
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
                if (expandable && expanded) {
                    Spacer(Modifier.height(10.dp))
                    if (publishedActions != null) {
                        PublishedNoteActionsRow(
                            actions = publishedActions,
                            hasProjects = note.projectIds.isNotEmpty()
                        )
                    } else {
                        TextButton(
                            onClick = { onNoteClick(note.id) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(LocalizedStrings.get("note_card_edit"), color = AppColors.PrimaryText)
                        }
                    }
                }
                }
            }
        }
    }
}

@Composable
private fun TimelineCardCategoryEmoji(
    emoji: String,
    accent: Color,
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(accent.copy(alpha = 0.14f)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = emoji, fontSize = 18.sp)
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
