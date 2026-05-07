package com.example.taskmodel.model

import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteSecondaryCategories
import java.util.Locale

/**
 * Full essay topic taxonomy: six fixed primary keys (see [PRIMARY_KEYS_ORDER]) and up to eight
 * secondaries per topic primary. Persisted as JSON in [com.example.taskmodel.repository.TaskRepository].
 *
 * [EssaySecondaryCategoryConfig.id] is stored on [Note.secondaryCategory]. Built-in presets use
 * their English default spellings as ids for backward compatibility.
 */
data class EssayCategoryConfig(
    val primaries: List<EssayPrimaryCategoryConfig>,
) {
    fun primaryOrNull(key: String): EssayPrimaryCategoryConfig? =
        primaries.firstOrNull { it.key == key }

    fun primary(key: String): EssayPrimaryCategoryConfig =
        primaryOrNull(key) ?: error("Missing primary category config for key=$key")

    fun updatePrimary(key: String, block: (EssayPrimaryCategoryConfig) -> EssayPrimaryCategoryConfig): EssayCategoryConfig {
        val next = primaries.map { if (it.key == key) block(it) else it }
        return copy(primaries = next)
    }

    companion object {
        val PRIMARY_KEYS_ORDER: List<String> = listOf(
            NotePrimaryCategory.FREESTYLE,
            NotePrimaryCategory.SELF_AWARENESS,
            NotePrimaryCategory.INTERPERSONAL,
            NotePrimaryCategory.INTIMACY_FAMILY,
            NotePrimaryCategory.SOMATIC_ENERGY,
            NotePrimaryCategory.MEANING,
        )

        /** Seed from built-in defaults plus legacy custom-only strings per primary. */
        fun buildInitial(legacyCustomExtras: Map<String, List<String>>): EssayCategoryConfig {
            val primaries = PRIMARY_KEYS_ORDER.map { key ->
                val defaults = NoteSecondaryCategories.defaults[key].orEmpty()
                val extras = legacyCustomExtras[key].orEmpty()
                val builtIns = defaults.map {
                    EssaySecondaryCategoryConfig(id = it, name = "", guide = null)
                }
                val usedLower = builtIns.map { it.id.lowercase(Locale.US) }.toMutableSet()
                val extraConfigs = extras.mapNotNull { raw ->
                    val t = raw.trim()
                    if (t.isEmpty()) return@mapNotNull null
                    if (t.lowercase(Locale.US) in usedLower) return@mapNotNull null
                    usedLower.add(t.lowercase(Locale.US))
                    EssaySecondaryCategoryConfig(id = t, name = t, guide = null)
                }
                val merged = (builtIns + extraConfigs).take(8)
                EssayPrimaryCategoryConfig(
                    key = key,
                    displayName = "",
                    emoji = "",
                    summaryGuide = "",
                    bodyPlaceholder = "",
                    secondaries = merged,
                )
            }
            return EssayCategoryConfig(primaries)
        }
    }
}

data class EssayPrimaryCategoryConfig(
    val key: String,
    val displayName: String = "",
    val emoji: String = "",
    /** Behavior-summary field placeholder (“判断引导句”). */
    val summaryGuide: String = "",
    /** Body field placeholder. */
    val bodyPlaceholder: String = "",
    val secondaries: List<EssaySecondaryCategoryConfig> = emptyList(),
)

data class EssaySecondaryCategoryConfig(
    /** Persisted on notes as [Note.secondaryCategory]. */
    val id: String,
    /** Display label; if blank, UI falls back to localized preset for official ids. */
    val name: String = "",
    val guide: String? = null,
)
