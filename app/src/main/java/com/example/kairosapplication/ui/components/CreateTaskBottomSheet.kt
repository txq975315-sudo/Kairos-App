package com.example.kairosapplication.ui.components

import com.example.kairosapplication.BuildConfig
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.text.TaskUiLocalLabels
import com.example.kairosapplication.core.text.rememberTaskTextProvider
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.core.ui.AppTypography
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task
import com.example.taskmodel.util.TaskUtils
import com.example.taskmodel.util.ColorUtils.parseHexToArgb
import com.example.kairosapplication.core.ui.LocalUrgencyConfig
import kotlinx.coroutines.delay

internal data class CreateSheetConfig(
    val timeBlock: String,
    val backgroundColor: Color,
    val titleColor: Color
)

internal data class CreateTaskMeta(
    val urgency: Int,
    val label: String?,
    val emojiImage: String?,
    val localImageUri: String?
)

private fun CreateTaskMeta.stripTaskImages(): CreateTaskMeta =
    copy(emojiImage = null, localImageUri = null)

private enum class IconSheetType {
    TIME, URGENCY, LABEL
}

/** Treat as repeating only when non-blank and not NONE. */
private fun isRepeatingTask(task: Task): Boolean {
    return task.repeatRule.isNotBlank() &&
        task.repeatRule.trim().uppercase() != TaskConstants.REPEAT_RULE_NONE
}

@Composable
fun CreateTaskBottomSheet(
    task: Task? = null,
    onDismiss: () -> Unit,
    onSave: (Task) -> Unit,
    onDelete: ((Task) -> Unit)? = null
) {
    val shouldShowStopButton = task?.let(::isRepeatingTask) ?: false
    val debugRepeatRuleText = task?.repeatRule
    val seed = task ?: Task(
        id = 0,
        title = "",
        description = "",
        timeBlock = TaskConstants.TIME_BLOCK_ANYTIME,
        urgency = TaskConstants.URGENCY_LOW
    )
    var draftTask by remember(task) { mutableStateOf(seed) }
    var title by remember(task) { mutableStateOf(seed.title) }
    var description by remember(task) { mutableStateOf(seed.description) }
    var meta by remember(task) {
        mutableStateOf(
            CreateTaskMeta(
                urgency = seed.urgency,
                label = seed.label,
                emojiImage = null,
                localImageUri = null
            )
        )
    }
    val config = remember(draftTask.timeBlock) {
        CreateSheetConfig(
            timeBlock = draftTask.timeBlock,
            backgroundColor = TaskUtils.getTimeBlockColor(draftTask.timeBlock),
            titleColor = TaskUtils.getTimeBlockTitleColor(draftTask.timeBlock)
        )
    }

    CreateTaskBottomSheet(
        config = config,
        onDismiss = onDismiss,
        title = title,
        onTitleChange = { title = it },
        description = description,
        onDescriptionChange = { description = it },
        meta = meta,
        onMetaChange = { meta = it },
        onTimeBlockChange = { draftTask = draftTask.copy(timeBlock = it) },
        onCreateTask = { valueTitle, valueDescription, timeBlock, valueMeta ->
            val trimmed = valueTitle.trim()
            if (trimmed.isEmpty()) {
                false
            } else {
                onSave(
                    draftTask.copy(
                        title = trimmed,
                        description = valueDescription.trim(),
                        timeBlock = timeBlock,
                        urgency = valueMeta.urgency,
                        label = valueMeta.label,
                        emojiImage = null,
                        localImageUri = null
                    )
                )
                true
            }
        },
        showStopButton = shouldShowStopButton,
        debugRepeatRuleText = debugRepeatRuleText,
        onStopClick = {
            val sourceTask = task ?: draftTask
            onSave(sourceTask.copy(repeatRule = TaskConstants.REPEAT_RULE_NONE))
            onDismiss()
        },
        showDeleteButton = task != null && onDelete != null,
        onDeleteClick = if (task != null && onDelete != null) {
            {
                onDelete(task)
                onDismiss()
            }
        } else {
            null
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun CreateTaskBottomSheet(
    config: CreateSheetConfig,
    onDismiss: () -> Unit,
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    meta: CreateTaskMeta,
    onMetaChange: (CreateTaskMeta) -> Unit,
    onTimeBlockChange: (String) -> Unit,
    onCreateTask: (title: String, description: String, timeBlock: String, meta: CreateTaskMeta) -> Boolean,
    showStopButton: Boolean = false,
    debugRepeatRuleText: String? = null,
    onStopClick: (() -> Unit)? = null,
    showDeleteButton: Boolean = false,
    onDeleteClick: (() -> Unit)? = null
) {
    val taskText = rememberTaskTextProvider()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val density = LocalDensity.current
    val titleFocusRequester = remember { FocusRequester() }
    var iconSheetType by remember { mutableStateOf<IconSheetType?>(null) }
    var customLabelInput by remember { mutableStateOf("") }
    var labelOptions by remember {
        mutableStateOf(
            TaskConstants.LABEL_OPTIONS.toMutableList()
        )
    }
    var isRecording by remember { mutableStateOf(false) }
    var hasSelectedTime by remember { mutableStateOf(false) }
    var hasSelectedUrgency by remember { mutableStateOf(false) }
    val urgencyColorConfig = LocalUrgencyConfig.current
    var keyboardHeightDp by remember { mutableStateOf(280.dp) }
    val imeBottomPx = WindowInsets.ime.getBottom(density)
    val isKeyboardVisible = imeBottomPx > 0
    /** Icon panel height ~ half keyboard to avoid huge empty space on short lists */
    val iconSheetPanelHeight = keyboardHeightDp * 0.5f

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
            Toast.makeText(context, taskText.toastNoSpeech, Toast.LENGTH_SHORT).show()
        }
    }

    val attemptSubmit: () -> Unit = {
        val success = onCreateTask(title, description, config.timeBlock, meta.stripTaskImages())
        if (success) {
            keyboardController?.hide()
            focusManager.clearFocus(force = true)
            onDismiss()
        } else {
            Toast.makeText(context, taskText.toastEmptyTitle, Toast.LENGTH_SHORT).show()
            titleFocusRequester.requestFocus()
            keyboardController?.show()
        }
    }

    // Defer IME slightly so the sheet settle animation does not fight the keyboard (smoother open).
    LaunchedEffect(Unit) {
        delay(48)
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
        // Bottom sheet window may not inherit composition locals; re-attach app language for i18n.
        CompositionLocalProvider(LocalCurrentLanguage provides LocalCurrentLanguage.current) {
        val sheetLang = LocalCurrentLanguage.current.value
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .imePadding()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = LocalizedStrings.timeBlockLabel(config.timeBlock),
                modifier = Modifier.fillMaxWidth(),
                color = config.titleColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            if (BuildConfig.DEBUG) {
                debugRepeatRuleText?.let { repeatRule ->
                    Text(
                        text = "repeatRule: $repeatRule",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }

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
                keyboardActions = KeyboardActions(onDone = { attemptSubmit() }),
                decorationBox = { innerTextField ->
                    if (title.isEmpty()) {
                        Text(
                            text = taskText.titlePlaceholder,
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
                            text = taskText.descriptionPlaceholder,
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = taskText.contentDescTimeIcon,
                            tint = if (hasSelectedTime) TaskUtils.getTimeBlockTitleColor(config.timeBlock) else AppColors.IconNeutral,
                            modifier = Modifier.clickable { showIconSheet(IconSheetType.TIME) }
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.Flag,
                            contentDescription = taskText.contentDescFlagIcon,
                            tint = if (hasSelectedUrgency) Color(parseHexToArgb(urgencyColorConfig.colorForLevel(meta.urgency))) else AppColors.IconNeutral,
                            modifier = Modifier.clickable { showIconSheet(IconSheetType.URGENCY) }
                        )
                        Spacer(Modifier.width(4.dp))
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(Color(parseHexToArgb(urgencyColorConfig.colorForLevel(meta.urgency))))
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Label,
                            contentDescription = taskText.contentDescLabelIcon,
                            tint = AppColors.IconNeutral,
                            modifier = Modifier.clickable { showIconSheet(IconSheetType.LABEL) }
                        )
                        if (!meta.label.isNullOrBlank()) {
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = TaskUiLocalLabels.labelDisplay(
                                    sheetLang,
                                    context,
                                    meta.label!!,
                                    withHashPrefixForNonNone = true,
                                ),
                                fontSize = 12.sp,
                                color = config.titleColor,
                            )
                        }
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = taskText.contentDescMicIcon,
                            tint = AppColors.IconNeutral,
                            modifier = Modifier.clickable {
                                isRecording = true
                                val intent = Intent("android.speech.action.RECOGNIZE_SPEECH").apply {
                                    putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form")
                                    putExtra("android.speech.extra.PROMPT", taskText.voicePrompt)
                                }
                                try {
                                    speechLauncher.launch(intent)
                                } catch (_: Exception) {
                                    isRecording = false
                                    Toast.makeText(context, taskText.toastVoiceNotSupported, Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                        if (isRecording) {
                            Text(text = taskText.recording, fontSize = AppTypography.Caption, color = AppColors.Urgent)
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = taskText.contentDescAddTask,
                        tint = Color(0xFF1A1A1A),
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(26.dp)
                            .clickable { attemptSubmit() },
                    )
                    if (showStopButton && onStopClick != null) {
                        IconButton(onClick = onStopClick) {
                            Icon(
                                imageVector = Icons.Default.Stop,
                                contentDescription = LocalizedStrings.stringFor(
                                    sheetLang,
                                    "task_sheet_stop_repeating",
                                    context,
                                ),
                                tint = Color(0xFFFF9800)
                            )
                        }
                    }
                    if (showDeleteButton && onDeleteClick != null) {
                        IconButton(onClick = onDeleteClick) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = LocalizedStrings.stringFor(
                                    sheetLang,
                                    "task_sheet_delete_task",
                                    context,
                                ),
                                tint = Color(0xFFD32F2F)
                            )
                        }
                    }
                }
            }

            if (iconSheetType == null && !isKeyboardVisible) {
                Spacer(modifier = Modifier.height(8.dp))
            }

            iconSheetType?.let { type ->
                // Icon strip replaces keyboard without resizing the sheet
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(iconSheetPanelHeight)
                        .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                        .background(config.backgroundColor)
                        .padding(top = 8.dp)
                ) {
                    when (type) {
                        IconSheetType.TIME -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .verticalScroll(rememberScrollState())
                            ) {
                                TaskConstants.TIME_BLOCKS.forEach { option ->
                                    OptionRow(
                                        text = LocalizedStrings.timeBlockLabel(option),
                                        leadingIcon = taskText.timeBlockIcons[option] ?: "•",
                                        selected = config.timeBlock == option,
                                        onClick = {
                                            hasSelectedTime = true
                                            onTimeBlockChange(option)
                                            closeIconSheetAndRestoreKeyboard()
                                        }
                                    )
                                }
                            }
                        }
                        IconSheetType.URGENCY -> {
                            val urgencyConfig = LocalUrgencyConfig.current
                            val options = TaskConstants.URGENCY_LEVELS
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .verticalScroll(rememberScrollState())
                            ) {
                                options.entries.forEach { entry ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                hasSelectedUrgency = true
                                                onMetaChange(meta.copy(urgency = entry.key))
                                                closeIconSheetAndRestoreKeyboard()
                                            }
                                            .padding(horizontal = 20.dp, vertical = 12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(12.dp)
                                                .clip(CircleShape)
                                                .background(Color(parseHexToArgb(urgencyConfig.colorForLevel(entry.key))))
                                        )
                                        Spacer(Modifier.width(8.dp))
                                        Text(
                                            text = urgencyConfig.labelForLevel(entry.key).ifBlank { LocalizedStrings.get("task_urgency_${entry.key}") },
                                            fontSize = 15.sp,
                                            color = if (meta.urgency == entry.key) AppColors.PrimaryText else AppColors.SecondaryText
                                        )
                                    }
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
                                        text = TaskUiLocalLabels.labelDisplay(
                                            sheetLang,
                                            context,
                                            option,
                                            withHashPrefixForNonNone = false,
                                        ),
                                        leadingIcon = TaskConstants.LABEL_ICONS[option] ?: taskText.labelFallbackIcon,
                                        selected = (option != TaskConstants.LABEL_NONE && option == meta.label) ||
                                            (option == TaskConstants.LABEL_NONE && meta.label == null),
                                        onClick = {
                                            when (option) {
                                                TaskConstants.LABEL_NONE -> {
                                                    onMetaChange(meta.copy(label = null))
                                                    closeIconSheetAndRestoreKeyboard()
                                                }
                                                TaskConstants.LABEL_CREATE_NEW -> {
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
                                    if (option == TaskConstants.LABEL_CREATE_NEW) {
                                        BasicTextField(
                                            value = customLabelInput,
                                            onValueChange = { customLabelInput = it },
                                            textStyle = TextStyle(fontSize = 15.sp, color = AppColors.PrimaryText),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 20.dp, vertical = 8.dp),
                                            decorationBox = { inner ->
                                                if (customLabelInput.isBlank()) {
                                                    Text(taskText.customLabelPlaceholder, fontSize = 15.sp, color = AppColors.SecondaryText)
                                                }
                                                inner()
                                            }
                                        )
                                    }
                                }
                            }
                        }
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
