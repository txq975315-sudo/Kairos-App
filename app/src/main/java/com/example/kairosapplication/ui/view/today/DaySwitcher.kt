package com.example.kairosapplication.ui.view.today

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

/**
 * Day navigation row matching [com.example.kairosapplication.ui.view.week.WeekSwitcher] (icon buttons + centered caption).
 */
@Composable
fun DaySwitcher(
    focusedDate: LocalDate,
    viewingCalendarToday: Boolean,
    onPreviousDay: () -> Unit,
    onNextDay: () -> Unit,
    onJumpToToday: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lang = LocalCurrentLanguage.current.value
    val formatter = remember(lang) {
        when (lang) {
            LocalizationManager.Language.ZH ->
                DateTimeFormatter.ofPattern("yyyy年M月d日", Locale.CHINA)
            LocalizationManager.Language.EN ->
                DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy", Locale.ENGLISH)
        }
    }
    val suffix = if (viewingCalendarToday) LocalizedStrings.get("view_day_today") else ""
    val label = focusedDate.format(formatter) + suffix
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onPreviousDay,
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
                text = label,
                color = AppColors.HintText,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable(onClick = onJumpToToday),
            )
        }
        IconButton(
            onClick = onNextDay,
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
