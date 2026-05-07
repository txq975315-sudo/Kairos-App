package com.example.kairosapplication.ui.topic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteSecondaryCategories

/** English storage value for notes without a secondary bucket. */
const val UncategorizedSecondaryEn = "Uncategorized"

/**
 * Essay topic display strings.
 *
 * Primary labels use keys `essay_primary_*` in [LocalizedStrings] only (EN/ZH pairs are fixed there).
 * Official secondaries follow app language; strings not in [NoteSecondaryCategories.defaults] stay as stored.
 */
object TopicDisplayStrings {

    fun primaryLabel(categoryKey: String, lang: LocalizationManager.Language): String {
        val key = when (categoryKey) {
            NotePrimaryCategory.FREESTYLE -> "essay_primary_freestyle"
            NotePrimaryCategory.SELF_AWARENESS -> "essay_primary_self_awareness"
            NotePrimaryCategory.INTERPERSONAL -> "essay_primary_interpersonal"
            NotePrimaryCategory.INTIMACY_FAMILY -> "essay_primary_intimacy_family"
            NotePrimaryCategory.SOMATIC_ENERGY -> "essay_primary_somatic_energy"
            NotePrimaryCategory.MEANING -> "essay_primary_meaning"
            else -> return categoryKey
        }
        return LocalizedStrings.stringFor(lang, key)
    }

    fun secondaryLabel(primaryKey: String, stored: String, lang: LocalizationManager.Language): String {
        val trimmed = stored.trim()
        if (trimmed.isEmpty()) return ""
        if (trimmed.equals(UncategorizedSecondaryEn, ignoreCase = true)) {
            return LocalizedStrings.stringFor(lang, "note_uncategorized")
        }
        val official = NoteSecondaryCategories.defaults[primaryKey].orEmpty()
        if (trimmed !in official) return stored
        val mapKey = secondaryLocalizationKey(trimmed)
        return LocalizedStrings.stringFor(lang, mapKey)
    }

    private fun secondaryLocalizationKey(englishOfficial: String): String =
        "essay_sec_" + englishOfficial.replace(" ", "_")

    /** Compact primary for narrow sidebar: first word (EN) or first chars (ZH). */
    fun primaryNavShort(categoryKey: String, lang: LocalizationManager.Language): String {
        val full = primaryLabel(categoryKey, lang)
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
fun rememberTopicPrimaryLabel(categoryKey: String): String {
    val lang = LocalCurrentLanguage.current.value
    return remember(categoryKey, lang) { TopicDisplayStrings.primaryLabel(categoryKey, lang) }
}

@Composable
fun rememberTopicSecondaryLabel(primaryKey: String, storedSecondary: String): String {
    val lang = LocalCurrentLanguage.current.value
    return remember(primaryKey, storedSecondary, lang) {
        TopicDisplayStrings.secondaryLabel(primaryKey, storedSecondary, lang)
    }
}

@Composable
fun rememberTopicPrimaryNavShort(categoryKey: String): String {
    val lang = LocalCurrentLanguage.current.value
    return remember(categoryKey, lang) { TopicDisplayStrings.primaryNavShort(categoryKey, lang) }
}
