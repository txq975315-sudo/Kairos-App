package com.example.kairosapplication.ui.components

import androidx.compose.ui.graphics.Color
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.ui.glass.timeBlockIconTint
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.util.TaskUtils

/**
 * Visual theme for [CreateTaskBottomSheet].
 *
 * - [Glass]: unified liquid-glass sheet (default) with a light time-block wash.
 * - [Classic]: original solid tinted card — kept for a future「原始色彩块」theme toggle.
 */
enum class CreateTaskSheetVisualTheme {
    Glass,
    Classic,
}

/**
 * @param backgroundColor Classic: opaque face. Glass: translucent time-block wash (alpha preset).
 * @param titleColor Accent for block title and selected tool states.
 */
data class CreateSheetConfig(
    val timeBlock: String,
    val theme: CreateTaskSheetVisualTheme,
    val backgroundColor: Color,
    val titleColor: Color,
)

/** Factories for quick-create sheet appearance. */
object CreateTaskSheetThemes {

    private const val GLASS_SHEET_TINT_ALPHA = 0.34f
    private const val GLASS_ICON_PANEL_TINT_ALPHA = 0.42f

    /** Unified glass — default for app quick create. */
    fun glass(timeBlock: String): CreateSheetConfig = CreateSheetConfig(
        timeBlock = timeBlock,
        theme = CreateTaskSheetVisualTheme.Glass,
        backgroundColor = TaskUtils.getTimeBlockColor(timeBlock).copy(alpha = GLASS_SHEET_TINT_ALPHA),
        titleColor = timeBlockIconTint(timeBlock),
    )

    /** Original solid color-card sheet (preserved, not used by default). */
    fun classic(timeBlock: String): CreateSheetConfig = CreateSheetConfig(
        timeBlock = timeBlock,
        theme = CreateTaskSheetVisualTheme.Classic,
        backgroundColor = TaskUtils.getTimeBlockColor(timeBlock),
        titleColor = TaskUtils.getTimeBlockTitleColor(timeBlock),
    )

    fun forUiTheme(uiTheme: AppUiTheme, timeBlock: String): CreateSheetConfig = when (uiTheme) {
        AppUiTheme.Classic -> classic(timeBlock)
        AppUiTheme.Glass -> glass(timeBlock)
    }

    fun iconPanelTint(config: CreateSheetConfig): Color = when (config.theme) {
        CreateTaskSheetVisualTheme.Classic -> config.backgroundColor
        CreateTaskSheetVisualTheme.Glass ->
            TaskUtils.getTimeBlockColor(config.timeBlock).copy(alpha = GLASS_ICON_PANEL_TINT_ALPHA)
    }
}
