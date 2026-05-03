package com.example.taskmodel.constants

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

    fun firstForQuickPublish(primary: String): String =
        defaults[primary]?.firstOrNull()?.takeIf { it.isNotBlank() } ?: "Other"
}
