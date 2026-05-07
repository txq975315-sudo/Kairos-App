package com.example.kairosapplication.core.ui

import androidx.compose.ui.graphics.Color

/** Maps settings theme keys ([DataStoreManager] / [SettingsViewModel]) to accent colors for emphasis text. */
object ThemeAccentColors {
    fun fromSettingsKey(themeKey: String): Color = when (themeKey) {
        "green" -> Color(0xFF43A047)
        "pink" -> Color(0xFFEC407A)
        "orange" -> Color(0xFFFF7043)
        else -> Color(0xFF8A7CF8)
    }
}
