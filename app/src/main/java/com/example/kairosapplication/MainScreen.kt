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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
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
import com.example.kairosapplication.ui.common.CommonBackButton
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import androidx.lifecycle.viewmodel.compose.viewModel
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
fun MainScreen() {
    val context = LocalContext.current
    val taskViewModel: TaskViewModel = viewModel(
        factory = TaskViewModel.factory(context.applicationContext)
    )
    val taskUiState by taskViewModel.uiState.collectAsState()

    var selectedTab by remember { mutableStateOf(AppTab.Today) }
    var overlay by remember { mutableStateOf<Overlay?>(null) }
    val navController = rememberNavController()
    val essayNavController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val essayNavBackStackEntry by essayNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val essayRoute = essayNavBackStackEntry?.destination?.route
    val hideBottomBar =
        (selectedTab == AppTab.Today && currentRoute == "create") ||
            (selectedTab == AppTab.Essay && essayRoute != null && essayRoute != "essay_main")
    var showCreatePendingLimitDialog by remember { mutableStateOf(false) }
    var createLimitTargetDate by remember { mutableStateOf<LocalDate?>(null) }

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
        return
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
                            taskViewModel = taskViewModel
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
                    taskViewModel = taskViewModel
                )
            } else {
                PlaceholderScreen(selectedTab.label)
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

@Composable
private fun PlaceholderScreen(name: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = 24.sp,
            color = SecondaryTextColor
        )
    }
}
