package com.example.kairosapplication.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.ui.glass.LocalGlassAtmosphereUi
import com.example.kairosapplication.ui.glass.quoteDividerColor

data class ViewChromeColors(
    val primary: Color,
    val secondary: Color,
    val muted: Color,
    val divider: Color,
    val icon: Color,
    /** True when chrome uses light-on-dark text (glass top zone is dark). */
    val useLightChrome: Boolean,
)

val LocalViewChrome = compositionLocalOf {
    ViewChromeColors(
        primary = AppColors.PrimaryText,
        secondary = AppColors.SecondaryText,
        muted = AppColors.HintText,
        divider = AppColors.Divider,
        icon = AppColors.IconNeutral,
        useLightChrome = false,
    )
}

@Composable
fun rememberViewChromeColors(): ViewChromeColors {
    val theme = LocalAppUiTheme.current
    if (theme != AppUiTheme.Glass) {
        return ViewChromeColors(
            primary = AppColors.PrimaryText,
            secondary = AppColors.SecondaryText,
            muted = AppColors.HintText,
            divider = AppColors.Divider,
            icon = AppColors.IconNeutral,
            useLightChrome = false,
        )
    }
    val atmosphere = LocalGlassAtmosphereUi.current
    return ViewChromeColors(
        primary = atmosphere.topChrome.primary,
        secondary = atmosphere.topChrome.secondary,
        muted = atmosphere.topChrome.muted,
        divider = atmosphere.quoteDividerColor(),
        icon = atmosphere.topChrome.secondary,
        useLightChrome = !atmosphere.zones.topIsLight,
    )
}
