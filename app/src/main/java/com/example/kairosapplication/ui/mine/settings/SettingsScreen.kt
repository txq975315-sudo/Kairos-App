package com.example.kairosapplication.ui.mine.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.theme.BackgroundColor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    localizationManager: LocalizationManager,
    viewModel: SettingsViewModel,
    onNavigateToExport: () -> Unit,
    onNavigateToImport: () -> Unit,
    onNavigateToNotification: () -> Unit,
    onNavigateToTheme: () -> Unit,
    onNavigateToMood: () -> Unit,
    onNavigateToWidget: () -> Unit,
    onNavigateToLanguage: () -> Unit,
    onNavigateToPrivacy: () -> Unit,
    onNavigateToMisc: () -> Unit,
    onOpenEssayTopics: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val uiLanguage = LocalCurrentLanguage.current.value
    val langKey by localizationManager.currentLanguage.collectAsState()
    val lastBackup by viewModel.lastBackupTime.collectAsState()
    val reminderTime by viewModel.dailyReminderTime.collectAsState()
    val reflectionTime by viewModel.dailyReflectionTime.collectAsState()
    val dark by viewModel.darkMode.collectAsState()
    val theme by viewModel.themeColor.collectAsState()
    val languageCode by viewModel.language.collectAsState()

    fun toastSoon() {
        Toast.makeText(context, LocalizedStrings.stringFor(uiLanguage, "soon"), Toast.LENGTH_SHORT).show()
    }

    val backupLabel = if (lastBackup > 0L) {
        SimpleDateFormat("yyyy/M/d HH:mm", Locale.getDefault()).format(Date(lastBackup))
    } else {
        "—"
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        LocalizedStrings.get("settings"),
                        color = SettingsTitleC,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = SettingsTitleC
                        )
                    }
                }
            )
        }
    ) { padding ->
        key(langKey) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(8.dp))
            SettingsGroupCard(title = LocalizedStrings.get("reminders_notifications")) {
                SettingsNavRow(
                    label = LocalizedStrings.get("notification_settings"),
                    value = "$reminderTime · $reflectionTime",
                    onClick = onNavigateToNotification
                )
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = LocalizedStrings.get("appearance")) {
                SettingsNavRow(
                    label = LocalizedStrings.get("theme_settings"),
                    value = "${themeSummary(dark)} · ${colorSummary(theme)}",
                    onClick = onNavigateToTheme
                )
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = LocalizedStrings.get("mood_records")) {
                SettingsNavRow(
                    label = LocalizedStrings.get("mood_settings"),
                    value = reflectionTime,
                    onClick = onNavigateToMood
                )
                SettingsGroupDivider()
                SettingsNavRow(
                    label = LocalizedStrings.get("mood_vocab"),
                    value = "",
                    onClick = { toastSoon() }
                )
                SettingsGroupDivider()
                SettingsNavRow(
                    label = LocalizedStrings.get("topic_manage"),
                    value = "",
                    onClick = onOpenEssayTopics
                )
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = LocalizedStrings.get("widget_settings")) {
                SettingsNavRow(
                    label = LocalizedStrings.get("widget_nav"),
                    value = "",
                    onClick = onNavigateToWidget
                )
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = LocalizedStrings.get("tools")) {
                SettingsNavRow(
                    label = LocalizedStrings.get("data_export"),
                    value = "",
                    onClick = onNavigateToExport
                )
                SettingsGroupDivider()
                SettingsNavRow(
                    label = LocalizedStrings.get("data_import"),
                    value = "",
                    onClick = onNavigateToImport
                )
                SettingsGroupDivider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        LocalizedStrings.get("backup_status"),
                        color = SettingsTitleC,
                        fontSize = 15.sp
                    )
                    Text(backupLabel, color = SettingsSubC, fontSize = 15.sp)
                }
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = "") {
                SettingsNavRow(
                    label = LocalizedStrings.get("language_nav"),
                    value = languageSummary(languageCode),
                    onClick = onNavigateToLanguage
                )
                SettingsGroupDivider()
                SettingsNavRow(
                    label = LocalizedStrings.get("privacy_settings"),
                    value = "",
                    onClick = onNavigateToPrivacy
                )
                SettingsGroupDivider()
                SettingsNavRow(
                    label = LocalizedStrings.get("other_settings"),
                    value = "",
                    onClick = onNavigateToMisc
                )
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = LocalizedStrings.get("experimental")) {
                SettingsNavRow(
                    label = LocalizedStrings.get("annual_review"),
                    value = "",
                    onClick = { toastSoon() }
                )
                SettingsGroupDivider()
                SettingsNavRow(
                    label = LocalizedStrings.get("data_analysis"),
                    value = "",
                    onClick = { toastSoon() }
                )
            }
            Spacer(Modifier.height(32.dp))
        }
        }
    }
}

@Composable
private fun themeSummary(key: String): String = when (key) {
    "light" -> LocalizedStrings.get("light_mode")
    "dark" -> LocalizedStrings.get("dark_mode_option")
    else -> LocalizedStrings.get("system_default")
}

@Composable
private fun colorSummary(key: String): String = when (key) {
    "green" -> LocalizedStrings.get("forest_green")
    "pink" -> LocalizedStrings.get("soft_pink")
    "orange" -> LocalizedStrings.get("warm_orange")
    else -> LocalizedStrings.get("vitality_blue")
}

@Composable
private fun languageSummary(key: String): String = when (key) {
    "en" -> LocalizationManager.Language.EN.displayName
    else -> LocalizationManager.Language.ZH.displayName
}
