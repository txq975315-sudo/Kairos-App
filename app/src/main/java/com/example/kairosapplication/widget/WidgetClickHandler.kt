package com.example.kairosapplication.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import com.example.kairosapplication.MainActivity
import kotlin.math.abs

object WidgetClickHandler {
    const val EXTRA_TARGET_PAGE = "widget_target_page"
    const val EXTRA_TASK_ID = "widget_task_id"
    const val TARGET_TODAY = "today"
    const val TARGET_ESSAY = "essay"
    const val TARGET_VIEW = "view"
    const val TARGET_MINE = "mine"
    const val TARGET_HOME = "home"
    const val TARGET_CREATE = "create"
    const val TARGET_EDIT_TASK = "edit_task"

    /** 0 = Monday … 6 = Sunday for [WidgetActions.ACTION_3X1_WEEK_SELECT_DAY]. */
    const val EXTRA_WEEK_DAY_INDEX = "widget_week_day_index"

    private const val SLOT_BASE = 64

    const val TASK_LIST_CLICK_TEMPLATE_SLOT = 40

    fun requestCode(appWidgetId: Int, slot: Int): Int = appWidgetId * SLOT_BASE + slot

    /**
     * ListView item fill-in supplies [EXTRA_TASK_ID]; toggles completion via broadcast.
     */
    fun taskListToggleTemplatePendingIntent(context: Context, appWidgetId: Int): PendingIntent {
        val intent = Intent(context, WidgetTaskToggleReceiver::class.java).apply {
            action = WidgetActions.ACTION_TOGGLE_TASK
        }
        val piFlags =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        return PendingIntent.getBroadcast(
            context,
            requestCode(appWidgetId, TASK_LIST_CLICK_TEMPLATE_SLOT),
            intent,
            piFlags
        )
    }

    fun activityPendingIntent(
        context: Context,
        appWidgetId: Int,
        slot: Int,
        target: String,
        taskId: Int = -1
    ): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(EXTRA_TARGET_PAGE, target)
            if (taskId >= 0) putExtra(EXTRA_TASK_ID, taskId)
        }
        return PendingIntent.getActivity(
            context,
            requestCode(appWidgetId, slot),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    fun setClick(
        context: Context,
        views: RemoteViews,
        appWidgetId: Int,
        viewId: Int,
        target: String,
        slot: Int,
        taskId: Int = -1
    ) {
        views.setOnClickPendingIntent(
            viewId,
            activityPendingIntent(context, appWidgetId, slot, target, taskId)
        )
    }

    fun widget3x1WeekNavPendingIntent(
        context: Context,
        appWidgetId: Int,
        action: String,
        slot: Int,
        weekDayIndex: Int = -1,
    ): PendingIntent {
        val intent = Intent(context, Widget3x1WeekInteractionReceiver::class.java).apply {
            this.action = action
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            if (weekDayIndex >= 0) putExtra(EXTRA_WEEK_DAY_INDEX, weekDayIndex)
        }
        val piFlags =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        return PendingIntent.getBroadcast(
            context,
            requestCode(appWidgetId, slot),
            intent,
            piFlags
        )
    }

    fun toggleTaskPendingIntent(context: Context, appWidgetId: Int, taskId: Int): PendingIntent {
        val intent = Intent(context, WidgetTaskToggleReceiver::class.java).apply {
            action = WidgetActions.ACTION_TOGGLE_TASK
            putExtra(EXTRA_TASK_ID, taskId)
        }
        val requestCode = abs(appWidgetId * 1_000_003 + taskId)
        return PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}
