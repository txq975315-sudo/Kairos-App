package com.example.kairosapplication.ui.mine.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalizedStrings

@Composable
fun ThemeSettingsScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dark by viewModel.darkMode.collectAsState()
    val theme by viewModel.themeColor.collectAsState()

    SettingsL2Scaffold(
        title = LocalizedStrings.get("theme_settings"),
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
            SettingsGroupCard(title = LocalizedStrings.get("dark_mode")) {
                listOf("system", "light", "dark").forEach { key ->
                    val label = when (key) {
                        "system" -> LocalizedStrings.get("system_default")
                        "light" -> LocalizedStrings.get("light_mode")
                        else -> LocalizedStrings.get("dark_mode_option")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.setDarkMode(key) }
                            .padding(vertical = 6.dp)
                    ) {
                        RadioButton(selected = dark == key, onClick = { viewModel.setDarkMode(key) })
                        Text(label, fontSize = 15.sp, color = SettingsTitleC)
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = LocalizedStrings.get("theme_color")) {
                listOf("blue", "green", "pink", "orange").forEach { key ->
                    val label = when (key) {
                        "green" -> LocalizedStrings.get("forest_green")
                        "pink" -> LocalizedStrings.get("soft_pink")
                        "orange" -> LocalizedStrings.get("warm_orange")
                        else -> LocalizedStrings.get("vitality_blue")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.setThemeColor(key) }
                            .padding(vertical = 6.dp)
                    ) {
                        RadioButton(selected = theme == key, onClick = { viewModel.setThemeColor(key) })
                        Text(label, fontSize = 15.sp, color = SettingsTitleC)
                    }
                }
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}
