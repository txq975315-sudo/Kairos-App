package com.example.taskmodel.repository

import android.content.Context
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.database.NoteDao
import com.example.taskmodel.database.NoteDatabase
import com.example.taskmodel.database.NoteEntity
import com.example.taskmodel.database.ProjectDao
import com.example.taskmodel.database.ProjectEntity
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.NoteRoomState
import com.example.taskmodel.model.Project
import java.time.LocalDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Essay module Room repository: validation, inbox deadline handling, soft delete, optional mock seed.
 */
class NoteRepository private constructor(
    private val noteDao: NoteDao,
    private val projectDao: ProjectDao,
    private val seedMockOnEmpty: Boolean
) {

    private val seedMutex = Mutex()
    private val seedScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        if (seedMockOnEmpty) {
            seedScope.launch {
                seedMutex.withLock {
                    if (noteDao.count() == 0L) {
                        insertMockData()
                    }
                }
            }
        }
    }

    fun observePublishedNotes(): Flow<List<Note>> =
        noteDao.getPublishedNotes().map { list -> list.map { it.toDomain() } }

    fun observeInboxNotes(): Flow<List<Note>> =
        noteDao.getInboxNotes().map { list -> list.map { it.toDomain() } }

    fun observeDeletedNotes(): Flow<List<Note>> =
        noteDao.getDeletedNotes().map { list -> list.map { it.toDomain() } }

    fun observeProjects(): Flow<List<Project>> =
        projectDao.getAllProjects().map { list -> list.map { it.toDomain() } }

    fun observeNoteRoomState(): Flow<NoteRoomState> = combine(
        observePublishedNotes(),
        observeInboxNotes(),
        observeProjects(),
        observeDeletedNotes()
    ) { published, inbox, projects, trash ->
        NoteRoomState(published, inbox, projects, trash)
    }

    suspend fun getPublishedNotesOnce(): List<Note> =
        noteDao.getPublishedNotes().first().map { it.toDomain() }

    suspend fun getAllNotesOnce(): List<Note> =
        noteDao.getAllNotesOnce().map { it.toDomain() }

    suspend fun addNote(note: Note): Long {
        validateLinked(note)
        if (NotePrimaryCategory.isTopic(note.primaryCategory)) {
            validateTopicRequired(note)
        } else if (note.primaryCategory != NotePrimaryCategory.FREESTYLE) {
            throw NoteValidationException("Invalid primaryCategory: ${note.primaryCategory}")
        }

        val now = System.currentTimeMillis()
        val recordedDate = note.recordedDate
        val prepared = if (note.primaryCategory == NotePrimaryCategory.FREESTYLE) {
            note.copy(
                id = 0L,
                linkedCategories = emptyList(),
                status = NoteStatus.INBOX,
                deadline = LocalDate.now().plusDays(15),
                needsManualClassification = note.needsManualClassification,
                createdAt = if (note.createdAt == 0L) now else note.createdAt,
                updatedAt = now,
                recordedDate = recordedDate
            )
        } else {
            note.copy(
                id = 0L,
                status = NoteStatus.PUBLISHED,
                deadline = null,
                needsManualClassification = false,
                createdAt = if (note.createdAt == 0L) now else note.createdAt,
                updatedAt = now,
                recordedDate = recordedDate
            )
        }
        return noteDao.insertNote(prepared.toEntity())
    }

    suspend fun updateNote(note: Note) {
        val existing = noteDao.getByIdAnyStatus(note.id)
            ?: throw IllegalArgumentException("Note not found: ${note.id}")
        validateLinked(note)
        if (NotePrimaryCategory.isTopic(note.primaryCategory)) {
            validateTopicRequired(note)
        }

        val now = System.currentTimeMillis()
        var needsManual = when {
            existing.status == NoteStatus.INBOX && note.status == NoteStatus.PUBLISHED ->
                note.needsManualClassification
            else ->
                note.needsManualClassification || existing.needsManualClassification
        }
        if (existing.status == NoteStatus.INBOX &&
            existing.projectIds.isEmpty() &&
            note.projectIds.isNotEmpty()
        ) {
            needsManual = true
        }

        val mergedEntity = note.toEntity().copy(
            id = existing.id,
            createdAt = existing.createdAt,
            updatedAt = now,
            needsManualClassification = needsManual
        )
        noteDao.updateNote(mergedEntity)
    }

    suspend fun checkInboxDeadline(asOf: LocalDate = LocalDate.now()) {
        val todayEpoch = asOf.toEpochDay()
        val expired = noteDao.getInboxExpiredNotes(todayEpoch)
        val now = System.currentTimeMillis()
        for (e in expired) {
            if (e.projectIds.isEmpty()) {
                val updated = e.copy(
                    status = NoteStatus.PUBLISHED,
                    primaryCategory = NotePrimaryCategory.FREESTYLE,
                    secondaryCategory = null,
                    behaviorSummary = null,
                    linkedCategories = emptyList(),
                    inboxDeadlineEpochDay = null,
                    needsManualClassification = false,
                    updatedAt = now
                )
                noteDao.updateNote(updated)
            } else {
                val updated = e.copy(
                    inboxDeadlineEpochDay = null,
                    needsManualClassification = true,
                    updatedAt = now
                )
                noteDao.updateNote(updated)
            }
        }
    }

    suspend fun softDelete(noteId: Long) {
        noteDao.deleteNote(noteId, System.currentTimeMillis())
    }

    suspend fun restoreNote(noteId: Long) {
        val entity = noteDao.getByIdAnyStatus(noteId) ?: return
        if (entity.status != NoteStatus.DELETED) return
        val now = System.currentTimeMillis()
        noteDao.updateNote(
            entity.copy(
                status = NoteStatus.PUBLISHED,
                updatedAt = now,
                inboxDeadlineEpochDay = null
            )
        )
    }

    suspend fun permanentlyDeleteNote(noteId: Long) {
        noteDao.deleteRowById(noteId)
    }

    suspend fun emptyTrash() {
        noteDao.deleteAllDeleted()
    }

    suspend fun searchNotes(keyword: String): List<Note> =
        noteDao.searchNotes(keyword).map { it.toDomain() }

    suspend fun getNotesByProjectId(projectId: Long): List<Note> =
        noteDao.getNotesByProjectId(projectId).map { it.toDomain() }

    suspend fun getNotesByPrimaryCategoryOnce(category: String): List<Note> =
        noteDao.getNotesByPrimaryCategory(category).first().map { it.toDomain() }

    private fun validateLinked(note: Note) {
        if (note.linkedCategories.any { it == NotePrimaryCategory.FREESTYLE }) {
            throw NoteValidationException("linked_categories cannot contain freestyle")
        }
        if (note.primaryCategory in note.linkedCategories) {
            throw NoteValidationException("linked_categories cannot duplicate primary_category")
        }
    }

    private fun validateTopicRequired(note: Note) {
        if (note.behaviorSummary.isBlank() || note.secondaryCategory.isBlank()) {
            throw NoteValidationException("Topic notes require behavior summary and secondary category")
        }
    }

    private suspend fun insertMockData() {
        val now = System.currentTimeMillis()
        val p1 = ProjectEntity(
            id = 0L,
            name = "Career Transition",
            description = "Career planning notes",
            coverImageUri = null,
            createdAt = now,
            updatedAt = now
        )
        val p2 = ProjectEntity(
            id = 0L,
            name = "Daily Reflection",
            description = null,
            coverImageUri = null,
            createdAt = now,
            updatedAt = now
        )
        val pid1 = projectDao.insert(p1)
        val pid2 = projectDao.insert(p2)

        val today = LocalDate.now()
        fun day(d: Long) = today.plusDays(d).toEpochDay()

        val notes = mutableListOf<NoteEntity>()

        notes += NoteEntity(
            id = 0L,
            primaryCategory = NotePrimaryCategory.FREESTYLE,
            secondaryCategory = null,
            behaviorSummary = null,
            body = "Inbox quick capture — review in five days.",
            sceneTags = listOf("inbox"),
            moodIcon = "🙂",
            linkedCategories = emptyList(),
            projectIds = emptyList(),
            recordedDateEpochDay = today.toEpochDay(),
            createdAt = now,
            updatedAt = now,
            status = NoteStatus.INBOX,
            inboxDeadlineEpochDay = day(5),
            needsManualClassification = false
        )

        notes += NoteEntity(
            id = 0L,
            primaryCategory = NotePrimaryCategory.FREESTYLE,
            secondaryCategory = null,
            behaviorSummary = null,
            body = "Urgent inbox — due tomorrow.",
            sceneTags = emptyList(),
            moodIcon = null,
            linkedCategories = emptyList(),
            projectIds = emptyList(),
            recordedDateEpochDay = today.toEpochDay(),
            createdAt = now,
            updatedAt = now,
            status = NoteStatus.INBOX,
            inboxDeadlineEpochDay = day(1),
            needsManualClassification = false
        )

        notes += NoteEntity(
            id = 0L,
            primaryCategory = NotePrimaryCategory.FREESTYLE,
            secondaryCategory = null,
            behaviorSummary = null,
            body = "Inbox without project — will become permanent memo if left.",
            sceneTags = emptyList(),
            moodIcon = null,
            linkedCategories = emptyList(),
            projectIds = emptyList(),
            recordedDateEpochDay = today.toEpochDay(),
            createdAt = now,
            updatedAt = now,
            status = NoteStatus.INBOX,
            inboxDeadlineEpochDay = day(10),
            needsManualClassification = false
        )

        notes += NoteEntity(
            id = 0L,
            primaryCategory = NotePrimaryCategory.SELF_AWARENESS,
            secondaryCategory = "Emotional trigger event",
            behaviorSummary = "Noticed anxiety before the meeting.",
            body = "Self-awareness reflection body text.",
            sceneTags = listOf("work"),
            moodIcon = "😟",
            linkedCategories = emptyList(),
            projectIds = emptyList(),
            recordedDateEpochDay = today.minusDays(1).toEpochDay(),
            createdAt = now,
            updatedAt = now,
            status = NoteStatus.PUBLISHED,
            inboxDeadlineEpochDay = null,
            needsManualClassification = false
        )

        notes += NoteEntity(
            id = 0L,
            primaryCategory = NotePrimaryCategory.INTERPERSONAL,
            secondaryCategory = "Boundary breakthrough event",
            behaviorSummary = "Said no to an unreasonable request.",
            body = "Interpersonal note content.",
            sceneTags = emptyList(),
            moodIcon = null,
            linkedCategories = emptyList(),
            projectIds = emptyList(),
            recordedDateEpochDay = today.minusDays(2).toEpochDay(),
            createdAt = now,
            updatedAt = now,
            status = NoteStatus.PUBLISHED,
            inboxDeadlineEpochDay = null,
            needsManualClassification = false
        )

        notes += NoteEntity(
            id = 0L,
            primaryCategory = NotePrimaryCategory.INTIMACY_FAMILY,
            secondaryCategory = "Power struggle scene",
            behaviorSummary = "Argument about chores distribution.",
            body = "Family dynamics note.",
            sceneTags = listOf("home"),
            moodIcon = null,
            linkedCategories = emptyList(),
            projectIds = emptyList(),
            recordedDateEpochDay = today.minusDays(3).toEpochDay(),
            createdAt = now,
            updatedAt = now,
            status = NoteStatus.PUBLISHED,
            inboxDeadlineEpochDay = null,
            needsManualClassification = false
        )

        notes += NoteEntity(
            id = 0L,
            primaryCategory = NotePrimaryCategory.SOMATIC_ENERGY,
            secondaryCategory = "Body signal log",
            behaviorSummary = "Poor sleep and afternoon crash.",
            body = "Energy tracking entry.",
            sceneTags = emptyList(),
            moodIcon = "😴",
            linkedCategories = emptyList(),
            projectIds = emptyList(),
            recordedDateEpochDay = today.minusDays(1).toEpochDay(),
            createdAt = now,
            updatedAt = now,
            status = NoteStatus.PUBLISHED,
            inboxDeadlineEpochDay = null,
            needsManualClassification = false
        )

        notes += NoteEntity(
            id = 0L,
            primaryCategory = NotePrimaryCategory.MEANING,
            secondaryCategory = "Value ordering event",
            behaviorSummary = "Chose rest over extra work.",
            body = "Meaning exploration note.",
            sceneTags = emptyList(),
            moodIcon = null,
            linkedCategories = emptyList(),
            projectIds = emptyList(),
            recordedDateEpochDay = today.minusDays(4).toEpochDay(),
            createdAt = now,
            updatedAt = now,
            status = NoteStatus.PUBLISHED,
            inboxDeadlineEpochDay = null,
            needsManualClassification = false
        )

        notes += NoteEntity(
            id = 0L,
            primaryCategory = NotePrimaryCategory.INTIMACY_FAMILY,
            secondaryCategory = "Emotional flow record",
            behaviorSummary = "Partner and I repaired after conflict.",
            body = "Linked sample: also relevant to interpersonal.",
            sceneTags = listOf("repair"),
            moodIcon = "💬",
            linkedCategories = listOf(NotePrimaryCategory.INTERPERSONAL),
            projectIds = emptyList(),
            recordedDateEpochDay = today.minusDays(5).toEpochDay(),
            createdAt = now,
            updatedAt = now,
            status = NoteStatus.PUBLISHED,
            inboxDeadlineEpochDay = null,
            needsManualClassification = false
        )

        repeat(3) { idx ->
            notes += NoteEntity(
                id = 0L,
                primaryCategory = NotePrimaryCategory.FREESTYLE,
                secondaryCategory = null,
                behaviorSummary = null,
                body = "Permanent memo snippet $idx — freestyle published.",
                sceneTags = listOf("memo"),
                moodIcon = null,
                linkedCategories = emptyList(),
                projectIds = emptyList(),
                recordedDateEpochDay = today.minusDays(10L + idx).toEpochDay(),
                createdAt = now,
                updatedAt = now,
                status = NoteStatus.PUBLISHED,
                inboxDeadlineEpochDay = null,
                needsManualClassification = false
            )
        }

        notes += NoteEntity(
            id = 0L,
            primaryCategory = NotePrimaryCategory.FREESTYLE,
            secondaryCategory = null,
            behaviorSummary = null,
            body = "Joined project while still in inbox — needs classification.",
            sceneTags = emptyList(),
            moodIcon = null,
            linkedCategories = emptyList(),
            projectIds = listOf(pid1),
            recordedDateEpochDay = today.toEpochDay(),
            createdAt = now,
            updatedAt = now,
            status = NoteStatus.INBOX,
            inboxDeadlineEpochDay = null,
            needsManualClassification = true
        )

        notes += NoteEntity(
            id = 0L,
            primaryCategory = NotePrimaryCategory.SELF_AWARENESS,
            secondaryCategory = "Habit experiment record",
            behaviorSummary = "Tried a new morning routine.",
            body = "Published note tied to project.",
            sceneTags = emptyList(),
            moodIcon = null,
            linkedCategories = emptyList(),
            projectIds = listOf(pid1),
            recordedDateEpochDay = today.minusDays(1).toEpochDay(),
            createdAt = now,
            updatedAt = now,
            status = NoteStatus.PUBLISHED,
            inboxDeadlineEpochDay = null,
            needsManualClassification = false
        )

        notes += NoteEntity(
            id = 0L,
            primaryCategory = NotePrimaryCategory.FREESTYLE,
            secondaryCategory = null,
            behaviorSummary = null,
            body = "Freestyle published with project tag.",
            sceneTags = listOf("journal"),
            moodIcon = "📎",
            linkedCategories = emptyList(),
            projectIds = listOf(pid2),
            recordedDateEpochDay = today.minusDays(2).toEpochDay(),
            createdAt = now,
            updatedAt = now,
            status = NoteStatus.PUBLISHED,
            inboxDeadlineEpochDay = null,
            needsManualClassification = false
        )

        noteDao.insertNotes(notes)
    }

    companion object {
        operator fun invoke(context: Context): NoteRepository {
            val db = NoteDatabase.getInstance(context.applicationContext)
            return NoteRepository(db.noteDao(), db.projectDao(), seedMockOnEmpty = true)
        }

        fun createForTest(
            noteDao: NoteDao,
            projectDao: ProjectDao,
            seedMockOnEmpty: Boolean
        ): NoteRepository {
            return NoteRepository(noteDao, projectDao, seedMockOnEmpty)
        }
    }
}
