package com.example.kairosapplication.ui.view

import android.app.Application
import android.os.SystemClock
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.ui.EssayChangeTopicDialog
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import com.example.kairosapplication.ui.components.CreateSheetConfig
import com.example.kairosapplication.ui.components.CreateTaskBottomSheet
import com.example.kairosapplication.ui.components.CreateTaskMeta
import com.example.kairosapplication.ui.view.month.MonthTab
import com.example.kairosapplication.ui.view.today.TodayTab
import com.example.kairosapplication.ui.view.week.WeekSwitcher
import com.example.kairosapplication.ui.view.week.WeekTab
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.CreateTaskParam
import com.example.taskmodel.util.TaskUtils
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.LocalDate

@Composable
fun ViewScreen(
    onRequestOpenToday: () -> Unit,
    onRequestOpenEssay: () -> Unit,
    onRequestOpenEssayTopic: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val application = LocalContext.current.applicationContext as Application
    val taskViewModel: TaskViewModel = viewModel(factory = TaskViewModel.factory(application))
    val viewModel: ViewViewModel = viewModel(factory = ViewViewModel.factory(application, taskViewModel))
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val uiState by viewModel.uiState.collectAsState()
    val taskUi by taskViewModel.uiState.collectAsState()
    var expandedNoteId by remember { mutableStateOf<Long?>(null) }
    var changeTopicNoteId by remember { mutableStateOf<Long?>(null) }
    var deleteConfirmNoteId by remember { mutableStateOf<Long?>(null) }
    val projectsById = uiState.noteProjects.associate { it.id to it.name }
    var createSheetConfig by remember { mutableStateOf<CreateSheetConfig?>(null) }
    var quickCreateTaskDate by remember { mutableStateOf<LocalDate?>(null) }
    var createTitle by remember { mutableStateOf("") }
    var createDescription by remember { mutableStateOf("") }
    var createUrgency by remember { mutableStateOf(3) }
    var createLabel by remember { mutableStateOf<String?>(null) }
    var createEmojiImage by remember { mutableStateOf<String?>(null) }
    var createLocalImageUri by remember { mutableStateOf<String?>(null) }
    var showWeekCreateLimitDialog by remember { mutableStateOf(false) }
    var weekCreateLimitDate by remember { mutableStateOf<LocalDate?>(null) }
    var lastTaskToggleUptimeMs by remember { mutableLongStateOf(0L) }

    LaunchedEffect(Unit) {
        taskViewModel.syncFromCreationBus()
    }

    LaunchedEffect(selectedTabIndex) {
        expandedNoteId = null
        changeTopicNoteId = null
        deleteConfirmNoteId = null
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .statusBarsPadding()
            .padding(top = 8.dp),
    ) {
        Column(Modifier.fillMaxSize()) {
            TimeSelector(
                selectedIndex = selectedTabIndex,
                onSelect = { idx ->
                    selectedTabIndex = idx
                    if (idx == 0) {
                        viewModel.setFocusedDate(LocalDate.now())
                    }
                },
            )
            if (selectedTabIndex == 1) {
                Spacer(modifier = Modifier.height(8.dp))
                WeekSwitcher(
                    currentWeekRange = uiState.currentWeekRange,
                    viewingCurrentWeek = uiState.viewingCurrentWeek,
                    onPreviousWeek = { viewModel.switchToPreviousWeek() },
                    onNextWeek = { viewModel.switchToNextWeek() },
                    onResetToCurrentWeek = { viewModel.resetToCurrentWeek() },
                )
                Spacer(modifier = Modifier.height(8.dp))
            } else {
                Spacer(modifier = Modifier.height(12.dp))
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 0.5.dp,
                color = AppColors.Divider,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                when (selectedTabIndex) {
                    0 -> TodayTab(
                        modifier = Modifier.fillMaxSize(),
                        focusedDate = uiState.focusedDate,
                        uiState = uiState,
                        onToggleTaskComplete = { task -> taskViewModel.toggleTaskComplete(task) },
                        onPreviousDay = { viewModel.shiftFocusedDateBy(-1) },
                        onNextDay = { viewModel.shiftFocusedDateBy(1) },
                        onRequestOpenToday = onRequestOpenToday,
                        onRequestOpenEssay = onRequestOpenEssay,
                        expandedNoteId = expandedNoteId,
                        onToggleExpand = { noteId ->
                            expandedNoteId = if (expandedNoteId == noteId) null else noteId
                        },
                        onPublishedChangeTopic = { changeTopicNoteId = it },
                        onPublishedChangeProject = { },
                        onPublishedContinueCreate = { },
                        onPublishedDelete = { deleteConfirmNoteId = it },
                        projectsById = projectsById,
                    )
                    1 -> WeekTab(
                        modifier = Modifier.fillMaxSize(),
                        uiState = uiState,
                        onToggleTaskComplete = { task -> taskViewModel.toggleTaskComplete(task) },
                        onAddTaskForDate = { date ->
                            quickCreateTaskDate = date
                            createTitle = ""
                            createDescription = ""
                            createUrgency = TaskConstants.URGENCY_LOW
                            createLabel = null
                            createEmojiImage = null
                            createLocalImageUri = null
                            createSheetConfig = CreateSheetConfig(
                                timeBlock = TaskConstants.TIME_BLOCK_ANYTIME,
                                backgroundColor = TaskUtils.getTimeBlockColor(TaskConstants.TIME_BLOCK_ANYTIME),
                                titleColor = TaskUtils.getTimeBlockTitleColor(TaskConstants.TIME_BLOCK_ANYTIME),
                            )
                        },
                        expandedNoteId = expandedNoteId,
                        onToggleExpand = { noteId ->
                            expandedNoteId = if (expandedNoteId == noteId) null else noteId
                        },
                        onPublishedChangeTopic = { changeTopicNoteId = it },
                        onPublishedChangeProject = { },
                        onPublishedContinueCreate = { },
                        onPublishedDelete = { deleteConfirmNoteId = it },
                        projectsById = projectsById,
                    )
                    else -> MonthTab(
                        modifier = Modifier.fillMaxSize(),
                        uiState = uiState,
                        yearMonth = uiState.calendarYearMonth,
                        onDateClick = { d ->
                            viewModel.setFocusedDate(d)
                            selectedTabIndex = 0
                        },
                        onMonthChange = { viewModel.setCalendarYearMonth(it) },
                    )
                }
            }
        }

        changeTopicNoteId?.let { nid ->
            taskUi.notePublished.find { it.id == nid }?.let { note ->
                EssayChangeTopicDialog(
                    note = note,
                    customSecondaryCategories = taskUi.customSecondaryCategories,
                    onDismiss = { changeTopicNoteId = null },
                    onConfirm = { pri, sec ->
                        viewModel.updateNoteTopic(nid, pri, sec)
                        expandedNoteId = null
                        changeTopicNoteId = null
                    },
                )
            }
        }

        deleteConfirmNoteId?.let { nid ->
            AlertDialog(
                onDismissRequest = { deleteConfirmNoteId = null },
                title = { Text("Delete note?", color = PrimaryTextColor) },
                text = { Text("This note will be moved to trash.", color = SecondaryTextColor) },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.deleteNote(nid)
                            expandedNoteId = null
                            deleteConfirmNoteId = null
                        },
                    ) {
                        Text("Delete", color = PrimaryTextColor)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { deleteConfirmNoteId = null }) {
                        Text("Cancel", color = SecondaryTextColor)
                    }
                },
            )
        }

        createSheetConfig?.let { config ->
            CreateTaskBottomSheet(
                config = config,
                onDismiss = {
                    createSheetConfig = null
                    quickCreateTaskDate = null
                },
                title = createTitle,
                onTitleChange = { createTitle = it },
                description = createDescription,
                onDescriptionChange = { createDescription = it },
                meta = CreateTaskMeta(
                    urgency = createUrgency,
                    label = createLabel,
                    emojiImage = createEmojiImage,
                    localImageUri = createLocalImageUri,
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
                        titleColor = TaskUtils.getTimeBlockTitleColor(newTimeBlock),
                    )
                },
                onCreateTask = { title, description, timeBlock, meta ->
                    val trimmedTitle = title.trim()
                    if (trimmedTitle.isEmpty()) {
                        false
                    } else {
                        val targetDate = quickCreateTaskDate ?: LocalDate.now()
                        if (taskViewModel.wouldExceedDailyPendingLimit(targetDate, 1)) {
                            weekCreateLimitDate = targetDate
                            showWeekCreateLimitDialog = true
                            false
                        } else {
                            val allTasks = taskViewModel.uiState.value.tasks
                            val nextTaskId = (allTasks.maxOfOrNull { it.id } ?: 0) + 1
                            val newTask = CreateTaskParam(
                                title = trimmedTitle,
                                description = description.trim(),
                                timeBlock = timeBlock,
                                urgency = meta.urgency,
                                label = meta.label,
                                emojiImage = meta.emojiImage,
                                localImageUri = meta.localImageUri,
                                taskDate = targetDate,
                            ).toTask(id = nextTaskId)
                            taskViewModel.saveTasks(allTasks + newTask)
                            true
                        }
                    }
                },
            )
        }

        if (showWeekCreateLimitDialog && weekCreateLimitDate != null) {
            val limitDate = weekCreateLimitDate!!
            AlertDialog(
                onDismissRequest = { showWeekCreateLimitDialog = false },
                title = { Text("Daily to-do limit reached", color = PrimaryTextColor) },
                text = {
                    Text(
                        "You can have at most ${TaskViewModel.DAILY_PENDING_LIMIT} incomplete tasks per day. Clear tasks for that day to add more.",
                        color = SecondaryTextColor,
                    )
                },
                confirmButton = {
                    TextButton(onClick = { showWeekCreateLimitDialog = false }) {
                        Text("Got it", color = PrimaryTextColor)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            taskViewModel.deleteAllTasksForDate(limitDate)
                            showWeekCreateLimitDialog = false
                        },
                    ) {
                        Text("Clear day's tasks", color = PrimaryTextColor)
                    }
                },
            )
        }
    }
}
