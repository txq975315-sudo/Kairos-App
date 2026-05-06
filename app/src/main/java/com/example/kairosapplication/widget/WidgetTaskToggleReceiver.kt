package com.example.kairosapplication.widget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.taskmodel.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Toggles task completion from the 3×1 widget row tap without opening the app.
 */
class WidgetTaskToggleReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != WidgetActions.ACTION_TOGGLE_TASK) return
        val taskId = intent.getIntExtra(WidgetClickHandler.EXTRA_TASK_ID, -1)
        if (taskId < 0) return
        val pendingResult = goAsync()
        val app = context.applicationContext
        AppScope.launch {
            try {
                val repo = TaskRepository(app)
                val list = repo.getTasksSnapshot()
                val updated = list.map { t ->
                    if (t.id == taskId) t.copy(isCompleted = !t.isCompleted) else t
                }
                repo.saveTasks(updated)
                withContext(Dispatchers.Main) {
                    WidgetManager.refreshWidgetsAsync(app, null)
                }
            } catch (_: Exception) {
                // ignore IO/persist errors; widget stays stale until next refresh
            } finally {
                pendingResult.finish()
            }
        }
    }

    private companion object {
        private val AppScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }
}
