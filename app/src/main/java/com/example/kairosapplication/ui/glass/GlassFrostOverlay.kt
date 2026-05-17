package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.example.kairosapplication.core.ui.constants.GlassConstants

/**
 * Flat route-C polish: uniform lift + 1dp top specular (liquid edge, not a bulging gradient).
 */
@Composable
fun GlassFrostOverlays(
    shape: Shape,
    modifier: Modifier = Modifier,
) {
    if (!GlassConstants.usesBackdropBlur) return

    val lift = GlassConstants.GlassFrostLiftAlpha
    if (lift > 0f) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = lift), shape),
        )
    }
    val specular = GlassConstants.GlassSpecularLineAlpha
    if (specular > 0f) {
        Box(modifier = modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(GlassConstants.GlassSpecularLineHeight)
                    .background(Color.White.copy(alpha = specular)),
            )
        }
    }
}
