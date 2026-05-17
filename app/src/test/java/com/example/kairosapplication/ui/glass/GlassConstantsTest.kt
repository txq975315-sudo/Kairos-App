package com.example.kairosapplication.ui.glass

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.constants.GlassConstants
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GlassConstantsTest {

    // ═══════════════════════════════════════════════════════════
    //  圆角体系
    // ═══════════════════════════════════════════════════════════

    @Test
    fun cornerRadius_is24dp() {
        assertEquals(24.dp, GlassConstants.CornerRadius)
    }

    @Test
    fun cornerRadius_isPositive() {
        assertTrue(GlassConstants.CornerRadius > 0.dp)
    }

    // ═══════════════════════════════════════════════════════════
    //  模糊半径
    // ═══════════════════════════════════════════════════════════

    @Test
    fun taskCardBlurRadius_is30dp() {
        assertEquals(30.dp, GlassConstants.TaskCardBlurRadius)
    }

    @Test
    fun bottomNavBlurRadius_is40dp() {
        assertEquals(40.dp, GlassConstants.BottomNavBlurRadius)
    }

    @Test
    fun timeBlockBlurRadius_between20And30() {
        val blur = GlassConstants.TimeBlockBlurRadius
        assertTrue(blur >= 20.dp && blur <= 30.dp)
    }

    @Test
    fun taskCardBlurRadius_lessThanBottomNav() {
        assertTrue(GlassConstants.TaskCardBlurRadius < GlassConstants.BottomNavBlurRadius)
    }

    // ═══════════════════════════════════════════════════════════
    //  半透明填充
    // ═══════════════════════════════════════════════════════════

    @Test
    fun taskCardFillAlpha_between5And8() {
        val alpha = GlassConstants.TaskCardFillAlpha
        assertTrue(alpha >= 0.05f && alpha <= 0.08f)
    }

    @Test
    fun bottomNavFillAlpha_between5And10() {
        val alpha = GlassConstants.BottomNavFillAlpha
        assertTrue(alpha >= 0.05f && alpha <= 0.10f)
    }

    @Test
    fun timeBlockFillAlpha_between3And6() {
        val alpha = GlassConstants.TimeBlockFillAlpha
        assertTrue(alpha >= 0.03f && alpha <= 0.06f)
    }

    // ═══════════════════════════════════════════════════════════
    //  边框
    // ═══════════════════════════════════════════════════════════

    @Test
    fun glassBorderWidth_below1dp() {
        assertTrue(GlassConstants.GlassBorderWidth < 1.dp)
    }

    @Test
    fun glassBorderAlpha_below12() {
        assertTrue(GlassConstants.GlassBorderAlpha < 0.12f)
    }

    // ═══════════════════════════════════════════════════════════
    //  发光 / 阴影
    // ═══════════════════════════════════════════════════════════

    @Test
    fun glowElevation_between4And6() {
        val elevation = GlassConstants.GlowElevation
        assertTrue(elevation >= 4.dp && elevation <= 6.dp)
    }

    @Test
    fun selectedGlowElevation_between6And8() {
        val elevation = GlassConstants.SelectedGlowElevation
        assertTrue(elevation >= 6.dp && elevation <= 8.dp)
    }

    @Test
    fun shadowAmbientAlpha_below10() {
        assertTrue(GlassConstants.ShadowAmbientAlpha < 0.10f)
    }

    // ═══════════════════════════════════════════════════════════
    //  文字颜色（白色系）
    // ═══════════════════════════════════════════════════════════

    @Test
    fun textPrimary_isWhite() {
        assertEquals(Color.White, GlassConstants.TextPrimary)
    }

    @Test
    fun textSecondaryAlpha_is70() {
        assertEquals(0.70f, GlassConstants.TextSecondary.alpha, 0.01f)
    }

    @Test
    fun textMutedAlpha_is50() {
        assertEquals(0.50f, GlassConstants.TextMuted.alpha, 0.01f)
    }

    // ═══════════════════════════════════════════════════════════
    //  图标按钮
    // ═══════════════════════════════════════════════════════════

    @Test
    fun iconBackgroundAlpha_isAbout12() {
        assertEquals(0.12f, GlassConstants.IconBackgroundAlpha, 0.02f)
    }
}
