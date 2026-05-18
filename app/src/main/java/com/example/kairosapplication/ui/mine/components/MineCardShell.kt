package com.example.kairosapplication.ui.mine.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.ui.widget.WidgetPreviewCardShell

/**
 * Mine tab cards: Classic = opaque white + shadow; Glass = [GlassSurface] frost.
 */
@Composable
fun MineCardShell(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(AppShapes.FeaturePanelRadius),
    wrapHazeToContent: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    WidgetPreviewCardShell(
        modifier = modifier,
        shape = shape,
        wrapHazeToContent = wrapHazeToContent,
        content = content,
    )
}
