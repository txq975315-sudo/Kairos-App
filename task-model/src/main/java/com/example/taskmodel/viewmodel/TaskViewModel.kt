package com.example.taskmodel.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Essay
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.NoteRoomState
import com.example.taskmodel.model.Project
import com.example.taskmodel.model.Task
import com.example.taskmodel.repository.NoteRepository
import com.example.taskmodel.repository.NoteValidationException
import com.example.taskmodel.repository.TaskRepository
import com.example.taskmodel.store.TaskCreationBus
import com.example.taskmodel.util.TaskUtils
import java.time.LocalDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val onboardingHandled: Boolean = true,
    val essays: List<Essay> = emptyList(),
    val dailyQuoteEssayId: Long? = null,
    /** Essay module (Room) — published notes for timeline / topic views */
    val notePublished: List<Note> = emptyList(),
    /** Essay module (Room) — inbox notes */
    val noteInbox: List<Note> = emptyList(),
    /** Essay module (Room) — user projects */
    val noteProjects: List<Project> = emptyList(),
    /** Essay module (Room) — soft-deleted notes (Trash) */
    val noteTrash: List<Note> = emptyList()
)

class TaskViewModel(
    private val repository: TaskRepository,
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val refreshingFromBus = MutableStateFlow(0)
    private val deleteMutex = Mutex()
    private val lastDeletedForUndo = MutableStateFlow<Task?>(null)
    private val essayDeleteMutex = Mutex()
    private val lastDeletedEssayForUndo = MutableStateFlow<Essay?>(null)

    private val _noteValidationErrors = MutableSharedFlow<String>(extraBufferCapacity = 8)
    val noteValidationErrors = _noteValidationErrors.asSharedFlow()

    val uiState: StateFlow<TaskUiState> = combine(
        combine(
            repository.tasksFlow,
            repository.onboardingHandledFlow,
            refreshingFromBus
        ) { tasks, onboardingHandled, _ ->
            Pair(tasks, onboardingHandled)
        },
        repository.essaysFlow,
        repository.dailyQuoteEssayIdFlow,
        noteRepository.observeNoteRoomState()
    ) { taskPair, essays, dailyQuoteEssayId, noteRoom: NoteRoomState ->
        val (tasks, onboardingHandled) = taskPair
        TaskUiState(
            tasks = tasks,
            onboardingHandled = onboardingHandled,
            essays = essays,
            dailyQuoteEssayId = dailyQuoteEssayId,
            notePublished = noteRoom.published,
            noteInbox = noteRoom.inbox,
            noteProjects = noteRoom.projects,
            noteTrash = noteRoom.trash
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TaskUiState()
    )

    val noteTrash: StateFlow<List<Note>> = uiState
        .map { it.noteTrash }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun syncFromCreationBus() {
        viewModelScope.launch {
            val incoming = TaskCreationBus.consumeAll()
            if (incoming.isNotEmpty()) {
                repository.appendTasks(incoming)
                refreshingFromBus.value += 1
            }
        }
    }

    fun saveTasks(tasks: List<Task>) {
        viewModelScope.launch {
            repository.saveTasks(tasks)
        }
    }

    fun updateTask(updated: Task) {
        viewModelScope.launch {
            val current = uiState.value.tasks
            val newTasks = current.map { task ->
                if (task.id == updated.id) updated else task
            }
            repository.saveTasks(newTasks)
        }
    }

    fun toggleTaskComplete(task: Task) {
        val current = uiState.value.tasks
        val newTasks = current.map {
            if (it.id == task.id) it.copy(isCompleted = !it.isCompleted) else it
        }
        saveTasks(newTasks)
    }

    fun applyStopRepeat(task: Task) {
        val updated = TaskUtils.stopRepeat(uiState.value.tasks, task)
        saveTasks(updated)
    }

    fun markOnboardingChoice(loadSamples: Boolean) {
        viewModelScope.launch {
            if (loadSamples) {
                repository.loadSampleTasksIfNeeded()
            }
            repository.setOnboardingHandled(true)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteMutex.withLock {
                val current = repository.tasksFlow.first()
                lastDeletedForUndo.value = task
                repository.saveTasks(current.filter { it.id != task.id })
            }
        }
    }

    fun undoDeleteTask() {
        viewModelScope.launch {
            deleteMutex.withLock {
                val t = lastDeletedForUndo.value ?: return@launch
                lastDeletedForUndo.value = null
                repository.appendTasks(listOf(t))
            }
        }
    }

    fun clearDeleteUndo() {
        lastDeletedForUndo.value = null
    }

    fun saveEssay(essay: Essay) {
        viewModelScope.launch {
            val current = repository.essaysFlow.first()
            val without = current.filter { it.id != essay.id }
            repository.saveEssays(without + essay)
        }
    }

    fun deleteEssay(essay: Essay) {
        viewModelScope.launch {
            essayDeleteMutex.withLock {
                val current = repository.essaysFlow.first()
                lastDeletedEssayForUndo.value = essay
                val filtered = current.filter { it.id != essay.id }
                repository.saveEssays(filtered)
                val quoteId = repository.dailyQuoteEssayIdFlow.first()
                if (quoteId == essay.id) {
                    repository.setDailyQuoteEssayId(null)
                }
            }
        }
    }

    fun undoDeleteEssay() {
        viewModelScope.launch {
            essayDeleteMutex.withLock {
                val e = lastDeletedEssayForUndo.value ?: return@launch
                lastDeletedEssayForUndo.value = null
                val current = repository.essaysFlow.first()
                repository.saveEssays(current + e)
            }
        }
    }

    fun clearEssayDeleteUndo() {
        lastDeletedEssayForUndo.value = null
    }

    fun setDailyQuoteFromEssay(essayId: Long) {
        viewModelScope.launch {
            val current = repository.essaysFlow.first()
            val updated = current.map { it.copy(isDailyQuote = it.id == essayId) }
            repository.saveEssays(updated)
            repository.setDailyQuoteEssayId(essayId)
        }
    }

    fun clearDailyQuoteFromEssay() {
        viewModelScope.launch {
            val current = repository.essaysFlow.first()
            repository.saveEssays(current.map { it.copy(isDailyQuote = false) })
            repository.setDailyQuoteEssayId(null)
        }
    }

    /** 首页每日一句展示文案：来自选中的 Essay 首行，无则默认文案 */
    fun dailyQuoteDisplayText(defaultText: String): String {
        val id = uiState.value.dailyQuoteEssayId ?: return defaultText
        val essay = uiState.value.essays.find { it.id == id } ?: return defaultText
        val line = essay.body.trim().lineSequence().firstOrNull()?.trim().orEmpty()
        if (line.isEmpty()) return defaultText
        return if (line.length > 200) line.take(200) + "…" else line
    }

    fun pendingTaskCountForDate(date: LocalDate): Int =
        uiState.value.tasks.count { it.taskDate == date && !it.isCompleted }

    fun wouldExceedDailyPendingLimit(date: LocalDate, additionalIncomplete: Int): Boolean =
        pendingTaskCountForDate(date) + additionalIncomplete > DAILY_PENDING_LIMIT

    fun addRoomNote(note: Note) {
        viewModelScope.launch {
            try {
                noteRepository.addNote(note)
            } catch (e: NoteValidationException) {
                _noteValidationErrors.emit(e.message ?: "Validation failed")
            }
        }
    }

    /** Alias for [addRoomNote] (essay module naming). */
    fun addNote(note: Note) = addRoomNote(note)

    fun updateRoomNote(note: Note) {
        viewModelScope.launch {
            try {
                noteRepository.updateNote(note)
            } catch (e: NoteValidationException) {
                _noteValidationErrors.emit(e.message ?: "Validation failed")
            }
        }
    }

    /** Alias for [updateRoomNote] (essay module naming). */
    fun updateNote(note: Note) = updateRoomNote(note)

    /** Alias for [softDeleteRoomNote] (essay module naming). */
    fun softDeleteNote(noteId: Long) = softDeleteRoomNote(noteId)

    /**
     * Save from the note editor; invokes [onSuccess] on the main thread after persistence succeeds.
     */
    fun saveRoomNoteForEditor(note: Note, isNew: Boolean, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                if (isNew) {
                    noteRepository.addNote(note)
                } else {
                    noteRepository.updateNote(note)
                }
                onSuccess()
            } catch (e: NoteValidationException) {
                _noteValidationErrors.emit(e.message ?: "Validation failed")
            } catch (e: IllegalArgumentException) {
                _noteValidationErrors.emit(e.message ?: "Save failed")
            }
        }
    }

    fun softDeleteRoomNote(noteId: Long) {
        viewModelScope.launch {
            noteRepository.softDelete(noteId)
        }
    }

    fun restoreNote(noteId: Long) {
        viewModelScope.launch {
            noteRepository.restoreNote(noteId)
        }
    }

    fun permanentlyDeleteNote(noteId: Long) {
        viewModelScope.launch {
            noteRepository.permanentlyDeleteNote(noteId)
        }
    }

    fun emptyTrash() {
        viewModelScope.launch {
            noteRepository.emptyTrash()
        }
    }

    /** Publish a single inbox note as the given primary category (used by quick classify & clear-all). */
    fun publishInboxNote(noteId: Long, primaryCategory: String = NotePrimaryCategory.FREESTYLE) {
        viewModelScope.launch {
            try {
                val note = uiState.value.noteInbox.find { it.id == noteId } ?: return@launch
                val published = note.toPublishedFromInbox(primaryCategory)
                noteRepository.updateNote(published)
            } catch (e: NoteValidationException) {
                _noteValidationErrors.emit(e.message ?: "Validation failed")
            } catch (e: IllegalArgumentException) {
                _noteValidationErrors.emit(e.message ?: "Update failed")
            }
        }
    }

    fun quickClassifyNote(noteId: Long, category: String) {
        publishInboxNote(noteId, category)
    }

    fun clearAllInbox() {
        viewModelScope.launch {
            try {
                val inbox = uiState.value.noteInbox.filter { it.status == NoteStatus.INBOX }
                for (n in inbox) {
                    noteRepository.updateNote(
                        n.toPublishedFromInbox(NotePrimaryCategory.FREESTYLE)
                    )
                }
            } catch (e: NoteValidationException) {
                _noteValidationErrors.emit(e.message ?: "Validation failed")
            } catch (e: IllegalArgumentException) {
                _noteValidationErrors.emit(e.message ?: "Update failed")
            }
        }
    }

    fun applyNoteInboxDeadlines() {
        viewModelScope.launch {
            noteRepository.checkInboxDeadline()
        }
    }

    fun firstDateExceedingLimitIfAdded(newTasks: List<Task>): LocalDate? {
        val current = uiState.value.tasks
        val addsByDate = newTasks.filter { !it.isCompleted }.groupBy { it.taskDate }
        for ((date, adds) in addsByDate) {
            val n = current.count { it.taskDate == date && !it.isCompleted }
            if (n + adds.size > DAILY_PENDING_LIMIT) return date
        }
        return null
    }

    fun deleteAllTasksForDate(date: LocalDate) {
        viewModelScope.launch {
            deleteMutex.withLock {
                val current = repository.tasksFlow.first()
                repository.saveTasks(current.filter { it.taskDate != date })
            }
        }
    }

    companion object {
        const val DAILY_PENDING_LIMIT = 12

        fun factory(appContext: Context): ViewModelProvider.Factory {
            val repository = TaskRepository(appContext)
            val noteRepository = NoteRepository(appContext)
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
                        return TaskViewModel(repository, noteRepository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
        }
    }
}

private fun Note.toPublishedFromInbox(primaryCategory: String): Note {
    val isTopic = NotePrimaryCategory.isTopic(primaryCategory)
    val secondary = when {
        !isTopic -> ""
        secondaryCategory.isNotBlank() -> secondaryCategory
        else -> defaultSecondaryForInboxQuickPublish(primaryCategory)
    }
    val behavior = behaviorSummary.ifBlank {
        if (isTopic) "Untitled capture" else ""
    }
    val now = System.currentTimeMillis()
    return copy(
        primaryCategory = primaryCategory,
        secondaryCategory = secondary,
        behaviorSummary = behavior,
        needsManualClassification = false,
        status = NoteStatus.PUBLISHED,
        deadline = null,
        updatedAt = now
    )
}

private fun defaultSecondaryForInboxQuickPublish(primary: String): String = when (primary) {
    NotePrimaryCategory.SELF_AWARENESS -> "Emotional Trigger Event"
    NotePrimaryCategory.INTERPERSONAL -> "Friend Interaction"
    NotePrimaryCategory.INTIMACY_FAMILY -> "Partner Conflict"
    NotePrimaryCategory.SOMATIC_ENERGY -> "Fatigue Signal"
    NotePrimaryCategory.MEANING -> "Career Questioning"
    else -> "Daily Note"
}
