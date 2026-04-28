package com.example.taskmodel.constants

object TaskConstants {
    const val TIME_BLOCK_ANYTIME = "ANYTIME"
    const val TIME_BLOCK_MORNING = "MORNING"
    const val TIME_BLOCK_AFTERNOON = "AFTERNOON"
    const val TIME_BLOCK_EVENING = "EVENING"

    val TIME_BLOCKS = listOf(
        TIME_BLOCK_ANYTIME,
        TIME_BLOCK_MORNING,
        TIME_BLOCK_AFTERNOON,
        TIME_BLOCK_EVENING
    )

    const val URGENCY_URGENT = 0
    const val URGENCY_HIGH = 1
    const val URGENCY_NORMAL = 2
    const val URGENCY_LOW = 3

    val URGENCY_LEVELS = mapOf(
        URGENCY_URGENT to "紧急",
        URGENCY_HIGH to "重要",
        URGENCY_NORMAL to "普通",
        URGENCY_LOW to "低优先级"
    )

    const val LABEL_NONE = "None"
    const val LABEL_WORK = "Work"
    const val LABEL_HABIT = "Habit"
    const val LABEL_STUDY = "Study"
    const val LABEL_LIFE = "Life"
    const val LABEL_EXERCISE = "Exercise"
    const val LABEL_TRAVEL = "Travel"
    const val LABEL_CREATE_NEW = "Create New"

    val LABEL_OPTIONS = listOf(
        LABEL_NONE,
        LABEL_WORK,
        LABEL_HABIT,
        LABEL_STUDY,
        LABEL_LIFE,
        LABEL_EXERCISE,
        LABEL_TRAVEL,
        LABEL_CREATE_NEW
    )

    val LABEL_ICONS = mapOf(
        LABEL_NONE to "⚪",
        LABEL_WORK to "💼",
        LABEL_HABIT to "🔄",
        LABEL_STUDY to "📚",
        LABEL_LIFE to "🏡",
        LABEL_EXERCISE to "🏃",
        LABEL_TRAVEL to "✈️",
        LABEL_CREATE_NEW to "➕"
    )

    const val REPEAT_RULE_NONE = "NONE"
    const val REPEAT_RULE_DAILY = "DAILY"
    const val REPEAT_RULE_WEEKLY = "WEEKLY"
    const val REPEAT_RULE_MONTHLY = "MONTHLY"

    val REPEAT_RULES = listOf(
        REPEAT_RULE_NONE,
        REPEAT_RULE_DAILY,
        REPEAT_RULE_WEEKLY,
        REPEAT_RULE_MONTHLY
    )
}
