package com.example.kairosapplication.ui.mine.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.i18n.LocalizedStrings

@Composable
fun ThemeSettingsScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiTheme by viewModel.uiTheme.collectAsState()
    val wallpaperUri by viewModel.atmosphereWallpaperUri.collectAsState()

    SettingsL2Scaffold(
        title = LocalizedStrings.get("theme_settings"),
        onBack = onBack,
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(8.dp))
            SettingsGroupCard(title = LocalizedStrings.get("ui_theme")) {
                listOf(AppUiTheme.STORAGE_GLASS, AppUiTheme.STORAGE_CLASSIC).forEach { key ->
                    val label = when (key) {
                        AppUiTheme.STORAGE_CLASSIC -> LocalizedStrings.get("ui_theme_classic")
                        else -> LocalizedStrings.get("ui_theme_glass")
                    }
                    SettingsThemeRadioRow(
                        label = label,
                        selected = uiTheme == key,
                        onClick = { viewModel.setUiTheme(key) },
                    )
                }
            }
            if (uiTheme == AppUiTheme.STORAGE_GLASS) {
                Spacer(Modifier.height(16.dp))
                AtmosphereBackgroundPickerCard(
                    wallpaperUri = wallpaperUri,
                    onWallpaperPicked = { viewModel.setAtmosphereWallpaperUri(it) },
                    onRestoreDefault = { viewModel.setAtmosphereWallpaperUri(null) },
                )
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}
