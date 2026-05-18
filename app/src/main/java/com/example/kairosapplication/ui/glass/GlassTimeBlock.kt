package com.example.kairosapplication.ui.glass

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task
import java.time.LocalDate

fun timeBlockIconTint(blockKey: String): Color = when (blockKey) {
    TaskConstants.TIME_BLOCK_ANYTIME -> GlassConstants.TimeBlockIconAnytime
    TaskConstants.TIME_BLOCK_MORNING -> GlassConstants.TimeBlockIconMorning
    TaskConstants.TIME_BLOCK_AFTERNOON -> GlassConstants.TimeBlockIconAfternoon
    TaskConstants.TIME_BLOCK_EVENING -> GlassConstants.TimeBlockIconEvening
    else -> GlassConstants.TextSecondary
}

@Composable
fun GlassTimeBlock(
    blockKey: String,
    displayTitle: String,
    count: Int,
    icon: ImageVector,
    expanded: Boolean,
    onToggle: () -> Unit,
    tasks: List<Task>,
    viewDate: LocalDate,
    onToggleComplete: (Task) -> Unit,
    onOpenDetail: (Task) -> Unit,
    onSwipeDelete: (Task) -> Unit,
    onTaskDragEnd: (Task, Float) -> Unit = { _, _ -> },
    onDragHandleY: (Int, Float) -> Unit = { _, _ -> },
    onBlockBounds: (String, Rect) -> Unit = { _, _ -> },
    onCreateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val iconTint = timeBlockIconTint(blockKey)
    val hasTasks = count > 0

    Column(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coords ->
                onBlockBounds(blockKey, coords.boundsInRoot())
            },
    ) {
        GlassTimeBlockHeader(
            title = displayTitle,
            count = count,
            icon = icon,
            iconTint = iconTint,
            expanded = expanded,
            hasTasks = hasTasks,
            onToggle = onToggle,
            onCreateClick = onCreateClick,
        )

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(),
            exit = shrinkVertically(),
        ) {
            Column(
                modifier = Modifier.padding(top = 6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                tasks.forEach { task ->
                    val allowDrag = task.taskDate == viewDate && !task.isCompleted
                    GlassTaskCard(
                        task = task,
                        onToggleComplete = { onToggleComplete(task) },
                        onOpenDetail = { onOpenDetail(task) },
                        enableSwipeToDelete = task.isSwipeDeletableByPolicy(viewDate),
                        onSwipeDelete = { onSwipeDelete(task) },
                        onDragAnchorYRoot = if (allowDrag) {
                            { y -> onDragHandleY(task.id, y) }
                        } else {
                            null
                        },
                        onDragVerticalEnd = if (allowDrag) {
                            { dy -> onTaskDragEnd(task, dy) }
                        } else {
                            null
                        },
                    )
                }
                if (!hasTasks) {
                    GlassTimeBlockEmptyHint(
                        blockKey = blockKey,
                        onCreateClick = onCreateClick,
                    )
                }
            }
        }
    }
}

@Composable
private fun GlassTimeBlockHeader(
    title: String,
    count: Int,
    icon: ImageVector,
    iconTint: Color,
    expanded: Boolean,
    hasTasks: Boolean,
    onToggle: () -> Unit,
    onCreateClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BoxWithConstraints {
            val maxPillWidth = maxWidth * GlassConstants.TimeBlockPillWidthFraction
            GlassPillLabel(
                text = title,
                leadingIcon = icon,
                iconTint = iconTint,
                modifier = Modifier
                    .widthIn(max = maxPillWidth)
                    .clickable(onClick = onToggle),
                trailingContent = {
                    if (count > 0) {
                        Text(
                            text = "$count",
                            color = GlassConstants.TextMuted,
                            fontSize = 12.sp,
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        tint = GlassConstants.TextMuted,
                        modifier = Modifier.size(18.dp),
                    )
                },
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (hasTasks) {
            GlassTimeBlockAddTaskButton(
                onClick = onCreateClick,
                modifier = Modifier.padding(end = GlassConstants.CardPaddingHorizontal),
            )
        }
    }
}

@Composable
fun GlassTimeBlockAddTaskButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardText = LocalGlassTextColors.current

    val addButtonSize = com.example.kairosapplication.core.ui.TimeBlockQuickAddDimens.buttonSize
    val iconSize = com.example.kairosapplication.core.ui.TimeBlockQuickAddDimens.iconSize
    GlassSurface(
        modifier = modifier
            .size(addButtonSize)
            .clickable(onClick = onClick),
        shape = CircleShape,
        fillAlpha = GlassConstants.PillFillAlpha,
        wrapHazeToContent = false,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = LocalizedStrings.get("cd_add_task"),
                tint = cardText.secondary,
                modifier = Modifier.size(iconSize),
            )
        }
    }
}

@Composable
private fun GlassTimeBlockEmptyHint(
    blockKey: String,
    onCreateClick: () -> Unit,
) {
    val shape = RoundedCornerShape(GlassConstants.CornerRadius)
    GlassSurface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onCreateClick),
        shape = shape,
        fillAlpha = GlassConstants.PillFillAlpha * 0.6f,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = LocalizedStrings.timeBlockEmptyHint(blockKey),
                color = GlassConstants.TextMuted,
                fontSize = 13.sp,
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = LocalizedStrings.get("cd_add_task"),
                tint = GlassConstants.TextSecondary,
                modifier = Modifier.size(18.dp),
            )
        }
    }
}

/** Policy mirror of TodayScreen private helper */
fun Task.isSwipeDeletableByPolicy(today: LocalDate = LocalDate.now()): Boolean {
    if (isCompleted) return false
    if (taskDate.isBefore(today)) return false
    return true
}
