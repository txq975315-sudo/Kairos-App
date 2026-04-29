package com.example.kairosapplication

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

private data class CreateSheetConfig(
    val timeBlock: String,
    val backgroundColor: Color,
    val titleColor: Color
)

private data class CreateTaskMeta(
    val urgency: Int,
    val label: String?,
    val emojiImage: String?,
    val localImageUri: String?
)

private enum class IconSheetType {
    TIME, URGENCY, LABEL, ATTACH, ATTACH_LOCAL
}

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
        DateSection(
            currentDate = currentDate,
            onPrevious = { currentDate = currentDate.minusDays(1) },
            onNext = { currentDate = currentDate.plusDays(1) }
        )
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
                onOpenDetail = { task -> detailTask = task },
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
                onOpenDetail = { task -> detailTask = task },
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
                onOpenDetail = { task -> detailTask = task },
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
                onOpenDetail = { task -> detailTask = task },
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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CreateTaskBottomSheet(
    config: CreateSheetConfig,
    onDismiss: () -> Unit,
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    meta: CreateTaskMeta,
    onMetaChange: (CreateTaskMeta) -> Unit,
    onTimeBlockChange: (String) -> Unit,
    onCreateTask: (title: String, description: String, timeBlock: String, meta: CreateTaskMeta) -> Boolean
) {
    val taskText = rememberTaskTextProvider()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val density = LocalDensity.current
    val titleFocusRequester = remember { FocusRequester() }
    var iconSheetType by remember { mutableStateOf<IconSheetType?>(null) }
    var customLabelInput by remember { mutableStateOf("") }
    var labelOptions by remember {
        mutableStateOf(
            TaskConstants.LABEL_OPTIONS.toMutableList()
        )
    }
    var isRecording by remember { mutableStateOf(false) }
    var hasSelectedTime by remember { mutableStateOf(false) }
    var hasSelectedUrgency by remember { mutableStateOf(false) }
    var keyboardHeightDp by remember { mutableStateOf(280.dp) }
    val imeBottomPx = WindowInsets.ime.getBottom(density)

    LaunchedEffect(imeBottomPx) {
        if (imeBottomPx > 0) {
            keyboardHeightDp = with(density) { imeBottomPx.toDp() }
        }
    }

    val showIconSheet: (IconSheetType) -> Unit = { type ->
        keyboardController?.hide()
        iconSheetType = type
    }

    val closeIconSheetAndRestoreKeyboard: () -> Unit = {
        iconSheetType = null
        titleFocusRequester.requestFocus()
        keyboardController?.show()
    }

    val speechLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        isRecording = false
        val matches = result.data?.getStringArrayListExtra("android.speech.extra.RESULTS")
        val text = matches?.firstOrNull()?.trim().orEmpty()
        if (text.isNotEmpty()) {
            onTitleChange(text)
        } else {
            Toast.makeText(context, taskText.toastNoSpeech, Toast.LENGTH_SHORT).show()
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            onMetaChange(meta.copy(emojiImage = null, localImageUri = uri.toString()))
            iconSheetType = null
            titleFocusRequester.requestFocus()
            keyboardController?.show()
        } else {
            // 用户取消本地图片选择时，恢复到主输入状态，避免停留在附件选择态
            iconSheetType = null
            titleFocusRequester.requestFocus()
            keyboardController?.show()
        }
    }

    // 弹窗出现时自动聚焦主输入框并拉起系统键盘
    LaunchedEffect(Unit) {
        titleFocusRequester.requestFocus()
        keyboardController?.show()
    }

    ModalBottomSheet(
        onDismissRequest = {
            keyboardController?.hide()
            focusManager.clearFocus(force = true)
            onDismiss()
        },
        sheetState = sheetState,
        containerColor = config.backgroundColor,
        contentColor = Color.Black,
        dragHandle = null,
        shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = config.timeBlock,
                modifier = Modifier.fillMaxWidth(),
                color = config.titleColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            BasicTextField(
                value = title,
                onValueChange = onTitleChange,
                textStyle = TextStyle(
                    fontSize = 22.sp,
                    color = AppColors.SecondaryText,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(titleFocusRequester),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        val success = onCreateTask(title, description, config.timeBlock, meta)
                        if (success) {
                            keyboardController?.hide()
                            focusManager.clearFocus(force = true)
                            onDismiss()
                        } else {
                            Toast.makeText(context, taskText.toastEmptyTitle, Toast.LENGTH_SHORT).show()
                            titleFocusRequester.requestFocus()
                            keyboardController?.show()
                        }
                    }
                ),
                decorationBox = { innerTextField ->
                    if (title.isEmpty()) {
                        Text(
                            text = taskText.titlePlaceholder,
                            fontSize = 22.sp,
                            color = AppColors.SecondaryText.copy(alpha = 0.7f)
                        )
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            BasicTextField(
                value = description,
                onValueChange = onDescriptionChange,
                textStyle = TextStyle(
                    fontSize = 15.sp,
                    color = AppColors.SecondaryText
                ),
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    if (description.isEmpty()) {
                        Text(
                            text = taskText.descriptionPlaceholder,
                            fontSize = 15.sp,
                            color = AppColors.HintText
                        )
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = taskText.contentDescTimeIcon,
                        tint = if (hasSelectedTime) TaskUtils.getTimeBlockTitleColor(config.timeBlock) else AppColors.IconNeutral,
                        modifier = Modifier.clickable { showIconSheet(IconSheetType.TIME) }
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Flag,
                        contentDescription = taskText.contentDescFlagIcon,
                        tint = if (hasSelectedUrgency) TaskUtils.getUrgencyColor(meta.urgency) else AppColors.IconNeutral,
                        modifier = Modifier.clickable { showIconSheet(IconSheetType.URGENCY) }
                    )
                    Spacer(Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(TaskUtils.getUrgencyColor(meta.urgency))
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Label,
                        contentDescription = taskText.contentDescLabelIcon,
                        tint = AppColors.IconNeutral,
                        modifier = Modifier.clickable { showIconSheet(IconSheetType.LABEL) }
                    )
                    if (!meta.label.isNullOrBlank()) {
                        Spacer(Modifier.width(4.dp))
                        Text(text = "# ${meta.label}", fontSize = 12.sp, color = config.titleColor)
                    }
                }
                Icon(
                    imageVector = Icons.Default.AttachFile,
                    contentDescription = taskText.contentDescAttachIcon,
                    tint = AppColors.IconNeutral,
                    modifier = Modifier.clickable { showIconSheet(IconSheetType.ATTACH) }
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = taskText.contentDescMicIcon,
                        tint = AppColors.IconNeutral,
                        modifier = Modifier.clickable {
                            isRecording = true
                            val intent = Intent("android.speech.action.RECOGNIZE_SPEECH").apply {
                                putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form")
                                putExtra("android.speech.extra.PROMPT", taskText.voicePrompt)
                            }
                            try {
                                speechLauncher.launch(intent)
                            } catch (_: Exception) {
                                isRecording = false
                                Toast.makeText(context, taskText.toastVoiceNotSupported, Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                    if (isRecording) {
                        Text(text = taskText.recording, fontSize = AppTypography.Caption, color = AppColors.Urgent)
                    }
                }
            }

            if (iconSheetType == null) {
                Spacer(modifier = Modifier.height(8.dp))
            }

            iconSheetType?.let { type ->
                // 图标选项区域替代键盘显示，保持主弹窗尺寸不变
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(keyboardHeightDp)
                        .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                        .background(config.backgroundColor)
                        .padding(top = 8.dp)
                ) {
                    when (type) {
                        IconSheetType.TIME -> {
                            TaskConstants.TIME_BLOCKS.forEach { option ->
                                OptionRow(
                                    text = option,
                                    leadingIcon = taskText.timeBlockIcons[option] ?: "•",
                                    selected = config.timeBlock == option,
                                    onClick = {
                                        hasSelectedTime = true
                                        onTimeBlockChange(option)
                                        closeIconSheetAndRestoreKeyboard()
                                    }
                                )
                            }
                        }
                        IconSheetType.URGENCY -> {
                            val options = TaskConstants.URGENCY_LEVELS
                            options.entries.forEach { entry ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            hasSelectedUrgency = true
                                            onMetaChange(meta.copy(urgency = entry.key))
                                            closeIconSheetAndRestoreKeyboard()
                                        }
                                        .padding(horizontal = 20.dp, vertical = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(12.dp)
                                            .clip(CircleShape)
                                            .background(TaskUtils.getUrgencyColor(entry.key))
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        text = entry.value,
                                        fontSize = 15.sp,
                                        color = if (meta.urgency == entry.key) AppColors.PrimaryText else AppColors.SecondaryText
                                    )
                                }
                            }
                        }
                        IconSheetType.LABEL -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                labelOptions.forEach { option ->
                                    OptionRow(
                                        text = option,
                                        leadingIcon = TaskConstants.LABEL_ICONS[option] ?: taskText.labelFallbackIcon,
                                        selected = (option != TaskConstants.LABEL_NONE && option == meta.label) ||
                                            (option == TaskConstants.LABEL_NONE && meta.label == null),
                                        onClick = {
                                            when (option) {
                                                TaskConstants.LABEL_NONE -> {
                                                    onMetaChange(meta.copy(label = null))
                                                    closeIconSheetAndRestoreKeyboard()
                                                }
                                                TaskConstants.LABEL_CREATE_NEW -> {
                                                    if (customLabelInput.isNotBlank()) {
                                                        val custom = customLabelInput.trim()
                                                        if (!labelOptions.contains(custom)) {
                                                            labelOptions.add(labelOptions.size - 1, custom)
                                                        }
                                                        onMetaChange(meta.copy(label = custom))
                                                        customLabelInput = ""
                                                        closeIconSheetAndRestoreKeyboard()
                                                    }
                                                }
                                                else -> {
                                                    onMetaChange(meta.copy(label = option))
                                                    closeIconSheetAndRestoreKeyboard()
                                                }
                                            }
                                        }
                                    )
                                    if (option == TaskConstants.LABEL_CREATE_NEW) {
                                        BasicTextField(
                                            value = customLabelInput,
                                            onValueChange = { customLabelInput = it },
                                            textStyle = TextStyle(fontSize = 15.sp, color = AppColors.PrimaryText),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 20.dp, vertical = 8.dp),
                                            decorationBox = { inner ->
                                                if (customLabelInput.isBlank()) {
                                                    Text(taskText.customLabelPlaceholder, fontSize = 15.sp, color = AppColors.SecondaryText)
                                                }
                                                inner()
                                            }
                                        )
                                    }
                                }
                            }
                        }
                        IconSheetType.ATTACH -> {
                            OptionRow(text = taskText.attachEmojiOption, selected = meta.emojiImage != null, onClick = {})
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                taskText.attachEmojis.forEach { emoji ->
                                    Text(
                                        text = emoji,
                                        fontSize = 24.sp,
                                        modifier = Modifier.clickable {
                                            onMetaChange(meta.copy(emojiImage = emoji, localImageUri = null))
                                            closeIconSheetAndRestoreKeyboard()
                                        }
                                    )
                                }
                            }
                            OptionRow(
                                text = taskText.attachLocalOption,
                                selected = meta.localImageUri != null,
                                onClick = { iconSheetType = IconSheetType.ATTACH_LOCAL }
                            )
                        }
                        IconSheetType.ATTACH_LOCAL -> {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = taskText.contentDescBack,
                                    tint = AppColors.IconNeutral,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable { closeIconSheetAndRestoreKeyboard() }
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = taskText.localImageTitle,
                                    fontSize = 15.sp,
                                    color = AppColors.IconNeutral,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            OptionRow(
                                text = taskText.openLocalImagePicker,
                                selected = false,
                                leadingIcon = taskText.localPickerIcon,
                                onClick = { imagePickerLauncher.launch(taskText.imagePickerMime) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun OptionRow(
    text: String,
    leadingIcon: String? = null,
    selected: Boolean,
    onClick: () -> Unit
) {
    val textColor = if (selected) AppColors.PrimaryText else AppColors.SecondaryText
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) {
            Text(
                text = leadingIcon,
                fontSize = 16.sp,
                color = textColor
            )
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = text,
            fontSize = 15.sp,
            color = textColor,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}
