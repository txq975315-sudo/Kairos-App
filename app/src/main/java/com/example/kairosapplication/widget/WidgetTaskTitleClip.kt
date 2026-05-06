package com.example.kairosapplication.widget

object WidgetTaskTitleClip {

    /**
     * 3×3 月历格内任务标题：含中日韩等表意文字时最多 4 个码点，否则最多 9 个码点（偏拉丁/英文）。
     */
    fun for3x3CalendarCell(raw: String): String {
        val t = raw.trim()
        if (t.isEmpty()) return "—"
        val cps = t.codePoints().toArray()
        val hasCjk = cps.any { cp ->
            cp in 0x4E00..0x9FFF ||
                cp in 0x3400..0x4DBF ||
                cp in 0x3040..0x30FF ||
                cp in 0xAC00..0xD7AF
        }
        val max = if (hasCjk) 4 else 9
        return cps.take(max.coerceAtMost(cps.size)).joinToString("") { String(Character.toChars(it)) }
    }
}
