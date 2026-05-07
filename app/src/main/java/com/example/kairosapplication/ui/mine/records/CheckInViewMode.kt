package com.example.kairosapplication.ui.mine.records

enum class CheckInViewMode {
    WEEK,
    MONTH,
    ;

    companion object {
        fun fromStored(raw: String?): CheckInViewMode =
            runCatching { valueOf(raw?.trim().orEmpty()) }.getOrElse { MONTH }

        fun encode(v: CheckInViewMode): String = v.name
    }
}
