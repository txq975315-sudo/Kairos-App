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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.platform.LocalLifecycleOwner

@Composable
fun TodayScreen(
    onCreateClick: () -> Unit = {},
    onDailyReviewClick: () -> Unit = {},
    onQuoteClick: () -> Unit = {}
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var anytimeExpanded by remember { mutableStateOf(true) }
    var morningExpanded by remember { mutableStateOf(true) }
    var afternoonExpanded by remember { mutableStateOf(true) }
    var eveningExpanded by remember { mutableStateOf(true) }

    var currentDate by remember { mutableStateOf(LocalDate.now()) }

    val allTasks = remember {
        mutableStateListOf(
            Task(1, "Learn Figma", description = "", timeBlock = TaskConstants.TIME_BLOCK_AFTERNOON, urgency = 0, taskDate = LocalDate.now()),
            Task(2, "Write a PRD", description = "", timeBlock = TaskConstants.TIME_BLOCK_AFTERNOON, urgency = 1, taskDate = LocalDate.now()),
            Task(3, "Learn SQL", description = "", timeBlock = TaskConstants.TIME_BLOCK_AFTERNOON, urgency = 2, isCompleted = true, taskDate = LocalDate.now()),
            Task(4, "Reading", description = "", timeBlock = TaskConstants.TIME_BLOCK_EVENING, urgency = 1, taskDate = LocalDate.now()),
            Task(5, "Practice Kotlin", description = "", timeBlock = TaskConstants.TIME_BLOCK_EVENING, urgency = 3, taskDate = LocalDate.now())
        )
    }
    val anytimeTasks by remember(currentDate, allTasks.size) {
        derivedStateOf {
            TaskUtils.sortTasks(
                allTasks.filter {
                    it.taskDate == currentDate && it.timeBlock == TaskConstants.TIME_BLOCK_ANYTIME
                }
            )
        }
    }
    val morningTasks by remember(currentDate, allTasks.size) {
        derivedStateOf {
            TaskUtils.sortTasks(
                allTasks.filter {
                    it.taskDate == currentDate && it.timeBlock == TaskConstants.TIME_BLOCK_MORNING
                }
            )
        }
    }
    val afternoonTasks by remember(currentDate, allTasks.size) {
        derivedStateOf {
            TaskUtils.sortTasks(
                allTasks.filter {
                    it.taskDate == currentDate && it.timeBlock == TaskConstants.TIME_BLOCK_AFTERNOON
                }
            )
        }
    }
    val eveningTasks by remember(currentDate, allTasks.size) {
        derivedStateOf {
            TaskUtils.sortTasks(
                allTasks.filter {
                    it.taskDate == currentDate && it.timeBlock == TaskConstants.TIME_BLOCK_EVENING
                }
            )
        }
    }
    var nextTaskId by remember { mutableStateOf(6) }

    val onTaskCompleteToggle: (Task) -> Unit = { task ->
        val index = allTasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            val current = allTasks[index]
            allTasks[index] = current.copy(isCompleted = !current.isCompleted)
        }
    }

    LaunchedEffect(Unit) {
        val incoming = TaskCreationBus.consumeAll()
        if (incoming.isNotEmpty()) {
            allTasks.addAll(incoming)
        }
    }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                val incoming = TaskCreationBus.consumeAll()
                if (incoming.isNotEmpty()) {
                    allTasks.addAll(incoming)
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    // 临时调试：跟踪列表中的 repeatRule，定位创建链路是否丢失重复规则。
    LaunchedEffect(allTasks.size) {
        allTasks.forEach { task ->
            println("TodayScreen task='${task.title}', date=${task.taskDate}, repeatRule='${task.repeatRule}'")
        }
    }

    // TopBar 汇总数据：实时追踪所有任务列表的完成状态（非今天显示 0）
    val totalCount by remember {
        derivedStateOf {
            anytimeTasks.size + morningTasks.size + afternoonTasks.size + eveningTasks.size
        }
    }
    val completedCount by remember {
        derivedStateOf {
            anytimeTasks.count { it.isCompleted } +
                morningTasks.count { it.isCompleted } +
                afternoonTasks.count { it.isCompleted } +
                eveningTasks.count { it.isCompleted }
        }
    }
    var createSheetConfig by remember { mutableStateOf<CreateSheetConfig?>(null) }
    // 弹窗输入内容上提：关闭弹窗时不清空，保留用户已输入文本
    var createTitle by remember { mutableStateOf("") }
    var createDescription by remember { mutableStateOf("") }
    var createUrgency by remember { mutableStateOf(3) }
    var createLabel by remember { mutableStateOf<String?>(null) }
    var createEmojiImage by remember { mutableStateOf<String?>(null) }
    var createLocalImageUri by remember { mutableStateOf<String?>(null) }
    var editingTask by remember { mutableStateOf<Task?>(null) }
    var detailTask by remember { mutableStateOf<Task?>(null) }

    // 统一时间块创建入口：按点击块动态切换弹窗标题与配色
    val showCreateSheet: (String) -> Unit = { timeBlock ->
        createSheetConfig = CreateSheetConfig(
            timeBlock = timeBlock,
            backgroundColor = TaskUtils.getTimeBlockColor(timeBlock),
            titleColor = TaskUtils.getTimeBlockTitleColor(timeBlock)
        )
    }

    val applyToAllTaskLists: ((List<Task>) -> List<Task>) -> Unit = { updater ->
        val updated = updater(allTasks.toList())
        allTasks.clear()
        allTasks.addAll(updated)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.ScreenBackground)
            .statusBarsPadding()
            .padding(horizontal = AppSpacing.PageHorizontal)
    ) {
        // 固定头部区域（不随内容滚动）
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
        QuoteSection(onClick = onQuoteClick)
        Spacer(Modifier.height(AppSpacing.SectionMedium))

        // 任务区域独立滚动
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
                onToggleComplete = { task -> onTaskCompleteToggle(task) },
                // 统一编辑入口：任务卡片点击后仅通过 editingTask 打开编辑弹窗。
                onOpenDetail = { clickedTask -> editingTask = clickedTask },
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
                onToggleComplete = { task -> onTaskCompleteToggle(task) },
                onOpenDetail = { clickedTask -> editingTask = clickedTask },
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
                onToggleComplete = { task -> onTaskCompleteToggle(task) },
                onOpenDetail = { clickedTask -> editingTask = clickedTask },
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
                onToggleComplete = { task -> onTaskCompleteToggle(task) },
                onOpenDetail = { clickedTask -> editingTask = clickedTask },
                onCreateClick = { showCreateSheet(TaskConstants.TIME_BLOCK_EVENING) }
            )

            Spacer(Modifier.height(AppSpacing.SectionXLarge))
        }
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
                    val newTask = CreateTaskParam(
                        title = trimmedTitle,
                        description = trimmedDescription,
                        timeBlock = timeBlock,
                        urgency = meta.urgency,
                        label = meta.label,
                        emojiImage = meta.emojiImage,
                        localImageUri = meta.localImageUri,
                        taskDate = currentDate
                    ).toTask(id = nextTaskId++)
                    allTasks.add(newTask)
                    true
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
                    // Stop 需要作用于整条重复链：当天置为 NONE，未来日期同系列任务移除。
                    val updatedTasks = TaskUtils.stopRepeat(allTasks.toList(), task)
                    allTasks.clear()
                    allTasks.addAll(updatedTasks)
                } else {
                    val index = allTasks.indexOfFirst { it.id == task.id }
                    if (index != -1) {
                        allTasks[index] = updated.copy(id = task.id, taskDate = task.taskDate)
                    }
                }
                editingTask = null
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
private fun TopBar(
    completed: Int,
    total: Int,
    onCreateClick: () -> Unit,
    onDailyReviewClick: () -> Unit
) {
    val taskText = rememberTaskTextProvider()
    // 统一阴影参数：Blur≈8, Y偏移≈2, Black 5%
    val shadowElevation = 4.dp
    val shadowColor = Color.Black.copy(alpha = AppInteraction.ShadowAlpha)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 左侧：任务数汇总卡片（加阴影）
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

        // 中间：创建按钮（白底、黑色图标、加阴影）
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

        // 右侧：Daily Review 入口（加阴影）
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
private fun QuoteSection(onClick: () -> Unit) {
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
                text = "纵有疾风起，人生不言弃",
                fontSize = 14.sp,
                color = AppColors.SecondaryText
            )
        }
        Spacer(Modifier.height(8.dp))
        HorizontalDivider(color = AppColors.Divider, thickness = 1.dp)
    }
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
    onToggleComplete: (Task) -> Unit,
    onOpenDetail: (Task) -> Unit,
    onCreateClick: () -> Unit
) {
    val taskText = rememberTaskTextProvider()
    val hasTasks = count > 0

    Column {
        // 用Box包裹头部，确保按钮位置固定
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

                // 仅在有任务时显示右侧 + 按钮；无任务时隐藏
                if (hasTasks) {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clip(CircleShape)
                            .clickable { onCreateClick() }
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(Color.Black.copy(alpha = 0.05f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = taskText.contentDescAddTask,
                                tint = Color.Black.copy(alpha = 0.18f),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
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
                    TaskItemCard(
                        task = task,
                        onToggleComplete = { onToggleComplete(task) },
                        onOpenDetail = { onOpenDetail(task) }
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
                .padding(horizontal = 16.dp), // 左右padding统一
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = hintText,
                fontSize = 14.sp,
                color = AppColors.HintText.copy(alpha = 0.6f)
            )
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.05f))
                    .clickable { onCreateClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = taskText.contentDescAddTask,
                    tint = Color.Black.copy(alpha = 0.18f),
                    modifier = Modifier.size(27.dp)
                )
            }
        }
    }
}

