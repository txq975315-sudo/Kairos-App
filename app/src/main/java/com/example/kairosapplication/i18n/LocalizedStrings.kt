package com.example.kairosapplication.i18n

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.kairosapplication.R
import com.example.taskmodel.constants.TaskConstants

val LocalCurrentLanguage =
    compositionLocalOf<State<LocalizationManager.Language>> { mutableStateOf(LocalizationManager.Language.ZH) }

object LocalizedStrings {

    fun stringFor(
        language: LocalizationManager.Language,
        key: String,
        context: Context,
        vararg formatArgs: Any,
    ): String = I18nResources.stringForKey(context, language, key, *formatArgs)

    fun timeBlockLabelFor(
        blockKey: String,
        language: LocalizationManager.Language,
        context: Context,
    ): String {
        val key = when (blockKey) {
            TaskConstants.TIME_BLOCK_ANYTIME -> "view_time_anytime"
            TaskConstants.TIME_BLOCK_MORNING -> "view_time_morning"
            TaskConstants.TIME_BLOCK_AFTERNOON -> "view_time_afternoon"
            TaskConstants.TIME_BLOCK_EVENING -> "view_time_evening"
            else -> return blockKey
        }
        return stringFor(language, key, context)
    }

    fun timeBlockEmptyHintFor(
        blockKey: String,
        language: LocalizationManager.Language,
        context: Context,
    ): String {
        val key = when (blockKey) {
            TaskConstants.TIME_BLOCK_ANYTIME -> "todo_hint_anytime"
            TaskConstants.TIME_BLOCK_MORNING -> "todo_hint_morning"
            TaskConstants.TIME_BLOCK_AFTERNOON -> "todo_hint_afternoon"
            TaskConstants.TIME_BLOCK_EVENING -> "todo_hint_evening"
            else -> "todo_hint_default"
        }
        return stringFor(language, key, context)
    }

    @Composable
    fun get(key: String, vararg formatArgs: Any): String {
        val ctx = LocalContext.current
        val lang = LocalCurrentLanguage.current.value
        return remember(ctx, lang, key, formatArgs.contentHashCode()) {
            stringFor(lang, key, ctx, *formatArgs)
        }
    }

    @Composable
    fun timeBlockLabel(blockKey: String): String {
        val ctx = LocalContext.current
        val lang = LocalCurrentLanguage.current.value
        return remember(ctx, lang, blockKey) { timeBlockLabelFor(blockKey, lang, ctx) }
    }

    @Composable
    fun timeBlockEmptyHint(blockKey: String): String {
        val ctx = LocalContext.current
        val lang = LocalCurrentLanguage.current.value
        return remember(ctx, lang, blockKey) { timeBlockEmptyHintFor(blockKey, lang, ctx) }
    }

    @Composable
    fun emojiLabel(id: String): String = when (id) {
        "smiley_happy" -> get("emoji_smiley_happy")
        "flower_cool" -> get("emoji_flower_cool")
        "meaning" -> get("emoji_meaning")
        "calm" -> get("emoji_calm")
        "ignorant" -> get("emoji_ignorant")
        "sad_tear" -> get("emoji_sad_tear")
        "annoying" -> get("emoji_annoying")
        "sick" -> get("emoji_sick")
        "mood_bubble" -> get("emoji_mood_bubble")
        else -> id
    }

    @Composable
    fun monthMoodDistributionTitle(monthValue: Int): String {
        val ctx = LocalContext.current
        val lang = LocalCurrentLanguage.current.value
        return remember(ctx, lang, monthValue) {
            I18nResources.applyAppLocale(ctx.applicationContext, lang)
                .getString(R.string.app_monthvalue_48001, monthValue)
        }
    }

    @Composable
    fun exportNotesLabel(count: Int): String {
        val ctx = LocalContext.current
        val lang = LocalCurrentLanguage.current.value
        return remember(ctx, lang, count) {
            I18nResources.applyAppLocale(ctx.applicationContext, lang)
                .getString(R.string.app_count_48601, count)
        }
    }

    @Composable
    fun exportTasksLabel(count: Int): String {
        val ctx = LocalContext.current
        val lang = LocalCurrentLanguage.current.value
        return remember(ctx, lang, count) {
            I18nResources.applyAppLocale(ctx.applicationContext, lang)
                .getString(R.string.app_count_49201, count)
        }
    }

    @Composable
    fun exportMoodsLabel(count: Int): String {
        val ctx = LocalContext.current
        val lang = LocalCurrentLanguage.current.value
        return remember(ctx, lang, count) {
            I18nResources.applyAppLocale(ctx.applicationContext, lang)
                .getString(R.string.app_count_49801, count)
        }
    }

    fun importSuccessMessageFor(
        context: Context,
        language: LocalizationManager.Language,
        notes: Int,
        tasks: Int,
        moods: Int,
        conflicts: Int,
    ): String {
        val ctx = I18nResources.applyAppLocale(context.applicationContext, language)
        return ctx.getString(
            R.string.app_notestasksmoodsconflicts_51001,
            notes,
            tasks,
            moods,
            conflicts,
        )
    }

    fun moodShareSummaryFor(
        context: Context,
        language: LocalizationManager.Language,
        prefix: String,
        lines: String,
    ): String {
        val ctx = I18nResources.applyAppLocale(context.applicationContext, language)
        return ctx.getString(R.string.app_prefixlines_52201, prefix, lines)
    }

    fun moodStatTimesFor(language: LocalizationManager.Language, context: Context): String =
        stringFor(language, "mood_times", context)
}
