package com.example.kairosapplication.core

import kotlin.RequiresOptIn
import kotlin.annotation.AnnotationRetention
import kotlin.annotation.Retention

/**
 * Kairos 编码规范声明：
 * - 命名规范
 * - 状态管理规范
 * - 注释模板
 * - 硬编码样式限制提示
 */

object NamingStandard {
    // Composable 命名：名词 + Screen/Section/Card/Button 后缀
    const val COMPOSABLE_SUFFIX_SCREEN = "Screen"
    const val COMPOSABLE_SUFFIX_SECTION = "Section"
    const val COMPOSABLE_SUFFIX_CARD = "Card"
    const val COMPOSABLE_SUFFIX_BUTTON = "Button"

    // 状态命名：is/has/can 前缀，事件命名：onXxx
    const val STATE_PREFIX_IS = "is"
    const val STATE_PREFIX_HAS = "has"
    const val EVENT_PREFIX_ON = "on"
}

object StateStandard {
    /**
     * 页面级状态：
     * - 跨模块/跨页面状态优先 ViewModel；
     * - 页面局部 UI 状态可用 remember / mutableStateOf。
     */
    const val VIEWMODEL_RULE = "Cross-feature state -> ViewModel"
    const val REMEMBER_RULE = "Local UI state -> remember"

    /**
     * 列表状态：
     * - 可变列表优先 SnapshotStateList（mutableStateListOf）；
     * - 展示排序优先 derivedStateOf，避免重复排序开销。
     */
    const val LIST_RULE = "mutableStateListOf + derivedStateOf"
}

object CommentTemplate {
    const val PAGE_HEADER =
        "// Page: <ScreenName>\n// Responsibility: <What this page manages>\n// Navigation: <from/to routes>"

    const val COMPONENT_HEADER =
        "// Component: <ComponentName>\n// Input: <params>\n// Behavior: <interaction summary>"

    const val COMPLEX_LOGIC_HEADER =
        "// Why: <business reason>\n// What: <logic summary>\n// Risk: <edge case>"
}

/**
 * 标记“临时允许硬编码样式”的代码。
 * 默认不建议使用：样式应收敛到 core/ui/StyleConstants.kt。
 */
@Retention(AnnotationRetention.BINARY)
@RequiresOptIn(
    message = "Do not hardcode styles directly. Use StyleConstants/AppColors/AppSpacing/AppSize.",
    level = RequiresOptIn.Level.WARNING
)
annotation class HardcodedStyleTemporary

