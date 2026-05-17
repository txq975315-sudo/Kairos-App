package com.example.kairosapplication.ui.glass

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.constants.GlassBubbleRecipe
import com.example.kairosapplication.core.ui.constants.GlassConstants
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GlassConstantsTest {

    @Test
    fun cornerRadius_is24dp() {
        assertEquals(24.dp, GlassConstants.CornerRadius)
    }

    @Test
    fun taskCardBlurRadius_isZero_forClearGlass() {
        assertEquals(0.dp, GlassConstants.TaskCardBlurRadius)
    }

    @Test
    fun taskCardFillAlpha_matchesActiveRecipe() {
        val alpha = GlassConstants.TaskCardFillAlpha
        when (GlassBubbleRecipe.active) {
            GlassBubbleRecipe.A -> assertEquals(0.22f, alpha, 0.01f)
            GlassBubbleRecipe.B -> assertEquals(0.10f, alpha, 0.01f)
            GlassBubbleRecipe.C -> assertEquals(0f, alpha, 0.01f)
        }
    }

    @Test
    fun routeC_enablesBackdropBlur() {
        if (GlassBubbleRecipe.active == GlassBubbleRecipe.C) {
            assertTrue(GlassConstants.usesBackdropBlur)
            assertTrue(GlassConstants.GlassBackdropBlurRadius <= 10.dp)
            assertTrue(GlassConstants.GlassHazeTintAlpha <= 0.06f)
            assertTrue(GlassConstants.GlassFrostLiftAlpha > 0f)
            assertEquals(0.dp, GlassConstants.GlassShadowElevation)
            assertEquals(0f, GlassConstants.TaskCardSheenAlpha, 0.01f)
        }
    }

    @Test
    fun glassBorder_isStrongerOnRouteB() {
        if (GlassBubbleRecipe.active == GlassBubbleRecipe.B) {
            assertTrue(GlassConstants.GlassBorderAlpha >= 0.28f)
        }
    }

    @Test
    fun atmosphereImageBlurRadius_isMinimal() {
        assertTrue(GlassConstants.AtmosphereImageBlurRadius <= 2.dp)
    }

    @Test
    fun atmosphereDimAlpha_isLight() {
        assertTrue(GlassConstants.AtmosphereDimAlpha in 0.08f..0.16f)
    }

    @Test
    fun textPrimary_isWhite_forTaskCards() {
        assertEquals(Color.White, GlassConstants.TextPrimary)
    }

    @Test
    fun chromeOnLightPrimary_isDark() {
        assertTrue(GlassConstants.ChromeOnLightPrimary.luminance() < 0.35f)
    }

    @Test
    fun useTaskCardTextShadow_onRouteBOrC() {
        val expected = GlassBubbleRecipe.active == GlassBubbleRecipe.B ||
            GlassBubbleRecipe.active == GlassBubbleRecipe.C
        assertEquals(expected, GlassConstants.useTaskCardTextShadow)
    }
}
