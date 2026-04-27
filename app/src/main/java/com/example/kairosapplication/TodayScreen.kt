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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import androidx.compose.ui.draw.shadow

// 紧急程度颜色
val UrgencyColors = listOf(
    Color(0xFFFF4444),  // 红色 - 紧急
    Color(0xFFFF9800),  // 橙色 - 高
    Color(0xFFFFEB3B),  // 黄色 - 中
    Color(0xFF9E9E9E)   // 中性灰 - 低
)

// 时间块背景颜色（透明度 80%）
val TimeBlockColors = mapOf(
    "ANYTIME" to Color(0xEFE9E6),
    "MORNING" to Color(0xFFF4DC),
    "AFTERNOON" to Color(0xFBE1D6),
    "EVENING" to Color(0xECE7FF)
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

    // 示例任务数据
    val tasks = remember {
        mapOf(
            "afternoon" to listOf(
                Task(1, "Learn Figma", 0),
                Task(2, "Write a PRD", 1),
                Task(3, "Learn SQL", 2, completed = true),
                Task(4, "Design UI", 1),
                Task(5, "Review code", 0),
                Task(6, "Team meeting", 3)
            ),
            "evening" to listOf(
                Task(7, "Reading", 1),
                Task(8, "Practice Kotlin", 3),
                Task(9, "Watch tutorial", 2),
                Task(10, "Write notes", 1)
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        // 顶部固定区域（不滑动）
        Spacer(Modifier.height(8.dp))
        TopBar()
        DateSection()
        QuoteSection()
        Spacer(Modifier.height(10.dp))  // 新加：QuoteSection下面加10dp间距

        // 任务区域（可以滑动）
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // ANYTIME 时间块
            TimeBlock(
                label = "ANYTIME",
                count = 0,
                backgroundColor = TimeBlockColors["ANYTIME"] ?: Color(0xFFF2EEE6),
                icon = Icons.Default.AccessTime,
                expanded = anytimeExpanded,
                onToggle = { anytimeExpanded = !anytimeExpanded },
                tasks = emptyList()
            )

            // MORNING 时间块
            TimeBlock(
                label = "MORNING",
                count = 0,
                backgroundColor = TimeBlockColors["MORNING"] ?: Color(0xFFFFF8E6),
                icon = Icons.Default.WbTwilight,
                expanded = morningExpanded,
                onToggle = { morningExpanded = !morningExpanded },
                tasks = emptyList()
            )

            // AFTERNOON 时间块
            TimeBlock(
                label = "AFTERNOON",
                count = 3,
                backgroundColor = TimeBlockColors["AFTERNOON"] ?: Color(0xFFFED7C7),
                icon = Icons.Default.WbSunny,
                expanded = afternoonExpanded,
                onToggle = { afternoonExpanded = !afternoonExpanded },
                tasks = tasks["afternoon"] ?: emptyList()
            )

            // EVENING 时间块
            TimeBlock(
                label = "EVENING",
                count = 2,
                backgroundColor = TimeBlockColors["EVENING"] ?: Color(0xFFE0DBFF),
                icon = Icons.Default.DarkMode,
                expanded = eveningExpanded,
                onToggle = { eveningExpanded = !eveningExpanded },
                tasks = tasks["evening"] ?: emptyList()
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
                .size(36.dp)  // 减少1/3（从44dp变为36dp）
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.05f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add task",
                tint = Color.Black.copy(alpha = 0.18f),
                modifier = Modifier.size(18.dp)  // 图标也相应缩小
            )
        }

        Spacer(Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .size(36.dp)  // 减少1/3
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
            tint = PrimaryTextColor,
            modifier = Modifier.size(32.dp)  // 稍微缩小
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
            tint = PrimaryTextColor,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
private fun QuoteSection() {
    Column {
        HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 1.dp)
        Spacer(Modifier.height(8.dp))  // 减少间距
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
        Spacer(Modifier.height(8.dp))  // 减少间距
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
    tasks: List<Task>
) {
    val hasTasks = count > 0
    val buttonSize = 24.dp  // 创建按钮大小
    val buttonPadding = 16.dp  // 右侧padding，与空白待办卡片一致

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = buttonPadding),  // 与空白待办卡片右侧padding一致
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 时间块头部卡片（缩短长度）
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

            // 填充空白，使创建按钮与下方按钮对齐
            Spacer(Modifier.weight(1f))

            // 有任务时，创建按钮外置
            if (hasTasks) {
                Box(
                    modifier = Modifier
                        .size(buttonSize)
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

        // 展开的内容
        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier.padding(top = 6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // 无任务时：显示添加卡片
                if (!hasTasks) {
                    EmptyTaskCard(label = label)
                }

                // 有任务时：只显示任务列表
                tasks.forEach { task ->
                    TaskItemCard(task = task)
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
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRoundRect(
                        color = Color(0xC1C1C1).copy(alpha = 0.5f),
                        size = size,
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(12.dp.toPx()),
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(
                                floatArrayOf(7.dp.toPx(), 4.dp.toPx()),
                                0f
                            )
                        )
                    )
                }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
            }
        }
    }
}

@Composable
private fun TaskItemCard(task: Task) {
    var completed by remember { mutableStateOf(task.completed) }
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
                .clickable { completed = !completed }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 紧急程度圆圈
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(if (completed) Color(0xFFE0E0E0) else Color.Transparent)
                    .border(
                        width = 2.dp,
                        color = if (completed) Color(0xFFE0E0E0) else urgencyColor,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (completed) {
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
                color = if (completed) Color(0xFF9E9E9E) else PrimaryTextColor,
                textDecoration = if (completed) TextDecoration.LineThrough else TextDecoration.None
            )
        }
    }
}