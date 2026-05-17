package com.example.kairosapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

/**
 * Swipe-to-delete: red strip grows from the trailing edge as the card slides left.
 */
@Composable
fun TaskCardSwipeStack(
    taskId: Int,
    cardShape: RoundedCornerShape,
    cornerRadius: Dp,
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
    val stripEndShape = remember(cornerRadius) {
        RoundedCornerShape(
            topStart = 0.dp,
            bottomStart = 0.dp,
            topEnd = cornerRadius,
            bottomEnd = cornerRadius,
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
                .offset { IntOffset(offsetX.roundToInt(), 0) }
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
                        onDragCancel = { offsetX = 0f },
                    )
                },
        ) {
            content()
        }
    }
}
