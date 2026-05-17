package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.LocalUrgencyConfig
import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.components.TaskCardSwipeStack
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task
import com.example.taskmodel.util.ColorUtils.parseHexToArgb

/**
 * Unified glass task row: **分类图标 · 文本 · 紧急度圆圈** + 玻璃皮 + 经典手势（左滑删除、长按跨时段拖）。
 */
@Composable
fun GlassTaskCard(
    task: Task,
    onToggleComplete: () -> Unit,
    onOpenDetail: () -> Unit,
    modifier: Modifier = Modifier,
    enableSwipeToDelete: Boolean = false,
    onSwipeDelete: () -> Unit = {},
    onDragAnchorYRoot: ((Float) -> Unit)? = null,
    onDragVerticalEnd: ((totalDy: Float) -> Unit)? = null,
) {
    val urgencyConfig = LocalUrgencyConfig.current
    val urgencyColor = Color(parseHexToArgb(urgencyConfig.colorForLevel(task.urgency)))
    val hasDescription = task.description.isNotBlank()
    val minCardHeight = if (hasDescription) 58.dp else 48.dp
    val shape = RoundedCornerShape(GlassConstants.CornerRadius)

    val titleColor = if (task.isCompleted) {
        GlassConstants.TextMuted
    } else {
        GlassConstants.TextPrimary
    }
    val descriptionColor = if (task.isCompleted) {
        GlassConstants.TextMuted.copy(alpha = 0.75f)
    } else {
        GlassConstants.TextSecondary
    }

    val routeBTextShadow = GlassConstants.useTaskCardTextShadow
    fun taskTextStyle(base: TextStyle): TextStyle =
        if (routeBTextShadow) glassChromeTextStyle(base, useLightText = true) else base

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
                onDragCancel = { totalDy = 0f },
            )
        }
    } else {
        Modifier
    }

    @Composable
    fun CardBody() {
        GlassSurface(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = minCardHeight)
                .then(
                    if (onDragAnchorYRoot != null) {
                        Modifier.onGloballyPositioned { lc ->
                            onDragAnchorYRoot.invoke(lc.boundsInRoot().center.y)
                        }
                    } else {
                        Modifier
                    },
                ),
            shape = shape,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = GlassConstants.CardPaddingHorizontal,
                        vertical = GlassConstants.CardPaddingVertical,
                    ),
                verticalAlignment = Alignment.Top,
            ) {
                GlassTaskTagLeading(
                    label = task.label,
                    modifier = Modifier.size(22.dp),
                )
                Spacer(Modifier.width(12.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .then(dragModifier)
                        .clickable(onClick = onOpenDetail),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = task.title,
                        color = titleColor,
                        style = taskTextStyle(
                            TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                            ),
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null,
                    )
                    if (hasDescription) {
                        Text(
                            text = task.description,
                            color = descriptionColor,
                            style = taskTextStyle(TextStyle(fontSize = 11.sp)),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null,
                        )
                    }
                }

                Spacer(Modifier.width(12.dp))

                GlassUrgencyCompleteButton(
                    isCompleted = task.isCompleted,
                    urgencyColor = urgencyColor,
                    onClick = onToggleComplete,
                )
            }
        }
    }

    val wrapped = if (enableSwipeToDelete) {
        @Composable {
            TaskCardSwipeStack(
                taskId = task.id,
                cardShape = shape,
                cornerRadius = GlassConstants.CornerRadius,
                onSwipeDelete = onSwipeDelete,
            ) { CardBody() }
        }
    } else {
        @Composable { CardBody() }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onDragAnchorYRoot != null) {
                    Modifier // anchor handled inside if needed
                } else {
                    Modifier
                },
            ),
    ) {
        wrapped()
    }
}

@Composable
private fun GlassTaskTagLeading(
    label: String?,
    modifier: Modifier = Modifier,
) {
    val emoji = when {
        label.isNullOrBlank() -> TaskConstants.LABEL_ICONS[TaskConstants.LABEL_NONE]
        else -> TaskConstants.LABEL_ICONS[label]
    }
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (emoji != null) {
            Text(text = emoji, fontSize = 16.sp)
        } else {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Label,
                contentDescription = null,
                tint = GlassConstants.TextSecondary,
                modifier = Modifier.size(18.dp),
            )
        }
    }
}

@Composable
private fun GlassUrgencyCompleteButton(
    isCompleted: Boolean,
    urgencyColor: Color,
    onClick: () -> Unit,
) {
    val completedBrush = Brush.radialGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.35f),
            Color.White.copy(alpha = 0.18f),
        ),
    )
    val softUrgencyFill = urgencyColor.copy(alpha = 0.20f)
    val softUrgencyBorder = urgencyColor.copy(alpha = 0.62f)

    Box(
        modifier = Modifier
            .size(GlassConstants.CompleteButtonSize)
            .clip(CircleShape)
            .then(
                if (isCompleted) {
                    Modifier.background(completedBrush)
                } else {
                    Modifier.background(softUrgencyFill)
                },
            )
            .border(
                width = GlassConstants.CompleteButtonBorderWidth,
                color = if (isCompleted) {
                    GlassConstants.TextMuted.copy(alpha = 0.55f)
                } else {
                    softUrgencyBorder
                },
                shape = CircleShape,
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        if (isCompleted) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = LocalizedStrings.get("cd_completed_task"),
                tint = GlassConstants.TextPrimary.copy(alpha = 0.85f),
                modifier = Modifier.size(GlassConstants.CompletedCheckSize),
            )
        }
    }
}
