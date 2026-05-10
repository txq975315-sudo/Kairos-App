package com.example.kairosapplication.widget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.util.TypedValue
import com.example.kairosapplication.R
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.widget.data.WidgetConfig
import com.example.kairosapplication.widget.data.WidgetConfigRepository
import com.example.kairosapplication.widget.data.WidgetDataRepository
import com.example.kairosapplication.widget.data.WidgetLayoutKind
import com.example.kairosapplication.widget.data.WidgetSize
import com.example.kairosapplication.widget.data.WidgetTaskRow
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.repository.TaskRepository
import com.example.taskmodel.util.TaskUtils
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class Widget1x1DisplayState(
    val dateText: String,
    val completed: Int,
    val total: Int,
    val quoteBody: String,
    val language: LocalizationManager.Language
)

data class Widget2x2DisplayState(
    val dateLine: String,
    val statsLine: String,
    val quoteLine: String,
    val tasks: List<WidgetTaskRow>,
    val showTasks: Boolean,
    val showQuote: Boolean,
    val language: LocalizationManager.Language,
    val monthTitle: String,
    val monthGrid: String
)

data class Widget1x1TodoLine(
    val title: String,
    val done: Boolean,
    val taskId: Int = -1,
    val urgency: Int = TaskConstants.URGENCY_NORMAL
)

data class Widget1x1TodoDisplayState(
    val dateText: String,
    val completed: Int,
    val total: Int,
    val lines: List<Widget1x1TodoLine>,
    val quoteBody: String,
    val language: LocalizationManager.Language
)

object WidgetManager {
    private val dateFormatterMmDd: DateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd")
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val allProviderClasses = listOf(
        KairosWidgetProvider1x1::class.java,
        KairosWidgetProvider2x2::class.java,
        KairosWidgetProvider3x1::class.java,
        KairosWidgetProvider3x3::class.java
    )

    private fun forcedHintFromProvider(
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ): WidgetSize? {
        val info = runCatching { appWidgetManager.getAppWidgetInfo(appWidgetId) }.getOrNull()
            ?: return null
        return when (info.provider.className) {
            KairosWidgetProvider1x1::class.java.name -> WidgetSize._1X1
            KairosWidgetProvider2x2::class.java.name -> WidgetSize._2X2
            KairosWidgetProvider3x1::class.java.name -> WidgetSize._3X1
            KairosWidgetProvider3x3::class.java.name -> WidgetSize._3X3
            else -> null
        }
    }

    fun getSizeFromOptions(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ): WidgetSize {
        val options = appWidgetManager.getAppWidgetOptions(appWidgetId)
        var minWidth = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH, 0)
        var minHeight = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT, 0)
        if (minWidth <= 0 || minHeight <= 0) {
            val info = runCatching { appWidgetManager.getAppWidgetInfo(appWidgetId) }.getOrNull()
            if (info != null) {
                val dm = context.resources.displayMetrics
                minWidth = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    info.minWidth.toFloat(),
                    dm
                ).toInt()
                minHeight = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    info.minHeight.toFloat(),
                    dm
                ).toInt()
            }
        }
        if (minWidth <= 0 || minHeight <= 0) return WidgetSize._1X1
        return when {
            minWidth in 1..149 && minHeight in 1..149 -> WidgetSize._1X1
            minWidth in 1..249 && minHeight in 1..249 -> WidgetSize._2X2
            minWidth >= 250 && minHeight in 1..149 -> WidgetSize._3X1
            minWidth in 1..149 && minHeight >= 250 -> WidgetSize._3X1
            else -> WidgetSize._3X3
        }
    }

    suspend fun load1x1DisplayState(context: Context): Widget1x1DisplayState {
        val appContext = context.applicationContext
        val today = LocalDate.now()
        val dateText = today.format(dateFormatterMmDd)
        val taskRepository = TaskRepository(appContext)
        val dayTasks = taskRepository.getTasksSnapshot().filter { it.taskDate == today }
        val total = dayTasks.size
        val completed = dayTasks.count { it.isCompleted }
        val dataStoreManager = DataStoreManager(appContext)
        val language = LocalizationManager.Language.fromCode(dataStoreManager.getLanguageSync())
        val quoteBody = loadDailyQuoteBody(taskRepository, language, appContext)
        return Widget1x1DisplayState(
            dateText = dateText,
            completed = completed,
            total = total,
            quoteBody = quoteBody,
            language = language
        )
    }

    suspend fun load1x1TodoDisplayState(context: Context, config: WidgetConfig): Widget1x1TodoDisplayState {
        val appContext = context.applicationContext
        val today = LocalDate.now()
        val dateText = today.format(dateFormatterMmDd)
        val taskRepository = TaskRepository(appContext)
        val dayTasks = TaskUtils.sortTasks(
            taskRepository.getTasksSnapshot().filter { it.taskDate == today }
        )
        val dataStoreManager = DataStoreManager(appContext)
        val language = LocalizationManager.Language.fromCode(dataStoreManager.getLanguageSync())
        val showTasks = config.displayConfig.showTasks
        val total = if (showTasks) dayTasks.size else 0
        val completed = if (showTasks) dayTasks.count { it.isCompleted } else 0
        val lines = if (showTasks) {
            dayTasks.take(3).map {
                Widget1x1TodoLine(
                    it.title.ifBlank { "—" },
                    it.isCompleted,
                    it.id,
                    it.urgency
                )
            }
        } else {
            emptyList()
        }
        val quoteBody = loadDailyQuoteBody(taskRepository, language, appContext)
        return Widget1x1TodoDisplayState(
            dateText = dateText,
            completed = completed,
            total = total,
            lines = lines,
            quoteBody = quoteBody,
            language = language
        )
    }

    private suspend fun loadDailyQuoteBody(
        taskRepository: TaskRepository,
        language: LocalizationManager.Language,
        appContext: Context,
    ): String {
        val defaultText = LocalizedStrings.stringFor(language, "widget_quote_default", appContext)
        val id = taskRepository.dailyQuoteEssayIdFlow.first() ?: return defaultText
        val essay = taskRepository.essaysFlow.first().find { it.id == id } ?: return defaultText
        val line = essay.body.trim().lineSequence().firstOrNull()?.trim().orEmpty()
        if (line.isEmpty()) return defaultText
        return if (line.length > 200) line.take(200) + "…" else line
    }

    suspend fun load2x2DisplayState(context: Context, config: WidgetConfig): Widget2x2DisplayState {
        val appContext = context.applicationContext
        val today = LocalDate.now()
        val language =
            LocalizationManager.Language.fromCode(DataStoreManager(appContext).getLanguageSync())
        val locale =
            if (language == LocalizationManager.Language.ZH) Locale.CHINA else Locale.ENGLISH
        val dateLine = today.dayOfWeek.getDisplayName(TextStyle.FULL, locale)
        val taskRepository = TaskRepository(appContext)
        val dayTasks =
            TaskUtils.sortTasks(taskRepository.getTasksSnapshot().filter { it.taskDate == today })
        val showTasks = config.displayConfig.showTasks
        val total = if (showTasks) dayTasks.size else 0
        val completed = if (showTasks) dayTasks.count { it.isCompleted } else 0
        val statsLine = if (!showTasks) {
            "—"
        } else {
            String.format(
                Locale.US,
                LocalizedStrings.stringFor(language, "widget_2x2_stats", appContext),
                completed,
                total
            )
        }
        val quoteBody = loadDailyQuoteBody(taskRepository, language, appContext)
        val quotePrefix = LocalizedStrings.stringFor(language, "widget_quote_prefix", appContext)
        val rawQuote = if (config.displayConfig.showDailyQuote) {
            if (quotePrefix.isNotEmpty()) quotePrefix + quoteBody else quoteBody
        } else {
            ""
        }
        val quoteLine = when {
            rawQuote.isEmpty() -> ""
            rawQuote.length <= 120 -> rawQuote
            else -> rawQuote.take(117) + "…"
        }
        val tasks = if (showTasks) {
            dayTasks.take(10).map {
                WidgetTaskRow(it.title.ifBlank { "—" }, it.isCompleted, it.id, it.urgency)
            }
        } else {
            emptyList()
        }
        val (monthTitle, monthGrid) = WidgetViewFactory.build2x2CalendarExtras(today, language)
        return Widget2x2DisplayState(
            dateLine = dateLine,
            statsLine = statsLine,
            quoteLine = quoteLine,
            tasks = tasks,
            showTasks = showTasks,
            showQuote = config.displayConfig.showDailyQuote,
            language = language,
            monthTitle = monthTitle,
            monthGrid = monthGrid
        )
    }

    suspend fun updateWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        configRepository: WidgetConfigRepository? = null,
        taskViewModel: TaskViewModel? = null,
        forcedSizeHint: WidgetSize? = null
    ) {
        val appContext = context.applicationContext
        val repo = configRepository ?: WidgetConfigRepository(DataStoreManager(appContext))
        val measured = getSizeFromOptions(appContext, appWidgetManager, appWidgetId)
        val hint = forcedSizeHint ?: forcedHintFromProvider(appWidgetManager, appWidgetId)
        val size = WidgetSizeResolver.resolve(hint, measured)
        val config = repo.getConfig(size).copy(size = size)
        val language =
            LocalizationManager.Language.fromCode(DataStoreManager(appContext).getLanguageSync())
        val remoteViews = if (taskViewModel != null) {
            val dataRepo = WidgetDataRepository(taskViewModel)
            WidgetViewFactory.createRemoteViewsWithData(
                appContext,
                config,
                dataRepo,
                language,
                appWidgetId
            )
        } else {
            when {
                config.size == WidgetSize._1X1 && config.layoutKind == WidgetLayoutKind._1B -> {
                    val st = load1x1TodoDisplayState(appContext, config)
                    WidgetViewFactory.createRemoteViews1x1_1b(appContext, config, st, appWidgetId)
                }
                config.size == WidgetSize._1X1 -> {
                    val state = load1x1DisplayState(appContext)
                    WidgetViewFactory.createRemoteViews(appContext, config, state, appWidgetId)
                }
                config.size == WidgetSize._2X2 -> {
                    val st = load2x2DisplayState(appContext, config)
                    WidgetViewFactory.createRemoteViews2x2(appContext, config, st, appWidgetId)
                }
                config.size == WidgetSize._3X1 ->
                    WidgetViewFactory.createRemoteViews3x1Fallback(
                        appContext,
                        config,
                        language,
                        appWidgetId
                    )
                config.size == WidgetSize._3X3 ->
                    WidgetViewFactory.createRemoteViews3x3Fallback(
                        appContext,
                        config,
                        language,
                        appWidgetId
                    )
                else -> {
                    val state = load1x1DisplayState(appContext)
                    WidgetViewFactory.createRemoteViews(appContext, config, state, appWidgetId)
                }
            }
        }
        WidgetViewFactory.applyBackgroundToRemoteViews(appContext, config, remoteViews, size)
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        val notifyTaskList = when (config.size) {
            WidgetSize._2X2, WidgetSize._3X3 -> true
            WidgetSize._3X1 -> config.layoutKind == WidgetLayoutKind._3B ||
                config.layoutKind == WidgetLayoutKind._3D
            else -> false
        }
        if (notifyTaskList) {
            val listId = when {
                config.size == WidgetSize._3X1 && config.layoutKind == WidgetLayoutKind._3D ->
                    R.id.widget_3x1_week_task_list
                else -> R.id.widget_task_list
            }
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, listId)
        }
        if (config.size == WidgetSize._3X3) {
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_3x3_week_list)
        }
    }

    fun refreshWidgetsAsync(context: Context, taskViewModel: TaskViewModel?) {
        scope.launch {
            refreshWidgets(context, taskViewModel)
        }
    }

    suspend fun refreshWidgets(context: Context, taskViewModel: TaskViewModel? = null) {
        // Inject urgency config into WidgetTaskStyle
        val urgencyConfig = DataStoreManager(context).getUrgencyConfigSync()
        WidgetTaskStyle.updateFromConfig(urgencyConfig)
        TaskUtils.setUrgencyConfig(urgencyConfig)
        val appContext = context.applicationContext
        val appWidgetManager = AppWidgetManager.getInstance(appContext)
        val allIds = allProviderClasses.flatMap { cls ->
            appWidgetManager.getAppWidgetIds(ComponentName(appContext, cls)).toList()
        }.distinct()
        if (allIds.isEmpty()) return
        allIds.forEach { id ->
            updateWidget(appContext, appWidgetManager, id, taskViewModel = taskViewModel)
        }
    }
}
