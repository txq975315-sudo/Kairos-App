package com.example.kairosapplication.ui.mine.settings

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Restore
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kairosapplication.R
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.glass.AtmosphereWallpaperAccess

private val ActionBlue = Color(0xFF2196F3)
private val RestoreRed = Color(0xFFE53935)

@Composable
fun AtmosphereBackgroundPickerCard(
    wallpaperUri: String?,
    onWallpaperPicked: (String) -> Unit,
    onRestoreDefault: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val chrome = rememberSettingsChrome()
    val pickWallpaper = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
    ) { uri ->
        if (uri != null) {
            AtmosphereWallpaperAccess.persistReadPermission(context, uri)
            onWallpaperPicked(uri.toString())
        }
    }

    SettingsGroupCard(
        title = LocalizedStrings.get("atmosphere_background_section"),
        modifier = modifier,
    ) {
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
            labelColor = chrome.title,
            iconTint = ActionBlue,
            onClick = {
                pickWallpaper.launch(arrayOf("image/*"))
            },
        )
        HorizontalDivider(
            modifier = Modifier.padding(start = 40.dp, top = 4.dp, bottom = 4.dp),
            thickness = 0.5.dp,
            color = chrome.divider,
        )
        AtmosphereBackgroundActionRow(
            icon = Icons.Outlined.Restore,
            label = LocalizedStrings.get("restore_default_atmosphere_background"),
            labelColor = RestoreRed,
            iconTint = ActionBlue,
            onClick = onRestoreDefault,
        )
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
        Spacer(Modifier.size(12.dp))
        Text(label, color = labelColor, fontSize = 15.sp)
    }
}
