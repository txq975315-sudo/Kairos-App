package com.example.kairosapplication.ui

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.CreateTaskParam
import com.example.taskmodel.model.Task
import com.example.taskmodel.store.TaskCreationBus
import com.example.taskmodel.util.TaskUtils
import com.example.kairosapplication.ui.components.ArrowButton
import com.example.kairosapplication.ui.components.ArrowDirection
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun CreateScreen(onBack: () -> Unit) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var selectedDates by remember { mutableStateOf(setOf(LocalDate.now())) }
    var rangeStartDate by remember { mutableStateOf<LocalDate?>(null) }
    var rangeEndDate by remember { mutableStateOf<LocalDate?>(null) }
    var dateSelectionMode by remember { mutableStateOf<DateSelectionMode?>(DateSelectionMode.SINGLE) }
    var repeatRule by remember { mutableStateOf<String?>(null) }
    var repeatRange by remember { mutableStateOf<RepeatRange?>(null) }
    var customRepeatRule by remember { mutableStateOf("") }
    var customRepeatEndDate by remember { mutableStateOf<LocalDate?>(null) }
    var titleInput by remember { mutableStateOf("") }
    var descriptionInput by remember { mutableStateOf("") }
    var selectedTimeBlock by remember { mutableStateOf(TaskConstants.TIME_BLOCK_ANYTIME) }
    var selectedUrgency by remember { mutableStateOf(TaskConstants.URGENCY_LOW) }
    var selectedLabel by remember { mutableStateOf<String?>(null) }
    var selectedSticker by remember { mutableStateOf<String?>(null) }
    var activeTool by remember { mutableStateOf<CreateTool?>(null) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val titleFocusRequester = remember { FocusRequester() }
    val availableLabels = remember {
        TaskConstants.LABEL_OPTIONS.filter {
            it != TaskConstants.LABEL_CREATE_NEW
        }
    }
    val draftTask = Task(
        id = 0,
        title = titleInput.ifBlank { "Untitled" },
        description = descriptionInput,
        timeBlock = selectedTimeBlock,
        urgency = selectedUrgency,
        label = selectedLabel,
        taskDate = selectedDate,
        emojiImage = selectedSticker
    )
    var isRecording by remember { mutableStateOf(false) }
    val speechLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        isRecording = false
        val matches = result.data?.getStringArrayListExtra("android.speech.extra.RESULTS")
        val speechText = matches?.firstOrNull()?.trim().orEmpty()
        if (speechText.isNotEmpty()) {
            titleInput = speechText
        } else {
            Toast.makeText(context, "未识别到语音", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        titleFocusRequester.requestFocus()
        keyboardController?.show()
    }

    // 根据时间选择模式处理日期点击逻辑，支持单选/多选/范围选择。
    val onCalendarDateSelected: (LocalDate) -> Unit = { clickedDate ->
        when (dateSelectionMode) {
            DateSelectionMode.SINGLE -> {
                selectedDate = clickedDate
                selectedDates = setOf(clickedDate)
                rangeStartDate = null
                rangeEndDate = null
            }
            DateSelectionMode.MULTI -> {
                selectedDate = clickedDate
                selectedDates = if (selectedDates.contains(clickedDate)) {
                    selectedDates - clickedDate
                } else {
                    selectedDates + clickedDate
                }
                if (selectedDates.isEmpty()) {
                    selectedDate = clickedDate
                }
                rangeStartDate = null
                rangeEndDate = null
            }
            DateSelectionMode.RANGE -> {
                selectedDate = clickedDate
                when {
                    rangeStartDate == null || (rangeStartDate != null && rangeEndDate != null) -> {
                        rangeStartDate = clickedDate
                        rangeEndDate = null
                        selectedDates = setOf(clickedDate)
                    }
                    rangeStartDate != null && rangeEndDate == null -> {
                        val start = minOf(rangeStartDate!!, clickedDate)
                        val end = maxOf(rangeStartDate!!, clickedDate)
                        rangeStartDate = start
                        rangeEndDate = end
                        selectedDates = generateDateSequence(start, end).toSet()
                    }
                }
            }
            null -> Unit
        }
    }

    val repeatSummary = remember(repeatRule, repeatRange, customRepeatRule, customRepeatEndDate) {
        buildRepeatSummary(
            repeatRule = repeatRule,
            repeatRange = repeatRange,
            customRepeatRule = customRepeatRule,
            customRepeatEndDate = customRepeatEndDate
        )
    }

    // 创建成功后的输入重置：保留日历与选择上下文，仅清空输入文本，方便连续创建。
    val resetInputsAfterCreate: () -> Unit = {
        titleInput = ""
        descriptionInput = ""
    }

    val submitCreateTask: () -> Unit = {
        if (titleInput.isBlank()) {
            Toast.makeText(context, "请输入任务标题", Toast.LENGTH_SHORT).show()
        } else {
            val dates = resolveCreateDates(
                selectedDate = selectedDate,
                selectedDates = selectedDates,
                repeatRule = repeatRule,
                repeatRange = repeatRange,
                customRepeatEndDate = customRepeatEndDate
            )
            val normalizedRepeatRule = normalizeRepeatRuleForSave(
                dateSelectionMode = dateSelectionMode,
                repeatRule = repeatRule,
                repeatRange = repeatRange,
                customRepeatRule = customRepeatRule
            )
            val nowSeed = System.currentTimeMillis().toInt()
            val tasks = dates.mapIndexed { index, date ->
                CreateTaskParam(
                    title = titleInput,
                    description = descriptionInput,
                    timeBlock = selectedTimeBlock,
                    urgency = selectedUrgency,
                    label = selectedLabel,
                    taskDate = date,
                    repeatRule = normalizedRepeatRule,
                    emojiImage = selectedSticker
                ).toTask(id = nowSeed + index)
            }
            TaskCreationBus.push(tasks)
            Toast.makeText(context, "Task created!", Toast.LENGTH_SHORT).show()
            resetInputsAfterCreate()
            titleFocusRequester.requestFocus()
            keyboardController?.show()
        }
    }

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
                text = "Let's plan!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A1A),
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        CalendarSection(
            currentMonth = currentMonth,
            selectedDate = selectedDate,
            selectedDates = selectedDates,
            rangeStartDate = rangeStartDate,
            rangeEndDate = rangeEndDate,
            dateSelectionMode = dateSelectionMode,
            onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
            onNextMonth = { currentMonth = currentMonth.plusMonths(1) },
            onDateSelected = onCalendarDateSelected,
            modifier = Modifier.padding(top = 16.dp)
        )

        InputField(
            value = titleInput,
            onValueChange = {
                titleInput = it
                // 输入时收起选项面板，确保键盘与图标行同时可见。
                activeTool = null
            },
            placeholder = "What are you doing?",
            modifier = Modifier.padding(top = 20.dp),
            focusRequester = titleFocusRequester,
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            )
        )

        InputField(
            value = descriptionInput,
            onValueChange = {
                descriptionInput = it
                // 输入时收起选项面板，确保键盘与图标行同时可见。
                activeTool = null
            },
            placeholder = "Describe it",
            modifier = Modifier.padding(top = 12.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            )
        )

        ActionIconsRow(
            modifier = Modifier
                .padding(top = 18.dp, bottom = 6.dp),
            selectedUrgency = selectedUrgency,
            onTimeClick = {
                activeTool = CreateTool.TIME
                keyboardController?.hide()
            },
            onUrgencyClick = {
                activeTool = CreateTool.URGENCY
                keyboardController?.hide()
            },
            onLabelClick = {
                activeTool = CreateTool.LABEL
                keyboardController?.hide()
            },
            onStickerClick = {
                activeTool = CreateTool.STICKER
                keyboardController?.hide()
            },
            onVoiceClick = {
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
            },
            onSubmit = submitCreateTask
        )

        if (isRecording) {
            Text(
                text = "录音中...",
                color = Color(0xFFFF4444),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        CreateToolPanel(
            activeTool = activeTool,
            dateSelectionMode = dateSelectionMode,
            repeatRule = repeatRule,
            repeatRange = repeatRange,
            customRepeatRule = customRepeatRule,
            customRepeatEndDate = customRepeatEndDate,
            selectedTimeBlock = selectedTimeBlock,
            selectedUrgency = selectedUrgency,
            selectedLabel = selectedLabel,
            selectedSticker = selectedSticker,
            selectedDates = selectedDates,
            rangeStartDate = rangeStartDate,
            rangeEndDate = rangeEndDate,
            labelOptions = availableLabels,
            onSelectionModeChanged = { mode ->
                // 日期选择模式与重复规则联动：
                // Single -> NONE；Multi/Range -> 至少 DAILY。
                dateSelectionMode = if (dateSelectionMode == mode) null else mode
                if (dateSelectionMode != null) {
                    repeatRange = null
                    customRepeatEndDate = null
                }
                when (dateSelectionMode) {
                    DateSelectionMode.SINGLE -> {
                        repeatRule = TaskConstants.REPEAT_RULE_NONE
                    }
                    DateSelectionMode.MULTI, DateSelectionMode.RANGE -> {
                        val currentRule = repeatRule?.trim().orEmpty()
                        if (currentRule.isEmpty() || currentRule == TaskConstants.REPEAT_RULE_NONE) {
                            repeatRule = TaskConstants.REPEAT_RULE_DAILY
                        }
                    }
                    null -> {
                        if (repeatRule == null) {
                            repeatRule = TaskConstants.REPEAT_RULE_NONE
                        }
                    }
                }
                when (dateSelectionMode) {
                    DateSelectionMode.SINGLE -> {
                        selectedDates = setOf(selectedDate)
                        rangeStartDate = null
                        rangeEndDate = null
                    }
                    DateSelectionMode.MULTI -> {
                        selectedDates = if (selectedDates.isEmpty()) setOf(selectedDate) else selectedDates
                        rangeStartDate = null
                        rangeEndDate = null
                    }
                    DateSelectionMode.RANGE -> {
                        selectedDates = setOf(selectedDate)
                        rangeStartDate = selectedDate
                        rangeEndDate = null
                    }
                    null -> {
                        selectedDates = emptySet()
                        rangeStartDate = null
                        rangeEndDate = null
                    }
                }
            },
            onRepeatRuleChanged = { rule ->
                // 允许用户先选规则再选范围：若范围为空则自动兜底为无限范围，避免 repeatRule 丢失为 NONE。
                if (repeatRange == null) {
                    repeatRange = RepeatRange.UNLIMITED
                }
                repeatRule = if (repeatRule == rule) null else rule
            },
            onRepeatRangeChanged = { range ->
                // 互斥逻辑：启用 Repeat Range 时，清空 Date Selection。
                repeatRange = if (repeatRange == range) null else range
                if (repeatRange != null) {
                    dateSelectionMode = null
                    selectedDates = emptySet()
                    rangeStartDate = null
                    rangeEndDate = null
                } else {
                    repeatRule = null
                    customRepeatEndDate = null
                }
                when (repeatRange) {
                    RepeatRange.UNLIMITED -> {
                        customRepeatEndDate = null
                    }
                    RepeatRange.NEXT_1_WEEK -> {
                        customRepeatEndDate = null
                    }
                    RepeatRange.NEXT_2_WEEKS -> {
                        customRepeatEndDate = null
                    }
                    RepeatRange.NEXT_4_WEEKS -> {
                        customRepeatEndDate = null
                    }
                    RepeatRange.THIS_MONTH -> {
                        customRepeatEndDate = null
                    }
                    RepeatRange.CUSTOM_END_DATE -> {
                        customRepeatEndDate = selectedDate
                    }
                    null -> Unit
                }
            },
            onCustomRepeatRuleChanged = { customRepeatRule = it },
            onCustomRepeatEndDateChanged = { customRepeatEndDate = it },
            onTimeSelected = { selectedTimeBlock = it },
            onUrgencySelected = { selectedUrgency = it },
            onLabelSelected = { selectedLabel = it },
            onStickerSelected = { selectedSticker = it },
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = buildDraftSummary(
                timeBlock = draftTask.timeBlock,
                urgency = draftTask.urgency,
                label = draftTask.label,
                selectedDaysCount = selectedDates.size,
                repeatRule = repeatRule
            ),
            color = Color(0xFF757575),
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
        if (dateSelectionMode == null) {
            Text(
                text = repeatSummary,
                color = Color(0xFF616161),
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

    }
}

private enum class CreateTool { TIME, URGENCY, LABEL, STICKER }
private enum class DateSelectionMode { SINGLE, MULTI, RANGE }
private enum class RepeatRange { UNLIMITED, NEXT_1_WEEK, NEXT_2_WEEKS, NEXT_4_WEEKS, THIS_MONTH, CUSTOM_END_DATE }

@Composable
private fun CalendarSection(
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    selectedDates: Set<LocalDate>,
    rangeStartDate: LocalDate?,
    rangeEndDate: LocalDate?,
    dateSelectionMode: DateSelectionMode?,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val monthLabel = remember(currentMonth) {
        currentMonth.atDay(1).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH))
    }
    val monthDays = remember(currentMonth) { buildCalendarDays(currentMonth) }
    val weekTitles = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ArrowButton(
                onClick = onPreviousMonth,
                direction = ArrowDirection.LEFT,
                size = 28.dp,
                tint = Color(0xFF1A1A1A),
                contentDescription = "Previous month"
            )
            Text(
                text = monthLabel,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1A1A1A)
            )
            ArrowButton(
                onClick = onNextMonth,
                direction = ArrowDirection.RIGHT,
                size = 28.dp,
                tint = Color(0xFF1A1A1A),
                contentDescription = "Next month"
            )
        }

        Row(modifier = Modifier.fillMaxWidth().padding(top = 6.dp)) {
            weekTitles.forEach { title ->
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color(0xFF757575),
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Column(modifier = Modifier.padding(top = 6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            monthDays.chunked(7).forEach { week ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    week.forEach { day ->
                        DayCell(
                            day = day,
                            selectedDate = selectedDate,
                            selectedDates = selectedDates,
                            rangeStartDate = rangeStartDate,
                            rangeEndDate = rangeEndDate,
                            dateSelectionMode = dateSelectionMode,
                            onDateSelected = onDateSelected
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.DayCell(
    day: LocalDate?,
    selectedDate: LocalDate,
    selectedDates: Set<LocalDate>,
    rangeStartDate: LocalDate?,
    rangeEndDate: LocalDate?,
    dateSelectionMode: DateSelectionMode?,
    onDateSelected: (LocalDate) -> Unit
) {
    val isRangeHit = if (day != null && rangeStartDate != null && rangeEndDate != null) {
        !day.isBefore(rangeStartDate) && !day.isAfter(rangeEndDate)
    } else {
        false
    }
    val isSelected = when (dateSelectionMode) {
        DateSelectionMode.SINGLE -> day == selectedDate
        DateSelectionMode.MULTI -> day != null && selectedDates.contains(day)
        DateSelectionMode.RANGE -> isRangeHit || (day == rangeStartDate) || (day == rangeEndDate)
        null -> false
    }
    val backgroundColor = if (isSelected) Color(0xFF1A1A1A) else Color.Transparent
    val textColor = if (isSelected) Color.White else Color(0xFF1A1A1A)

    Box(
        modifier = Modifier
            .weight(1f)
            .height(34.dp),
        contentAlignment = Alignment.Center
    ) {
        if (day != null) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(backgroundColor)
                    .clickable { onDateSelected(day) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day.dayOfMonth.toString(),
                    color = textColor,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    val fieldModifier = if (focusRequester == null) {
        modifier
    } else {
        modifier.focusRequester(focusRequester)
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = fieldModifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(horizontal = 14.dp, vertical = 12.dp),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        textStyle = TextStyle(fontSize = 16.sp, color = Color(0xFF1A1A1A)),
        decorationBox = { inner ->
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    color = Color(0xFF9E9E9E),
                    fontSize = 16.sp
                )
            }
            inner()
        }
    )
}

@Composable
private fun ActionIconsRow(
    modifier: Modifier = Modifier,
    selectedUrgency: Int,
    onTimeClick: () -> Unit,
    onUrgencyClick: () -> Unit,
    onLabelClick: () -> Unit,
    onStickerClick: () -> Unit,
    onVoiceClick: () -> Unit,
    onSubmit: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = "Time",
                tint = Color(0xFF757575),
                modifier = Modifier.clickable { onTimeClick() }
            )
            Icon(
                imageVector = Icons.Default.Flag,
                contentDescription = "Urgency",
                tint = TaskUtils.getUrgencyColor(selectedUrgency),
                modifier = Modifier.clickable { onUrgencyClick() }
            )
            Icon(
                imageVector = Icons.Default.Label,
                contentDescription = "Label",
                tint = Color(0xFF757575),
                modifier = Modifier.clickable { onLabelClick() }
            )
            Icon(
                imageVector = Icons.Default.AttachFile,
                contentDescription = "Sticker",
                tint = Color(0xFF757575),
                modifier = Modifier.clickable { onStickerClick() }
            )
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Voice",
                tint = Color(0xFF757575),
                modifier = Modifier.clickable { onVoiceClick() }
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Send,
            contentDescription = "Send create task",
            tint = Color(0xFF1A1A1A),
            modifier = Modifier.clickable { onSubmit() }
        )
    }
}

@Composable
private fun CreateToolPanel(
    activeTool: CreateTool?,
    dateSelectionMode: DateSelectionMode?,
    repeatRule: String?,
    repeatRange: RepeatRange?,
    customRepeatRule: String,
    customRepeatEndDate: LocalDate?,
    selectedTimeBlock: String,
    selectedUrgency: Int,
    selectedLabel: String?,
    selectedSticker: String?,
    selectedDates: Set<LocalDate>,
    rangeStartDate: LocalDate?,
    rangeEndDate: LocalDate?,
    labelOptions: List<String>,
    onSelectionModeChanged: (DateSelectionMode) -> Unit,
    onRepeatRuleChanged: (String) -> Unit,
    onRepeatRangeChanged: (RepeatRange) -> Unit,
    onCustomRepeatRuleChanged: (String) -> Unit,
    onCustomRepeatEndDateChanged: (LocalDate?) -> Unit,
    onTimeSelected: (String) -> Unit,
    onUrgencySelected: (Int) -> Unit,
    onLabelSelected: (String?) -> Unit,
    onStickerSelected: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    if (activeTool == null) return

    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 120.dp, max = 220.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            when (activeTool) {
                CreateTool.TIME -> {
                    Text(text = "Date Selection", fontSize = 13.sp, color = Color(0xFF757575))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        SelectionChip(
                            text = "Single",
                            selected = dateSelectionMode == DateSelectionMode.SINGLE,
                            onClick = { onSelectionModeChanged(DateSelectionMode.SINGLE) }
                        )
                        SelectionChip(
                            text = "Multi",
                            selected = dateSelectionMode == DateSelectionMode.MULTI,
                            onClick = { onSelectionModeChanged(DateSelectionMode.MULTI) }
                        )
                        SelectionChip(
                            text = "Range",
                            selected = dateSelectionMode == DateSelectionMode.RANGE,
                            onClick = { onSelectionModeChanged(DateSelectionMode.RANGE) }
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color(0xFFE0E0E0))
                    )

                    Text(
                        text = "Selected ${selectedDates.size} day(s) ${if (rangeStartDate != null) "from $rangeStartDate" else ""} ${if (rangeEndDate != null) "to $rangeEndDate" else ""}",
                        fontSize = 12.sp,
                        color = Color(0xFF9E9E9E)
                    )

                    Text(text = "Repeat Range", fontSize = 13.sp, color = Color(0xFF757575))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        SelectionChip(
                            text = "Infinite",
                            selected = repeatRange == RepeatRange.UNLIMITED,
                            onClick = { onRepeatRangeChanged(RepeatRange.UNLIMITED) }
                        )
                        SelectionChip(
                            text = "Next 1 week",
                            selected = repeatRange == RepeatRange.NEXT_1_WEEK,
                            onClick = { onRepeatRangeChanged(RepeatRange.NEXT_1_WEEK) }
                        )
                        SelectionChip(
                            text = "Next 2 weeks",
                            selected = repeatRange == RepeatRange.NEXT_2_WEEKS,
                            onClick = { onRepeatRangeChanged(RepeatRange.NEXT_2_WEEKS) }
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        SelectionChip(
                            text = "Next 4 weeks",
                            selected = repeatRange == RepeatRange.NEXT_4_WEEKS,
                            onClick = { onRepeatRangeChanged(RepeatRange.NEXT_4_WEEKS) }
                        )
                        SelectionChip(
                            text = "This month",
                            selected = repeatRange == RepeatRange.THIS_MONTH,
                            onClick = { onRepeatRangeChanged(RepeatRange.THIS_MONTH) }
                        )
                        SelectionChip(
                            text = "Custom end date",
                            selected = repeatRange == RepeatRange.CUSTOM_END_DATE,
                            onClick = { onRepeatRangeChanged(RepeatRange.CUSTOM_END_DATE) }
                        )
                    }
                    if (repeatRange == RepeatRange.CUSTOM_END_DATE) {
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            SelectionChip(
                                text = "今天",
                                selected = customRepeatEndDate == LocalDate.now(),
                                onClick = { onCustomRepeatEndDateChanged(LocalDate.now()) }
                            )
                            SelectionChip(
                                text = "7天后",
                                selected = customRepeatEndDate == LocalDate.now().plusDays(7),
                                onClick = { onCustomRepeatEndDateChanged(LocalDate.now().plusDays(7)) }
                            )
                            SelectionChip(
                                text = "30天后",
                                selected = customRepeatEndDate == LocalDate.now().plusDays(30),
                                onClick = { onCustomRepeatEndDateChanged(LocalDate.now().plusDays(30)) }
                            )
                        }
                    }

                    Text(text = "Repeat Rules", fontSize = 13.sp, color = Color(0xFF757575))
                    val weekdayRules = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        weekdayRules.forEach { weekday ->
                            SelectionChip(
                                text = weekday,
                                selected = repeatRule == "WEEKLY_$weekday",
                                enabled = repeatRange != null,
                                onClick = { onRepeatRuleChanged("WEEKLY_$weekday") }
                            )
                        }
                    }
                    SelectionChip(
                        text = "Custom Repeat",
                        selected = repeatRule == "CUSTOM",
                        enabled = repeatRange != null,
                        onClick = { onRepeatRuleChanged("CUSTOM") }
                    )
                    if (repeatRule == "CUSTOM" && repeatRange != null) {
                        BasicTextField(
                            value = customRepeatRule,
                            onValueChange = onCustomRepeatRuleChanged,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFF5F5F5))
                                .padding(horizontal = 10.dp, vertical = 8.dp),
                            textStyle = TextStyle(fontSize = 13.sp, color = Color(0xFF1A1A1A)),
                            decorationBox = { inner ->
                                if (customRepeatRule.isBlank()) {
                                    Text("Custom rule...", fontSize = 13.sp, color = Color(0xFF9E9E9E))
                                }
                                inner()
                            }
                        )
                    }

                    TaskConstants.TIME_BLOCKS.forEach { block ->
                        OptionPill(
                            text = block,
                            selected = selectedTimeBlock == block,
                            leadingEmoji = timeBlockEmoji(block),
                            onClick = { onTimeSelected(block) }
                        )
                    }
                }

                CreateTool.URGENCY -> {
                    TaskConstants.URGENCY_LEVELS.entries.forEach { entry ->
                        OptionPill(
                            text = entry.value,
                            selected = selectedUrgency == entry.key,
                            colorDot = TaskUtils.getUrgencyColor(entry.key),
                            onClick = { onUrgencySelected(entry.key) }
                        )
                    }
                }

                CreateTool.LABEL -> {
                    labelOptions.forEach { label ->
                        val isNone = label == TaskConstants.LABEL_NONE
                        OptionPill(
                            text = if (isNone) label else "# $label",
                            selected = if (isNone) selectedLabel == null else selectedLabel == label,
                            leadingEmoji = labelEmoji(label),
                            onClick = { onLabelSelected(if (isNone) null else label) }
                        )
                    }
                }

                CreateTool.STICKER -> {
                    val stickerOptions = listOf("📅", "💼", "📚", "🎯", "🏃")
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        stickerOptions.forEach { sticker ->
                            Text(
                                text = sticker,
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(if (selectedSticker == sticker) Color(0xFF1A1A1A) else Color.Transparent)
                                    .clickable { onStickerSelected(sticker) }
                                    .padding(6.dp)
                            )
                        }
                    }
                    OptionPill(
                        text = "Clear Sticker",
                        selected = selectedSticker == null,
                        onClick = { onStickerSelected(null) }
                    )
                }
            }
        }
    }
}

@Composable
private fun OptionPill(
    text: String,
    selected: Boolean,
    leadingEmoji: String? = null,
    colorDot: Color? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) Color(0xFFEFEFEF) else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingEmoji != null) {
            Text(
                text = leadingEmoji,
                fontSize = 15.sp
            )
            Box(modifier = Modifier.size(8.dp))
        }
        if (colorDot != null) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(colorDot)
            )
            Box(modifier = Modifier.size(8.dp))
        }
        Text(text = text, fontSize = 14.sp, color = Color(0xFF1A1A1A))
    }
}

@Composable
private fun SelectionChip(
    text: String,
    selected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        !enabled -> Color(0xFFEEEEEE)
        selected -> Color(0xFF1A1A1A)
        else -> Color(0xFFF0F0F0)
    }
    val textColor = when {
        !enabled -> Color(0xFFB0B0B0)
        selected -> Color.White
        else -> Color(0xFF4A4A4A)
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .clickable(enabled = enabled) { onClick() }
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 11.sp
        )
    }
}

private fun buildCalendarDays(month: YearMonth): List<LocalDate?> {
    val firstDay = month.atDay(1)
    val leadingEmpty = firstDay.dayOfWeek.value % DayOfWeek.SUNDAY.value
    val totalDays = month.lengthOfMonth()
    val cells = MutableList<LocalDate?>(leadingEmpty) { null }

    for (day in 1..totalDays) {
        cells.add(month.atDay(day))
    }
    while (cells.size % 7 != 0) {
        cells.add(null)
    }
    return cells
}

private fun generateDateSequence(start: LocalDate, end: LocalDate): List<LocalDate> {
    val days = mutableListOf<LocalDate>()
    var cursor = start
    while (!cursor.isAfter(end)) {
        days.add(cursor)
        cursor = cursor.plusDays(1)
    }
    return days
}

private fun buildRepeatSummary(
    repeatRule: String?,
    repeatRange: RepeatRange?,
    customRepeatRule: String,
    customRepeatEndDate: LocalDate?
): String {
    val ruleText = when {
        repeatRule == null -> "No repeat rule selected"
        repeatRule.startsWith("WEEKLY_") -> {
            val weekday = repeatRule.removePrefix("WEEKLY_")
            "Weekly $weekday"
        }
        repeatRule == "CUSTOM" -> {
            if (customRepeatRule.isBlank()) "Custom rule" else customRepeatRule
        }
        else -> "No repeat"
    }

    val rangeText = when (repeatRange) {
        RepeatRange.UNLIMITED -> "Infinite"
        RepeatRange.NEXT_1_WEEK -> "Next 1 week"
        RepeatRange.NEXT_2_WEEKS -> "Next 2 weeks"
        RepeatRange.NEXT_4_WEEKS -> "Next 4 weeks"
        RepeatRange.THIS_MONTH -> "This month"
        RepeatRange.CUSTOM_END_DATE -> "Until ${customRepeatEndDate ?: "date not set"}"
        null -> "No range selected"
    }
    return "$ruleText，$rangeText"
}

private fun buildDraftSummary(
    timeBlock: String,
    urgency: Int,
    label: String?,
    selectedDaysCount: Int,
    repeatRule: String?
): String {
    val urgencyText = when (urgency) {
        TaskConstants.URGENCY_URGENT -> "Urgent"
        TaskConstants.URGENCY_HIGH -> "High Priority"
        TaskConstants.URGENCY_NORMAL -> "Normal"
        else -> "Low Priority"
    }
    val parts = mutableListOf<String>()
    parts += timeBlock
    parts += urgencyText
    if (!label.isNullOrBlank()) {
        parts += label
    }
    if (selectedDaysCount > 0) {
        parts += "$selectedDaysCount days"
    }
    if (!repeatRule.isNullOrBlank()) {
        parts += repeatRule
    }
    return parts.joinToString(" · ")
}

private fun timeBlockEmoji(timeBlock: String): String {
    return when (timeBlock) {
        TaskConstants.TIME_BLOCK_ANYTIME -> "🕐"
        TaskConstants.TIME_BLOCK_MORNING -> "🌅"
        TaskConstants.TIME_BLOCK_AFTERNOON -> "☀️"
        TaskConstants.TIME_BLOCK_EVENING -> "🌙"
        else -> "🕐"
    }
}

private fun labelEmoji(label: String): String? {
    return when (label) {
        TaskConstants.LABEL_NONE -> "○"
        TaskConstants.LABEL_WORK -> "💼"
        TaskConstants.LABEL_HABIT -> "🎯"
        TaskConstants.LABEL_STUDY -> "📚"
        TaskConstants.LABEL_LIFE -> "🏠"
        TaskConstants.LABEL_EXERCISE -> "🏃"
        TaskConstants.LABEL_TRAVEL -> "✈️"
        else -> null
    }
}

private fun resolveCreateDates(
    selectedDate: LocalDate,
    selectedDates: Set<LocalDate>,
    repeatRule: String?,
    repeatRange: RepeatRange?,
    customRepeatEndDate: LocalDate?
): List<LocalDate> {
    val baseDates = if (selectedDates.isEmpty()) listOf(selectedDate) else selectedDates.toList().sorted()
    if (repeatRange == null) return baseDates

    val start = baseDates.minOrNull() ?: selectedDate
    val end = when (repeatRange) {
        RepeatRange.NEXT_1_WEEK -> start.plusWeeks(1)
        RepeatRange.NEXT_2_WEEKS -> start.plusWeeks(2)
        RepeatRange.NEXT_4_WEEKS -> start.plusWeeks(4)
        RepeatRange.THIS_MONTH -> start.withDayOfMonth(start.lengthOfMonth())
        RepeatRange.CUSTOM_END_DATE -> customRepeatEndDate ?: start
        RepeatRange.UNLIMITED, null -> start.plusWeeks(4)
    }
    val rangeDates = generateDateSequence(start, end)

    if (repeatRule == null || !repeatRule.startsWith("WEEKLY_")) {
        return rangeDates
    }

    val targetDay = when (repeatRule.removePrefix("WEEKLY_")) {
        "MON" -> DayOfWeek.MONDAY
        "TUE" -> DayOfWeek.TUESDAY
        "WED" -> DayOfWeek.WEDNESDAY
        "THU" -> DayOfWeek.THURSDAY
        "FRI" -> DayOfWeek.FRIDAY
        "SAT" -> DayOfWeek.SATURDAY
        "SUN" -> DayOfWeek.SUNDAY
        else -> selectedDate.dayOfWeek
    }
    return rangeDates.filter { it.dayOfWeek == targetDay }
}

private fun normalizeRepeatRuleForSave(
    dateSelectionMode: DateSelectionMode?,
    repeatRule: String?,
    repeatRange: RepeatRange?,
    customRepeatRule: String
): String {
    if (dateSelectionMode == DateSelectionMode.SINGLE) {
        return TaskConstants.REPEAT_RULE_NONE
    }

    if (dateSelectionMode == DateSelectionMode.MULTI || dateSelectionMode == DateSelectionMode.RANGE) {
        // Multi/Range 一律按重复任务保存，默认 DAILY。
        val modeRule = repeatRule?.trim().orEmpty()
        if (modeRule.isEmpty() || modeRule == TaskConstants.REPEAT_RULE_NONE) {
            return TaskConstants.REPEAT_RULE_DAILY
        }
        if (modeRule == "CUSTOM") {
            val custom = customRepeatRule.trim()
            return if (custom.isNotEmpty()) custom else TaskConstants.REPEAT_RULE_DAILY
        }
        return modeRule
    }

    if (repeatRange == null) return TaskConstants.REPEAT_RULE_NONE

    val normalizedRule = repeatRule?.trim().orEmpty()
    if (normalizedRule.isEmpty()) {
        // 选择了重复范围但未显式选择规则时，默认按每日重复保存，避免落成 NONE。
        return TaskConstants.REPEAT_RULE_DAILY
    }

    if (normalizedRule == "CUSTOM") {
        val custom = customRepeatRule.trim()
        return if (custom.isNotEmpty()) custom else TaskConstants.REPEAT_RULE_DAILY
    }

    return normalizedRule
}
