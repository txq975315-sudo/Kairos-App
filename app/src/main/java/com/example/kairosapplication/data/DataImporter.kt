package com.example.kairosapplication.data

import com.example.kairosapplication.model.ExportData
import com.example.kairosapplication.model.toNote
import com.example.kairosapplication.model.toTask
import com.example.taskmodel.model.LocalProfile
import com.example.taskmodel.repository.NoteRepository
import com.example.taskmodel.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataImporter(
    private val noteRepository: NoteRepository,
    private val taskRepository: TaskRepository,
    private val dataStoreManager: DataStoreManager
) {

    suspend fun parseImportFile(jsonContent: String): Result<ExportData> =
        ExportDataCodec.decodeFromJson(jsonContent)

    suspend fun importData(
        data: ExportData,
        mode: ImportMode
    ): Result<ImportResult> = withContext(Dispatchers.IO) {
        runCatching {
            when (mode) {
                ImportMode.MERGE -> mergeInternal(data)
                ImportMode.OVERWRITE -> overwriteInternal(data)
            }
        }
    }

    private suspend fun mergeInternal(data: ExportData): ImportResult {
        val beforeNoteIds = noteRepository.getAllNotesOnce().map { it.id }.toSet()
        val notesToImport = data.notes.map { it.toNote() }
        var noteConflicts = 0
        for (n in notesToImport) {
            if (n.id in beforeNoteIds) noteConflicts++
        }
        noteRepository.mergeImportedNotesById(notesToImport)
        val afterNoteIds = noteRepository.getAllNotesOnce().map { it.id }.toSet()
        val newNotes = afterNoteIds.count { it !in beforeNoteIds }

        val curTasks = taskRepository.getTasksSnapshot()
        val taskIdSet = curTasks.map { it.id }.toSet()
        var taskConflicts = 0
        val mergedTasks = curTasks.toMutableList()
        for (t in data.tasks.map { it.toTask() }) {
            if (t.id in taskIdSet) {
                taskConflicts++
            } else {
                mergedTasks.add(t)
            }
        }
        val newTasks = mergedTasks.size - curTasks.size
        taskRepository.saveTasks(mergedTasks)

        val moodByDate = dataStoreManager.getMoods().associateBy { it.date }.toMutableMap()
        var moodConflicts = 0
        var newMoods = 0
        for (m in data.moods) {
            if (moodByDate.containsKey(m.date)) {
                moodConflicts++
            } else {
                moodByDate[m.date] = m
                newMoods++
            }
        }
        dataStoreManager.saveMoods(moodByDate.values.sortedBy { it.date })

        return ImportResult(
            newNotes = newNotes,
            newTasks = newTasks,
            newMoods = newMoods,
            conflicts = noteConflicts + taskConflicts + moodConflicts
        )
    }

    private suspend fun overwriteInternal(data: ExportData): ImportResult {
        noteRepository.replaceAllNotesForImport(data.notes.map { it.toNote() })
        taskRepository.saveTasks(data.tasks.map { it.toTask() })
        dataStoreManager.saveMoods(data.moods)
        if (data.profile != null) {
            dataStoreManager.saveProfile(data.profile)
        } else {
            dataStoreManager.saveProfile(LocalProfile())
        }
        return ImportResult(
            newNotes = data.notes.size,
            newTasks = data.tasks.size,
            newMoods = data.moods.size,
            conflicts = 0
        )
    }

    enum class ImportMode {
        MERGE,
        OVERWRITE
    }

    data class ImportResult(
        val newNotes: Int,
        val newTasks: Int,
        val newMoods: Int,
        val conflicts: Int
    )
}
