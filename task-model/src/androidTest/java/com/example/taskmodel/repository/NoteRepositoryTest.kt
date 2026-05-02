package com.example.taskmodel.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.database.NoteDatabase
import com.example.taskmodel.database.ProjectEntity
import com.example.taskmodel.model.Note
import java.time.LocalDate
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteRepositoryTest {

    private lateinit var db: NoteDatabase
    private lateinit var repo: NoteRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        repo = NoteRepository.createForTest(db.noteDao(), db.projectDao(), seedMockOnEmpty = false)
    }

    @After
    fun tearDown() {
        db.close()
    }

    private fun baseNote(
        primary: String = NotePrimaryCategory.FREESTYLE,
        body: String = "Hello",
        recorded: LocalDate = LocalDate.now()
    ) = Note(
        id = 0L,
        primaryCategory = primary,
        secondaryCategory = "",
        behaviorSummary = "",
        body = body,
        sceneTags = emptyList(),
        moodIcon = null,
        linkedCategories = emptyList(),
        projectIds = emptyList(),
        recordedDate = recorded,
        createdAt = 0L,
        updatedAt = 0L,
        status = NoteStatus.INBOX,
        deadline = null,
        needsManualClassification = false
    )

    @Test
    fun `createFreestyleNote should have inbox status and deadline`() = runBlocking {
        val id = repo.addNote(baseNote())
        val loaded = repo.getAllNotesOnce().first { it.id == id }
        assertEquals(NoteStatus.INBOX, loaded.status)
        assertEquals(LocalDate.now().plusDays(15), loaded.deadline)
    }

    @Test
    fun `createTopicNote should have published status`() = runBlocking {
        val id = repo.addNote(
            baseNote(primary = NotePrimaryCategory.MEANING).copy(
                secondaryCategory = "Value ordering event",
                behaviorSummary = "Chose health over overtime.",
                body = "Topic body"
            )
        )
        val loaded = repo.getAllNotesOnce().first { it.id == id }
        assertEquals(NoteStatus.PUBLISHED, loaded.status)
        assertEquals(null, loaded.deadline)
    }

    @Test
    fun `createNote without summary for topic should throw exception`() {
        assertThrows(NoteValidationException::class.java) {
            runBlocking {
                repo.addNote(
                    baseNote(primary = NotePrimaryCategory.MEANING).copy(
                        secondaryCategory = "Value ordering event",
                        behaviorSummary = "",
                        body = "x"
                    )
                )
            }
        }
    }

    @Test
    fun `expired inbox without project should become permanent`() = runBlocking {
        val id = repo.addNote(baseNote())
        val before = repo.getAllNotesOnce().first { it.id == id }
        repo.updateNote(before.copy(deadline = LocalDate.now().minusDays(1)))
        repo.checkInboxDeadline(LocalDate.now())
        val after = repo.getAllNotesOnce().first { it.id == id }
        assertEquals(NoteStatus.PUBLISHED, after.status)
        assertEquals(NotePrimaryCategory.FREESTYLE, after.primaryCategory)
        assertEquals(null, after.deadline)
        assertTrue(repo.getPublishedNotesOnce().any { it.id == id })
    }

    @Test
    fun `expired inbox with project should mark needsManualClassification`() = runBlocking {
        val now = System.currentTimeMillis()
        val pId = db.projectDao().insert(
            ProjectEntity(
                id = 0L,
                name = "Test project",
                description = null,
                coverImageUri = null,
                createdAt = now,
                updatedAt = now
            )
        )
        val id = repo.addNote(baseNote())
        val mid = repo.getAllNotesOnce().first { it.id == id }
        repo.updateNote(
            mid.copy(
                projectIds = listOf(pId),
                deadline = LocalDate.now().minusDays(1)
            )
        )
        repo.checkInboxDeadline(LocalDate.now())
        val after = repo.getAllNotesOnce().first { it.id == id }
        assertEquals(NoteStatus.INBOX, after.status)
        assertTrue(after.needsManualClassification)
        assertEquals(null, after.deadline)
    }

    @Test
    fun `soft delete should set status to deleted`() = runBlocking {
        val id = repo.addNote(baseNote())
        repo.softDelete(id)
        val row = db.noteDao().getAllNotesOnce().first { it.id == id }
        assertEquals(NoteStatus.DELETED, row.status)
    }

    @Test
    fun `restoreNote should set status back to published`() = runBlocking {
        val id = repo.addNote(baseNote())
        repo.softDelete(id)
        repo.restoreNote(id)
        val row = db.noteDao().getByIdAnyStatus(id)
        assertEquals(NoteStatus.PUBLISHED, row?.status)
    }

    @Test
    fun `permanentlyDeleteNote should remove row`() = runBlocking {
        val id = repo.addNote(baseNote())
        repo.softDelete(id)
        repo.permanentlyDeleteNote(id)
        assertEquals(null, db.noteDao().getByIdAnyStatus(id))
    }

    @Test
    fun `emptyTrash should remove all deleted notes`() = runBlocking {
        val id1 = repo.addNote(baseNote(body = "a"))
        val id2 = repo.addNote(baseNote(body = "b"))
        repo.softDelete(id1)
        repo.softDelete(id2)
        repo.emptyTrash()
        assertTrue(db.noteDao().getAllNotesOnce().none { it.status == NoteStatus.DELETED })
    }

    @Test
    fun `getNotesByPrimaryCategory should return correct notes`() = runBlocking {
        repo.addNote(baseNote(body = "freestyle body"))
        repo.addNote(
            baseNote(primary = NotePrimaryCategory.MEANING).copy(
                secondaryCategory = "Value ordering event",
                behaviorSummary = "Summary text",
                body = "Meaning body"
            )
        )
        val meaning = repo.getNotesByPrimaryCategoryOnce(NotePrimaryCategory.MEANING)
        assertEquals(1, meaning.size)
        assertEquals(NotePrimaryCategory.MEANING, meaning.first().primaryCategory)
    }

    @Test
    fun `searchNotes should filter by keyword`() = runBlocking {
        repo.addNote(baseNote(body = "uniqueAlphaToken789"))
        val r = repo.searchNotes("uniqueAlphaToken789")
        assertEquals(1, r.size)
    }
}
