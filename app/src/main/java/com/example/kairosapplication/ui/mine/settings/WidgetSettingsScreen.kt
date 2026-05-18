package com.example.kairosapplication.ui.mine.settings

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.widget.WidgetAlarmScheduler
import com.example.kairosapplication.widget.WidgetManager
import com.example.kairosapplication.widget.data.WidgetConfigRepository
import com.example.kairosapplication.widget.data.WidgetRefreshStrategy
import com.example.kairosapplication.widget.data.WidgetSize
import com.example.kairosapplication.ui.widget.settings.WidgetBackgroundActivity
import kotlinx.coroutines.launch

private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

private fun refreshStrategyKey(strategy: WidgetRefreshStrategy): String = when (strategy) {
    WidgetRefreshStrategy.ON_APP_OPEN -> "widget_refresh_on_app_open"
    WidgetRefreshStrategy.HOURLY -> "widget_refresh_hourly"
    WidgetRefreshStrategy.DAILY -> "widget_refresh_daily"
}

/**
 * Global widget preferences: background (incl. transparency via [WidgetBackgroundActivity]) and refresh cadence.
 * Per-size layout/display options are fixed to defaults in storage; background editor applies to all sizes.
 */
@Composable
fun WidgetSettingsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val repo = remember(context) {
        WidgetConfigRepository(DataStoreManager(context.applicationContext))
    }
    var refreshStrategy by remember { mutableStateOf(WidgetRefreshStrategy.ON_APP_OPEN) }
    var showRefreshPicker by remember { mutableStateOf(false) }
    val chrome = rememberSettingsChrome()

    LaunchedEffect(Unit) {
        refreshStrategy = repo.getConfig(WidgetSize._1X1).refreshStrategy
    }

    fun persistRefreshToAllSizes(strategy: WidgetRefreshStrategy) {
        scope.launch {
            WidgetSize.entries.forEach { size ->
                val c = repo.getConfig(size)
                repo.saveConfig(size, c.copy(refreshStrategy = strategy))
            }
            refreshStrategy = strategy
            WidgetAlarmScheduler.rescheduleAll(context.applicationContext)
            WidgetManager.refreshWidgets(context.applicationContext)
        }
    }

    SettingsL2Scaffold(
        title = LocalizedStrings.get("widget_settings"),
        onBack = onBack,
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = "") {
                SettingsNavRow(
                    label = LocalizedStrings.get("widget_background_style"),
                    value = "",
                    onClick = {
                        val act = context.findActivity()
                        if (act != null) {
                            act.startActivity(
                                WidgetBackgroundActivity.createIntent(act, WidgetSize._1X1)
                            )
                        }
                    }
                )
                SettingsGroupDivider()
                SettingsNavRow(
                    label = LocalizedStrings.get("widget_refresh_time"),
                    value = LocalizedStrings.get(refreshStrategyKey(refreshStrategy)),
                    onClick = { showRefreshPicker = true },
                )
            }
            Spacer(Modifier.height(24.dp))
        }
    }

    if (showRefreshPicker) {
        AlertDialog(
            onDismissRequest = { showRefreshPicker = false },
            title = { Text(LocalizedStrings.get("widget_refresh_time"), color = chrome.title) },
            text = {
                Column {
                    WidgetRefreshStrategy.entries.forEach { strategy ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    persistRefreshToAllSizes(strategy)
                                    showRefreshPicker = false
                                }
                                .padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = refreshStrategy == strategy,
                                onClick = {
                                    persistRefreshToAllSizes(strategy)
                                    showRefreshPicker = false
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = chrome.title,
                                    unselectedColor = chrome.subtitle
                                )
                            )
                            Text(
                                LocalizedStrings.get(refreshStrategyKey(strategy)),
                                fontSize = 15.sp,
                                color = chrome.title
                            )
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showRefreshPicker = false }) {
                    Text(LocalizedStrings.get("close"))
                }
            }
        )
    }
}
