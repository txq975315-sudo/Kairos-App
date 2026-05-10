package com.example.taskmodel.util

import org.junit.Assert.assertEquals
import org.junit.Test

class ColorUtilsTest {

    @Test
    fun parseHexToArgb_validHex_returnsCorrectInt() {
        // #FF0000 = 0xFFFF0000 = -65536
        assertEquals(-65536, ColorUtils.parseHexToArgb("#FF0000"))
        // #007BFF = 0xFF007BFF = -16745473
        assertEquals(-16745473, ColorUtils.parseHexToArgb("#007BFF"))
        // #000000 = 0xFF000000 = -16777216
        assertEquals(-16777216, ColorUtils.parseHexToArgb("#000000"))
        // #FFFFFF = 0xFFFFFFFF = -1
        assertEquals(-1, ColorUtils.parseHexToArgb("#FFFFFF"))
    }

    @Test
    fun parseHexToArgb_invalidHex_returnsFallback() {
        val fallback = ColorUtils.FALLBACKArgb
        assertEquals(fallback, ColorUtils.parseHexToArgb("invalid", fallback))
        assertEquals(fallback, ColorUtils.parseHexToArgb("", fallback))
        assertEquals(fallback, ColorUtils.parseHexToArgb("#GG", fallback))
    }

    @Test
    fun parseHexToArgb_noHashPrefix_returnsFallback() {
        val fallback = ColorUtils.FALLBACKArgb
        assertEquals(fallback, ColorUtils.parseHexToArgb("FF0000", fallback))
        assertEquals(fallback, ColorUtils.parseHexToArgb("AABBCC", fallback))
    }
}
