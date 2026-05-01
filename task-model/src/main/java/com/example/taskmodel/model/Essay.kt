package com.example.taskmodel.model

/**
 * 今日记录（Essay）实体。
 */
data class Essay(
    val id: Long,
    val topic: EssayTopic,
    /** 正文 */
    val body: String,
    /** 本地图片 URI，最多 [com.example.taskmodel.constants.EssayConstants.MAX_IMAGES] 张 */
    val imageUris: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val createdAtMillis: Long,
    val updatedAtMillis: Long = createdAtMillis,
    val isDraft: Boolean = false,
    val weather: String? = null,
    val mood: String? = null,
    val locationLabel: String? = null,
    /** 是否被设为首页/小组件「每日一句」来源（全局仅一条有效，由 ViewModel 维护） */
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
