package com.example.kairosapplication.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.taskmodel.model.LocalProfile
import com.example.taskmodel.model.MoodRecord
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import org.json.JSONObject

private val Context.minePreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "kairos_mine_preferences"
)

private val KEY_PROFILE_JSON = stringPreferencesKey("profile_json")
private val KEY_MOODS_JSON = stringPreferencesKey("moods_json")
private val KEY_LAST_BACKUP_TIMESTAMP = longPreferencesKey("last_backup_timestamp")
private val KEY_SETTINGS_MY_WEEKLY_INSIGHTS =
    booleanPreferencesKey("settings_my_weekly_insights")

class DataStoreManager(context: Context) {

    private val appContext = context.applicationContext
    private val dataStore = appContext.minePreferencesDataStore

    private val keyDarkMode = stringPreferencesKey(KEY_SETTINGS_DARK_MODE)
    private val keyThemeColor = stringPreferencesKey(KEY_SETTINGS_THEME_COLOR)
    private val keyLanguage = stringPreferencesKey(KEY_SETTINGS_LANGUAGE)
    private val keyDailyReminder = booleanPreferencesKey(KEY_SETTINGS_DAILY_REMINDER)
    private val keyDailyReminderTime = stringPreferencesKey(KEY_SETTINGS_DAILY_REMINDER_TIME)
    private val keyDailyReflection = booleanPreferencesKey(KEY_SETTINGS_DAILY_REFLECTION)
    private val keyDailyReflectionTime = stringPreferencesKey(KEY_SETTINGS_DAILY_REFLECTION_TIME)

    val profileFlow: Flow<LocalProfile> = dataStore.data.map { prefs ->
        decodeProfile(prefs[KEY_PROFILE_JSON])
    }

    val moodsFlow: Flow<List<MoodRecord>> = dataStore.data.map { prefs ->
        decodeMoods(prefs[KEY_MOODS_JSON])
    }

    val weeklyInsightsEnabledFlow: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[KEY_SETTINGS_MY_WEEKLY_INSIGHTS] ?: true
    }

    val lastBackupTimestampFlow: Flow<Long> = dataStore.data.map { prefs ->
        prefs[KEY_LAST_BACKUP_TIMESTAMP] ?: 0L
    }

    fun getDarkMode(): Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[keyDarkMode] == "dark"
    }

    fun getDarkModeOption(): Flow<String> = dataStore.data.map { prefs ->
        prefs[keyDarkMode] ?: "system"
    }

    suspend fun setDarkModeOption(option: String) {
        dataStore.edit { prefs ->
            prefs[keyDarkMode] = option
        }
    }

    fun getThemeColor(): Flow<String> = dataStore.data.map { prefs ->
        normalizeThemeColor(prefs[keyThemeColor])
    }

    suspend fun setThemeColor(color: String) {
        dataStore.edit { prefs ->
            prefs[keyThemeColor] = normalizeThemeColorForStore(color)
        }
    }

    fun getLanguage(): Flow<String> = dataStore.data.map { prefs ->
        normalizeLanguage(prefs[keyLanguage])
    }

    suspend fun setLanguage(language: String) {
        dataStore.edit { prefs ->
            prefs[keyLanguage] = when (language) {
                "en" -> "en"
                else -> "zh"
            }
        }
    }

    suspend fun getLanguageValue(): String = normalizeLanguage(dataStore.data.first()[keyLanguage])

    fun getLanguageSync(): String = runBlocking(Dispatchers.IO) { getLanguageValue() }

    suspend fun getDailyReminderValue(): Boolean = dataStore.data.first()[keyDailyReminder] ?: false

    fun getDailyReminderSync(): Boolean = runBlocking(Dispatchers.IO) { getDailyReminderValue() }

    suspend fun getDailyReminderTimeValue(): String =
        dataStore.data.first()[keyDailyReminderTime] ?: "09:00"

    fun getDailyReminderTimeSync(): String = runBlocking(Dispatchers.IO) { getDailyReminderTimeValue() }

    suspend fun getDailyReflectionValue(): Boolean = dataStore.data.first()[keyDailyReflection] ?: false

    fun getDailyReflectionSync(): Boolean = runBlocking(Dispatchers.IO) { getDailyReflectionValue() }

    suspend fun getDailyReflectionTimeValue(): String =
        dataStore.data.first()[keyDailyReflectionTime] ?: "21:00"

    fun getDailyReflectionTimeSync(): String = runBlocking(Dispatchers.IO) { getDailyReflectionTimeValue() }

    fun getDailyReminder(): Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[keyDailyReminder] ?: false
    }

    suspend fun setDailyReminder(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[keyDailyReminder] = enabled
        }
    }

    fun getDailyReminderTime(): Flow<String> = dataStore.data.map { prefs ->
        prefs[keyDailyReminderTime] ?: "09:00"
    }

    suspend fun setDailyReminderTime(time: String) {
        dataStore.edit { prefs ->
            prefs[keyDailyReminderTime] = time
        }
    }

    fun getDailyReflection(): Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[keyDailyReflection] ?: false
    }

    suspend fun setDailyReflection(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[keyDailyReflection] = enabled
        }
    }

    fun getDailyReflectionTime(): Flow<String> = dataStore.data.map { prefs ->
        prefs[keyDailyReflectionTime] ?: "21:00"
    }

    suspend fun setDailyReflectionTime(time: String) {
        dataStore.edit { prefs ->
            prefs[keyDailyReflectionTime] = time
        }
    }

    suspend fun saveProfile(profile: LocalProfile) {
        dataStore.edit { prefs ->
            prefs[KEY_PROFILE_JSON] = encodeProfile(profile)
        }
    }

    suspend fun getProfile(): LocalProfile {
        val prefs = dataStore.data.first()
        return decodeProfile(prefs[KEY_PROFILE_JSON])
    }

    suspend fun saveMoods(moods: List<MoodRecord>) {
        dataStore.edit { prefs ->
            prefs[KEY_MOODS_JSON] = encodeMoods(moods)
        }
    }

    suspend fun getMoods(): List<MoodRecord> {
        val prefs = dataStore.data.first()
        return decodeMoods(prefs[KEY_MOODS_JSON])
    }

    suspend fun saveLastBackupTimestamp(timestamp: Long) {
        dataStore.edit { prefs ->
            prefs[KEY_LAST_BACKUP_TIMESTAMP] = timestamp
        }
    }

    suspend fun getLastBackupTimestamp(): Long {
        val prefs = dataStore.data.first()
        return prefs[KEY_LAST_BACKUP_TIMESTAMP] ?: 0L
    }

    suspend fun saveSetting(key: String, value: Boolean) {
        dataStore.edit { prefs ->
            prefs[booleanPreferencesKey(key)] = value
        }
    }

    suspend fun getSetting(key: String, default: Boolean): Boolean {
        val prefs = dataStore.data.first()
        return prefs[booleanPreferencesKey(key)] ?: default
    }

    suspend fun setWeeklyInsightsEnabled(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[KEY_SETTINGS_MY_WEEKLY_INSIGHTS] = enabled
        }
    }

    companion object {
        const val KEY_SETTINGS_DARK_MODE = "settings_dark_mode"
        const val KEY_SETTINGS_THEME_COLOR = "settings_theme_color"
        const val KEY_SETTINGS_LANGUAGE = "settings_language"
        const val KEY_SETTINGS_DAILY_REMINDER = "settings_daily_reminder"
        const val KEY_SETTINGS_DAILY_REMINDER_TIME = "settings_daily_reminder_time"
        const val KEY_SETTINGS_DAILY_REFLECTION = "settings_daily_reflection"
        const val KEY_SETTINGS_DAILY_REFLECTION_TIME = "settings_daily_reflection_time"

        private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

        fun encodeProfile(profile: LocalProfile): String {
            val o = JSONObject()
            o.put("displayName", profile.displayName)
            o.put("avatarEmoji", profile.avatarEmoji)
            if (profile.avatarImageUri != null) {
                o.put("avatarImageUri", profile.avatarImageUri)
            } else {
                o.put("avatarImageUri", JSONObject.NULL)
            }
            return o.toString()
        }

        fun decodeProfile(json: String?): LocalProfile {
            if (json.isNullOrBlank()) return LocalProfile()
            return try {
                val o = JSONObject(json)
                val uri = if (o.isNull("avatarImageUri")) null else o.optString("avatarImageUri")
                    .takeIf { it.isNotEmpty() }
                LocalProfile(
                    displayName = o.optString("displayName", "用户昵称").ifBlank { "用户昵称" },
                    avatarEmoji = o.optString("avatarEmoji", "😊").ifBlank { "😊" },
                    avatarImageUri = uri
                )
            } catch (_: Exception) {
                LocalProfile()
            }
        }

        fun encodeMoods(moods: List<MoodRecord>): String {
            val arr = JSONArray()
            for (m in moods) {
                val o = JSONObject()
                o.put("date", m.date.format(dateFormatter))
                o.put("moodIcon", m.moodIcon)
                arr.put(o)
            }
            return arr.toString()
        }

        fun decodeMoods(json: String?): List<MoodRecord> {
            if (json.isNullOrBlank()) return emptyList()
            return try {
                val arr = JSONArray(json)
                buildList {
                    for (i in 0 until arr.length()) {
                        val o = arr.getJSONObject(i)
                        val dateStr = o.optString("date")
                        val icon = o.optString("moodIcon")
                        if (dateStr.isNotBlank() && icon.isNotBlank()) {
                            add(MoodRecord(LocalDate.parse(dateStr, dateFormatter), icon))
                        }
                    }
                }
            } catch (_: Exception) {
                emptyList()
            }
        }
    }

    private fun normalizeThemeColor(raw: String?): String {
        if (raw.isNullOrBlank()) return "blue"
        return when (raw) {
            "vitality_blue", "blue" -> "blue"
            "forest_green", "green" -> "green"
            "pink" -> "pink"
            "sunset_orange", "orange" -> "orange"
            else -> "blue"
        }
    }

    private fun normalizeThemeColorForStore(color: String): String =
        when (color) {
            "green", "pink", "orange", "blue" -> color
            else -> "blue"
        }

    private fun normalizeLanguage(raw: String?): String {
        if (raw.isNullOrBlank()) return "zh"
        return when (raw) {
            "en" -> "en"
            "zh", "zh_CN" -> "zh"
            else -> "zh"
        }
    }
}
