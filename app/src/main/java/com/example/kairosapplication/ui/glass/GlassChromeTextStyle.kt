package com.example.kairosapplication.ui.glass

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle

/** Light text on variable wallpaper — subtle dual shadow for legibility without a card. */
fun glassChromeTextStyle(base: TextStyle, useLightText: Boolean): TextStyle {
    if (!useLightText) {
        return base.copy(
            shadow = Shadow(
                color = Color.White.copy(alpha = 0.45f),
                offset = Offset(0f, 1f),
                blurRadius = 2f,
            ),
        )
    }
    return base.copy(
        shadow = Shadow(
            color = Color.Black.copy(alpha = 0.35f),
            offset = Offset(0f, 1f),
            blurRadius = 3f,
        ),
    )
}
