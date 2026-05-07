package com.example.kairosapplication.ui.view.month

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppScreenHeader
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.weekShortHeadersMondayFirst
import com.example.kairosapplication.ui.components.NoteCardConstants
import com.example.kairosapplication.ui.view.DayCalendarData
import com.example.kairosapplication.ui.view.viewClickable
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.util.TaskUtils
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

private val DateNumberMuted = Color(0xFF9E9E9E)
private val NoteEmptyBg = Color(0xFFF0F0F0)
private val TaskEmptyBg = Color(0xFFF0F0F0)
private val TodayCellBg = Color(0xFFF0F4FF)
private val SummaryCardText = Color(0xFF1A1A1A)
private val SummaryCardRadius = 4.dp
private val SummaryCardHeight = 18.dp
/** Date number + two summary rows (tasks + notes), compact height. */
private val CalendarRowMinHeight = 76.dp

@Composable
fun MonthCalendar(
    yearMonth: YearMonth,
    calendarData: Map<LocalDate, DayCalendarData>,
    onDateClick: (LocalDate) -> Unit,
    onMonthChange: (YearMonth) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val monthFmt = remember(lang) {
        when (lang) {
            LocalizationManager.Language.ZH ->
                DateTimeFormatter.ofPattern("yyyy年M月", Locale.CHINA)
            LocalizationManager.Language.EN ->
                DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)
        }
    }
    val weekHeaders = remember(lang, context) { weekShortHeadersMondayFirst(context, lang) }
    val today = LocalDate.now()
    val dim = yearMonth.lengthOfMonth()
    val firstCol = (yearMonth.atDay(1).dayOfWeek.value + 6) % 7
    val totalCells = ((firstCol + dim + 6) / 7) * 7

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "<",
                color = AppColors.IconNeutral,
                fontSize = 20.sp,
                modifier = Modifier
                    .viewClickable { onMonthChange(yearMonth.minusMonths(1)) }
                    .padding(horizontal = 8.dp, vertical = 8.dp),
            )
            Text(
                text = yearMonth.atDay(1).format(monthFmt),
                color = AppScreenHeader.titleColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center,
            )
            Text(
                text = ">",
                color = AppColors.IconNeutral,
                fontSize = 20.sp,
                modifier = Modifier
                    .viewClickable { onMonthChange(yearMonth.plusMonths(1)) }
                    .padding(horizontal = 8.dp, vertical = 8.dp),
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            weekHeaders.forEach { label ->
                Text(
                    text = label,
                    color = DateNumberMuted,
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
    val taskText = if (data.taskCount > 0) {
        "${data.taskCompletedCount}/${data.taskCount}"
    } else {
        "—"
    }
    val noteCat = data.dominantNoteCategory
    val taskTint = if (data.taskCount > 0) {
        TaskUtils.getUrgencyColor(data.dominantTaskUrgency ?: TaskConstants.URGENCY_LOW)
    } else {
        Color(0xFFBDBDBD)
    }
    val noteTint = if (data.noteCount > 0 && noteCat != null) {
        NoteCardConstants.categoryColor(noteCat)
    } else {
        Color(0xFFBDBDBD)
    }
    val taskCardBg = if (data.taskCount > 0) {
        taskTint.copy(alpha = 0.28f)
    } else {
        TaskEmptyBg
    }
    val noteCardBg = if (data.noteCount > 0 && noteCat != null) {
        noteTint.copy(alpha = 0.28f)
    } else {
        NoteEmptyBg
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .viewClickable(onClick)
            .background(if (isToday) TodayCellBg else Color.Transparent)
            .padding(horizontal = 3.dp, vertical = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            color = if (isToday) AppScreenHeader.titleColor else DateNumberMuted,
            fontSize = 13.sp,
            fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(3.dp))
        MonthDaySummaryCard(
            text = taskText,
            backgroundColor = taskCardBg,
        )
        Spacer(modifier = Modifier.height(3.dp))
        MonthDaySummaryCard(
            text = if (data.noteCount > 0) data.noteCount.toString() else "—",
            backgroundColor = noteCardBg,
        )
    }
}

@Composable
private fun MonthDaySummaryCard(
    text: String,
    backgroundColor: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(SummaryCardHeight)
            .clip(RoundedCornerShape(SummaryCardRadius))
            .background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = SummaryCardText,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            textAlign = TextAlign.Center,
        )
    }
}
