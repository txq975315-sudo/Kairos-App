package com.example.kairosapplication.ui.glass

/**
 * Luminance classification for fixed screen zones over [androidx.compose.ui.layout.ContentScale.Crop] wallpaper.
 *
 * - [topIsLight]: top bar, date, daily quote
 * - [bottomIsLight]: bottom navigation
 *
 * Task cards use fixed light-on-dark bubbles and do not read these flags.
 */
data class GlassAtmosphereZones(
    val topIsLight: Boolean,
    val bottomIsLight: Boolean,
) {
    companion object {
        fun default(): GlassAtmosphereZones = GlassAtmosphereZones(
            topIsLight = true,
            bottomIsLight = false,
        )
    }
}
