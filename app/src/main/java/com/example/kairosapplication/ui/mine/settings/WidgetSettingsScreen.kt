package com.example.kairosapplication.ui.mine.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalizedStrings

@Composable
fun WidgetSettingsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    SettingsL2Scaffold(
        title = LocalizedStrings.get("widget_settings"),
        onBack = onBack,
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = "") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            LocalizedStrings.get("dev_in_widget"),
                            fontSize = 14.sp,
                            color = SettingsSubC,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            LocalizedStrings.get("coming_soon"),
                            fontSize = 14.sp,
                            color = SettingsSubC,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
