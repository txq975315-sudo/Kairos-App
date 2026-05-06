package com.example.kairosapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.notification.NotificationHelper
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kairosapplication.ui.CreateScreen
import com.example.kairosapplication.ui.EssayNavHost
import com.example.kairosapplication.ui.mine.MineScreen
import com.example.kairosapplication.ui.mine.MineViewModel
import com.example.kairosapplication.ui.mine.MoodCalendarScreen
import com.example.kairosapplication.ui.mine.settings.DataExportScreen
import com.example.kairosapplication.ui.mine.settings.DataImportScreen
import com.example.kairosapplication.ui.mine.settings.LanguageSettingsScreen
import com.example.kairosapplication.ui.mine.settings.MiscSettingsScreen
import com.example.kairosapplication.ui.mine.settings.MoodSettingsScreen
import com.example.kairosapplication.ui.mine.settings.NotificationSettingsScreen
import com.example.kairosapplication.ui.mine.settings.PrivacySettingsScreen
import com.example.kairosapplication.ui.mine.settings.SettingsScreen
import com.example.kairosapplication.ui.mine.settings.SettingsViewModel
import com.example.kairosapplication.ui.mine.settings.ThemeSettingsScreen
import com.example.kairosapplication.ui.mine.settings.WidgetSettingsScreen
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.kairosapplication.ui.view.ViewScreen
import com.example.kairosapplication.ui.widget.WidgetMainScreen
import com.example.kairosapplication.widget.WidgetClickHandler
import com.example.kairosapplication.widget.WidgetManager
import com.example.kairosapplication.ui.common.CommonBackButton
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.LocalDate

private enum class AppTab(val label: String, val icon: ImageVector) {
    Today("Today", Icons.Default.CalendarToday),
    Essay("Essay", Icons.Default.Edit),
    View("View", Icons.Default.ViewList),
    Widget("Widget", Icons.Default.Widgets),
    Mine("Mine", Icons.Default.Person)
}

private enum class Overlay { DailyReview }

@Composable
fun MainScreen(
    widgetIntentViewModel: MainWidgetIntentViewModel = viewModel()
) {
    val context = LocalContext.current
    val dataStoreManager = remember(context) { DataStoreManager(context.applicationContext) }
    val localizationManager = remember { LocalizationManager(dataStoreManager) }
    val notificationHelper = remember(context) { NotificationHelper(context.applicationContext) }
    LaunchedEffect(Unit) { localizationManager.loadLanguage() }
    val languageState = localizationManager.currentLanguage.collectAsState()
    val taskViewModel: TaskViewModel = viewModel(
        factory = TaskViewModel.factory(context.applicationContext)
    )
    val mineViewModel: MineViewModel = viewModel(
        factory = MineViewModel.factory(context.applicationContext, taskViewModel.uiState)
    )
    val settingsViewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModel.factory(context.applicationContext, taskViewModel.uiState)
    )
    val taskUiState by taskViewModel.uiState.collectAsState()

    LaunchedEffect(taskUiState.tasks, taskUiState.dailyQuoteEssayId) {
        WidgetManager.refreshWidgetsAsync(context.applicationContext, taskViewModel)
    }

    var selectedTab by remember { mutableStateOf(AppTab.Today) }
    val widgetNav by widgetIntentViewModel.widgetNav.collectAsState(initial = 0 to WidgetNavPayload(null))

    /** Incremented on widget "create" tap so Today opens [CreateTaskBottomSheet] like in-app +. */
    var widgetCreateSheetNonce by remember { mutableStateOf(0) }
    var pendingWidgetEditTaskId by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(widgetNav.first) {
        when (val target = widgetNav.second.target) {
            WidgetClickHandler.TARGET_TODAY -> selectedTab = AppTab.Today
            WidgetClickHandler.TARGET_ESSAY -> selectedTab = AppTab.Essay
            WidgetClickHandler.TARGET_VIEW -> selectedTab = AppTab.View
            WidgetClickHandler.TARGET_MINE -> selectedTab = AppTab.Mine
            WidgetClickHandler.TARGET_HOME -> selectedTab = AppTab.Today
            WidgetClickHandler.TARGET_CREATE -> {
                selectedTab = AppTab.Today
                widgetCreateSheetNonce++
            }
            WidgetClickHandler.TARGET_EDIT_TASK -> {
                val id = widgetNav.second.taskId
                if (id >= 0) {
                    selectedTab = AppTab.Today
                    pendingWidgetEditTaskId = id
                }
            }
            null -> Unit
            else -> Unit
        }
    }
    var overlay by remember { mutableStateOf<Overlay?>(null) }
    var showMoodCalendar by remember { mutableStateOf(false) }
    var showSettingsScreen by remember { mutableStateOf(false) }
    var showExportScreen by remember { mutableStateOf(false) }
    var showImportScreen by remember { mutableStateOf(false) }
    var showNotificationSettings by remember { mutableStateOf(false) }
    var showThemeSettings by remember { mutableStateOf(false) }
    var showMoodSettings by remember { mutableStateOf(false) }
    var showWidgetSettings by remember { mutableStateOf(false) }
    var showLanguageSettings by remember { mutableStateOf(false) }
    var showPrivacySettings by remember { mutableStateOf(false) }
    var showMiscSettings by remember { mutableStateOf(false) }
    val navController = rememberNavController()

    val essayNavController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val essayNavBackStackEntry by essayNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val essayRoute = essayNavBackStackEntry?.destination?.route
    val hideBottomBar =
        (selectedTab == AppTab.Today && currentRoute == "create") ||
            (selectedTab == AppTab.Essay && essayRoute != null && essayRoute != "essay_main") ||
            (selectedTab == AppTab.Mine &&
                (showMoodCalendar || showSettingsScreen || showExportScreen || showImportScreen ||
                    showNotificationSettings || showThemeSettings || showMoodSettings ||
                    showWidgetSettings || showLanguageSettings || showPrivacySettings || showMiscSettings))
    var showCreatePendingLimitDialog by remember { mutableStateOf(false) }
    var createLimitTargetDate by remember { mutableStateOf<LocalDate?>(null) }
    var essayOpenTopicPrimary by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(selectedTab) {
        if (selectedTab != AppTab.Mine) {
            showMoodCalendar = false
            showSettingsScreen = false
            showExportScreen = false
            showImportScreen = false
            showNotificationSettings = false
            showThemeSettings = false
            showMoodSettings = false
            showWidgetSettings = false
            showLanguageSettings = false
            showPrivacySettings = false
            showMiscSettings = false
        }
    }

    CompositionLocalProvider(LocalCurrentLanguage provides languageState) {
    if (overlay != null) {
        AnimatedContent(
            targetState = overlay,
            transitionSpec = {
                slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
            },
            label = "overlay"
        ) { current ->
            when (current) {
                Overlay.DailyReview -> DailyReviewScreen(
                    onBack = { overlay = null },
                    allTasks = taskUiState.tasks,
                    onTasksCreated = { newTasks ->
                        taskViewModel.saveTasks(taskUiState.tasks + newTasks)
                    },
                    onTaskUpdated = { updated -> taskViewModel.updateTask(updated) }
                )
                null -> Unit
            }
        }
        return@CompositionLocalProvider
    }

    Scaffold(
        containerColor = BackgroundColor,
        bottomBar = {
            AnimatedVisibility(
                visible = !hideBottomBar,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 0.dp
                ) {
                    AppTab.entries.forEach { tab ->
                        val selected = selectedTab == tab
                        NavigationBarItem(
                            selected = selected,
                            onClick = { selectedTab = tab },
                            icon = {
                                Icon(imageVector = tab.icon, contentDescription = tab.label)
                            },
                            label = {
                                Text(
                                    text = tab.label,
                                    fontSize = 11.sp,
                                    fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = PrimaryTextColor,
                                selectedTextColor = PrimaryTextColor,
                                unselectedIconColor = SecondaryTextColor,
                                unselectedTextColor = SecondaryTextColor,
                                indicatorColor = BackgroundColor
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            if (selectedTab == AppTab.Today) {
                NavHost(
                    navController = navController,
                    startDestination = "today"
                ) {
                    composable("today") {
                        TodayScreen(
                            onCreateClick = { navController.navigate("create") },
                            onDailyReviewClick = { overlay = Overlay.DailyReview },
                            onQuoteClick = { navController.navigate("quote_settings") },
                            taskViewModel = taskViewModel,
                            widgetEditTaskId = pendingWidgetEditTaskId,
                            onConsumedWidgetEditIntent = { pendingWidgetEditTaskId = null },
                            widgetCreateSheetNonce = widgetCreateSheetNonce
                        )
                    }
                    composable("create") {
                        CreateScreen(
                            onBack = { navController.popBackStack() },
                            onTasksCreated = { newTasks ->
                                val blockedDate = taskViewModel.firstDateExceedingLimitIfAdded(newTasks)
                                if (blockedDate != null) {
                                    createLimitTargetDate = blockedDate
                                    showCreatePendingLimitDialog = true
                                } else {
                                    val cur = taskViewModel.uiState.value.tasks
                                    taskViewModel.saveTasks(cur + newTasks)
                                }
                            }
                        )
                    }
                    composable("quote_settings") {
                        QuoteSettingScreen(onBack = { navController.popBackStack() })
                    }
                }
            } else if (selectedTab == AppTab.Essay) {
                EssayNavHost(
                    navController = essayNavController,
                    taskViewModel = taskViewModel,
                    openTopicTabWithPrimary = essayOpenTopicPrimary,
                    onOpenTopicTabConsumed = { essayOpenTopicPrimary = null },
                )
            } else if (selectedTab == AppTab.View) {
                ViewScreen(
                    onRequestOpenToday = { selectedTab = AppTab.Today },
                    onRequestOpenEssay = { selectedTab = AppTab.Essay },
                    onRequestOpenEssayTopic = { primary ->
                        essayOpenTopicPrimary = primary
                        selectedTab = AppTab.Essay
                    },
                )
            } else if (selectedTab == AppTab.Widget) {
                WidgetMainScreen(taskViewModel = taskViewModel)
            } else if (selectedTab == AppTab.Mine) {
                when {
                    showMoodCalendar -> MoodCalendarScreen(
                        mineViewModel = mineViewModel,
                        onBack = { showMoodCalendar = false }
                    )
                    showExportScreen -> DataExportScreen(
                        viewModel = settingsViewModel,
                        onBack = {
                            settingsViewModel.clearExportState()
                            showExportScreen = false
                            showSettingsScreen = true
                        }
                    )
                    showImportScreen -> DataImportScreen(
                        viewModel = settingsViewModel,
                        onBack = {
                            showImportScreen = false
                            showSettingsScreen = true
                        }
                    )
                    showNotificationSettings -> NotificationSettingsScreen(
                        onBack = {
                            showNotificationSettings = false
                            showSettingsScreen = true
                        },
                        viewModel = settingsViewModel,
                        notificationHelper = notificationHelper
                    )
                    showThemeSettings -> ThemeSettingsScreen(
                        viewModel = settingsViewModel,
                        onBack = {
                            showThemeSettings = false
                            showSettingsScreen = true
                        }
                    )
                    showMoodSettings -> MoodSettingsScreen(
                        onBack = {
                            showMoodSettings = false
                            showSettingsScreen = true
                        },
                        viewModel = settingsViewModel,
                        notificationHelper = notificationHelper
                    )
                    showWidgetSettings -> WidgetSettingsScreen(
                        onBack = {
                            showWidgetSettings = false
                            showSettingsScreen = true
                        }
                    )
                    showLanguageSettings -> LanguageSettingsScreen(
                        onBack = {
                            showLanguageSettings = false
                            showSettingsScreen = true
                        },
                        localizationManager = localizationManager
                    )
                    showPrivacySettings -> PrivacySettingsScreen(
                        onBack = {
                            showPrivacySettings = false
                            showSettingsScreen = true
                        }
                    )
                    showMiscSettings -> MiscSettingsScreen(
                        viewModel = settingsViewModel,
                        onBack = {
                            showMiscSettings = false
                            showSettingsScreen = true
                        }
                    )
                    showSettingsScreen -> SettingsScreen(
                        onBack = { showSettingsScreen = false },
                        localizationManager = localizationManager,
                        viewModel = settingsViewModel,
                        onNavigateToExport = {
                            showSettingsScreen = false
                            showExportScreen = true
                        },
                        onNavigateToImport = {
                            showSettingsScreen = false
                            showImportScreen = true
                        },
                        onNavigateToNotification = {
                            showSettingsScreen = false
                            showNotificationSettings = true
                        },
                        onNavigateToTheme = {
                            showSettingsScreen = false
                            showThemeSettings = true
                        },
                        onNavigateToMood = {
                            showSettingsScreen = false
                            showMoodSettings = true
                        },
                        onNavigateToWidget = {
                            showSettingsScreen = false
                            showWidgetSettings = true
                        },
                        onNavigateToLanguage = {
                            showSettingsScreen = false
                            showLanguageSettings = true
                        },
                        onNavigateToPrivacy = {
                            showSettingsScreen = false
                            showPrivacySettings = true
                        },
                        onNavigateToMisc = {
                            showSettingsScreen = false
                            showMiscSettings = true
                        },
                        onOpenEssayTopics = {
                            essayOpenTopicPrimary = NotePrimaryCategory.SELF_AWARENESS
                            selectedTab = AppTab.Essay
                            showSettingsScreen = false
                        }
                    )
                    else -> MineScreen(
                        mineViewModel = mineViewModel,
                        onNavigateToMoodCalendar = { showMoodCalendar = true },
                        onOpenSettings = { showSettingsScreen = true },
                        onOpenTheme = { showThemeSettings = true }
                    )
                }
            }

            if (showCreatePendingLimitDialog && createLimitTargetDate != null) {
                val limitDate = createLimitTargetDate!!
                AlertDialog(
                    onDismissRequest = {
                        showCreatePendingLimitDialog = false
                    },
                    title = { Text("Daily to-do limit reached", color = PrimaryTextColor) },
                    text = {
                        Text(
                            "You can have at most ${TaskViewModel.DAILY_PENDING_LIMIT} incomplete tasks per day. Clear tasks for that day to add more.",
                            color = SecondaryTextColor
                        )
                    },
                    confirmButton = {
                        TextButton(onClick = { showCreatePendingLimitDialog = false }) {
                            Text("Got it", color = PrimaryTextColor)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                taskViewModel.deleteAllTasksForDate(limitDate)
                                showCreatePendingLimitDialog = false
                            }
                        ) {
                            Text("Clear today's tasks", color = PrimaryTextColor)
                        }
                    }
                )
            }
        }
    }
    }
}

@Composable
private fun QuoteSettingScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .statusBarsPadding()
    ) {
        CommonBackButton(onClick = onBack)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Daily Sentence",
                fontSize = 22.sp,
                color = SecondaryTextColor
            )
        }
    }
}

