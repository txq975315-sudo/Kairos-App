package com.example.kairosapplication.ui.mine.settings

import android.widget.Toast
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.R
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.i18n.findActivity
import kotlinx.coroutines.launch

@Composable
fun LanguageSettingsScreen(
    onBack: () -> Unit,
    localizationManager: LocalizationManager,
    modifier: Modifier = Modifier
) {
    val current by localizationManager.currentLanguage.collectAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val currentLabel = current.displayName

    suspend fun applyIfChanged(language: LocalizationManager.Language) {
        if (language == current) return
        localizationManager.setLanguage(language)
        Toast.makeText(
            context,
            context.getString(R.string.i18n_language_applied),
            Toast.LENGTH_SHORT
        ).show()
        context.findActivity()?.recreate()
    }

    SettingsL2Scaffold(
        title = LocalizedStrings.get("language_settings"),
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
            SettingsGroupCard(title = LocalizedStrings.get("language")) {
                Text(
                    "${LocalizedStrings.get("current_language")}: $currentLabel",
                    fontSize = 15.sp,
                    color = SettingsTitleC
                )
                SettingsGroupDivider()
                Spacer(Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch { applyIfChanged(LocalizationManager.Language.ZH) }
                        }
                        .padding(vertical = 6.dp)
                ) {
                    RadioButton(
                        selected = current == LocalizationManager.Language.ZH,
                        onClick = {
                            scope.launch { applyIfChanged(LocalizationManager.Language.ZH) }
                        }
                    )
                    Text(
                        LocalizationManager.Language.ZH.displayName,
                        fontSize = 15.sp,
                        color = SettingsTitleC
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch { applyIfChanged(LocalizationManager.Language.EN) }
                        }
                        .padding(vertical = 6.dp)
                ) {
                    RadioButton(
                        selected = current == LocalizationManager.Language.EN,
                        onClick = {
                            scope.launch { applyIfChanged(LocalizationManager.Language.EN) }
                        }
                    )
                    Text(
                        LocalizationManager.Language.EN.displayName,
                        fontSize = 15.sp,
                        color = SettingsTitleC
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Text(
                LocalizedStrings.get("language_hint"),
                fontSize = 13.sp,
                color = SettingsSubC,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Spacer(Modifier.height(32.dp))
        }
    }
}
