package com.example.kairosapplication.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TaskReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != ACTION_TASK_REMINDER) return
        val appCtx = context.applicationContext
        val title = intent.getStringExtra(EXTRA_TITLE).orEmpty().ifBlank { return }
        val taskId = intent.getIntExtra(EXTRA_TASK_ID, -1)
        if (taskId < 0) return

        val helper = NotificationHelper(appCtx)
        helper.createNotificationChannels()
        helper.showTaskReminderNotification(taskId, title)
        TaskReminderScheduler.cancelAlarm(appCtx, taskId)
    }

    companion object {
        const val ACTION_TASK_REMINDER = "com.example.kairosapplication.ACTION_TASK_REMINDER"
        const val EXTRA_TASK_ID = "extra_task_id"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_TASK_DATE_EPOCH_DAY = "extra_task_date_epoch_day"
    }
}
