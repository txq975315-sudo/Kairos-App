package com.example.kairosapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.R
import com.example.kairosapplication.core.ui.constants.GlassConstants

/**
 * Unified full-screen atmosphere: green Monet field ([R.drawable.kairos_atmosphere_bg])
 * with background blur, lowered brightness, and sage mist so white/dark chrome both read well.
 */
@Composable
fun KairosAtmosphereBackground(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.kairos_atmosphere_bg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(GlassConstants.AtmosphereImageBlurRadius),
            contentScale = ContentScale.Crop,
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = GlassConstants.AtmosphereDimAlpha)),
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.10f)),
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0f to Color.White.copy(alpha = 0.08f),
                            0.40f to Color(0xFFE6EBE6).copy(alpha = 0.28f),
                            0.72f to Color(0xFFDFE6DF).copy(alpha = 0.36f),
                            1f to Color(0xFFD8E0D8).copy(alpha = 0.42f),
                        ),
                    ),
                ),
        )
    }
}
