package com.example.kairosapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.WbSunny
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.theme.AfternoonBackground
import com.example.kairosapplication.ui.theme.AnytimeBackground
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.theme.EveningBackground
import com.example.kairosapplication.ui.theme.MorningBackground
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor

@Composable
fun TodayScreen() {
    var anytimeExpanded by remember { mutableStateOf(false) }
    var morningExpanded by remember { mutableStateOf(false) }
    var afternoonExpanded by remember { mutableStateOf(true) }
    var eveningExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(Modifier.height(8.dp))
        TopBar()
        DateSection()
        QuoteSection()
        TimeBlock(
            label = "ANYTIME",
            count = 0,
            backgroundColor = AnytimeBackground,
            icon = Icons.Default.AccessTime,
            expanded = anytimeExpanded,
            onToggle = { anytimeExpanded = !anytimeExpanded }
        )
        TimeBlock(
            label = "MORNING",
            count = 0,
            backgroundColor = MorningBackground,
            icon = Icons.Default.WbSunny,
            expanded = morningExpanded,
            onToggle = { morningExpanded = !morningExpanded }
        )
        TimeBlock(
            label = "AFTERNOON",
            count = 3,
            backgroundColor = AfternoonBackground,
            icon = Icons.Default.WbSunny,
            expanded = afternoonExpanded,
            onToggle = { afternoonExpanded = !afternoonExpanded }
        )
        TimeBlock(
            label = "EVENING",
            count = 2,
            backgroundColor = EveningBackground,
            icon = Icons.Default.DarkMode,
            expanded = eveningExpanded,
            onToggle = { eveningExpanded = !eveningExpanded }
        )
        Spacer(Modifier.height(16.dp))
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
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
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
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add task",
                tint = PrimaryTextColor,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = PrimaryTextColor,
                modifier = Modifier.size(22.dp)
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
            modifier = Modifier.size(36.dp)
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "sunday",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryTextColor,
                lineHeight = 52.sp
            )
            Text(
                text = "April 19th, 2026",
                fontSize = 18.sp,
                color = SecondaryTextColor
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Next day",
            tint = PrimaryTextColor,
            modifier = Modifier.size(36.dp)
        )
    }
}

@Composable
private fun QuoteSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.FormatQuote,
                contentDescription = null,
                tint = SecondaryTextColor,
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "纵有疾风起，人生不言弃",
                fontSize = 15.sp,
                color = SecondaryTextColor
            )
        }
        Spacer(Modifier.height(12.dp))
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
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)) {
            // Header row — clickable to toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggle() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = SecondaryTextColor,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = label,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryTextColor
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "($count)",
                    fontSize = 14.sp,
                    color = SecondaryTextColor
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    tint = SecondaryTextColor,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Expandable content with animation
            AnimatedVisibility(visible = expanded) {
                Column {
                    Spacer(Modifier.height(12.dp))
                    if (count > 0) {
                        repeat(count) { index ->
                            TaskItem(index = index + 1)
                            if (index < count - 1) Spacer(Modifier.height(8.dp))
                        }
                        Spacer(Modifier.height(8.dp))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (count == 0) "暂无任务" else "",
                            fontSize = 13.sp,
                            color = SecondaryTextColor.copy(alpha = 0.6f)
                        )
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add task",
                            tint = SecondaryTextColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TaskItem(index: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.7f))
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = "Task $index",
            fontSize = 14.sp,
            color = PrimaryTextColor
        )
    }
}
