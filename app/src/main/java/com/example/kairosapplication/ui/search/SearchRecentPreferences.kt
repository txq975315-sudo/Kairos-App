package com.example.kairosapplication.ui.search

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray

private val Context.essaySearchRecentDataStore by preferencesDataStore(name = "essay_search_recent")

private val recentSearchesKey = stringPreferencesKey("recent_list_json")

private const val MAX_RECENT = 5

private fun encodeList(list: List<String>): String {
    val arr = JSONArray()
    list.forEach { arr.put(it) }
    return arr.toString()
}

private fun decodeList(json: String): List<String> {
    if (json.isBlank()) return emptyList()
    return runCatching {
        val arr = JSONArray(json)
        buildList {
            for (i in 0 until arr.length()) {
                add(arr.getString(i))
            }
        }
    }.getOrDefault(emptyList())
}

fun recentSearchesFlow(context: Context): Flow<List<String>> =
    context.essaySearchRecentDataStore.data.map { prefs ->
        decodeList(prefs[recentSearchesKey] ?: "[]").take(MAX_RECENT)
    }

suspend fun addRecentSearch(context: Context, query: String) {
    val q = query.trim()
    if (q.isEmpty()) return
    context.essaySearchRecentDataStore.edit { prefs ->
        val cur = decodeList(prefs[recentSearchesKey] ?: "[]").toMutableList()
        cur.removeAll { it.equals(q, ignoreCase = true) }
        cur.add(0, q)
        while (cur.size > MAX_RECENT) cur.removeAt(cur.lastIndex)
        prefs[recentSearchesKey] = encodeList(cur)
    }
}

suspend fun removeRecentSearch(context: Context, query: String) {
    context.essaySearchRecentDataStore.edit { prefs ->
        val cur = decodeList(prefs[recentSearchesKey] ?: "[]").filterNot { it == query }
        prefs[recentSearchesKey] = encodeList(cur)
    }
}

suspend fun clearRecentSearches(context: Context) {
    context.essaySearchRecentDataStore.edit { prefs ->
        prefs[recentSearchesKey] = encodeList(emptyList())
    }
}

suspend fun replaceRecentSearch(context: Context, old: String, new: String) {
    val n = new.trim()
    if (n.isEmpty()) return
    context.essaySearchRecentDataStore.edit { prefs ->
        val cur = decodeList(prefs[recentSearchesKey] ?: "[]").toMutableList()
        val idx = cur.indexOfFirst { it == old }
        if (idx < 0) return@edit
        cur[idx] = n
        val deduped = mutableListOf<String>()
        for (e in cur) {
            if (deduped.none { it.equals(e, ignoreCase = true) }) deduped.add(e)
        }
        prefs[recentSearchesKey] = encodeList(deduped.take(MAX_RECENT))
    }
}
