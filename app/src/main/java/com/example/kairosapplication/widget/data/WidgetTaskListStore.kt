package com.example.kairosapplication.widget.data

import android.content.Context
import com.example.taskmodel.constants.TaskConstants
import org.json.JSONArray
import org.json.JSONObject

data class WidgetTaskRow(
    val title: String,
    val done: Boolean,
    val taskId: Int = -1,
    val urgency: Int = TaskConstants.URGENCY_NORMAL
)

object WidgetTaskListStore {
    private const val PREF_NAME = "kairos_widget_task_rows"
    private fun key(appWidgetId: Int) = "rows_$appWidgetId"

    fun save(context: Context, appWidgetId: Int, rows: List<WidgetTaskRow>) {
        val arr = JSONArray()
        rows.forEach { row ->
            arr.put(
                JSONObject().apply {
                    put("t", row.title)
                    put("d", row.done)
                    put("i", row.taskId)
                    put("u", row.urgency)
                }
            )
        }
        context.applicationContext
            .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(key(appWidgetId), arr.toString())
            .apply()
    }

    fun load(context: Context, appWidgetId: Int): List<WidgetTaskRow> {
        val raw = context.applicationContext
            .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(key(appWidgetId), null)
            ?: return emptyList()
        return try {
            val arr = JSONArray(raw)
            val out = ArrayList<WidgetTaskRow>(arr.length())
            for (i in 0 until arr.length()) {
                val o = arr.optJSONObject(i) ?: continue
                out.add(
                    WidgetTaskRow(
                        title = o.optString("t", ""),
                        done = o.optBoolean("d", false),
                        taskId = o.optInt("i", -1),
                        urgency = o.optInt("u", TaskConstants.URGENCY_NORMAL)
                    )
                )
            }
            out
        } catch (_: Exception) {
            emptyList()
        }
    }
}
