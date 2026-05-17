package com.example.kairosapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.kairosapplication.R
import com.example.kairosapplication.core.ui.constants.GlassConstants

/** Sharp wallpaper only — used as [dev.chrisbanes.haze] source so bubbles stay bright on any photo. */
@Composable
fun KairosAtmosphereWallpaper(
    modifier: Modifier = Modifier,
    wallpaperUri: String? = null,
) {
    val imageModifier = modifier
        .fillMaxSize()
        .blur(GlassConstants.AtmosphereImageBlurRadius)
    if (!wallpaperUri.isNullOrBlank()) {
        AsyncImage(
            model = wallpaperUri,
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop,
        )
    } else {
        Image(
            painter = painterResource(R.drawable.kairos_atmosphere_bg),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop,
        )
    }
}

/** Full-screen dim veil (chrome readability); keep outside haze source when using route C. */
@Composable
fun KairosAtmosphereDimOverlay(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = GlassConstants.AtmosphereDimAlpha)),
    )
}

/**
 * Full-screen atmosphere: sharp photo + light dim only.
 * Readability for chrome / tasks comes from adaptive text and glass bubbles, not heavy veils.
 */
@Composable
fun KairosAtmosphereBackground(
    modifier: Modifier = Modifier,
    wallpaperUri: String? = null,
) {
    Box(modifier = modifier) {
        KairosAtmosphereWallpaper(Modifier.fillMaxSize(), wallpaperUri = wallpaperUri)
        KairosAtmosphereDimOverlay(Modifier.fillMaxSize())
    }
}
