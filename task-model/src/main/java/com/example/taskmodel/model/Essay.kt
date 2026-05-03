package com.example.taskmodel.model

/**
 * Journal entry (Essay) model.
 */
data class Essay(
    val id: Long,
    val topic: EssayTopic,
    /** Body text */
    val body: String,
    /** Local image URIs, at most [com.example.taskmodel.constants.EssayConstants.MAX_IMAGES] */
    val imageUris: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val createdAtMillis: Long,
    val updatedAtMillis: Long = createdAtMillis,
    val isDraft: Boolean = false,
    val weather: String? = null,
    val mood: String? = null,
    val locationLabel: String? = null,
    /** When true, this essay feeds the home/widget daily quote (one active id; ViewModel owns it) */
    val isDailyQuote: Boolean = false
)

enum class EssayTopic {
    SELF_AWARENESS,
    INTERPERSONAL,
    INTIMACY_FAMILY,
    HEALTH_ENERGY,
    MEANING_EXPLORATION;

    companion object {
        fun fromKey(key: String): EssayTopic? =
            entries.firstOrNull { it.name == key }
    }
}
