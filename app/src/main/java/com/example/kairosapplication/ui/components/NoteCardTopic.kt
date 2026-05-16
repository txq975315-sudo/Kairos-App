package com.example.kairosapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.blur
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

@Composable
fun NoteCardTopic(
    note: Note,
    onNoteClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    expandable: Boolean = false,
    expanded: Boolean = false,
    onToggleExpand: () -> Unit = {},
    publishedActions: PublishedNoteCardActions? = null,
    topicPeekWhenCollapsed: Boolean = false,
) {
    val zone = ZoneId.systemDefault()
    val timeStr = remember(note.createdAt) {
        val z = Instant.ofEpochMilli(note.createdAt).atZone(zone)
        DateTimeFormatter.ofPattern("HH:mm").format(z)
    }
    val primaryLoc = rememberTopicPrimaryLabel(note.primaryCategory)
    val secondaryLoc = rememberTopicSecondaryLabel(note.primaryCategory, note.secondaryCategory)
    val topicLabelLine = remember(primaryLoc, secondaryLoc) {
        if (secondaryLoc.isNotBlank()) "$primaryLoc · $secondaryLoc" else primaryLoc
    }

    val peekOnlySummaryBody =
        topicPeekWhenCollapsed && expandable && !expanded
    val bodyMaxLines = when {
        !expandable || expanded -> Int.MAX_VALUE
        peekOnlySummaryBody && note.behaviorSummary.isBlank() -> 4
        expandable && !expanded -> 2
        else -> Int.MAX_VALUE
    }
    val cardShape = RoundedCornerShape(AppShapes.CardRadius)
    
    // Glass effect brushes (aligned with TaskCard)
    val taskCardGlassBrush = Brush.verticalGradient(
        colorStops = arrayOf(
            0f to AppColors.TaskCardGlassTop,
            0.42f to AppColors.TaskCardGlassMid,
            1f to AppColors.TaskCardGlassBottom,
        ),
    )
    val taskCardInnerGlowBrush = Brush.verticalGradient(
        colorStops = arrayOf(
            0f to AppColors.TaskCardGlassInnerGlow,
            0.3f to Color.Transparent,
            0.7f to Color.Transparent,
            1f to AppColors.TaskCardGlassInnerGlow.copy(alpha = 0.2f),
        ),
    )
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = AppColors.TaskCardShadowElevation,
                shape = cardShape,
                ambientColor = AppColors.TaskCardShadowColor,
                spotColor = Color(0xFF1A2850).copy(alpha = 0.15f),
            )
            .clip(cardShape)
            .border(
                width = 1.1.dp,
                color = AppColors.TaskCardGlassHairline,
                shape = cardShape
            )
            .clickable {
                if (expandable) onToggleExpand() else onNoteClick(note.id)
            },
        shape = cardShape,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Box {
            // Layer 1: Glass gradient base
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(cardShape)
                    .background(taskCardGlassBrush)
            )
            // Layer 2: Gray mist overlay
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(cardShape)
                    .blur(AppColors.TaskCardBlurRadius)
                    .background(AppColors.TaskCardGrayMist)
            )
            // Layer 3: Inner glow
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(cardShape)
                    .blur(AppColors.TaskCardBlurRadius)
                    .background(taskCardInnerGlowBrush)
            )
            // Layer 4: Top veil
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(cardShape)
                    .background(AppColors.GlassFill.copy(alpha = 0.07f))
            )
            // Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppSpacing.CardHorizontal, AppSpacing.CardVertical)
            ) {
            if (!peekOnlySummaryBody) {
                Text(
                    text = topicLabelLine,
                    fontSize = 12.sp,
                    color = AppColors.HintText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (note.behaviorSummary.isNotBlank()) {
                if (!peekOnlySummaryBody) {
                    Spacer(Modifier.height(4.dp))
                }
                Text(
                    text = note.behaviorSummary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.PrimaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(
                Modifier.height(
                    when {
                        note.behaviorSummary.isBlank() -> if (peekOnlySummaryBody) 0.dp else 6.dp
                        peekOnlySummaryBody -> 6.dp
                        else -> 8.dp
                    },
                ),
            )
            val (topicMainBody, topicParsedReview) = NoteCardConstants.splitReviewFromBody(note.body)
            Text(
                text = topicMainBody.ifBlank { " " },
                fontSize = 14.sp,
                color = AppColors.SecondaryText,
                maxLines = bodyMaxLines,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp
            )
            if (note.imageUris.isNotEmpty() && !peekOnlySummaryBody) {
                Spacer(Modifier.height(8.dp))
                NoteImageRow(imageUris = note.imageUris, maxImages = 4)
            }
            if (topicParsedReview != null && !peekOnlySummaryBody) {
                Spacer(Modifier.height(8.dp))
                NoteReviewSection(
                    review = topicParsedReview,
                    expanded = expanded || !expandable,
                    collapsedMaxLines = if (expandable && !expanded) 2 else Int.MAX_VALUE,
                    textColor = AppColors.HintText,
                    timestampColor = AppColors.HintText,
                )
            }
            if (!peekOnlySummaryBody) {
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
                                        .clip(RoundedCornerShape(AppShapes.DenseInsetRadius))
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
            }
            if (expandable && expanded) {
                    Spacer(Modifier.height(8.dp))
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
            } // Column close
        } // Box close
    }
}
