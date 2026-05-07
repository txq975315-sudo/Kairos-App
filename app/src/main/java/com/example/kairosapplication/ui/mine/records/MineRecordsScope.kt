package com.example.kairosapplication.ui.mine.records

enum class MineRecordsScope {
    THIS_YEAR,
    ALL_TIME,
    ;

    companion object {
        fun fromStored(raw: String?): MineRecordsScope =
            runCatching { valueOf(raw?.trim().orEmpty()) }.getOrElse { ALL_TIME }

        fun encode(v: MineRecordsScope): String = v.name
    }
}
