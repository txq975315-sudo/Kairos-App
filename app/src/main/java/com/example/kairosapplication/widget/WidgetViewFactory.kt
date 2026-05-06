package com.example.kairosapplication.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.RemoteViews
import com.example.kairosapplication.R
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.widget.data.WidgetBackgroundType
import com.example.kairosapplication.widget.data.WidgetConfig
import com.example.kairosapplication.widget.data.WidgetDataRepository
import com.example.kairosapplication.widget.data.WidgetLayoutKind
import com.example.kairosapplication.widget.data.WidgetSize
import com.example.kairosapplication.widget.data.WidgetTaskListStore
import com.example.kairosapplication.widget.data.WidgetTaskRow
import com.example.taskmodel.model.Task
import com.example.taskmodel.repository.TaskRepository
import com.example.taskmodel.util.TaskUtils
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

object WidgetViewFactory {

    private val CHECKIN_IDS = intArrayOf(
        R.id.checkin_1,
        R.id.checkin_2,
        R.id.checkin_3,
        R.id.checkin_4,
        R.id.checkin_5,
        R.id.checkin_6,
        R.id.checkin_7
    )

    private val WD_IDS = intArrayOf(
        R.id.widget_wd_0,
        R.id.widget_wd_1,
        R.id.widget_wd_2,
        R.id.widget_wd_3,
        R.id.widget_wd_4,
        R.id.widget_wd_5,
        R.id.widget_wd_6
    )

    private val WS_IDS = intArrayOf(
        R.id.widget_ws_0,
        R.id.widget_ws_1,
        R.id.widget_ws_2,
        R.id.widget_ws_3,
        R.id.widget_ws_4,
        R.id.widget_ws_5,
        R.id.widget_ws_6
    )

    suspend fun createRemoteViewsWithData(
        context: Context,
        config: WidgetConfig,
        dataRepository: WidgetDataRepository,
        language: LocalizationManager.Language,
        appWidgetId: Int
    ): RemoteViews {
        return withContext(Dispatchers.Default) {
            val tasks = dataRepository.getAllTasks()
            when (config.size) {
                WidgetSize._1X1 ->
                    when (config.layoutKind) {
                        WidgetLayoutKind._1B ->
                            createRemoteViews1x1_1bWithData(
                                context,
                                config,
                                dataRepository,
                                language,
                                appWidgetId
                            )
                        else ->
                            createRemoteViews1x1WithData(context, config, dataRepository, language, appWidgetId)
                    }
                WidgetSize._2X2 ->
                    createRemoteViews2x2WithData(context, config, dataRepository, language, appWidgetId)
                WidgetSize._3X1 -> {
                    val tasks = dataRepository.getAllTasks()
                    when (config.layoutKind) {
                        WidgetLayoutKind._3B ->
                            build3x1_3b(context, config, language, appWidgetId, tasks)
                        WidgetLayoutKind._3C ->
                            build3x1_3c(context, config, language, appWidgetId, tasks)
                        else ->
                            build3x1_3a(context, config, language, appWidgetId, tasks)
                    }
                }
                WidgetSize._3X3 -> {
                    val tasks = dataRepository.getAllTasks()
                    val q = quoteCombined(context, config, language, dataRepository)
                    when (config.layoutKind) {
                        WidgetLayoutKind._4B ->
                            build3x3_4b(context, config, language, appWidgetId, tasks, q)
                        else ->
                            build3x3_4a(context, config, language, appWidgetId, tasks, q)
                    }
                }
            }
        }
    }

    suspend fun createRemoteViews3x1Fallback(
        context: Context,
        config: WidgetConfig,
        language: LocalizationManager.Language,
        appWidgetId: Int
    ): RemoteViews {
        return withContext(Dispatchers.IO) {
            val tasks = TaskRepository(context.applicationContext).getTasksSnapshot()
            when (config.layoutKind) {
                WidgetLayoutKind._3B ->
                    build3x1_3b(context, config, language, appWidgetId, tasks)
                WidgetLayoutKind._3C ->
                    build3x1_3c(context, config, language, appWidgetId, tasks)
                else ->
                    build3x1_3a(context, config, language, appWidgetId, tasks)
            }
        }
    }

    suspend fun createRemoteViews3x3Fallback(
        context: Context,
        config: WidgetConfig,
        language: LocalizationManager.Language,
        appWidgetId: Int
    ): RemoteViews {
        return withContext(Dispatchers.IO) {
            val tasks = TaskRepository(context.applicationContext).getTasksSnapshot()
            val quoteBody = loadQuoteBody(context, language)
            val q = formatQuoteDisplay(config, language, quoteBody)
            when (config.layoutKind) {
                WidgetLayoutKind._4B ->
                    build3x3_4b(context, config, language, appWidgetId, tasks, q)
                else ->
                    build3x3_4a(context, config, language, appWidgetId, tasks, q)
            }
        }
    }

    fun createRemoteViews(
        context: Context,
        config: WidgetConfig,
        state: Widget1x1DisplayState,
        appWidgetId: Int
    ): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_1x1_1a)
        views.setTextViewText(R.id.widget_date, state.dateText)
        val showTasks = config.displayConfig.showTasks
        val completed = if (showTasks) state.completed else 0
        val total = if (showTasks) state.total else 0
        val progressPercent = when {
            !showTasks || total <= 0 -> 0
            else -> ((completed * 100) / total).coerceIn(0, 100)
        }
        views.setProgressBar(R.id.widget_progress_circle, 100, progressPercent, false)
        views.setTextViewText(
            R.id.widget_progress_text,
            if (showTasks) "$completed/$total" else "0/0"
        )
        val quotePrefix = LocalizedStrings.stringFor(state.language, "widget_quote_prefix")
        val quoteLine = if (config.displayConfig.showDailyQuote) {
            if (quotePrefix.isNotEmpty()) quotePrefix + state.quoteBody else state.quoteBody
        } else {
            ""
        }
        if (config.displayConfig.showDailyQuote) {
            views.setViewVisibility(R.id.widget_daily_quote, View.VISIBLE)
            views.setTextViewText(R.id.widget_daily_quote, quoteLine)
            WidgetClickHandler.setClick(
                context,
                views,
                appWidgetId,
                R.id.widget_daily_quote,
                WidgetClickHandler.TARGET_ESSAY,
                10
            )
        } else {
            views.setViewVisibility(R.id.widget_daily_quote, View.GONE)
        }
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_root,
            WidgetClickHandler.TARGET_HOME,
            11
        )
        bindCreateButton(context, views, appWidgetId, 61)
        return views
    }

    fun createRemoteViews1x1_1b(
        context: Context,
        config: WidgetConfig,
        state: Widget1x1TodoDisplayState,
        appWidgetId: Int
    ): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_1x1_1b)
        views.setTextViewText(R.id.widget_date, state.dateText)
        val showTasks = config.displayConfig.showTasks
        views.setTextViewText(
            R.id.widget_todo_stats_1b,
            if (showTasks) "${state.completed}/${state.total}" else "—"
        )
        val lineIds = intArrayOf(R.id.widget_todo_line_0, R.id.widget_todo_line_1, R.id.widget_todo_line_2)
        for (i in lineIds.indices) {
            val line = state.lines.getOrNull(i)
            if (line != null && showTasks) {
                views.setViewVisibility(lineIds[i], View.VISIBLE)
                val sym = if (line.done) "✔" else "○"
                views.setTextViewText(lineIds[i], "$sym ${line.title}")
            } else {
                views.setViewVisibility(lineIds[i], View.GONE)
            }
        }
        val quotePrefix = LocalizedStrings.stringFor(state.language, "widget_quote_prefix")
        val quoteLine = if (config.displayConfig.showDailyQuote) {
            if (quotePrefix.isNotEmpty()) quotePrefix + state.quoteBody else state.quoteBody
        } else {
            ""
        }
        val displayQuote = when {
            quoteLine.isEmpty() -> ""
            quoteLine.length <= 72 -> quoteLine
            else -> quoteLine.take(69) + "…"
        }
        if (config.displayConfig.showDailyQuote) {
            views.setViewVisibility(R.id.widget_daily_quote, View.VISIBLE)
            views.setTextViewText(R.id.widget_daily_quote, displayQuote)
            WidgetClickHandler.setClick(
                context,
                views,
                appWidgetId,
                R.id.widget_daily_quote,
                WidgetClickHandler.TARGET_ESSAY,
                62
            )
        } else {
            views.setViewVisibility(R.id.widget_daily_quote, View.GONE)
        }
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_root,
            WidgetClickHandler.TARGET_HOME,
            63
        )
        bindCreateButton(context, views, appWidgetId, 64)
        return views
    }

    fun createRemoteViews2x2(
        context: Context,
        config: WidgetConfig,
        display: Widget2x2DisplayState,
        appWidgetId: Int
    ): RemoteViews {
        WidgetTaskListStore.save(context, appWidgetId, display.tasks)
        val views = RemoteViews(context.packageName, R.layout.widget_2x2_2a)
        views.setTextViewText(R.id.widget_date, display.dateLine)
        views.setTextViewText(R.id.widget_task_count, display.statsLine)
        views.setTextViewText(
            R.id.widget_task_list_empty,
            LocalizedStrings.stringFor(display.language, "widget_task_list_empty")
        )
        bindTaskList(context, views, appWidgetId, display.showTasks)
        if (display.showQuote) {
            views.setViewVisibility(R.id.widget_daily_quote, View.VISIBLE)
            views.setTextViewText(R.id.widget_daily_quote, display.quoteLine)
            WidgetClickHandler.setClick(
                context,
                views,
                appWidgetId,
                R.id.widget_daily_quote,
                WidgetClickHandler.TARGET_ESSAY,
                12
            )
        } else {
            views.setViewVisibility(R.id.widget_daily_quote, View.GONE)
        }
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_root,
            WidgetClickHandler.TARGET_HOME,
            14
        )
        bindCreateButton(context, views, appWidgetId, 69)
        return views
    }

    private suspend fun quoteCombined(
        context: Context,
        config: WidgetConfig,
        language: LocalizationManager.Language,
        dataRepository: WidgetDataRepository
    ): String {
        val defaultQuote = LocalizedStrings.stringFor(language, "widget_quote_default")
        val body = dataRepository.getDailyQuote(defaultQuote)
        return formatQuoteDisplay(config, language, body)
    }

    private fun formatQuoteDisplay(
        config: WidgetConfig,
        language: LocalizationManager.Language,
        quoteBody: String
    ): String {
        if (!config.displayConfig.showDailyQuote) return ""
        val quotePrefix = LocalizedStrings.stringFor(language, "widget_quote_prefix")
        val raw = if (quotePrefix.isNotEmpty()) quotePrefix + quoteBody else quoteBody
        return when {
            raw.length <= 120 -> raw
            else -> raw.take(117) + "…"
        }
    }

    private suspend fun loadQuoteBody(context: Context, language: LocalizationManager.Language): String {
        val taskRepository = TaskRepository(context.applicationContext)
        val defaultText = LocalizedStrings.stringFor(language, "widget_quote_default")
        val id = taskRepository.dailyQuoteEssayIdFlow.first() ?: return defaultText
        val essay = taskRepository.essaysFlow.first().find { it.id == id } ?: return defaultText
        val line = essay.body.trim().lineSequence().firstOrNull()?.trim().orEmpty()
        if (line.isEmpty()) return defaultText
        return if (line.length > 200) line.take(200) + "…" else line
    }

    private fun createRemoteViews1x1WithData(
        context: Context,
        config: WidgetConfig,
        dataRepository: WidgetDataRepository,
        language: LocalizationManager.Language,
        appWidgetId: Int
    ): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_1x1_1a)
        views.setTextViewText(R.id.widget_date, dataRepository.getTodayDateString())
        val showTasks = config.displayConfig.showTasks
        val taskData = dataRepository.getTodayTaskData()
        val completed = if (showTasks) taskData.completedCount else 0
        val total = if (showTasks) taskData.totalCount else 0
        val progressPercent = when {
            !showTasks || total <= 0 -> 0
            else -> (taskData.progressPercent * 100).toInt().coerceIn(0, 100)
        }
        views.setProgressBar(R.id.widget_progress_circle, 100, progressPercent, false)
        views.setTextViewText(
            R.id.widget_progress_text,
            if (showTasks) "$completed/$total" else "0/0"
        )
        val quotePrefix = LocalizedStrings.stringFor(language, "widget_quote_prefix")
        val defaultQuote = LocalizedStrings.stringFor(language, "widget_quote_default")
        val quoteBody = dataRepository.getDailyQuote(defaultQuote)
        val combined = if (config.displayConfig.showDailyQuote) {
            if (quotePrefix.isNotEmpty()) quotePrefix + quoteBody else quoteBody
        } else {
            ""
        }
        val displayQuote = when {
            combined.length <= 72 -> combined
            else -> combined.take(69) + "…"
        }
        if (config.displayConfig.showDailyQuote) {
            views.setViewVisibility(R.id.widget_daily_quote, View.VISIBLE)
            views.setTextViewText(R.id.widget_daily_quote, displayQuote)
            WidgetClickHandler.setClick(
                context,
                views,
                appWidgetId,
                R.id.widget_daily_quote,
                WidgetClickHandler.TARGET_ESSAY,
                15
            )
        } else {
            views.setViewVisibility(R.id.widget_daily_quote, View.GONE)
        }
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_root,
            WidgetClickHandler.TARGET_HOME,
            16
        )
        bindCreateButton(context, views, appWidgetId, 60)
        return views
    }

    private fun createRemoteViews1x1_1bWithData(
        context: Context,
        config: WidgetConfig,
        dataRepository: WidgetDataRepository,
        language: LocalizationManager.Language,
        appWidgetId: Int
    ): RemoteViews {
        val today = LocalDate.now()
        val views = RemoteViews(context.packageName, R.layout.widget_1x1_1b)
        views.setTextViewText(R.id.widget_date, dataRepository.getTodayDateString())
        val showTasks = config.displayConfig.showTasks
        val taskData = dataRepository.getTodayTaskData()
        val completed = if (showTasks) taskData.completedCount else 0
        val total = if (showTasks) taskData.totalCount else 0
        views.setTextViewText(
            R.id.widget_todo_stats_1b,
            if (showTasks) "$completed/$total" else "—"
        )
        val lines = if (showTasks) {
            TaskUtils.sortTasks(
                dataRepository.getAllTasks().filter { it.taskDate == today }
            ).take(3).map { Widget1x1TodoLine(it.title.ifBlank { "—" }, it.isCompleted) }
        } else {
            emptyList()
        }
        val lineIds = intArrayOf(R.id.widget_todo_line_0, R.id.widget_todo_line_1, R.id.widget_todo_line_2)
        for (i in lineIds.indices) {
            val line = lines.getOrNull(i)
            if (line != null) {
                views.setViewVisibility(lineIds[i], View.VISIBLE)
                val sym = if (line.done) "✔" else "○"
                views.setTextViewText(lineIds[i], "$sym ${line.title}")
            } else {
                views.setViewVisibility(lineIds[i], View.GONE)
            }
        }
        val quotePrefix = LocalizedStrings.stringFor(language, "widget_quote_prefix")
        val defaultQuote = LocalizedStrings.stringFor(language, "widget_quote_default")
        val quoteBody = dataRepository.getDailyQuote(defaultQuote)
        val combined = if (config.displayConfig.showDailyQuote) {
            if (quotePrefix.isNotEmpty()) quotePrefix + quoteBody else quoteBody
        } else {
            ""
        }
        val displayQuote = when {
            combined.isEmpty() -> ""
            combined.length <= 72 -> combined
            else -> combined.take(69) + "…"
        }
        if (config.displayConfig.showDailyQuote) {
            views.setViewVisibility(R.id.widget_daily_quote, View.VISIBLE)
            views.setTextViewText(R.id.widget_daily_quote, displayQuote)
            WidgetClickHandler.setClick(
                context,
                views,
                appWidgetId,
                R.id.widget_daily_quote,
                WidgetClickHandler.TARGET_ESSAY,
                65
            )
        } else {
            views.setViewVisibility(R.id.widget_daily_quote, View.GONE)
        }
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_root,
            WidgetClickHandler.TARGET_HOME,
            66
        )
        bindCreateButton(context, views, appWidgetId, 67)
        return views
    }

    private fun createRemoteViews2x2WithData(
        context: Context,
        config: WidgetConfig,
        dataRepository: WidgetDataRepository,
        language: LocalizationManager.Language,
        appWidgetId: Int
    ): RemoteViews {
        val today = LocalDate.now()
        val locale =
            if (language == LocalizationManager.Language.ZH) Locale.CHINA else Locale.ENGLISH
        val dateLine = today.format(DateTimeFormatter.ofPattern("MM-dd EEEE", locale))
        val showTasks = config.displayConfig.showTasks
        val taskData = dataRepository.getTodayTaskData()
        val completed = if (showTasks) taskData.completedCount else 0
        val total = if (showTasks) taskData.totalCount else 0
        val statsLine = formatStatsLine(language, completed, total, showTasks)
        val quotePrefix = LocalizedStrings.stringFor(language, "widget_quote_prefix")
        val defaultQuote = LocalizedStrings.stringFor(language, "widget_quote_default")
        val quoteBody = dataRepository.getDailyQuote(defaultQuote)
        val quoteCombined = if (config.displayConfig.showDailyQuote) {
            if (quotePrefix.isNotEmpty()) quotePrefix + quoteBody else quoteBody
        } else {
            ""
        }
        val quoteDisplay = when {
            quoteCombined.length <= 120 -> quoteCombined
            else -> quoteCombined.take(117) + "…"
        }
        val rows =
            if (showTasks) {
                TaskUtils.sortTasks(
                    dataRepository.getAllTasks().filter { it.taskDate == today }
                ).map {
                    WidgetTaskRow(it.title.ifBlank { "—" }, it.isCompleted, it.id)
                }.take(10)
            } else {
                emptyList()
            }
        return build2x2RemoteViews(
            context,
            config,
            appWidgetId,
            dateLine,
            statsLine,
            quoteDisplay,
            rows,
            showTasks,
            config.displayConfig.showDailyQuote,
            language
        )
    }

    private fun build2x2RemoteViews(
        context: Context,
        config: WidgetConfig,
        appWidgetId: Int,
        dateLine: String,
        statsLine: String,
        quoteDisplay: String,
        rows: List<WidgetTaskRow>,
        showTasks: Boolean,
        showQuote: Boolean,
        language: LocalizationManager.Language
    ): RemoteViews {
        WidgetTaskListStore.save(context, appWidgetId, rows)
        val views = RemoteViews(context.packageName, R.layout.widget_2x2_2a)
        views.setTextViewText(R.id.widget_date, dateLine)
        views.setTextViewText(R.id.widget_task_count, statsLine)
        views.setTextViewText(
            R.id.widget_task_list_empty,
            LocalizedStrings.stringFor(language, "widget_task_list_empty")
        )
        bindTaskList(context, views, appWidgetId, showTasks)
        if (showQuote) {
            views.setViewVisibility(R.id.widget_daily_quote, View.VISIBLE)
            views.setTextViewText(R.id.widget_daily_quote, quoteDisplay)
            WidgetClickHandler.setClick(
                context,
                views,
                appWidgetId,
                R.id.widget_daily_quote,
                WidgetClickHandler.TARGET_ESSAY,
                17
            )
        } else {
            views.setViewVisibility(R.id.widget_daily_quote, View.GONE)
        }
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_root,
            WidgetClickHandler.TARGET_HOME,
            19
        )
        bindCreateButton(context, views, appWidgetId, 68)
        return views
    }

    private fun build3x1_3a(
        context: Context,
        config: WidgetConfig,
        language: LocalizationManager.Language,
        appWidgetId: Int,
        tasks: List<Task>
    ): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_3x1_3a)
        val today = LocalDate.now()
        val locale =
            if (language == LocalizationManager.Language.ZH) Locale.CHINA else Locale.ENGLISH
        views.setTextViewText(
            R.id.widget_day_of_week,
            today.format(DateTimeFormatter.ofPattern("EEEE", locale))
        )
        views.setTextViewText(R.id.widget_date, today.format(DateTimeFormatter.ofPattern("MM-dd", locale)))
        val dayTasks = TaskUtils.sortTasks(tasks.filter { it.taskDate == today })
        val showTasks = config.displayConfig.showTasks
        val total = if (showTasks) dayTasks.size else 0
        val completed = if (showTasks) dayTasks.count { it.isCompleted } else 0
        val progressPercent = when {
            !showTasks || total <= 0 -> 0
            else -> ((completed * 100) / total).coerceIn(0, 100)
        }
        views.setProgressBar(R.id.widget_progress_circle, 100, progressPercent, false)
        views.setTextViewText(
            R.id.widget_progress_text,
            if (showTasks) "$completed/$total" else "0/0"
        )
        val rows =
            if (showTasks) {
                dayTasks.take(10).map {
                    WidgetTaskRow(it.title.ifBlank { "—" }, it.isCompleted, it.id)
                }
            } else {
                emptyList()
            }
        WidgetTaskListStore.save(context, appWidgetId, rows)
        views.setTextViewText(
            R.id.widget_task_list_empty,
            LocalizedStrings.stringFor(language, "widget_task_list_empty")
        )
        bindTaskList(context, views, appWidgetId, showTasks)
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_left_panel,
            WidgetClickHandler.TARGET_VIEW,
            20
        )
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_root,
            WidgetClickHandler.TARGET_HOME,
            22
        )
        bindCreateButton(context, views, appWidgetId, 70)
        return views
    }

    private fun build3x1_3b(
        context: Context,
        config: WidgetConfig,
        language: LocalizationManager.Language,
        appWidgetId: Int,
        tasks: List<Task>
    ): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_3x1_3b)
        val today = LocalDate.now()
        val locale =
            if (language == LocalizationManager.Language.ZH) Locale.CHINA else Locale.ENGLISH
        views.setTextViewText(R.id.widget_date, today.format(DateTimeFormatter.ofPattern("MM-dd", locale)))
        views.setTextViewText(
            R.id.widget_day_of_week,
            today.format(DateTimeFormatter.ofPattern("EEEE", locale))
        )
        val showTasks = config.displayConfig.showTasks
        val dayTasks = TaskUtils.sortTasks(tasks.filter { it.taskDate == today })
        val rows =
            if (showTasks) {
                dayTasks.take(10).map {
                    WidgetTaskRow(it.title.ifBlank { "—" }, it.isCompleted, it.id)
                }
            } else {
                emptyList()
            }
        WidgetTaskListStore.save(context, appWidgetId, rows)
        views.setTextViewText(
            R.id.widget_task_list_empty,
            LocalizedStrings.stringFor(language, "widget_task_list_empty")
        )
        bindTaskList(context, views, appWidgetId, showTasks)
        val weekDays = mondayFirstWeek(today)
        weekDays.forEachIndexed { i, date ->
            val filled = tasks.any { it.taskDate == date && it.isCompleted }
            val id = CHECKIN_IDS.getOrNull(i) ?: return@forEachIndexed
            views.setInt(
                id,
                "setBackgroundResource",
                if (filled) R.drawable.circle_filled else R.drawable.circle_empty
            )
        }
        val streak = completionStreak(tasks, today)
        val totalDays = completionDistinctDays(tasks, today, 90)
        views.setTextViewText(
            R.id.widget_checkin_stats,
            String.format(
                Locale.US,
                LocalizedStrings.stringFor(language, "widget_checkin_stats"),
                streak,
                totalDays
            )
        )
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_left_tasks,
            WidgetClickHandler.TARGET_TODAY,
            23
        )
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_right_stats,
            WidgetClickHandler.TARGET_MINE,
            24
        )
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_root,
            WidgetClickHandler.TARGET_HOME,
            25
        )
        bindCreateButton(context, views, appWidgetId, 71)
        return views
    }

    private fun build3x1_3c(
        context: Context,
        config: WidgetConfig,
        language: LocalizationManager.Language,
        appWidgetId: Int,
        tasks: List<Task>
    ): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_3x1_3c)
        val today = LocalDate.now()
        val labels = weekDayLabels(language)
        val weekDays = mondayFirstWeek(today)
        weekDays.forEachIndexed { i, date ->
            val wdId = WD_IDS.getOrNull(i) ?: return@forEachIndexed
            val wsId = WS_IDS.getOrNull(i) ?: return@forEachIndexed
            views.setTextViewText(wdId, labels.getOrElse(i) { "·" })
            val dayTasks = tasks.filter { it.taskDate == date }
            val sym = when {
                dayTasks.isEmpty() -> "·"
                dayTasks.all { it.isCompleted } -> "✔"
                else -> "●"
            }
            views.setTextViewText(wsId, sym)
        }
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_week_strip,
            WidgetClickHandler.TARGET_VIEW,
            26
        )
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_root,
            WidgetClickHandler.TARGET_HOME,
            27
        )
        bindCreateButton(context, views, appWidgetId, 72)
        return views
    }

    private fun build3x3_4a(
        context: Context,
        config: WidgetConfig,
        language: LocalizationManager.Language,
        appWidgetId: Int,
        tasks: List<Task>,
        quoteDisplay: String
    ): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_3x3_4a)
        val today = LocalDate.now()
        val ym = YearMonth.from(today)
        bind3x3Common(context, views, config, language, appWidgetId, tasks, quoteDisplay, ym, today, false)
        return views
    }

    private fun build3x3_4b(
        context: Context,
        config: WidgetConfig,
        language: LocalizationManager.Language,
        appWidgetId: Int,
        tasks: List<Task>,
        quoteDisplay: String
    ): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_3x3_4a)
        val today = LocalDate.now()
        val ym = YearMonth.from(today)
        bind3x3Common(context, views, config, language, appWidgetId, tasks, quoteDisplay, ym, today, true)
        return views
    }

    private fun bind3x3Common(
        context: Context,
        views: RemoteViews,
        config: WidgetConfig,
        language: LocalizationManager.Language,
        appWidgetId: Int,
        tasks: List<Task>,
        quoteDisplay: String,
        ym: YearMonth,
        today: LocalDate,
        monthScopeTasks: Boolean
    ) {
        val locale =
            if (language == LocalizationManager.Language.ZH) Locale.CHINA else Locale.ENGLISH
        val monthTitle = if (language == LocalizationManager.Language.ZH) {
            ym.format(DateTimeFormatter.ofPattern("yyyy年M月", locale))
        } else {
            ym.format(DateTimeFormatter.ofPattern("MMMM yyyy", locale))
        }
        views.setTextViewText(R.id.widget_month_title, monthTitle)
        views.setTextViewText(R.id.widget_week_header, LocalizedStrings.stringFor(language, "widget_main_week_row"))
        views.setTextViewText(R.id.widget_calendar_grid, buildCalendarHeatmap(ym, today, tasks))
        if (monthScopeTasks) {
            views.setTextViewText(R.id.widget_section_tasks, LocalizedStrings.stringFor(language, "widget_4b_section"))
        } else {
            views.setTextViewText(R.id.widget_section_tasks, LocalizedStrings.stringFor(language, "widget_main_today_tasks"))
        }
        val showTasks = config.displayConfig.showTasks
        val listTasks =
            if (!showTasks) {
                emptyList()
            } else if (monthScopeTasks) {
                TaskUtils.sortTasks(
                    tasks.filter { YearMonth.from(it.taskDate) == ym }
                ).take(15)
            } else {
                TaskUtils.sortTasks(tasks.filter { it.taskDate == today }).take(10)
            }
        val rows = listTasks.map { WidgetTaskRow(it.title.ifBlank { "—" }, it.isCompleted, it.id) }
        WidgetTaskListStore.save(context, appWidgetId, rows)
        views.setTextViewText(
            R.id.widget_task_list_empty,
            LocalizedStrings.stringFor(language, "widget_task_list_empty")
        )
        bindTaskList(context, views, appWidgetId, showTasks)
        if (config.displayConfig.showDailyQuote && quoteDisplay.isNotEmpty()) {
            views.setViewVisibility(R.id.widget_daily_quote, View.VISIBLE)
            views.setTextViewText(R.id.widget_daily_quote, quoteDisplay)
            WidgetClickHandler.setClick(
                context,
                views,
                appWidgetId,
                R.id.widget_daily_quote,
                WidgetClickHandler.TARGET_ESSAY,
                28
            )
        } else {
            views.setViewVisibility(R.id.widget_daily_quote, View.GONE)
        }
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_month_title,
            WidgetClickHandler.TARGET_VIEW,
            29
        )
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_month_prev,
            WidgetClickHandler.TARGET_VIEW,
            30
        )
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_month_next,
            WidgetClickHandler.TARGET_VIEW,
            31
        )
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_calendar_grid,
            WidgetClickHandler.TARGET_VIEW,
            32
        )
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_root,
            WidgetClickHandler.TARGET_HOME,
            34
        )
        bindCreateButton(context, views, appWidgetId, 74)
    }

    private fun bindCreateButton(context: Context, views: RemoteViews, appWidgetId: Int, slot: Int) {
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_create_button,
            WidgetClickHandler.TARGET_CREATE,
            slot
        )
    }

    private fun bindTaskList(context: Context, views: RemoteViews, appWidgetId: Int, showTasks: Boolean) {
        if (showTasks) {
            views.setViewVisibility(R.id.widget_task_list, View.VISIBLE)
            views.setViewVisibility(R.id.widget_task_list_empty, View.GONE)
            val svcIntent = Intent(context, WidgetRemoteViewsService::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                data = Uri.parse("widget://tasklist/$appWidgetId")
            }
            views.setRemoteAdapter(R.id.widget_task_list, svcIntent)
            views.setEmptyView(R.id.widget_task_list, R.id.widget_task_list_empty)
            views.setPendingIntentTemplate(
                R.id.widget_task_list,
                WidgetClickHandler.taskListClickTemplatePendingIntent(context, appWidgetId)
            )
        } else {
            views.setViewVisibility(R.id.widget_task_list, View.GONE)
            views.setViewVisibility(R.id.widget_task_list_empty, View.GONE)
        }
    }

    private fun formatStatsLine(
        language: LocalizationManager.Language,
        completed: Int,
        total: Int,
        showTasks: Boolean
    ): String {
        if (!showTasks) return "—"
        return String.format(
            Locale.US,
            LocalizedStrings.stringFor(language, "widget_2x2_stats"),
            completed,
            total
        )
    }

    suspend fun applyBackgroundToRemoteViews(
        context: Context,
        config: WidgetConfig,
        views: RemoteViews,
        size: WidgetSize
    ) {
        val bg = config.background
        val side = 220
        val backdropArgb =
            when (bg.type) {
                WidgetBackgroundType.WHITE -> {
                    views.setViewVisibility(R.id.widget_background_bitmap, View.GONE)
                    views.setInt(R.id.widget_root, "setBackgroundColor", Color.WHITE)
                    Color.WHITE
                }
                WidgetBackgroundType.SOLID_COLOR -> {
                    val c = parseColorSafe(bg.solidColor, Color.WHITE)
                    views.setViewVisibility(R.id.widget_background_bitmap, View.GONE)
                    views.setInt(R.id.widget_root, "setBackgroundColor", c)
                    c
                }
                WidgetBackgroundType.IMAGE, WidgetBackgroundType.GRADIENT -> {
                    val bmp = WidgetBackgroundManager.generateBackgroundBitmap(context.applicationContext, bg, side, side)
                    if (bmp != null) {
                        val approx = WidgetBackgroundManager.approximateArgbFromBitmap(bmp)
                        views.setInt(R.id.widget_root, "setBackgroundColor", Color.TRANSPARENT)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            views.setViewVisibility(R.id.widget_background_bitmap, View.VISIBLE)
                            runCatching {
                                views.setImageViewBitmap(R.id.widget_background_bitmap, bmp)
                            }.onFailure {
                                views.setViewVisibility(R.id.widget_background_bitmap, View.GONE)
                                views.setInt(R.id.widget_root, "setBackgroundColor", approx)
                            }
                        } else {
                            views.setViewVisibility(R.id.widget_background_bitmap, View.GONE)
                            views.setInt(R.id.widget_root, "setBackgroundColor", approx)
                        }
                        approx
                    } else {
                        views.setViewVisibility(R.id.widget_background_bitmap, View.GONE)
                        views.setInt(R.id.widget_root, "setBackgroundColor", Color.WHITE)
                        Color.WHITE
                    }
                }
            }
        WidgetContrast.maybeApply(config, size, views, backdropArgb)
    }

    private fun parseColorSafe(hex: String, fallback: Int): Int =
        runCatching { Color.parseColor(hex) }.getOrDefault(fallback)

    private fun mondayFirstWeek(today: LocalDate): List<LocalDate> {
        val monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        return (0..6).map { monday.plusDays(it.toLong()) }
    }

    private fun weekDayLabels(language: LocalizationManager.Language): List<String> {
        val s = LocalizedStrings.stringFor(language, "widget_main_week_row")
        return s.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
    }

    private fun completionStreak(tasks: List<Task>, today: LocalDate): Int {
        var d = today
        var n = 0
        while (tasks.any { it.taskDate == d && it.isCompleted }) {
            n++
            d = d.minusDays(1)
        }
        return n
    }

    private fun completionDistinctDays(tasks: List<Task>, today: LocalDate, lookback: Long): Int {
        val start = today.minusDays(lookback)
        return tasks
            .filter { it.isCompleted && !it.taskDate.isBefore(start) && !it.taskDate.isAfter(today) }
            .map { it.taskDate }
            .distinct()
            .size
    }

    private fun buildCalendarHeatmap(ym: YearMonth, today: LocalDate, tasks: List<Task>): String {
        val activeDays = tasks.map { it.taskDate }.toSet()
        val first = ym.atDay(1)
        val len = ym.lengthOfMonth()
        val offset = (first.dayOfWeek.value - DayOfWeek.MONDAY.value + 7) % 7
        val cells = MutableList(42) { " " }
        var idx = offset
        for (day in 1..len) {
            val date = ym.atDay(day)
            val ch = when {
                date == today -> "●"
                activeDays.contains(date) -> "■"
                else -> "·"
            }
            if (idx < 42) cells[idx] = ch
            idx++
        }
        return cells.chunked(7).joinToString("\n") { row ->
            row.joinToString(" ")
        }
    }
}
