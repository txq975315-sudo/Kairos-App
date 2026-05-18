package com.example.kairosapplication.ui.view.month

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.core.ui.LocalUrgencyConfig
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.weekShortHeadersMondayFirst
import com.example.kairosapplication.ui.components.NoteCardConstants
import com.example.kairosapplication.ui.view.DayCalendarData
import com.example.kairosapplication.ui.view.LocalViewChrome
import com.example.kairosapplication.ui.view.viewClickable
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.util.ColorUtils.parseHexToArgb
import java.time.LocalDate
import java.time.YearMonth

private val SummaryCardHeight = 18.dp
/** Date number + two summary rows (tasks + notes), compact height. */
private val CalendarRowMinHeight = 76.dp

@Composable
fun MonthCalendar(
    yearMonth: YearMonth,
    calendarData: Map<LocalDate, DayCalendarData>,
    onDateClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val chrome = LocalViewChrome.current
    val weekHeaders = remember(lang, context) { weekShortHeadersMondayFirst(context, lang) }
    val today = LocalDate.now()
    val dim = yearMonth.lengthOfMonth()
    val firstCol = (yearMonth.atDay(1).dayOfWeek.value + 6) % 7
    val totalCells = ((firstCol + dim + 6) / 7) * 7

    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            weekHeaders.forEach { label ->
                Text(
                    text = label,
                    color = chrome.muted,
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 2.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        val rowCount = totalCells / 7
        for (row in 0 until rowCount) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(CalendarRowMinHeight),
            ) {
                repeat(7) { colInRow ->
                    val cell = row * 7 + colInRow
                    val dayNum = cell - firstCol + 1
                    if (dayNum < 1 || dayNum > dim) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize(),
                        )
                    } else {
                        val date = yearMonth.atDay(dayNum)
                        val dayData = calendarData[date] ?: DayCalendarData(0, 0, 0, null, null, null)
                        CalendarDayCell(
                            date = date,
                            data = dayData,
                            isToday = date == today,
                            onClick = { onDateClick(date) },
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CalendarDayCell(
    date: LocalDate,
    data: DayCalendarData,
    isToday: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val chrome = LocalViewChrome.current
    val urgencyColorConfig = LocalUrgencyConfig.current
    val taskText = if (data.taskCount > 0) {
        "${data.taskCompletedCount}/${data.taskCount}"
    } else {
        "—"
    }
    val noteCat = data.dominantNoteCategory
    val emptyBorder = chrome.divider
    val taskTint = if (data.taskCount > 0) {
        Color(parseHexToArgb(urgencyColorConfig.colorForLevel(data.dominantTaskUrgency ?: TaskConstants.URGENCY_LOW)))
    } else {
        chrome.muted
    }
    val noteTint = if (data.noteCount > 0 && noteCat != null) {
        NoteCardConstants.categoryColor(noteCat)
    } else {
        chrome.muted
    }
    val todayHighlight = Color.White.copy(alpha = 0.12f)
    Column(
        modifier = modifier
            .fillMaxSize()
            .viewClickable(onClick)
            .then(
                if (isToday) {
                    Modifier.background(todayHighlight)
                } else {
                    Modifier
                },
            )
            .padding(horizontal = 3.dp, vertical = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            color = if (isToday) chrome.primary else chrome.secondary,
            fontSize = 13.sp,
            fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(3.dp))
        MonthDaySummaryCard(
            text = taskText,
            borderColor = if (data.taskCount > 0) taskTint else emptyBorder,
            textColor = if (data.taskCount > 0) chrome.primary else chrome.muted,
            isEmpty = data.taskCount == 0,
        )
        Spacer(modifier = Modifier.height(3.dp))
        MonthDaySummaryCard(
            text = if (data.noteCount > 0) data.noteCount.toString() else "—",
            borderColor = if (data.noteCount > 0 && noteCat != null) noteTint else emptyBorder,
            textColor = if (data.noteCount > 0) chrome.primary else chrome.muted,
            isEmpty = data.noteCount == 0,
        )
    }
}

@Composable
private fun MonthDaySummaryCard(
    text: String,
    borderColor: Color,
    textColor: Color,
    isEmpty: Boolean,
) {
    val shape = RoundedCornerShape(AppShapes.MiniRadius)
    val borderAlpha = if (isEmpty) 0.35f else 0.72f
    val fillAlpha = when {
        isEmpty -> 0f
        LocalAppUiTheme.current == AppUiTheme.Classic -> 0.16f
        else -> 0.10f
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(SummaryCardHeight)
            .clip(shape)
            .then(
                if (fillAlpha > 0f) {
                    Modifier.background(borderColor.copy(alpha = fillAlpha), shape)
                } else {
                    Modifier
                },
            )
            .border(
                width = 1.dp,
                color = borderColor.copy(alpha = borderAlpha),
                shape = shape,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            textAlign = TextAlign.Center,
            style = TextStyle(
                textAlign = TextAlign.Center,
                lineHeight = 11.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
            ),
        )
    }
}
