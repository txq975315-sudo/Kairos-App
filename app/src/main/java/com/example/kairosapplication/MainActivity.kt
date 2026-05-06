package com.example.kairosapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kairosapplication.widget.WidgetManager
import com.example.taskmodel.viewmodel.TaskViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    private val taskViewModel: TaskViewModel by viewModels(factoryProducer = {
        TaskViewModel.factory(applicationContext)
    })

    private val widgetIntentViewModel: MainWidgetIntentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationHelper(this).createNotificationChannels()
        enableEdgeToEdge()
        widgetIntentViewModel.resetFromCreate(intent)
        setContent {
            KairosApplicationTheme(dynamicColor = false) {
                MainScreen(widgetIntentViewModel = widgetIntentViewModel)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        widgetIntentViewModel.pushFromNewIntent(intent)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch(Dispatchers.IO) {
            WidgetManager.refreshWidgets(this@MainActivity, taskViewModel)
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