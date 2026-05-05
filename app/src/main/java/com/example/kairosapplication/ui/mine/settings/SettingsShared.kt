package com.example.kairosapplication.ui.mine.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.theme.BackgroundColor

val SettingsCardBg = Color.White
val SettingsDividerC = Color(0xFFE8E5E0)
val SettingsTitleC = Color(0xFF1A1A1A)
val SettingsSubC = Color(0xFF9E9E9E)
val SettingsSwitchBlue = Color(0xFF2196F3)
val SettingsSwitchThumbOff = Color(0xFFE0E0E0)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsL2Scaffold(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = { Text(title, color = SettingsTitleC, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = SettingsTitleC)
                    }
                }
            )
        }
    ) { padding ->
        content(padding)
    }
}

@Composable
fun SettingsGroupCard(title: String, content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = SettingsCardBg,
        shadowElevation = 2.dp
    ) {
        Column(Modifier.padding(16.dp)) {
            if (title.isNotBlank()) {
                Text(title, color = SettingsSubC, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(8.dp))
                HorizontalDivider(thickness = 0.5.dp, color = SettingsDividerC)
                Spacer(Modifier.height(4.dp))
            }
            content()
        }
    }
}

@Composable
fun SettingsGroupDivider() {
    HorizontalDivider(thickness = 0.5.dp, color = SettingsDividerC)
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
        Text(label, color = SettingsTitleC, fontSize = 15.sp)
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = SettingsTitleC, fontSize = 15.sp)
        if (value.isNotEmpty()) {
            Text(value, color = SettingsSubC, fontSize = 15.sp)
        } else {
            Text("›", color = SettingsSubC, fontSize = 18.sp)
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
