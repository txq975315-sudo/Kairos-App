package com.example.taskmodel.model

data class UrgencyLevelConfig(
    val level: Int,
    val label: String,
    val description: String,
    val colorHex: String
)

data class UrgencyConfig(
    val levels: List<UrgencyLevelConfig> = defaultLevels()
) {
    companion object {
        fun defaultLevels(): List<UrgencyLevelConfig> = listOf(
            UrgencyLevelConfig(0, "紧急", "需要立即处理", "#FF0000"),
            UrgencyLevelConfig(1, "较紧急", "近期需要完成", "#FF7A00"),
            UrgencyLevelConfig(2, "一般", "正常推进即可", "#FFD700"),
            UrgencyLevelConfig(3, "不紧急", "可以稍后处理", "#007BFF")
        )

        const val FALLBACK_COLOR = "#9E9E9E"

        fun fromJson(json: String?): UrgencyConfig {
            if (json.isNullOrBlank()) return UrgencyConfig()
            return try {
                val levels = parseJsonLevels(json)
                if (levels.isEmpty()) UrgencyConfig() else UrgencyConfig(levels)
            } catch (_: Exception) {
                UrgencyConfig()
            }
        }

        private fun parseJsonLevels(json: String): List<UrgencyLevelConfig> {
            val trimmed = json.trim()
            if (!trimmed.startsWith("[") || !trimmed.endsWith("]")) return emptyList()
            val inner = trimmed.drop(1).dropLast(1).trim()
            if (inner.isBlank()) return emptyList()
            return splitJsonObjects(inner).mapIndexed { index, objStr ->
                parseLevel(objStr, index)
            }
        }

        private fun splitJsonObjects(input: String): List<String> {
            val result = mutableListOf<String>()
            var depth = 0
            var current = StringBuilder()
            for (ch in input) {
                when (ch) {
                    '{' -> { depth++; current.append(ch) }
                    '}' -> { depth--; current.append(ch) }
                    ',' -> {
                        if (depth == 0) {
                            result.add(current.toString().trim())
                            current = StringBuilder()
                        } else {
                            current.append(ch)
                        }
                    }
                    else -> current.append(ch)
                }
            }
            if (current.isNotBlank()) result.add(current.toString().trim())
            return result
        }

        private fun parseLevel(objStr: String, defaultIndex: Int): UrgencyLevelConfig {
            val level = extractInt(objStr, "level", defaultIndex)
            val label = extractString(objStr, "label", "")
            val description = extractString(objStr, "description", "")
            val colorHex = extractString(objStr, "colorHex", FALLBACK_COLOR)
            return UrgencyLevelConfig(level, label, description, colorHex)
        }

        private fun extractInt(json: String, key: String, default: Int): Int {
            val pattern = """"$key"\s*:\s*(\d+)""".toRegex()
            return pattern.find(json)?.groupValues?.get(1)?.toIntOrNull() ?: default
        }

        private fun extractString(json: String, key: String, default: String): String {
            val pattern = """"$key"\s*:\s*"([^"]*)"""".toRegex()
            return pattern.find(json)?.groupValues?.get(1) ?: default
        }
    }

    fun colorForLevel(level: Int): String =
        levels.find { it.level == level }?.colorHex ?: FALLBACK_COLOR

    fun labelForLevel(level: Int): String =
        levels.find { it.level == level }?.label ?: ""

    fun descriptionForLevel(level: Int): String =
        levels.find { it.level == level }?.description ?: ""

    fun toJson(): String {
        val sb = StringBuilder("[")
        levels.forEachIndexed { index, lvl ->
            if (index > 0) sb.append(",")
            sb.append("{\"level\":${lvl.level},\"label\":\"${escapeJson(lvl.label)}\",")
            sb.append("\"description\":\"${escapeJson(lvl.description)}\",")
            sb.append("\"colorHex\":\"${lvl.colorHex}\"}")
        }
        sb.append("]")
        return sb.toString()
    }

    private fun escapeJson(s: String): String =
        s.replace("\\", "\\\\").replace("\"", "\\\"")
}
