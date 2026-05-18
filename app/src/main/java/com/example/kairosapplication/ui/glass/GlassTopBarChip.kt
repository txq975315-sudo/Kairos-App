package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.constants.GlassConstants

/** Top bar glass chip — same frosted treatment as the progress pill. */
@Composable
fun GlassTopBarChip(
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(GlassConstants.CornerRadius),
    content: @Composable BoxScope.() -> Unit,
) {
    val chipModifier = if (onClick != null) {
        modifier.clickable(onClick = onClick)
    } else {
        modifier
    }

    GlassSurface(
        modifier = chipModifier,
        shape = shape,
        fillAlpha = GlassConstants.BottomNavSelectedFillAlpha,
        wrapHazeToContent = true,
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center,
            content = content,
        )
    }
}
