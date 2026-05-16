package com.example.kairosapplication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings

/**
 * Pick local clock time `HH:mm` for a task reminder, or clear.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskReminderTimeDialog(
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
                }
            ) { Text(LocalizedStrings.get("confirm")) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(LocalizedStrings.get("cancel"))
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                TimePicker(state = timeState, layoutType = TimePickerLayoutType.Vertical)
                Spacer(Modifier.height(8.dp))
                TextButton(
                    onClick = {
                        onClear()
                        onDismiss()
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(LocalizedStrings.stringFor(lang, "task_reminder_clear", ctx))
                }
            }
        }
    )
}
