package com.example.taskmodel.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.taskmodel.constants.NoteSecondaryCategories

/** Align legacy `secondary_category` values with [NoteSecondaryCategories.defaults]. */
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        for ((primary, oldSec, canonical) in NoteSecondaryCategories.migrationSecondaryRenames()) {
            db.execSQL(
                "UPDATE notes SET secondary_category=? WHERE primary_category=? AND secondary_category=?",
                arrayOf(canonical, primary, oldSec)
            )
        }
    }
}
