package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.kairosapplication.R
import com.example.kairosapplication.core.ui.constants.GlassConstants

/**
 * Full-screen atmosphere background for Glass UI.
 *
 * Uses the Monet Water Lilies painting (purple-blue-green tones) with
 * contrast-compression overlays:
 * - Black 15%: darkens bright areas (sky, water highlights)
 * - White 5%: lightens dark areas (water shadows, lily pads)
 *
 * This creates a calm, mid-tone environment where all UI elements "float"
 * without competing with the background.
 *
 * Coexists with [com.example.kairosapplication.KairosAtmosphereBackground]
 * — no existing code is modified.
 */
@Composable
fun GlassAtmosphereBackground(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.glass_waterlily_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        // Darken bright areas (sky highlights, water reflections)
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = GlassConstants.BackgroundDarkenAlpha))
        )
        // Lighten dark areas (water shadows, lily pad darks)
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = GlassConstants.BackgroundLightenAlpha))
        )
    }
}
