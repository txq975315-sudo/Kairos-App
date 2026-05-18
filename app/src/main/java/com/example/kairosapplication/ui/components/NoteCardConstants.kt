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
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import androidx.compose.runtime.Composable
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteSecondaryCategories

/** Topic display names (EN/ZH) live in [com.example.kairosapplication.i18n.LocalizedStrings] via [com.example.kairosapplication.ui.topic.TopicDisplayStrings]. */
object NoteCardConstants {

    // ── Primary Theme Colors (主题色) ────────────────────────────────
    // Design spec: 六大主题视觉主题色示意图

    val PRIMARY_CATEGORY_COLOR: Map<String, Color> = mapOf(
        NotePrimaryCategory.FREESTYLE to Color(0xFF7AC943),
        NotePrimaryCategory.SELF_AWARENESS to Color(0xFF6A5AF9),
        NotePrimaryCategory.INTERPERSONAL to Color(0xFF00BFA5),
        NotePrimaryCategory.INTIMACY_FAMILY to Color(0xFFFF4D6D),
        NotePrimaryCategory.SOMATIC_ENERGY to Color(0xFFFF9A00),
        NotePrimaryCategory.MEANING to Color(0xFF2563EB)
    )

    // ── Secondary Colors (辅助色) ────────────────────────────────────

    val PRIMARY_CATEGORY_SECONDARY_COLOR: Map<String, Color> = mapOf(
        NotePrimaryCategory.FREESTYLE to Color(0xFFCFF7B3),
        NotePrimaryCategory.SELF_AWARENESS to Color(0xFFCBB9FF),
        NotePrimaryCategory.INTERPERSONAL to Color(0xFFB2F2E5),
        NotePrimaryCategory.INTIMACY_FAMILY to Color(0xFFFFB3C1),
        NotePrimaryCategory.SOMATIC_ENERGY to Color(0xFFFFD580),
        NotePrimaryCategory.MEANING to Color(0xFFA5C8FF)
    )

    // ── Background Colors (浅色背景) ─────────────────────────────────

    val PRIMARY_CATEGORY_BACKGROUND_COLOR: Map<String, Color> = mapOf(
        NotePrimaryCategory.FREESTYLE to Color(0xFFF3FFE6),
        NotePrimaryCategory.SELF_AWARENESS to Color(0xFFF2ECFF),
        NotePrimaryCategory.INTERPERSONAL to Color(0xFFE6FBF6),
        NotePrimaryCategory.INTIMACY_FAMILY to Color(0xFFFFF0F3),
        NotePrimaryCategory.SOMATIC_ENERGY to Color(0xFFFFF7E6),
        NotePrimaryCategory.MEANING to Color(0xFFEAF2FF)
    )

    // ── Emoji ─────────────────────────────────────────────────────────

    val PRIMARY_CATEGORY_EMOJI: Map<String, String> = mapOf(
        NotePrimaryCategory.FREESTYLE to "🍃",
        NotePrimaryCategory.SELF_AWARENESS to "💡",
        NotePrimaryCategory.INTERPERSONAL to "👥",
        NotePrimaryCategory.INTIMACY_FAMILY to "❤️",
        NotePrimaryCategory.SOMATIC_ENERGY to "🧘",
        NotePrimaryCategory.MEANING to "🧭"
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
        PRIMARY_CATEGORY_COLOR[key] ?: Color(0xFF7AC943)

    fun categorySecondaryColor(key: String): Color =
        PRIMARY_CATEGORY_SECONDARY_COLOR[key] ?: Color(0xFFCFF7B3)

    fun categoryBackgroundColor(key: String): Color =
        PRIMARY_CATEGORY_BACKGROUND_COLOR[key] ?: Color(0xFFF3FFE6)

    fun categoryEmoji(key: String): String =
        PRIMARY_CATEGORY_EMOJI[key] ?: "📝"

    /** Shared emoji tile wash — project-card style, slightly more transparent. */
    private const val CategoryIconTileAlpha = 0.45f

    fun categoryIconTileBackground(): Color =
        AppColors.MorningBackground.copy(alpha = CategoryIconTileAlpha)

    /** Thin rail between left-side note times (card timeline). */
    val TimelineConnectorColorGlass = Color.White.copy(alpha = 0.32f)
    val TimelineConnectorColorClassic = Color(0xFFE0E0E0)

    @Composable
    fun timelineConnectorColor(): Color =
        if (LocalAppUiTheme.current == AppUiTheme.Classic) {
            TimelineConnectorColorClassic
        } else {
            TimelineConnectorColorGlass
        }

    fun categoryIcon(key: String): ImageVector = when (key) {
        NotePrimaryCategory.FREESTYLE -> Icons.Filled.AutoStories
        NotePrimaryCategory.SELF_AWARENESS -> Icons.Filled.Psychology
        NotePrimaryCategory.INTERPERSONAL -> Icons.Filled.Groups
        NotePrimaryCategory.INTIMACY_FAMILY -> Icons.Filled.Favorite
        NotePrimaryCategory.SOMATIC_ENERGY -> Icons.Filled.Spa
        NotePrimaryCategory.MEANING -> Icons.Filled.Explore
        else -> Icons.Filled.AutoStories
    }

    /**
     * Trailing self-review / comment appended after the main note body.
     * @param comment User-visible comment only (no timestamp prefix).
     * @param timestampLine Machine-friendly time for expanded row (e.g. `2026-05-10 18:29`).
     */
    data class ParsedReview(
        val comment: String,
        val timestampLine: String,
    )

    /**
     * Splits note body into main content and trailing review.
     * Standard storage: `\n\n{yyyy-MM-dd HH:mm}\n{comment}` ([appendReviewCommentToNote]).
     * Legacy UI strings sometimes used a labeled line with `复盘` / `Review` before the timestamp.
     */
    private val REVIEW_STANDARD =
        Regex("""\n\n(\d{4}-\d{2}-\d{2} \d{2}:\d{2})\n(.+)$""", RegexOption.DOT_MATCHES_ALL)
    /** e.g. `\n\n— 复盘 · 2026-05-10 18:29\ncomment` or leading `——`. */
    private val REVIEW_LEGACY_LABELED = Regex(
        """\n\n[^\n]*(?:复盘|Review)[^\n]*·\s*(\d{4}-\d{2}-\d{2}(?:\s+\d{2}:\d{2})?)[^\n]*\n([\s\S]+)$""",
        setOf(RegexOption.DOT_MATCHES_ALL, RegexOption.IGNORE_CASE),
    )

    fun splitReviewFromBody(body: String): Pair<String, ParsedReview?> {
        REVIEW_STANDARD.find(body)?.let { m ->
            val mainBody = body.substring(0, m.range.first).trimEnd()
            val time = m.groupValues[1]
            val comment = m.groupValues[2].trim()
            if (comment.isNotEmpty()) return mainBody to ParsedReview(comment, time)
        }
        REVIEW_LEGACY_LABELED.find(body)?.let { m ->
            val mainBody = body.substring(0, m.range.first).trimEnd()
            val time = m.groupValues[1].trim()
            val comment = m.groupValues[2].trim()
            if (comment.isNotEmpty()) return mainBody to ParsedReview(comment, time)
        }
        return body to null
    }
}
