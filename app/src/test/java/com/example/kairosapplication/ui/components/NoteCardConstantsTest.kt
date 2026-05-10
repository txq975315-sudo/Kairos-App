package com.example.kairosapplication.ui.components

import androidx.compose.ui.graphics.Color
import com.example.taskmodel.constants.NotePrimaryCategory
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * TDD tests for the new Essay theme visual spec.
 * Verifies updated color mappings, emoji mappings,
 * and new secondary/background color functions in NoteCardConstants.
 */
class NoteCardConstantsTest {

    // ── Primary Theme Colors ──────────────────────────────────────────

    @Test fun primaryColor_freestyle() {
        assertEquals(Color(0xFF7AC943), NoteCardConstants.categoryColor(NotePrimaryCategory.FREESTYLE))
    }

    @Test fun primaryColor_selfAwareness() {
        assertEquals(Color(0xFF6A5AF9), NoteCardConstants.categoryColor(NotePrimaryCategory.SELF_AWARENESS))
    }

    @Test fun primaryColor_interpersonal() {
        assertEquals(Color(0xFF00BFA5), NoteCardConstants.categoryColor(NotePrimaryCategory.INTERPERSONAL))
    }

    @Test fun primaryColor_intimacyFamily() {
        assertEquals(Color(0xFFFF4D6D), NoteCardConstants.categoryColor(NotePrimaryCategory.INTIMACY_FAMILY))
    }

    @Test fun primaryColor_somaticEnergy() {
        assertEquals(Color(0xFFFF9A00), NoteCardConstants.categoryColor(NotePrimaryCategory.SOMATIC_ENERGY))
    }

    @Test fun primaryColor_meaning() {
        assertEquals(Color(0xFF2563EB), NoteCardConstants.categoryColor(NotePrimaryCategory.MEANING))
    }

    // ── Primary Emoji ─────────────────────────────────────────────────

    @Test fun emoji_freestyle_isLeaf() {
        assertEquals("🍃", NoteCardConstants.categoryEmoji(NotePrimaryCategory.FREESTYLE))
    }

    @Test fun emoji_selfAwareness_isLightbulb() {
        assertEquals("💡", NoteCardConstants.categoryEmoji(NotePrimaryCategory.SELF_AWARENESS))
    }

    @Test fun emoji_interpersonal_isPeople() {
        assertEquals("👥", NoteCardConstants.categoryEmoji(NotePrimaryCategory.INTERPERSONAL))
    }

    @Test fun emoji_intimacyFamily_isHeart() {
        assertEquals("❤️", NoteCardConstants.categoryEmoji(NotePrimaryCategory.INTIMACY_FAMILY))
    }

    @Test fun emoji_somaticEnergy_isMeditation() {
        assertEquals("🧘", NoteCardConstants.categoryEmoji(NotePrimaryCategory.SOMATIC_ENERGY))
    }

    @Test fun emoji_meaning_isCompass() {
        assertEquals("🧭", NoteCardConstants.categoryEmoji(NotePrimaryCategory.MEANING))
    }

    // ── Secondary Colors (new) ───────────────────────────────────────

    @Test fun secondaryColor_freestyle() {
        assertEquals(Color(0xFFCFF7B3), NoteCardConstants.categorySecondaryColor(NotePrimaryCategory.FREESTYLE))
    }

    @Test fun secondaryColor_selfAwareness() {
        assertEquals(Color(0xFFCBB9FF), NoteCardConstants.categorySecondaryColor(NotePrimaryCategory.SELF_AWARENESS))
    }

    @Test fun secondaryColor_interpersonal() {
        assertEquals(Color(0xFFB2F2E5), NoteCardConstants.categorySecondaryColor(NotePrimaryCategory.INTERPERSONAL))
    }

    @Test fun secondaryColor_intimacyFamily() {
        assertEquals(Color(0xFFFFB3C1), NoteCardConstants.categorySecondaryColor(NotePrimaryCategory.INTIMACY_FAMILY))
    }

    @Test fun secondaryColor_somaticEnergy() {
        assertEquals(Color(0xFFFFD580), NoteCardConstants.categorySecondaryColor(NotePrimaryCategory.SOMATIC_ENERGY))
    }

    @Test fun secondaryColor_meaning() {
        assertEquals(Color(0xFFA5C8FF), NoteCardConstants.categorySecondaryColor(NotePrimaryCategory.MEANING))
    }

    // ── Background Colors (new) ──────────────────────────────────────

    @Test fun backgroundColor_freestyle() {
        assertEquals(Color(0xFFF3FFE6), NoteCardConstants.categoryBackgroundColor(NotePrimaryCategory.FREESTYLE))
    }

    @Test fun backgroundColor_selfAwareness() {
        assertEquals(Color(0xFFF2ECFF), NoteCardConstants.categoryBackgroundColor(NotePrimaryCategory.SELF_AWARENESS))
    }

    @Test fun backgroundColor_interpersonal() {
        assertEquals(Color(0xFFE6FBF6), NoteCardConstants.categoryBackgroundColor(NotePrimaryCategory.INTERPERSONAL))
    }

    @Test fun backgroundColor_intimacyFamily() {
        assertEquals(Color(0xFFFFF0F3), NoteCardConstants.categoryBackgroundColor(NotePrimaryCategory.INTIMACY_FAMILY))
    }

    @Test fun backgroundColor_somaticEnergy() {
        assertEquals(Color(0xFFFFF7E6), NoteCardConstants.categoryBackgroundColor(NotePrimaryCategory.SOMATIC_ENERGY))
    }

    @Test fun backgroundColor_meaning() {
        assertEquals(Color(0xFFEAF2FF), NoteCardConstants.categoryBackgroundColor(NotePrimaryCategory.MEANING))
    }

    // ── Fallback behavior ─────────────────────────────────────────────

    @Test fun categoryColor_unknownKey_returnsFallback() {
        assertEquals(Color(0xFF7AC943), NoteCardConstants.categoryColor("nonexistent"))
    }

    @Test fun categoryEmoji_unknownKey_returnsFallback() {
        assertEquals("📝", NoteCardConstants.categoryEmoji("nonexistent"))
    }

    @Test fun categorySecondaryColor_unknownKey_returnsFallback() {
        assertEquals(Color(0xFFCFF7B3), NoteCardConstants.categorySecondaryColor("nonexistent"))
    }

    @Test fun categoryBackgroundColor_unknownKey_returnsFallback() {
        assertEquals(Color(0xFFF3FFE6), NoteCardConstants.categoryBackgroundColor("nonexistent"))
    }
}
