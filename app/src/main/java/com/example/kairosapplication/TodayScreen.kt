package com.example.kairosapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import java.time.LocalDate

// 紧急程度颜色
val UrgencyColors = listOf(
    Color(0xFFFF4444),  // 红色 - 紧急
    Color(0xFFFF9800),  // 橙色 - 高
    Color(0xFFFFEB3B),  // 黄色 - 中
    Color(0xFF9E9E9E)   // 中性灰 - 低
)

// 时间块背景颜色
val TimeBlockColors = mapOf(
    "ANYTIME" to Color(0xFFF2EEE6),
    "MORNING" to Color(0xFFFFF8E6),
    "AFTERNOON" to Color(0xFFFBE1D6),
    "EVENING" to Color(0xFFECE7FF)
)

// 弹窗标题深色文字
val TimeBlockTitleColors = mapOf(
    "ANYTIME" to Color(0xFF6C5C4A),
    "MORNING" to Color(0xFF8A6E2F),
    "AFTERNOON" to Color(0xFF9B5A40),
    "EVENING" to Color(0xFF5C4F96)
)

data class Task(
    val id: Int,
    val title: String,
    val urgency: Int,  // 0-3 表示紧急程度
    var completed: Boolean = false
)

private data class CreateSheetConfig(
    val timeBlock: String,
    val backgroundColor: Color,
    val titleColor: Color
)

@Composable
fun TodayScreen(
    onCreateClick: () -> Unit = {},
    onDailyReviewClick: () -> Unit = {}
) {
    var anytimeExpanded by remember { mutableStateOf(true) }
    var morningExpanded by remember { mutableStateOf(true) }
    var afternoonExpanded by remember { mutableStateOf(true) }
    var eveningExpanded by remember { mutableStateOf(true) }

    var currentDate by remember { mutableStateOf(LocalDate.now()) }
    val isToday = currentDate == LocalDate.now()

    val afternoonTasks = remember {
        mutableStateListOf(
            Task(1, "Learn Figma", 0),
            Task(2, "Write a PRD", 1),
            Task(3, "Learn SQL", 2, completed = true)
        )
    }

    val eveningTasks = remember {
        mutableStateListOf(
            Task(4, "Reading", 1),
            Task(5, "Practice Kotlin", 3)
        )
    }

    // derivedStateOf 追踪 SnapshotStateList 内容变化，item 修改时自动重算排序
    val sortedAfternoonTasks by remember {
        derivedStateOf {
            afternoonTasks.sortedWith(compareBy({ it.completed }, { it.urgency }, { it.id }))
        }
    }

    val sortedEveningTasks by remember {
        derivedStateOf {
            eveningTasks.sortedWith(compareBy({ it.completed }, { it.urgency }, { it.id }))
        }
    }

    // TopBar 汇总数据：实时追踪所有任务列表的完成状态（非今天显示 0）
    val totalCount by remember {
        derivedStateOf { if (currentDate == LocalDate.now()) afternoonTasks.size + eveningTasks.size else 0 }
    }
    val completedCount by remember {
        derivedStateOf { if (currentDate == LocalDate.now()) afternoonTasks.count { it.completed } + eveningTasks.count { it.completed } else 0 }
    }
    var createSheetConfig by remember { mutableStateOf<CreateSheetConfig?>(null) }
    // 弹窗输入内容上提：关闭弹窗时不清空，保留用户已输入文本
    var createTitle by remember { mutableStateOf("") }
    var createDescription by remember { mutableStateOf("") }

    // 统一时间块创建入口：按点击块动态切换弹窗标题与配色
    val showCreateSheet: (String) -> Unit = { timeBlock ->
        createSheetConfig = CreateSheetConfig(
            timeBlock = timeBlock,
            backgroundColor = TimeBlockColors[timeBlock] ?: Color(0xFFF2EEE6),
            titleColor = TimeBlockTitleColors[timeBlock] ?: PrimaryTextColor
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        // 固定头部区域（不随内容滚动）
        Spacer(Modifier.height(8.dp))
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
        QuoteSection()
        Spacer(Modifier.height(10.dp))

        // 任务区域独立滚动
        Column(
            modifier = Modifier
                .weight(1f, fill = false)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            TimeBlock(
                label = "ANYTIME",
                count = 0,
                backgroundColor = TimeBlockColors["ANYTIME"] ?: Color(0xFFF2EEE6),
                icon = Icons.Default.AccessTime,
                expanded = anytimeExpanded,
                onToggle = { anytimeExpanded = !anytimeExpanded },
                tasks = emptyList(),
                onToggleComplete = {},
                onCreateClick = { showCreateSheet("ANYTIME") }
            )

            TimeBlock(
                label = "MORNING",
                count = 0,
                backgroundColor = TimeBlockColors["MORNING"] ?: Color(0xFFFFF8E6),
                icon = Icons.Default.WbTwilight,
                expanded = morningExpanded,
                onToggle = { morningExpanded = !morningExpanded },
                tasks = emptyList(),
                onToggleComplete = {},
                onCreateClick = { showCreateSheet("MORNING") }
            )

            TimeBlock(
                label = "AFTERNOON",
                count = if (isToday) afternoonTasks.size else 0,
                backgroundColor = TimeBlockColors["AFTERNOON"] ?: Color(0xFFFED7C7),
                icon = Icons.Default.WbSunny,
                expanded = afternoonExpanded,
                onToggle = { afternoonExpanded = !afternoonExpanded },
                tasks = if (isToday) sortedAfternoonTasks else emptyList(),
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
                backgroundColor = TimeBlockColors["EVENING"] ?: Color(0xFFE0DBFF),
                icon = Icons.Default.DarkMode,
                expanded = eveningExpanded,
                onToggle = { eveningExpanded = !eveningExpanded },
                tasks = if (isToday) sortedEveningTasks else emptyList(),
                onToggleComplete = { task ->
                    val index = eveningTasks.indexOfFirst { it.id == task.id }
                    if (index != -1) {
                        eveningTasks[index] = eveningTasks[index].copy(completed = !eveningTasks[index].completed)
                    }
                },
                onCreateClick = { showCreateSheet("EVENING") }
            )

            Spacer(Modifier.height(16.dp))
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
            onCreateTask = { _, _, _ ->
                // 预留创建任务回调，后续接入真实存储逻辑
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
    val shadowColor = Color.Black.copy(alpha = 0.05f)

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
                    tint = SecondaryTextColor,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "$completed / $total",
                    color = PrimaryTextColor,
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
                tint = PrimaryTextColor,
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
            tint = PrimaryTextColor,
            modifier = Modifier.size(32.dp).clickable { onPrevious() }
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = dayOfWeek,
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryTextColor,
                lineHeight = 48.sp
            )
            Text(
                text = "$month $dayWithSuffix, $year",
                fontSize = 16.sp,
                color = SecondaryTextColor
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Next day",
            tint = PrimaryTextColor,
            modifier = Modifier.size(32.dp).clickable { onNext() }
        )
    }
}

@Composable
private fun QuoteSection() {
    Column {
        HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 1.dp)
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.FormatQuote,
                contentDescription = null,
                tint = SecondaryTextColor,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "纵有疾风起，人生不言弃",
                fontSize = 14.sp,
                color = SecondaryTextColor
            )
        }
        Spacer(Modifier.height(8.dp))
        HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 1.dp)
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
                            .clickable(enabled = hasTasks) { if (hasTasks) onToggle() }
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = SecondaryTextColor,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = label,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryTextColor
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "($count)",
                            fontSize = 14.sp,
                            color = SecondaryTextColor
                        )
                        Spacer(Modifier.width(4.dp))
                        Icon(
                            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = if (expanded) "Collapse" else "Expand",
                            tint = SecondaryTextColor,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(Modifier.weight(1f))

                // 每个时间块都提供创建入口
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
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.05f)),
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
                    color = Color(0xC1C1C1).copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp), // 左右padding统一
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = hintText,
                fontSize = 14.sp,
                color = Color(0x999999).copy(alpha = 0.6f)
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
    onCreateTask: (title: String, description: String, timeBlock: String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val titleFocusRequester = remember { FocusRequester() }

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
                    color = Color(0xFF666666),
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(titleFocusRequester),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus(force = true)
                        onDismiss()
                    }
                ),
                decorationBox = { innerTextField ->
                    if (title.isEmpty()) {
                        Text(
                            text = "What are you doing?",
                            fontSize = 22.sp,
                            color = Color(0xFF666666).copy(alpha = 0.7f)
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
                    color = Color(0xFF666666)
                ),
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    if (description.isEmpty()) {
                        Text(
                            text = "Describe it",
                            fontSize = 15.sp,
                            color = Color(0xFF9A9A9A)
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
                Icon(imageVector = Icons.Default.AccessTime, contentDescription = "Time icon", tint = Color(0xFF6F6F6F))
                Icon(imageVector = Icons.Default.Flag, contentDescription = "Flag icon", tint = Color(0xFF6F6F6F))
                Icon(imageVector = Icons.Default.Label, contentDescription = "Label icon", tint = Color(0xFF6F6F6F))
                Icon(imageVector = Icons.Default.AttachFile, contentDescription = "Attach icon", tint = Color(0xFF6F6F6F))
                Icon(imageVector = Icons.Default.Mic, contentDescription = "Mic icon", tint = Color(0xFF6F6F6F))
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    @Suppress("UNUSED_VARIABLE")
    val reservedCreateCallback = onCreateTask
}

@Composable
private fun TaskItemCard(
    task: Task,
    onToggleComplete: () -> Unit
) {
    val urgencyColor = UrgencyColors[task.urgency]

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
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
                .fillMaxSize()
                .clickable { onToggleComplete() }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(if (task.completed) Color(0xFFE0E0E0) else Color.Transparent)
                    .border(
                        width = 2.dp,
                        color = if (task.completed) Color(0xFFE0E0E0) else urgencyColor,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (task.completed) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Completed",
                        tint = Color(0xFF9E9E9E),
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            Spacer(Modifier.width(12.dp))

            Text(
                text = task.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = if (task.completed) Color(0xFF9E9E9E) else PrimaryTextColor,
                textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None
            )
        }
    }
}