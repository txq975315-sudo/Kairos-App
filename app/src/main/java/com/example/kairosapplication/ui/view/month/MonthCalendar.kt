package com.example.kairosapplication.ui.view.month

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
private val DateNumberToday = Color(0xFF1A1A1A)
private val BarText = Color(0xFF1A1A1A)
private val ArrowGray = Color(0xFF9E9E9E)
private val NoteEmptyBg = Color(0xFFF5F0F0)
private val NoteEmptyDot = Color(0xFF9E9E9E)
private val TaskEmptyBg = Color(0xFFF5F5F5)
private val TodayCellBg = Color(0xFFF0F4FF)

@Composable
fun MonthCalendar(
    yearMonth: YearMonth,
    calendarData: Map<LocalDate, DayCalendarData>,
    onDateClick: (LocalDate) -> Unit,
    onMonthChange: (YearMonth) -> Unit,
    modifier: Modifier = Modifier,
) {
    val monthFmt = remember {
        DateTimeFormatter.ofPattern("yyyy年M月", Locale.CHINA)
    }
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
                color = ArrowGray,
                fontSize = 20.sp,
                modifier = Modifier
                    .viewClickable { onMonthChange(yearMonth.minusMonths(1)) }
                    .padding(horizontal = 8.dp, vertical = 8.dp),
            )
            Text(
                text = yearMonth.atDay(1).format(monthFmt),
                color = DateNumberToday,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center,
            )
            Text(
                text = ">",
                color = ArrowGray,
                fontSize = 20.sp,
                modifier = Modifier
                    .viewClickable { onMonthChange(yearMonth.plusMonths(1)) }
                    .padding(horizontal = 8.dp, vertical = 8.dp),
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            val wdays = listOf("一", "二", "三", "四", "五", "六", "日")
            wdays.forEach { w ->
                Text(
                    text = w,
                    color = DateNumberMuted,
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 4.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        val rowCount = totalCells / 7
        for (row in 0 until rowCount) {
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(7) { colInRow ->
                    val cell = row * 7 + colInRow
                    val dayNum = cell - firstCol + 1
                    if (dayNum < 1 || dayNum > dim) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f),
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
                                .aspectRatio(1f),
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
    val taskBg = if (data.taskCount > 0 && data.dominantTaskTimeBlock != null) {
        TaskUtils.getTimeBlockColor(data.dominantTaskTimeBlock).copy(alpha = 0.2f)
    } else {
        TaskEmptyBg
    }
    val taskText = if (data.taskCount > 0) {
        "${data.taskCompletedCount}/${data.taskCount}"
    } else {
        "—"
    }
    val noteCat = data.dominantNoteCategory
    val noteBg = if (data.noteCount > 0 && noteCat != null) {
        NoteCardConstants.categoryColor(noteCat).copy(alpha = 0.2f)
    } else {
        NoteEmptyBg
    }
    val dotColor = if (data.noteCount > 0 && noteCat != null) {
        NoteCardConstants.categoryColor(noteCat)
    } else {
        NoteEmptyDot
    }
    val urgencyStripe = if (data.taskCount > 0) {
        TaskUtils.getUrgencyColor(data.dominantTaskUrgency ?: TaskConstants.URGENCY_LOW)
    } else {
        Color.Transparent
    }
    Row(
        modifier = modifier
            .fillMaxSize()
            .viewClickable(onClick)
            .background(if (isToday) TodayCellBg else Color.Transparent)
            .padding(2.dp),
    ) {
        if (data.taskCount > 0) {
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(urgencyStripe),
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(start = if (data.taskCount > 0) 2.dp else 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = date.dayOfMonth.toString(),
                color = if (isToday) DateNumberToday else DateNumberMuted,
                fontSize = 12.sp,
                fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(taskBg)
                    .padding(horizontal = 2.dp, vertical = 2.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = taskText,
                    color = BarText,
                    fontSize = 11.sp,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(noteBg)
                    .padding(horizontal = 2.dp, vertical = 2.dp),
                contentAlignment = Alignment.Center,
            ) {
                if (data.noteCount > 0) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .background(dotColor, CircleShape),
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = data.noteCount.toString(),
                            color = BarText,
                            fontSize = 11.sp,
                            maxLines = 1,
                        )
                    }
                } else {
                    Text(
                        text = "—",
                        color = BarText,
                        fontSize = 11.sp,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}
