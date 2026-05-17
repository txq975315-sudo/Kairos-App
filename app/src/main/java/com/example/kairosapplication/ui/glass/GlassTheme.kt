package com.example.kairosapplication.ui.glass

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.kairosapplication.core.ui.constants.GlassConstants

/**
 * Text color palette for Glass UI.
 * All colors are white-based to work on dark backgrounds.
 */
data class GlassTextColors(
    val primary: Color = GlassConstants.TextPrimary,
    val secondary: Color = GlassConstants.TextSecondary,
    val muted: Color = GlassConstants.TextMuted,
)

/**
 * CompositionLocal for providing Glass text colors down the tree.
 * Mirrors the pattern of [com.example.kairosapplication.core.ui.LocalUrgencyConfig].
 */
val LocalGlassTextColors = compositionLocalOf { GlassTextColors() }
