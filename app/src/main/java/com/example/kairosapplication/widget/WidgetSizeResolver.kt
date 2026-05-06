package com.example.kairosapplication.widget

import com.example.kairosapplication.widget.data.WidgetSize

object WidgetSizeResolver {
    private fun rank(s: WidgetSize): Int =
        when (s) {
            WidgetSize._1X1 -> 1
            WidgetSize._2X2, WidgetSize._3X1 -> 2
            WidgetSize._3X3 -> 3
        }

    fun resolve(hint: WidgetSize?, measured: WidgetSize): WidgetSize {
        if (hint == null) return measured
        return if (rank(measured) > rank(hint)) measured else hint
    }
}
