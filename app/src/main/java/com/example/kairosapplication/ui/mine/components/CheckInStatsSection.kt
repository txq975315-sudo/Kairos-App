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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.mine.MineViewModel
import com.example.kairosapplication.ui.mine.records.CheckInViewMode
import com.example.kairosapplication.ui.mine.records.DailyCheckInTier
import com.example.kairosapplication.ui.mine.records.dailyCheckInTierForDay
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
private val WeekLabels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
private val EmojiSize = 26.sp
private val EmptyMarkSize = 13.sp

@Composable
fun CheckInStatsSection(
    tasks: List<Task>,
    viewMode: CheckInViewMode,
    onViewModeChange: (CheckInViewMode) -> Unit,
    modifier: Modifier = Modifier,
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

    Column(modifier = modifier.fillMaxWidth()) {
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
                    .background(ToggleBg, RoundedCornerShape(20.dp))
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
            WeekLabels.forEach { w ->
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
            CheckInViewMode.MONTH -> CheckInMonthGrid(yearMonth = yearMonth, tasksByDate = tasksByDate)
            CheckInViewMode.WEEK -> {
                val monday = MineViewModel.mondayOfWeekContaining(weekCursor)
                CheckInWeekRow(monday = monday, tasksByDate = tasksByDate)
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = LocalizedStrings.get("mine_checkin_legend"),
            color = SubGray,
            fontSize = 11.sp,
            lineHeight = 15.sp
        )
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
            .background(bg, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 6.dp)
    )
}

@Composable
private fun CheckInMonthGrid(
    yearMonth: YearMonth,
    tasksByDate: Map<LocalDate, List<Task>>,
) {
    val firstDow = yearMonth.atDay(1).dayOfWeek.value
    val leading = firstDow - 1
    val daysInMonth = yearMonth.lengthOfMonth()
    val totalSlots = leading + daysInMonth
    val rows = (totalSlots + 6) / 7
    val today = LocalDate.now()
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
                        CheckInDayCell(
                            dayNum = dayNum,
                            tier = dailyCheckInTierForDay(tasksByDate[date].orEmpty()),
                            isToday = date == today,
                            modifier = Modifier.weight(1f)
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
    tasksByDate: Map<LocalDate, List<Task>>,
) {
    val today = LocalDate.now()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        for (i in 0 until 7) {
            val date = monday.plusDays(i.toLong())
            CheckInDayCell(
                dayNum = date.dayOfMonth,
                tier = dailyCheckInTierForDay(tasksByDate[date].orEmpty()),
                isToday = date == today,
                modifier = Modifier.weight(1f),
                dimDateIfOtherMonth = false
            )
        }
    }
}

@Composable
private fun CheckInDayCell(
    dayNum: Int,
    tier: DailyCheckInTier,
    isToday: Boolean,
    modifier: Modifier = Modifier,
    dimDateIfOtherMonth: Boolean = true,
) {
    val hasTasks = tier != DailyCheckInTier.NO_TASKS
    val titleColor = when {
        !dimDateIfOtherMonth -> TitleColor
        hasTasks -> TitleColor
        else -> SubGray
    }
    Column(
        modifier = modifier
            .height(54.dp)
            .then(
                if (isToday) Modifier.border(2.dp, TodayBorder, RoundedCornerShape(6.dp))
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
        when (tier) {
            DailyCheckInTier.NO_TASKS -> Text(
                text = "—",
                fontSize = EmptyMarkSize,
                color = SubGray.copy(alpha = 0.75f),
                modifier = Modifier.padding(top = 2.dp)
            )
            else -> Text(
                text = tier.emoji(),
                fontSize = EmojiSize,
                modifier = Modifier.padding(top = 0.dp)
            )
        }
    }
}
