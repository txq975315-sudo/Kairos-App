package com.example.kairosapplication.data

import com.example.kairosapplication.model.ExportData
import com.example.kairosapplication.model.NoteExportDto
import com.example.kairosapplication.model.TaskExportDto
import com.example.taskmodel.model.LocalProfile
import com.example.taskmodel.model.MoodRecord
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.json.JSONArray
import org.json.JSONObject

object ExportDataCodec {

    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    fun encodeToJson(data: ExportData): String {
        val root = JSONObject()
        root.put("exportDate", data.exportDate)
        root.put("appVersion", data.appVersion)
        root.put("schemaVersion", 1)
        val notesArr = JSONArray()
        for (n in data.notes) {
            val o = JSONObject()
            o.put("id", n.id)
            o.put("title", n.title)
            o.put("content", n.content)
            o.put("primaryCategory", n.primaryCategory)
            o.put(
                "linkedCategories",
                JSONArray().apply { n.linkedCategories.forEach { put(it) } }
            )
            o.put("status", n.status)
            o.put("recordedDate", n.recordedDate)
            o.put("createdAt", n.createdAt)
            o.put("secondaryCategory", n.secondaryCategory)
            o.put("behaviorSummary", n.behaviorSummary)
            o.put("sceneTags", JSONArray().apply { n.sceneTags.forEach { put(it) } })
            if (n.moodIcon != null) o.put("moodIcon", n.moodIcon) else o.put("moodIcon", JSONObject.NULL)
            o.put("projectIds", JSONArray().apply { n.projectIds.forEach { put(it) } })
            o.put("imageUris", JSONArray().apply { n.imageUris.forEach { put(it) } })
            o.put("updatedAt", n.updatedAt)
            if (n.deadline != null) o.put("deadline", n.deadline) else o.put("deadline", JSONObject.NULL)
            o.put("needsManualClassification", n.needsManualClassification)
            notesArr.put(o)
        }
        root.put("notes", notesArr)
        val tasksArr = JSONArray()
        for (t in data.tasks) {
            val o = JSONObject()
            o.put("id", t.id)
            o.put("title", t.title)
            o.put("taskDate", t.taskDate)
            o.put("timeBlock", t.timeBlock)
            o.put("isCompleted", t.isCompleted)
            o.put("urgency", t.urgency)
            o.put("createdAt", t.createdAt)
            o.put("description", t.description)
            if (t.label != null) o.put("label", t.label) else o.put("label", JSONObject.NULL)
            o.put("repeatRule", t.repeatRule)
            if (t.emojiImage != null) o.put("emojiImage", t.emojiImage) else o.put("emojiImage", JSONObject.NULL)
            if (t.localImageUri != null) o.put("localImageUri", t.localImageUri) else o.put("localImageUri", JSONObject.NULL)
            if (t.reminderTime != null) o.put("reminderTime", t.reminderTime) else o.put("reminderTime", JSONObject.NULL)
            tasksArr.put(o)
        }
        root.put("tasks", tasksArr)
        val moodsArr = JSONArray()
        for (m in data.moods) {
            val o = JSONObject()
            o.put("date", m.date.format(dateFormatter))
            o.put("moodIcon", m.moodIcon)
            moodsArr.put(o)
        }
        root.put("moods", moodsArr)
        if (data.profile != null) {
            root.put("profile", JSONObject(DataStoreManager.encodeProfile(data.profile)))
        } else {
            root.put("profile", JSONObject.NULL)
        }
        return root.toString()
    }

    fun decodeFromJson(json: String): Result<ExportData> = runCatching {
        val root = JSONObject(json)
        val exportDate = root.optString("exportDate", "")
        val appVersion = root.optString("appVersion", "")
        val notes = parseNotes(root.optJSONArray("notes"))
        val tasks = parseTasks(root.optJSONArray("tasks"))
        val moods = parseMoods(root.optJSONArray("moods"))
        val profile = if (root.isNull("profile")) {
            null
        } else {
            DataStoreManager.decodeProfile(root.getJSONObject("profile").toString())
        }
        ExportData(
            exportDate = exportDate,
            appVersion = appVersion,
            notes = notes,
            tasks = tasks,
            moods = moods,
            profile = profile
        )
    }

    private fun parseNotes(arr: JSONArray?): List<NoteExportDto> {
        if (arr == null) return emptyList()
        return buildList {
            for (i in 0 until arr.length()) {
                val o = arr.getJSONObject(i)
                val links = mutableListOf<String>()
                val la = o.optJSONArray("linkedCategories")
                if (la != null) {
                    for (j in 0 until la.length()) links.add(la.getString(j))
                }
                val tags = mutableListOf<String>()
                val ta = o.optJSONArray("sceneTags")
                if (ta != null) {
                    for (j in 0 until ta.length()) tags.add(ta.getString(j))
                }
                val pids = mutableListOf<Long>()
                val pa = o.optJSONArray("projectIds")
                if (pa != null) {
                    for (j in 0 until pa.length()) pids.add(pa.getLong(j))
                }
                val imgs = mutableListOf<String>()
                val ia = o.optJSONArray("imageUris")
                if (ia != null) {
                    for (j in 0 until ia.length()) imgs.add(ia.getString(j))
                }
                add(
                    NoteExportDto(
                        id = o.optLong("id"),
                        title = o.optString("title"),
                        content = o.optString("content"),
                        primaryCategory = o.optString("primaryCategory"),
                        linkedCategories = links,
                        status = o.optString("status"),
                        recordedDate = o.optString("recordedDate"),
                        createdAt = o.optLong("createdAt"),
                        secondaryCategory = o.optString("secondaryCategory", ""),
                        behaviorSummary = o.optString("behaviorSummary", ""),
                        sceneTags = tags,
                        moodIcon = if (o.isNull("moodIcon")) null else o.optString("moodIcon").ifBlank { null },
                        projectIds = pids,
                        imageUris = imgs,
                        updatedAt = o.optLong("updatedAt", o.optLong("createdAt")),
                        deadline = if (o.isNull("deadline")) null else o.optString("deadline").ifBlank { null },
                        needsManualClassification = o.optBoolean("needsManualClassification", false)
                    )
                )
            }
        }
    }

    private fun parseTasks(arr: JSONArray?): List<TaskExportDto> {
        if (arr == null) return emptyList()
        return buildList {
            for (i in 0 until arr.length()) {
                val o = arr.getJSONObject(i)
                add(
                    TaskExportDto(
                        id = o.optLong("id"),
                        title = o.optString("title"),
                        taskDate = o.optString("taskDate"),
                        timeBlock = o.optString("timeBlock"),
                        isCompleted = o.optBoolean("isCompleted"),
                        urgency = o.optInt("urgency"),
                        createdAt = o.optLong("createdAt"),
                        description = o.optString("description", ""),
                        label = if (o.isNull("label")) null else o.optString("label").ifBlank { null },
                        repeatRule = o.optString("repeatRule", "NONE"),
                        emojiImage = if (o.isNull("emojiImage")) null else o.optString("emojiImage").ifBlank { null },
                        localImageUri = if (o.isNull("localImageUri")) null else o.optString("localImageUri").ifBlank { null },
                        reminderTime = if (o.isNull("reminderTime")) null else o.optString("reminderTime").ifBlank { null }
                    )
                )
            }
        }
    }

    private fun parseMoods(arr: JSONArray?): List<MoodRecord> {
        if (arr == null) return emptyList()
        return buildList {
            for (i in 0 until arr.length()) {
                val o = arr.getJSONObject(i)
                val d = o.optString("date")
                val icon = o.optString("moodIcon")
                if (d.isNotBlank() && icon.isNotBlank()) {
                    add(MoodRecord(LocalDate.parse(d, dateFormatter), icon))
                }
            }
        }
    }
}
