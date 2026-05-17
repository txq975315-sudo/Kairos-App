package com.example.kairosapplication.ui.glass

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.kairosapplication.R
import com.example.kairosapplication.core.ui.constants.GlassConstants
import dev.chrisbanes.haze.HazeState

/** Adaptive chrome colors + zone flags; task cards use [cardText] only. */
data class GlassAtmosphereUi(
    val zones: GlassAtmosphereZones,
    val topChrome: GlassTextColors,
    val bottomChrome: GlassTextColors,
    val cardText: GlassTextColors = GlassTextColors.onDarkBubble(),
) {
    companion object {
        fun fromZones(zones: GlassAtmosphereZones): GlassAtmosphereUi = GlassAtmosphereUi(
            zones = zones,
            topChrome = chromeColorsForZone(zones.topIsLight),
            bottomChrome = chromeColorsForZone(zones.bottomIsLight),
        )

        fun default(): GlassAtmosphereUi = fromZones(GlassAtmosphereZones.default())

        private fun chromeColorsForZone(isLight: Boolean): GlassTextColors =
            if (isLight) GlassTextColors.onLightBackground() else GlassTextColors.onDarkBackground()
    }
}

val LocalGlassAtmosphereUi = compositionLocalOf { GlassAtmosphereUi.default() }

@Composable
fun rememberGlassAtmosphereUi(
    @DrawableRes wallpaperResId: Int = R.drawable.kairos_atmosphere_bg,
): GlassAtmosphereUi {
    val context = LocalContext.current
    return remember(wallpaperResId) {
        val zones = AtmosphereLuminance.sampleZones(context, wallpaperResId)
        GlassAtmosphereUi.fromZones(zones)
    }
}

@Composable
fun ProvideGlassAtmosphereUi(
    hazeState: HazeState? = null,
    @DrawableRes wallpaperResId: Int = R.drawable.kairos_atmosphere_bg,
    content: @Composable () -> Unit,
) {
    val ui = rememberGlassAtmosphereUi(wallpaperResId)
    CompositionLocalProvider(
        LocalGlassAtmosphereUi provides ui,
        LocalGlassTextColors provides ui.cardText,
        LocalGlassHazeState provides hazeState,
        content = content,
    )
}

fun GlassAtmosphereUi.quoteDividerColor(): Color =
    if (zones.topIsLight) GlassConstants.ChromeOnLightDivider else GlassConstants.ChromeOnDarkDivider
