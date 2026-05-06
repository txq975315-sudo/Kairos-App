package com.example.kairosapplication.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Bundle
import com.example.kairosapplication.widget.data.WidgetSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

abstract class KairosWidgetProviderBase : AppWidgetProvider() {
    abstract val boundSize: WidgetSize

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        scope.launch {
            appWidgetIds.forEach { id ->
                WidgetManager.updateWidget(
                    context.applicationContext,
                    appWidgetManager,
                    id,
                    forcedSizeHint = boundSize
                )
            }
        }
    }

    override fun onAppWidgetOptionsChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
        scope.launch {
            WidgetManager.updateWidget(
                context.applicationContext,
                appWidgetManager,
                appWidgetId,
                forcedSizeHint = boundSize
            )
        }
    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        job.cancel()
    }
}

class KairosWidgetProvider1x1 : KairosWidgetProviderBase() {
    override val boundSize = WidgetSize._1X1
}

class KairosWidgetProvider2x2 : KairosWidgetProviderBase() {
    override val boundSize = WidgetSize._2X2
}

class KairosWidgetProvider3x1 : KairosWidgetProviderBase() {
    override val boundSize = WidgetSize._3X1
}

class KairosWidgetProvider3x3 : KairosWidgetProviderBase() {
    override val boundSize = WidgetSize._3X3
}
