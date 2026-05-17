package com.example.kairosapplication.ui.glass

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kairosapplication.MainWidgetIntentViewModel
import com.example.kairosapplication.core.ui.LocalUrgencyConfig
import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.mine.settings.SettingsViewModel
import com.example.taskmodel.util.TaskUtils
import com.example.taskmodel.viewmodel.TaskViewModel
import com.example.kairosapplication.ui.glass.glassHazeSource

/**
 * Glass UI entry point — switch between Glass and Classic UI.
 *
 * When [useGlass] is true, renders the full Glass UI system.
 * When false, falls through to [onClassic] (existing MainScreen).
 *
 * Default is false — existing behavior is preserved.
 */
@Composable
fun GlassOrClassicScreen(
    widgetIntentViewModel: MainWidgetIntentViewModel,
    useGlass: Boolean,
    onClassic: @Composable () -> Unit,
) {
    if (useGlass) {
        GlassMainScreen(widgetIntentViewModel = widgetIntentViewModel)
    } else {
        onClassic()
    }
}

/**
 * Full Glass UI main screen.
 *
 * Architecture mirrors MainScreen.kt but uses Glass UI components:
 * - Background: GlassAtmosphereBackground (Monet Water Lilies + contrast compression)
 * - Bottom nav: GlassBottomNav (ultra-light frosted glass)
 * - Today content: GlassTodayContent (GlassTaskCard + GlassTimeBlock)
 * - Text colors: white-based (LocalGlassTextColors)
 *
 * Shares all data layer (TaskViewModel, DataStoreManager) with MainScreen.
 */
@Composable
fun GlassMainScreen(
    widgetIntentViewModel: MainWidgetIntentViewModel,
) {
    val context = LocalContext.current
    val dataStoreManager = remember(context) { DataStoreManager(context.applicationContext) }
    val localizationManager = remember { LocalizationManager(dataStoreManager) }

    var bootReady by remember { mutableStateOf(false) }
    var needsLanguageOnboarding by remember { mutableStateOf(false) }

    LaunchedEffect(dataStoreManager, localizationManager) {
        dataStoreManager.ensureLanguageOnboardingMigration()
        needsLanguageOnboarding = !dataStoreManager.isLanguageOnboardingDone()
        localizationManager.loadLanguage()
        bootReady = true
    }

    val languageState = localizationManager.currentLanguage.collectAsState()
    val taskViewModel: TaskViewModel = viewModel(
        factory = TaskViewModel.factory(context.applicationContext)
    )
    val settingsViewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModel.factory(context.applicationContext, taskViewModel.uiState)
    )

    val taskUiState by taskViewModel.uiState.collectAsState()
    val urgencyConfig by settingsViewModel.urgencyConfig.collectAsState()
    val atmosphereWallpaperUri by settingsViewModel.atmosphereWallpaperUri.collectAsState()

    // Keep TaskUtils static cache in sync
    LaunchedEffect(urgencyConfig) {
        TaskUtils.setUrgencyConfig(urgencyConfig)
    }

    var selectedTab by remember { mutableStateOf(GlassAppTab.Today) }
    val glassHazeState = rememberGlassHazeState()

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .glassHazeSource(glassHazeState),
        ) {
            if (com.example.kairosapplication.core.ui.constants.GlassConstants.usesBackdropBlur) {
                com.example.kairosapplication.ui.KairosAtmosphereWallpaper(
                    Modifier.fillMaxSize(),
                    wallpaperUri = atmosphereWallpaperUri,
                )
            } else {
                com.example.kairosapplication.ui.KairosAtmosphereBackground(
                    Modifier.fillMaxSize(),
                    wallpaperUri = atmosphereWallpaperUri,
                )
            }
        }
        if (com.example.kairosapplication.core.ui.constants.GlassConstants.usesBackdropBlur) {
            com.example.kairosapplication.ui.KairosAtmosphereDimOverlay(Modifier.fillMaxSize())
        }

        if (bootReady && !needsLanguageOnboarding) {
            ProvideGlassAtmosphereUi(
                hazeState = glassHazeState,
                wallpaperUri = atmosphereWallpaperUri,
            ) {
            CompositionLocalProvider(
                LocalCurrentLanguage provides languageState,
                LocalUrgencyConfig provides urgencyConfig,
            ) {
                Scaffold(
                    containerColor = Color.Transparent,
                    bottomBar = {
                        AnimatedVisibility(
                            visible = true,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it }),
                        ) {
                            GlassBottomNav(
                                selectedIndex = selectedTab.ordinal,
                                onTabSelected = { selectedTab = GlassAppTab.entries[it] },
                                tabs = glassMainNavTabs(),
                            )
                        }
                    },
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = innerPadding.calculateBottomPadding()),
                    ) {
                        when (selectedTab) {
                            GlassAppTab.Today -> {
                                GlassTodayContent(
                                    taskViewModel = taskViewModel,
                                    onCreateClick = { /* TODO: navigate to create */ },
                                    onDailyReviewClick = { /* TODO: show daily review */ },
                                    onQuoteClick = { /* TODO: navigate to quote settings */ },
                                )
                            }
                            // Other tabs: placeholder (to be implemented)
                            else -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = LocalizedStrings.get("glass_tab_coming_soon"),
                                        color = GlassConstants.TextMuted,
                                    )
                                }
                            }
                        }
                    }
                }
            }
            }
        }
    }
}
