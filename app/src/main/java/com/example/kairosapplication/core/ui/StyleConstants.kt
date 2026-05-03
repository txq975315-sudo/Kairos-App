package com.example.kairosapplication.core.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Global style tokens for Kairos.
 *
 * Goals:
 * 1) Replace scattered literals with shared constants.
 * 2) Stay aligned with the Today screen look.
 * 3) Reuse across Essay / View / Widget / Mine.
 */
object AppColors {
    // ---------- Screen backgrounds ----------
    val ScreenBackground = Color(0xFFF9F9F9)
    val SurfaceWhite = Color(0xFFFFFFFF)
    val Divider = Color(0xFFE0E0E0)
    /** White cards (Essay NoteCard, etc.) */
    val CardBackground = Color(0xFFFFFFFF)
    /** Timeline vertical rail */
    val TimelineLine = Color(0xFFE0E0E0)

    // ---------- Text / icons ----------
    val PrimaryText = Color(0xFF333333)
    val SecondaryText = Color(0xFF666666)
    val HintText = Color(0xFF9A9A9A)
    val IconNeutral = Color(0xFF9E9E9E)
    val BackIcon = Color(0xFF000000) // Spec: back icon solid black

    // ---------- Time-block backgrounds (match Today) ----------
    val AnytimeBackground = Color(0xFFF2EEE6)
    val MorningBackground = Color(0xFFFFF8E6)
    val AfternoonBackground = Color(0xFFFBE1D6)
    val EveningBackground = Color(0xFFECE7FF)

    // ---------- Time-block title (dark) ----------
    val AnytimeTitle = Color(0xFF6C5C4A)
    val MorningTitle = Color(0xFF8A6E2F)
    val AfternoonTitle = Color(0xFF9B5A40)
    val EveningTitle = Color(0xFF5C4F96)

    // ---------- Urgency ----------
    val Urgent = Color(0xFFFF4444)
    val High = Color(0xFFFF9800)
    val Normal = Color(0xFFFFFC3A)
    val Low = Color(0xFF9E9E9E)

    // ---------- BottomBar ----------
    val BottomBarItem = Color(0xFF666666)
    val BottomBarSelectedContainer = Color(0xFFDEDEDE).copy(alpha = 0.5f)
}

object AppShapes {
    // From Figma and current implementation
    val SheetTopRadius = 25.dp
    val CardRadius = 12.dp
    val TimeBlockRadius = 16.dp
    val BottomBarSelectedRadius = 20.dp // Figma ~18–21dp; use 20dp
    val CircularButton = 999.dp
}

object AppSpacing {
    // Screen-level
    val PageHorizontal = 16.dp
    val SectionSmall = 8.dp
    val SectionMedium = 10.dp
    val SectionLarge = 12.dp
    val SectionXLarge = 16.dp
    val BlockGap = 12.dp

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
    val titleWeight = FontWeight.Bold
    val titleColor = Color(0xFF1A1A1A)
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

