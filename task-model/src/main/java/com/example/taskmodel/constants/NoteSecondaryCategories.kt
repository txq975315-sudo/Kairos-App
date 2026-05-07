package com.example.taskmodel.constants

/**
 * Single source of truth for **official** essay secondary topics (English storage strings).
 *
 * All persistence paths must store values that are either:
 * - exactly one of [defaults] for that primary, or
 * - a user-defined string (not in [defaults]).
 *
 * [canonicalSecondary] aligns legacy rows and imports with [defaults]; UI must not duplicate that logic.
 */
object NoteSecondaryCategories {

    val defaults: Map<String, List<String>> = mapOf(
        NotePrimaryCategory.SELF_AWARENESS to listOf(
            "Emotional awareness",
            "Behavioral habits",
            "Thinking blind spots",
            "Value exploration"
        ),
        NotePrimaryCategory.INTERPERSONAL to listOf(
            "Workplace dynamics",
            "Friendship issues",
            "Stranger encounters",
            "Social anxiety"
        ),
        NotePrimaryCategory.INTIMACY_FAMILY to listOf(
            "Partner conflicts",
            "Parenting struggles",
            "Family boundaries",
            "Love language"
        ),
        NotePrimaryCategory.SOMATIC_ENERGY to listOf(
            "Energy levels",
            "Sleep quality",
            "Physical pain",
            "Exercise habits"
        ),
        NotePrimaryCategory.MEANING to listOf(
            "Career direction",
            "Life goals",
            "Meaning questions",
            "Self-actualization"
        ),
        NotePrimaryCategory.FREESTYLE to emptyList()
    )

    /**
     * Old DB / seed / export strings that referred to an official preset but did not use the exact
     * spelling in [defaults]. Values are the canonical storage string from [defaults] (same primary).
     */
    private val deprecatedStoredOfficialSecondary: Map<Pair<String, String>, String> by lazy {
        val d = defaults
        mapOf(
            (NotePrimaryCategory.SELF_AWARENESS to "Emotional trigger event") to
                d.getValue(NotePrimaryCategory.SELF_AWARENESS)[0],
            (NotePrimaryCategory.SELF_AWARENESS to "Habit experiment record") to
                d.getValue(NotePrimaryCategory.SELF_AWARENESS)[1],
            (NotePrimaryCategory.INTERPERSONAL to "Boundary breakthrough event") to
                d.getValue(NotePrimaryCategory.INTERPERSONAL)[0],
            (NotePrimaryCategory.INTIMACY_FAMILY to "Power struggle scene") to
                d.getValue(NotePrimaryCategory.INTIMACY_FAMILY)[0],
            (NotePrimaryCategory.INTIMACY_FAMILY to "Emotional flow record") to
                d.getValue(NotePrimaryCategory.INTIMACY_FAMILY)[0],
            (NotePrimaryCategory.INTIMACY_FAMILY to "Partner Conflict") to
                d.getValue(NotePrimaryCategory.INTIMACY_FAMILY)[0],
            (NotePrimaryCategory.SOMATIC_ENERGY to "Body signal log") to
                d.getValue(NotePrimaryCategory.SOMATIC_ENERGY)[1],
            (NotePrimaryCategory.MEANING to "Value ordering event") to
                d.getValue(NotePrimaryCategory.MEANING)[1],
        )
    }

    /**
     * Normalize secondary for storage/domain: official presets use the exact spelling from [defaults];
     * unknown strings are left as-is (user-defined topics).
     */
    fun canonicalSecondary(primaryCategory: String, storedSecondary: String?): String {
        val trimmed = storedSecondary?.trim().orEmpty()
        if (trimmed.isEmpty()) return ""
        if (!NotePrimaryCategory.isTopic(primaryCategory)) return trimmed
        val allowed = defaults[primaryCategory].orEmpty()
        if (allowed.isEmpty()) return trimmed
        allowed.firstOrNull { it.equals(trimmed, ignoreCase = true) }?.let { return it }
        deprecatedStoredOfficialSecondary[primaryCategory to trimmed]?.let { return it }
        return trimmed
    }

    /** Pairs for Room `UPDATE notes SET secondary_category = … WHERE …`. */
    fun migrationSecondaryRenames(): List<Triple<String, String, String>> =
        deprecatedStoredOfficialSecondary.entries.map { (primaryAndOld, canonical) ->
            Triple(primaryAndOld.first, primaryAndOld.second, canonical)
        }

    fun firstForQuickPublish(primary: String): String =
        defaults[primary]?.firstOrNull()?.takeIf { it.isNotBlank() } ?: "Other"
}
