package com.example.kairosapplication.ui.mine.settings

import android.Manifest
import android.app.AlarmManager
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.notification.NotificationHelper

private enum class TimeTarget { None, Reminder, Reflection }

private fun parseTime(time: String): Pair<Int, Int> {
    val p = time.split(':')
    val h = p.getOrNull(0)?.toIntOrNull()?.coerceIn(0, 23) ?: 9
    val m = p.getOrNull(1)?.toIntOrNull()?.coerceIn(0, 59) ?: 0
    return h to m
}

@Composable
fun NotificationSettingsScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel,
    notificationHelper: NotificationHelper,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val dailyReminder by viewModel.dailyReminder.collectAsState()
    val reminderTime by viewModel.dailyReminderTime.collectAsState()
    val dailyReflection by viewModel.dailyReflection.collectAsState()
    val reflectionTime by viewModel.dailyReflectionTime.collectAsState()
    var timeTarget by remember { mutableStateOf(TimeTarget.None) }
    var permissionTarget by remember { mutableIntStateOf(0) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        when (permissionTarget) {
            1 -> if (granted) viewModel.setDailyReminder(true)
            2 -> if (granted) viewModel.setDailyReflection(true)
        }
        permissionTarget = 0
    }

    fun canPost(): Boolean =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED

    fun canScheduleExactAlarms(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return true
        val am = context.getSystemService(AlarmManager::class.java) ?: return true
        return am.canScheduleExactAlarms()
    }

    fun requestExactAlarmPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return
        context.startActivity(
            Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                data = Uri.parse("package:${context.packageName}")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            },
        )
    }

    LaunchedEffect(Unit) {
        notificationHelper.createNotificationChannels()
        if (dailyReminder) {
            val (h, m) = parseTime(reminderTime)
            notificationHelper.scheduleDailyReminder(h, m)
        }
        if (dailyReflection) {
            val (h, m) = parseTime(reflectionTime)
            notificationHelper.scheduleDailyReflection(h, m)
        }
    }

    LaunchedEffect(dailyReminder, reminderTime) {
        if (dailyReminder) {
            notificationHelper.createNotificationChannels()
            val (h, m) = parseTime(reminderTime)
            notificationHelper.scheduleDailyReminder(h, m)
        } else {
            notificationHelper.cancelDailyReminder()
        }
    }

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
        title = LocalizedStrings.get("notification_settings"),
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
                    label = LocalizedStrings.get("daily_reminder"),
                    checked = dailyReminder,
                    onCheckedChange = { next ->
                        if (next) {
                            if (canPost()) {
                                if (!canScheduleExactAlarms()) {
                                    requestExactAlarmPermission()
                                }
                                viewModel.setDailyReminder(true)
                            } else {
                                permissionTarget = 1
                                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                            }
                        } else {
                            viewModel.setDailyReminder(false)
                        }
                    }
                )
                SettingsGroupDivider()
                SettingsNavRow(
                    label = LocalizedStrings.get("daily_reminder_time"),
                    value = reminderTime,
                    onClick = { timeTarget = TimeTarget.Reminder }
                )
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = "") {
                SettingsSwitchRow(
                    label = LocalizedStrings.get("daily_reflection"),
                    checked = dailyReflection,
                    onCheckedChange = { next ->
                        if (next) {
                            if (canPost()) {
                                if (!canScheduleExactAlarms()) {
                                    requestExactAlarmPermission()
                                }
                                viewModel.setDailyReflection(true)
                            } else {
                                permissionTarget = 2
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
                    onClick = { timeTarget = TimeTarget.Reflection }
                )
            }
            Spacer(Modifier.height(32.dp))
        }
    }

    when (timeTarget) {
        TimeTarget.Reminder -> SettingsTimePickerDialog(
            initialTime = reminderTime,
            onDismiss = { timeTarget = TimeTarget.None },
            onConfirm = { viewModel.setDailyReminderTime(it) }
        )
        TimeTarget.Reflection -> SettingsTimePickerDialog(
            initialTime = reflectionTime,
            onDismiss = { timeTarget = TimeTarget.None },
            onConfirm = { viewModel.setDailyReflectionTime(it) }
        )
        TimeTarget.None -> Unit
    }
}
