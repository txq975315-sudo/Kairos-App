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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor

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
    "AFTERNOON" to Color(0xFFFED7C7),
    "EVENING" to Color(0xFFE0DBFF)
)

data class Task(
    val id: Int,
    val title: String,
    val urgency: Int,  // 0-3 表示紧急程度
    var completed: Boolean = false
)

@Composable
fun TodayScreen() {
    var anytimeExpanded by remember { mutableStateOf(true) }
    var morningExpanded by remember { mutableStateOf(true) }
    var afternoonExpanded by remember { mutableStateOf(true) }
    var eveningExpanded by remember { mutableStateOf(true) }

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        // 固定头部区域（不随内容滚动）
        Spacer(Modifier.height(8.dp))
        TopBar()
        DateSection()
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
                onToggleComplete = {}
            )

            TimeBlock(
                label = "MORNING",
                count = 0,
                backgroundColor = TimeBlockColors["MORNING"] ?: Color(0xFFFFF8E6),
                icon = Icons.Default.WbTwilight,
                expanded = morningExpanded,
                onToggle = { morningExpanded = !morningExpanded },
                tasks = emptyList(),
                onToggleComplete = {}
            )

            TimeBlock(
                label = "AFTERNOON",
                count = afternoonTasks.size,
                backgroundColor = TimeBlockColors["AFTERNOON"] ?: Color(0xFFFED7C7),
                icon = Icons.Default.WbSunny,
                expanded = afternoonExpanded,
                onToggle = { afternoonExpanded = !afternoonExpanded },
                tasks = sortedAfternoonTasks,
                onToggleComplete = { task ->
                    val index = afternoonTasks.indexOfFirst { it.id == task.id }
                    if (index != -1) {
                        afternoonTasks[index] = afternoonTasks[index].copy(completed = !afternoonTasks[index].completed)
                    }
                }
            )

            TimeBlock(
                label = "EVENING",
                count = eveningTasks.size,
                backgroundColor = TimeBlockColors["EVENING"] ?: Color(0xFFE0DBFF),
                icon = Icons.Default.DarkMode,
                expanded = eveningExpanded,
                onToggle = { eveningExpanded = !eveningExpanded },
                tasks = sortedEveningTasks,
                onToggleComplete = { task ->
                    val index = eveningTasks.indexOfFirst { it.id == task.id }
                    if (index != -1) {
                        eveningTasks[index] = eveningTasks[index].copy(completed = !eveningTasks[index].completed)
                    }
                }
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = SecondaryTextColor,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "0 / 0",
                    color = PrimaryTextColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(36.dp)
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

        Spacer(Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = PrimaryTextColor,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun DateSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "Previous day",
            tint = PrimaryTextColor.copy(alpha = 0.5f),
            modifier = Modifier.size(32.dp)
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "sunday",
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryTextColor,
                lineHeight = 48.sp
            )
            Text(
                text = "April 19th, 2026",
                fontSize = 16.sp,
                color = SecondaryTextColor
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Next day",
            tint = PrimaryTextColor.copy(alpha = 0.5f),
            modifier = Modifier.size(32.dp)
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
    onToggleComplete: (Task) -> Unit
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

                // 创建按钮 - 用固定的右边距
                if (hasTasks) {
                    Box(
                        modifier = Modifier.padding(end = 16.dp), // 这里设置右边距
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
        }

        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier.padding(top = 6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (!hasTasks) {
                    EmptyTaskCard(label = label)
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
private fun EmptyTaskCard(label: String) {
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