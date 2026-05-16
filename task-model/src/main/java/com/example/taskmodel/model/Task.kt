package com.example.taskmodel.model

import java.time.LocalDate

data class Task(
    val id: Int,
    val title: String,
    val description: String = "",
    val timeBlock: String,
    val urgency: Int,
    val label: String? = null,
    val taskDate: LocalDate = LocalDate.now(),
    val repeatRule: String = "NONE",
    val isCompleted: Boolean = false,
    val emojiImage: String? = null,
    val localImageUri: String? = null,
    /** Local wall-clock time `HH:mm` for a one-shot reminder on [taskDate]; null = off. */
    val reminderTime: String? = null,
)
