package com.example.kairosapplication.ui.glass

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.core.ui.constants.GlassBubbleRecipe
import com.example.kairosapplication.core.ui.constants.GlassConstants
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

val LocalGlassHazeState = compositionLocalOf<HazeState?> { null }

@Composable
fun rememberGlassHazeState(): HazeState = remember { HazeState() }

/** Wallpaper layer sampled by route-C bubbles (pair with [glassBubbleBackdrop]). */
fun Modifier.glassHazeSource(state: HazeState): Modifier = haze(state)

/**
 * Route C: backdrop blur of [glassHazeSource] content (reference chat glass).
 */
@Composable
fun Modifier.glassBubbleBackdrop(): Modifier {
    if (LocalAppUiTheme.current == AppUiTheme.Classic) return this
    if (GlassConstants.bubbleRecipe != GlassBubbleRecipe.C) return this
    val state = LocalGlassHazeState.current ?: return this
    val style = HazeDefaults.style(
        backgroundColor = Color.Transparent,
        tint = HazeDefaults.tint(Color.Black.copy(alpha = GlassConstants.GlassHazeTintAlpha)),
        blurRadius = GlassConstants.GlassBackdropBlurRadius,
        noiseFactor = GlassConstants.GlassHazeNoiseFactor,
    )
    return hazeChild(state = state, style = style)
}

/** Flat glass plate for essay side nav columns (no corner radius). */
@Composable
fun Modifier.glassNavColumnPlate(
    fillAlpha: Float = GlassConstants.TimeBlockFillAlpha,
): Modifier {
    val fill = Color.Black.copy(alpha = fillAlpha)
    if (!GlassConstants.usesBackdropBlur) return background(fill)
    return glassBubbleBackdrop().background(fill)
}

/** Unified bottom dock plate — same backdrop recipe as task bubbles. */
@Composable
fun Modifier.glassBottomNavDock(): Modifier {
    if (LocalAppUiTheme.current == AppUiTheme.Classic) {
        return background(com.example.kairosapplication.core.ui.AppColors.ScreenBackground)
    }
    val fill = Color.Black.copy(alpha = GlassConstants.BottomNavFillAlpha)
    if (!GlassConstants.usesBackdropBlur) return background(fill)
    return glassBubbleBackdrop().background(fill)
}
