package com.example.kairosapplication.widget

import com.example.kairosapplication.widget.data.WidgetSize

object WidgetSizeResolver {
    /**
     * Uses the widget [hint] from [AppWidgetProvider] when present so placement keeps the bound
     * layout after the launcher resizes the widget; [measured] is only used when the provider
     * cannot be classified.
     */
    fun resolve(hint: WidgetSize?, measured: WidgetSize): WidgetSize = hint ?: measured
}
