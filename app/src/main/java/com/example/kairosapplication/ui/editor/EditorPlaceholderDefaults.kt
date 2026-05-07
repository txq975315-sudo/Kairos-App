package com.example.kairosapplication.ui.editor

import com.example.taskmodel.constants.NotePrimaryCategory

object EditorPlaceholderDefaults {
    val summaryFreestyle: String = "Optional: briefly summarize…"
    val bodyFreestyle: String = "Write anything you want…"

    fun summaryFor(category: String): String = when (category) {
        NotePrimaryCategory.FREESTYLE -> summaryFreestyle
        NotePrimaryCategory.SELF_AWARENESS -> "What did I do?"
        NotePrimaryCategory.INTERPERSONAL -> "What did I do?"
        NotePrimaryCategory.INTIMACY_FAMILY -> "What did I do?"
        NotePrimaryCategory.SOMATIC_ENERGY -> "What are my body signals?"
        NotePrimaryCategory.MEANING -> "What did I do and why?"
        else -> "Optional…"
    }

    fun bodyFor(category: String): String = when (category) {
        NotePrimaryCategory.FREESTYLE -> bodyFreestyle
        NotePrimaryCategory.SELF_AWARENESS -> "Describe emotions, behaviors, or discoveries…"
        NotePrimaryCategory.INTERPERSONAL -> "Record interaction details and feelings…"
        NotePrimaryCategory.INTIMACY_FAMILY -> "Record conflicts or warm moments…"
        NotePrimaryCategory.SOMATIC_ENERGY -> "Describe body feelings or energy changes…"
        NotePrimaryCategory.MEANING -> "Write your value questions or thoughts…"
        else -> "Write…"
    }
}
