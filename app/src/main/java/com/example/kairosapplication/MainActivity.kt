package com.example.kairosapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.notification.NotificationHelper
import com.example.kairosapplication.ui.theme.KairosApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationHelper(this).createNotificationChannels()
        enableEdgeToEdge()
        setContent {
            KairosApplicationTheme(dynamicColor = false) {
                MainScreen()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    KairosApplicationTheme(dynamicColor = false) {
        CompositionLocalProvider(
            LocalCurrentLanguage provides remember { mutableStateOf(LocalizationManager.Language.ZH) }
        ) {
            MainScreen()
        }
    }
}