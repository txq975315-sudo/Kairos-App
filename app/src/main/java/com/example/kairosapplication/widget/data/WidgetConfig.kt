package com.example.kairosapplication.widget.data

import org.json.JSONObject

enum class WidgetSize(val displayName: String) {
    _1X1("1×1"),
    _2X2("2×2"),
    _3X1("3×1"),
    _3X3("3×3")
}

enum class WidgetLayoutKind(val displayName: String) {
    _1A("计数类"),
    _1B("Todo类"),
    _2A("Todo展示类"),
    _3A("Todo展示类"),
    _3B("统计类"),
    _3C("一周任务条"),
    _4A("展示今日"),
    _4B("展示全部")
}

enum class WidgetRefreshStrategy(val displayName: String) {
    ON_APP_OPEN("每次打开app更新一次"),
    HOURLY("每小时更新一次"),
    DAILY("每天更新一次")
}

enum class WidgetBackgroundType {
    WHITE,
    SOLID_COLOR,
    IMAGE,
    GRADIENT
}

data class WidgetBackground(
    val type: WidgetBackgroundType = WidgetBackgroundType.WHITE,
    val solidColor: String = "#FFFFFF",
    val imageUri: String? = null,
    val blurRadius: Int = 0,
    val alphaPercent: Int = 100,
    val gradientStartColor: String = "#FFFFFF",
    val gradientEndColor: String = "#FFFFFF",
    val gradientAngle: Int = 0,
    val isRadialGradient: Boolean = false
)

data class WidgetDisplayConfig(
    val showTasks: Boolean = true,
    val showDailyQuote: Boolean = true
)

data class WidgetConfig(
    val size: WidgetSize = WidgetSize._1X1,
    val layoutKind: WidgetLayoutKind = WidgetLayoutKind._1A,
    val background: WidgetBackground = WidgetBackground(),
    val displayConfig: WidgetDisplayConfig = WidgetDisplayConfig(),
    val refreshStrategy: WidgetRefreshStrategy = WidgetRefreshStrategy.ON_APP_OPEN
)

object WidgetDefaults {
    fun defaultConfig(size: WidgetSize): WidgetConfig {
        val layout = when (size) {
            WidgetSize._1X1 -> WidgetLayoutKind._1A
            WidgetSize._2X2 -> WidgetLayoutKind._2A
            WidgetSize._3X1 -> WidgetLayoutKind._3A
            WidgetSize._3X3 -> WidgetLayoutKind._4A
        }
        return WidgetConfig(size = size, layoutKind = layout)
    }
}

fun WidgetConfig.toJsonString(): String {
    val bg = background
    val bgJson = JSONObject().apply {
        put("type", bg.type.name)
        put("solidColor", bg.solidColor)
        put("imageUri", bg.imageUri ?: JSONObject.NULL)
        put("blurRadius", bg.blurRadius)
        put("alphaPercent", bg.alphaPercent)
        put("gradientStartColor", bg.gradientStartColor)
        put("gradientEndColor", bg.gradientEndColor)
        put("gradientAngle", bg.gradientAngle)
        put("isRadialGradient", bg.isRadialGradient)
    }
    val display = displayConfig
    val displayJson = JSONObject().apply {
        put("showTasks", display.showTasks)
        put("showDailyQuote", display.showDailyQuote)
    }
    return JSONObject().apply {
        put("size", size.name)
        put("layoutKind", layoutKind.name)
        put("background", bgJson)
        put("displayConfig", displayJson)
        put("refreshStrategy", refreshStrategy.name)
    }.toString()
}

fun decodeWidgetConfig(json: String?): WidgetConfig? {
    if (json.isNullOrBlank()) return null
    return try {
        val o = JSONObject(json)
        val size = o.optString("size").let { s ->
            WidgetSize.entries.find { it.name == s } ?: return null
        }
        val layoutKind = o.optString("layoutKind").let { s ->
            WidgetLayoutKind.entries.find { it.name == s }
                ?: WidgetDefaults.defaultConfig(size).layoutKind
        }
        val refreshStrategy = o.optString("refreshStrategy").let { s ->
            WidgetRefreshStrategy.entries.find { it.name == s }
                ?: WidgetRefreshStrategy.ON_APP_OPEN
        }
        val bgO = o.optJSONObject("background")
        val background = if (bgO != null) {
            val typeStr = bgO.optString("type", WidgetBackgroundType.WHITE.name)
            val type = runCatching { WidgetBackgroundType.valueOf(typeStr) }
                .getOrDefault(WidgetBackgroundType.WHITE)
            WidgetBackground(
                type = type,
                solidColor = bgO.optString("solidColor", "#FFFFFF"),
                imageUri = if (bgO.isNull("imageUri")) null else bgO.optString("imageUri").takeIf { it.isNotEmpty() },
                blurRadius = bgO.optInt("blurRadius", 0),
                alphaPercent = bgO.optInt("alphaPercent", 100),
                gradientStartColor = bgO.optString("gradientStartColor", "#FFFFFF"),
                gradientEndColor = bgO.optString("gradientEndColor", "#FFFFFF"),
                gradientAngle = bgO.optInt("gradientAngle", 0),
                isRadialGradient = bgO.optBoolean("isRadialGradient", false)
            )
        } else {
            WidgetBackground()
        }
        val dO = o.optJSONObject("displayConfig")
        val displayConfig = if (dO != null) {
            WidgetDisplayConfig(
                showTasks = dO.optBoolean("showTasks", true),
                showDailyQuote = dO.optBoolean("showDailyQuote", true)
            )
        } else {
            WidgetDisplayConfig()
        }
        WidgetConfig(
            size = size,
            layoutKind = layoutKind,
            background = background,
            displayConfig = displayConfig,
            refreshStrategy = refreshStrategy
        )
    } catch (_: Exception) {
        null
    }
}
