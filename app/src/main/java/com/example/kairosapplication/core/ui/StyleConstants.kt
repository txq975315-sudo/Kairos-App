package com.example.kairosapplication.core.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.compositionLocalOf
import com.example.taskmodel.model.UrgencyConfig

/**
 * Global style tokens for Kairos.
 *
 * Goals:
 * 1) Replace scattered literals with shared constants.
 * 2) Stay aligned with the Today screen look.
 * 3) Reuse across Essay / View / Widget / Mine.
 */
object AppColors {
    // ---------- Screen backgrounds (quiet sage-warm mist) ----------
    val ScreenBackground = Color(0xFFEDF0EC)
    val SurfaceWhite = Color(0xFFFAFBFA)
    val Divider = Color(0xFFE1E6E1)
    /** Cards: green-gray white (avoid pure #FFF per “calm” spec) */
    val CardBackground = Color(0xFFF5F7F5)
    /**
     * Unified translucent face over global atmosphere (tasks, notes, mine cards, widget previews, …).
     * Single knob: lower = more see-through.
     */
    const val AtmospherePanelAlpha = 0.56f
    val GlassFill = CardBackground.copy(alpha = AtmospherePanelAlpha)
    /** @see GlassFill */
    val NoteCardFace = GlassFill
    /** Rim light (edge brighter than center — glass read) */
    val CardRimLight = Color.White.copy(alpha = 0.38f)
    /**
     * Today [TaskItemCard] frosted glass face: thin white + gray mist.
     * Creates a "misty glass" effect without making the card too bright.
     * 
     * Key characteristics:
     * - Low white opacity (10-15%) for background transparency
     * - Extra gray mist layer (10%) for added "blur" visual
     * - Soft gradient from near-white top to pale blue-gray bottom
     */
    val TaskCardGlassTop = Color(0xFFFFFFFF).copy(alpha = 0.15f)
    val TaskCardGlassMid = Color(0xFFFAFBFD).copy(alpha = 0.12f)
    val TaskCardGlassBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    /** Extra gray mist overlay for simulated blur effect */
    val TaskCardGrayMist = Color(0xFF808080).copy(alpha = 0.10f)
    /** Subtle glass edge that defines the border gently */
    val TaskCardGlassHairline = Color.White.copy(alpha = 0.35f)
    /** Soft inner glow for glass dimensionality */
    val TaskCardGlassInnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.15f)
    /** Moderate shadow elevation for floating effect */
    val TaskCardShadowColor = Color(0xFF1A2850).copy(alpha = 0.12f)
    val TaskCardShadowElevation = 3.5.dp
    /** Subtle cool depth (optional second pass / dividers) */
    val CardRimDepth = Color(0xFF2A3328).copy(alpha = 0.06f)
    /** Timeline vertical rail */
    val TimelineLine = Color(0xFFDDE2DD)

    // ---------- Text / icons (soft charcoal, not pure black) ----------
    val PrimaryText = Color(0xFF2E312E)
    val SecondaryText = Color(0xFF6A706A)
    val HintText = Color(0xFF949994)
    val IconNeutral = Color(0xFF8B918B)
    val BackIcon = Color(0xFF3A3D3A)

    // ---------- Time-block backgrounds (same families, lower chroma) ----------
    val AnytimeBackground = Color(0xFFEFEBE3)
    val MorningBackground = Color(0xFFF7F2E6)
    val AfternoonBackground = Color(0xFFF3E4DC)
    val EveningBackground = Color(0xFFE9E5F2)

    // ---------- Time-block title (dark) ----------
    val AnytimeTitle = Color(0xFF5E564A)
    val MorningTitle = Color(0xFF7A6430)
    val AfternoonTitle = Color(0xFF8B5845)
    val EveningTitle = Color(0xFF564A7E)

    // ---------- Urgency (keep prior hues for recognition) ----------
    val Urgent = Color(0xFFFF4444)
    val High = Color(0xFFFF9800)
    val Normal = Color(0xFFFFFC3A)
    val Low = Color(0xFF9E9E9E)

    // ---------- BottomBar (floating frosted island — neutral, not sage-tinted) ----------
    /** Theme surface token; also base for non-glass surfaces */
    val BottomBarSurface = Color(0xFFF6F6F9)
    val BottomBarItem = Color(0xFF5C625C)
    val BottomBarSelectedContainer = Color(0xFFD9DCE6).copy(alpha = 0.58f)
    /** Vertical gradient stops for main nav pill */
    val BottomBarGlassVeilTop = Color(0xFFFCFCFE).copy(alpha = 0.94f)
    val BottomBarGlassVeilBottom = Color(0xFFE9ECF3).copy(alpha = 0.82f)
    /** Hairline stroke + top edge light */
    val BottomBarGlassStroke = Color.White.copy(alpha = 0.26f)
    val BottomBarGlassRim = Color.White.copy(alpha = 0.48f)

    // ---------- Small controls (e.g. time-block +) ----------
    val SoftCircleFill = Color(0xFF2A3328).copy(alpha = 0.06f)
    val SoftCircleIcon = Color(0xFF2A3328).copy(alpha = 0.22f)
}

/**
 * Layered “glass-lite” veils over the global atmosphere (no logic coupling).
 * Use on strips / sheets; keep dense list rows on [CardBackground] for readability.
 */
object AppMaterials {
    /** Topic / chrome strip over atmosphere when no essay wallpaper */
    val StripOverAtmosphere = Color(0xFFEDF1ED).copy(alpha = 0.62f)
}

/**
 * Corner-radius ladder (one source of truth). Prefer these over raw `N.dp` in UI code.
 *
 * Tiering: micro → mini → compact → dense inset → inset → pill → card → feature panel → sheet.
 */
object AppShapes {
    /** Modal / bottom sheet top corners */
    val SheetTopRadius = 28.dp
    /** Task rows, note cards, CommonCard, dense list surfaces */
    val CardRadius = 16.dp
    /** Large glass panels (Mine stats, mood, records, widget phone chrome) */
    val FeaturePanelRadius = 20.dp
    /** Prominent capsules (e.g. full-width search field) */
    val ProminentRadius = 24.dp
    /** Secondary surfaces: review rows, inbox cards, editor sections */
    val InsetContentRadius = 12.dp
    /** Tool rows / image tiles between dense inset (8) and inset (12) */
    val EmbedRadius = 10.dp
    /** Thumbnails & embeds inside a card (keeps density) */
    val DenseInsetRadius = 8.dp
    /** Bottom-nav selection, ~pill controls */
    val BottomBarSelectedRadius = 14.dp
    /** Time blocks use the same tier as cards */
    val TimeBlockRadius = CardRadius
    /** Full pill / circular tap targets */
    val CircularButton = 999.dp
    /** Calendar “today”, tight toggles */
    val CompactRadius = 6.dp
    /** Hairline chips, week bar caps */
    val MicroRadius = 2.dp
    /** Tiny palette / preview tiles */
    val MiniRadius = 4.dp
}

object AppSpacing {
    // Screen-level
    val PageHorizontal = 16.dp
    val SectionSmall = 8.dp
    val SectionMedium = 10.dp
    val SectionLarge = 12.dp
    val SectionXLarge = 16.dp
    val BlockGap = 12.dp

    /** Vertical gap between stacked glass sections (e.g. Mine) */
    val GlassSectionStack = 14.dp

    // In-component
    val CardHorizontal = 16.dp
    val CardVertical = 10.dp
    val IconTextGap = 6.dp
    val CompactGap = 4.dp
}

object AppSize {
    // Common buttons
    val TopActionButton = 36.dp
    val TimeBlockAddButton = 24.dp
    val TimeBlockAddIcon = 27.dp

    // Time-block header height (tightened)
    val TimeBlockHeaderHeight = 32.dp

    // Backdrop blur (API 31+ only — see MainAppBottomBar)
    val BottomBarBackdropBlur = 22.dp

    // Input / cards
    val EmptyTaskCardHeight = 48.dp
    val TaskCardBaseHeight = 48.dp
    val TaskCardMaxHeight = 96.dp
    const val TaskImageMaxHeightRatio = 2f // max 2× height

    // Back chevron hit area (360×800 baseline)
    val BackArrowWidthBase = 14.dp
    val BackArrowHeightBase = 8.dp
    val BackHitAreaBase = 24.dp
}

object AppTypography {
    val H1 = 44.sp
    val H2 = 32.sp
    val Title = 22.sp
    val Body = 15.sp
    val BodySmall = 14.sp
    val Caption = 12.sp
    val BottomBarLabel = 11.sp
}

/** Full-screen headers (Daily Review, Create, …) */
object AppScreenHeader {
    val titleSp = 24.sp
    val titleWeight = FontWeight.SemiBold
    val titleColor = AppColors.PrimaryText
}

object AppReviewLayout {
    /** Min viewport height for one task section (~4 DailyTaskCard rows) */
    val minTaskListViewport = 228.dp
}

object AppInteraction {
    const val PressedAlpha = 0.8f
    const val NormalAlpha = 1f
    val ShadowAlpha = 0.05f
}



/** CompositionLocal for dynamic urgency config */
val LocalUrgencyConfig = compositionLocalOf { UrgencyConfig() }