package com.example.taskmodel.constants

/**
 * Essay [Note] primary category keys: stable, lowercase snake-case storage identifiers.
 * UI copy is localized separately (see app `TopicDisplayStrings` / `LocalizedStrings`); do not persist translated labels here.
 */
object NotePrimaryCategory {
    const val FREESTYLE = "freestyle"
    const val SELF_AWARENESS = "self_awareness"
    const val INTERPERSONAL = "interpersonal"
    const val INTIMACY_FAMILY = "intimacy_family"
    const val SOMATIC_ENERGY = "somatic_energy"
    const val MEANING = "meaning"

    val TOPIC_KEYS: Set<String> = setOf(
        SELF_AWARENESS,
        INTERPERSONAL,
        INTIMACY_FAMILY,
        SOMATIC_ENERGY,
        MEANING
    )

    fun isTopic(category: String): Boolean = category in TOPIC_KEYS
}
