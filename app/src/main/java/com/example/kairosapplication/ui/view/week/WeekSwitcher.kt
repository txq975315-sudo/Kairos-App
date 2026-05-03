package com.example.kairosapplication.ui.view.week

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

private val WeekSwitcherMuted = Color(0xFF9E9E9E)
private val RangeFormatter = DateTimeFormatter.ofPattern("MMM d", Locale.US)

@Composable
fun WeekSwitcher(
    currentWeekRange: Pair<LocalDate, LocalDate>,
    viewingCurrentWeek: Boolean,
    onPreviousWeek: () -> Unit,
    onNextWeek: () -> Unit,
    onResetToCurrentWeek: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (start, end) = currentWeekRange
    val rangeText = buildString {
        append(start.format(RangeFormatter))
        append(" - ")
        append(end.format(RangeFormatter))
        if (viewingCurrentWeek) {
            append(" · This week")
        }
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onPreviousWeek,
            modifier = Modifier.size(40.dp),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = WeekSwitcherMuted,
                modifier = Modifier.size(20.dp),
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = rangeText,
                color = WeekSwitcherMuted,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable(onClick = onResetToCurrentWeek),
            )
        }
        IconButton(
            onClick = onNextWeek,
            modifier = Modifier.size(40.dp),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = WeekSwitcherMuted,
                modifier = Modifier.size(20.dp),
            )
        }
    }
}
