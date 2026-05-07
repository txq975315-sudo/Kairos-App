package com.example.kairosapplication.core.text

import android.content.Context
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.taskmodel.constants.TaskConstants

/** Official task label chips (storage key → i18n). User-defined labels pass through as-is. */
object TaskUiLocalLabels {

    fun labelDisplay(
        lang: LocalizationManager.Language,
        context: Context,
        storageLabel: String,
        withHashPrefixForNonNone: Boolean,
    ): String {
        val name = when (storageLabel) {
            TaskConstants.LABEL_NONE -> LocalizedStrings.stringFor(lang, "task_label_none", context)
            TaskConstants.LABEL_WORK -> LocalizedStrings.stringFor(lang, "task_label_work", context)
            TaskConstants.LABEL_HABIT -> LocalizedStrings.stringFor(lang, "task_label_habit", context)
            TaskConstants.LABEL_STUDY -> LocalizedStrings.stringFor(lang, "task_label_study", context)
            TaskConstants.LABEL_LIFE -> LocalizedStrings.stringFor(lang, "task_label_life", context)
            TaskConstants.LABEL_EXERCISE -> LocalizedStrings.stringFor(lang, "task_label_exercise", context)
            TaskConstants.LABEL_TRAVEL -> LocalizedStrings.stringFor(lang, "task_label_travel", context)
            TaskConstants.LABEL_CREATE_NEW -> LocalizedStrings.stringFor(lang, "task_label_create_new", context)
            else -> storageLabel
        }
        return if (storageLabel == TaskConstants.LABEL_NONE || !withHashPrefixForNonNone) {
            name
        } else {
            "# $name"
        }
    }
}
