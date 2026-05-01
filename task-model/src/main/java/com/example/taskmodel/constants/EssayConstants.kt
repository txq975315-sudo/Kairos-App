package com.example.taskmodel.constants

import com.example.taskmodel.model.EssayTopic

object EssayConstants {
    const val MAX_IMAGES = 4

    val TOPIC_LABEL: Map<EssayTopic, String> = mapOf(
        EssayTopic.SELF_AWARENESS to "自我认知",
        EssayTopic.INTERPERSONAL to "人际互动",
        EssayTopic.INTIMACY_FAMILY to "亲密与家庭",
        EssayTopic.HEALTH_ENERGY to "健康与能量",
        EssayTopic.MEANING_EXPLORATION to "意义探索"
    )

    /** 选中课题后正文区引导占位（行为摘要） */
    val TOPIC_GUIDE_PLACEHOLDER: Map<EssayTopic, String> = mapOf(
        EssayTopic.SELF_AWARENESS to "行为摘要：这件事是否让我重新认识自己？",
        EssayTopic.INTERPERSONAL to "行为摘要：互动中我的感受与回应是什么？",
        EssayTopic.INTIMACY_FAMILY to "行为摘要：这段关系里我在意的是什么？",
        EssayTopic.HEALTH_ENERGY to "行为摘要：身体与情绪给出了什么信号？",
        EssayTopic.MEANING_EXPLORATION to "行为摘要：这件事对我意味着什么？"
    )

    fun topicLabel(topic: EssayTopic): String = TOPIC_LABEL[topic] ?: topic.name

    fun guideFor(topic: EssayTopic): String =
        TOPIC_GUIDE_PLACEHOLDER[topic] ?: ""
}
