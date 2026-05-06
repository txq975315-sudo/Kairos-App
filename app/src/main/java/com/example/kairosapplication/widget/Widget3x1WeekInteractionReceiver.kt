package com.example.kairosapplication.widget

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.kairosapplication.widget.data.Widget3x1WeekStateStore
import java.time.LocalDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Handles week prev/next and day-of-week selection for the 3×1 week-task widget ([WidgetLayoutKind._3D]).
 */
class Widget3x1WeekInteractionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action ?: return
        if (action != WidgetActions.ACTION_3X1_WEEK_PREV &&
            action != WidgetActions.ACTION_3X1_WEEK_NEXT &&
            action != WidgetActions.ACTION_3X1_WEEK_SELECT_DAY
        ) {
            return
        }
        val appWidgetId = intent.getIntExtra(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        )
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) return
        val app = context.applicationContext
        val pendingResult = goAsync()
        AppScope.launch {
            try {
                val (off, sel) = Widget3x1WeekStateStore.load(app, appWidgetId)
                when (action) {
                    WidgetActions.ACTION_3X1_WEEK_PREV -> {
                        val newOff = (off - 1).coerceIn(-520, 520)
                        val monday = Widget3x1WeekStateStore.mondayForOffset(newOff)
                        val selDate = LocalDate.ofEpochDay(sel)
                        val newSel = if (selDate in monday..monday.plusDays(6)) {
                            sel
                        } else {
                            monday.toEpochDay()
                        }
                        Widget3x1WeekStateStore.save(app, appWidgetId, newOff, newSel)
                    }
                    WidgetActions.ACTION_3X1_WEEK_NEXT -> {
                        val newOff = (off + 1).coerceIn(-520, 520)
                        val monday = Widget3x1WeekStateStore.mondayForOffset(newOff)
                        val selDate = LocalDate.ofEpochDay(sel)
                        val newSel = if (selDate in monday..monday.plusDays(6)) {
                            sel
                        } else {
                            monday.toEpochDay()
                        }
                        Widget3x1WeekStateStore.save(app, appWidgetId, newOff, newSel)
                    }
                    WidgetActions.ACTION_3X1_WEEK_SELECT_DAY -> {
                        val dayIndex = intent.getIntExtra(WidgetClickHandler.EXTRA_WEEK_DAY_INDEX, 0)
                            .coerceIn(0, 6)
                        val monday = Widget3x1WeekStateStore.mondayForOffset(off)
                        val picked = monday.plusDays(dayIndex.toLong())
                        Widget3x1WeekStateStore.save(app, appWidgetId, off, picked.toEpochDay())
                    }
                }
                WidgetManager.updateWidget(
                    app,
                    AppWidgetManager.getInstance(app),
                    appWidgetId,
                )
            } catch (_: Exception) {
                // ignore
            } finally {
                pendingResult.finish()
            }
        }
    }

    private companion object {
        private val AppScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }
}
