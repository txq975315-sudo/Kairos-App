package com.example.kairosapplication.ui.components

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import com.example.kairosapplication.core.text.rememberTaskTextProvider
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppTypography
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task
import com.example.taskmodel.util.TaskUtils

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

private enum class IconSheetType {
    TIME, URGENCY, LABEL, ATTACH, ATTACH_LOCAL
}

/** 统一重复任务判断：非空且非 NONE 才视为重复任务。 */
private fun isRepeatingTask(task: Task): Boolean {
    return task.repeatRule.isNotBlank() &&
        task.repeatRule.trim().uppercase() != TaskConstants.REPEAT_RULE_NONE
}

@Composable
fun CreateTaskBottomSheet(
    task: Task? = null,
    onDismiss: () -> Unit,
    onSave: (Task) -> Unit
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
                emojiImage = seed.emojiImage,
                localImageUri = seed.localImageUri
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
                        emojiImage = valueMeta.emojiImage,
                        localImageUri = valueMeta.localImageUri
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
    onStopClick: (() -> Unit)? = null
) {
    val taskText = rememberTaskTextProvider()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
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
    var keyboardHeightDp by remember { mutableStateOf(280.dp) }
    val imeBottomPx = WindowInsets.ime.getBottom(density)
    val isKeyboardVisible = imeBottomPx > 0

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

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            onMetaChange(meta.copy(emojiImage = null, localImageUri = uri.toString()))
            iconSheetType = null
            titleFocusRequester.requestFocus()
            keyboardController?.show()
        } else {
            // 用户取消本地图片选择时，恢复到主输入状态，避免停留在附件选择态
            iconSheetType = null
            titleFocusRequester.requestFocus()
            keyboardController?.show()
        }
    }

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

            debugRepeatRuleText?.let { repeatRule ->
                Text(
                    text = "repeatRule: $repeatRule",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
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
                keyboardActions = KeyboardActions(
                    onDone = {
                        val success = onCreateTask(title, description, config.timeBlock, meta)
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
                ),
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
                            tint = if (hasSelectedUrgency) TaskUtils.getUrgencyColor(meta.urgency) else AppColors.IconNeutral,
                            modifier = Modifier.clickable { showIconSheet(IconSheetType.URGENCY) }
                        )
                        Spacer(Modifier.width(4.dp))
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(TaskUtils.getUrgencyColor(meta.urgency))
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.Label,
                            contentDescription = taskText.contentDescLabelIcon,
                            tint = AppColors.IconNeutral,
                            modifier = Modifier.clickable { showIconSheet(IconSheetType.LABEL) }
                        )
                        if (!meta.label.isNullOrBlank()) {
                            Spacer(Modifier.width(4.dp))
                            Text(text = "# ${meta.label}", fontSize = 12.sp, color = config.titleColor)
                        }
                    }
                    Icon(
                        imageVector = Icons.Default.AttachFile,
                        contentDescription = taskText.contentDescAttachIcon,
                        tint = AppColors.IconNeutral,
                        modifier = Modifier.clickable {
                            showIconSheet(IconSheetType.ATTACH)
                        }
                    )
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
                if (showStopButton && onStopClick != null) {
                    IconButton(onClick = onStopClick) {
                        Icon(
                            imageVector = Icons.Default.Stop,
                            contentDescription = "Stop 重复",
                            tint = Color.Red
                        )
                    }
                }
            }

            if (iconSheetType == null && !isKeyboardVisible) {
                Spacer(modifier = Modifier.height(8.dp))
            }

            iconSheetType?.let { type ->
                // 图标选项区域替代键盘显示，保持主弹窗尺寸不变
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(keyboardHeightDp)
                        .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                        .background(config.backgroundColor)
                        .padding(top = 8.dp)
                ) {
                    when (type) {
                        IconSheetType.TIME -> {
                            TaskConstants.TIME_BLOCKS.forEach { option ->
                                OptionRow(
                                    text = option,
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
                        IconSheetType.URGENCY -> {
                            val options = TaskConstants.URGENCY_LEVELS
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
                                            .background(TaskUtils.getUrgencyColor(entry.key))
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        text = entry.value,
                                        fontSize = 15.sp,
                                        color = if (meta.urgency == entry.key) AppColors.PrimaryText else AppColors.SecondaryText
                                    )
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
                                        text = option,
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
                        IconSheetType.ATTACH -> {
                            OptionRow(text = taskText.attachEmojiOption, selected = meta.emojiImage != null, onClick = {})
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                taskText.attachEmojis.forEach { emoji ->
                                    Text(
                                        text = emoji,
                                        fontSize = 24.sp,
                                        modifier = Modifier.clickable {
                                            onMetaChange(meta.copy(emojiImage = emoji, localImageUri = null))
                                            closeIconSheetAndRestoreKeyboard()
                                        }
                                    )
                                }
                            }
                            OptionRow(
                                text = taskText.attachLocalOption,
                                selected = meta.localImageUri != null,
                                onClick = { iconSheetType = IconSheetType.ATTACH_LOCAL }
                            )
                        }
                        IconSheetType.ATTACH_LOCAL -> {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = taskText.contentDescBack,
                                    tint = AppColors.IconNeutral,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable { closeIconSheetAndRestoreKeyboard() }
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = taskText.localImageTitle,
                                    fontSize = 15.sp,
                                    color = AppColors.IconNeutral,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            OptionRow(
                                text = taskText.openLocalImagePicker,
                                selected = false,
                                leadingIcon = taskText.localPickerIcon,
                                onClick = { imagePickerLauncher.launch(taskText.imagePickerMime) }
                            )
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
