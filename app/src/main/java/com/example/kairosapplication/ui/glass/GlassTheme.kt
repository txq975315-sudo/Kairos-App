package com.example.kairosapplication.ui.glass

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.kairosapplication.core.ui.constants.GlassConstants

/** Text palette for a surface type. */
data class GlassTextColors(
    val primary: Color = GlassConstants.TextPrimary,
    val secondary: Color = GlassConstants.TextSecondary,
    val muted: Color = GlassConstants.TextMuted,
) {
    companion object {
        fun onDarkBubble(): GlassTextColors = GlassTextColors()

        fun onDarkBackground(): GlassTextColors = GlassTextColors()

        fun onLightBackground(): GlassTextColors = GlassTextColors(
            primary = GlassConstants.ChromeOnLightPrimary,
            secondary = GlassConstants.ChromeOnLightSecondary,
            muted = GlassConstants.ChromeOnLightMuted,
        )
    }
}

/** Task-card text colors (fixed white system). */
val LocalGlassTextColors = compositionLocalOf { GlassTextColors.onDarkBubble() }
