package com.example.taskmodel.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.taskmodel.constants.NoteStatus
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NoteDao {

    @Query("SELECT * FROM notes ORDER BY updated_at DESC")
    abstract fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes ORDER BY updated_at DESC")
    abstract suspend fun getAllNotesOnce(): List<NoteEntity>

    @Query(
        """
        SELECT * FROM notes 
        WHERE status = 'published' 
        ORDER BY recorded_date_epoch_day DESC, updated_at DESC
        """
    )
    abstract fun getPublishedNotes(): Flow<List<NoteEntity>>

    @Query(
        """
        SELECT * FROM notes 
        WHERE status = 'inbox' 
        ORDER BY (inbox_deadline_epoch_day IS NULL), inbox_deadline_epoch_day ASC
        """
    )
    abstract fun getInboxNotes(): Flow<List<NoteEntity>>

    @Query(
        """
        SELECT * FROM notes 
        WHERE status = 'deleted' 
        ORDER BY updated_at DESC
        """
    )
    abstract fun getDeletedNotes(): Flow<List<NoteEntity>>

    @Query(
        """
        SELECT * FROM notes 
        WHERE primary_category = :category AND status != 'deleted'
        ORDER BY recorded_date_epoch_day DESC, updated_at DESC
        """
    )
    abstract fun getNotesByPrimaryCategory(category: String): Flow<List<NoteEntity>>

    @Query(
        """
        SELECT * FROM notes 
        WHERE status = 'inbox' 
        AND inbox_deadline_epoch_day IS NOT NULL 
        AND inbox_deadline_epoch_day < :todayEpochDay
        """
    )
    abstract suspend fun getInboxExpiredNotes(todayEpochDay: Long): List<NoteEntity>

    @Query(
        """
        SELECT * FROM notes 
        WHERE status != 'deleted' AND (
            body LIKE '%' || :keyword || '%' OR
            IFNULL(behavior_summary, '') LIKE '%' || :keyword || '%' OR
            scene_tags LIKE '%' || :keyword || '%'
        )
        ORDER BY updated_at DESC
        """
    )
    abstract suspend fun searchNotes(keyword: String): List<NoteEntity>

    @Insert
    abstract suspend fun insertNote(note: NoteEntity): Long

    @Insert
    abstract suspend fun insertNotes(notes: List<NoteEntity>)

    @Query(
        """
        UPDATE notes SET status = 'deleted', updated_at = :updatedAt 
        WHERE id = :noteId
        """
    )
    abstract suspend fun deleteNote(noteId: Long, updatedAt: Long)

    @Query("SELECT COUNT(*) FROM notes")
    abstract suspend fun count(): Long

    @Query("SELECT * FROM notes WHERE id = :id AND status != 'deleted' LIMIT 1")
    abstract suspend fun getById(id: Long): NoteEntity?

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    abstract suspend fun getByIdAnyStatus(id: Long): NoteEntity?

    @Query("DELETE FROM notes WHERE id = :noteId")
    abstract suspend fun deleteRowById(noteId: Long)

    @Query("DELETE FROM notes WHERE status = 'deleted'")
    abstract suspend fun deleteAllDeleted()

    @Update
    abstract suspend fun updateNote(note: NoteEntity)

    @Transaction
    open suspend fun getNotesByProjectId(projectId: Long): List<NoteEntity> {
        return getAllNotesOnce().filter { it.status != NoteStatus.DELETED && projectId in it.projectIds }
    }
}
