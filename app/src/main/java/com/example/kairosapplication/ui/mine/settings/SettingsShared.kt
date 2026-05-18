package com.example.kairosapplication.ui.mine.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.glass.GlassTopBarChip
import com.example.kairosapplication.ui.glass.GlassSurface
import com.example.kairosapplication.ui.glass.LocalGlassAtmosphereUi
import com.example.kairosapplication.ui.glass.glassChromeTextStyle
import com.example.kairosapplication.ui.glass.quoteDividerColor

val SettingsCardBg = AppColors.SurfaceWhite
val SettingsDividerC = Color(0xFFE8E5E0)
val SettingsTitleC = Color(0xFF1A1A1A)
val SettingsSubC = Color(0xFF9E9E9E)
val SettingsSwitchBlue = Color(0xFF2196F3)
val SettingsSwitchThumbOff = Color(0xFFE0E0E0)

data class SettingsChrome(
    val pageBackground: Color,
    val title: Color,
    val subtitle: Color,
    val divider: Color,
    val useLightChrome: Boolean,
)

@Composable
fun rememberSettingsChrome(): SettingsChrome {
    val theme = LocalAppUiTheme.current
    if (theme == AppUiTheme.Classic) {
        return SettingsChrome(
            pageBackground = AppColors.ScreenBackground,
            title = SettingsTitleC,
            subtitle = SettingsSubC,
            divider = SettingsDividerC,
            useLightChrome = false,
        )
    }
    val atmosphere = LocalGlassAtmosphereUi.current
    return SettingsChrome(
        pageBackground = Color.Transparent,
        title = atmosphere.topChrome.primary,
        subtitle = atmosphere.topChrome.muted,
        divider = atmosphere.quoteDividerColor(),
        useLightChrome = !atmosphere.zones.topIsLight,
    )
}

@Composable
fun SettingsBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    val chrome = rememberSettingsChrome()
    val cd = contentDescription ?: LocalizedStrings.get("back")
    when (LocalAppUiTheme.current) {
        AppUiTheme.Glass -> {
            GlassTopBarChip(
                onClick = onClick,
                modifier = modifier.size(40.dp),
                shape = CircleShape,
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = cd,
                    tint = chrome.title,
                    modifier = Modifier.size(20.dp),
                )
            }
        }
        AppUiTheme.Classic -> {
            Box(
                modifier = modifier
                    .size(40.dp)
                    .shadow(1.dp, CircleShape)
                    .clip(CircleShape)
                    .background(AppColors.SurfaceWhite)
                    .clickable(onClick = onClick),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = cd,
                    tint = AppColors.BackIcon,
                    modifier = Modifier.size(20.dp),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsL2Scaffold(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    val chrome = rememberSettingsChrome()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(chrome.pageBackground),
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    modifier = Modifier.statusBarsPadding(),
                    title = {
                        Text(
                            title,
                            color = chrome.title,
                            fontWeight = FontWeight.SemiBold,
                            style = glassChromeTextStyle(
                                androidx.compose.ui.text.TextStyle.Default,
                                chrome.useLightChrome,
                            ),
                        )
                    },
                    navigationIcon = {
                        SettingsBackButton(onClick = onBack)
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = chrome.pageBackground,
                    ),
                )
            },
            content = content,
        )
    }
}

@Composable
fun SettingsGroupCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val chrome = rememberSettingsChrome()
    val shape = RoundedCornerShape(AppShapes.DenseInsetRadius)
    val inner: @Composable () -> Unit = {
        Column(Modifier.padding(16.dp)) {
            if (title.isNotBlank()) {
                Text(
                    title,
                    color = chrome.subtitle,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    style = glassChromeTextStyle(
                        androidx.compose.ui.text.TextStyle.Default,
                        chrome.useLightChrome,
                    ),
                )
                Spacer(Modifier.height(8.dp))
                HorizontalDivider(thickness = 0.5.dp, color = chrome.divider)
                Spacer(Modifier.height(4.dp))
            }
            content()
        }
    }
    when (LocalAppUiTheme.current) {
        AppUiTheme.Glass -> {
            GlassSurface(
                modifier = modifier.fillMaxWidth(),
                shape = shape,
                fillAlpha = GlassConstants.TaskCardFillAlpha,
                wrapHazeToContent = false,
                content = { inner() },
            )
        }
        AppUiTheme.Classic -> {
            Surface(
                modifier = modifier.fillMaxWidth(),
                shape = shape,
                color = SettingsCardBg,
                shadowElevation = 2.dp,
                content = { inner() },
            )
        }
    }
}

@Composable
fun SettingsThemeRadioRow(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val chrome = rememberSettingsChrome()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 6.dp),
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = if (LocalAppUiTheme.current == AppUiTheme.Glass) {
                    Color.White
                } else {
                    SettingsSwitchBlue
                },
                unselectedColor = chrome.subtitle,
            ),
        )
        Text(
            label,
            fontSize = 15.sp,
            color = chrome.title,
            style = glassChromeTextStyle(
                androidx.compose.ui.text.TextStyle.Default,
                chrome.useLightChrome,
            ),
        )
    }
}

@Composable
fun SettingsGroupDivider() {
    val chrome = rememberSettingsChrome()
    HorizontalDivider(thickness = 0.5.dp, color = chrome.divider)
}

@Composable
fun SettingsSwitchRow(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val chrome = rememberSettingsChrome()
        Text(label, color = chrome.title, fontSize = 15.sp)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = SettingsSwitchBlue,
                checkedTrackColor = SettingsSwitchBlue.copy(alpha = 0.5f),
                uncheckedThumbColor = SettingsSwitchThumbOff,
                uncheckedTrackColor = SettingsSwitchThumbOff.copy(alpha = 0.35f)
            )
        )
    }
}

@Composable
fun SettingsSwitchRow(
    icon: ImageVector,
    title: String,
    subtitle: String?,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val chrome = rememberSettingsChrome()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = chrome.title,
                modifier = Modifier.padding(end = 16.dp)
            )
            Column {
                Text(title, color = chrome.title, fontSize = 15.sp)
                if (subtitle != null) {
                    Text(subtitle, color = chrome.subtitle, fontSize = 12.sp)
                }
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = SettingsSwitchBlue,
                checkedTrackColor = SettingsSwitchBlue.copy(alpha = 0.5f),
                uncheckedThumbColor = SettingsSwitchThumbOff,
                uncheckedTrackColor = SettingsSwitchThumbOff.copy(alpha = 0.35f)
            )
        )
    }
}

@Composable
fun SettingsNavRow(
    label: String,
    value: String,
    onClick: () -> Unit
) {
    val chrome = rememberSettingsChrome()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            label,
            color = chrome.title,
            fontSize = 15.sp,
            style = glassChromeTextStyle(
                androidx.compose.ui.text.TextStyle.Default,
                chrome.useLightChrome,
            ),
        )
        if (value.isNotEmpty()) {
            Text(
                value,
                color = chrome.subtitle,
                fontSize = 15.sp,
                style = glassChromeTextStyle(
                    androidx.compose.ui.text.TextStyle.Default,
                    chrome.useLightChrome,
                ),
            )
        } else {
            Text(
                "›",
                color = chrome.subtitle,
                fontSize = 18.sp,
                style = glassChromeTextStyle(
                    androidx.compose.ui.text.TextStyle.Default,
                    chrome.useLightChrome,
                ),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTimePickerDialog(
    initialTime: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    val parts = initialTime.split(':')
    val h = parts.getOrNull(0)?.toIntOrNull()?.coerceIn(0, 23) ?: 0
    val m = parts.getOrNull(1)?.toIntOrNull()?.coerceIn(0, 59) ?: 0
    val timeState = rememberTimePickerState(initialHour = h, initialMinute = m, is24Hour = true)
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val hh = timeState.hour.toString().padStart(2, '0')
                val mm = timeState.minute.toString().padStart(2, '0')
                onConfirm("$hh:$mm")
                onDismiss()
            }) { Text(LocalizedStrings.get("confirm")) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(LocalizedStrings.get("cancel")) }
        },
        text = {
            TimePicker(state = timeState, layoutType = TimePickerLayoutType.Vertical)
        }
    )
}
