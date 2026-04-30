package com.example.kairosapplication

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.components.ArrowButton
import com.example.kairosapplication.ui.components.ArrowDirection
import com.example.kairosapplication.ui.components.CreateTaskBottomSheet
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task
import com.example.taskmodel.store.TaskCreationBus
import java.time.LocalDate

@Composable
fun DailyReviewScreen(
    onBack: () -> Unit,
    isDialogMode: Boolean = false
) {
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)
    val tasks = remember {
        mutableStateListOf(
            Task(1001, "Sport", timeBlock = TaskConstants.TIME_BLOCK_ANYTIME, urgency = TaskConstants.URGENCY_HIGH, taskDate = yesterday),
            Task(1002, "Write a PRD", timeBlock = TaskConstants.TIME_BLOCK_AFTERNOON, urgency = TaskConstants.URGENCY_NORMAL, taskDate = yesterday),
            Task(1003, "Learn SQL", timeBlock = TaskConstants.TIME_BLOCK_AFTERNOON, urgency = TaskConstants.URGENCY_LOW, isCompleted = true, taskDate = yesterday),
            Task(1004, "READING", timeBlock = TaskConstants.TIME_BLOCK_EVENING, urgency = TaskConstants.URGENCY_LOW, isCompleted = true, taskDate = yesterday),
            Task(1005, "Learn Figma", timeBlock = TaskConstants.TIME_BLOCK_MORNING, urgency = TaskConstants.URGENCY_NORMAL, isCompleted = true, taskDate = yesterday)
        )
    }
    val selectedTaskIds = remember { mutableStateListOf<Int>() }
    val nextTaskId = remember { mutableIntStateOf((tasks.maxOfOrNull { it.id } ?: 1000) + 1) }
    var editingTask = remember { mutableStateOf<Task?>(null) }

    fun toggleSelect(id: Int) {
        if (!selectedTaskIds.add(id)) selectedTaskIds.remove(id)
    }

    fun toggleSelectAll(sectionTaskIds: List<Int>) {
        if (sectionTaskIds.isEmpty()) return
        val allSelected = sectionTaskIds.all { selectedTaskIds.contains(it) }
        if (allSelected) {
            selectedTaskIds.removeAll(sectionTaskIds.toSet())
        } else {
            selectedTaskIds.addAll(sectionTaskIds)
        }
    }

    val overdueTasks = tasks.filter { it.taskDate == yesterday && !it.isCompleted }
    val completedTasks = tasks.filter { it.taskDate == yesterday && it.isCompleted }
    val selectedTasks = tasks.filter { selectedTaskIds.contains(it.id) }
    val selectedOverdueTasks = overdueTasks.filter { selectedTaskIds.contains(it.id) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ArrowButton(
                onClick = onBack,
                direction = ArrowDirection.LEFT,
                size = 36.dp,
                tint = Color(0xFF1A1A1A),
                contentDescription = "Back"
            )
            Text(
                text = "Daily Review !",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A1A),
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        QuoteSnapshot(text = "纵有疾风起，人生不言弃")
        Spacer(modifier = Modifier.height(18.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ReviewSection(
                title = "Overdue (${overdueTasks.size})",
                titleColor = Color(0xFFE53935),
                backgroundColor = Color(0x40E53935),
                borderColor = Color.Transparent,
                tasks = overdueTasks,
                    selectedIds = selectedTaskIds,
                allSelected = overdueTasks.isNotEmpty() && overdueTasks.all { selectedTaskIds.contains(it.id) },
                onSelectAll = { toggleSelectAll(overdueTasks.map { it.id }) },
                selectable = true,
                onToggle = ::toggleSelect,
                onEdit = { task -> editingTask.value = task }
            )

            ReviewSection(
                title = "Completed (${completedTasks.size})",
                titleColor = Color(0xFF1A1A1A),
                backgroundColor = Color(0x404A74FF),
                borderColor = Color(0x664A74FF),
                tasks = completedTasks,
                selectedIds = selectedTaskIds,
                allSelected = completedTasks.isNotEmpty() && completedTasks.all { selectedTaskIds.contains(it.id) },
                onSelectAll = { toggleSelectAll(completedTasks.map { it.id }) },
                selectable = true,
                onToggle = ::toggleSelect,
                onEdit = { task -> editingTask.value = task }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(bottom = if (isDialogMode) 30.dp else 36.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    val copiedTasks = selectedTasks.map { task ->
                        task.copy(
                            id = nextTaskId.intValue++,
                            taskDate = today,
                            isCompleted = false
                        )
                    }
                    TaskCreationBus.push(copiedTasks)
                    selectedTaskIds.clear()
                },
                modifier = Modifier.weight(1f),
                enabled = selectedTasks.isNotEmpty(),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFF1A1A1A)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF1A1A1A),
                    disabledContainerColor = Color(0xFFEDEDED),
                    disabledContentColor = Color(0xFF9E9E9E)
                )
            ) {
                Text("Copy to Today", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            }

            Button(
                onClick = {
                    val movedToToday = mutableListOf<Task>()
                    for (i in tasks.indices) {
                        val task = tasks[i]
                        if (task.id in selectedTaskIds && task.taskDate == yesterday && !task.isCompleted) {
                            val movedTask = task.copy(
                                taskDate = today,
                                isCompleted = false
                            )
                            tasks[i] = movedTask
                            movedToToday.add(movedTask)
                        }
                    }
                    TaskCreationBus.push(movedToToday)
                    selectedTaskIds.clear()
                },
                modifier = Modifier.weight(1f),
                enabled = selectedOverdueTasks.isNotEmpty(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8A7CF8),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFFD5D0FF),
                    disabledContentColor = Color(0xFFF5F3FF)
                )
            ) {
                Text("Move to Today", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }

    editingTask.value?.let { task ->
        CreateTaskBottomSheet(
            task = task,
            onDismiss = { editingTask.value = null },
            onSave = { updatedTask ->
                val index = tasks.indexOfFirst { it.id == task.id }
                if (index != -1) {
                    tasks[index] = updatedTask.copy(id = task.id, taskDate = task.taskDate)
                }
                editingTask.value = null
            }
        )
    }
}

@Composable
private fun QuoteSnapshot(text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp)
    ) {
        HorizontalDivider(color = Color(0xFFE3E3E3), thickness = 1.dp)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.FormatQuote,
                contentDescription = null,
                tint = Color(0xFF6B6B6B),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.size(6.dp))
            Text(text = text, fontSize = 14.sp, color = Color(0xFF5F687A))
        }
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider(color = Color(0xFFE3E3E3), thickness = 1.dp)
    }
}

@Composable
private fun ReviewSection(
    title: String,
    titleColor: Color,
    backgroundColor: Color,
    borderColor: Color,
    tasks: List<Task>,
    selectedIds: Collection<Int>,
    allSelected: Boolean,
    onSelectAll: () -> Unit,
    selectable: Boolean,
    onToggle: (Int) -> Unit,
    onEdit: (Task) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .border(1.dp, borderColor, RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = titleColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .clickable(enabled = tasks.isNotEmpty(), onClick = onSelectAll)
                    .border(
                        width = 2.dp,
                        color = if (allSelected) Color(0xFF8A7CF8) else Color(0xFF1A1A1A),
                        shape = CircleShape
                    )
                    .background(
                        if (allSelected) Color(0xFF8A7CF8).copy(alpha = 0.2f) else Color.Transparent,
                        CircleShape
                    )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tasks.forEach { task ->
                DailyTaskCard(
                    task = task,
                    selected = selectedIds.contains(task.id),
                    selectable = selectable,
                    onToggle = { onToggle(task.id) },
                    onEdit = { onEdit(task) }
                )
            }
        }
    }
}

@Composable
private fun DailyTaskCard(
    task: Task,
    selected: Boolean,
    selectable: Boolean,
    onToggle: () -> Unit,
    onEdit: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onEdit),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = task.title,
                color = Color(0xFF000000),
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .clickable(enabled = selectable, onClick = onToggle)
                    .border(
                        width = 2.dp,
                        color = if (!selectable) Color(0xFFB0B0B0) else if (selected) Color(0xFF8A7CF8) else Color(0xFF1A1A1A),
                        shape = CircleShape
                    )
                    .background(
                        if (selectable && selected) Color(0xFF8A7CF8).copy(alpha = 0.2f) else Color.Transparent,
                        CircleShape
                    )
            )
        }
    }
}
