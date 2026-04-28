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
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.viewinterop.AndroidView
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppInteraction
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.AppSize
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.core.ui.AppTypography
import java.time.LocalDate
import android.widget.Toast
import android.widget.ImageView
import kotlinx.coroutines.delay

// 紧急程度颜色
val UrgencyColors = listOf(
    AppColors.Urgent,  // 红色 - 紧急
    AppColors.High,  // 橙色 - 高
    AppColors.Normal,  // 黄色 - 中
    AppColors.Low   // 中性灰 - 低
)

// 时间块背景颜色
val TimeBlockColors = mapOf(
    "ANYTIME" to AppColors.AnytimeBackground,
    "MORNING" to AppColors.MorningBackground,
    "AFTERNOON" to AppColors.AfternoonBackground,
    "EVENING" to AppColors.EveningBackground
)

// 弹窗标题深色文字
val TimeBlockTitleColors = mapOf(
    "ANYTIME" to AppColors.AnytimeTitle,
    "MORNING" to AppColors.MorningTitle,
    "AFTERNOON" to AppColors.AfternoonTitle,
    "EVENING" to AppColors.EveningTitle
)

data class Task(
    val id: Int,
    val title: String,
    val description: String = "",
    val timeBlock: String = "ANYTIME",
    val label: String? = null,
    val emojiImage: String? = null,
    val localImageUri: String? = null,
    val urgency: Int,  // 0-3 表示紧急程度
    var completed: Boolean = false
)

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
    var anytimeExpanded by remember { mutableStateOf(true) }
    var morningExpanded by remember { mutableStateOf(true) }
    var afternoonExpanded by remember { mutableStateOf(true) }
    var eveningExpanded by remember { mutableStateOf(true) }

    var currentDate by remember { mutableStateOf(LocalDate.now()) }
    val isToday = currentDate == LocalDate.now()

    val anytimeTasks = remember {
        mutableStateListOf<Task>()
    }

    val morningTasks = remember {
        mutableStateListOf<Task>()
    }

    val afternoonTasks = remember {
        mutableStateListOf(
            Task(1, "Learn Figma", description = "", timeBlock = "AFTERNOON", urgency = 0),
            Task(2, "Write a PRD", description = "", timeBlock = "AFTERNOON", urgency = 1),
            Task(3, "Learn SQL", description = "", timeBlock = "AFTERNOON", urgency = 2, completed = true)
        )
    }

    val eveningTasks = remember {
        mutableStateListOf(
            Task(4, "Reading", description = "", timeBlock = "EVENING", urgency = 1),
            Task(5, "Practice Kotlin", description = "", timeBlock = "EVENING", urgency = 3)
        )
    }
    var nextTaskId by remember { mutableStateOf(6) }

    // TopBar 汇总数据：实时追踪所有任务列表的完成状态（非今天显示 0）
    val totalCount by remember {
        derivedStateOf {
            if (currentDate == LocalDate.now()) {
                anytimeTasks.size + morningTasks.size + afternoonTasks.size + eveningTasks.size
            } else 0
        }
    }
    val completedCount by remember {
        derivedStateOf {
            if (currentDate == LocalDate.now()) {
                anytimeTasks.count { it.completed } +
                    morningTasks.count { it.completed } +
                    afternoonTasks.count { it.completed } +
                    eveningTasks.count { it.completed }
            } else 0
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

    // 统一时间块创建入口：按点击块动态切换弹窗标题与配色
    val showCreateSheet: (String) -> Unit = { timeBlock ->
        createSheetConfig = CreateSheetConfig(
            timeBlock = timeBlock,
            backgroundColor = TimeBlockColors[timeBlock] ?: AppColors.AnytimeBackground,
            titleColor = TimeBlockTitleColors[timeBlock] ?: AppColors.PrimaryText
        )
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
                label = "ANYTIME",
                count = if (isToday) anytimeTasks.size else 0,
                backgroundColor = TimeBlockColors["ANYTIME"] ?: AppColors.AnytimeBackground,
                icon = Icons.Default.AccessTime,
                expanded = anytimeExpanded,
                onToggle = { anytimeExpanded = !anytimeExpanded },
                tasks = if (isToday) anytimeTasks else emptyList(),
                onToggleComplete = { task ->
                    val index = anytimeTasks.indexOfFirst { it.id == task.id }
                    if (index != -1) {
                        anytimeTasks[index] = anytimeTasks[index].copy(completed = !anytimeTasks[index].completed)
                    }
                },
                onCreateClick = { showCreateSheet("ANYTIME") }
            )

            TimeBlock(
                label = "MORNING",
                count = if (isToday) morningTasks.size else 0,
                backgroundColor = TimeBlockColors["MORNING"] ?: AppColors.MorningBackground,
                icon = Icons.Default.WbTwilight,
                expanded = morningExpanded,
                onToggle = { morningExpanded = !morningExpanded },
                tasks = if (isToday) morningTasks else emptyList(),
                onToggleComplete = { task ->
                    val index = morningTasks.indexOfFirst { it.id == task.id }
                    if (index != -1) {
                        morningTasks[index] = morningTasks[index].copy(completed = !morningTasks[index].completed)
                    }
                },
                onCreateClick = { showCreateSheet("MORNING") }
            )

            TimeBlock(
                label = "AFTERNOON",
                count = if (isToday) afternoonTasks.size else 0,
                backgroundColor = TimeBlockColors["AFTERNOON"] ?: AppColors.AfternoonBackground,
                icon = Icons.Default.WbSunny,
                expanded = afternoonExpanded,
                onToggle = { afternoonExpanded = !afternoonExpanded },
                tasks = if (isToday) afternoonTasks else emptyList(),
                onToggleComplete = { task ->
                    val index = afternoonTasks.indexOfFirst { it.id == task.id }
                    if (index != -1) {
                        afternoonTasks[index] = afternoonTasks[index].copy(completed = !afternoonTasks[index].completed)
                    }
                },
                onCreateClick = { showCreateSheet("AFTERNOON") }
            )

            TimeBlock(
                label = "EVENING",
                count = if (isToday) eveningTasks.size else 0,
                backgroundColor = TimeBlockColors["EVENING"] ?: AppColors.EveningBackground,
                icon = Icons.Default.DarkMode,
                expanded = eveningExpanded,
                onToggle = { eveningExpanded = !eveningExpanded },
                tasks = if (isToday) eveningTasks else emptyList(),
                onToggleComplete = { task ->
                    val index = eveningTasks.indexOfFirst { it.id == task.id }
                    if (index != -1) {
                        eveningTasks[index] = eveningTasks[index].copy(completed = !eveningTasks[index].completed)
                    }
                },
                onCreateClick = { showCreateSheet("EVENING") }
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
                    backgroundColor = TimeBlockColors[newTimeBlock] ?: AppColors.AnytimeBackground,
                    titleColor = TimeBlockTitleColors[newTimeBlock] ?: AppColors.PrimaryText
                )
            },
            onCreateTask = { title, description, timeBlock, meta ->
                val trimmedTitle = title.trim()
                val trimmedDescription = description.trim()
                if (trimmedTitle.isEmpty()) {
                    false
                } else {
                    val newTask = Task(
                        id = nextTaskId++,
                        title = trimmedTitle,
                        description = trimmedDescription,
                        timeBlock = timeBlock,
                        label = meta.label,
                        emojiImage = meta.emojiImage,
                        localImageUri = meta.localImageUri,
                        urgency = meta.urgency
                    )
                    when (timeBlock) {
                        "ANYTIME" -> anytimeTasks.add(newTask)
                        "MORNING" -> morningTasks.add(newTask)
                        "AFTERNOON" -> afternoonTasks.add(newTask)
                        "EVENING" -> eveningTasks.add(newTask)
                    }
                    true
                }
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
                contentDescription = "Add task",
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
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "Previous day",
            tint = AppColors.PrimaryText,
            modifier = Modifier.size(32.dp).clickable { onPrevious() }
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
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Next day",
            tint = AppColors.PrimaryText,
            modifier = Modifier.size(32.dp).clickable { onNext() }
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
    onCreateClick: () -> Unit
) {
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
                                contentDescription = "Add task",
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
                        onToggleComplete = { onToggleComplete(task) }
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
    val hintText = when (label) {
        "ANYTIME" -> "Anytime today works"
        "MORNING" -> "Morning today works"
        "AFTERNOON" -> "Afternoon today works"
        "EVENING" -> "Evening today works"
        else -> "Add a task"
    }

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
                    contentDescription = "Add task",
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
            mutableListOf("None", "Work", "Habit", "Study", "Life", "Exercise", "Travel", "Create New")
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
            Toast.makeText(context, "未识别到语音", Toast.LENGTH_SHORT).show()
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
                            Toast.makeText(context, "请输入任务标题", Toast.LENGTH_SHORT).show()
                            titleFocusRequester.requestFocus()
                            keyboardController?.show()
                        }
                    }
                ),
                decorationBox = { innerTextField ->
                    if (title.isEmpty()) {
                        Text(
                            text = "What are you doing?",
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
                            text = "Describe it",
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
                        contentDescription = "Time icon",
                        tint = if (hasSelectedTime) (TimeBlockTitleColors[config.timeBlock] ?: AppColors.IconNeutral) else AppColors.IconNeutral,
                        modifier = Modifier.clickable { showIconSheet(IconSheetType.TIME) }
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Flag,
                        contentDescription = "Flag icon",
                        tint = if (hasSelectedUrgency) UrgencyColors[meta.urgency] else AppColors.IconNeutral,
                        modifier = Modifier.clickable { showIconSheet(IconSheetType.URGENCY) }
                    )
                    Spacer(Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(UrgencyColors[meta.urgency])
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Label,
                        contentDescription = "Label icon",
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
                    contentDescription = "Attach icon",
                    tint = AppColors.IconNeutral,
                    modifier = Modifier.clickable { showIconSheet(IconSheetType.ATTACH) }
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = "Mic icon",
                        tint = AppColors.IconNeutral,
                        modifier = Modifier.clickable {
                            isRecording = true
                            val intent = Intent("android.speech.action.RECOGNIZE_SPEECH").apply {
                                putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form")
                                putExtra("android.speech.extra.PROMPT", "请说出任务标题")
                            }
                            try {
                                speechLauncher.launch(intent)
                            } catch (_: Exception) {
                                isRecording = false
                                Toast.makeText(context, "当前设备不支持语音识别", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                    if (isRecording) {
                        Text(text = "录音中", fontSize = AppTypography.Caption, color = AppColors.Urgent)
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
                            listOf("ANYTIME", "MORNING", "AFTERNOON", "EVENING").forEach { option ->
                                OptionRow(
                                    text = option,
                                    leadingIcon = when (option) {
                                        "ANYTIME" -> "🕒"
                                        "MORNING" -> "🌅"
                                        "AFTERNOON" -> "☀️"
                                        "EVENING" -> "🌙"
                                        else -> "•"
                                    },
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
                            val options = listOf("紧急", "重要", "普通", "低优先级")
                            options.forEachIndexed { index, option ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            hasSelectedUrgency = true
                                            onMetaChange(meta.copy(urgency = index))
                                            closeIconSheetAndRestoreKeyboard()
                                        }
                                        .padding(horizontal = 20.dp, vertical = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(12.dp)
                                            .clip(CircleShape)
                                            .background(UrgencyColors[index])
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        text = option,
                                        fontSize = 15.sp,
                                        color = if (meta.urgency == index) AppColors.PrimaryText else AppColors.SecondaryText
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
                                        leadingIcon = when (option) {
                                            "None" -> "⚪"
                                            "Work" -> "💼"
                                            "Habit" -> "🔄"
                                            "Study" -> "📚"
                                            "Life" -> "🏡"
                                            "Exercise" -> "🏃"
                                            "Travel" -> "✈️"
                                            "Create New" -> "➕"
                                            else -> "🏷️"
                                        },
                                        selected = (option != "None" && option == meta.label) || (option == "None" && meta.label == null),
                                        onClick = {
                                            when (option) {
                                                "None" -> {
                                                    onMetaChange(meta.copy(label = null))
                                                    closeIconSheetAndRestoreKeyboard()
                                                }
                                                "Create New" -> {
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
                                    if (option == "Create New") {
                                        BasicTextField(
                                            value = customLabelInput,
                                            onValueChange = { customLabelInput = it },
                                            textStyle = TextStyle(fontSize = 15.sp, color = AppColors.PrimaryText),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 20.dp, vertical = 8.dp),
                                            decorationBox = { inner ->
                                                if (customLabelInput.isBlank()) {
                                                    Text("Input custom label", fontSize = 15.sp, color = AppColors.SecondaryText)
                                                }
                                                inner()
                                            }
                                        )
                                    }
                                }
                            }
                        }
                        IconSheetType.ATTACH -> {
                            OptionRow(text = "选择 emoji 图片", selected = meta.emojiImage != null, onClick = {})
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                listOf("📅", "💼", "📚").forEach { emoji ->
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
                                text = "选择本地图片",
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
                                    contentDescription = "Back",
                                    tint = AppColors.IconNeutral,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable { closeIconSheetAndRestoreKeyboard() }
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = "Local Image",
                                    fontSize = 15.sp,
                                    color = AppColors.IconNeutral,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            OptionRow(
                                text = "Open local image picker",
                                selected = false,
                                leadingIcon = "🖼️",
                                onClick = { imagePickerLauncher.launch("image/*") }
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

@Composable
private fun TaskItemCard(
    task: Task,
    onToggleComplete: () -> Unit
) {
    val urgencyColor = UrgencyColors[task.urgency]
    val hasDescription = task.description.isNotBlank()
    val hasLabel = !task.label.isNullOrBlank()
    val hasImage = !task.emojiImage.isNullOrBlank() || !task.localImageUri.isNullOrBlank()
    val baseCardHeight = 48.dp
    val maxCardHeight = if (hasImage && (hasDescription || hasLabel)) 96.dp else baseCardHeight
    val imageSize = if (hasImage) maxCardHeight else 0.dp
    var showFiltering by remember { mutableStateOf(false) }
    if (showFiltering) {
        LaunchedEffect(Unit) {
            delay(2000)
            showFiltering = false
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.Black.copy(alpha = 0.2f),
                spotColor = Color.Black.copy(alpha = 0.2f)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = baseCardHeight, max = maxCardHeight)
                .clickable { onToggleComplete() }
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(if (task.completed) AppColors.Divider else Color.Transparent)
                    .border(
                        width = 2.dp,
                        color = if (task.completed) AppColors.Divider else urgencyColor,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (task.completed) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Completed",
                        tint = AppColors.IconNeutral,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = task.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (task.completed) AppColors.IconNeutral else AppColors.PrimaryText,
                    textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None
                )
                if (task.description.isNotBlank()) {
                    Text(
                        text = task.description,
                        fontSize = 13.sp,
                        color = AppColors.SecondaryText.copy(alpha = 0.8f),
                        textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None
                    )
                }
                if (!task.label.isNullOrBlank()) {
                    Text(
                        text = "# ${task.label}",
                        fontSize = 12.sp,
                        color = TimeBlockTitleColors[task.timeBlock] ?: AppColors.SecondaryText,
                        modifier = Modifier.clickable { showFiltering = true }
                    )
                }
                if (showFiltering) {
                    Text(
                        text = "筛选中",
                        fontSize = 12.sp,
                        color = AppColors.SecondaryText
                    )
                }
            }

            if (hasImage) {
                Spacer(Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(imageSize)
                        .clip(RoundedCornerShape(8.dp))
                        .background(AppColors.Divider.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    if (!task.emojiImage.isNullOrBlank()) {
                        Text(text = task.emojiImage, fontSize = 34.sp)
                    } else if (!task.localImageUri.isNullOrBlank()) {
                        AndroidView(
                            factory = { ctx ->
                                ImageView(ctx).apply {
                                    scaleType = ImageView.ScaleType.CENTER_CROP
                                }
                            },
                            update = { view ->
                                view.setImageURI(Uri.parse(task.localImageUri))
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}