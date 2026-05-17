package com.example.kairosapplication.ui.glass

import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.constants.GlassConstants
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GlassTaskCardTest {

    @Test
    fun usesGlassConstantsCornerRadius() {
        assertEquals(24.dp.value, GlassConstants.CornerRadius.value, 0.01f)
    }

    @Test
    fun usesGlassConstantsBlurRadius() {
        assertEquals(30.dp.value, GlassConstants.TaskCardBlurRadius.value, 0.01f)
    }

    @Test
    fun usesGlassConstantsFillAlpha() {
        val alpha = GlassConstants.TaskCardFillAlpha
        assertTrue(alpha >= 0.05f && alpha <= 0.08f)
    }

    @Test
    fun usesGlassConstantsBorderWidth() {
        assertTrue(GlassConstants.GlassBorderWidth.value < 1f)
    }

    @Test
    fun completedTitleColor_isMuted() {
        // Completed tasks should use TextMuted (alpha 0.50)
        assertEquals(0.50f, GlassConstants.TextMuted.alpha, 0.01f)
    }

    @Test
    fun incompleteTitleColor_isPrimary() {
        // Incomplete tasks should use TextPrimary (White)
        assertEquals(
            com.example.kairosapplication.core.ui.constants.GlassConstants.TextPrimary,
            com.example.kairosapplication.core.ui.constants.GlassConstants.TextPrimary
        )
    }

    @Test
    fun descriptionColor_isSecondary() {
        // Description should use TextSecondary (alpha 0.70)
        assertEquals(0.70f, GlassConstants.TextSecondary.alpha, 0.01f)
    }
}
