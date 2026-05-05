package com.example.kairosapplication.data

import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.example.kairosapplication.model.ExportData
import com.example.kairosapplication.model.toExportDto
import com.example.taskmodel.repository.NoteRepository
import com.example.taskmodel.repository.TaskRepository
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataExporter(
    private val noteRepository: NoteRepository,
    private val taskRepository: TaskRepository,
    private val dataStoreManager: DataStoreManager,
    private val context: Context
) {

    suspend fun exportToJsonFile(
        includeNotes: Boolean,
        includeTasks: Boolean,
        includeMoods: Boolean,
        includeProfile: Boolean
    ): Result<File> = withContext(Dispatchers.IO) {
        runCatching {
            val notes = if (includeNotes) noteRepository.getAllNotesOnce().map { it.toExportDto() } else emptyList()
            val tasks = if (includeTasks) taskRepository.getTasksSnapshot().map { it.toExportDto() } else emptyList()
            val moods = if (includeMoods) dataStoreManager.getMoods() else emptyList()
            val profile = if (includeProfile) dataStoreManager.getProfile() else null
            val exportDate = DateTimeFormatter.ofPattern("yyyy/M/d HH:mm").format(LocalDateTime.now())
            val appVersion = readVersionName()
            val data = ExportData(
                exportDate = exportDate,
                appVersion = appVersion,
                notes = notes,
                tasks = tasks,
                moods = moods,
                profile = profile
            )
            val json = ExportDataCodec.encodeToJson(data)
            val fileName = "kairos_export_${System.currentTimeMillis()}.json"
            val cacheDir = File(context.cacheDir, "exports").apply { mkdirs() }
            val cacheFile = File(cacheDir, fileName)
            cacheFile.writeText(json)
            writeToDownloads(fileName, json.toByteArray(Charsets.UTF_8), "application/json")
            cacheFile
        }
    }

    suspend fun exportToTxtFile(): Result<File> = withContext(Dispatchers.IO) {
        runCatching {
            val notes = noteRepository.getAllNotesOnce()
            val sb = StringBuilder()
            for (n in notes) {
                val title = n.behaviorSummary.ifBlank { n.body.lineSequence().firstOrNull()?.take(120) ?: "" }
                sb.append(title).append('\n').append(n.body).append("\n\n")
            }
            val text = sb.toString()
            val fileName = "kairos_notes_${System.currentTimeMillis()}.txt"
            val cacheDir = File(context.cacheDir, "exports").apply { mkdirs() }
            val cacheFile = File(cacheDir, fileName)
            cacheFile.writeText(text)
            writeToDownloads(fileName, text.toByteArray(Charsets.UTF_8), "text/plain")
            cacheFile
        }
    }

    private fun readVersionName(): String {
        return try {
            val pm = context.packageManager
            val pi = pm.getPackageInfo(context.packageName, 0)
            pi.versionName ?: "1.0"
        } catch (_: PackageManager.NameNotFoundException) {
            "1.0"
        }
    }

    private fun writeToDownloads(displayName: String, bytes: ByteArray, mimeType: String) {
        val resolver = context.contentResolver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
                put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + "/Kairos")
                put(MediaStore.MediaColumns.IS_PENDING, 1)
            }
            val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values) ?: return
            resolver.openOutputStream(uri)?.use { it.write(bytes) }
            values.clear()
            values.put(MediaStore.MediaColumns.IS_PENDING, 0)
            resolver.update(uri, values, null, null)
        }
    }
}
