package com.example.kairosapplication.ui.mine.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kairosapplication.data.DataExporter
import com.example.kairosapplication.data.DataImporter
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.model.ExportData
import com.example.taskmodel.repository.NoteRepository
import com.example.taskmodel.repository.TaskRepository
import com.example.taskmodel.viewmodel.TaskUiState
import com.example.taskmodel.model.UrgencyConfig
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(
    private val appContext: Context,
    private val dataStoreManager: DataStoreManager,
    private val dataExporter: DataExporter,
    private val dataImporter: DataImporter,
    taskUiState: StateFlow<TaskUiState>
) : ViewModel() {

    private val _cacheSizeBytes = MutableStateFlow(0L)
    val cacheSizeBytes: StateFlow<Long> = _cacheSizeBytes.asStateFlow()

    init {
        refreshCacheSize()
    }

    val lastBackupTime: StateFlow<Long> = dataStoreManager.lastBackupTimestampFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0L)

    val dailyReminder: StateFlow<Boolean> = dataStoreManager.getDailyReminder()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    val dailyReminderTime: StateFlow<String> = dataStoreManager.getDailyReminderTime()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "09:00")

    val dailyReflection: StateFlow<Boolean> = dataStoreManager.getDailyReflection()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    val dailyReflectionTime: StateFlow<String> = dataStoreManager.getDailyReflectionTime()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "21:00")

    val darkMode: StateFlow<String> = dataStoreManager.getDarkModeOption()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "system")

    val themeColor: StateFlow<String> = dataStoreManager.getThemeColor()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "blue")

    val uiTheme: StateFlow<String> = dataStoreManager.getUiTheme()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppUiTheme.STORAGE_GLASS)

    val language: StateFlow<String> = dataStoreManager.getLanguage()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "zh")

    fun setDailyReminder(enabled: Boolean) {
        viewModelScope.launch { dataStoreManager.setDailyReminder(enabled) }
    }

    fun setDailyReminderTime(time: String) {
        viewModelScope.launch { dataStoreManager.setDailyReminderTime(time) }
    }

    fun setDailyReflection(enabled: Boolean) {
        viewModelScope.launch { dataStoreManager.setDailyReflection(enabled) }
    }

    fun setDailyReflectionTime(time: String) {
        viewModelScope.launch { dataStoreManager.setDailyReflectionTime(time) }
    }

    fun setDarkMode(mode: String) {
        viewModelScope.launch { dataStoreManager.setDarkModeOption(mode) }
    }

    fun setThemeColor(color: String) {
        viewModelScope.launch { dataStoreManager.setThemeColor(color) }
    }

    fun setUiTheme(theme: String) {
        viewModelScope.launch { dataStoreManager.setUiTheme(theme) }
    }

    val atmosphereWallpaperUri: StateFlow<String?> = dataStoreManager.getAtmosphereWallpaperUri()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    fun setAtmosphereWallpaperUri(uri: String?) {
        viewModelScope.launch { dataStoreManager.setAtmosphereWallpaperUri(uri) }
    }

    fun setLanguage(language: String) {
        viewModelScope.launch { dataStoreManager.setLanguage(language) }
    }

    fun setDarkModeOption(option: String) = setDarkMode(option)

    fun setThemeColorKey(key: String) = setThemeColor(key)

    fun setLanguageKey(key: String) = setLanguage(key)

    val darkModeOption: StateFlow<String> = darkMode

    val themeColorKey: StateFlow<String> = themeColor

    val languageKey: StateFlow<String> = language

    // Urgency Config
    val urgencyConfig: StateFlow<UrgencyConfig> = dataStoreManager.urgencyConfigFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UrgencyConfig())

    fun setUrgencyConfig(config: UrgencyConfig) {
        viewModelScope.launch { dataStoreManager.saveUrgencyConfig(config) }
    }

    fun resetUrgencyConfig() {
        viewModelScope.launch { dataStoreManager.resetUrgencyConfig() }
    }


    fun getCacheSize(): Long = _cacheSizeBytes.value

    fun refreshCacheSize() {
        viewModelScope.launch {
            _cacheSizeBytes.value = withContext(Dispatchers.IO) {
                computeDirBytes(appContext.cacheDir)
            }
        }
    }

    fun clearCache() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                appContext.cacheDir.listFiles()?.forEach { child ->
                    if (child.isDirectory) child.deleteRecursively() else child.delete()
                }
            }
            refreshCacheSize()
        }
    }

    private fun computeDirBytes(root: File): Long {
        if (!root.exists()) return 0L
        var sum = 0L
        root.walkTopDown().forEach { f ->
            if (f.isFile) sum += f.length()
        }
        return sum
    }

    val exportCounts: StateFlow<ExportCounts> = combine(
        dataStoreManager.moodsFlow,
        taskUiState
    ) { moods, ui ->
        ExportCounts(
            notes = ui.notePublished.size + ui.noteInbox.size + ui.noteTrash.size,
            tasks = ui.tasks.size,
            moods = moods.size
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ExportCounts(0, 0, 0)
    )

    private val _exportState = MutableStateFlow<ExportState>(ExportState.Idle)
    val exportState: StateFlow<ExportState> = _exportState.asStateFlow()

    private val _importState = MutableStateFlow<ImportState>(ImportState.Idle)
    val importState: StateFlow<ImportState> = _importState.asStateFlow()

    private val _importPreview = MutableStateFlow<ExportData?>(null)
    val importPreview: StateFlow<ExportData?> = _importPreview.asStateFlow()

    fun clearExportState() {
        _exportState.value = ExportState.Idle
    }

    fun clearImportState() {
        _importState.value = ImportState.Idle
    }

    fun setImportPreview(data: ExportData?) {
        _importPreview.value = data
    }

    fun parseImportJson(json: String, onResult: (Result<ExportData>) -> Unit) {
        viewModelScope.launch {
            val r = dataImporter.parseImportFile(json)
            _importPreview.value = r.getOrNull()
            onResult(r)
        }
    }

    fun exportJson(
        includeNotes: Boolean,
        includeTasks: Boolean,
        includeMoods: Boolean,
        includeProfile: Boolean,
        customFileName: String? = null,
        saveToDocuments: Boolean = false
    ) {
        viewModelScope.launch {
            _exportState.value = ExportState.Running
            dataExporter.exportToJsonFile(
                includeNotes = includeNotes,
                includeTasks = includeTasks,
                includeMoods = includeMoods,
                includeProfile = includeProfile,
                customFileName = customFileName,
                saveToDocuments = saveToDocuments
            ).onSuccess { file ->
                dataStoreManager.saveLastBackupTimestamp(System.currentTimeMillis())
                _exportState.value = ExportState.Success(file)
            }.onFailure { e ->
                _exportState.value = ExportState.Error(e.message ?: "export failed")
            }
        }
    }

    fun exportTxt(
        customFileName: String? = null,
        saveToDocuments: Boolean = false
    ) {
        viewModelScope.launch {
            _exportState.value = ExportState.Running
            dataExporter.exportToTxtFile(
                customFileName = customFileName,
                saveToDocuments = saveToDocuments
            ).onSuccess { file ->
                dataStoreManager.saveLastBackupTimestamp(System.currentTimeMillis())
                _exportState.value = ExportState.Success(file)
            }.onFailure { e ->
                _exportState.value = ExportState.Error(e.message ?: "export failed")
            }
        }
    }

    fun importData(data: ExportData, mode: DataImporter.ImportMode) {
        viewModelScope.launch {
            _importState.value = ImportState.Running
            dataImporter.importData(data, mode).onSuccess { result ->
                _importState.value = ImportState.Success(result)
                _importPreview.value = null
            }.onFailure { e ->
                _importState.value = ImportState.Error(e.message ?: "import failed")
            }
        }
    }

    data class ExportCounts(val notes: Int, val tasks: Int, val moods: Int)

    sealed class ExportState {
        data object Idle : ExportState()
        data object Running : ExportState()
        data class Success(val file: File) : ExportState()
        data class Error(val message: String) : ExportState()
    }

    sealed class ImportState {
        data object Idle : ImportState()
        data object Running : ImportState()
        data class Success(val result: DataImporter.ImportResult) : ImportState()
        data class Error(val message: String) : ImportState()
    }

    companion object {
        fun factory(
            appContext: Context,
            taskUiState: StateFlow<TaskUiState>
        ): ViewModelProvider.Factory {
            val ctx = appContext.applicationContext
            val dataStore = DataStoreManager(ctx)
            val noteRepo = NoteRepository(ctx)
            val taskRepo = TaskRepository(ctx)
            val exporter = DataExporter(noteRepo, taskRepo, dataStore, ctx)
            val importer = DataImporter(noteRepo, taskRepo, dataStore)
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SettingsViewModel(ctx, dataStore, exporter, importer, taskUiState) as T
                }
            }
        }
    }
}
