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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.kairosapplication.i18n.LocalizedStrings
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

private val WidgetScreenBg = Color(0xFFF2F2F2)
private val PreviewCardBg = Color.White
private val WidgetPurpleTrack = Color(0xFFE8DEF8)
private val WidgetPurpleProgress = Color(0xFF7B61FF)
private val WidgetPurpleAccent = Color(0xFF7B61FF)
private val WidgetCalTodayFill = Color(0xFF9F8CF7)
private val TextPrimary = Color(0xFF1A1A1A)
private val TextMuted = Color(0xFF9E9E9E)
private val TextQuote = Color(0xFF333333)

@Composable
fun WidgetMainScreen(
    taskViewModel: TaskViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by taskViewModel.uiState.collectAsState()
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
    val yesterday = remember(today) { today.minusDays(1) }
    val tomorrow = remember(today) { today.plusDays(1) }
    val yTasks = remember(uiState.tasks, yesterday) {
        TaskUtils.sortTasks(uiState.tasks.filter { it.taskDate == yesterday })
    }
    val tmTasks = remember(uiState.tasks, tomorrow) {
        TaskUtils.sortTasks(uiState.tasks.filter { it.taskDate == tomorrow })
    }
    val triptychMergedDateLine = remember(today, lang, isZh) {
        val mmDd = if (isZh) {
            DateTimeFormatter.ofPattern("MM-dd", Locale.CHINA).format(today)
        } else {
            DateTimeFormatter.ofPattern("MM-dd", Locale.ENGLISH).format(today)
        }
        val wd = today.dayOfWeek.getDisplayName(
            TextStyle.SHORT,
            if (isZh) Locale.CHINA else Locale.ENGLISH
        )
        val todayLabel = LocalizedStrings.stringFor(lang, "view_tab_today")
        "${today.dayOfMonth} · $todayLabel · $wd · $mmDd"
    }
    val triptychGridSlots = remember(sortedToday) {
        List(10) { idx ->
            sortedToday.getOrNull(idx)?.let { t ->
                t.title.trim().ifBlank { "—" } to t.isCompleted
            }
        }
    }
    val triptychOverflow = remember(sortedToday, lang) {
        val n = sortedToday.size - 10
        if (n > 0) {
            LocalizedStrings.stringFor(lang, "widget_3x1_more_tasks").replace("{n}", n.toString())
        } else {
            null
        }
    }
    val weekdayFullFor2x2 = remember(today, lang) {
        today.dayOfWeek.getDisplayName(
            TextStyle.FULL,
            if (lang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.ENGLISH
        )
    }
    val monthTitle2x2 = remember(today, lang) {
        WidgetViewFactory.build2x2CalendarExtras(today, lang).first
    }
    val monthGrid2x2Annotated = remember(today) {
        monthGridPreviewAnnotated(YearMonth.from(today), today)
    }
    val yesterdaySideAnnotated = remember(yTasks) {
        buildWidgetSideAnnotated(yTasks, strikeCompleted = true)
    }
    val tomorrowSideAnnotated = remember(tmTasks) {
        buildWidgetSideAnnotated(tmTasks, strikeCompleted = false)
    }

    var selectedSizeKey by rememberSaveable { mutableStateOf(WidgetSize._1X1.name) }
    val selectedSize = WidgetSize.valueOf(selectedSizeKey)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WidgetScreenBg)
            .statusBarsPadding()
    ) {
        WidgetSizeTabRow(
            selected = selectedSize,
            onSelect = { selectedSizeKey = it.name }
        )
        HorizontalDivider(thickness = 0.5.dp, color = Color(0xFFE0E0E0))
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
                WidgetSize._2X2 -> Widget3x1TriptychStrip(
                    yesterdayDay = yesterday.dayOfMonth,
                    yesterdayTasksAnnotated = yesterdaySideAnnotated,
                    mergedDateLine = triptychMergedDateLine,
                    gridSlots = triptychGridSlots,
                    overflowHint = triptychOverflow,
                    tomorrowDay = tomorrow.dayOfMonth,
                    tomorrowTasksAnnotated = tomorrowSideAnnotated,
                    modifier = Modifier.fillMaxWidth()
                )
                WidgetSize._3X1 -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Split2x2WidgetPreviewCard(
                            weekdayLabel = weekdayFullFor2x2,
                            completed = completed,
                            total = total,
                            tasks = previewTasks,
                            quote = quote,
                            monthTitle = monthTitle2x2,
                            monthGrid = monthGrid2x2Annotated,
                            isZh = isZh
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

@Composable
private fun WidgetSizeTabRow(
    selected: WidgetSize,
    onSelect: (WidgetSize) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        WidgetSize.entries.forEach { size ->
            val label = when (size) {
                WidgetSize._1X1 -> LocalizedStrings.get("widget_main_tab_small")
                WidgetSize._2X2 -> LocalizedStrings.get("widget_main_tab_medium")
                WidgetSize._3X1 -> LocalizedStrings.get("widget_main_tab_large")
                WidgetSize._3X3 -> LocalizedStrings.get("widget_main_tab_super")
            }
            val isSelected = size == selected
            val interaction = remember { MutableInteractionSource() }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = interaction,
                        indication = null
                    ) { onSelect(size) }
                    .padding(vertical = 8.dp, horizontal = 4.dp)
            ) {
                Text(
                    text = label,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) TextPrimary else TextMuted,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Box(
                    modifier = Modifier
                        .height(3.dp)
                        .fillMaxWidth(0.55f)
                        .background(if (isSelected) TextPrimary else Color.Transparent)
                )
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
    Column(
        modifier = modifier
            .shadow(6.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(PreviewCardBg)
            .padding(12.dp)
            .aspectRatio(1f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(dayName, fontSize = 11.sp, color = TextMuted, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(dayNum, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        }
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val frac = if (total <= 0) 0f else (completed.toFloat() / total.toFloat()).coerceIn(0f, 1f)
            Box(contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.size(88.dp)) {
                    val stroke = 6.dp.toPx()
                    val diameter = size.minDimension
                    drawArc(
                        color = WidgetPurpleTrack,
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        topLeft = androidx.compose.ui.geometry.Offset.Zero,
                        size = Size(diameter, diameter),
                        style = Stroke(width = stroke, cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = WidgetPurpleProgress,
                        startAngle = -90f,
                        sweepAngle = 360f * frac,
                        useCenter = false,
                        topLeft = androidx.compose.ui.geometry.Offset.Zero,
                        size = Size(diameter, diameter),
                        style = Stroke(width = stroke, cap = StrokeCap.Round)
                    )
                }
                Text(
                    text = if (total > 0) "$completed / $total" else "0 / 0",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary
                )
            }
        }
        Text(
            text = quote,
            fontSize = 11.sp,
            color = TextQuote,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun Preview1BCard(
    completed: Int,
    total: Int,
    quote: String,
    tasks: List<Task>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .shadow(6.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(PreviewCardBg)
            .padding(12.dp)
            .aspectRatio(1f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (total > 0) "$completed/$total" else "0/0",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = WidgetPurpleAccent,
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            val rows = if (tasks.isEmpty()) {
                List(3) { null as Task? }
            } else {
                (tasks + List(3) { null }).take(3)
            }
            rows.forEach { task ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = task?.title?.takeIf { it.isNotBlank() } ?: "—",
                        fontSize = 12.sp,
                        color = TextPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    val ringColor = if (task == null) {
                        TextMuted
                    } else if (task.isCompleted) {
                        WidgetPurpleProgress
                    } else {
                        TaskUtils.getUrgencyColor(task.urgency)
                    }
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .border(2.dp, ringColor, CircleShape)
                    )
                }
            }
        }
        Text(
            text = quote,
            fontSize = 11.sp,
            color = TextQuote,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun Split2x2WidgetPreviewCard(
    weekdayLabel: String,
    completed: Int,
    total: Int,
    tasks: List<Task>,
    quote: String,
    monthTitle: String,
    monthGrid: AnnotatedString,
    isZh: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .shadow(6.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(PreviewCardBg)
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.42f)
                .fillMaxHeight()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = weekdayLabel,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = if (isZh) "已完成${completed}/总${total}" else "${completed} of ${total} done",
                    fontSize = 10.sp,
                    color = TextMuted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                tasks.take(5).forEach { task ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = if (task.isCompleted) "✓" else "○",
                            fontSize = 12.sp,
                            color = if (task.isCompleted) WidgetPurpleProgress else TextMuted
                        )
                        Text(
                            text = task.title,
                            fontSize = 12.sp,
                            color = if (task.isCompleted) TextMuted else TextPrimary,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                if (tasks.isEmpty()) {
                    Text(
                        LocalizedStrings.get("widget_main_no_tasks"),
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                }
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = quote,
                fontSize = 10.sp,
                color = TextMuted,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Column(
            modifier = Modifier
                .weight(0.58f)
                .fillMaxHeight()
                .padding(start = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = monthTitle,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
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
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}

private fun previewCalendarWeekCount(ym: YearMonth): Int {
    val first = ym.atDay(1)
    val offset = (first.dayOfWeek.value - DayOfWeek.MONDAY.value + 7) % 7
    val startGrid = ym.atDay(1).minusDays(offset.toLong())
    val spanDays = ChronoUnit.DAYS.between(startGrid, ym.atEndOfMonth()).toInt() + 1
    return kotlin.math.max(1, (spanDays + 6) / 7)
}

private fun monthGridPreviewAnnotated(ym: YearMonth, today: LocalDate): AnnotatedString {
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
                    val color = if (inMonth) TextPrimary else Color(0xFFBDBDBD)
                    addStyle(SpanStyle(color = color), start, length)
                }
                if (col < 6) append(' ')
            }
            if (week < numWeeks - 1) append('\n')
        }
    }
}

private fun widget3x3PreviewTaskBackground(urgency: Int, done: Boolean): Color =
    if (done) TextMuted.copy(alpha = 0.38f)
    else TaskUtils.getUrgencyColor(urgency).copy(alpha = 0.45f)

private fun buildWidgetSideAnnotated(tasks: List<Task>, strikeCompleted: Boolean) = buildAnnotatedString {
    if (tasks.isEmpty()) {
        append("—")
        return@buildAnnotatedString
    }
    val grey = Color(0xFF888888)
    tasks.take(4).forEachIndexed { i, t ->
        if (i > 0) append('\n')
        val title = t.title.trim().ifBlank { "—" }.take(22)
        val start = length
        append(title)
        addStyle(SpanStyle(color = grey), start, length)
        if (strikeCompleted && t.isCompleted) {
            addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), start, length)
        }
    }
}

@Composable
private fun Widget3x1WeekQuotePreview(
    today: LocalDate,
    lang: LocalizationManager.Language,
    tasks: List<Task>,
    quote: String,
    modifier: Modifier = Modifier,
) {
    val locale = if (lang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.US
    val mdFmt = remember(lang) { DateTimeFormatter.ofPattern("M/d", locale) }
    val weekStart = remember(today) {
        today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
    }
    val weekEnd = weekStart.plusDays(6)
    val weekLabels = remember(lang) {
        LocalizedStrings.stringFor(lang, "widget_main_week_row")
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

    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 228.dp)
            .shadow(6.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(PreviewCardBg)
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "‹",
                fontSize = 16.sp,
                color = TextMuted,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "${weekStart.format(mdFmt)}–${weekEnd.format(mdFmt)}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                modifier = Modifier.padding(horizontal = 6.dp),
            )
            Text(
                text = "›",
                fontSize = 16.sp,
                color = TextMuted,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
        Spacer(Modifier.height(4.dp))
        Row(Modifier.fillMaxWidth()) {
            weekLabels.forEach { label ->
                Text(
                    text = label,
                    modifier = Modifier.weight(1f),
                    fontSize = 9.sp,
                    color = TextMuted,
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
                val dayColor = if (d == today) Color(0xFF1976D2) else TextPrimary
                Text(
                    text = d.dayOfMonth.toString(),
                    modifier = Modifier.weight(1f),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = dayColor,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            tasksByWeekday.forEach { colTasks ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    if (colTasks.isEmpty()) {
                        Text(
                            text = "—",
                            fontSize = 8.sp,
                            color = TextMuted,
                            maxLines = 1
                        )
                    } else {
                        colTasks.forEach { task ->
                            val snippet = WidgetTaskTitleClip.for3x3CalendarCell(task.title)
                            Text(
                                text = snippet,
                                fontSize = 8.sp,
                                color = if (task.isCompleted) TextMuted else TextPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(widget3x3PreviewTaskBackground(task.urgency, task.isCompleted))
                                    .padding(horizontal = 2.dp, vertical = 1.dp)
                            )
                        }
                    }
                }
            }
        }
        Text(
            text = quote,
            fontSize = 11.sp,
            color = TextMuted,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp)
        )
    }
}

@Composable
private fun SuperLargePreviewCard(
    today: LocalDate,
    allTasks: List<Task>,
    quote: String,
    lang: LocalizationManager.Language
) {
    val isZh = lang == LocalizationManager.Language.ZH
    val ym = YearMonth.from(today)
    val monthTitle = remember(today, isZh) {
        if (isZh) DateTimeFormatter.ofPattern("yyyy年M月", Locale.CHINA).format(today)
        else DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH).format(today)
    }
    val weekLabels = remember(lang) {
        LocalizedStrings.stringFor(lang, "widget_main_week_row")
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(PreviewCardBg)
            .padding(12.dp)
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
                    color = TextMuted,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Text(
                    text = monthTitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = "›",
                    fontSize = 18.sp,
                    color = TextMuted,
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
                    fontSize = 10.sp,
                    color = TextMuted,
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
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    maxLines = 1
                                )
                            }
                        } else {
                            Text(
                                text = date.dayOfMonth.toString(),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (inMonth) TextPrimary else Color(0xFFBDBDBD),
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                        dayTasks.forEach { t ->
                            val snippet = WidgetTaskTitleClip.for3x3CalendarCell(t.title)
                            Text(
                                text = snippet,
                                fontSize = 8.sp,
                                color = if (t.isCompleted) TextMuted else TextPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textDecoration = if (t.isCompleted) TextDecoration.LineThrough else null,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(widget3x3PreviewTaskBackground(t.urgency, t.isCompleted))
                                    .padding(horizontal = 2.dp, vertical = 1.dp)
                            )
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = quote,
            fontSize = 11.sp,
            color = TextMuted,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
