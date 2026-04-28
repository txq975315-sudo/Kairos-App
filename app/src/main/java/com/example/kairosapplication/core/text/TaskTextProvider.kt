package com.example.kairosapplication.core.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.kairosapplication.R
import com.example.taskmodel.constants.TaskConstants

data class TaskTextProvider(
    val contentDescAddTask: String,
    val contentDescTimeIcon: String,
    val contentDescFlagIcon: String,
    val contentDescLabelIcon: String,
    val contentDescAttachIcon: String,
    val contentDescMicIcon: String,
    val contentDescCompleted: String,
    val contentDescBack: String,
    val toastNoSpeech: String,
    val toastEmptyTitle: String,
    val toastVoiceNotSupported: String,
    val voicePrompt: String,
    val recording: String,
    val titlePlaceholder: String,
    val descriptionPlaceholder: String,
    val customLabelPlaceholder: String,
    val attachEmojiOption: String,
    val attachLocalOption: String,
    val localImageTitle: String,
    val openLocalImagePicker: String,
    val filtering: String,
    val labelFallbackIcon: String,
    val localPickerIcon: String,
    val imagePickerMime: String,
    val anytimeHint: String,
    val morningHint: String,
    val afternoonHint: String,
    val eveningHint: String,
    val defaultHint: String
) {
    val timeBlockIcons = mapOf(
        TaskConstants.TIME_BLOCK_ANYTIME to "🕒",
        TaskConstants.TIME_BLOCK_MORNING to "🌅",
        TaskConstants.TIME_BLOCK_AFTERNOON to "☀️",
        TaskConstants.TIME_BLOCK_EVENING to "🌙"
    )

    val attachEmojis = listOf("📅", "💼", "📚")

    fun timeBlockHint(timeBlock: String): String {
        return when (timeBlock) {
            TaskConstants.TIME_BLOCK_ANYTIME -> anytimeHint
            TaskConstants.TIME_BLOCK_MORNING -> morningHint
            TaskConstants.TIME_BLOCK_AFTERNOON -> afternoonHint
            TaskConstants.TIME_BLOCK_EVENING -> eveningHint
            else -> defaultHint
        }
    }
}

@Composable
fun rememberTaskTextProvider(): TaskTextProvider {
    val contentDescAddTask = stringResource(R.string.task_content_desc_add_task)
    val contentDescTimeIcon = stringResource(R.string.task_content_desc_time_icon)
    val contentDescFlagIcon = stringResource(R.string.task_content_desc_flag_icon)
    val contentDescLabelIcon = stringResource(R.string.task_content_desc_label_icon)
    val contentDescAttachIcon = stringResource(R.string.task_content_desc_attach_icon)
    val contentDescMicIcon = stringResource(R.string.task_content_desc_mic_icon)
    val contentDescCompleted = stringResource(R.string.task_content_desc_completed)
    val contentDescBack = stringResource(R.string.task_content_desc_back)
    val toastNoSpeech = stringResource(R.string.task_toast_no_speech)
    val toastEmptyTitle = stringResource(R.string.task_toast_empty_title)
    val toastVoiceNotSupported = stringResource(R.string.task_toast_voice_not_supported)
    val voicePrompt = stringResource(R.string.task_voice_prompt)
    val recording = stringResource(R.string.task_recording)
    val titlePlaceholder = stringResource(R.string.task_title_placeholder)
    val descriptionPlaceholder = stringResource(R.string.task_description_placeholder)
    val customLabelPlaceholder = stringResource(R.string.task_custom_label_placeholder)
    val attachEmojiOption = stringResource(R.string.task_attach_emoji_option)
    val attachLocalOption = stringResource(R.string.task_attach_local_option)
    val localImageTitle = stringResource(R.string.task_local_image_title)
    val openLocalImagePicker = stringResource(R.string.task_open_local_image_picker)
    val filtering = stringResource(R.string.task_filtering)
    val labelFallbackIcon = stringResource(R.string.task_label_fallback_icon)
    val localPickerIcon = stringResource(R.string.task_local_picker_icon)
    val imagePickerMime = stringResource(R.string.task_image_picker_mime)
    val anytimeHint = stringResource(R.string.task_hint_anytime)
    val morningHint = stringResource(R.string.task_hint_morning)
    val afternoonHint = stringResource(R.string.task_hint_afternoon)
    val eveningHint = stringResource(R.string.task_hint_evening)
    val defaultHint = stringResource(R.string.task_hint_default)

    return remember(
        contentDescAddTask,
        contentDescTimeIcon,
        contentDescFlagIcon,
        contentDescLabelIcon,
        contentDescAttachIcon,
        contentDescMicIcon,
        contentDescCompleted,
        contentDescBack,
        toastNoSpeech,
        toastEmptyTitle,
        toastVoiceNotSupported,
        voicePrompt,
        recording,
        titlePlaceholder,
        descriptionPlaceholder,
        customLabelPlaceholder,
        attachEmojiOption,
        attachLocalOption,
        localImageTitle,
        openLocalImagePicker,
        filtering,
        labelFallbackIcon,
        localPickerIcon,
        imagePickerMime,
        anytimeHint,
        morningHint,
        afternoonHint,
        eveningHint,
        defaultHint
    ) {
        TaskTextProvider(
            contentDescAddTask = contentDescAddTask,
            contentDescTimeIcon = contentDescTimeIcon,
            contentDescFlagIcon = contentDescFlagIcon,
            contentDescLabelIcon = contentDescLabelIcon,
            contentDescAttachIcon = contentDescAttachIcon,
            contentDescMicIcon = contentDescMicIcon,
            contentDescCompleted = contentDescCompleted,
            contentDescBack = contentDescBack,
            toastNoSpeech = toastNoSpeech,
            toastEmptyTitle = toastEmptyTitle,
            toastVoiceNotSupported = toastVoiceNotSupported,
            voicePrompt = voicePrompt,
            recording = recording,
            titlePlaceholder = titlePlaceholder,
            descriptionPlaceholder = descriptionPlaceholder,
            customLabelPlaceholder = customLabelPlaceholder,
            attachEmojiOption = attachEmojiOption,
            attachLocalOption = attachLocalOption,
            localImageTitle = localImageTitle,
            openLocalImagePicker = openLocalImagePicker,
            filtering = filtering,
            labelFallbackIcon = labelFallbackIcon,
            localPickerIcon = localPickerIcon,
            imagePickerMime = imagePickerMime,
            anytimeHint = anytimeHint,
            morningHint = morningHint,
            afternoonHint = afternoonHint,
            eveningHint = eveningHint,
            defaultHint = defaultHint
        )
    }
}
