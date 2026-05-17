package com.example.kairosapplication.ui.glass

import androidx.compose.ui.graphics.Color
import com.example.kairosapplication.core.ui.constants.GlassConstants
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class GlassThemeTest {

    @Test
    fun defaultPrimary_isWhite() {
        val colors = GlassTextColors()
        assertEquals(GlassConstants.TextPrimary, colors.primary)
    }

    @Test
    fun defaultSecondary_alpha70() {
        val colors = GlassTextColors()
        assertEquals(0.70f, colors.secondary.alpha, 0.01f)
    }

    @Test
    fun defaultMuted_alpha50() {
        val colors = GlassTextColors()
        assertEquals(0.50f, colors.muted.alpha, 0.01f)
    }

    @Test
    fun customPrimary_overridesDefault() {
        val customColor = Color.Red
        val colors = GlassTextColors(primary = customColor)
        assertEquals(customColor, colors.primary)
        // secondary and muted should still be defaults
        assertEquals(GlassConstants.TextSecondary, colors.secondary)
        assertEquals(GlassConstants.TextMuted, colors.muted)
    }
}
