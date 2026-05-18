package com.example.kairosapplication.ui.glass

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.kairosapplication.R
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.core.ui.constants.GlassConstants
import dev.chrisbanes.haze.HazeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
    wallpaperUri: String? = null,
): GlassAtmosphereUi {
    val context = LocalContext.current
    var ui by remember { mutableStateOf(GlassAtmosphereUi.default()) }
    LaunchedEffect(wallpaperResId, wallpaperUri) {
        val zones = withContext(Dispatchers.IO) {
            if (!wallpaperUri.isNullOrBlank()) {
                AtmosphereLuminance.sampleZonesFromUri(context, wallpaperUri)
                    ?: AtmosphereLuminance.sampleZones(context, wallpaperResId)
            } else {
                AtmosphereLuminance.sampleZones(context, wallpaperResId)
            }
        }
        ui = GlassAtmosphereUi.fromZones(zones)
    }
    return ui
}

@Composable
fun ProvideGlassAtmosphereUi(
    hazeState: HazeState? = null,
    @DrawableRes wallpaperResId: Int = R.drawable.kairos_atmosphere_bg,
    wallpaperUri: String? = null,
    content: @Composable () -> Unit,
) {
    val ui = rememberGlassAtmosphereUi(wallpaperResId, wallpaperUri)
    CompositionLocalProvider(
        LocalAppUiTheme provides AppUiTheme.Glass,
        LocalGlassAtmosphereUi provides ui,
        LocalGlassTextColors provides ui.cardText,
        LocalGlassHazeState provides hazeState,
    ) {
        KairosSystemBarsEffect(
            lightStatusBars = ui.zones.topIsLight,
            lightNavigationBars = ui.zones.bottomIsLight,
        )
        content()
    }
}

fun GlassAtmosphereUi.quoteDividerColor(): Color =
    if (zones.topIsLight) GlassConstants.ChromeOnLightDivider else GlassConstants.ChromeOnDarkDivider

@Composable
fun ProvideAppUiTheme(
    theme: AppUiTheme,
    hazeState: HazeState? = null,
    wallpaperUri: String? = null,
    content: @Composable () -> Unit,
) {
    when (theme) {
        AppUiTheme.Glass -> ProvideGlassAtmosphereUi(
            hazeState = hazeState,
            wallpaperUri = wallpaperUri,
            content = content,
        )
        AppUiTheme.Classic -> {
            val classicUi = GlassAtmosphereUi.fromZones(
                GlassAtmosphereZones(topIsLight = true, bottomIsLight = true),
            )
            CompositionLocalProvider(
                LocalAppUiTheme provides AppUiTheme.Classic,
                LocalGlassAtmosphereUi provides classicUi,
                LocalGlassTextColors provides GlassTextColors.onLightBackground(),
                LocalGlassHazeState provides null,
            ) {
                KairosSystemBarsEffect(lightStatusBars = true, lightNavigationBars = true)
                content()
            }
        }
    }
}
