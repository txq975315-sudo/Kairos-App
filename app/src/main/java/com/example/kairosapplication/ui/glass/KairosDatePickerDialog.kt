package com.example.kairosapplication.ui.glass

import android.app.DatePickerDialog
import androidx.annotation.StyleRes
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.R
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.i18n.LocalizedStrings
import java.time.LocalDate
import java.time.ZoneId

/**
 * Task date picker: Glass = [GlassPickerDialog] + custom month grid; Classic = system dialog.
 */
@Composable
fun KairosDatePickerDialog(
    onDismissRequest: () -> Unit,
    onDateConfirmed: (LocalDate) -> Unit,
    initialDate: LocalDate,
    zone: ZoneId = ZoneId.systemDefault(),
) {
    when (LocalAppUiTheme.current) {
        AppUiTheme.Glass -> GlassPickerDateDialog(
            onDismissRequest = onDismissRequest,
            onDateConfirmed = onDateConfirmed,
            initialDate = initialDate,
        )
        AppUiTheme.Classic -> ThemedSystemDatePickerDialog(
            themeRes = R.style.Theme_Kairos_DatePicker_Classic,
            onDismissRequest = onDismissRequest,
            onDateConfirmed = onDateConfirmed,
            initialDate = initialDate,
        )
    }
}

@Composable
private fun GlassPickerDateDialog(
    onDismissRequest: () -> Unit,
    onDateConfirmed: (LocalDate) -> Unit,
    initialDate: LocalDate,
) {
    val atmosphere = LocalGlassAtmosphereUi.current
    val chrome = atmosphere.topChrome
    val useLightChrome = !atmosphere.zones.topIsLight
    var selectedDate by remember(initialDate) { mutableStateOf(initialDate) }

    CompositionLocalProvider(
        LocalAppUiTheme provides AppUiTheme.Glass,
        LocalGlassAtmosphereUi provides atmosphere,
        LocalGlassTextColors provides atmosphere.cardText,
    ) {
        GlassPickerDialog(
            title = stringResource(R.string.task_sheet_pick_task_date),
            onDismiss = onDismissRequest,
            chromePrimary = chrome.primary,
            useLightChrome = useLightChrome,
        ) {
            GlassMonthPickerGrid(
                selectedDate = selectedDate,
                onSelectedDateChange = { selectedDate = it },
                modifier = Modifier.padding(bottom = 4.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                TextButton(onClick = onDismissRequest) {
                    Text(
                        text = LocalizedStrings.get("cancel"),
                        color = chrome.secondary,
                        style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                    )
                }
                TextButton(
                    onClick = {
                        onDateConfirmed(selectedDate)
                        onDismissRequest()
                    },
                ) {
                    Text(
                        text = LocalizedStrings.get("confirm"),
                        color = chrome.primary,
                        fontWeight = FontWeight.SemiBold,
                        style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                    )
                }
            }
        }
    }
}

@Composable
private fun ThemedSystemDatePickerDialog(
    @StyleRes themeRes: Int,
    onDismissRequest: () -> Unit,
    onDateConfirmed: (LocalDate) -> Unit,
    initialDate: LocalDate,
) {
    val context = LocalContext.current
    val title = stringResource(R.string.task_sheet_pick_task_date)
    val onDismissState = rememberUpdatedState(onDismissRequest)
    val onConfirmState = rememberUpdatedState(onDateConfirmed)

    DisposableEffect(themeRes, initialDate) {
        val themedContext = ContextThemeWrapper(context, themeRes)
        var confirmed = false
        val dialog = DatePickerDialog(
            themedContext,
            { _, year, month, dayOfMonth ->
                confirmed = true
                onConfirmState.value(LocalDate.of(year, month + 1, dayOfMonth))
            },
            initialDate.year,
            initialDate.monthValue - 1,
            initialDate.dayOfMonth,
        )
        dialog.setTitle(title)
        dialog.setOnCancelListener { onDismissState.value() }
        dialog.setOnDismissListener {
            if (!confirmed) {
                onDismissState.value()
            }
        }
        dialog.show()
        onDispose {
            dialog.setOnCancelListener(null)
            dialog.setOnDismissListener(null)
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }
    }
}
