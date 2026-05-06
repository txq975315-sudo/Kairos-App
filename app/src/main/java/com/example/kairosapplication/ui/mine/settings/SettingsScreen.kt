package com.example.kairosapplication.ui.mine.settings

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Brightness6
import androidx.compose.material.icons.outlined.Cached
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.UploadFile
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings

private val ScreenBg = Color(0xFFFAFAFA)
private val RowText = Color(0xFF1A1A1A)

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
    val reminderTime by viewModel.dailyReminderTime.collectAsState()
    val reflectionTime by viewModel.dailyReflectionTime.collectAsState()
    val dark by viewModel.darkMode.collectAsState()
    val theme by viewModel.themeColor.collectAsState()
    val languageCode by viewModel.language.collectAsState()

    fun toastSoon() {
        Toast.makeText(context, LocalizedStrings.stringFor(uiLanguage, "soon"), Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBg)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .shadow(1.dp, CircleShape)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable(onClick = onBack),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "‹",
                    fontSize = 22.sp,
                    color = RowText,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(Modifier.size(12.dp))
            Text(
                text = LocalizedStrings.get("settings"),
                color = RowText,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif
            )
        }
        HorizontalDivider(color = Color(0xFFECECEC), thickness = 1.dp)
        key(langKey) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp)
            ) {
                Spacer(Modifier.height(8.dp))
                SettingsProtoRow(
                    icon = Icons.Outlined.Notifications,
                    title = LocalizedStrings.get("notification_settings"),
                    subtitle = "$reminderTime · $reflectionTime",
                    onClick = onNavigateToNotification
                )
                SettingsProtoRow(
                    icon = Icons.Outlined.Brightness6,
                    title = LocalizedStrings.get("theme_settings"),
                    subtitle = "${themeSummary(dark)} · ${colorSummary(theme)}",
                    onClick = onNavigateToTheme
                )
                SettingsProtoRow(
                    icon = Icons.Outlined.Widgets,
                    title = LocalizedStrings.get("widget_settings"),
                    subtitle = null,
                    onClick = onNavigateToWidget
                )
                SettingsProtoRow(
                    icon = Icons.Outlined.UploadFile,
                    title = LocalizedStrings.get("settings_export_csv_json"),
                    subtitle = null,
                    onClick = onNavigateToExport
                )
                SettingsProtoRow(
                    icon = Icons.Outlined.FileDownload,
                    title = LocalizedStrings.get("data_import"),
                    subtitle = null,
                    onClick = onNavigateToImport
                )
                SettingsProtoRow(
                    icon = Icons.Outlined.Analytics,
                    title = LocalizedStrings.get("settings_stat_analysis"),
                    subtitle = null,
                    onClick = { toastSoon() }
                )
                SettingsProtoRow(
                    icon = Icons.Outlined.MenuBook,
                    title = LocalizedStrings.get("language_nav"),
                    subtitle = languageSummary(languageCode),
                    onClick = onNavigateToLanguage
                )
                SettingsProtoRow(
                    icon = Icons.Outlined.Shield,
                    title = LocalizedStrings.get("settings_privacy_management"),
                    subtitle = null,
                    onClick = onNavigateToPrivacy
                )
                SettingsProtoRow(
                    icon = Icons.Outlined.Cached,
                    title = LocalizedStrings.get("clear_cache"),
                    subtitle = null,
                    onClick = onNavigateToMisc
                )
                SettingsProtoRow(
                    icon = Icons.Outlined.Feedback,
                    title = LocalizedStrings.get("settings_problem_feedback"),
                    subtitle = null,
                    onClick = { toastSoon() }
                )
                SettingsProtoRow(
                    icon = Icons.AutoMirrored.Outlined.Logout,
                    title = LocalizedStrings.get("settings_sign_out"),
                    subtitle = null,
                    onClick = { toastSoon() }
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp),
                    color = Color(0xFFE8E8E8)
                )
                SettingsProtoRow(
                    icon = Icons.Outlined.MenuBook,
                    title = LocalizedStrings.get("mood_settings"),
                    subtitle = reflectionTime,
                    onClick = onNavigateToMood
                )
                SettingsProtoRow(
                    icon = Icons.Outlined.AutoStories,
                    title = LocalizedStrings.get("topic_manage"),
                    subtitle = null,
                    onClick = onOpenEssayTopics
                )
                Spacer(Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun SettingsProtoRow(
    icon: ImageVector,
    title: String,
    subtitle: String?,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(horizontal = 12.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = RowText,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.size(18.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = RowText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                )
                if (!subtitle.isNullOrEmpty()) {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = subtitle,
                        color = SettingsSubC,
                        fontSize = 13.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
            Text("›", color = SettingsSubC, fontSize = 20.sp)
        }
        HorizontalDivider(
            modifier = Modifier.padding(start = 54.dp),
            thickness = 0.5.dp,
            color = Color(0xFFE8E8E8)
        )
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
