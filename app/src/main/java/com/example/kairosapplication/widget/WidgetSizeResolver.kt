package com.example.kairosapplication.widget

import com.example.kairosapplication.widget.data.WidgetSize

object WidgetSizeResolver {
    /** Physical area ranking: 3×1 strip is smaller than 2×2 block; 3×3 is largest. */
    private fun rank(s: WidgetSize): Int =
        when (s) {
            WidgetSize._1X1 -> 1
            WidgetSize._3X1 -> 2
            WidgetSize._2X2 -> 3
            WidgetSize._3X3 -> 4
        }

    fun resolve(hint: WidgetSize?, measured: WidgetSize): WidgetSize {
        if (hint == null) return measured
        return if (rank(measured) > rank(hint)) measured else hint
    }
}
