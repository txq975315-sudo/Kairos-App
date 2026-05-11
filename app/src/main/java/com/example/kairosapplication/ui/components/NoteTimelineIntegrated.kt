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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.ui.topic.rememberTopicPrimaryLabel
import com.example.kairosapplication.ui.topic.rememberTopicSecondaryLabel
import com.example.taskmodel.model.Note
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val IntegratedSummaryColor = Color(0xFF000000)
private val IntegratedBodyColor = Color(0xFF333333)
private val TopicCapsuleBg = Color(0xFFF0F0F0)

/** Vertical inset so the rail sits ~10–15dp from adjacent note times (see day list spacing). */
private val RailLineEndInset = 12.dp

/** Push the rail below the time row to avoid overlap. */
private val RailLineBelowTimeShift = 6.dp

/** Extends drawable rail length while keeping total vertical margin budget. */
private val RailLineLengthExtra = 6.dp

/** Shorten rail from the top; bottom anchor unchanged. */
private val RailLineTopCut = 4.dp

private val SecondarySummaryGap = 1.dp

/**
 * Pure timeline: line 1 = time + optional secondary + topic capsule; optional summary row;
 * body and tags share the same start inset as secondary. Empty secondary/summary rows are omitted.
 */
@Composable
fun NoteTimelineIntegrated(
    note: Note,
    accentColor: Color,
    onNoteClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    projectsById: Map<Long, String> = emptyMap(),
    expandable: Boolean = true,
    expanded: Boolean = false,
    onToggleExpand: () -> Unit = {},
    publishedActions: PublishedNoteCardActions? = null,
    /** Dark wallpaper: use light summary/body/capsule and action colors. */
    lightForeground: Boolean = false,
) {
    val zone = ZoneId.systemDefault()
    val timeStr = remember(note.createdAt) {
        val z = Instant.ofEpochMilli(note.createdAt).atZone(zone)
        DateTimeFormatter.ofPattern("HH:mm").format(z)
    }
    val secondaryText = rememberTopicSecondaryLabel(note.primaryCategory, note.secondaryCategory)
    val hasSecondary = secondaryText.isNotBlank()
    val summaryText = remember(note.behaviorSummary) { note.behaviorSummary.trim() }
    val hasSummary = summaryText.isNotBlank()
    val primaryLabel = rememberTopicPrimaryLabel(note.primaryCategory)
    val categoryEmoji = remember(note.primaryCategory) {
        NoteCardConstants.categoryEmoji(note.primaryCategory)
    }
    val projectGuillemet = remember(note.projectIds, projectsById) {
        val id = note.projectIds.firstOrNull() ?: return@remember null
        val name = projectsById[id]?.trim().orEmpty().ifBlank { return@remember null }
        "《$name》"
    }
    val tagsLine = remember(note.sceneTags) {
        if (note.sceneTags.isEmpty()) return@remember null
        note.sceneTags.joinToString(" ") { tag ->
            val t = tag.trim()
            if (t.startsWith("#")) t else "#$t"
        }
    }
    val bodyMaxLines = if (expandable && !expanded) 4 else Int.MAX_VALUE

    val summaryColor = if (lightForeground) Color(0xE6FFFFFF) else IntegratedSummaryColor
    val bodyColor = if (lightForeground) Color(0xCCFFFFFF) else IntegratedBodyColor
    val capsuleLabelColor = if (lightForeground) Color(0xFFF5F5F5) else IntegratedBodyColor
    val capsuleBg = if (lightForeground) Color(0x33FFFFFF) else TopicCapsuleBg
    val metaTagColor = if (lightForeground) Color(0xB3FFFFFF) else AppColors.HintText

    val railWidth = 40.dp
    val railToTextGap = 8.dp
    val railBottomInset =
        RailLineEndInset - RailLineBelowTimeShift - RailLineLengthExtra

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (expandable) onToggleExpand() else onNoteClick(note.id)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    val topPx = (RailLineEndInset + RailLineBelowTimeShift + RailLineTopCut).toPx()
                    val bottomPx = railBottomInset.coerceAtLeast(0.dp).toPx()
                    val cx = railWidth.toPx() / 2f
                    val lineW = 2.dp.toPx()
                    drawRect(
                        color = AppColors.TimelineLine,
                        topLeft = Offset(cx - lineW / 2f, topPx),
                        size = Size(lineW, (size.height - topPx - bottomPx).coerceAtLeast(0f))
                    )
                }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.width(railWidth),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = timeStr,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = accentColor,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.width(railToTextGap))
                if (hasSecondary) {
                    Text(
                        text = secondaryText,
                        modifier = Modifier.weight(1f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = accentColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.width(8.dp))
                Surface(
                    shape = RoundedCornerShape(50),
                    color = capsuleBg,
                    shadowElevation = 0.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Text(
                            text = categoryEmoji,
                            fontSize = 14.sp,
                            maxLines = 1
                        )
                        Text(
                            text = primaryLabel,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = capsuleLabelColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            if (hasSummary) {
                Spacer(Modifier.height(SecondarySummaryGap))
                Text(
                    text = summaryText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = summaryColor,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = railWidth + railToTextGap)
                )
            }
            Spacer(
                Modifier.height(
                    when {
                        hasSummary -> 6.dp
                        hasSecondary -> 6.dp
                        else -> 2.dp
                    }
                )
            )
            val (mainBody, parsedReview) = NoteCardConstants.splitReviewFromBody(note.body)
            Text(
                text = mainBody.ifBlank { " " },
                modifier = Modifier.padding(start = railWidth + railToTextGap),
                fontSize = 14.sp,
                color = bodyColor,
                maxLines = bodyMaxLines,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp
            )
            if (note.imageUris.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                NoteImageRow(
                    imageUris = note.imageUris,
                    maxImages = 4,
                    modifier = Modifier.padding(start = railWidth + railToTextGap)
                )
            }
            if (parsedReview != null) {
                Spacer(Modifier.height(8.dp))
                val commentTint = if (lightForeground) Color(0x99FFFFFF) else AppColors.HintText
                val timeTint = if (lightForeground) Color(0xB3FFFFFF) else AppColors.HintText
                NoteReviewSection(
                    review = parsedReview,
                    expanded = expanded || !expandable,
                    collapsedMaxLines = if (expandable && !expanded) 2 else Int.MAX_VALUE,
                    textColor = commentTint,
                    timestampColor = timeTint,
                    modifier = Modifier.padding(start = railWidth + railToTextGap),
                )
            }
            if (tagsLine != null || projectGuillemet != null) {
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = railWidth + railToTextGap),
                    verticalAlignment = Alignment.Bottom
                ) {
                    if (tagsLine != null) {
                        Text(
                            text = tagsLine,
                            fontSize = 12.sp,
                            color = metaTagColor,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    if (projectGuillemet != null) {
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = projectGuillemet,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = bodyColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
            if (expandable && expanded) {
                Spacer(Modifier.height(10.dp))
                if (publishedActions != null) {
                    PublishedNoteActionsRow(
                        actions = publishedActions,
                        hasProjects = note.projectIds.isNotEmpty(),
                        horizontalContentPadding = 8.dp,
                        lightForeground = lightForeground
                    )
                } else {
                    TextButton(
                        onClick = { onNoteClick(note.id) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Edit",
                            color = if (lightForeground) Color.White else AppColors.PrimaryText
                        )
                    }
                }
            }
        }
    }
}
