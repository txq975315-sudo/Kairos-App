package com.example.kairosapplication.ui.mine.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Suppress("UNUSED_PARAMETER")
@Composable
fun MoodDragTarget(
    targetDate: LocalDate,
    onDragEnter: () -> Unit,
    onDragExit: () -> Unit,
    onDrop: (moodIcon: String, targetDate: LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    isHighlighted: Boolean,
    onBoundsChanged: (LocalDate, Rect) -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .onGloballyPositioned { coords ->
                val pos = coords.positionInRoot()
                onBoundsChanged(
                    targetDate,
                    Rect(
                        pos.x,
                        pos.y,
                        pos.x + coords.size.width,
                        pos.y + coords.size.height
                    )
                )
            }
            .background(
                if (isHighlighted) Color(0x334CAF50) else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
    ) {
        content()
    }
}
