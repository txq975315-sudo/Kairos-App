package com.example.kairosapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import com.example.kairosapplication.ui.glass.GlassNoteCardShell
import com.example.kairosapplication.ui.glass.GlassTextColors
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
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
    showTimeConnectorAbove: Boolean = false,
    showTimeConnectorBelow: Boolean = false,
    externalTimeRail: Boolean = false,
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
    val cardText = LocalGlassTextColors.current

    if (externalTimeRail) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    if (expandable) onToggleExpand() else onNoteClick(note.id)
                },
        ) {
            NoteCardTimelineShell(
                shellModifier = Modifier.fillMaxWidth(),
                note = note,
                categoryEmoji = categoryEmoji,
                categoryLabel = categoryLabel,
                secondaryLine = secondaryLine,
                headlineTopic = headlineTopic,
                tagsPreview = tagsPreview,
                projectFirstName = projectFirstName,
                projectCount = projectCount,
                bodyMaxLines = bodyMaxLines,
                timelineCompactThreeLines = timelineCompactThreeLines,
                expanded = expanded,
                expandable = expandable,
                cardText = cardText,
                onNoteClick = onNoteClick,
                publishedActions = publishedActions,
            )
        }
    } else {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clickable {
                    if (expandable) onToggleExpand() else onNoteClick(note.id)
                },
            verticalAlignment = Alignment.Top,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(40.dp)
                    .fillMaxHeight(),
            ) {
                if (showTimeConnectorAbove) {
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .weight(1f)
                            .background(NoteCardConstants.TimelineConnectorColor),
                    )
                    Spacer(Modifier.height(12.dp))
                }
                Text(
                    text = timeStr,
                    fontSize = 12.sp,
                    color = cardText.muted,
                )
                if (showTimeConnectorBelow) {
                    Spacer(Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .weight(1f)
                            .background(NoteCardConstants.TimelineConnectorColor),
                    )
                }
            }
            Spacer(Modifier.width(12.dp))
            NoteCardTimelineShell(
                shellModifier = Modifier.weight(1f),
                note = note,
                categoryEmoji = categoryEmoji,
                categoryLabel = categoryLabel,
                secondaryLine = secondaryLine,
                headlineTopic = headlineTopic,
                tagsPreview = tagsPreview,
                projectFirstName = projectFirstName,
                projectCount = projectCount,
                bodyMaxLines = bodyMaxLines,
                timelineCompactThreeLines = timelineCompactThreeLines,
                expanded = expanded,
                expandable = expandable,
                cardText = cardText,
                onNoteClick = onNoteClick,
                publishedActions = publishedActions,
            )
        }
    }
}

@Composable
private fun NoteCardTimelineShell(
    shellModifier: Modifier,
    note: Note,
    categoryEmoji: String,
    categoryLabel: String,
    secondaryLine: String,
    headlineTopic: String,
    tagsPreview: String,
    projectFirstName: String?,
    projectCount: Int,
    bodyMaxLines: Int,
    timelineCompactThreeLines: Boolean,
    expanded: Boolean,
    expandable: Boolean,
    cardText: GlassTextColors,
    onNoteClick: (Long) -> Unit,
    publishedActions: PublishedNoteCardActions?,
) {
    GlassNoteCardShell(modifier = shellModifier) {
                if (timelineCompactThreeLines && !expanded) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        NoteCategoryIconTile(emoji = categoryEmoji, fontSizeSp = 18)
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = headlineTopic,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = cardText.primary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = note.behaviorSummary.ifBlank { "—" },
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = cardText.primary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = note.body.ifBlank { " " },
                                fontSize = 13.sp,
                                color = cardText.secondary,
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
                        NoteCategoryIconTile(emoji = categoryEmoji, fontSizeSp = 18)
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = categoryLabel,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = cardText.primary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            if (secondaryLine.isNotBlank()) {
                                Text(
                                    text = secondaryLine,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = cardText.secondary,
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
                            textColor = cardText.muted,
                            timestampColor = cardText.muted,
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
                            MoodOrEmoji(note.moodIcon, tint = cardText.secondary)
                            if (tagsPreview.isNotBlank()) {
                                Text(
                                    text = tagsPreview,
                                    fontSize = 12.sp,
                                    color = cardText.muted,
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
                            Text(LocalizedStrings.get("note_card_edit"), color = cardText.primary)
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
