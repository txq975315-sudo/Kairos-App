package com.example.kairosapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.glass.GlassPickerDialog
import com.example.kairosapplication.ui.glass.LocalGlassAtmosphereUi
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import com.example.kairosapplication.ui.glass.glassChromeTextStyle

/**
 * Pick local clock time `HH:mm` for a task reminder, or clear.
 * Classic = Material [AlertDialog]; Glass = compact [GlassPickerDialog].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskReminderTimeDialog(
    initialTime: String?,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    onClear: () -> Unit,
) {
    when (LocalAppUiTheme.current) {
        AppUiTheme.Glass -> GlassTaskReminderTimeDialog(
            initialTime = initialTime,
            onDismiss = onDismiss,
            onConfirm = onConfirm,
            onClear = onClear,
        )
        AppUiTheme.Classic -> ClassicTaskReminderTimeDialog(
            initialTime = initialTime,
            onDismiss = onDismiss,
            onConfirm = onConfirm,
            onClear = onClear,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClassicTaskReminderTimeDialog(
    initialTime: String?,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    onClear: () -> Unit,
) {
    val ctx = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val parts = (initialTime ?: "09:00").split(':')
    val h = parts.getOrNull(0)?.toIntOrNull()?.coerceIn(0, 23) ?: 9
    val m = parts.getOrNull(1)?.toIntOrNull()?.coerceIn(0, 59) ?: 0
    val timeState = rememberTimePickerState(initialHour = h, initialMinute = m, is24Hour = true)
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(LocalizedStrings.stringFor(lang, "task_reminder_dialog_title", ctx))
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val hh = timeState.hour.toString().padStart(2, '0')
                    val mm = timeState.minute.toString().padStart(2, '0')
                    onConfirm("$hh:$mm")
                    onDismiss()
                },
            ) { Text(LocalizedStrings.get("confirm")) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(LocalizedStrings.get("cancel"))
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                TimePicker(
                    state = timeState,
                    layoutType = TimePickerLayoutType.Vertical,
                )
                Spacer(Modifier.height(8.dp))
                TextButton(
                    onClick = {
                        onClear()
                        onDismiss()
                    },
                    modifier = Modifier.align(Alignment.End),
                ) {
                    Text(LocalizedStrings.stringFor(lang, "task_reminder_clear", ctx))
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GlassTaskReminderTimeDialog(
    initialTime: String?,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    onClear: () -> Unit,
) {
    val ctx = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val parts = (initialTime ?: "09:00").split(':')
    val h = parts.getOrNull(0)?.toIntOrNull()?.coerceIn(0, 23) ?: 9
    val m = parts.getOrNull(1)?.toIntOrNull()?.coerceIn(0, 59) ?: 0
    val timeState = rememberTimePickerState(initialHour = h, initialMinute = m, is24Hour = true)
    val atmosphere = LocalGlassAtmosphereUi.current
    val chrome = atmosphere.topChrome
    val cardText = atmosphere.cardText
    val useLightChrome = !atmosphere.zones.topIsLight

    CompositionLocalProvider(
        LocalAppUiTheme provides AppUiTheme.Glass,
        LocalGlassAtmosphereUi provides atmosphere,
        LocalGlassTextColors provides cardText,
    ) {
        GlassPickerDialog(
            title = LocalizedStrings.stringFor(lang, "task_reminder_dialog_title", ctx),
            onDismiss = onDismiss,
            chromePrimary = chrome.primary,
            useLightChrome = useLightChrome,
        ) {
            TimePicker(
                state = timeState,
                layoutType = TimePickerLayoutType.Horizontal,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 180.dp),
                colors = TimePickerDefaults.colors(
                    clockDialColor = Color.White.copy(alpha = 0.12f),
                    selectorColor = chrome.primary,
                    containerColor = Color.Transparent,
                    periodSelectorBorderColor = cardText.muted,
                    clockDialSelectedContentColor = cardText.primary,
                    clockDialUnselectedContentColor = cardText.muted,
                    periodSelectorSelectedContainerColor = Color.White.copy(alpha = 0.2f),
                    periodSelectorUnselectedContainerColor = Color.Transparent,
                    periodSelectorSelectedContentColor = cardText.primary,
                    periodSelectorUnselectedContentColor = cardText.muted,
                    timeSelectorSelectedContainerColor = Color.White.copy(alpha = 0.2f),
                    timeSelectorUnselectedContainerColor = Color.Transparent,
                    timeSelectorSelectedContentColor = cardText.primary,
                    timeSelectorUnselectedContentColor = cardText.muted,
                ),
            )
            Spacer(Modifier.height(4.dp))
            TextButton(
                onClick = {
                    onClear()
                    onDismiss()
                },
                modifier = Modifier.align(Alignment.End),
            ) {
                Text(
                    LocalizedStrings.stringFor(lang, "task_reminder_clear", ctx),
                    color = chrome.secondary,
                    style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                TextButton(onClick = onDismiss) {
                    Text(
                        LocalizedStrings.get("cancel"),
                        color = chrome.secondary,
                        style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                    )
                }
                TextButton(
                    onClick = {
                        val hh = timeState.hour.toString().padStart(2, '0')
                        val mm = timeState.minute.toString().padStart(2, '0')
                        onConfirm("$hh:$mm")
                        onDismiss()
                    },
                ) {
                    Text(
                        LocalizedStrings.get("confirm"),
                        color = chrome.primary,
                        fontWeight = FontWeight.SemiBold,
                        style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                    )
                }
            }
        }
    }
}
