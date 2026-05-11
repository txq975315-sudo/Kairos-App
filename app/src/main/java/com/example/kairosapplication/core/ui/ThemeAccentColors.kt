package com.example.kairosapplication.core.ui

import androidx.compose.ui.graphics.Color

/** Maps settings theme keys ([DataStoreManager] / [SettingsViewModel]) to accent colors for emphasis text. */
object ThemeAccentColors {
    fun fromSettingsKey(themeKey: String): Color = when (themeKey) {
        "green" -> Color(0xFF4A7C59)
        "pink" -> Color(0xFFC75F7E)
        "orange" -> Color(0xFFE07A52)
        else -> Color(0xFF7B6FB0)
    }
}
