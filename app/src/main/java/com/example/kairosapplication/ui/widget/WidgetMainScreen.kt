package com.example.kairosapplication.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.ui.view.ChromeSegmentTabRow
import com.example.kairosapplication.ui.view.LocalViewChrome
import com.example.kairosapplication.ui.view.rememberViewChromeColors
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import com.example.kairosapplication.widget.WidgetTaskTitleClip
import com.example.kairosapplication.widget.WidgetViewFactory
import com.example.kairosapplication.widget.data.WidgetSize
import com.example.taskmodel.model.Task
import com.example.taskmodel.util.TaskUtils
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

private val WidgetPurpleTrack = Color(0xFFE8DEF8)
private val WidgetPurpleProgress = Color(0xFF7B61FF)
private val WidgetPurpleAccent = Color(0xFF7B61FF)
private val WidgetCalTodayFill = Color(0xFF9F8CF7)
@Composable
fun WidgetMainScreen(
    taskViewModel: TaskViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by taskViewModel.uiState.collectAsState()
    val context = LocalContext.current
    val today = LocalDate.now()
    val lang = LocalCurrentLanguage.current.value
    val isZh = lang == LocalizationManager.Language.ZH
    val todayTasks = remember(uiState.tasks, today) {
        uiState.tasks.filter { it.taskDate == today }
    }
    val sortedToday = remember(todayTasks) { TaskUtils.sortTasks(todayTasks) }
    val completed = todayTasks.count { it.isCompleted }
    val total = todayTasks.size
    val quote = taskViewModel.dailyQuoteDisplayText(LocalizedStrings.get("widget_quote_default"))
    val dayName = remember(today, isZh) {
        if (isZh) {
            DateTimeFormatter.ofPattern("EEEE", Locale.CHINA).format(today)
        } else {
            today.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        }
    }
    val dayNum = today.dayOfMonth.toString()
    val previewTasks = remember(sortedToday) { sortedToday.take(5) }
    val previewTasks1b = remember(sortedToday) { sortedToday.take(3) }
    val weekdayFullLabel = remember(today, lang) {
        today.dayOfWeek.getDisplayName(
            TextStyle.FULL,
            if (lang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.ENGLISH
        )
    }
    val monthTitleLabel = remember(today, lang) {
        WidgetViewFactory.buildWidgetMonthTitle(today, lang)
    }
    val previewText = LocalGlassTextColors.current
    val monthGridAnnotated = remember(today, previewText) {
        monthGridPreviewAnnotated(
            YearMonth.from(today),
            today,
            previewText.primary,
            previewText.muted,
        )
    }
    var selectedSizeKey by rememberSaveable { mutableStateOf(WidgetSize._1X1.name) }
    val selectedSize = WidgetSize.valueOf(selectedSizeKey)
    val viewChrome = rememberViewChromeColors()
    val widgetTabLabels = WidgetSize.entries.map { size ->
        when (size) {
            WidgetSize._1X1 -> LocalizedStrings.get("widget_main_tab_small")
            WidgetSize._3X1 -> LocalizedStrings.get("widget_main_tab_medium")
            WidgetSize._3X3 -> LocalizedStrings.get("widget_main_tab_large")
        }
    }
    val selectedTabIndex = WidgetSize.entries.indexOf(selectedSize).coerceAtLeast(0)

    CompositionLocalProvider(LocalViewChrome provides viewChrome) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .statusBarsPadding()
    ) {
        ChromeSegmentTabRow(
            labels = widgetTabLabels,
            selectedIndex = selectedTabIndex,
            onSelect = { idx -> selectedSizeKey = WidgetSize.entries[idx].name },
        )
        HorizontalDivider(thickness = 0.5.dp, color = viewChrome.divider)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            when (selectedSize) {
                WidgetSize._1X1 -> SmallPreviewsRow(
                    dayName = dayName,
                    dayNum = dayNum,
                    completed = completed,
                    total = total,
                    quote = quote,
                    tasks1b = previewTasks1b
                )
                WidgetSize._3X1 -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        LargeWidgetTodoPreviewCard(
                            weekdayLabel = weekdayFullLabel,
                            completed = completed,
                            total = total,
                            tasks = previewTasks,
                            quote = quote,
                            monthTitle = monthTitleLabel,
                            monthGrid = monthGridAnnotated,
                            isZh = isZh,
                        )
                        Widget3x1WeekQuotePreview(
                            today = today,
                            lang = lang,
                            tasks = uiState.tasks,
                            quote = quote,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                WidgetSize._3X3 -> SuperLargePreviewCard(
                    today = today,
                    allTasks = uiState.tasks,
                    quote = quote,
                    lang = lang
                )
            }
        }
    }
    }
}

@Composable
private fun SmallPreviewsRow(
    dayName: String,
    dayNum: String,
    completed: Int,
    total: Int,
    quote: String,
    tasks1b: List<Task>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Preview1ACard(
                dayName = dayName,
                dayNum = dayNum,
                completed = completed,
                total = total,
                quote = quote,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            Preview1BCard(
                dayNum = dayNum,
                weekdayLabel = dayName,
                completed = completed,
                total = total,
                quote = quote,
                tasks = tasks1b,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun Preview1ACard(
    dayName: String,
    dayNum: String,
    completed: Int,
    total: Int,
    quote: String,
    modifier: Modifier = Modifier
) {
    WidgetPreviewCardShell(modifier = modifier.aspectRatio(1f)) {
        val frac = if (total <= 0) 0f else (completed.toFloat() / total.toFloat()).coerceIn(0f, 1f)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
        ) {
            Box(
                modifier = Modifier.align(Alignment.Center),
                contentAlignment = Alignment.Center,
            ) {
                Canvas(modifier = Modifier.size(72.dp)) {
                    val stroke = 5.dp.toPx()
                    val diameter = size.minDimension
                    drawArc(
                        color = WidgetPurpleTrack,
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        topLeft = androidx.compose.ui.geometry.Offset.Zero,
                        size = Size(diameter, diameter),
                        style = Stroke(width = stroke, cap = StrokeCap.Round),
                    )
                    drawArc(
                        color = WidgetPurpleProgress,
                        startAngle = -90f,
                        sweepAngle = 360f * frac,
                        useCenter = false,
                        topLeft = androidx.compose.ui.geometry.Offset.Zero,
                        size = Size(diameter, diameter),
                        style = Stroke(width = stroke, cap = StrokeCap.Round),
                    )
                }
                Text(
                    text = if (total > 0) "$completed/$total" else "0/0",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = LocalGlassTextColors.current.primary,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    dayName,
                    fontSize = 11.sp,
                    color = LocalGlassTextColors.current.muted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    dayNum,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LocalGlassTextColors.current.primary,
                )
            }
            Text(
                text = quote,
                fontSize = 10.sp,
                color = LocalGlassTextColors.current.secondary,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            )
        }
    }
}

@Composable
private fun Preview1BCard(
    dayNum: String,
    weekdayLabel: String,
    completed: Int,
    total: Int,
    quote: String,
    tasks: List<Task>,
    modifier: Modifier = Modifier
) {
    WidgetPreviewCardShell(modifier = modifier.aspectRatio(1f)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = dayNum,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = LocalGlassTextColors.current.primary,
                maxLines = 1,
            )
            Text(
                text = weekdayLabel,
                fontSize = 10.sp,
                color = LocalGlassTextColors.current.muted,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = if (total > 0) "$completed/$total" else "0/0",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = LocalGlassTextColors.current.primary,
                maxLines = 1,
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = WidgetPurpleAccent,
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(Modifier.height(6.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val rows = if (tasks.isEmpty()) {
                List(3) { null as Task? }
            } else {
                (tasks + List(3) { null }).take(3)
            }
            rows.forEach { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 22.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (task != null) {
                        val markChar = if (task.isCompleted) "✓" else "○"
                        Text(
                            text = markChar,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (task.isCompleted) WidgetPurpleProgress else LocalGlassTextColors.current.muted,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                    } else {
                        Spacer(Modifier.width(19.dp))
                    }
                    val done = task?.isCompleted == true
                    Text(
                        text = task?.title?.takeIf { it.isNotBlank() } ?: "—",
                        fontSize = 11.sp,
                        color = if (done) LocalGlassTextColors.current.muted else LocalGlassTextColors.current.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textDecoration = if (done) TextDecoration.LineThrough else null,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        Text(
            text = quote,
            fontSize = 10.sp,
            color = LocalGlassTextColors.current.secondary,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )
        }
    }
}

@Composable
private fun LargeWidgetTodoPreviewCard(
    weekdayLabel: String,
    completed: Int,
    total: Int,
    tasks: List<Task>,
    quote: String,
    monthTitle: String,
    monthGrid: AnnotatedString,
    isZh: Boolean
) {
    WidgetPreviewCardShell(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 10.dp),
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.42f)
                        .fillMaxHeight(),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = weekdayLabel,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = LocalGlassTextColors.current.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f),
                        )
                        Text(
                            text = if (isZh) "已完成${completed}/总${total}" else "${completed} of ${total} done",
                            fontSize = 10.sp,
                            color = LocalGlassTextColors.current.muted,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                    ) {
                        tasks.take(5).forEach { task ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                            ) {
                                Text(
                                    text = if (task.isCompleted) "✓" else "○",
                                    fontSize = 12.sp,
                                    color = if (task.isCompleted) {
                                        WidgetPurpleProgress
                                    } else {
                                        LocalGlassTextColors.current.muted
                                    },
                                )
                                Text(
                                    text = task.title,
                                    fontSize = 12.sp,
                                    color = if (task.isCompleted) {
                                        LocalGlassTextColors.current.muted
                                    } else {
                                        LocalGlassTextColors.current.primary
                                    },
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null,
                                    modifier = Modifier.weight(1f),
                                )
                            }
                        }
                        if (tasks.isEmpty()) {
                            Text(
                                LocalizedStrings.get("widget_main_no_tasks"),
                                fontSize = 12.sp,
                                color = LocalGlassTextColors.current.muted,
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(0.58f)
                        .fillMaxHeight()
                        .padding(start = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = monthTitle,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = LocalGlassTextColors.current.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = monthGrid,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .verticalScroll(rememberScrollState()),
                    )
                }
            }
            WidgetDailyQuoteBar(
                quote = quote,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
            )
        }
    }
}

@Composable
private fun WidgetDailyQuoteBar(
    quote: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White.copy(alpha = 0.12f))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = quote,
            fontSize = 10.sp,
            color = LocalGlassTextColors.current.secondary,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 12.sp,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

private fun previewCalendarWeekCount(ym: YearMonth): Int {
    val first = ym.atDay(1)
    val offset = (first.dayOfWeek.value - DayOfWeek.MONDAY.value + 7) % 7
    val startGrid = ym.atDay(1).minusDays(offset.toLong())
    val spanDays = ChronoUnit.DAYS.between(startGrid, ym.atEndOfMonth()).toInt() + 1
    return kotlin.math.max(1, (spanDays + 6) / 7)
}

private fun monthGridPreviewAnnotated(
    ym: YearMonth,
    today: LocalDate,
    inMonthColor: Color,
    outOfMonthColor: Color,
): AnnotatedString {
    val first = ym.atDay(1)
    val offset = (first.dayOfWeek.value - DayOfWeek.MONDAY.value + 7) % 7
    val startGrid = ym.atDay(1).minusDays(offset.toLong())
    val numWeeks = previewCalendarWeekCount(ym)
    return buildAnnotatedString {
        for (week in 0 until numWeeks) {
            for (col in 0 until 7) {
                val date = startGrid.plusDays((week * 7 + col).toLong())
                val inMonth = YearMonth.from(date) == ym
                val start = length
                val num = date.dayOfMonth.toString().padStart(2, ' ')
                append(num)
                if (date == today && inMonth) {
                    addStyle(
                        SpanStyle(
                            background = WidgetCalTodayFill,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ),
                        start,
                        length
                    )
                } else {
                    val color = if (inMonth) inMonthColor else outOfMonthColor
                    addStyle(SpanStyle(color = color), start, length)
                }
                if (col < 6) append(' ')
            }
            if (week < numWeeks - 1) append('\n')
        }
    }
}

private fun widget3x3PreviewTaskBackground(urgency: Int, done: Boolean, muted: Color): Color =
    if (done) muted.copy(alpha = 0.38f)
    else TaskUtils.getUrgencyColor(urgency).copy(alpha = 0.28f)

@Composable
private fun Widget3x1WeekQuotePreview(
    today: LocalDate,
    lang: LocalizationManager.Language,
    tasks: List<Task>,
    quote: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val locale = if (lang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.US
    val mdFmt = remember(lang) { DateTimeFormatter.ofPattern("M/d", locale) }
    val weekStart = remember(today) {
        today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
    }
    val weekEnd = weekStart.plusDays(6)
    val weekLabels = remember(lang, context) {
        LocalizedStrings.stringFor(lang, "widget_main_week_row", context)
            .split(" ")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .take(7)
    }
    val tasksByWeekday = remember(tasks, weekStart) {
        (0..6).map { idx ->
            val d = weekStart.plusDays(idx.toLong())
            TaskUtils.sortTasks(tasks.filter { it.taskDate == d }).take(4)
        }
    }

    WidgetPreviewCardShell(
        modifier = modifier
            .fillMaxWidth()
            .height(228.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 8.dp),
        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "‹",
                fontSize = 16.sp,
                color = LocalGlassTextColors.current.muted,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "${weekStart.format(mdFmt)}–${weekEnd.format(mdFmt)}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = LocalGlassTextColors.current.primary,
                modifier = Modifier.padding(horizontal = 6.dp),
            )
            Text(
                text = "›",
                fontSize = 16.sp,
                color = LocalGlassTextColors.current.muted,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
        Spacer(Modifier.height(4.dp))
        Row(Modifier.fillMaxWidth()) {
            weekLabels.forEach { label ->
                Text(
                    text = label,
                    modifier = Modifier.weight(1f),
                    fontSize = 11.sp,
                    color = LocalGlassTextColors.current.muted,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp, bottom = 6.dp)
        ) {
            for (i in 0..6) {
                val d = weekStart.plusDays(i.toLong())
                val dayColor = if (d == today) Color(0xFF1976D2) else LocalGlassTextColors.current.primary
                Text(
                    text = d.dayOfMonth.toString(),
                    modifier = Modifier.weight(1f),
                    fontSize = 13.sp,
                    fontWeight = if (d == today) FontWeight.Bold else FontWeight.Normal,
                    color = dayColor,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 2.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                tasksByWeekday.forEach { colTasks ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(3.dp),
                    ) {
                        if (colTasks.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(18.dp)
                                    .clip(RoundedCornerShape(AppShapes.MiniRadius))
                                    .background(Color(0xFFF0F0F0)),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = "—",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = LocalGlassTextColors.current.muted,
                                    maxLines = 1,
                                )
                            }
                        } else {
                            colTasks.forEach { task ->
                                val snippet = WidgetTaskTitleClip.for3x3CalendarCell(task.title)
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(18.dp)
                                        .clip(RoundedCornerShape(AppShapes.MiniRadius))
                                        .background(
                                            widget3x3PreviewTaskBackground(
                                                task.urgency,
                                                task.isCompleted,
                                                LocalGlassTextColors.current.muted,
                                            ),
                                        ),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = snippet,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = if (task.isCompleted) {
                                            LocalGlassTextColors.current.muted
                                        } else {
                                            LocalGlassTextColors.current.primary
                                        },
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        textDecoration = if (task.isCompleted) {
                                            TextDecoration.LineThrough
                                        } else {
                                            null
                                        },
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(horizontal = 2.dp),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        WidgetDailyQuoteBar(
            quote = quote,
            modifier = Modifier.fillMaxWidth(),
        )
        }
    }
}

@Composable
private fun SuperLargePreviewCard(
    today: LocalDate,
    allTasks: List<Task>,
    quote: String,
    lang: LocalizationManager.Language
) {
    val context = LocalContext.current
    val isZh = lang == LocalizationManager.Language.ZH
    val ym = YearMonth.from(today)
    val monthTitle = remember(today, isZh) {
        if (isZh) DateTimeFormatter.ofPattern("yyyy年M月", Locale.CHINA).format(today)
        else DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH).format(today)
    }
    val weekLabels = remember(lang, context) {
        LocalizedStrings.stringFor(lang, "widget_main_week_row", context)
            .split(" ")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .take(7)
    }
    val startGrid = remember(ym) {
        val first = ym.atDay(1)
        val off = (first.dayOfWeek.value - DayOfWeek.MONDAY.value + 7) % 7
        ym.atDay(1).minusDays(off.toLong())
    }
    val superLargeWeekCount = remember(ym) { previewCalendarWeekCount(ym) }

    WidgetPreviewCardShell(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "‹",
                    fontSize = 18.sp,
                    color = LocalGlassTextColors.current.muted,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Text(
                    text = monthTitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = LocalGlassTextColors.current.primary,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = "›",
                    fontSize = 18.sp,
                    color = LocalGlassTextColors.current.muted,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
        Spacer(Modifier.height(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            weekLabels.forEach { label ->
                Text(
                    text = label,
                    modifier = Modifier.weight(1f),
                    fontSize = 11.sp,
                    color = LocalGlassTextColors.current.muted,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Spacer(Modifier.height(2.dp))
        repeat(superLargeWeekCount) { w ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(7) { c ->
                    val date = startGrid.plusDays((w * 7 + c).toLong())
                    val inMonth = YearMonth.from(date) == ym
                    val dayTasks = TaskUtils.sortTasks(allTasks.filter { it.taskDate == date }).take(3)
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 1.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (date == today && inMonth) {
                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .clip(CircleShape)
                                    .background(WidgetCalTodayFill),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = date.dayOfMonth.toString(),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    maxLines = 1
                                )
                            }
                        } else {
                            Text(
                                text = date.dayOfMonth.toString(),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Normal,
                                color = if (inMonth) {
                                    LocalGlassTextColors.current.primary
                                } else {
                                    LocalGlassTextColors.current.muted.copy(alpha = 0.65f)
                                },
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                        Spacer(Modifier.height(3.dp))
                        dayTasks.forEachIndexed { idx, t ->
                            val snippet = WidgetTaskTitleClip.for3x3CalendarCell(t.title)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(18.dp)
                                    .clip(RoundedCornerShape(AppShapes.MiniRadius))
                                    .background(
                                    widget3x3PreviewTaskBackground(
                                        t.urgency,
                                        t.isCompleted,
                                        LocalGlassTextColors.current.muted,
                                    ),
                                ),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = snippet,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = if (t.isCompleted) LocalGlassTextColors.current.muted else LocalGlassTextColors.current.primary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    textDecoration = if (t.isCompleted) TextDecoration.LineThrough else null,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(horizontal = 2.dp),
                                )
                            }
                            if (idx < dayTasks.lastIndex) {
                                Spacer(Modifier.height(3.dp))
                            }
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = quote,
            fontSize = 11.sp,
            color = LocalGlassTextColors.current.muted,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        }
    }
}
