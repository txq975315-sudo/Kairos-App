package com.example.kairosapplication.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.LocalUrgencyConfig
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task
import com.example.taskmodel.util.ColorUtils.parseHexToArgb
import kotlin.math.roundToInt

/**
 * Swipe-to-delete: **no** full-width red underlay at rest.
 * A red strip is **only** laid out on the trailing edge; its width grows with swipe distance
 * (`-offsetX`) while the card slides left, so delete UI “exports” from the right during the gesture.
 */
@Composable
private fun TaskCardSwipeStack(
    taskId: Int,
    cardShape: RoundedCornerShape,
    onSwipeDelete: () -> Unit,
    content: @Composable () -> Unit,
) {
    var offsetX by remember(taskId) { mutableFloatStateOf(0f) }
    LaunchedEffect(taskId) {
        offsetX = 0f
    }
    val density = LocalDensity.current
    val deleteBrush = remember {
        Brush.horizontalGradient(
            colorStops = arrayOf(
                0f to Color(0xFFE57373).copy(alpha = 0.95f),
                1f to Color(0xFFE53935).copy(alpha = 0.98f),
            ),
        )
    }
    val stripEndShape = remember {
        RoundedCornerShape(
            topStart = 0.dp,
            bottomStart = 0.dp,
            topEnd = AppShapes.CardRadius,
            bottomEnd = AppShapes.CardRadius,
        )
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .clip(cardShape),
    ) {
        val widthPx = constraints.maxWidth.toFloat().coerceAtLeast(1f)
        val maxDragPx = widthPx * 0.48f
        val dismissThresholdPx = widthPx * 0.28f
        val revealPx = (-offsetX).coerceIn(0f, widthPx * 0.52f)
        val stripWidthDp = with(density) { revealPx.toDp() }

        if (revealPx > 0.5f) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .width(stripWidthDp)
                    .fillMaxHeight()
                    .background(deleteBrush, stripEndShape),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset {
                    IntOffset(offsetX.roundToInt(), 0)
                }
                .pointerInput(taskId, widthPx, maxDragPx) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { change, dragAmount ->
                            change.consume()
                            offsetX = (offsetX + dragAmount).coerceIn(-maxDragPx, 0f)
                        },
                        onDragEnd = {
                            if (-offsetX >= dismissThresholdPx) {
                                onSwipeDelete()
                            }
                            offsetX = 0f
                        },
                        onDragCancel = {
                            offsetX = 0f
                        },
                    )
                },
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItemCard(
    task: Task,
    onToggleComplete: () -> Unit,
    onOpenDetail: () -> Unit,
    enableSwipeToDelete: Boolean = false,
    onSwipeDelete: () -> Unit = {},
    onDragAnchorYRoot: ((Float) -> Unit)? = null,
    onDragVerticalEnd: ((totalDy: Float) -> Unit)? = null
) {
    val urgencyConfig = LocalUrgencyConfig.current
    val urgencyColor = Color(parseHexToArgb(urgencyConfig.colorForLevel(task.urgency)))
    val hasDescription = task.description.isNotBlank()
    val titleFontSize = 15.sp
    val descriptionFontSize = 10.sp
    val minCardHeight = if (hasDescription) 58.dp else 48.dp
    val cardShape = RoundedCornerShape(AppShapes.CardRadius)

    val dragEnd = onDragVerticalEnd
    val dragModifier = if (dragEnd != null) {
        Modifier.pointerInput(task.id, dragEnd) {
            var totalDy = 0f
            detectDragGesturesAfterLongPress(
                onDragStart = { totalDy = 0f },
                onDrag = { _, dragAmount -> totalDy += dragAmount.y },
                onDragEnd = {
                    dragEnd.invoke(totalDy)
                    totalDy = 0f
                },
                onDragCancel = { totalDy = 0f }
            )
        }
    } else {
        Modifier
    }

    @Composable
    fun TaskCardInner() {
        val glassBrush = Brush.verticalGradient(
            colorStops = arrayOf(
                0f to AppColors.TaskCardGlassTop,
                0.42f to AppColors.TaskCardGlassMid,
                1f to AppColors.TaskCardGlassBottom,
            ),
        )
        val innerGlowBrush = Brush.verticalGradient(
            colorStops = arrayOf(
                0f to AppColors.TaskCardGlassInnerGlow,
                0.3f to Color.Transparent,
                0.7f to Color.Transparent,
                1f to AppColors.TaskCardGlassInnerGlow.copy(alpha = 0.2f),
            ),
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = AppColors.TaskCardShadowElevation,
                    shape = cardShape,
                    ambientColor = AppColors.TaskCardShadowColor,
                    spotColor = Color(0xFF1A2850).copy(alpha = 0.20f),
                )
                .clip(cardShape)
                .border(
                    width = 1.1.dp,
                    color = AppColors.TaskCardGlassHairline,
                    shape = cardShape
                )
                .onGloballyPositioned { lc ->
                    onDragAnchorYRoot?.invoke(lc.boundsInRoot().center.y)
                },
            shape = cardShape,
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(cardShape)
                    .background(glassBrush, cardShape)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .blur(AppColors.TaskCardBlurRadius)
                        .background(AppColors.TaskCardGrayMist, cardShape)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .blur(AppColors.TaskCardBlurRadius)
                        .background(innerGlowBrush, cardShape)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = minCardHeight)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TaskTagLeading(
                        label = task.label,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(Modifier.width(12.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .then(dragModifier)
                            .clickable { onOpenDetail() },
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = task.title,
                            fontSize = titleFontSize,
                            fontWeight = FontWeight.Medium,
                            color = if (task.isCompleted) Color(0xFF9E9E9E) else Color(0xFF333333),
                            textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                        )
                        if (hasDescription) {
                            Text(
                                text = task.description,
                                fontSize = descriptionFontSize,
                                color = Color(0xFF757575),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                            )
                        }
                    }

                    Spacer(Modifier.width(12.dp))

                    val completedBrush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFE8E8E8).copy(alpha = 0.9f),
                            Color(0xFFDFDFDF).copy(alpha = 0.7f)
                        ),
                        center = androidx.compose.ui.geometry.Offset(12f, 12f),
                        radius = 12f
                    )
                    val incompleteColor = urgencyColor.copy(alpha = 0.12f)
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .then(
                                if (task.isCompleted) {
                                    Modifier.background(completedBrush)
                                } else {
                                    Modifier.background(incompleteColor)
                                }
                            )
                            .border(
                                width = 2.2.dp,
                                color = if (task.isCompleted) {
                                    Color(0xFFCCCCCC)
                                } else {
                                    urgencyColor.copy(alpha = 0.85f)
                                },
                                shape = CircleShape
                            )
                            .clickable { onToggleComplete() },
                        contentAlignment = Alignment.Center
                    ) {
                        if (task.isCompleted) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = LocalizedStrings.get("cd_completed_task"),
                                tint = Color(0xFF8A8A8A),
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    if (enableSwipeToDelete) {
        TaskCardSwipeStack(
            taskId = task.id,
            cardShape = cardShape,
            onSwipeDelete = onSwipeDelete,
        ) {
            TaskCardInner()
        }
    } else {
        TaskCardInner()
    }
}

@Composable
private fun TaskTagLeading(
    label: String?,
    modifier: Modifier = Modifier,
) {
    val emoji = when {
        label.isNullOrBlank() -> TaskConstants.LABEL_ICONS[TaskConstants.LABEL_NONE]
        else -> TaskConstants.LABEL_ICONS[label]
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (emoji != null) {
            Text(text = emoji, fontSize = 15.sp)
        } else {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Label,
                contentDescription = null,
                tint = Color(0xFF757575),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
