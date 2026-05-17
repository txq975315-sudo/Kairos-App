package com.example.kairosapplication.core.ui.constants

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Design tokens for the Glass UI system.
 *
 * All values are derived from cross-referencing three reference images:
 * - Reflect (meditation app): warm sage + sky blue, 60-70% white opacity, 40-60px blur
 * - Onda Retreat (resort app): 20-40px blur, 10-25% white, 0.5-1px border
 * - Article 6-tips + 5-core: 24-32px radius, frosted glass, floating capsules
 *
 * This object coexists with [AppColors] and [AppShapes] — no existing code is modified.
 */
object GlassConstants {

    // ═══════════════════════════════════════════════════════════
    //  圆角体系（统一 24dp）
    // ═══════════════════════════════════════════════════════════
    val CornerRadius = 24.dp

    // ═══════════════════════════════════════════════════════════
    //  模糊半径
    // ═══════════════════════════════════════════════════════════
    /** TaskCard outer effect layer blur */
    val TaskCardBlurRadius = 50.dp
    /** BottomNav blur (2× legacy 40dp) */
    val BottomNavBlurRadius = 80.dp
    /** Selected tab chip inside bottom nav */
    val BottomNavSelectedBlurRadius = 50.dp
    /** TimeBlock medium blur */
    val TimeBlockBlurRadius = 25.dp

    // ═══════════════════════════════════════════════════════════
    //  半透明填充（白色系）
    // ═══════════════════════════════════════════════════════════
    val TaskCardFillAlpha = 0.06f        // 6% white
    val BottomNavFillAlpha = 0.12f       // 12% white bar
    val BottomNavSelectedFillAlpha = 0.10f
    val TimeBlockFillAlpha = 0.04f       // 4% even lighter for time blocks

    // ═══════════════════════════════════════════════════════════
    //  边框（极弱，亮度差替代实线）
    // ═══════════════════════════════════════════════════════════
    val GlassBorderAlpha = 0.10f         // 10% white
    val GlassBorderWidth = 0.5.dp        // ultra-thin

    // ═══════════════════════════════════════════════════════════
    //  发光 / 阴影
    // ═══════════════════════════════════════════════════════════
    val GlowElevation = 4.dp
    val SelectedGlowElevation = 6.dp
    val ShadowAmbientAlpha = 0.05f
    val ShadowSpotAlpha = 0.08f

    // ═══════════════════════════════════════════════════════════
    //  文字颜色（白色系 — 适配暗色背景）
    // ═══════════════════════════════════════════════════════════
    val TextPrimary = Color.White
    val TextSecondary = Color.White.copy(alpha = 0.70f)
    val TextMuted = Color.White.copy(alpha = 0.50f)

    // ═══════════════════════════════════════════════════════════
    //  图标按钮
    // ═══════════════════════════════════════════════════════════
    val IconBackgroundAlpha = 0.12f      // #ffffff20 ≈ 12%
    val IconSelectedAlpha = 0.20f
    val IconSize = 22.dp
    val IconButtonSize = 36.dp

    // ═══════════════════════════════════════════════════════════
    //  背景遮罩（压对比度）
    // ═══════════════════════════════════════════════════════════
    /** Full-screen photo blur behind sage veil */
    val AtmosphereImageBlurRadius = 18.dp
    /** Lower overall brightness for white chrome */
    val AtmosphereDimAlpha = 0.22f
    /** Black overlay — darken bright areas (sky, water highlights) */
    val BackgroundDarkenAlpha = 0.15f
    /** White overlay — lighten dark areas (water shadows, lily pads) */
    val BackgroundLightenAlpha = 0.05f

    // ═══════════════════════════════════════════════════════════
    //  氛围上的正文字色（每日一句等，B 版式 + A 字色）
    // ═══════════════════════════════════════════════════════════
    val QuoteText = Color(0xFF2D4A2D)
    val QuoteAccent = Color(0xFF6A706A)
    val QuoteDivider = Color(0xFFE1E6E1).copy(alpha = 0.85f)

    /** Time-block pill max width as fraction of screen (≈1/3) */
    const val TimeBlockPillWidthFraction = 0.38f

    // ═══════════════════════════════════════════════════════════
    //  边缘高光渐变色标（极柔和，无明暗交界线）
    // ═══════════════════════════════════════════════════════════
    /** Top subtle glow */
    val GlowTopAlpha = 0.06f
    /** Fast fade zone */
    val GlowFadeAlpha = 0.02f
    /** Bottom very subtle glow */
    val GlowBottomAlpha = 0.03f

    // ═══════════════════════════════════════════════════════════
    //  悬浮胶囊（Pill Label）
    // ═══════════════════════════════════════════════════════════
    val PillFillAlpha = 0.10f            // 10% white fill
    val PillBorderAlpha = 0.08f          // 8% white border
    val PillCornerRadius = 999.dp        // full pill shape
    val PillHeight = 28.dp

    // ═══════════════════════════════════════════════════════════
    //  完成按钮（圆形）
    // ═══════════════════════════════════════════════════════════
    val CompleteButtonSize = 24.dp
    val CompleteButtonBorderWidth = 2.dp
    val CompletedCheckSize = 14.dp

    // ═══════════════════════════════════════════════════════════
    //  间距
    // ═══════════════════════════════════════════════════════════
    val CardPaddingHorizontal = 16.dp
    val CardPaddingVertical = 12.dp
    val BottomNavPaddingHorizontal = 14.dp
    val BottomNavPaddingVertical = 7.dp
    val BottomNavTabPaddingHorizontal = 16.dp
    val BottomNavTabPaddingVertical = 5.dp
}
