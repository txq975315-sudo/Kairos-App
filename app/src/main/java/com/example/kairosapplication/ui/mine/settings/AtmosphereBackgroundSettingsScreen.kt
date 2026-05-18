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
import com.example.kairosapplication.i18n.LocalizedStrings

@Composable
fun AtmosphereBackgroundSettingsScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val wallpaperUri by viewModel.atmosphereWallpaperUri.collectAsState()

    SettingsL2Scaffold(
        title = LocalizedStrings.get("atmosphere_background_settings"),
        onBack = onBack,
        modifier = modifier,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(Modifier.height(8.dp))
            AtmosphereBackgroundPickerCard(
                wallpaperUri = wallpaperUri,
                onWallpaperPicked = { viewModel.setAtmosphereWallpaperUri(it) },
                onRestoreDefault = { viewModel.setAtmosphereWallpaperUri(null) },
            )
            Spacer(Modifier.height(32.dp))
        }
    }
}
