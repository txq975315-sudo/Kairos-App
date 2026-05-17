package com.example.kairosapplication

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.core.ui.AppReviewLayout
import com.example.kairosapplication.core.ui.AppScreenHeader
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.ui.glass.GlassQuoteSection
import com.example.kairosapplication.ui.glass.GlassSurface
import com.example.kairosapplication.ui.glass.LocalGlassAtmosphereUi
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import com.example.kairosapplication.ui.glass.glassChromeTextStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.CommonBackButton
import com.example.kairosapplication.ui.components.CreateTaskBottomSheet
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task
import com.example.taskmodel.store.TaskCreationBus
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.LocalDate

@Composable
fun DailyReviewScreen(
    onBack: () -> Unit,
    isDialogMode: Boolean = false,
    allTasks: List<Task> = emptyList(),
    onTasksCreated: (List<Task>) -> Unit = { TaskCreationBus.push(it) },
    onTaskUpdated: (Task) -> Unit = {}
) {
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val tasks = remember(allTasks, lang) {
        if (allTasks.isNotEmpty()) {
            mutableStateListOf<Task>().apply { addAll(allTasks) }
        } else {
            mutableStateListOf(
                Task(
                    1001,
                    LocalizedStrings.stringFor(lang, "daily_review_demo_1", context),
                    timeBlock = TaskConstants.TIME_BLOCK_ANYTIME,
                    urgency = TaskConstants.URGENCY_HIGH,
                    taskDate = yesterday,
                ),
                Task(
                    1002,
                    LocalizedStrings.stringFor(lang, "daily_review_demo_2", context),
                    timeBlock = TaskConstants.TIME_BLOCK_AFTERNOON,
                    urgency = TaskConstants.URGENCY_NORMAL,
                    taskDate = yesterday,
                ),
                Task(
                    1003,
                    LocalizedStrings.stringFor(lang, "daily_review_demo_3", context),
                    timeBlock = TaskConstants.TIME_BLOCK_AFTERNOON,
                    urgency = TaskConstants.URGENCY_LOW,
                    isCompleted = true,
                    taskDate = yesterday,
                ),
                Task(
                    1004,
                    LocalizedStrings.stringFor(lang, "daily_review_demo_4", context),
                    timeBlock = TaskConstants.TIME_BLOCK_EVENING,
                    urgency = TaskConstants.URGENCY_LOW,
                    isCompleted = true,
                    taskDate = yesterday,
                ),
                Task(
                    1005,
                    LocalizedStrings.stringFor(lang, "daily_review_demo_5", context),
                    timeBlock = TaskConstants.TIME_BLOCK_MORNING,
                    urgency = TaskConstants.URGENCY_NORMAL,
                    isCompleted = true,
                    taskDate = yesterday,
                ),
            )
        }
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
    val chrome = LocalGlassAtmosphereUi.current.topChrome
    val useLightChrome = !LocalGlassAtmosphereUi.current.zones.topIsLight

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .statusBarsPadding()
            .padding(horizontal = AppSpacing.PageHorizontal)
    ) {
        Spacer(modifier = Modifier.height(AppSpacing.SectionSmall))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CommonBackButton(onClick = onBack)
            Text(
                text = LocalizedStrings.get("daily_review_title"),
                fontSize = AppScreenHeader.titleSp,
                fontWeight = AppScreenHeader.titleWeight,
                color = chrome.primary,
                style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                modifier = Modifier.padding(start = 8.dp),
            )
        }

        Spacer(modifier = Modifier.height(AppSpacing.SectionMedium))
        GlassQuoteSection(quote = LocalizedStrings.get("daily_review_quote"))
        Spacer(modifier = Modifier.height(AppSpacing.SectionMedium))

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ReviewSection(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                title = LocalizedStrings.get("daily_review_overdue", overdueTasks.size),
                titleColor = Color(0xFFFF6B6B),
                tasks = overdueTasks,
                selectedIds = selectedTaskIds,
                allSelected = overdueTasks.isNotEmpty() && overdueTasks.all { selectedTaskIds.contains(it.id) },
                onSelectAll = { toggleSelectAll(overdueTasks.map { it.id }) },
                selectable = true,
                onToggle = ::toggleSelect,
                onEdit = { task -> editingTask.value = task }
            )

            ReviewSection(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                title = LocalizedStrings.get("daily_review_completed", completedTasks.size),
                titleColor = GlassConstants.TextPrimary,
                tasks = completedTasks,
                selectedIds = selectedTaskIds,
                allSelected = completedTasks.isNotEmpty() && completedTasks.all { selectedTaskIds.contains(it.id) },
                onSelectAll = { toggleSelectAll(completedTasks.map { it.id }) },
                selectable = true,
                onToggle = ::toggleSelect,
                onEdit = { task -> editingTask.value = task }
            )
        }

        // Task lists vs bottom actions: extra gap before Completed (2× the 12.dp section gap)
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(bottom = if (isDialogMode) 30.dp else 36.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            ReviewGlassActionButton(
                text = LocalizedStrings.get("daily_review_copy_today"),
                enabled = selectedTasks.isNotEmpty(),
                onClick = {
                    val copiedTasks = selectedTasks.map { task ->
                        task.copy(
                            id = nextTaskId.intValue++,
                            taskDate = today,
                            isCompleted = false,
                        )
                    }
                    onTasksCreated(copiedTasks)
                    selectedTaskIds.clear()
                },
                modifier = Modifier.weight(1f, fill = false),
                accent = false,
            )
            ReviewGlassActionButton(
                text = LocalizedStrings.get("daily_review_move_today"),
                enabled = selectedOverdueTasks.isNotEmpty(),
                onClick = {
                    val movedToToday = mutableListOf<Task>()
                    for (i in tasks.indices) {
                        val task = tasks[i]
                        if (task.id in selectedTaskIds && task.taskDate == yesterday && !task.isCompleted) {
                            val movedTask = task.copy(
                                taskDate = today,
                                isCompleted = false,
                            )
                            tasks[i] = movedTask
                            movedToToday.add(movedTask)
                        }
                    }
                    movedToToday.forEach { moved -> onTaskUpdated(moved) }
                    onTasksCreated(movedToToday)
                    selectedTaskIds.clear()
                },
                modifier = Modifier.weight(1f, fill = false),
                accent = true,
            )
        }
    }

    editingTask.value?.let { task ->
        CreateTaskBottomSheet(
            task = task,
            onDismiss = { editingTask.value = null },
            onSave = { updatedTask ->
                if (TaskViewModel.wouldExceedDailyPendingLimitWhenMovingTask(
                        tasks.toList(),
                        task,
                        updatedTask.taskDate,
                    )
                ) {
                    Toast.makeText(
                        context,
                        LocalizedStrings.stringFor(
                            lang,
                            "task_daily_limit_message",
                            context,
                            TaskViewModel.DAILY_PENDING_LIMIT,
                        ),
                        Toast.LENGTH_SHORT,
                    ).show()
                    false
                } else {
                    val index = tasks.indexOfFirst { it.id == task.id }
                    if (index != -1) {
                        tasks[index] = updatedTask.copy(id = task.id)
                        onTaskUpdated(tasks[index])
                    }
                    true
                }
            }
        )
    }
}

@Composable
private fun ReviewGlassActionButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    accent: Boolean,
) {
    val cardText = LocalGlassTextColors.current
    val shape = RoundedCornerShape(GlassConstants.CornerRadius)
    GlassSurface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(enabled = enabled, onClick = onClick),
        shape = shape,
        fillAlpha = if (accent) GlassConstants.BottomNavSelectedFillAlpha else GlassConstants.PillFillAlpha,
        wrapHazeToContent = true,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 14.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = if (accent) FontWeight.Bold else FontWeight.SemiBold,
                color = when {
                    !enabled -> cardText.muted
                    accent -> Color(0xFFE8E4FF)
                    else -> cardText.primary
                },
            )
        }
    }
}

@Composable
private fun ReviewSection(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color,
    tasks: List<Task>,
    selectedIds: Collection<Int>,
    allSelected: Boolean,
    onSelectAll: () -> Unit,
    selectable: Boolean,
    onToggle: (Int) -> Unit,
    onEdit: (Task) -> Unit,
) {
    val shape = RoundedCornerShape(GlassConstants.CornerRadius)
    val cardText = LocalGlassTextColors.current
    val selectAccent = Color(0xFF8A7CF8)

    GlassSurface(
        modifier = modifier.fillMaxWidth().fillMaxHeight(),
        shape = shape,
        fillAlpha = GlassConstants.TimeBlockFillAlpha,
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = title,
                    color = titleColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .clip(CircleShape)
                        .clickable(enabled = tasks.isNotEmpty(), onClick = onSelectAll)
                        .border(
                            width = 2.dp,
                            color = if (allSelected) selectAccent else cardText.secondary,
                            shape = CircleShape,
                        )
                        .background(
                            if (allSelected) selectAccent.copy(alpha = 0.2f) else Color.Transparent,
                            CircleShape,
                        ),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .heightIn(min = AppReviewLayout.minTaskListViewport)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                tasks.forEach { task ->
                    DailyTaskCard(
                        task = task,
                        selected = selectedIds.contains(task.id),
                        selectable = selectable,
                        onToggle = { onToggle(task.id) },
                        onEdit = { onEdit(task) },
                    )
                }
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
    onEdit: () -> Unit,
) {
    val cardText = LocalGlassTextColors.current
    val selectAccent = Color(0xFF8A7CF8)
    val shape = RoundedCornerShape(GlassConstants.CornerRadius)

    GlassSurface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onEdit),
        shape = shape,
        fillAlpha = GlassConstants.TaskCardFillAlpha,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = task.title,
                color = if (task.isCompleted) cardText.muted else cardText.primary,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null,
            )
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .clickable(enabled = selectable, onClick = onToggle)
                    .border(
                        width = 2.dp,
                        color = when {
                            !selectable -> cardText.muted
                            selected -> selectAccent
                            else -> cardText.secondary
                        },
                        shape = CircleShape,
                    )
                    .background(
                        if (selectable && selected) selectAccent.copy(alpha = 0.2f) else Color.Transparent,
                        CircleShape,
                    ),
            )
        }
    }
}
