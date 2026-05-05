package com.example.kairosapplication.ui.mine.settings

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.notification.NotificationHelper

private fun parseTime(time: String): Pair<Int, Int> {
    val p = time.split(':')
    val h = p.getOrNull(0)?.toIntOrNull()?.coerceIn(0, 23) ?: 21
    val m = p.getOrNull(1)?.toIntOrNull()?.coerceIn(0, 59) ?: 0
    return h to m
}

@Composable
fun MoodSettingsScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel,
    notificationHelper: NotificationHelper,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val dailyReflection by viewModel.dailyReflection.collectAsState()
    val reflectionTime by viewModel.dailyReflectionTime.collectAsState()
    var showTime by remember { mutableStateOf(false) }
    var pendingReflectionEnable by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (pendingReflectionEnable) {
            if (granted) viewModel.setDailyReflection(true)
            pendingReflectionEnable = false
        }
    }

    fun canPost(): Boolean =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED

    LaunchedEffect(dailyReflection, reflectionTime) {
        if (dailyReflection) {
            notificationHelper.createNotificationChannels()
            val (h, m) = parseTime(reflectionTime)
            notificationHelper.scheduleDailyReflection(h, m)
        } else {
            notificationHelper.cancelDailyReflection()
        }
    }

    SettingsL2Scaffold(
        title = LocalizedStrings.get("mood_settings"),
        onBack = onBack,
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(8.dp))
            SettingsGroupCard(title = "") {
                SettingsSwitchRow(
                    label = LocalizedStrings.get("daily_reflection"),
                    checked = dailyReflection,
                    onCheckedChange = { next ->
                        if (next) {
                            if (canPost()) {
                                viewModel.setDailyReflection(true)
                            } else {
                                pendingReflectionEnable = true
                                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                            }
                        } else {
                            viewModel.setDailyReflection(false)
                        }
                    }
                )
                SettingsGroupDivider()
                SettingsNavRow(
                    label = LocalizedStrings.get("reflection_time"),
                    value = reflectionTime,
                    onClick = { showTime = true }
                )
            }
            Spacer(Modifier.height(32.dp))
        }
    }

    if (showTime) {
        SettingsTimePickerDialog(
            initialTime = reflectionTime,
            onDismiss = { showTime = false },
            onConfirm = { viewModel.setDailyReflectionTime(it) }
        )
    }
}
