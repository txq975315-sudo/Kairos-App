package com.example.kairosapplication.ui.mine.settings

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.widget.WidgetAlarmScheduler
import com.example.kairosapplication.widget.WidgetManager
import com.example.kairosapplication.widget.data.WidgetConfig
import com.example.kairosapplication.widget.data.WidgetDefaults
import com.example.kairosapplication.widget.data.WidgetLayoutKind
import com.example.kairosapplication.widget.data.WidgetRefreshStrategy
import com.example.kairosapplication.widget.data.WidgetSize
import com.example.kairosapplication.widget.data.WidgetConfigRepository
import com.example.kairosapplication.ui.widget.settings.WidgetBackgroundActivity
import kotlinx.coroutines.launch

private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

private fun layoutKindsFor(size: WidgetSize): List<WidgetLayoutKind> = when (size) {
    WidgetSize._1X1 -> listOf(WidgetLayoutKind._1A, WidgetLayoutKind._1B)
    WidgetSize._2X2 -> listOf(WidgetLayoutKind._2A)
    WidgetSize._3X1 -> listOf(
        WidgetLayoutKind._3A,
        WidgetLayoutKind._3B,
        WidgetLayoutKind._3C,
        WidgetLayoutKind._3D,
    )
    WidgetSize._3X3 -> listOf(WidgetLayoutKind._4A, WidgetLayoutKind._4B)
}

private fun coercedLayout(size: WidgetSize, kind: WidgetLayoutKind): WidgetLayoutKind {
    val allowed = layoutKindsFor(size)
    return if (kind in allowed) kind else allowed.first()
}

private fun layoutLabelKey(kind: WidgetLayoutKind): String {
    val suffix = kind.name.removePrefix("_").lowercase()
    return "widget_layout_$suffix"
}

private fun refreshStrategyKey(strategy: WidgetRefreshStrategy): String = when (strategy) {
    WidgetRefreshStrategy.ON_APP_OPEN -> "widget_refresh_on_app_open"
    WidgetRefreshStrategy.HOURLY -> "widget_refresh_hourly"
    WidgetRefreshStrategy.DAILY -> "widget_refresh_daily"
}

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
    var selectedSize by remember { mutableStateOf(WidgetSize._1X1) }
    var draft by remember { mutableStateOf(WidgetDefaults.defaultConfig(WidgetSize._1X1)) }
    var showRefreshPicker by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val loaded = repo.getConfig(selectedSize)
        draft = loaded.copy(
            size = selectedSize,
            layoutKind = coercedLayout(selectedSize, loaded.layoutKind)
        )
    }

    fun persistAndRefresh(updated: WidgetConfig) {
        val toSave = updated.copy(size = selectedSize)
        draft = toSave
        scope.launch {
            repo.saveConfig(selectedSize, toSave)
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
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(16.dp))
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = SettingsCardBg,
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WidgetSize.entries.forEach { size ->
                        val selected = selectedSize == size
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable {
                                    if (size == selectedSize) return@clickable
                                    scope.launch {
                                        repo.saveConfig(selectedSize, draft.copy(size = selectedSize))
                                        selectedSize = size
                                        draft = repo.getConfig(size).copy(
                                            size = size,
                                            layoutKind = coercedLayout(size, repo.getConfig(size).layoutKind)
                                        )
                                    }
                                }
                                .padding(horizontal = 8.dp)
                        ) {
                            Text(
                                size.displayName,
                                fontSize = 14.sp,
                                color = if (selected) SettingsTitleC else SettingsSubC,
                                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                            )
                            Spacer(Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .width(28.dp)
                                    .height(2.dp)
                                    .background(if (selected) SettingsTitleC else Color.Transparent)
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = LocalizedStrings.get("widget_layout_section")) {
                layoutKindsFor(selectedSize).forEach { kind ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                persistAndRefresh(draft.copy(layoutKind = kind))
                            }
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = draft.layoutKind == kind,
                            onClick = { persistAndRefresh(draft.copy(layoutKind = kind)) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = SettingsTitleC,
                                unselectedColor = SettingsSubC
                            )
                        )
                        Text(
                            LocalizedStrings.get(layoutLabelKey(kind)),
                            fontSize = 14.sp,
                            color = SettingsTitleC
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = LocalizedStrings.get("widget_display_section")) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Checkbox(
                        checked = draft.displayConfig.showTasks,
                        onCheckedChange = { checked ->
                            persistAndRefresh(
                                draft.copy(displayConfig = draft.displayConfig.copy(showTasks = checked))
                            )
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = SettingsSwitchBlue,
                            uncheckedColor = SettingsSubC
                        )
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        LocalizedStrings.get("widget_show_tasks"),
                        fontSize = 12.sp,
                        color = SettingsTitleC
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Checkbox(
                        checked = draft.displayConfig.showDailyQuote,
                        onCheckedChange = { checked ->
                            persistAndRefresh(
                                draft.copy(displayConfig = draft.displayConfig.copy(showDailyQuote = checked))
                            )
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = SettingsSwitchBlue,
                            uncheckedColor = SettingsSubC
                        )
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        LocalizedStrings.get("widget_show_daily_quote"),
                        fontSize = 12.sp,
                        color = SettingsTitleC
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = "") {
                SettingsNavRow(
                    label = LocalizedStrings.get("widget_background_style"),
                    value = "",
                    onClick = {
                        val act = context.findActivity()
                        if (act != null) {
                            act.startActivity(
                                WidgetBackgroundActivity.createIntent(act, selectedSize)
                            )
                        }
                    }
                )
                SettingsGroupDivider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showRefreshPicker = true }
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        LocalizedStrings.get("widget_refresh_time"),
                        color = SettingsTitleC,
                        fontSize = 15.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            LocalizedStrings.get(refreshStrategyKey(draft.refreshStrategy)),
                            color = SettingsSubC,
                            fontSize = 12.sp
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("›", color = SettingsSubC, fontSize = 18.sp)
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            OutlinedButton(
                onClick = {
                    scope.launch {
                        repo.resetToDefault(selectedSize)
                        draft = WidgetDefaults.defaultConfig(selectedSize)
                        WidgetManager.refreshWidgets(context.applicationContext)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, SettingsDividerC)
            ) {
                Text(
                    LocalizedStrings.get("widget_reset_default"),
                    color = SettingsTitleC,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(Modifier.height(32.dp))
        }
    }

    if (showRefreshPicker) {
        AlertDialog(
            onDismissRequest = { showRefreshPicker = false },
            title = { Text(LocalizedStrings.get("widget_refresh_time"), color = SettingsTitleC) },
            text = {
                Column {
                    WidgetRefreshStrategy.entries.forEach { strategy ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    persistAndRefresh(draft.copy(refreshStrategy = strategy))
                                    showRefreshPicker = false
                                }
                                .padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = draft.refreshStrategy == strategy,
                                onClick = {
                                    persistAndRefresh(draft.copy(refreshStrategy = strategy))
                                    showRefreshPicker = false
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = SettingsTitleC,
                                    unselectedColor = SettingsSubC
                                )
                            )
                            Text(
                                LocalizedStrings.get(refreshStrategyKey(strategy)),
                                fontSize = 15.sp,
                                color = SettingsTitleC
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
