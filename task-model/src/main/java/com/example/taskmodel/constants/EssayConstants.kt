package com.example.taskmodel.constants

import com.example.taskmodel.model.EssayTopic

object EssayConstants {
    const val MAX_IMAGES = 4

    val TOPIC_LABEL: Map<EssayTopic, String> = mapOf(
        EssayTopic.SELF_AWARENESS to "Self-awareness",
        EssayTopic.INTERPERSONAL to "Interpersonal",
        EssayTopic.INTIMACY_FAMILY to "Intimacy & family",
        EssayTopic.HEALTH_ENERGY to "Health & energy",
        EssayTopic.MEANING_EXPLORATION to "Meaning & exploration"
    )

    /** Placeholder for the behavior-summary field when a topic is selected. */
    val TOPIC_GUIDE_PLACEHOLDER: Map<EssayTopic, String> = mapOf(
        EssayTopic.SELF_AWARENESS to
            "Behavior summary: What did this help me see about myself?",
        EssayTopic.INTERPERSONAL to
            "Behavior summary: What did I feel and how did I respond in this interaction?",
        EssayTopic.INTIMACY_FAMILY to
            "Behavior summary: What matters to me in this relationship?",
        EssayTopic.HEALTH_ENERGY to
            "Behavior summary: What signals did my body and mood give?",
        EssayTopic.MEANING_EXPLORATION to
            "Behavior summary: What might this mean for me?"
    )

    fun topicLabel(topic: EssayTopic): String = TOPIC_LABEL[topic] ?: topic.name

    fun guideFor(topic: EssayTopic): String =
        TOPIC_GUIDE_PLACEHOLDER[topic] ?: ""
}
