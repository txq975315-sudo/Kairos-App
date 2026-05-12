package com.example.kairosapplication.ui.mine.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.i18n.weekShortHeadersMondayFirst
import com.example.kairosapplication.ui.mine.MineViewModel
import com.example.kairosapplication.ui.mine.records.CalendarDayVisual
import com.example.kairosapplication.ui.mine.records.CheckInViewMode
import com.example.kairosapplication.ui.mine.records.FutureCheckInDisplay
import com.example.kairosapplication.ui.mine.records.PastCheckInDisplay
import com.example.kairosapplication.ui.mine.records.calendarDayVisual
import com.example.kairosapplication.ui.mine.records.pastTierCountsInMonth
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Task
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

private val TitleColor = Color(0xFF1A1A1A)
private val SubGray = Color(0xFF9E9E9E)
private val TodayBorder = Color(0xFF9E9E9E)
private val ToggleBg = Color(0xFFF0F0F2)
private val ToggleSelected = Color(0xFF5B7CFA)
private val EmojiSize = 26.sp
private val FutureMuted = Color(0xFFBDBDBD)
private val FutureDot = Color(0xFFE0E0E0)

@Composable
fun CheckInStatsSection(
    tasks: List<Task>,
    publishedNotes: List<Note>,
    viewMode: CheckInViewMode,
    onViewModeChange: (CheckInViewMode) -> Unit,
    modifier: Modifier = Modifier,
    showHeadingRow: Boolean = true,
    onDayClick: ((LocalDate) -> Unit)? = null,
    showMonthSummaryFooter: Boolean = false,
    onViewAllRecordsClick: (() -> Unit)? = null,
) {
    val lang = LocalCurrentLanguage.current.value
    var yearMonth by remember { mutableStateOf(YearMonth.now()) }
    var weekCursor by remember { mutableStateOf(LocalDate.now()) }

    val monthTitleFmt = remember(lang) {
        if (lang == LocalizationManager.Language.ZH) {
            DateTimeFormatter.ofPattern("yyyy年M月", Locale.CHINA)
        } else {
            DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)
        }
    }
    val shortMd = remember(lang) {
        if (lang == LocalizationManager.Language.ZH) {
            DateTimeFormatter.ofPattern("M月d日", Locale.CHINA)
        } else {
            DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH)
        }
    }

    val tasksByDate = remember(tasks) { tasks.groupBy { it.taskDate } }
    val context = LocalContext.current
    val weekHeaderLabels = remember(lang, context) { weekShortHeadersMondayFirst(context, lang) }
    val essayByDate = remember(publishedNotes) {
        publishedNotes
            .filter { it.status == NoteStatus.PUBLISHED }
            .groupingBy { it.recordedDate }
            .eachCount()
    }
    val today = LocalDate.now()

    Column(modifier = modifier.fillMaxWidth()) {
        if (showHeadingRow) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = LocalizedStrings.get("mine_checkin_title"),
                    color = TitleColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    modifier = Modifier
                        .background(ToggleBg, RoundedCornerShape(AppShapes.FeaturePanelRadius))
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    CheckInModeChip(
                        label = LocalizedStrings.get("mine_checkin_week"),
                        selected = viewMode == CheckInViewMode.WEEK,
                        onClick = { onViewModeChange(CheckInViewMode.WEEK) }
                    )
                    CheckInModeChip(
                        label = LocalizedStrings.get("mine_checkin_month"),
                        selected = viewMode == CheckInViewMode.MONTH,
                        onClick = { onViewModeChange(CheckInViewMode.MONTH) }
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                when (viewMode) {
                    CheckInViewMode.MONTH -> yearMonth = yearMonth.minusMonths(1)
                    CheckInViewMode.WEEK -> weekCursor = weekCursor.minusWeeks(1)
                }
            }) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null, tint = TitleColor)
            }
            Text(
                text = when (viewMode) {
                    CheckInViewMode.MONTH -> yearMonth.format(monthTitleFmt)
                    CheckInViewMode.WEEK -> {
                        val monday = MineViewModel.mondayOfWeekContaining(weekCursor)
                        val sunday = monday.plusDays(6)
                        "${monday.format(shortMd)} – ${sunday.format(shortMd)}"
                    }
                },
                color = TitleColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            IconButton(onClick = {
                when (viewMode) {
                    CheckInViewMode.MONTH -> yearMonth = yearMonth.plusMonths(1)
                    CheckInViewMode.WEEK -> weekCursor = weekCursor.plusWeeks(1)
                }
            }) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = TitleColor)
            }
        }
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            weekHeaderLabels.forEach { w ->
                Text(
                    text = w,
                    fontSize = 11.sp,
                    color = SubGray,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(Modifier.height(4.dp))
        when (viewMode) {
            CheckInViewMode.MONTH -> CheckInMonthGrid(
                yearMonth = yearMonth,
                today = today,
                tasksByDate = tasksByDate,
                essayByDate = essayByDate,
                onDayClick = onDayClick,
            )
            CheckInViewMode.WEEK -> {
                val monday = MineViewModel.mondayOfWeekContaining(weekCursor)
                CheckInWeekRow(
                    monday = monday,
                    today = today,
                    tasksByDate = tasksByDate,
                    essayByDate = essayByDate,
                    onDayClick = onDayClick,
                )
            }
        }
        if (showMonthSummaryFooter && viewMode == CheckInViewMode.MONTH) {
            Spacer(Modifier.height(10.dp))
            val counts = pastTierCountsInMonth(yearMonth, today, tasksByDate, essayByDate)
            Text(
                text = formatMonthPastSummary(lang, counts),
                color = SubGray,
                fontSize = 11.sp,
                lineHeight = 15.sp,
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = LocalizedStrings.get("mine_checkin_legend"),
            color = SubGray,
            fontSize = 11.sp,
            lineHeight = 15.sp
        )
        if (onViewAllRecordsClick != null) {
            Spacer(Modifier.height(12.dp))
            Text(
                text = LocalizedStrings.get("mine_all_records_open_full"),
                fontSize = 13.sp,
                color = Color(0xFF5C6BC0),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onViewAllRecordsClick)
                    .padding(vertical = 2.dp),
            )
        }
    }
}

private fun formatMonthPastSummary(
    lang: LocalizationManager.Language,
    counts: Map<PastCheckInDisplay, Int>,
): String {
    fun n(d: PastCheckInDisplay) = counts[d] ?: 0
    val zh = lang == LocalizationManager.Language.ZH
    return if (zh) {
        "本月完美：${n(PastCheckInDisplay.PERFECT)}天    优秀：${n(PastCheckInDisplay.EXCELLENT)}天\n" +
            "加油：${n(PastCheckInDisplay.CHEER)}天        新芽：${n(PastCheckInDisplay.SEEDLING)}天\n" +
            "文档记录者：${n(PastCheckInDisplay.WRITER)}天  空白：${n(PastCheckInDisplay.EMPTY)}天"
    } else {
        "Perfect ${n(PastCheckInDisplay.PERFECT)} · Great ${n(PastCheckInDisplay.EXCELLENT)} · " +
            "Cheer ${n(PastCheckInDisplay.CHEER)} · Sprout ${n(PastCheckInDisplay.SEEDLING)}\n" +
            "Writer ${n(PastCheckInDisplay.WRITER)} · Blank ${n(PastCheckInDisplay.EMPTY)}"
    }
}

@Composable
private fun CheckInModeChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val bg = if (selected) ToggleSelected else Color.Transparent
    val fg = if (selected) Color.White else TitleColor
    Text(
        text = label,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        color = fg,
        modifier = Modifier
            .background(bg, RoundedCornerShape(AppShapes.CardRadius))
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 6.dp)
    )
}

@Composable
private fun CheckInMonthGrid(
    yearMonth: YearMonth,
    today: LocalDate,
    tasksByDate: Map<LocalDate, List<Task>>,
    essayByDate: Map<LocalDate, Int>,
    onDayClick: ((LocalDate) -> Unit)?,
) {
    val firstDow = yearMonth.atDay(1).dayOfWeek.value
    val leading = firstDow - 1
    val daysInMonth = yearMonth.lengthOfMonth()
    val totalSlots = leading + daysInMonth
    val rows = (totalSlots + 6) / 7
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for (r in 0 until rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                for (c in 0 until 7) {
                    val idx = r * 7 + c
                    val dayNum = idx - leading + 1
                    if (idx < leading || dayNum > daysInMonth) {
                        Box(modifier = Modifier.weight(1f).height(54.dp))
                    } else {
                        val date = yearMonth.atDay(dayNum)
                        val visual = calendarDayVisual(
                            date = date,
                            today = today,
                            dayTasks = tasksByDate[date].orEmpty(),
                            essayCount = essayByDate[date] ?: 0,
                        )
                        CheckInDayCell(
                            dayNum = dayNum,
                            visual = visual,
                            isToday = date == today,
                            modifier = Modifier
                                .weight(1f)
                                .then(
                                    if (onDayClick != null) {
                                        Modifier.clickable { onDayClick(date) }
                                    } else {
                                        Modifier
                                    },
                                ),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CheckInWeekRow(
    monday: LocalDate,
    today: LocalDate,
    tasksByDate: Map<LocalDate, List<Task>>,
    essayByDate: Map<LocalDate, Int>,
    onDayClick: ((LocalDate) -> Unit)?,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        for (i in 0 until 7) {
            val date = monday.plusDays(i.toLong())
            val visual = calendarDayVisual(
                date = date,
                today = today,
                dayTasks = tasksByDate[date].orEmpty(),
                essayCount = essayByDate[date] ?: 0,
            )
            CheckInDayCell(
                dayNum = date.dayOfMonth,
                visual = visual,
                isToday = date == today,
                modifier = Modifier
                    .weight(1f)
                    .then(
                        if (onDayClick != null) Modifier.clickable { onDayClick(date) } else Modifier,
                    ),
            )
        }
    }
}

@Composable
private fun CheckInDayCell(
    dayNum: Int,
    visual: CalendarDayVisual,
    isToday: Boolean,
    modifier: Modifier = Modifier,
) {
    val isFutureStyle = visual.useMutedFutureStyle
    val titleColor = when {
        isFutureStyle -> FutureMuted
        visual.past == PastCheckInDisplay.EMPTY -> SubGray
        else -> TitleColor
    }
    Column(
        modifier = modifier
            .height(54.dp)
            .then(
                if (isToday) Modifier.border(2.dp, TodayBorder, RoundedCornerShape(AppShapes.CompactRadius))
                else Modifier
            )
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = dayNum.toString(),
            fontSize = 12.sp,
            color = titleColor
        )
        when {
            visual.future != null -> {
                val sym = visual.future.symbol()
                val col = if (visual.future == FutureCheckInDisplay.NONE_DOT) {
                    FutureDot
                } else {
                    FutureMuted
                }
                Text(
                    text = sym,
                    fontSize = EmojiSize,
                    color = col,
                )
            }
            visual.past != null -> Text(
                text = visual.past.emoji(),
                fontSize = EmojiSize,
            )
            else -> Spacer(Modifier.height(22.dp))
        }
    }
}
