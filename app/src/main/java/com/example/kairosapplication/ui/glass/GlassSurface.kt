package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.example.kairosapplication.core.ui.constants.GlassConstants

@Composable
fun GlassSurface(
    modifier: Modifier = Modifier,
    shape: Shape,
    fillAlpha: Float = GlassConstants.TaskCardFillAlpha,
    blurRadius: Dp = GlassConstants.TaskCardBlurRadius,
    withEdgeGlow: Boolean = true,
    content: @Composable BoxScope.() -> Unit,
) {
    val edgeGlowBrush = Brush.verticalGradient(
        colorStops = arrayOf(
            0f to Color.White.copy(alpha = GlassConstants.GlowTopAlpha),
            0.3f to Color.White.copy(alpha = GlassConstants.GlowFadeAlpha),
            0.7f to Color.Transparent,
            1f to Color.White.copy(alpha = GlassConstants.GlowBottomAlpha),
        ),
    )
    Box(modifier = modifier.clip(shape)) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .blur(blurRadius)
                .background(Color.White.copy(alpha = fillAlpha), shape),
        )
        if (withEdgeGlow) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(edgeGlowBrush, shape),
            )
        }
        Box(
            modifier = Modifier
                .matchParentSize()
                .border(
                    width = GlassConstants.GlassBorderWidth,
                    color = Color.White.copy(alpha = GlassConstants.GlassBorderAlpha),
                    shape = shape,
                ),
        )
        content()
    }
}
