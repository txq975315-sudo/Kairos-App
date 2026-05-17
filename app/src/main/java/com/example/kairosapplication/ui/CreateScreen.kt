package com.example.kairosapplication.ui

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.key
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
import androidx.compose.ui.res.stringResource
import com.example.kairosapplication.R
import com.example.kairosapplication.core.text.TaskTextProvider
import com.example.kairosapplication.core.text.TaskUiLocalLabels
import com.example.kairosapplication.core.text.rememberTaskTextProvider
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.CreateTaskParam
import com.example.taskmodel.model.Task
import com.example.taskmodel.store.TaskCreationBus
import com.example.taskmodel.util.TaskUtils
import com.example.taskmodel.util.ColorUtils.parseHexToArgb
import com.example.kairosapplication.core.ui.LocalUrgencyConfig
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.AppScreenHeader
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.ui.glass.GlassSurface
import com.example.kairosapplication.ui.glass.LocalGlassAtmosphereUi
import com.example.kairosapplication.ui.glass.glassBottomNavDock
import com.example.kairosapplication.ui.glass.quoteDividerColor
import com.example.kairosapplication.ui.glass.GlassTextColors
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import com.example.kairosapplication.ui.glass.glassChromeTextStyle
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.weekShortHeadersMondayFirst
import com.example.kairosapplication.ui.components.ArrowButton
import com.example.kairosapplication.ui.components.ArrowDirection
import com.example.kairosapplication.ui.components.TaskReminderTimeDialog
import com.example.kairosapplication.core.ui.CommonBackButton
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateScreen(
    onBack: () -> Unit,
    onTasksCreated: (List<Task>) -> Unit = { TaskCreationBus.push(it) }
) {
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
    var reminderTime by remember { mutableStateOf<String?>(null) }
    var showReminderDialog by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val taskTexts = rememberTaskTextProvider()
    val mediumDateFormatter = remember(lang) {
        val loc = when (lang) {
            LocalizationManager.Language.ZH -> Locale.forLanguageTag("zh-Hans-CN")
            LocalizationManager.Language.EN -> Locale.ENGLISH
        }
        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(loc)
    }
    val toastCreatedMessage = stringResource(R.string.task_toast_created)
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
    val createScreenScrollState = rememberScrollState()
    val speechLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        isRecording = false
        val matches = result.data?.getStringArrayListExtra("android.speech.extra.RESULTS")
        val speechText = matches?.firstOrNull()?.trim().orEmpty()
        if (speechText.isNotEmpty()) {
            titleInput = speechText
        } else {
            Toast.makeText(context, taskTexts.toastNoSpeech, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        titleFocusRequester.requestFocus()
        keyboardController?.show()
    }

    // Date taps depend on selection mode: single / multi / range.
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

    val repeatSummary = remember(
        repeatRule,
        repeatRange,
        customRepeatRule,
        customRepeatEndDate,
        lang,
        context,
        mediumDateFormatter,
    ) {
        buildRepeatSummary(
            repeatRule = repeatRule,
            repeatRange = repeatRange,
            customRepeatRule = customRepeatRule,
            customRepeatEndDate = customRepeatEndDate,
            lang = lang,
            context = context,
            dateFormatter = mediumDateFormatter,
        )
    }

    // After create: keep calendar/selection, clear text for rapid entry.
    val resetInputsAfterCreate: () -> Unit = {
        titleInput = ""
        descriptionInput = ""
        reminderTime = null
    }

    val submitCreateTask: () -> Unit = {
        if (titleInput.isBlank()) {
            Toast.makeText(context, taskTexts.toastEmptyTitle, Toast.LENGTH_SHORT).show()
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
                    emojiImage = selectedSticker,
                    reminderTime = reminderTime,
                ).toTask(id = nowSeed + index)
            }
            onTasksCreated(tasks)
            Toast.makeText(context, toastCreatedMessage, Toast.LENGTH_SHORT).show()
            resetInputsAfterCreate()
            titleFocusRequester.requestFocus()
            keyboardController?.show()
        }
    }

    val chrome = LocalGlassAtmosphereUi.current.topChrome
    val useLightChrome = !LocalGlassAtmosphereUi.current.zones.topIsLight
    val cardText = LocalGlassTextColors.current
    val atmosphere = LocalGlassAtmosphereUi.current

    val dockedTool = activeTool
    val dockBarHeight = 72.dp
    val toolPanelMaxHeight = 220.dp
    val scrollBottomInset = if (dockedTool != null) dockBarHeight + toolPanelMaxHeight else dockBarHeight
    val dockShape = RoundedCornerShape(
        topStart = GlassConstants.CornerRadius,
        topEnd = GlassConstants.CornerRadius,
        bottomStart = 0.dp,
        bottomEnd = 0.dp,
    )

    CompositionLocalProvider(LocalCurrentLanguage provides LocalCurrentLanguage.current) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .statusBarsPadding(),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(AppSpacing.SectionSmall))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppSpacing.PageHorizontal)
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CommonBackButton(
                    onClick = onBack,
                    contentDescription = taskTexts.contentDescBack,
                )
                Text(
                    text = stringResource(R.string.task_create_screen_title),
                    fontSize = AppScreenHeader.titleSp,
                    fontWeight = AppScreenHeader.titleWeight,
                    color = chrome.primary,
                    style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                    modifier = Modifier.padding(start = 8.dp),
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(createScreenScrollState)
                    .padding(bottom = scrollBottomInset),
            ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppSpacing.PageHorizontal),
        ) {
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
                // While typing, collapse options so keyboard + icon row stay visible.
                activeTool = null
            },
            placeholder = taskTexts.titlePlaceholder,
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
                // While typing, collapse options so keyboard + icon row stay visible.
                activeTool = null
            },
            placeholder = taskTexts.descriptionPlaceholder,
            modifier = Modifier.padding(top = 8.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            )
        )

        if (isRecording) {
            Text(
                text = taskTexts.recording,
                color = Color(0xFFFF4444),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        val draftSummaryText = remember(
            draftTask.timeBlock,
            draftTask.urgency,
            draftTask.label,
            selectedDates.size,
            repeatRule,
            lang,
            context,
            mediumDateFormatter,
        ) {
            buildDraftSummary(
                timeBlock = draftTask.timeBlock,
                urgency = draftTask.urgency,
                label = draftTask.label,
                selectedDaysCount = selectedDates.size,
                repeatRule = repeatRule,
                lang = lang,
                context = context,
                dateFormatter = mediumDateFormatter,
            )
        }
        Text(
            text = draftSummaryText,
            color = cardText.secondary,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        )
        if (dateSelectionMode == null) {
            Text(
                text = repeatSummary,
                color = cardText.muted,
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }

        }
            }
        }

        val dockBottomInset = if (WindowInsets.isImeVisible) {
            Modifier.imePadding()
        } else {
            Modifier.navigationBarsPadding()
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .wrapContentHeight()
                .then(dockBottomInset)
                .clip(dockShape)
                .glassBottomNavDock(),
        ) {
            if (dockedTool != null) {
                HorizontalDivider(color = atmosphere.quoteDividerColor(), thickness = 1.dp)
                CreateToolPanel(
                    activeTool = dockedTool,
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
                        if (repeatRange == null) {
                            repeatRange = RepeatRange.UNLIMITED
                        }
                        repeatRule = if (repeatRule == rule) null else rule
                    },
                    onRepeatRangeChanged = { range ->
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
                    modifier = Modifier.fillMaxWidth(),
                )
                HorizontalDivider(color = atmosphere.quoteDividerColor(), thickness = 1.dp)
            }
            ActionIconsRow(
            taskTexts = taskTexts,
            cardText = cardText,
            hasReminder = !reminderTime.isNullOrBlank(),
            onReminderClick = {
                keyboardController?.hide()
                activeTool = null
                showReminderDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 52.dp)
                .padding(
                    start = AppSpacing.PageHorizontal,
                    end = AppSpacing.PageHorizontal,
                    top = 10.dp,
                    bottom = 10.dp,
                ),
            selectedUrgency = selectedUrgency,
            onTimeClick = {
                activeTool = if (activeTool == CreateTool.TIME) null else CreateTool.TIME
            },
            onUrgencyClick = {
                activeTool = if (activeTool == CreateTool.URGENCY) null else CreateTool.URGENCY
            },
            onLabelClick = {
                activeTool = if (activeTool == CreateTool.LABEL) null else CreateTool.LABEL
            },
            onStickerClick = {
                activeTool = if (activeTool == CreateTool.STICKER) null else CreateTool.STICKER
            },
            onVoiceClick = {
                isRecording = true
                val intent = Intent("android.speech.action.RECOGNIZE_SPEECH").apply {
                    putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form")
                    putExtra("android.speech.extra.PROMPT", taskTexts.voicePrompt)
                }
                try {
                    speechLauncher.launch(intent)
                } catch (_: Exception) {
                    isRecording = false
                    Toast.makeText(context, taskTexts.toastVoiceNotSupported, Toast.LENGTH_SHORT).show()
                }
            },
            onSubmit = submitCreateTask,
            )
        }
    }

    if (showReminderDialog) {
        TaskReminderTimeDialog(
            initialTime = reminderTime,
            onDismiss = { showReminderDialog = false },
            onConfirm = { picked -> reminderTime = picked },
            onClear = { reminderTime = null },
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
    val ctx = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val monthLabel = remember(currentMonth, lang) {
        val loc = when (lang) {
            LocalizationManager.Language.ZH -> Locale.forLanguageTag("zh-Hans-CN")
            LocalizationManager.Language.EN -> Locale.ENGLISH
        }
        currentMonth.atDay(1).format(DateTimeFormatter.ofPattern("MMMM yyyy", loc))
    }
    val monthDays = remember(currentMonth) { buildCalendarDays(currentMonth) }
    val weekTitles = remember(lang, ctx) { weekShortHeadersMondayFirst(ctx, lang) }
    val monthPrevDesc = remember(lang, ctx) {
        LocalizedStrings.stringFor(lang, "calendar_month_prev", ctx)
    }
    val monthNextDesc = remember(lang, ctx) {
        LocalizedStrings.stringFor(lang, "calendar_month_next", ctx)
    }
    val cardText = LocalGlassTextColors.current
    val shape = RoundedCornerShape(GlassConstants.CornerRadius)

    GlassSurface(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        fillAlpha = GlassConstants.TimeBlockFillAlpha,
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ArrowButton(
                    onClick = onPreviousMonth,
                    direction = ArrowDirection.LEFT,
                    size = 28.dp,
                    tint = cardText.primary,
                    contentDescription = monthPrevDesc,
                )
                Text(
                    text = monthLabel,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = cardText.primary,
                )
                ArrowButton(
                    onClick = onNextMonth,
                    direction = ArrowDirection.RIGHT,
                    size = 28.dp,
                    tint = cardText.primary,
                    contentDescription = monthNextDesc,
                )
            }

            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                weekTitles.forEach { title ->
                    Text(
                        text = title,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = cardText.secondary,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }

            Column(
                modifier = Modifier.padding(top = 4.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp),
            ) {
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
                                onDateSelected = onDateSelected,
                            )
                        }
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
    val cardText = LocalGlassTextColors.current
    val backgroundColor = if (isSelected) cardText.primary else Color.Transparent
    val textColor = if (isSelected) Color(0xFF1A1A1A) else cardText.primary

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
    val cardText = LocalGlassTextColors.current
    val shape = RoundedCornerShape(GlassConstants.CornerRadius)
    val fieldModifier = if (focusRequester == null) {
        modifier
    } else {
        modifier.focusRequester(focusRequester)
    }

    GlassSurface(
        modifier = fieldModifier.fillMaxWidth(),
        shape = shape,
        fillAlpha = GlassConstants.TaskCardFillAlpha,
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            textStyle = TextStyle(fontSize = 16.sp, color = cardText.primary),
            decorationBox = { inner ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = cardText.muted,
                        fontSize = 16.sp,
                    )
                }
                inner()
            },
        )
    }
}

@Composable
private fun ActionIconsRow(
    taskTexts: TaskTextProvider,
    cardText: GlassTextColors,
    modifier: Modifier = Modifier,
    hasReminder: Boolean,
    onReminderClick: () -> Unit,
    selectedUrgency: Int,
    onTimeClick: () -> Unit,
    onUrgencyClick: () -> Unit,
    onLabelClick: () -> Unit,
    onStickerClick: () -> Unit,
    onVoiceClick: () -> Unit,
    onSubmit: () -> Unit
) {
    val urgencyColorConfig = LocalUrgencyConfig.current
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
                contentDescription = taskTexts.contentDescTimeIcon,
                tint = cardText.secondary,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onTimeClick() },
            )
            Icon(
                imageVector = Icons.Default.Flag,
                contentDescription = taskTexts.contentDescFlagIcon,
                tint = Color(parseHexToArgb(urgencyColorConfig.colorForLevel(selectedUrgency))),
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onUrgencyClick() },
            )
            Icon(
                imageVector = Icons.Default.Label,
                contentDescription = taskTexts.contentDescLabelIcon,
                tint = cardText.secondary,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onLabelClick() },
            )
            Icon(
                imageVector = Icons.Default.AttachFile,
                contentDescription = taskTexts.contentDescAttachIcon,
                tint = cardText.secondary,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onStickerClick() },
            )
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = taskTexts.contentDescReminderIcon,
                tint = if (hasReminder) Color(0xFF90CAF9) else cardText.secondary,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onReminderClick() },
            )
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = taskTexts.contentDescMicIcon,
                tint = cardText.secondary,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onVoiceClick() },
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Send,
            contentDescription = taskTexts.contentDescAddTask,
            tint = cardText.primary,
            modifier = Modifier
                .size(28.dp)
                .clickable { onSubmit() },
        )
    }
}

@Composable
private fun CreateToolPanel(
    activeTool: CreateTool,
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
    val panelCtx = LocalContext.current
    val panelLang = LocalCurrentLanguage.current.value
    val cardText = LocalGlassTextColors.current
    val panelDateFormatter = remember(panelLang) {
        val loc = when (panelLang) {
            LocalizationManager.Language.ZH -> Locale.forLanguageTag("zh-Hans-CN")
            LocalizationManager.Language.EN -> Locale.ENGLISH
        }
        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(loc)
    }
    val selectedDatesLine = remember(
        selectedDates.size,
        rangeStartDate,
        rangeEndDate,
        panelLang,
        panelCtx,
        panelDateFormatter,
    ) {
        formatCreateToolSelectedSummary(
            selectedCount = selectedDates.size,
            rangeStart = rangeStartDate,
            rangeEnd = rangeEndDate,
            lang = panelLang,
            context = panelCtx,
            dateFormatter = panelDateFormatter,
        )
    }
    // Full-width footer + flat card: same white shell as icon row, tight spacing.
    key(activeTool) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .heightIn(max = 220.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            when (activeTool) {
                CreateTool.TIME -> {
                    Text(
                        text = LocalizedStrings.stringFor(panelLang, "task_create_section_date_mode", panelCtx),
                        fontSize = 13.sp,
                        color = cardText.secondary,
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        SelectionChip(
                            text = LocalizedStrings.stringFor(panelLang, "task_create_date_mode_single", panelCtx),
                            selected = dateSelectionMode == DateSelectionMode.SINGLE,
                            onClick = { onSelectionModeChanged(DateSelectionMode.SINGLE) }
                        )
                        SelectionChip(
                            text = LocalizedStrings.stringFor(panelLang, "task_create_date_mode_multi", panelCtx),
                            selected = dateSelectionMode == DateSelectionMode.MULTI,
                            onClick = { onSelectionModeChanged(DateSelectionMode.MULTI) }
                        )
                        SelectionChip(
                            text = LocalizedStrings.stringFor(panelLang, "task_create_date_mode_range", panelCtx),
                            selected = dateSelectionMode == DateSelectionMode.RANGE,
                            onClick = { onSelectionModeChanged(DateSelectionMode.RANGE) }
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(cardText.muted.copy(alpha = 0.35f)),
                    )

                    Text(
                        text = selectedDatesLine,
                        fontSize = 12.sp,
                        color = cardText.muted,
                    )

                    Text(
                        text = LocalizedStrings.stringFor(panelLang, "task_create_repeat_range", panelCtx),
                        fontSize = 13.sp,
                        color = cardText.secondary,
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        SelectionChip(
                            text = LocalizedStrings.stringFor(panelLang, "task_create_repeat_infinite", panelCtx),
                            selected = repeatRange == RepeatRange.UNLIMITED,
                            onClick = { onRepeatRangeChanged(RepeatRange.UNLIMITED) }
                        )
                        SelectionChip(
                            text = LocalizedStrings.stringFor(panelLang, "task_create_repeat_next_1w", panelCtx),
                            selected = repeatRange == RepeatRange.NEXT_1_WEEK,
                            onClick = { onRepeatRangeChanged(RepeatRange.NEXT_1_WEEK) }
                        )
                        SelectionChip(
                            text = LocalizedStrings.stringFor(panelLang, "task_create_repeat_next_2w", panelCtx),
                            selected = repeatRange == RepeatRange.NEXT_2_WEEKS,
                            onClick = { onRepeatRangeChanged(RepeatRange.NEXT_2_WEEKS) }
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        SelectionChip(
                            text = LocalizedStrings.stringFor(panelLang, "task_create_repeat_next_4w", panelCtx),
                            selected = repeatRange == RepeatRange.NEXT_4_WEEKS,
                            onClick = { onRepeatRangeChanged(RepeatRange.NEXT_4_WEEKS) }
                        )
                        SelectionChip(
                            text = LocalizedStrings.stringFor(panelLang, "task_create_repeat_this_month", panelCtx),
                            selected = repeatRange == RepeatRange.THIS_MONTH,
                            onClick = { onRepeatRangeChanged(RepeatRange.THIS_MONTH) }
                        )
                        SelectionChip(
                            text = LocalizedStrings.stringFor(panelLang, "task_create_repeat_custom_end", panelCtx),
                            selected = repeatRange == RepeatRange.CUSTOM_END_DATE,
                            onClick = { onRepeatRangeChanged(RepeatRange.CUSTOM_END_DATE) }
                        )
                    }
                    if (repeatRange == RepeatRange.CUSTOM_END_DATE) {
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            SelectionChip(
                                text = LocalizedStrings.stringFor(panelLang, "task_create_end_today", panelCtx),
                                selected = customRepeatEndDate == LocalDate.now(),
                                onClick = { onCustomRepeatEndDateChanged(LocalDate.now()) }
                            )
                            SelectionChip(
                                text = LocalizedStrings.stringFor(panelLang, "task_create_end_plus_7", panelCtx),
                                selected = customRepeatEndDate == LocalDate.now().plusDays(7),
                                onClick = { onCustomRepeatEndDateChanged(LocalDate.now().plusDays(7)) }
                            )
                            SelectionChip(
                                text = LocalizedStrings.stringFor(panelLang, "task_create_end_plus_30", panelCtx),
                                selected = customRepeatEndDate == LocalDate.now().plusDays(30),
                                onClick = { onCustomRepeatEndDateChanged(LocalDate.now().plusDays(30)) }
                            )
                        }
                    }

                    Text(
                        text = LocalizedStrings.stringFor(panelLang, "task_create_repeat_rules", panelCtx),
                        fontSize = 13.sp,
                        color = Color(0xFF757575),
                    )
                    val weekdayRules = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        weekdayRules.forEach { weekday ->
                            val chipLabel = LocalizedStrings.stringFor(
                                panelLang,
                                weekdayChipI18nKey(weekday),
                                panelCtx,
                            )
                            SelectionChip(
                                text = chipLabel,
                                selected = repeatRule == "WEEKLY_$weekday",
                                enabled = repeatRange != null,
                                onClick = { onRepeatRuleChanged("WEEKLY_$weekday") }
                            )
                        }
                    }
                    SelectionChip(
                        text = LocalizedStrings.stringFor(panelLang, "task_create_repeat_custom", panelCtx),
                        selected = repeatRule == "CUSTOM",
                        enabled = repeatRange != null,
                        onClick = { onRepeatRuleChanged("CUSTOM") }
                    )
                    if (repeatRule == "CUSTOM" && repeatRange != null) {
                        val customRuleHint = LocalizedStrings.stringFor(
                            panelLang,
                            "task_create_custom_rule_hint",
                            panelCtx,
                        )
                        BasicTextField(
                            value = customRepeatRule,
                            onValueChange = onCustomRepeatRuleChanged,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(AppShapes.EmbedRadius))
                                .background(Color(0xFFF5F5F5))
                                .padding(horizontal = 10.dp, vertical = 8.dp),
                            textStyle = TextStyle(fontSize = 13.sp, color = Color(0xFF1A1A1A)),
                            decorationBox = { inner ->
                                if (customRepeatRule.isBlank()) {
                                    Text(customRuleHint, fontSize = 13.sp, color = Color(0xFF9E9E9E))
                                }
                                inner()
                            }
                        )
                    }

                    TaskConstants.TIME_BLOCKS.forEach { block ->
                        OptionPill(
                            text = LocalizedStrings.timeBlockLabelFor(block, panelLang, panelCtx),
                            selected = selectedTimeBlock == block,
                            leadingEmoji = timeBlockEmoji(block),
                            onClick = { onTimeSelected(block) }
                        )
                    }
                }

                CreateTool.URGENCY -> {
                    val urgencyConfig = LocalUrgencyConfig.current
                    TaskConstants.URGENCY_LEVELS.entries.forEach { entry ->
                        OptionPill(
                            text = urgencyConfig.labelForLevel(entry.key).ifBlank {
                                LocalizedStrings.stringFor(
                                    panelLang,
                                    "task_urgency_${entry.key}",
                                    panelCtx,
                                )
                            },
                            selected = selectedUrgency == entry.key,
                            colorDot = Color(parseHexToArgb(urgencyConfig.colorForLevel(entry.key))),
                            onClick = { onUrgencySelected(entry.key) }
                        )
                    }
                }

                CreateTool.LABEL -> {
                    labelOptions.forEach { label ->
                        val isNone = label == TaskConstants.LABEL_NONE
                        val display = TaskUiLocalLabels.labelDisplay(panelLang, panelCtx, label, !isNone)
                        OptionPill(
                            text = display,
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
                        text = LocalizedStrings.stringFor(panelLang, "task_create_clear_sticker", panelCtx),
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
    onClick: () -> Unit,
) {
    val cardText = LocalGlassTextColors.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppShapes.EmbedRadius))
            .background(
                if (selected) cardText.primary.copy(alpha = 0.18f) else Color.Transparent,
            )
            .clickable { onClick() }
            .padding(start = 10.dp, end = 10.dp, top = 1.dp, bottom = 1.dp),
        verticalAlignment = Alignment.CenterVertically,
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
        Text(text = text, fontSize = 14.sp, color = cardText.primary)
    }
}

@Composable
private fun SelectionChip(
    text: String,
    selected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val cardText = LocalGlassTextColors.current
    val backgroundColor = when {
        !enabled -> cardText.muted.copy(alpha = 0.2f)
        selected -> cardText.primary
        else -> cardText.muted.copy(alpha = 0.15f)
    }
    val textColor = when {
        !enabled -> cardText.muted
        selected -> Color(0xFF1A1A1A)
        else -> cardText.secondary
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(AppShapes.BottomBarSelectedRadius))
            .background(backgroundColor)
            .clickable(enabled = enabled) { onClick() }
            .padding(start = 10.dp, end = 10.dp, top = 1.dp, bottom = 1.dp)
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
    val leadingEmpty = (firstDay.dayOfWeek.value + 6) % 7
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

private fun weekdayChipI18nKey(code: String): String = when (code) {
    "MON" -> "calendar_week_mon"
    "TUE" -> "calendar_week_tue"
    "WED" -> "calendar_week_wed"
    "THU" -> "calendar_week_thu"
    "FRI" -> "calendar_week_fri"
    "SAT" -> "calendar_week_sat"
    "SUN" -> "calendar_week_sun"
    else -> "calendar_week_mon"
}

private fun formatCreateToolSelectedSummary(
    selectedCount: Int,
    rangeStart: LocalDate?,
    rangeEnd: LocalDate?,
    lang: LocalizationManager.Language,
    context: Context,
    dateFormatter: DateTimeFormatter,
): String {
    val countPart = LocalizedStrings.stringFor(lang, "task_create_selected_count", context, selectedCount)
    val extras = buildList {
        if (rangeStart != null) {
            add(
                LocalizedStrings.stringFor(
                    lang,
                    "task_create_range_from",
                    context,
                    rangeStart.format(dateFormatter),
                ),
            )
        }
        if (rangeEnd != null) {
            add(
                LocalizedStrings.stringFor(
                    lang,
                    "task_create_range_to",
                    context,
                    rangeEnd.format(dateFormatter),
                ),
            )
        }
    }
    return if (extras.isEmpty()) countPart else (listOf(countPart) + extras).joinToString(" ")
}

private fun weeklyRepeatSummary(lang: LocalizationManager.Language, context: Context, repeatRule: String): String {
    val suffix = repeatRule.removePrefix("WEEKLY_")
    val weekday = LocalizedStrings.stringFor(lang, weekdayChipI18nKey(suffix), context)
    return LocalizedStrings.stringFor(lang, "task_create_weekly_pattern", context, weekday)
}

private fun repeatRuleSummaryLine(
    lang: LocalizationManager.Language,
    context: Context,
    repeatRule: String?,
    customRepeatRule: String,
): String {
    if (repeatRule == null) {
        return LocalizedStrings.stringFor(lang, "task_create_no_repeat_rule", context)
    }
    if (repeatRule.startsWith("WEEKLY_")) {
        return weeklyRepeatSummary(lang, context, repeatRule)
    }
    if (repeatRule == TaskConstants.REPEAT_RULE_NONE) {
        return LocalizedStrings.stringFor(lang, "task_create_repeat_rule_none_short", context)
    }
    if (repeatRule == TaskConstants.REPEAT_RULE_DAILY) {
        return LocalizedStrings.stringFor(lang, "task_create_repeat_rule_daily_short", context)
    }
    if (repeatRule == "CUSTOM") {
        return if (customRepeatRule.isBlank()) {
            LocalizedStrings.stringFor(lang, "task_create_custom_rule_fallback", context)
        } else {
            customRepeatRule
        }
    }
    return repeatRule
}

private fun repeatRangeSummaryLine(
    lang: LocalizationManager.Language,
    context: Context,
    repeatRange: RepeatRange?,
    customRepeatEndDate: LocalDate?,
    dateFormatter: DateTimeFormatter,
): String {
    if (repeatRange == null) {
        return LocalizedStrings.stringFor(lang, "task_create_range_not_selected", context)
    }
    return when (repeatRange) {
        RepeatRange.UNLIMITED -> LocalizedStrings.stringFor(lang, "task_create_repeat_infinite", context)
        RepeatRange.NEXT_1_WEEK -> LocalizedStrings.stringFor(lang, "task_create_repeat_next_1w", context)
        RepeatRange.NEXT_2_WEEKS -> LocalizedStrings.stringFor(lang, "task_create_repeat_next_2w", context)
        RepeatRange.NEXT_4_WEEKS -> LocalizedStrings.stringFor(lang, "task_create_repeat_next_4w", context)
        RepeatRange.THIS_MONTH -> LocalizedStrings.stringFor(lang, "task_create_repeat_this_month", context)
        RepeatRange.CUSTOM_END_DATE -> {
            val endLabel = customRepeatEndDate?.format(dateFormatter)
                ?: LocalizedStrings.stringFor(lang, "task_create_date_not_set", context)
            LocalizedStrings.stringFor(lang, "task_create_range_until", context, endLabel)
        }
    }
}

private fun buildRepeatSummary(
    repeatRule: String?,
    repeatRange: RepeatRange?,
    customRepeatRule: String,
    customRepeatEndDate: LocalDate?,
    lang: LocalizationManager.Language,
    context: Context,
    dateFormatter: DateTimeFormatter,
): String {
    val ruleText = repeatRuleSummaryLine(lang, context, repeatRule, customRepeatRule)
    val rangeText = repeatRangeSummaryLine(lang, context, repeatRange, customRepeatEndDate, dateFormatter)
    val join = LocalizedStrings.stringFor(lang, "task_create_summary_join", context)
    return ruleText + join + rangeText
}

private fun repeatRuleDraftSnippet(
    lang: LocalizationManager.Language,
    context: Context,
    repeatRule: String?,
): String {
    val r = repeatRule?.trim().orEmpty()
    if (r.isEmpty()) return ""
    if (r == TaskConstants.REPEAT_RULE_NONE) {
        return LocalizedStrings.stringFor(lang, "task_create_repeat_rule_none_short", context)
    }
    if (r == TaskConstants.REPEAT_RULE_DAILY) {
        return LocalizedStrings.stringFor(lang, "task_create_repeat_rule_daily_short", context)
    }
    if (r.startsWith("WEEKLY_")) {
        return weeklyRepeatSummary(lang, context, r)
    }
    if (r == "CUSTOM") {
        return LocalizedStrings.stringFor(lang, "task_create_repeat_custom", context)
    }
    return r
}

private fun buildDraftSummary(
    timeBlock: String,
    urgency: Int,
    label: String?,
    selectedDaysCount: Int,
    repeatRule: String?,
    lang: LocalizationManager.Language,
    context: Context,
    dateFormatter: DateTimeFormatter,
): String {
    val sep = LocalizedStrings.stringFor(lang, "task_create_summary_join", context)
    val parts = mutableListOf<String>()
    parts += LocalizedStrings.timeBlockLabelFor(timeBlock, lang, context)
    parts += LocalizedStrings.stringFor(lang, "task_urgency_$urgency", context)
    if (!label.isNullOrBlank()) {
        parts += TaskUiLocalLabels.labelDisplay(lang, context, label, withHashPrefixForNonNone = true)
    }
    if (selectedDaysCount > 0) {
        parts += LocalizedStrings.stringFor(lang, "task_create_draft_days", context, selectedDaysCount)
    }
    val ruleSnippet = repeatRuleDraftSnippet(lang, context, repeatRule)
    if (ruleSnippet.isNotBlank()) {
        parts += ruleSnippet
    }
    return parts.joinToString(sep)
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
        // Multi/range selections save as repeating; default DAILY.
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
        // Range chosen without explicit repeat rule → default daily repeat (avoid NONE).
        return TaskConstants.REPEAT_RULE_DAILY
    }

    if (normalizedRule == "CUSTOM") {
        val custom = customRepeatRule.trim()
        return if (custom.isNotEmpty()) custom else TaskConstants.REPEAT_RULE_DAILY
    }

    return normalizedRule
}
