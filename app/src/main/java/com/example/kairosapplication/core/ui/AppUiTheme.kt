package com.example.kairosapplication.core.ui

import androidx.compose.runtime.compositionLocalOf

/** App-wide visual shell: frosted glass atmosphere vs classic solid surfaces. */
enum class AppUiTheme {
    Glass,
    Classic,
    ;

    fun toStorageKey(): String = when (this) {
        Glass -> STORAGE_GLASS
        Classic -> STORAGE_CLASSIC
    }

    companion object {
        const val STORAGE_GLASS = "glass"
        const val STORAGE_CLASSIC = "classic"

        fun fromStorageKey(raw: String?): AppUiTheme =
            if (raw == STORAGE_CLASSIC) Classic else Glass
    }
}

val LocalAppUiTheme = compositionLocalOf { AppUiTheme.Glass }
