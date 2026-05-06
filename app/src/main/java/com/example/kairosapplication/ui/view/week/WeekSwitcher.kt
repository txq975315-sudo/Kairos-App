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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun WeekSwitcher(
    currentWeekRange: Pair<LocalDate, LocalDate>,
    viewingCurrentWeek: Boolean,
    onPreviousWeek: () -> Unit,
    onNextWeek: () -> Unit,
    onResetToCurrentWeek: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lang = LocalCurrentLanguage.current.value
    val locale = if (lang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.US
    val rangeFormatter = remember(lang) {
        DateTimeFormatter.ofPattern("MMM d", locale)
    }
    val (start, end) = currentWeekRange
    val rangeText = buildString {
        append(start.format(rangeFormatter))
        append(" – ")
        append(end.format(rangeFormatter))
        if (viewingCurrentWeek) {
            append(LocalizedStrings.get("view_week_this_week"))
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
                tint = AppColors.IconNeutral,
                modifier = Modifier.size(20.dp),
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = rangeText,
                color = AppColors.HintText,
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
                tint = AppColors.IconNeutral,
                modifier = Modifier.size(20.dp),
            )
        }
    }
}
