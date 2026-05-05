package com.example.kairosapplication.ui.mine.components

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun DraggableMoodGestureHost(
    dragKey: String,
    modifier: Modifier = Modifier,
    onDragStart: (startInRoot: Offset) -> Unit,
    onDrag: (dragAmount: Offset) -> Unit,
    onDragEnd: (fingerInRoot: Offset) -> Unit,
    onDragCancel: () -> Unit,
    content: @Composable () -> Unit
) {
    var coords by remember { mutableStateOf<LayoutCoordinates?>(null) }
    var startRoot by remember { mutableStateOf<Offset?>(null) }
    var sumDelta by remember { mutableStateOf(Offset.Zero) }
    Box(
        modifier = modifier
            .onGloballyPositioned { coords = it }
            .pointerInput(dragKey) {
                detectDragGesturesAfterLongPress(
                    onDragStart = { local ->
                        val c = coords
                        val root = if (c != null && c.isAttached) {
                            c.localToRoot(local)
                        } else {
                            local
                        }
                        startRoot = root
                        sumDelta = Offset.Zero
                        onDragStart(root)
                    },
                    onDrag = { change, dragAmount ->
                        sumDelta += dragAmount
                        onDrag(dragAmount)
                        change.consume()
                    },
                    onDragEnd = {
                        val s = startRoot ?: Offset.Zero
                        onDragEnd(s + sumDelta)
                        startRoot = null
                        sumDelta = Offset.Zero
                    },
                    onDragCancel = {
                        startRoot = null
                        sumDelta = Offset.Zero
                        onDragCancel()
                    }
                )
            }
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun DraggableMoodItem(
    moodIcon: String,
    modifier: Modifier = Modifier,
    onDragStart: (startInRoot: Offset) -> Unit,
    onDrag: (dragAmount: Offset) -> Unit,
    onDragEnd: (fingerInRoot: Offset) -> Unit,
    onDragCancel: () -> Unit
) {
    DraggableMoodGestureHost(
        dragKey = moodIcon,
        modifier = modifier,
        onDragStart = onDragStart,
        onDrag = onDrag,
        onDragEnd = onDragEnd,
        onDragCancel = onDragCancel
    ) {
        Text(text = moodIcon, fontSize = 26.sp)
    }
}
