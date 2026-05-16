package com.example.taskmodel.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteSecondaryCategories
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Essay
import com.example.taskmodel.model.EssayCategoryConfig
import com.example.taskmodel.model.EssayPrimaryCategoryConfig
import com.example.taskmodel.model.EssaySecondaryCategoryConfig
import com.example.taskmodel.model.EssayTopic
import com.example.taskmodel.model.Task
import java.io.IOException
import java.time.LocalDate
import java.util.Locale
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
        val essaysJson = stringPreferencesKey("essays_json")
        /** Essay id used as home daily quote; -1 if none */
        val dailyQuoteEssayId = longPreferencesKey("daily_quote_essay_id")
        /** Custom text for daily quote (takes priority over essay/note source); blank if none */
        val dailyQuoteCustomText = stringPreferencesKey("daily_quote_custom_text")
        /** Note id used as home daily quote; -1 if none */
        val dailyQuoteNoteId = longPreferencesKey("daily_quote_note_id")
        val noteCustomSecondariesJson = stringPreferencesKey("note_custom_secondary_categories_v1")
        val essayCategoryConfigJson = stringPreferencesKey("essay_category_config_v1")
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

    val essaysFlow: Flow<List<Essay>> = context.taskDataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { prefs -> parseEssays(prefs) }

    val dailyQuoteEssayIdFlow: Flow<Long?> = context.taskDataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { prefs ->
            val v = prefs[Keys.dailyQuoteEssayId] ?: -1L
            if (v < 0) null else v
        }

    val dailyQuoteCustomTextFlow: Flow<String> = context.taskDataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { prefs -> prefs[Keys.dailyQuoteCustomText].orEmpty() }

    val dailyQuoteNoteIdFlow: Flow<Long?> = context.taskDataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { prefs ->
            val v = prefs[Keys.dailyQuoteNoteId] ?: -1L
            if (v < 0) null else v
        }

    val essayCategoryConfigFlow: Flow<EssayCategoryConfig> = context.taskDataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { prefs ->
            decodeEssayCategoryConfigResolved(prefs)
        }

    suspend fun saveEssayCategoryConfig(config: EssayCategoryConfig) {
        val normalized = normalizeEssayCategoryConfig(config)
        context.taskDataStore.edit { prefs ->
            prefs[Keys.essayCategoryConfigJson] = encodeEssayCategoryConfig(normalized)
        }
    }

    suspend fun appendSecondaryToEssayCategory(primary: String, label: String) {
        val trimmed = label.trim()
        if (trimmed.isBlank() || !NotePrimaryCategory.isTopic(primary)) return
        context.taskDataStore.edit { prefs ->
            val config = decodeEssayCategoryConfigResolved(prefs)
            val prim = config.primaryOrNull(primary) ?: return@edit
            if (prim.secondaries.size >= 8) return@edit
            val lower = prim.secondaries.map { it.id.lowercase(Locale.US) }.toSet()
            if (trimmed.lowercase(Locale.US) in lower) return@edit
            val nextSec = prim.secondaries + EssaySecondaryCategoryConfig(id = trimmed, name = trimmed, guide = null)
            val nextConfig = config.updatePrimary(primary) { it.copy(secondaries = nextSec) }
            prefs[Keys.essayCategoryConfigJson] = encodeEssayCategoryConfig(normalizeEssayCategoryConfig(nextConfig))
        }
    }

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

    suspend fun getTasksSnapshot(): List<Task> = tasksFlowFirst()

    suspend fun loadSampleTasksIfNeeded() {
        val current = tasksFlowFirst()
        if (current.isNotEmpty()) return
        saveTasks(buildSampleTasks())
    }

    suspend fun saveEssays(essays: List<Essay>) {
        context.taskDataStore.edit { prefs ->
            prefs[Keys.essaysJson] = encodeEssays(essays)
        }
    }

    suspend fun setDailyQuoteEssayId(essayId: Long?) {
        context.taskDataStore.edit { prefs ->
            prefs[Keys.dailyQuoteEssayId] = essayId ?: -1L
        }
    }

    suspend fun setDailyQuoteCustomText(text: String) {
        context.taskDataStore.edit { prefs ->
            prefs[Keys.dailyQuoteCustomText] = text
        }
    }

    suspend fun setDailyQuoteNoteId(noteId: Long?) {
        context.taskDataStore.edit { prefs ->
            prefs[Keys.dailyQuoteNoteId] = noteId ?: -1L
        }
    }

    suspend fun clearAllDailyQuoteSources() {
        context.taskDataStore.edit { prefs ->
            prefs[Keys.dailyQuoteEssayId] = -1L
            prefs[Keys.dailyQuoteCustomText] = ""
            prefs[Keys.dailyQuoteNoteId] = -1L
        }
    }

    private suspend fun tasksFlowFirst(): List<Task> {
        return tasksFlow.first()
    }

    private fun parseTasks(prefs: Preferences): List<Task> {
        val encoded = prefs[Keys.tasksJson].orEmpty()
        if (encoded.isBlank()) return emptyList()
        return decodeTasks(encoded)
    }

    private fun parseEssays(prefs: Preferences): List<Essay> {
        val encoded = prefs[Keys.essaysJson].orEmpty()
        if (encoded.isBlank()) return emptyList()
        return decodeEssays(encoded)
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

    private fun decodeEssayCategoryConfigResolved(prefs: Preferences): EssayCategoryConfig {
        val raw = prefs[Keys.essayCategoryConfigJson]?.trim().orEmpty()
        val legacy = prefs[Keys.noteCustomSecondariesJson].orEmpty()
        if (raw.isNotBlank()) {
            return runCatching { parseEssayCategoryConfig(raw) }.getOrElse {
                EssayCategoryConfig.buildInitial(decodeSecondaryCategoriesMap(legacy))
            }
        }
        return EssayCategoryConfig.buildInitial(decodeSecondaryCategoriesMap(legacy))
    }

    private fun normalizeEssayCategoryConfig(config: EssayCategoryConfig): EssayCategoryConfig {
        val byKey = config.primaries.associateBy { it.key }
        val seed = EssayCategoryConfig.buildInitial(emptyMap())
        val primaries = EssayCategoryConfig.PRIMARY_KEYS_ORDER.map { key ->
            val existing = byKey[key]
            val fallback = seed.primary(key)
            val merged = if (existing == null) {
                fallback
            } else {
                existing.copy(
                    secondaries = existing.secondaries.take(8).map { sec ->
                        EssaySecondaryCategoryConfig(
                            id = sec.id.trim(),
                            name = sec.name.trim(),
                            guide = sec.guide?.trim()?.takeIf { it.isNotBlank() },
                        )
                    }.filter { it.id.isNotBlank() },
                )
            }
            if (key == NotePrimaryCategory.FREESTYLE) merged.copy(secondaries = emptyList()) else merged
        }
        return EssayCategoryConfig(primaries)
    }

    private fun encodeEssayCategoryConfig(config: EssayCategoryConfig): String {
        val root = JSONObject()
        val arr = JSONArray()
        config.primaries.forEach { p ->
            val sArr = JSONArray()
            p.secondaries.forEach { s ->
                sArr.put(
                    JSONObject().apply {
                        put("id", s.id)
                        put("name", s.name)
                        if (!s.guide.isNullOrBlank()) put("guide", s.guide)
                    },
                )
            }
            arr.put(
                JSONObject().apply {
                    put("key", p.key)
                    put("displayName", p.displayName)
                    put("emoji", p.emoji)
                    put("summaryGuide", p.summaryGuide)
                    put("bodyPlaceholder", p.bodyPlaceholder)
                    put("secondaries", sArr)
                },
            )
        }
        root.put("primaries", arr)
        return root.toString()
    }

    private fun parseEssayCategoryConfig(raw: String): EssayCategoryConfig {
        val o = JSONObject(raw)
        val arr = o.getJSONArray("primaries")
        val list = buildList {
            for (i in 0 until arr.length()) {
                val p = arr.getJSONObject(i)
                val secArr = p.optJSONArray("secondaries")
                val secs = buildList {
                    if (secArr != null) {
                        for (j in 0 until secArr.length()) {
                            val s = secArr.getJSONObject(j)
                            val guideRaw = s.optString("guide", "").trim()
                            add(
                                EssaySecondaryCategoryConfig(
                                    id = s.getString("id"),
                                    name = s.optString("name", ""),
                                    guide = guideRaw.takeIf { it.isNotEmpty() },
                                ),
                            )
                        }
                    }
                }
                add(
                    EssayPrimaryCategoryConfig(
                        key = p.getString("key"),
                        displayName = p.optString("displayName", ""),
                        emoji = p.optString("emoji", ""),
                        summaryGuide = p.optString("summaryGuide", ""),
                        bodyPlaceholder = p.optString("bodyPlaceholder", ""),
                        secondaries = secs.take(8),
                    ),
                )
            }
        }
        return EssayCategoryConfig(list)
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
    val localImageUri: String? = null,
    val reminderTime: String? = null,
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
    localImageUri = localImageUri,
    reminderTime = reminderTime,
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
    localImageUri = localImageUri,
    reminderTime = reminderTime,
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
            if (task.reminderTime != null) put("reminderTime", task.reminderTime) else put("reminderTime", JSONObject.NULL)
        }
        array.put(json)
    }
    return array.toString()
}

private fun encodeEssays(essays: List<Essay>): String {
    val array = JSONArray()
    essays.forEach { e ->
        val imgs = JSONArray()
        e.imageUris.forEach { imgs.put(it) }
        val tags = JSONArray()
        e.tags.forEach { tags.put(it) }
        val json = JSONObject().apply {
            put("id", e.id)
            put("topic", e.topic.name)
            put("body", e.body)
            put("images", imgs)
            put("tags", tags)
            put("createdAt", e.createdAtMillis)
            put("updatedAt", e.updatedAtMillis)
            put("isDraft", e.isDraft)
            put("weather", e.weather)
            put("mood", e.mood)
            put("location", e.locationLabel)
            put("isDailyQuote", e.isDailyQuote)
        }
        array.put(json)
    }
    return array.toString()
}

private fun decodeEssays(raw: String): List<Essay> {
    return runCatching {
        val array = JSONArray(raw)
        buildList {
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                val topic = EssayTopic.fromKey(obj.optString("topic")) ?: EssayTopic.SELF_AWARENESS
                val imgs = obj.optJSONArray("images")
                val imageUris = buildList {
                    if (imgs != null) {
                        for (j in 0 until imgs.length()) {
                            add(imgs.getString(j))
                        }
                    }
                }
                val tagsArr = obj.optJSONArray("tags")
                val tags = buildList {
                    if (tagsArr != null) {
                        for (j in 0 until tagsArr.length()) {
                            add(tagsArr.getString(j))
                        }
                    }
                }
                add(
                    Essay(
                        id = obj.optLong("id"),
                        topic = topic,
                        body = obj.optString("body"),
                        imageUris = imageUris,
                        tags = tags,
                        createdAtMillis = obj.optLong("createdAt"),
                        updatedAtMillis = obj.optLong("updatedAt", obj.optLong("createdAt")),
                        isDraft = obj.optBoolean("isDraft", false),
                        weather = obj.optString("weather").ifBlank { null },
                        mood = obj.optString("mood").ifBlank { null },
                        locationLabel = obj.optString("location").ifBlank { null },
                        isDailyQuote = obj.optBoolean("isDailyQuote", false)
                    )
                )
            }
        }
    }.getOrElse { emptyList() }
}

private fun encodeSecondaryCategoriesMap(map: Map<String, List<String>>): String {
    val o = JSONObject()
    map.forEach { (k, v) ->
        val arr = JSONArray()
        v.forEach { arr.put(it) }
        o.put(k, arr)
    }
    return o.toString()
}

private fun decodeSecondaryCategoriesMap(raw: String): Map<String, List<String>> =
    runCatching {
        if (raw.isBlank()) return emptyMap()
        val o = JSONObject(raw)
        val names = o.names() ?: return emptyMap()
        buildMap {
            for (i in 0 until names.length()) {
                val key = names.getString(i)
                val arr = o.getJSONArray(key)
                put(
                    key,
                    buildList {
                        for (j in 0 until arr.length()) {
                            add(arr.getString(j))
                        }
                    }
                )
            }
        }
    }.getOrElse { emptyMap() }

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
                        localImageUri = obj.optString("localImageUri").ifBlank { null },
                        reminderTime = if (obj.isNull("reminderTime")) null else obj.optString("reminderTime").ifBlank { null }
                    ).toModel()
                )
            }
        }
    }.getOrElse { emptyList() }
}
