package com.example.kairosapplication.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes

private val KairosShapes = Shapes(
    extraSmall = RoundedCornerShape(AppShapes.DenseInsetRadius),
    small = RoundedCornerShape(AppShapes.InsetContentRadius),
    medium = RoundedCornerShape(AppShapes.CardRadius),
    large = RoundedCornerShape(AppShapes.FeaturePanelRadius),
    extraLarge = RoundedCornerShape(AppShapes.SheetTopRadius),
)

private val KairosLightColorScheme = lightColorScheme(
    primary = Color(0xFF4E6B5C),
    onPrimary = Color(0xFFFAFBFA),
    primaryContainer = Color(0xFFC9D9CE),
    onPrimaryContainer = Color(0xFF102018),
    secondary = Color(0xFF5E6661),
    onSecondary = Color(0xFFFAFBFA),
    secondaryContainer = Color(0xFFDFE6DF),
    onSecondaryContainer = Color(0xFF1B211D),
    tertiary = Color(0xFF6E6479),
    onTertiary = Color(0xFFFAFBFA),
    tertiaryContainer = Color(0xFFE5DFF0),
    onTertiaryContainer = Color(0xFF25152F),
    background = AppColors.ScreenBackground,
    onBackground = AppColors.PrimaryText,
    surface = AppColors.CardBackground,
    onSurface = AppColors.PrimaryText,
    surfaceVariant = AppColors.SurfaceWhite,
    onSurfaceVariant = AppColors.SecondaryText,
    outline = AppColors.Divider,
    outlineVariant = AppColors.TimelineLine,
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    surfaceContainerLowest = AppColors.ScreenBackground,
    surfaceContainerLow = AppColors.BottomBarSurface,
    surfaceContainer = AppColors.SurfaceWhite,
    surfaceContainerHigh = Color(0xFFFFFFFF),
    surfaceContainerHighest = Color(0xFFFFFFFF),
)

private val KairosDarkColorScheme = darkColorScheme(
    primary = Color(0xFF9CB8A8),
    onPrimary = Color(0xFF0D1612),
    primaryContainer = Color(0xFF355045),
    onPrimaryContainer = Color(0xFFD4E8DD),
    secondary = Color(0xFFB4BCB6),
    onSecondary = Color(0xFF0D100E),
    secondaryContainer = Color(0xFF3D4540),
    onSecondaryContainer = Color(0xFFE3EAE4),
    tertiary = Color(0xFFC8BFD4),
    onTertiary = Color(0xFF0D0A10),
    tertiaryContainer = Color(0xFF4A4258),
    onTertiaryContainer = Color(0xFFEAE4F2),
    background = Color(0xFF121512),
    onBackground = Color(0xFFE6E9E6),
    surface = Color(0xFF1A1D1A),
    onSurface = Color(0xFFE6E9E6),
    surfaceVariant = Color(0xFF242924),
    onSurfaceVariant = Color(0xFFB4BAB4),
    outline = Color(0xFF3D4540),
    outlineVariant = Color(0xFF2A302C),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    surfaceContainerLowest = Color(0xFF0C0F0C),
    surfaceContainerLow = Color(0xFF161916),
    surfaceContainer = Color(0xFF1A1D1A),
    surfaceContainerHigh = Color(0xFF222522),
    surfaceContainerHighest = Color(0xFF2A2E2A),
)

@Composable
fun KairosApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> KairosDarkColorScheme
        else -> KairosLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = KairosShapes,
        content = content
    )
}
