package com.example.kairosapplication.ui.mine.settings

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Restore
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kairosapplication.R
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.i18n.LocalizedStrings

private val ActionBlue = Color(0xFF2196F3)
private val RestoreRed = Color(0xFFE53935)

@Composable
fun AtmosphereBackgroundSettingsScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val wallpaperUri by viewModel.atmosphereWallpaperUri.collectAsState()

    val pickWallpaper = rememberLauncherForActivityResult(
        contract = PickVisualMedia(),
    ) { uri ->
        uri?.let { viewModel.setAtmosphereWallpaperUri(it.toString()) }
    }

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
            Text(
                text = LocalizedStrings.get("atmosphere_background_section"),
                color = SettingsSubC,
                fontSize = 13.sp,
                modifier = Modifier.padding(start = 4.dp, bottom = 8.dp),
            )
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(AppShapes.DenseInsetRadius),
                color = SettingsCardBg,
                shadowElevation = 2.dp,
            ) {
                Column(Modifier.padding(16.dp)) {
                    val previewShape = RoundedCornerShape(12.dp)
                    if (!wallpaperUri.isNullOrBlank()) {
                        AsyncImage(
                            model = wallpaperUri,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .clip(previewShape),
                            contentScale = ContentScale.Crop,
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.kairos_atmosphere_bg),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .clip(previewShape),
                            contentScale = ContentScale.Crop,
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    AtmosphereBackgroundActionRow(
                        icon = Icons.Outlined.Image,
                        label = LocalizedStrings.get("change_atmosphere_background"),
                        labelColor = SettingsTitleC,
                        iconTint = ActionBlue,
                        onClick = {
                            pickWallpaper.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
                        },
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 40.dp, top = 4.dp, bottom = 4.dp),
                        thickness = 0.5.dp,
                        color = SettingsDividerC,
                    )
                    AtmosphereBackgroundActionRow(
                        icon = Icons.Outlined.Restore,
                        label = LocalizedStrings.get("restore_default_atmosphere_background"),
                        labelColor = RestoreRed,
                        iconTint = ActionBlue,
                        onClick = { viewModel.setAtmosphereWallpaperUri(null) },
                    )
                }
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun AtmosphereBackgroundActionRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    labelColor: Color,
    iconTint: Color,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(22.dp),
        )
        Text(
            text = label,
            color = labelColor,
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 12.dp),
        )
    }
}
