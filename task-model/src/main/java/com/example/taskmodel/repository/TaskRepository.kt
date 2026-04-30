package com.example.taskmodel.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task
import java.io.IOException
import java.time.LocalDate
import org.json.JSONArray
import org.json.JSONObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.taskDataStore by preferencesDataStore(name = "kairos_tasks")

class TaskRepository(private val context: Context) {
    private object Keys {
        val tasksJson = stringPreferencesKey("tasks_json")
        val onboardingHandled = booleanPreferencesKey("onboarding_handled")
    }

    val tasksFlow: Flow<List<Task>> = context.taskDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { prefs ->
            parseTasks(prefs)
        }

    val onboardingHandledFlow: Flow<Boolean> = context.taskDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { prefs -> prefs[Keys.onboardingHandled] ?: false }

    suspend fun saveTasks(tasks: List<Task>) {
        context.taskDataStore.edit { prefs ->
            prefs[Keys.tasksJson] = encodeTasks(tasks)
        }
    }

    suspend fun setOnboardingHandled(handled: Boolean) {
        context.taskDataStore.edit { prefs ->
            prefs[Keys.onboardingHandled] = handled
        }
    }

    suspend fun appendTasks(newTasks: List<Task>) {
        if (newTasks.isEmpty()) return
        val current = tasksFlowFirst()
        saveTasks(current + newTasks)
    }

    suspend fun loadSampleTasksIfNeeded() {
        val current = tasksFlowFirst()
        if (current.isNotEmpty()) return
        saveTasks(buildSampleTasks())
    }

    private suspend fun tasksFlowFirst(): List<Task> {
        return tasksFlow.first()
    }

    private fun parseTasks(prefs: Preferences): List<Task> {
        val encoded = prefs[Keys.tasksJson].orEmpty()
        if (encoded.isBlank()) return emptyList()
        return decodeTasks(encoded)
    }

    private fun buildSampleTasks(): List<Task> {
        val today = LocalDate.now()
        return listOf(
            Task(1, "Learn Figma", timeBlock = TaskConstants.TIME_BLOCK_AFTERNOON, urgency = TaskConstants.URGENCY_NORMAL, taskDate = today),
            Task(2, "Write a PRD", timeBlock = TaskConstants.TIME_BLOCK_AFTERNOON, urgency = TaskConstants.URGENCY_HIGH, taskDate = today),
            Task(3, "Learn SQL", timeBlock = TaskConstants.TIME_BLOCK_AFTERNOON, urgency = TaskConstants.URGENCY_LOW, isCompleted = true, taskDate = today),
            Task(4, "Reading", timeBlock = TaskConstants.TIME_BLOCK_EVENING, urgency = TaskConstants.URGENCY_NORMAL, taskDate = today),
            Task(5, "Practice Kotlin", timeBlock = TaskConstants.TIME_BLOCK_EVENING, urgency = TaskConstants.URGENCY_URGENT, taskDate = today)
        )
    }
}

private data class TaskEntity(
    val id: Int,
    val title: String,
    val description: String = "",
    val timeBlock: String,
    val urgency: Int,
    val label: String? = null,
    val taskDate: String,
    val repeatRule: String = "NONE",
    val isCompleted: Boolean = false,
    val emojiImage: String? = null,
    val localImageUri: String? = null
)

private fun Task.toEntity(): TaskEntity = TaskEntity(
    id = id,
    title = title,
    description = description,
    timeBlock = timeBlock,
    urgency = urgency,
    label = label,
    taskDate = taskDate.toString(),
    repeatRule = repeatRule,
    isCompleted = isCompleted,
    emojiImage = emojiImage,
    localImageUri = localImageUri
)

private fun TaskEntity.toModel(): Task = Task(
    id = id,
    title = title,
    description = description,
    timeBlock = timeBlock,
    urgency = urgency,
    label = label,
    taskDate = LocalDate.parse(taskDate),
    repeatRule = repeatRule,
    isCompleted = isCompleted,
    emojiImage = emojiImage,
    localImageUri = localImageUri
)

private fun encodeTasks(tasks: List<Task>): String {
    val array = JSONArray()
    tasks.forEach { task ->
        val json = JSONObject().apply {
            put("id", task.id)
            put("title", task.title)
            put("description", task.description)
            put("timeBlock", task.timeBlock)
            put("urgency", task.urgency)
            put("label", task.label)
            put("taskDate", task.taskDate.toString())
            put("repeatRule", task.repeatRule)
            put("isCompleted", task.isCompleted)
            put("emojiImage", task.emojiImage)
            put("localImageUri", task.localImageUri)
        }
        array.put(json)
    }
    return array.toString()
}

private fun decodeTasks(raw: String): List<Task> {
    return runCatching {
        val array = JSONArray(raw)
        buildList {
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                add(
                    TaskEntity(
                        id = obj.optInt("id"),
                        title = obj.optString("title"),
                        description = obj.optString("description"),
                        timeBlock = obj.optString("timeBlock"),
                        urgency = obj.optInt("urgency"),
                        label = obj.optString("label").ifBlank { null },
                        taskDate = obj.optString("taskDate"),
                        repeatRule = obj.optString("repeatRule", "NONE"),
                        isCompleted = obj.optBoolean("isCompleted", false),
                        emojiImage = obj.optString("emojiImage").ifBlank { null },
                        localImageUri = obj.optString("localImageUri").ifBlank { null }
                    ).toModel()
                )
            }
        }
    }.getOrElse { emptyList() }
}
