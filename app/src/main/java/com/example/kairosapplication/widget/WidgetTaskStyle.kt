package com.example.kairosapplication.widget

import android.graphics.Color
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.UrgencyConfig
import com.example.taskmodel.util.ColorUtils.parseHexToArgb

object WidgetTaskStyle {

    /** Dynamic urgency config for widget rendering */
    @Volatile
    private var dynamicUrgencyConfig: UrgencyConfig = UrgencyConfig()

    fun updateFromConfig(config: UrgencyConfig) {
        dynamicUrgencyConfig = config
    }

    fun resolveUrgencyArgb(urgency: Int): Int {
        val hex = dynamicUrgencyConfig.colorForLevel(urgency)
        return parseHexToArgb(hex)
    }

    /** 月历「今日」填充圆与 Spannable 背景统一色 */
    const val CAL_TODAY_FILL_ARGB: Int = 0xFF9F8CF7.toInt()

    private val urgencyArgb = intArrayOf(
        Color.parseColor("#FF4444"),
        Color.parseColor("#FF9800"),
        Color.parseColor("#FFEB3B"),
        Color.parseColor("#9E9E9E")
    )

    private const val URGENCY_TINT_ALPHA = 0.45f

    fun urgencyRowBackgroundArgb(urgency: Int, done: Boolean): Int {
        if (done) return Color.TRANSPARENT
        if (urgency >= TaskConstants.URGENCY_NORMAL) return Color.TRANSPARENT
        val base = urgencyArgb.getOrElse(urgency.coerceIn(0, 3)) { urgencyArgb[3] }
        val a = (255f * URGENCY_TINT_ALPHA).toInt().coerceIn(0, 255)
        return (base and 0x00FFFFFF) or (a shl 24)
    }

    fun cellBackgroundArgb(urgency: Int, done: Boolean): Int = urgencyRowBackgroundArgb(urgency, done)

    /** 3×3 月历任务行：按紧急程度着色，统一约 45% 透明度（已完成用浅灰）。 */
    fun widget3x3TaskLineBackgroundArgb(urgency: Int, done: Boolean): Int {
        val a = (255f * URGENCY_TINT_ALPHA).toInt().coerceIn(0, 255)
        val base =
            if (done) {
                Color.parseColor("#BDBDBD")
            } else {
                urgencyArgb.getOrElse(urgency.coerceIn(0, 3)) { urgencyArgb[TaskConstants.URGENCY_NORMAL] }
            }
        return (base and 0x00FFFFFF) or (a shl 24)
    }

    fun titleColorArgb(done: Boolean): Int =
        if (done) Color.parseColor("#9E9E9E") else Color.parseColor("#1A1A1A")

    fun markColorArgb(done: Boolean): Int =
        if (done) Color.parseColor("#7B61FF") else Color.parseColor("#9E9E9E")

    /** 2×2 任务行右侧紧急程度圆点（与左侧完成态 ○ 区分） */
    fun urgencyPriorityDotArgb(urgency: Int, done: Boolean): Int {
        if (done) return Color.parseColor("#E0E0E0")
        return urgencyArgb.getOrElse(urgency.coerceIn(0, 3)) {
            urgencyArgb[TaskConstants.URGENCY_NORMAL]
        }
    }
}
