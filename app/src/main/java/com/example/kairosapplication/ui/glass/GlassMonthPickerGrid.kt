package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.buildCalendarDaysForMonth
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.weekShortHeadersMondayFirst
import com.example.kairosapplication.ui.components.ArrowButton
import com.example.kairosapplication.ui.components.ArrowDirection
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Lightweight month grid for [GlassPickerDialog] — avoids Material3 [DatePicker] crashes in Dialog.
 */
@Composable
fun GlassMonthPickerGrid(
    selectedDate: LocalDate,
    onSelectedDateChange: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    var visibleMonth by remember(selectedDate) { mutableStateOf(YearMonth.from(selectedDate)) }
    val monthLabel = remember(visibleMonth, lang) {
        val loc = when (lang) {
            LocalizationManager.Language.ZH -> Locale.forLanguageTag("zh-Hans-CN")
            LocalizationManager.Language.EN -> Locale.ENGLISH
        }
        visibleMonth.atDay(1).format(DateTimeFormatter.ofPattern("MMMM yyyy", loc))
    }
    val monthDays = remember(visibleMonth) { buildCalendarDaysForMonth(visibleMonth) }
    val weekTitles = remember(lang, context) { weekShortHeadersMondayFirst(context, lang) }
    val chrome = LocalGlassAtmosphereUi.current.topChrome
    val useLightChrome = !LocalGlassAtmosphereUi.current.zones.topIsLight

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            ArrowButton(
                onClick = { visibleMonth = visibleMonth.minusMonths(1) },
                direction = ArrowDirection.LEFT,
                size = 28.dp,
                tint = chrome.primary,
                contentDescription = null,
            )
            Text(
                text = monthLabel,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = chrome.primary,
                style = glassChromeTextStyle(
                    base = androidx.compose.ui.text.TextStyle.Default,
                    useLightText = useLightChrome,
                ),
            )
            ArrowButton(
                onClick = { visibleMonth = visibleMonth.plusMonths(1) },
                direction = ArrowDirection.RIGHT,
                size = 28.dp,
                tint = chrome.primary,
                contentDescription = null,
            )
        }

        Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
            weekTitles.forEach { title ->
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = chrome.secondary,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        Column(
            modifier = Modifier.padding(top = 6.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            monthDays.chunked(7).forEach { week ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    week.forEach { day ->
                        GlassPickerDayCell(
                            day = day,
                            selectedDate = selectedDate,
                            onDateSelected = onSelectedDateChange,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.GlassPickerDayCell(
    day: LocalDate?,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
) {
    val chrome = LocalGlassAtmosphereUi.current.topChrome
    val isSelected = day == selectedDate
    val selectionFill = chrome.primary.copy(alpha = if (LocalGlassAtmosphereUi.current.zones.topIsLight) 0.14f else 0.28f)

    Box(
        modifier = Modifier
            .weight(1f)
            .height(36.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (day != null) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) selectionFill else Color.Transparent)
                    .clickable { onDateSelected(day) },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = day.dayOfMonth.toString(),
                    color = if (isSelected) chrome.primary else chrome.secondary,
                    fontSize = 14.sp,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                )
            }
        }
    }
}
