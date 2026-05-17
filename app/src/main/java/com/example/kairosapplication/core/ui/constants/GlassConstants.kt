package com.example.kairosapplication.core.ui.constants

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Design tokens for the Glass UI system.
 *
 * Clear atmosphere wallpaper + recipe [GlassBubbleRecipe] bubbles for task rows;
 * fixed chrome (top / bottom) uses adaptive text via [com.example.kairosapplication.ui.glass.AtmosphereLuminance].
 */
object GlassConstants {

    val bubbleRecipe: GlassBubbleRecipe get() = GlassBubbleRecipe.active

    // ═══════════════════════════════════════════════════════════
    //  圆角体系（统一 24dp）
    // ═══════════════════════════════════════════════════════════
    val CornerRadius = 24.dp

    // ═══════════════════════════════════════════════════════════
    //  模糊半径（背景可清晰；卡片不用假 blur 雾）
    // ═══════════════════════════════════════════════════════════
    /** Full-screen photo — keep near 0 for a sharp wallpaper */
    val AtmosphereImageBlurRadius = 1.dp
    val TaskCardBlurRadius = 0.dp
    val BottomNavBlurRadius = 0.dp
    val BottomNavSelectedBlurRadius = 0.dp
    val TimeBlockBlurRadius = 0.dp

    // ═══════════════════════════════════════════════════════════
    //  气泡填充（A / B 由 [bubbleRecipe] 切换）
    // ═══════════════════════════════════════════════════════════
    val TaskCardFillAlpha: Float
        get() = when (bubbleRecipe) {
            GlassBubbleRecipe.A -> 0.22f
            GlassBubbleRecipe.B -> 0.10f
            GlassBubbleRecipe.C -> 0f
        }

    val TaskCardSheenAlpha: Float
        get() = when (bubbleRecipe) {
            GlassBubbleRecipe.A -> 0.12f
            GlassBubbleRecipe.B -> 0.12f
            GlassBubbleRecipe.C -> 0f
        }

    val TimeBlockFillAlpha: Float
        get() = when (bubbleRecipe) {
            GlassBubbleRecipe.A -> 0.20f
            GlassBubbleRecipe.B -> 0.10f
            GlassBubbleRecipe.C -> 0f
        }

    val BottomNavFillAlpha: Float
        get() = when (bubbleRecipe) {
            GlassBubbleRecipe.A -> 0.24f
            GlassBubbleRecipe.B -> 0.14f
            GlassBubbleRecipe.C -> 0.10f
        }

    val BottomNavSelectedFillAlpha: Float
        get() = when (bubbleRecipe) {
            GlassBubbleRecipe.A -> 0.28f
            GlassBubbleRecipe.B -> 0.18f
            GlassBubbleRecipe.C -> 0f
        }

    val PillFillAlpha: Float
        get() = when (bubbleRecipe) {
            GlassBubbleRecipe.A -> 0.20f
            GlassBubbleRecipe.B -> 0.10f
            GlassBubbleRecipe.C -> 0f
        }

    /**
     * Route C — bright liquid glass: light blur + minimal tint; color comes from wallpaper.
     */
    val GlassBackdropBlurRadius = 8.dp
    const val GlassHazeTintAlpha = 0.035f
    const val GlassHazeNoiseFactor = 0f

    /** Uniform lift after blur (flat — not a center bulge). */
    const val GlassFrostLiftAlpha = 0.055f

    /** Top-edge specular glint (ref. chat). */
    const val GlassSpecularLineAlpha = 0.26f
    val GlassSpecularLineHeight = 1.dp

    val usesBackdropBlur: Boolean
        get() = bubbleRecipe == GlassBubbleRecipe.C

    // ═══════════════════════════════════════════════════════════
    //  边框 + 阴影
    // ═══════════════════════════════════════════════════════════
    val GlassBorderAlpha: Float
        get() = when (bubbleRecipe) {
            GlassBubbleRecipe.A -> 0.26f
            GlassBubbleRecipe.B -> 0.30f
            GlassBubbleRecipe.C -> 0.24f
        }

    val GlassBorderWidth: Dp
        get() = when (bubbleRecipe) {
            GlassBubbleRecipe.C -> 0.5.dp
            else -> 1.dp
        }

    val GlassShadowElevation: Dp
        get() = when (bubbleRecipe) {
            GlassBubbleRecipe.A -> 5.dp
            GlassBubbleRecipe.B -> 4.dp
            GlassBubbleRecipe.C -> 0.dp
        }

    val ShadowAmbientAlpha: Float
        get() = when (bubbleRecipe) {
            GlassBubbleRecipe.A -> 0.14f
            GlassBubbleRecipe.B -> 0.10f
            GlassBubbleRecipe.C -> 0f
        }

    val ShadowSpotAlpha: Float
        get() = when (bubbleRecipe) {
            GlassBubbleRecipe.A -> 0.16f
            GlassBubbleRecipe.B -> 0.12f
            GlassBubbleRecipe.C -> 0f
        }

    val useTaskCardTextShadow: Boolean
        get() = bubbleRecipe == GlassBubbleRecipe.B || bubbleRecipe == GlassBubbleRecipe.C

    // ═══════════════════════════════════════════════════════════
    //  发光 / 阴影（legacy elevation tokens）
    // ═══════════════════════════════════════════════════════════
    val GlowElevation = 4.dp
    val SelectedGlowElevation = 6.dp

    // ═══════════════════════════════════════════════════════════
    //  任务卡文字（固定浅色，不跟壁纸）
    // ═══════════════════════════════════════════════════════════
    val TextPrimary = Color.White
    val TextSecondary = Color.White.copy(alpha = 0.70f)
    val TextMuted = Color.White.copy(alpha = 0.50f)

    // ═══════════════════════════════════════════════════════════
    //  固定区自适应字色（亮底深字 / 暗底浅字）
    // ═══════════════════════════════════════════════════════════
    val ChromeOnLightPrimary = Color(0xFF2E312E)
    val ChromeOnLightSecondary = Color(0xFF6A706A)
    val ChromeOnLightMuted = Color(0xFF9AA09A)
    val ChromeOnLightDivider = Color(0xFF2E312E).copy(alpha = 0.18f)
    val ChromeOnDarkDivider = Color.White.copy(alpha = 0.32f)

    // ═══════════════════════════════════════════════════════════
    //  图标按钮
    // ═══════════════════════════════════════════════════════════
    val IconBackgroundAlpha = 0.12f
    val IconSelectedAlpha = 0.20f
    val IconSize = 22.dp
    val IconButtonSize = 36.dp

    // ═══════════════════════════════════════════════════════════
    //  全屏氛围（轻 dim，少雾）
    // ═══════════════════════════════════════════════════════════
    val AtmosphereDimAlpha = 0.12f
    /** Legacy glass-only overlay tokens ([com.example.kairosapplication.ui.glass.GlassAtmosphereBackground]) */
    val BackgroundDarkenAlpha = 0.15f
    val BackgroundLightenAlpha = 0.05f
    val AtmosphereLightZoneThreshold = 0.55f
    const val AtmosphereTopZoneFraction = 0.20f
    const val AtmosphereBottomZoneStartFraction = 0.85f

    /** @deprecated Use chrome adaptive colors; kept for non-glass callers */
    val QuoteText = ChromeOnLightPrimary
    val QuoteAccent = ChromeOnLightSecondary
    val QuoteDivider = ChromeOnLightDivider

    const val TimeBlockPillWidthFraction = 0.38f

    val TimeBlockIconAnytime = Color(0xFFCBBBA5)
    val TimeBlockIconMorning = Color(0xFFCCC27A)
    val TimeBlockIconAfternoon = Color(0xFFCC9E6E)
    val TimeBlockIconEvening = Color(0xFFA89CC4)

    val GlowTopAlpha = 0.06f
    val GlowFadeAlpha = 0.02f
    val GlowBottomAlpha = 0.03f

    val PillBorderAlpha: Float
        get() = when (bubbleRecipe) {
            GlassBubbleRecipe.C -> 0.18f
            else -> 0.22f
        }
    val PillCornerRadius = 999.dp
    val PillHeight = 28.dp

    val CompleteButtonSize = 24.dp
    val CompleteButtonBorderWidth = 2.dp
    val CompletedCheckSize = 14.dp

    val CardPaddingHorizontal = 16.dp
    val CardPaddingVertical = 12.dp
    val BottomNavPaddingHorizontal = 8.dp
    val BottomNavPaddingVertical = 8.dp
    val BottomNavTabPaddingHorizontal = 16.dp
    val BottomNavTabPaddingVertical = 5.dp

    /** Fixed selected-tab chip — same size for all five tabs. */
    val BottomNavSelectedChipWidth = 68.dp
    val BottomNavSelectedChipHeight = 52.dp
    val BottomNavTabSlotHeight = 56.dp
    /** Frost dock extends above the hairline so the selected chip highlight reads clearly. */
    val BottomNavDockTopExtension = 10.dp
    val BottomNavTopBorderAlpha = 0.22f
}
