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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteSecondaryCategories

/** Topic display names (EN/ZH) live in [com.example.kairosapplication.i18n.LocalizedStrings] via [com.example.kairosapplication.ui.topic.TopicDisplayStrings]. */
object NoteCardConstants {

    val PRIMARY_CATEGORY_COLOR: Map<String, Color> = mapOf(
        NotePrimaryCategory.FREESTYLE to Color(0xFF9E9E9E),
        NotePrimaryCategory.SELF_AWARENESS to Color(0xFF2196F3),
        NotePrimaryCategory.INTERPERSONAL to Color(0xFFFF9800),
        NotePrimaryCategory.INTIMACY_FAMILY to Color(0xFFE91E63),
        NotePrimaryCategory.SOMATIC_ENERGY to Color(0xFF4CAF50),
        NotePrimaryCategory.MEANING to Color(0xFF9C27B0)
    )

    val PRIMARY_CATEGORY_EMOJI: Map<String, String> = mapOf(
        NotePrimaryCategory.FREESTYLE to "✨",
        NotePrimaryCategory.SELF_AWARENESS to "🧠",
        NotePrimaryCategory.INTERPERSONAL to "👥",
        NotePrimaryCategory.INTIMACY_FAMILY to "❤️",
        NotePrimaryCategory.SOMATIC_ENERGY to "💪",
        NotePrimaryCategory.MEANING to "🔮"
    )

    val MOOD_ICON_MAP: Map<String, ImageVector> = mapOf(
        "happy" to Icons.Filled.SentimentSatisfied,
        "neutral" to Icons.Filled.SentimentNeutral,
        "sad" to Icons.Filled.SentimentDissatisfied,
        "angry" to Icons.Filled.SentimentVeryDissatisfied,
        "excited" to Icons.Filled.EmojiEvents
    )

    val secondaryCategories: Map<String, List<String>> = NoteSecondaryCategories.defaults

    fun mergedSecondaryLabels(primary: String, custom: Map<String, List<String>>): List<String> {
        val base = secondaryCategories[primary].orEmpty()
        val extra = custom[primary].orEmpty()
        return base + extra.filter { e -> base.none { it.equals(e, ignoreCase = true) } }
    }

    fun categoryColor(key: String): Color =
        PRIMARY_CATEGORY_COLOR[key] ?: Color(0xFF9E9E9E)

    fun categoryEmoji(key: String): String =
        PRIMARY_CATEGORY_EMOJI[key] ?: "📝"

    fun categoryIcon(key: String): ImageVector = when (key) {
        NotePrimaryCategory.FREESTYLE -> Icons.Filled.AutoStories
        NotePrimaryCategory.SELF_AWARENESS -> Icons.Filled.Psychology
        NotePrimaryCategory.INTERPERSONAL -> Icons.Filled.Groups
        NotePrimaryCategory.INTIMACY_FAMILY -> Icons.Filled.Favorite
        NotePrimaryCategory.SOMATIC_ENERGY -> Icons.Filled.Spa
        NotePrimaryCategory.MEANING -> Icons.Filled.Explore
        else -> Icons.Filled.AutoStories
    }
}
