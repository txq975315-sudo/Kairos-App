package com.example.kairosapplication.core.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Kairos 全局样式常量库。
 *
 * 目标：
 * 1) 用统一常量替换页面中的硬编码值；
 * 2) 与当前 Today 页面视觉保持一致；
 * 3) 供后续 Essay / View / Widget / Mine 直接复用。
 */
object AppColors {
    // ---------- 页面背景 ----------
    val ScreenBackground = Color(0xFFF9F9F9)
    val SurfaceWhite = Color(0xFFFFFFFF)
    val Divider = Color(0xFFE0E0E0)
    /** Essay NoteCard 等白底卡片 */
    val CardBackground = Color(0xFFFFFFFF)
    /** 时间轴左侧竖线 */
    val TimelineLine = Color(0xFFE0E0E0)

    // ---------- 文本/图标 ----------
    val PrimaryText = Color(0xFF333333)
    val SecondaryText = Color(0xFF666666)
    val HintText = Color(0xFF9A9A9A)
    val IconNeutral = Color(0xFF9E9E9E)
    val BackIcon = Color(0xFF000000) // 规范：通用返回键纯黑

    // ---------- 时间块背景（与 Today 一致） ----------
    val AnytimeBackground = Color(0xFFF2EEE6)
    val MorningBackground = Color(0xFFFFF8E6)
    val AfternoonBackground = Color(0xFFFBE1D6)
    val EveningBackground = Color(0xFFECE7FF)

    // ---------- 时间块标题深色 ----------
    val AnytimeTitle = Color(0xFF6C5C4A)
    val MorningTitle = Color(0xFF8A6E2F)
    val AfternoonTitle = Color(0xFF9B5A40)
    val EveningTitle = Color(0xFF5C4F96)

    // ---------- 紧急程度 ----------
    val Urgent = Color(0xFFFF4444)
    val High = Color(0xFFFF9800)
    val Normal = Color(0xFFFFFC3A)
    val Low = Color(0xFF9E9E9E)

    // ---------- BottomBar ----------
    val BottomBarItem = Color(0xFF666666)
    val BottomBarSelectedContainer = Color(0xFFDEDEDE).copy(alpha = 0.5f)
}

object AppShapes {
    // 来源：Figma 与现有实现
    val SheetTopRadius = 25.dp
    val CardRadius = 12.dp
    val TimeBlockRadius = 16.dp
    val BottomBarSelectedRadius = 20.dp // Figma 18~21dp 取 20dp
    val CircularButton = 999.dp
}

object AppSpacing {
    // 页面级
    val PageHorizontal = 16.dp
    val SectionSmall = 8.dp
    val SectionMedium = 10.dp
    val SectionLarge = 12.dp
    val SectionXLarge = 16.dp
    val BlockGap = 12.dp

    // 组件内
    val CardHorizontal = 16.dp
    val CardVertical = 10.dp
    val IconTextGap = 6.dp
    val CompactGap = 4.dp
}

object AppSize {
    // 通用按钮
    val TopActionButton = 36.dp
    val TimeBlockAddButton = 24.dp
    val TimeBlockAddIcon = 27.dp

    // 时间块高度（当前收紧后的值）
    val TimeBlockHeaderHeight = 32.dp

    // 输入/卡片
    val EmptyTaskCardHeight = 48.dp
    val TaskCardBaseHeight = 48.dp
    val TaskCardMaxHeight = 96.dp
    const val TaskImageMaxHeightRatio = 2f // 最大 2 倍

    // 通用返回键规范（360x800 基准）
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

/** Daily Review / Create 等全屏页顶栏标题统一规格 */
object AppScreenHeader {
    val titleSp = 24.sp
    val titleWeight = FontWeight.Bold
    val titleColor = Color(0xFF1A1A1A)
}

object AppReviewLayout {
    /** 单节任务列表最小可视高度（约 4 条 DailyTaskCard） */
    val minTaskListViewport = 228.dp
}

object AppInteraction {
    const val PressedAlpha = 0.8f
    const val NormalAlpha = 1f
    val ShadowAlpha = 0.05f
}

