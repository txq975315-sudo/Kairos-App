package com.example.kairosapplication.widget.data

import com.example.kairosapplication.data.DataStoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WidgetConfigRepository(
    private val dataStoreManager: DataStoreManager
) {
    suspend fun getConfig(size: WidgetSize): WidgetConfig {
        val stored = dataStoreManager.getWidgetConfig(size)
        if (stored != null) return stored
        val def = WidgetDefaults.defaultConfig(size)
        dataStoreManager.saveWidgetConfig(size, def)
        return def
    }

    fun getConfigFlow(size: WidgetSize): Flow<WidgetConfig> {
        return dataStoreManager.getWidgetConfigFlow(size).map { it ?: WidgetDefaults.defaultConfig(size) }
    }

    suspend fun saveConfig(size: WidgetSize, config: WidgetConfig) {
        dataStoreManager.saveWidgetConfig(size, config)
    }

    suspend fun applyToAllSizes(config: WidgetConfig) {
        WidgetSize.entries.forEach { size ->
            saveConfig(size, config.copy(size = size))
        }
    }

    suspend fun resetToDefault(size: WidgetSize) {
        saveConfig(size, WidgetDefaults.defaultConfig(size))
    }
}
