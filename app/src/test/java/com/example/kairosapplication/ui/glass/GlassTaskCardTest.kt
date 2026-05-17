package com.example.kairosapplication.ui.glass

import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.constants.GlassBubbleRecipe
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
    fun usesZeroBlur_onDarkBubble() {
        assertEquals(0.dp, GlassConstants.TaskCardBlurRadius)
    }

    @Test
    fun fillAlpha_matchesActiveRecipe() {
        val alpha = GlassConstants.TaskCardFillAlpha
        when (GlassBubbleRecipe.active) {
            GlassBubbleRecipe.A -> assertEquals(0.22f, alpha, 0.01f)
            GlassBubbleRecipe.B -> assertEquals(0.10f, alpha, 0.01f)
            GlassBubbleRecipe.C -> assertEquals(0f, alpha, 0.01f)
        }
    }

    @Test
    fun completedTitleColor_isMuted() {
        assertEquals(0.50f, GlassConstants.TextMuted.alpha, 0.01f)
    }

    @Test
    fun incompleteTitleColor_isPrimaryWhite() {
        assertEquals(GlassConstants.TextPrimary, GlassConstants.TextPrimary)
    }

    @Test
    fun descriptionColor_isSecondary() {
        assertEquals(0.70f, GlassConstants.TextSecondary.alpha, 0.01f)
    }
}
