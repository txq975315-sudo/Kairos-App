package com.example.kairosapplication

import com.example.kairosapplication.ui.components.*
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.kairosapplication.core.text.rememberTaskTextProvider
import com.example.kairosapplication.ui.TaskDetailBottomSheet
import com.example.kairosapplication.ui.TaskItemCard
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppInteraction
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.core.ui.AppTypography
import com.example.kairosapplication.ui.components.ArrowButton
import com.example.kairosapplication.ui.components.ArrowDirection
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.CreateTaskParam
import com.example.taskmodel.model.Task
import com.example.taskmodel.store.TaskCreationBus
import com.example.taskmodel.util.TaskUtils
import java.time.LocalDate
import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateMapOf
import com.example.taskmodel.viewmodel.TaskViewModel
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun TodayScreen(
    onCreateClick: () -> Unit = {},
    onDailyReviewClick: () -> Unit = {},
    onQuoteClick: () -> Unit = {},
    taskViewModel: TaskViewModel
) {
    val uiState by taskViewModel.uiState.collectAsState()
    val allTasks = uiState.tasks
    var anytimeExpanded by remember { mutableStateOf(true) }
    var morningExpanded by remember { mutableStateOf(true) }
    var afternoonExpanded by remember { mutableStateOf(true) }
    var eveningExpanded by remember { mutableStateOf(true) }

    var currentDate by remember { mutableStateOf(LocalDate.now()) }

    val anytimeTasks by remember(currentDate, allTasks) {
        derivedStateOf {
            allTasks.filter {
                it.taskDate == currentDate && it.timeBlock == TaskConstants.TIME_BLOCK_ANYTIME
            }
        }
    }
    val morningTasks by remember(currentDate, allTasks) {
        derivedStateOf {
            allTasks.filter {
                it.taskDate == currentDate && it.timeBlock == TaskConstants.TIME_BLOCK_MORNING
            }
        }
    }
    val afternoonTasks by remember(currentDate, allTasks) {
        derivedStateOf {
            allTasks.filter {
                it.taskDate == currentDate && it.timeBlock == TaskConstants.TIME_BLOCK_AFTERNOON
            }
        }
    }
    val eveningTasks by remember(currentDate, allTasks) {
        derivedStateOf {
            allTasks.filter {
                it.taskDate == currentDate && it.timeBlock == TaskConstants.TIME_BLOCK_EVENING
            }
        }
    }
    val onTaskCompleteToggle: (Task) -> Unit = { task -> taskViewModel.toggleTaskComplete(task) }

    LaunchedEffect(Unit) {
        taskViewModel.syncFromCreationBus()
    }

    // Debug: log repeatRule per task to trace creation pipeline.
    LaunchedEffect(allTasks.size) {
        allTasks.forEach { task ->
            println("TodayScreen task='${task.title}', date=${task.taskDate}, repeatRule='${task.repeatRule}'")
        }
    }

    // TopBar counts from TaskViewModel for the viewed date (stable refresh vs keyless remember).
    val todayTasksForTopBar = allTasks.filter { it.taskDate == currentDate }
    val totalCount = todayTasksForTopBar.size
    val completedCount = todayTasksForTopBar.count { it.isCompleted }
    var createSheetConfig by remember { mutableStateOf<CreateSheetConfig?>(null) }
    // Create sheet fields: keep text when sheet closes.
    var createTitle by remember { mutableStateOf("") }
    var createDescription by remember { mutableStateOf("") }
    var createUrgency by remember { mutableStateOf(3) }
    var createLabel by remember { mutableStateOf<String?>(null) }
    var createEmojiImage by remember { mutableStateOf<String?>(null) }
    var createLocalImageUri by remember { mutableStateOf<String?>(null) }
    var editingTask by remember { mutableStateOf<Task?>(null) }
    var detailTask by remember { mutableStateOf<Task?>(null) }

    // Single create entry: time block drives sheet title and colors.
    val showCreateSheet: (String) -> Unit = { timeBlock ->
        createSheetConfig = CreateSheetConfig(
            timeBlock = timeBlock,
            backgroundColor = TaskUtils.getTimeBlockColor(timeBlock),
            titleColor = TaskUtils.getTimeBlockTitleColor(timeBlock)
        )
    }

    val applyToAllTaskLists: ((List<Task>) -> List<Task>) -> Unit = { updater ->
        taskViewModel.saveTasks(updater(allTasks))
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showDailyLimitDialog by remember { mutableStateOf(false) }

    val blockBounds = remember { mutableStateMapOf<String, Rect>() }
    val dragHandleCenterY = remember { mutableStateMapOf<Int, Float>() }

    val handleTaskDragEnd: (Task, Float) -> Unit = dragEnd@{ task, totalDy ->
        val y0 = dragHandleCenterY[task.id] ?: return@dragEnd
        val endY = y0 + totalDy
        val target = resolveTimeBlockAtY(endY, blockBounds)
        if (target != null && target != task.timeBlock) {
            val latest = taskViewModel.uiState.value.tasks
            val t = latest.firstOrNull { it.id == task.id } ?: return@dragEnd
            taskViewModel.saveTasks(moveTaskToTimeBlock(latest, t, target, currentDate))
        }
    }

    val handleSwipeDelete: (Task) -> Unit = { task ->
        taskViewModel.deleteTask(task)
        scope.launch {
            when (
                snackbarHostState.showSnackbar(
                    message = "Task deleted",
                    actionLabel = "Undo",
                    duration = SnackbarDuration.Short
                )
            ) {
                SnackbarResult.ActionPerformed -> taskViewModel.undoDeleteTask()
                SnackbarResult.Dismissed -> taskViewModel.clearDeleteUndo()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.ScreenBackground)
            .statusBarsPadding()
            .padding(horizontal = AppSpacing.PageHorizontal)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
        // Fixed header (does not scroll with task list)
        Spacer(Modifier.height(AppSpacing.SectionSmall))
        TopBar(
            completed = completedCount,
            total = totalCount,
            onCreateClick = onCreateClick,
            onDailyReviewClick = onDailyReviewClick
        )
        Spacer(Modifier.height(6.dp))
        DateSection(
            currentDate = currentDate,
            onPrevious = { currentDate = currentDate.minusDays(1) },
            onNext = { currentDate = currentDate.plusDays(1) }
        )
        Spacer(Modifier.height(10.dp))
        QuoteSection(
            onClick = onQuoteClick,
            quoteText = taskViewModel.dailyQuoteDisplayText("The wind rises; I keep going.")
        )
        Spacer(Modifier.height(AppSpacing.SectionMedium))

        // First-run onboarding UI temporarily disabled (restore when product-ready).
        // if (!uiState.onboardingHandled && allTasks.isEmpty()) {
        //     FirstUseOnboarding(
        //         onLoadSample = { taskViewModel.markOnboardingChoice(loadSamples = true) },
        //         onStartEmpty = { taskViewModel.markOnboardingChoice(loadSamples = false) }
        //     )
        // } else {
        // Task area scrolls independently
        Column(
            modifier = Modifier
                .weight(1f, fill = false)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.BlockGap)
        ) {

            TimeBlock(
                label = TaskConstants.TIME_BLOCK_ANYTIME,
                count = anytimeTasks.size,
                backgroundColor = TaskUtils.getTimeBlockColor(TaskConstants.TIME_BLOCK_ANYTIME),
                icon = Icons.Default.AccessTime,
                expanded = anytimeExpanded,
                onToggle = { anytimeExpanded = !anytimeExpanded },
                tasks = anytimeTasks,
                viewDate = currentDate,
                onToggleComplete = { task -> onTaskCompleteToggle(task) },
                // Edit path: card tap sets editingTask to open the bottom sheet.
                onOpenDetail = { clickedTask -> editingTask = clickedTask },
                onSwipeDelete = handleSwipeDelete,
                onTaskDragEnd = handleTaskDragEnd,
                onDragHandleY = { id, y -> dragHandleCenterY[id] = y },
                onBlockBounds = { label, rect -> blockBounds[label] = rect },
                onCreateClick = { showCreateSheet(TaskConstants.TIME_BLOCK_ANYTIME) }
            )

            TimeBlock(
                label = TaskConstants.TIME_BLOCK_MORNING,
                count = morningTasks.size,
                backgroundColor = TaskUtils.getTimeBlockColor(TaskConstants.TIME_BLOCK_MORNING),
                icon = Icons.Default.WbTwilight,
                expanded = morningExpanded,
                onToggle = { morningExpanded = !morningExpanded },
                tasks = morningTasks,
                viewDate = currentDate,
                onToggleComplete = { task -> onTaskCompleteToggle(task) },
                onOpenDetail = { clickedTask -> editingTask = clickedTask },
                onSwipeDelete = handleSwipeDelete,
                onTaskDragEnd = handleTaskDragEnd,
                onDragHandleY = { id, y -> dragHandleCenterY[id] = y },
                onBlockBounds = { label, rect -> blockBounds[label] = rect },
                onCreateClick = { showCreateSheet(TaskConstants.TIME_BLOCK_MORNING) }
            )

            TimeBlock(
                label = TaskConstants.TIME_BLOCK_AFTERNOON,
                count = afternoonTasks.size,
                backgroundColor = TaskUtils.getTimeBlockColor(TaskConstants.TIME_BLOCK_AFTERNOON),
                icon = Icons.Default.WbSunny,
                expanded = afternoonExpanded,
                onToggle = { afternoonExpanded = !afternoonExpanded },
                tasks = afternoonTasks,
                viewDate = currentDate,
                onToggleComplete = { task -> onTaskCompleteToggle(task) },
                onOpenDetail = { clickedTask -> editingTask = clickedTask },
                onSwipeDelete = handleSwipeDelete,
                onTaskDragEnd = handleTaskDragEnd,
                onDragHandleY = { id, y -> dragHandleCenterY[id] = y },
                onBlockBounds = { label, rect -> blockBounds[label] = rect },
                onCreateClick = { showCreateSheet(TaskConstants.TIME_BLOCK_AFTERNOON) }
            )

            TimeBlock(
                label = TaskConstants.TIME_BLOCK_EVENING,
                count = eveningTasks.size,
                backgroundColor = TaskUtils.getTimeBlockColor(TaskConstants.TIME_BLOCK_EVENING),
                icon = Icons.Default.DarkMode,
                expanded = eveningExpanded,
                onToggle = { eveningExpanded = !eveningExpanded },
                tasks = eveningTasks,
                viewDate = currentDate,
                onToggleComplete = { task -> onTaskCompleteToggle(task) },
                onOpenDetail = { clickedTask -> editingTask = clickedTask },
                onSwipeDelete = handleSwipeDelete,
                onTaskDragEnd = handleTaskDragEnd,
                onDragHandleY = { id, y -> dragHandleCenterY[id] = y },
                onBlockBounds = { label, rect -> blockBounds[label] = rect },
                onCreateClick = { showCreateSheet(TaskConstants.TIME_BLOCK_EVENING) }
            )

            Spacer(Modifier.height(AppSpacing.SectionXLarge))
        }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
    }

    if (showDailyLimitDialog) {
        AlertDialog(
            onDismissRequest = { showDailyLimitDialog = false },
            title = { Text("Daily to-do limit reached") },
            text = { Text("You can have at most ${TaskViewModel.DAILY_PENDING_LIMIT} incomplete tasks per day. Clear tasks for that day to add more.") },
            confirmButton = {
                TextButton(onClick = { showDailyLimitDialog = false }) {
                    Text("Got it")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        taskViewModel.deleteAllTasksForDate(currentDate)
                        showDailyLimitDialog = false
                    }
                ) {
                    Text("Clear today's tasks")
                }
            }
        )
    }

    createSheetConfig?.let { config ->
        CreateTaskBottomSheet(
            config = config,
            onDismiss = { createSheetConfig = null },
            title = createTitle,
            onTitleChange = { createTitle = it },
            description = createDescription,
            onDescriptionChange = { createDescription = it },
            meta = CreateTaskMeta(
                urgency = createUrgency,
                label = createLabel,
                emojiImage = createEmojiImage,
                localImageUri = createLocalImageUri
            ),
            onMetaChange = { meta ->
                createUrgency = meta.urgency
                createLabel = meta.label
                createEmojiImage = meta.emojiImage
                createLocalImageUri = meta.localImageUri
            },
            onTimeBlockChange = { newTimeBlock ->
                createSheetConfig = createSheetConfig?.copy(
                    timeBlock = newTimeBlock,
                    backgroundColor = TaskUtils.getTimeBlockColor(newTimeBlock),
                    titleColor = TaskUtils.getTimeBlockTitleColor(newTimeBlock)
                )
            },
            onCreateTask = { title, description, timeBlock, meta ->
                val trimmedTitle = title.trim()
                val trimmedDescription = description.trim()
                if (trimmedTitle.isEmpty()) {
                    false
                } else {
                    if (taskViewModel.wouldExceedDailyPendingLimit(currentDate, 1)) {
                        showDailyLimitDialog = true
                        false
                    } else {
                        val nextTaskId = (allTasks.maxOfOrNull { it.id } ?: 0) + 1
                        val newTask = CreateTaskParam(
                            title = trimmedTitle,
                            description = trimmedDescription,
                            timeBlock = timeBlock,
                            urgency = meta.urgency,
                            label = meta.label,
                            emojiImage = meta.emojiImage,
                            localImageUri = meta.localImageUri,
                            taskDate = currentDate
                        ).toTask(id = nextTaskId)
                        taskViewModel.saveTasks(allTasks + newTask)
                        true
                    }
                }
            }
        )
    }

    editingTask?.let { task ->
        CreateTaskBottomSheet(
            task = task,
            onDismiss = { editingTask = null },
            onSave = { updated ->
                val isStopRepeatAction =
                    task.repeatRule != TaskConstants.REPEAT_RULE_NONE &&
                        updated.repeatRule == TaskConstants.REPEAT_RULE_NONE

                if (isStopRepeatAction) {
                    // Stop repeat: clear series from this day; drop future same-series rows.
                    val updatedTasks = TaskUtils.stopRepeat(allTasks.toList(), task)
                    taskViewModel.saveTasks(updatedTasks)
                } else {
                    taskViewModel.updateTask(updated.copy(id = task.id, taskDate = task.taskDate))
                }
                editingTask = null
            },
            onDelete = { deleted ->
                taskViewModel.deleteTask(deleted)
                editingTask = null
                scope.launch {
                    when (
                        snackbarHostState.showSnackbar(
                            message = "Task deleted",
                            actionLabel = "Undo",
                            duration = SnackbarDuration.Short
                        )
                    ) {
                        SnackbarResult.ActionPerformed -> taskViewModel.undoDeleteTask()
                        SnackbarResult.Dismissed -> taskViewModel.clearDeleteUndo()
                    }
                }
            }
        )
    }

    detailTask?.let { task ->
        TaskDetailBottomSheet(
            task = task,
            onDismiss = { detailTask = null },
            onCompleteToday = {
                applyToAllTaskLists { taskList -> TaskUtils.completeToday(taskList, task) }
                detailTask = null
            },
            onCloseAll = {
                applyToAllTaskLists { taskList -> TaskUtils.closeAllOccurrences(taskList, task) }
                detailTask = null
            },
            onStopRepeat = {
                applyToAllTaskLists { taskList -> TaskUtils.stopRepeat(taskList, task) }
                detailTask = null
            }
        )
    }
}

@Composable
private fun FirstUseOnboarding(
    onLoadSample: () -> Unit,
    onStartEmpty: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "Welcome to Kairos",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppColors.PrimaryText
            )
            Text(
                text = "Welcome. Load sample tasks to explore, or start from an empty list.",
                fontSize = 14.sp,
                color = AppColors.SecondaryText
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onLoadSample() },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF8A7CF8))
                ) {
                    Text(
                        text = "Load sample data",
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 10.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onStartEmpty() },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
                ) {
                    Text(
                        text = "Start empty",
                        color = AppColors.PrimaryText,
                        modifier = Modifier.padding(vertical = 10.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBar(
    completed: Int,
    total: Int,
    onCreateClick: () -> Unit,
    onDailyReviewClick: () -> Unit
) {
    val taskText = rememberTaskTextProvider()
    // Shadow: ~8 blur, ~2dp Y, black 5%
    val shadowElevation = 4.dp
    val shadowColor = Color.Black.copy(alpha = AppInteraction.ShadowAlpha)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left: summary card with shadow
        Card(
            modifier = Modifier.shadow(
                elevation = shadowElevation,
                shape = RoundedCornerShape(16.dp),
                ambientColor = shadowColor,
                spotColor = shadowColor
            ),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = AppColors.SecondaryText,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "$completed / $total",
                    color = AppColors.PrimaryText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(Modifier.weight(1f))

        // Center: create FAB (white, black icon, shadow)
        Box(
            modifier = Modifier
                .size(36.dp)
                .shadow(
                    elevation = shadowElevation,
                    shape = CircleShape,
                    ambientColor = shadowColor,
                    spotColor = shadowColor
                )
                .clip(CircleShape)
                .background(Color.White)
                .clickable { onCreateClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = taskText.contentDescAddTask,
                tint = Color.Black,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(Modifier.width(8.dp))

        // Right: Daily Review entry (shadow)
        Box(
            modifier = Modifier
                .size(36.dp)
                .shadow(
                    elevation = shadowElevation,
                    shape = CircleShape,
                    ambientColor = shadowColor,
                    spotColor = shadowColor
                )
                .clip(CircleShape)
                .background(Color.White)
                .clickable { onDailyReviewClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.MoreHoriz,
                contentDescription = "Daily Review",
                tint = AppColors.PrimaryText,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

@Composable
private fun DateSection(
    currentDate: LocalDate,
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    val dayOfWeek = currentDate.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
    val month = currentDate.month.name.lowercase().replaceFirstChar { it.uppercase() }
    val dayOfMonth = currentDate.dayOfMonth
    val year = currentDate.year
    val dayWithSuffix = when {
        dayOfMonth % 100 in 11..13 -> "${dayOfMonth}th"
        dayOfMonth % 10 == 1 -> "${dayOfMonth}st"
        dayOfMonth % 10 == 2 -> "${dayOfMonth}nd"
        dayOfMonth % 10 == 3 -> "${dayOfMonth}rd"
        else -> "${dayOfMonth}th"
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ArrowButton(
            onClick = onPrevious,
            direction = ArrowDirection.LEFT,
            size = 32.dp,
            tint = AppColors.PrimaryText,
            contentDescription = "Previous day"
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = dayOfWeek,
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.PrimaryText,
                lineHeight = 48.sp
            )
            Text(
                text = "$month $dayWithSuffix, $year",
                fontSize = 16.sp,
                color = AppColors.SecondaryText
            )
        }
        ArrowButton(
            onClick = onNext,
            direction = ArrowDirection.RIGHT,
            size = 32.dp,
            tint = AppColors.PrimaryText,
            contentDescription = "Next day"
        )
    }
}

@Composable
private fun QuoteSection(onClick: () -> Unit, quoteText: String) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Transparent)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() }
            .padding(horizontal = 2.dp)
            .alpha(if (isPressed) 0.8f else 1f)
    ) {
        HorizontalDivider(color = AppColors.Divider, thickness = 1.dp)
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.FormatQuote,
                contentDescription = null,
                tint = AppColors.SecondaryText,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = quoteText,
                fontSize = 14.sp,
                color = AppColors.SecondaryText
            )
        }
        Spacer(Modifier.height(8.dp))
        HorizontalDivider(color = AppColors.Divider, thickness = 1.dp)
    }
}

private fun resolveTimeBlockAtY(endY: Float, bounds: Map<String, Rect>): String? {
    if (bounds.isEmpty()) return null
    bounds.entries.firstOrNull { (_, r) -> endY >= r.top && endY <= r.bottom }?.key?.let { return it }
    return bounds.minByOrNull { (_, r) ->
        val mid = (r.top + r.bottom) / 2f
        abs(endY - mid)
    }?.key
}

private fun moveTaskToTimeBlock(
    allTasks: List<Task>,
    task: Task,
    newBlock: String,
    date: LocalDate
): List<Task> {
    val updated = task.copy(timeBlock = newBlock)
    val firstIdx = allTasks.indexOfFirst { it.taskDate == date }
    val lastIdx = allTasks.indexOfLast { it.taskDate == date }
    if (firstIdx == -1) return allTasks
    val before = allTasks.take(firstIdx)
    val daySlice = allTasks.slice(firstIdx..lastIdx).filter { it.id != task.id }.toMutableList()
    val order = TaskConstants.TIME_BLOCKS
    val newRank = order.indexOf(newBlock)
    val insertAt = daySlice.indexOfLast { it.timeBlock == newBlock }.let { lastInBlock ->
        if (lastInBlock == -1) {
            daySlice.indexOfFirst { order.indexOf(it.timeBlock) > newRank }
                .let { ix -> if (ix == -1) daySlice.size else ix }
        } else {
            lastInBlock + 1
        }
    }
    daySlice.add(insertAt, updated)
    return before + daySlice + allTasks.drop(lastIdx + 1)
}

/** Completed or overdue (before today) tasks cannot swipe-delete until moved back to today. */
private fun Task.isSwipeDeletableByPolicy(today: LocalDate = LocalDate.now()): Boolean {
    if (isCompleted) return false
    if (taskDate.isBefore(today)) return false
    return true
}

@Composable
private fun TimeBlock(
    label: String,
    count: Int,
    backgroundColor: Color,
    icon: ImageVector,
    expanded: Boolean,
    onToggle: () -> Unit,
    tasks: List<Task>,
    viewDate: LocalDate,
    onToggleComplete: (Task) -> Unit,
    onOpenDetail: (Task) -> Unit,
    onSwipeDelete: (Task) -> Unit,
    onTaskDragEnd: (Task, Float) -> Unit,
    onDragHandleY: (Int, Float) -> Unit,
    onBlockBounds: (String, Rect) -> Unit,
    onCreateClick: () -> Unit
) {
    val taskText = rememberTaskTextProvider()
    val hasTasks = count > 0

    Column(
        modifier = Modifier.onGloballyPositioned { coords ->
            onBlockBounds(label, coords.boundsInRoot())
        }
    ) {
        // Box keeps header layout stable
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = backgroundColor.copy(alpha = 0.8f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .height(32.dp)
                            .clickable(enabled = hasTasks) { if (hasTasks) onToggle() }
                            .padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = AppColors.SecondaryText,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = label,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AppColors.PrimaryText
                        )
                        Spacer(Modifier.width(2.dp))
                        Text(
                            text = "($count)",
                            fontSize = 14.sp,
                            color = AppColors.SecondaryText
                        )
                        Spacer(Modifier.width(2.dp))
                        Icon(
                            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = if (expanded) "Collapse" else "Expand",
                            tint = AppColors.SecondaryText,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }

                Spacer(Modifier.weight(1f))

                // Show trailing + only when the block has tasks (matches EmptyTaskCard)
                if (hasTasks) {
                    TimeBlockAddTaskButton(
                        onClick = onCreateClick,
                        contentDescription = taskText.contentDescAddTask,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            }
        }

        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier.padding(top = 6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (!hasTasks) {
                    EmptyTaskCard(label = label, onCreateClick = onCreateClick)
                }

                tasks.forEach { task ->
                    val allowDrag = task.taskDate == viewDate && !task.isCompleted
                    TaskItemCard(
                        task = task,
                        onToggleComplete = { onToggleComplete(task) },
                        onOpenDetail = { onOpenDetail(task) },
                        enableSwipeToDelete = task.isSwipeDeletableByPolicy(),
                        onSwipeDelete = { onSwipeDelete(task) },
                        onDragAnchorYRoot = if (allowDrag) {
                            { y -> onDragHandleY(task.id, y) }
                        } else {
                            null
                        },
                        onDragVerticalEnd = if (allowDrag) {
                            { dy -> onTaskDragEnd(task, dy) }
                        } else {
                            null
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyTaskCard(
    label: String,
    onCreateClick: () -> Unit
) {
    val taskText = rememberTaskTextProvider()
    val hintText = taskText.timeBlockHint(label)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .border(
                    width = 1.dp,
                    color = AppColors.SecondaryText.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp), // symmetric horizontal padding
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = hintText,
                fontSize = 14.sp,
                color = AppColors.HintText.copy(alpha = 0.6f)
            )
            TimeBlockAddTaskButton(
                onClick = onCreateClick,
                contentDescription = taskText.contentDescAddTask
            )
        }
    }
}

@Composable
private fun TimeBlockAddTaskButton(
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color.Black.copy(alpha = 0.05f))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = contentDescription,
            tint = Color.Black.copy(alpha = 0.18f),
            modifier = Modifier.size(18.dp)
        )
    }
}

