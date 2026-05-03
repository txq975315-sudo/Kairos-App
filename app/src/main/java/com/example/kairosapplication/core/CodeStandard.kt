package com.example.kairosapplication.core

import kotlin.RequiresOptIn
import kotlin.annotation.AnnotationRetention
import kotlin.annotation.Retention

/**
 * Kairos coding standards (reference):
 * - Naming
 * - State management
 * - Comment templates
 * - Guidance on hardcoded styles
 */

object NamingStandard {
    // Composable names: noun + Screen / Section / Card / Button suffix
    const val COMPOSABLE_SUFFIX_SCREEN = "Screen"
    const val COMPOSABLE_SUFFIX_SECTION = "Section"
    const val COMPOSABLE_SUFFIX_CARD = "Card"
    const val COMPOSABLE_SUFFIX_BUTTON = "Button"

    // State: is/has/can prefix; events: onXxx
    const val STATE_PREFIX_IS = "is"
    const val STATE_PREFIX_HAS = "has"
    const val EVENT_PREFIX_ON = "on"
}

object StateStandard {
    /**
     * Page-level state:
     * - Prefer ViewModel for cross-module / cross-screen state.
     * - Page-local UI state may use remember / mutableStateOf.
     */
    const val VIEWMODEL_RULE = "Cross-feature state -> ViewModel"
    const val REMEMBER_RULE = "Local UI state -> remember"

    /**
     * List state:
     * - Prefer SnapshotStateList (mutableStateListOf) for mutable lists.
     * - Prefer derivedStateOf for display ordering to avoid repeated sort work.
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
 * Marks code where hardcoded styles are temporarily allowed.
 * Prefer consolidating styles in core/ui/StyleConstants.kt.
 */
@Retention(AnnotationRetention.BINARY)
@RequiresOptIn(
    message = "Do not hardcode styles directly. Use StyleConstants/AppColors/AppSpacing/AppSize.",
    level = RequiresOptIn.Level.WARNING
)
annotation class HardcodedStyleTemporary
