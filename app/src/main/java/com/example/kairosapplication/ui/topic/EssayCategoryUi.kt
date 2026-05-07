package com.example.kairosapplication.ui.topic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.ui.components.NoteCardConstants
import com.example.kairosapplication.ui.editor.EditorPlaceholderDefaults
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.model.EssayCategoryConfig
import com.example.taskmodel.model.EssaySecondaryCategoryConfig

object EssayCategoryUi {

    fun mergedSecondaryIds(primary: String, config: EssayCategoryConfig): List<String> =
        config.primaryOrNull(primary)?.secondaries?.map { it.id }.orEmpty()

    fun secondaryEntry(primary: String, id: String, config: EssayCategoryConfig): EssaySecondaryCategoryConfig? =
        config.primaryOrNull(primary)?.secondaries?.firstOrNull { it.id == id }

    fun secondaryDisplayLabel(
        primaryKey: String,
        sec: EssaySecondaryCategoryConfig,
        lang: LocalizationManager.Language,
    ): String {
        val trimmed = sec.name.trim()
        if (trimmed.isNotEmpty()) return trimmed
        return TopicDisplayStrings.secondaryLabel(primaryKey, sec.id, lang)
    }

    fun secondaryGuide(primaryKey: String, secondaryId: String, config: EssayCategoryConfig): String? =
        secondaryEntry(primaryKey, secondaryId, config)?.guide?.trim()?.takeIf { it.isNotEmpty() }

    /** 主课题固定为系统文案，不读取配置中的自定义名称。 */
    fun primaryDisplayName(key: String, @Suppress("UNUSED_PARAMETER") config: EssayCategoryConfig, lang: LocalizationManager.Language): String =
        TopicDisplayStrings.primaryLabel(key, lang)

    /** 主课题 Emoji 固定为内置映射。 */
    fun primaryEmoji(key: String, @Suppress("UNUSED_PARAMETER") config: EssayCategoryConfig): String =
        NoteCardConstants.categoryEmoji(key)

    /** 摘要占位固定为内置引导句。 */
    fun summaryPlaceholder(key: String, @Suppress("UNUSED_PARAMETER") config: EssayCategoryConfig): String =
        if (key == NotePrimaryCategory.FREESTYLE) EditorPlaceholderDefaults.summaryFreestyle
        else EditorPlaceholderDefaults.summaryFor(key)

    /** 正文占位固定为内置引导句。 */
    fun bodyPlaceholder(key: String, @Suppress("UNUSED_PARAMETER") config: EssayCategoryConfig): String =
        if (key == NotePrimaryCategory.FREESTYLE) EditorPlaceholderDefaults.bodyFreestyle
        else EditorPlaceholderDefaults.bodyFor(key)

    fun primaryNavShort(categoryKey: String, config: EssayCategoryConfig, lang: LocalizationManager.Language): String {
        val full = primaryDisplayName(categoryKey, config, lang)
        if (lang == LocalizationManager.Language.ZH) {
            return when {
                full.length <= 4 -> full
                else -> full.take(4)
            }
        }
        return full.trim().split(Regex("\\s+")).firstOrNull().orEmpty().ifBlank { full }
    }
}

@Composable
fun rememberTopicPrimaryLabelWithConfig(categoryKey: String, config: EssayCategoryConfig): String {
    val lang = LocalCurrentLanguage.current.value
    return remember(categoryKey, config, lang) {
        EssayCategoryUi.primaryDisplayName(categoryKey, config, lang)
    }
}

@Composable
fun rememberTopicPrimaryNavShortWithConfig(categoryKey: String, config: EssayCategoryConfig): String {
    val lang = LocalCurrentLanguage.current.value
    return remember(categoryKey, config, lang) {
        EssayCategoryUi.primaryNavShort(categoryKey, config, lang)
    }
}

@Composable
fun rememberTopicSecondaryLabelWithConfig(
    primaryKey: String,
    storedSecondary: String,
    config: EssayCategoryConfig,
): String {
    val lang = LocalCurrentLanguage.current.value
    return remember(primaryKey, storedSecondary, config, lang) {
        val sec = EssayCategoryUi.secondaryEntry(primaryKey, storedSecondary, config)
            ?: EssaySecondaryCategoryConfig(id = storedSecondary)
        EssayCategoryUi.secondaryDisplayLabel(primaryKey, sec, lang)
    }
}
