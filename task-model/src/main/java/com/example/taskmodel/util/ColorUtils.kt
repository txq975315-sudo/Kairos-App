package com.example.taskmodel.util

object ColorUtils {
    /**
     * Parse a hex color string (must start with "#") to ARGB int.
     * Only accepts "#RRGGBB" format (6 hex digits after #).
     * Returns fallback for invalid input.
     */
    fun parseHexToArgb(hex: String, fallback: Int = FALLBACKArgb): Int {
        if (!hex.startsWith("#")) return fallback
        return try {
            val cleaned = hex.removePrefix("#")
            if (cleaned.length != 6) return fallback
            val r = cleaned.substring(0, 2).toInt(16)
            val g = cleaned.substring(2, 4).toInt(16)
            val b = cleaned.substring(4, 6).toInt(16)
            (0xFF shl 24) or (r shl 16) or (g shl 8) or b
        } catch (_: Exception) {
            fallback
        }
    }

    val FALLBACKArgb: Int = (0xFF shl 24) or (0x9E shl 16) or (0x9E shl 8) or 0x9E
}