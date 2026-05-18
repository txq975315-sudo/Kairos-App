package com.example.kairosapplication.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.ui.glass.GlassSurface
import com.example.kairosapplication.ui.glass.GlassTextColors
import com.example.kairosapplication.ui.glass.LocalGlassAtmosphereUi
import com.example.kairosapplication.ui.glass.LocalGlassTextColors

/**
 * Widget preview card shell: Classic = opaque white + shadow; Glass = [GlassSurface] frost.
 * Provides adaptive [LocalGlassTextColors] for preview content.
 */
@Composable
fun WidgetPreviewCardShell(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(AppShapes.FeaturePanelRadius),
    wrapHazeToContent: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    val cardText = when (LocalAppUiTheme.current) {
        AppUiTheme.Glass -> LocalGlassAtmosphereUi.current.cardText
        AppUiTheme.Classic -> GlassTextColors.onLightBackground()
    }
    CompositionLocalProvider(LocalGlassTextColors provides cardText) {
        when (LocalAppUiTheme.current) {
            AppUiTheme.Glass -> {
                GlassSurface(
                    modifier = modifier,
                    shape = shape,
                    fillAlpha = GlassConstants.TaskCardFillAlpha,
                    wrapHazeToContent = wrapHazeToContent,
                    content = content,
                )
            }
            AppUiTheme.Classic -> {
                Box(
                    modifier = modifier
                        .shadow(6.dp, shape)
                        .clip(shape)
                        .background(AppColors.SurfaceWhite, shape),
                    content = content,
                )
            }
        }
    }
}
