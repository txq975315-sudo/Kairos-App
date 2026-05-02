package com.example.kairosapplication.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.Spa
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.taskmodel.constants.NotePrimaryCategory

/**
 * Essay NoteCard：主课题中文名、心情键到图标（正文里也可能是 emoji，由卡片层解析）。
 */
object NoteCardConstants {

    val PRIMARY_CATEGORY_MAP: Map<String, String> = mapOf(
        NotePrimaryCategory.FREESTYLE to "自由记录",
        NotePrimaryCategory.SELF_AWARENESS to "自我觉察",
        NotePrimaryCategory.INTERPERSONAL to "人际关系",
        NotePrimaryCategory.INTIMACY_FAMILY to "亲密家庭",
        NotePrimaryCategory.SOMATIC_ENERGY to "身体能量",
        NotePrimaryCategory.MEANING to "意义探索"
    )

    val MOOD_ICON_MAP: Map<String, ImageVector> = mapOf(
        "happy" to Icons.Default.SentimentSatisfied,
        "neutral" to Icons.Default.SentimentNeutral,
        "sad" to Icons.Default.SentimentDissatisfied,
        "angry" to Icons.Default.SentimentVeryDissatisfied,
        "excited" to Icons.Default.EmojiEvents
    )

    fun primaryCategoryLabel(key: String): String =
        PRIMARY_CATEGORY_MAP[key] ?: key

    fun categoryIcon(key: String): ImageVector = when (key) {
        NotePrimaryCategory.FREESTYLE -> Icons.Default.AutoStories
        NotePrimaryCategory.SELF_AWARENESS -> Icons.Default.Psychology
        NotePrimaryCategory.INTERPERSONAL -> Icons.Default.Groups
        NotePrimaryCategory.INTIMACY_FAMILY -> Icons.Default.Favorite
        NotePrimaryCategory.SOMATIC_ENERGY -> Icons.Default.Spa
        NotePrimaryCategory.MEANING -> Icons.Default.Explore
        else -> Icons.Default.AutoStories
    }
}
