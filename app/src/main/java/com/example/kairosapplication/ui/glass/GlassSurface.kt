package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.example.kairosapplication.core.ui.constants.GlassConstants

/**
 * Frosted bubble: A/B use layered tint; [GlassBubbleRecipe.C] is bright liquid glass
 * (backdrop blur + flat lift + top specular + hairline edge).
 *
 * @param wrapHazeToContent When true (chips/pills), haze is clipped to content size.
 *   When false (task rows with [Modifier.fillMaxWidth]), haze fills the given bounds.
 */
@Composable
fun GlassSurface(
    modifier: Modifier = Modifier,
    shape: Shape,
    fillAlpha: Float = GlassConstants.TaskCardFillAlpha,
    wrapHazeToContent: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    val borderColor = Color.White.copy(alpha = GlassConstants.GlassBorderAlpha)
    val flatFrost = GlassConstants.usesBackdropBlur
    val sizedModifier = if (flatFrost && wrapHazeToContent) {
        modifier.wrapContentSize(Alignment.Center, unbounded = true)
    } else {
        modifier
    }

    Box(
        modifier = sizedModifier
            .then(
                if (flatFrost) {
                    Modifier
                } else {
                    Modifier.shadow(
                        elevation = GlassConstants.GlassShadowElevation,
                        shape = shape,
                        ambientColor = Color.Black.copy(alpha = GlassConstants.ShadowAmbientAlpha),
                        spotColor = Color.Black.copy(alpha = GlassConstants.ShadowSpotAlpha),
                    )
                },
            )
            .clip(shape)
            .glassBubbleBackdrop(),
    ) {
        if (!flatFrost && fillAlpha > 0f) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = fillAlpha), shape),
            )
        }
        val sheen = GlassConstants.TaskCardSheenAlpha
        if (!flatFrost && sheen > 0f) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.White.copy(alpha = sheen), shape),
            )
        }
        GlassFrostOverlays(shape = shape)
        if (GlassConstants.GlassBorderAlpha > 0f) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .border(
                        width = GlassConstants.GlassBorderWidth,
                        color = borderColor,
                        shape = shape,
                    ),
            )
        }
        content()
    }
}
