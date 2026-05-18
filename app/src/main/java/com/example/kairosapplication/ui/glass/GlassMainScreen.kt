package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.viewmodel.TaskViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Star
import java.time.LocalDate

/**
 * Glass-style Today page content.
 *
 * Replaces the visual layer of TodayScreen while keeping the same
 * data flow and state management. Uses Glass UI components exclusively.
 */
@Composable
fun GlassTodayContent(
    taskViewModel: TaskViewModel,
    onCreateClick: () -> Unit = {},
    onDailyReviewClick: () -> Unit = {},
    onQuoteClick: () -> Unit = {},
) {
    val uiState by taskViewModel.uiState.collectAsState()
    val allTasks = uiState.tasks

    var currentDate by remember { mutableStateOf(LocalDate.now()) }

    // Time block expansion states
    var anytimeExpanded by remember { mutableStateOf(true) }
    var morningExpanded by remember { mutableStateOf(true) }
    var afternoonExpanded by remember { mutableStateOf(true) }
    var eveningExpanded by remember { mutableStateOf(true) }

    // Reactive task filtering
    val anytimeTasks by remember(currentDate, allTasks) {
        derivedStateOf {
            com.example.taskmodel.util.TaskUtils.sortTasks(
                allTasks.filter {
                    it.taskDate == currentDate && it.timeBlock == TaskConstants.TIME_BLOCK_ANYTIME
                },
            )
        }
    }
    val morningTasks by remember(currentDate, allTasks) {
        derivedStateOf {
            com.example.taskmodel.util.TaskUtils.sortTasks(
                allTasks.filter {
                    it.taskDate == currentDate && it.timeBlock == TaskConstants.TIME_BLOCK_MORNING
                },
            )
        }
    }
    val afternoonTasks by remember(currentDate, allTasks) {
        derivedStateOf {
            com.example.taskmodel.util.TaskUtils.sortTasks(
                allTasks.filter {
                    it.taskDate == currentDate && it.timeBlock == TaskConstants.TIME_BLOCK_AFTERNOON
                },
            )
        }
    }
    val eveningTasks by remember(currentDate, allTasks) {
        derivedStateOf {
            com.example.taskmodel.util.TaskUtils.sortTasks(
                allTasks.filter {
                    it.taskDate == currentDate && it.timeBlock == TaskConstants.TIME_BLOCK_EVENING
                },
            )
        }
    }

    // TopBar stats
    val todayTasksForTopBar = allTasks.filter { it.taskDate == currentDate }
    val totalCount = todayTasksForTopBar.size
    val completedCount = todayTasksForTopBar.count { it.isCompleted }

    LaunchedEffect(Unit) {
        taskViewModel.syncFromCreationBus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(48.dp)) // status bar space

        // Top bar
        GlassTopBar(
            completedCount = completedCount,
            totalCount = totalCount,
            onCreateClick = onCreateClick,
            onDailyReviewClick = onDailyReviewClick,
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Day switcher
        GlassDaySwitcher(
            currentDate = currentDate,
            onPreviousDay = { currentDate = currentDate.minusDays(1) },
            onNextDay = { currentDate = currentDate.plusDays(1) },
            onJumpToToday = { currentDate = LocalDate.now() },
        )

        Spacer(modifier = Modifier.height(12.dp))

        val quoteText = taskViewModel.dailyQuoteDisplayText(
            LocalizedStrings.get("widget_quote_default"),
        )
        GlassQuoteSection(quote = quoteText, onClick = onQuoteClick)

        Spacer(modifier = Modifier.height(20.dp))

        // Time blocks — vertical immersive flow with rhythmic spacing
        GlassTimeBlock(
            blockKey = TaskConstants.TIME_BLOCK_ANYTIME,
            displayTitle = LocalizedStrings.timeBlockLabel(TaskConstants.TIME_BLOCK_ANYTIME),
            count = anytimeTasks.size,
            icon = Icons.Default.Star,
            expanded = anytimeExpanded,
            onToggle = { anytimeExpanded = !anytimeExpanded },
            tasks = anytimeTasks,
            viewDate = currentDate,
            onToggleComplete = { task -> taskViewModel.toggleTaskComplete(task) },
            onOpenDetail = { /* TODO: open detail sheet */ },
            onSwipeDelete = { task -> taskViewModel.deleteTask(task) },
            onCreateClick = onCreateClick,
        )

        Spacer(modifier = Modifier.height(8.dp))

        GlassTimeBlock(
            blockKey = TaskConstants.TIME_BLOCK_MORNING,
            displayTitle = LocalizedStrings.timeBlockLabel(TaskConstants.TIME_BLOCK_MORNING),
            count = morningTasks.size,
            icon = androidx.compose.material.icons.Icons.Default.WbSunny,
            expanded = morningExpanded,
            onToggle = { morningExpanded = !morningExpanded },
            tasks = morningTasks,
            viewDate = currentDate,
            onToggleComplete = { task -> taskViewModel.toggleTaskComplete(task) },
            onOpenDetail = { /* TODO: open detail sheet */ },
            onSwipeDelete = { task -> taskViewModel.deleteTask(task) },
            onCreateClick = onCreateClick,
        )

        Spacer(modifier = Modifier.height(8.dp))

        GlassTimeBlock(
            blockKey = TaskConstants.TIME_BLOCK_AFTERNOON,
            displayTitle = LocalizedStrings.timeBlockLabel(TaskConstants.TIME_BLOCK_AFTERNOON),
            count = afternoonTasks.size,
            icon = Icons.Default.WbTwilight,
            expanded = afternoonExpanded,
            onToggle = { afternoonExpanded = !afternoonExpanded },
            tasks = afternoonTasks,
            viewDate = currentDate,
            onToggleComplete = { task -> taskViewModel.toggleTaskComplete(task) },
            onOpenDetail = { /* TODO: open detail sheet */ },
            onSwipeDelete = { task -> taskViewModel.deleteTask(task) },
            onCreateClick = onCreateClick,
        )

        Spacer(modifier = Modifier.height(8.dp))

        GlassTimeBlock(
            blockKey = TaskConstants.TIME_BLOCK_EVENING,
            displayTitle = LocalizedStrings.timeBlockLabel(TaskConstants.TIME_BLOCK_EVENING),
            count = eveningTasks.size,
            icon = Icons.Default.DarkMode,
            expanded = eveningExpanded,
            onToggle = { eveningExpanded = !eveningExpanded },
            tasks = eveningTasks,
            viewDate = currentDate,
            onToggleComplete = { task -> taskViewModel.toggleTaskComplete(task) },
            onOpenDetail = { /* TODO: open detail sheet */ },
            onSwipeDelete = { task -> taskViewModel.deleteTask(task) },
            onCreateClick = onCreateClick,
        )

        // Bottom spacing for nav bar
        Spacer(modifier = Modifier.height(80.dp))
    }
}
