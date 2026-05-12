package com.example.kairosapplication.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.StrikethroughSpan
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
import com.example.kairosapplication.widget.data.Widget3x1WeekStateStore
import com.example.kairosapplication.widget.data.Widget3x3CalendarStore
import com.example.kairosapplication.widget.data.WidgetTaskListStore
import com.example.kairosapplication.widget.data.WidgetTaskRow
import com.example.taskmodel.model.Task
import com.example.taskmodel.repository.TaskRepository
import com.example.taskmodel.util.TaskUtils
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters
import java.util.Locale
import kotlin.math.max
import kotlinx.coroutines.Dispatchers
import org.json.JSONArray
import org.json.JSONObject
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

    private val W3A_GRID_CELLS: List<Triple<Int, Int, Int>> = listOf(
        Triple(R.id.widget_today_cell_0, R.id.widget_today_cell_0_mark, R.id.widget_today_cell_0_title),
        Triple(R.id.widget_today_cell_1, R.id.widget_today_cell_1_mark, R.id.widget_today_cell_1_title),
        Triple(R.id.widget_today_cell_2, R.id.widget_today_cell_2_mark, R.id.widget_today_cell_2_title),
        Triple(R.id.widget_today_cell_3, R.id.widget_today_cell_3_mark, R.id.widget_today_cell_3_title),
        Triple(R.id.widget_today_cell_4, R.id.widget_today_cell_4_mark, R.id.widget_today_cell_4_title),
        Triple(R.id.widget_today_cell_5, R.id.widget_today_cell_5_mark, R.id.widget_today_cell_5_title),
        Triple(R.id.widget_today_cell_6, R.id.widget_today_cell_6_mark, R.id.widget_today_cell_6_title),
        Triple(R.id.widget_today_cell_7, R.id.widget_today_cell_7_mark, R.id.widget_today_cell_7_title),
        Triple(R.id.widget_today_cell_8, R.id.widget_today_cell_8_mark, R.id.widget_today_cell_8_title),
        Triple(R.id.widget_today_cell_9, R.id.widget_today_cell_9_mark, R.id.widget_today_cell_9_title)
    )

    private val W1B_TODO_ROWS: List<Triple<Int, Int, Int>> = listOf(
        Triple(R.id.widget_todo_row_0, R.id.widget_todo_0_mark, R.id.widget_todo_0_title),
        Triple(R.id.widget_todo_row_1, R.id.widget_todo_1_mark, R.id.widget_todo_1_title),
        Triple(R.id.widget_todo_row_2, R.id.widget_todo_2_mark, R.id.widget_todo_2_title)
    )

    private val W3X3_WEEK_HEADER_IDS = intArrayOf(
        R.id.widget_week_h0,
        R.id.widget_week_h1,
        R.id.widget_week_h2,
        R.id.widget_week_h3,
        R.id.widget_week_h4,
        R.id.widget_week_h5,
        R.id.widget_week_h6
    )

    private val W3X1_WEEK_DOM_IDS = intArrayOf(
        R.id.widget_wweek_dom0,
        R.id.widget_wweek_dom1,
        R.id.widget_wweek_dom2,
        R.id.widget_wweek_dom3,
        R.id.widget_wweek_dom4,
        R.id.widget_wweek_dom5,
        R.id.widget_wweek_dom6
    )

    private fun bind1x1TodoRows(
        context: Context,
        views: RemoteViews,
        appWidgetId: Int,
        lines: List<Widget1x1TodoLine>,
        showTasks: Boolean
    ) {
        W1B_TODO_ROWS.forEachIndexed { i, (rowId, markId, titleId) ->
            val line = lines.getOrNull(i)
            if (line == null || !showTasks) {
                views.setViewVisibility(rowId, View.GONE)
                return@forEachIndexed
            }
            views.setViewVisibility(rowId, View.VISIBLE)
            val done = line.done
            views.setTextViewText(markId, if (done) "✓" else "○")
            views.setTextColor(markId, WidgetTaskStyle.markColorArgb(done))
            val title = line.title.trim().ifBlank { "—" }
            if (done) {
                val ssb = SpannableStringBuilder(title)
                ssb.setSpan(
                    StrikethroughSpan(),
                    0,
                    ssb.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                ssb.setSpan(
                    ForegroundColorSpan(WidgetTaskStyle.titleColorArgb(true)),
                    0,
                    ssb.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                views.setTextViewText(titleId, ssb)
            } else {
                views.setTextViewText(titleId, title)
                views.setTextColor(titleId, WidgetTaskStyle.titleColorArgb(false))
            }
            views.setInt(
                rowId,
                "setBackgroundColor",
                WidgetTaskStyle.cellBackgroundArgb(line.urgency, done)
            )
            if (line.taskId >= 0) {
                views.setOnClickPendingIntent(
                    rowId,
                    WidgetClickHandler.toggleTaskPendingIntent(context, appWidgetId, line.taskId)
                )
            }
        }
    }

    fun build2x2CalendarExtras(
        today: LocalDate,
        language: LocalizationManager.Language
    ): Pair<String, String> {
        val locale =
            if (language == LocalizationManager.Language.ZH) Locale.CHINA else Locale.ENGLISH
        val ym = YearMonth.from(today)
        val title = if (language == LocalizationManager.Language.ZH) {
            DateTimeFormatter.ofPattern("yyyy年M月", Locale.CHINA).format(today)
        } else {
            DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH).format(today)
        }
        return title to buildMiniMonthNumberGrid(ym, today)
    }

    private fun buildMiniMonthNumberGrid(ym: YearMonth, today: LocalDate): String {
        val first = ym.atDay(1)
        val len = ym.lengthOfMonth()
        val offset = (first.dayOfWeek.value - DayOfWeek.MONDAY.value + 7) % 7
        val cells = MutableList(42) { "  " }
        var idx = offset
        for (day in 1..len) {
            val d = ym.atDay(day)
            val cell = when {
                d == today -> "*$day".take(3).padEnd(3)
                else -> day.toString().padStart(2, ' ')
            }.take(3)
            if (idx < 42) cells[idx] = cell
            idx++
        }
        return cells.chunked(7).joinToString("\n") { row -> row.joinToString(" ") }
    }

    fun buildMiniMonthGridSpanned(ym: YearMonth, today: LocalDate): CharSequence {
        val first = ym.atDay(1)
        val offset = (first.dayOfWeek.value - DayOfWeek.MONDAY.value + 7) % 7
        val startGrid = ym.atDay(1).minusDays(offset.toLong())
        val primary = Color.parseColor("#1A1A1A")
        val muted = Color.parseColor("#BDBDBD")
        val ssb = SpannableStringBuilder()
        for (week in 0 until 6) {
            for (col in 0 until 7) {
                val date = startGrid.plusDays((week * 7 + col).toLong())
                val inMonth = YearMonth.from(date) == ym
                val segStart = ssb.length
                val num = date.dayOfMonth.toString().padStart(2, ' ')
                ssb.append(num)
                val segEnd = ssb.length
                if (date == today && inMonth) {
                    ssb.setSpan(
                        BackgroundColorSpan(WidgetTaskStyle.CAL_TODAY_FILL_ARGB),
                        segStart,
                        segEnd,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    ssb.setSpan(
                        ForegroundColorSpan(Color.WHITE),
                        segStart,
                        segEnd,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    ssb.setSpan(
                        StyleSpan(android.graphics.Typeface.BOLD),
                        segStart,
                        segEnd,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } else {
                    val color = if (inMonth) primary else muted
                    ssb.setSpan(
                        ForegroundColorSpan(color),
                        segStart,
                        segEnd,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                if (col < 6) ssb.append(' ')
            }
            if (week < 5) ssb.append('\n')
        }
        return ssb
    }

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
                        WidgetLayoutKind._3D -> {
                            val q = quoteCombined(context, config, language, dataRepository)
                            build3x1_3d(context, config, language, appWidgetId, tasks, q)
                        }
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
                WidgetLayoutKind._3D -> {
                    val quoteBody = loadQuoteBody(context, language)
                    val q = formatQuoteDisplay(context, config, language, quoteBody)
                    build3x1_3d(context, config, language, appWidgetId, tasks, q)
                }
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
            val q = formatQuoteDisplay(context, config, language, quoteBody)
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
        val quotePrefix = LocalizedStrings.stringFor(state.language, "widget_quote_prefix", context)
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
        views.setTextViewText(R.id.widget_1b_day, state.dayOfMonthText)
        views.setTextViewText(R.id.widget_1b_weekday, state.weekdayText)
        val showTasks = config.displayConfig.showTasks
        views.setTextViewText(
            R.id.widget_1b_task_count,
            if (showTasks) "${state.completed}/${state.total}" else "0/0"
        )
        bind1x1TodoRows(context, views, appWidgetId, state.lines, showTasks)
        val quotePrefix = LocalizedStrings.stringFor(state.language, "widget_quote_prefix", context)
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
        val today = LocalDate.now()
        return build2x2RemoteViews(
            context,
            config,
            appWidgetId,
            display.dateLine,
            display.statsLine,
            display.quoteLine,
            display.tasks,
            display.showTasks,
            display.showQuote,
            display.language,
            display.monthTitle,
            today
        )
    }

    private suspend fun quoteCombined(
        context: Context,
        config: WidgetConfig,
        language: LocalizationManager.Language,
        dataRepository: WidgetDataRepository
    ): String {
        val defaultQuote = LocalizedStrings.stringFor(language, "widget_quote_default", context)
        val body = dataRepository.getDailyQuote(defaultQuote)
        return formatQuoteDisplay(context, config, language, body)
    }

    private fun formatQuoteDisplay(
        context: Context,
        config: WidgetConfig,
        language: LocalizationManager.Language,
        quoteBody: String,
    ): String {
        if (!config.displayConfig.showDailyQuote) return ""
        val quotePrefix = LocalizedStrings.stringFor(language, "widget_quote_prefix", context)
        val raw = if (quotePrefix.isNotEmpty()) quotePrefix + quoteBody else quoteBody
        return when {
            raw.length <= 120 -> raw
            else -> raw.take(117) + "…"
        }
    }

    private suspend fun loadQuoteBody(context: Context, language: LocalizationManager.Language): String {
        val taskRepository = TaskRepository(context.applicationContext)
        val defaultText = LocalizedStrings.stringFor(language, "widget_quote_default", context)
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
        val quotePrefix = LocalizedStrings.stringFor(language, "widget_quote_prefix", context)
        val defaultQuote = LocalizedStrings.stringFor(language, "widget_quote_default", context)
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
        val locale =
            if (language == LocalizationManager.Language.ZH) Locale.CHINA else Locale.ENGLISH
        val weekdayText = today.dayOfWeek.getDisplayName(
            java.time.format.TextStyle.FULL,
            locale
        )
        views.setTextViewText(R.id.widget_1b_day, today.dayOfMonth.toString())
        views.setTextViewText(R.id.widget_1b_weekday, weekdayText)
        val showTasks = config.displayConfig.showTasks
        val taskData = dataRepository.getTodayTaskData()
        val completed = if (showTasks) taskData.completedCount else 0
        val total = if (showTasks) taskData.totalCount else 0
        views.setTextViewText(
            R.id.widget_1b_task_count,
            if (showTasks) "$completed/$total" else "0/0"
        )
        val lines = if (showTasks) {
            TaskUtils.sortTasks(
                dataRepository.getAllTasks().filter { it.taskDate == today }
            ).take(3).map {
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
        bind1x1TodoRows(context, views, appWidgetId, lines, showTasks)
        val quotePrefix = LocalizedStrings.stringFor(language, "widget_quote_prefix", context)
        val defaultQuote = LocalizedStrings.stringFor(language, "widget_quote_default", context)
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
        val dateLine = today.dayOfWeek.getDisplayName(
            java.time.format.TextStyle.FULL,
            locale
        )
        val showTasks = config.displayConfig.showTasks
        val taskData = dataRepository.getTodayTaskData()
        val completed = if (showTasks) taskData.completedCount else 0
        val total = if (showTasks) taskData.totalCount else 0
        val statsLine = formatStatsLine(context, language, completed, total, showTasks)
        val quotePrefix = LocalizedStrings.stringFor(language, "widget_quote_prefix", context)
        val defaultQuote = LocalizedStrings.stringFor(language, "widget_quote_default", context)
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
                    WidgetTaskRow(
                        it.title.ifBlank { "—" },
                        it.isCompleted,
                        it.id,
                        it.urgency
                    )
                }.take(10)
            } else {
                emptyList()
            }
        val (monthTitle, _) = build2x2CalendarExtras(today, language)
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
            language,
            monthTitle,
            today
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
        language: LocalizationManager.Language,
        monthTitle: String,
        calendarDay: LocalDate
    ): RemoteViews {
        WidgetTaskListStore.save(context, appWidgetId, rows)
        val views = RemoteViews(context.packageName, R.layout.widget_2x2_2a)
        views.setTextViewText(R.id.widget_date, dateLine)
        views.setTextViewText(R.id.widget_task_count, statsLine)
        views.setTextViewText(R.id.widget_2x2_month_title, monthTitle)
        val ym = YearMonth.from(calendarDay)
        views.setTextViewText(R.id.widget_2x2_month_grid, buildMiniMonthGridSpanned(ym, calendarDay))
        views.setTextViewText(
            R.id.widget_task_list_empty,
            LocalizedStrings.stringFor(language, "widget_task_list_empty", context)
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
        val yesterday = today.minusDays(1)
        val tomorrow = today.plusDays(1)
        val locale =
            if (language == LocalizationManager.Language.ZH) Locale.CHINA else Locale.ENGLISH
        val showTasks = config.displayConfig.showTasks

        views.setTextViewText(R.id.widget_yesterday_day, yesterday.dayOfMonth.toString())
        views.setTextViewText(R.id.widget_tomorrow_day, tomorrow.dayOfMonth.toString())

        val yesterdayTasks =
            if (showTasks) TaskUtils.sortTasks(tasks.filter { it.taskDate == yesterday })
            else emptyList()
        val tomorrowTasks =
            if (showTasks) TaskUtils.sortTasks(tasks.filter { it.taskDate == tomorrow })
            else emptyList()
        views.setTextViewText(
            R.id.widget_yesterday_tasks,
            widgetSideTaskLinesSpanned(yesterdayTasks, strikeCompleted = true)
        )
        views.setTextViewText(
            R.id.widget_tomorrow_tasks,
            widgetSideTaskLinesSpanned(tomorrowTasks, strikeCompleted = false)
        )

        val mmDd = today.format(DateTimeFormatter.ofPattern("MM-dd", locale))
        val weekdayShort = today.dayOfWeek.getDisplayName(
            java.time.format.TextStyle.SHORT,
            locale
        )
        val todayLabel = LocalizedStrings.stringFor(language, "view_tab_today", context)
        views.setTextViewText(
            R.id.widget_today_date_line,
            "${today.dayOfMonth} · $todayLabel · $weekdayShort · $mmDd"
        )

        val dayTasks =
            if (showTasks) TaskUtils.sortTasks(tasks.filter { it.taskDate == today })
            else emptyList()
        W3A_GRID_CELLS.forEachIndexed { index, (cellId, markId, titleId) ->
            val task = dayTasks.getOrNull(index)
            if (task == null || !showTasks) {
                views.setViewVisibility(cellId, View.GONE)
            } else {
                views.setViewVisibility(cellId, View.VISIBLE)
                views.setTextViewText(markId, if (task.isCompleted) "✓" else "○")
                views.setTextColor(markId, WidgetTaskStyle.markColorArgb(task.isCompleted))
                val title = task.title.trim().ifBlank { "—" }
                if (task.isCompleted) {
                    val ssb = SpannableStringBuilder(title)
                    ssb.setSpan(
                        StrikethroughSpan(),
                        0,
                        ssb.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    ssb.setSpan(
                        ForegroundColorSpan(WidgetTaskStyle.titleColorArgb(true)),
                        0,
                        ssb.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    views.setTextViewText(titleId, ssb)
                } else {
                    views.setTextViewText(titleId, title)
                    views.setTextColor(titleId, WidgetTaskStyle.titleColorArgb(false))
                }
                views.setInt(
                    cellId,
                    "setBackgroundColor",
                    WidgetTaskStyle.cellBackgroundArgb(task.urgency, task.isCompleted)
                )
                views.setOnClickPendingIntent(
                    cellId,
                    WidgetClickHandler.toggleTaskPendingIntent(context, appWidgetId, task.id)
                )
            }
        }
        val overflow = dayTasks.size - W3A_GRID_CELLS.size
        if (overflow > 0) {
            views.setViewVisibility(R.id.widget_today_overflow_hint, View.VISIBLE)
            views.setTextViewText(
                R.id.widget_today_overflow_hint,
                LocalizedStrings.stringFor(language, "widget_3x1_more_tasks", context, overflow),
            )
        } else {
            views.setViewVisibility(R.id.widget_today_overflow_hint, View.GONE)
        }

        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_today_date_line,
            WidgetClickHandler.TARGET_HOME,
            74
        )
        bindCreateButton(context, views, appWidgetId, 73)
        return views
    }

    private fun widgetSideTaskLinesSpanned(
        dayTasks: List<Task>,
        strikeCompleted: Boolean
    ): CharSequence {
        if (dayTasks.isEmpty()) return "—"
        val grey = Color.parseColor("#888888")
        val ssb = SpannableStringBuilder()
        dayTasks.take(4).forEachIndexed { i, t ->
            if (i > 0) ssb.append('\n')
            val line = t.title.trim().ifBlank { "—" }.take(22)
            val start = ssb.length
            ssb.append(line)
            val end = ssb.length
            ssb.setSpan(ForegroundColorSpan(grey), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            if (strikeCompleted && t.isCompleted) {
                ssb.setSpan(StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
        return ssb
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
                    WidgetTaskRow(it.title.ifBlank { "—" }, it.isCompleted, it.id, it.urgency)
                }
            } else {
                emptyList()
            }
        WidgetTaskListStore.save(context, appWidgetId, rows)
        views.setTextViewText(
            R.id.widget_task_list_empty,
            LocalizedStrings.stringFor(language, "widget_task_list_empty", context)
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
                LocalizedStrings.stringFor(language, "widget_checkin_stats", context),
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
        val labels = weekDayLabels(context, language)
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

    private suspend fun build3x1_3d(
        context: Context,
        config: WidgetConfig,
        language: LocalizationManager.Language,
        appWidgetId: Int,
        tasks: List<Task>,
        quoteDisplay: String,
    ): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_3x1_3d)
        val (weekOff, selEpochRaw) = Widget3x1WeekStateStore.load(context, appWidgetId)
        val coercedSel = Widget3x1WeekStateStore.coerceSelectedToWeek(weekOff, selEpochRaw)
        if (coercedSel != selEpochRaw) {
            Widget3x1WeekStateStore.save(context, appWidgetId, weekOff, coercedSel)
        }
        val weekStart = Widget3x1WeekStateStore.mondayForOffset(weekOff)
        val selected = LocalDate.ofEpochDay(coercedSel)
        val locale =
            if (language == LocalizationManager.Language.ZH) Locale.CHINA else Locale.US
        val mdFmt = DateTimeFormatter.ofPattern("M/d", locale)
        views.setTextViewText(
            R.id.widget_wweek_title,
            "${weekStart.format(mdFmt)}–${weekStart.plusDays(6).format(mdFmt)}"
        )
        val labels = weekDayLabels(context, language)
        W3X3_WEEK_HEADER_IDS.forEachIndexed { i, hid ->
            views.setTextViewText(hid, labels.getOrElse(i) { "" })
        }
        val primaryText = Color.parseColor("#1A1A1A")
        val todayColor = Color.parseColor("#4CAF50")
        val selectedColor = Color.parseColor("#1976D2")
        W3X1_WEEK_DOM_IDS.forEachIndexed { i, domId ->
            val d = weekStart.plusDays(i.toLong())
            views.setTextViewText(domId, d.format(mdFmt))
            val c = when {
                d == selected -> selectedColor
                d == LocalDate.now() -> todayColor
                else -> primaryText
            }
            views.setTextColor(domId, c)
            views.setOnClickPendingIntent(
                domId,
                WidgetClickHandler.widget3x1WeekNavPendingIntent(
                    context,
                    appWidgetId,
                    WidgetActions.ACTION_3X1_WEEK_SELECT_DAY,
                    90 + i,
                    i
                )
            )
        }
        views.setOnClickPendingIntent(
            R.id.widget_wweek_prev,
            WidgetClickHandler.widget3x1WeekNavPendingIntent(
                context,
                appWidgetId,
                WidgetActions.ACTION_3X1_WEEK_PREV,
                81
            )
        )
        views.setOnClickPendingIntent(
            R.id.widget_wweek_next,
            WidgetClickHandler.widget3x1WeekNavPendingIntent(
                context,
                appWidgetId,
                WidgetActions.ACTION_3X1_WEEK_NEXT,
                82
            )
        )
        WidgetClickHandler.setClick(
            context,
            views,
            appWidgetId,
            R.id.widget_wweek_title,
            WidgetClickHandler.TARGET_VIEW,
            83
        )
        val showTasks = config.displayConfig.showTasks
        if (showTasks) {
            val dayTasks = TaskUtils.sortTasks(tasks.filter { it.taskDate == selected })
            WidgetTaskListStore.save(
                context,
                appWidgetId,
                dayTasks.map {
                    WidgetTaskRow(
                        it.title.ifBlank { "—" },
                        it.isCompleted,
                        it.id,
                        it.urgency
                    )
                }
            )
            bindWeekTaskList(context, views, appWidgetId, true)
        } else {
            WidgetTaskListStore.save(context, appWidgetId, emptyList())
            bindWeekTaskList(context, views, appWidgetId, false)
        }
        if (config.displayConfig.showDailyQuote && quoteDisplay.isNotEmpty()) {
            views.setViewVisibility(R.id.widget_daily_quote, View.VISIBLE)
            views.setTextViewText(R.id.widget_daily_quote, quoteDisplay)
            WidgetClickHandler.setClick(
                context,
                views,
                appWidgetId,
                R.id.widget_daily_quote,
                WidgetClickHandler.TARGET_ESSAY,
                86
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
            87
        )
        bindCreateButton(context, views, appWidgetId, 88)
        return views
    }

    private fun bindWeekTaskList(context: Context, views: RemoteViews, appWidgetId: Int, showTasks: Boolean) {
        if (showTasks) {
            views.setViewVisibility(R.id.widget_3x1_week_task_list, View.VISIBLE)
            views.setViewVisibility(R.id.widget_3x1_week_task_list_empty, View.GONE)
            val svcIntent = Intent(context, WidgetRemoteViewsService::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                data = Uri.parse("widget://3x1weektasks/$appWidgetId")
            }
            views.setRemoteAdapter(R.id.widget_3x1_week_task_list, svcIntent)
            views.setEmptyView(R.id.widget_3x1_week_task_list, R.id.widget_3x1_week_task_list_empty)
            views.setPendingIntentTemplate(
                R.id.widget_3x1_week_task_list,
                WidgetClickHandler.taskListToggleTemplatePendingIntent(context, appWidgetId)
            )
        } else {
            views.setViewVisibility(R.id.widget_3x1_week_task_list, View.GONE)
            views.setViewVisibility(R.id.widget_3x1_week_task_list_empty, View.GONE)
        }
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

    /** Weeks needed to cover [startGrid .. ym.end], full rows only — no extra trailing next-month row. */
    private fun calendarGridWeekCount(ym: YearMonth): Int {
        val first = ym.atDay(1)
        val offset = (first.dayOfWeek.value - DayOfWeek.MONDAY.value + 7) % 7
        val startGrid = ym.atDay(1).minusDays(offset.toLong())
        val spanDays = ChronoUnit.DAYS.between(startGrid, ym.atEndOfMonth()).toInt() + 1
        return max(1, (spanDays + 6) / 7)
    }

    private fun encode3x3CalendarWeeksJson(ym: YearMonth, today: LocalDate, tasks: List<Task>): String {
        val first = ym.atDay(1)
        val offset = (first.dayOfWeek.value - DayOfWeek.MONDAY.value + 7) % 7
        val startGrid = ym.atDay(1).minusDays(offset.toLong())
        val weeks = JSONArray()
        val numWeeks = calendarGridWeekCount(ym)
        for (w in 0 until numWeeks) {
            val days = JSONArray()
            for (c in 0 until 7) {
                val date = startGrid.plusDays((w * 7 + c).toLong())
                val inMonth = YearMonth.from(date) == ym
                val dayTasks = TaskUtils.sortTasks(tasks.filter { it.taskDate == date }).take(3)
                val lines = JSONArray()
                dayTasks.forEach { t ->
                    lines.put(
                        JSONObject().apply {
                            put("i", t.id)
                            put("t", t.title)
                            put("d", t.isCompleted)
                            put("u", t.urgency)
                        }
                    )
                }
                days.put(
                    JSONObject().apply {
                        put("dom", date.dayOfMonth)
                        put("in", inMonth)
                        put("today", date == today)
                        put("lines", lines)
                    }
                )
            }
            weeks.put(JSONObject().put("days", days))
        }
        return weeks.toString()
    }

    private fun bind3x3WeekList(context: Context, views: RemoteViews, appWidgetId: Int) {
        val svcIntent = Intent(context, Widget3x3CalendarRemoteViewsService::class.java).apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            data = Uri.parse("widget://3x3week/$appWidgetId")
        }
        views.setRemoteAdapter(R.id.widget_3x3_week_list, svcIntent)
        views.setPendingIntentTemplate(
            R.id.widget_3x3_week_list,
            WidgetClickHandler.taskListToggleTemplatePendingIntent(context, appWidgetId)
        )
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
        @Suppress("UNUSED_PARAMETER") monthScopeTasks: Boolean
    ) {
        val locale =
            if (language == LocalizationManager.Language.ZH) Locale.CHINA else Locale.ENGLISH
        val monthTitle = if (language == LocalizationManager.Language.ZH) {
            ym.format(DateTimeFormatter.ofPattern("yyyy年M月", locale))
        } else {
            ym.format(DateTimeFormatter.ofPattern("MMMM yyyy", locale))
        }
        views.setTextViewText(R.id.widget_month_title, monthTitle)
        val weekLabs = weekDayLabels(context, language)
        W3X3_WEEK_HEADER_IDS.forEachIndexed { i, hid ->
            views.setTextViewText(hid, weekLabs.getOrElse(i) { "" })
        }
        val showTasks = config.displayConfig.showTasks
        val calTasks = if (showTasks) tasks else emptyList()
        val json = encode3x3CalendarWeeksJson(ym, today, calTasks)
        Widget3x3CalendarStore.save(context, appWidgetId, json)
        bind3x3WeekList(context, views, appWidgetId)
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
                WidgetClickHandler.taskListToggleTemplatePendingIntent(context, appWidgetId)
            )
        } else {
            views.setViewVisibility(R.id.widget_task_list, View.GONE)
            views.setViewVisibility(R.id.widget_task_list_empty, View.GONE)
        }
    }

    private fun formatStatsLine(
        context: Context,
        language: LocalizationManager.Language,
        completed: Int,
        total: Int,
        showTasks: Boolean,
    ): String {
        if (!showTasks) return "—"
        return String.format(
            Locale.US,
            LocalizedStrings.stringFor(language, "widget_2x2_stats", context),
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

    private fun weekDayLabels(context: Context, language: LocalizationManager.Language): List<String> {
        val s = LocalizedStrings.stringFor(language, "widget_main_week_row", context)
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
}
