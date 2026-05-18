package com.example.kairosapplication.ui.view.month

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
import com.example.kairosapplication.ui.view.LocalViewChrome
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Month navigation row aligned with [com.example.kairosapplication.ui.view.today.DaySwitcher]
 * and [com.example.kairosapplication.ui.view.week.WeekSwitcher] (icon buttons + centered caption).
 */
@Composable
fun MonthSwitcher(
    yearMonth: YearMonth,
    viewingCurrentMonth: Boolean,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onJumpToCurrentMonth: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lang = LocalCurrentLanguage.current.value
    val monthFmt = remember(lang) {
        when (lang) {
            LocalizationManager.Language.ZH ->
                DateTimeFormatter.ofPattern("yyyy年M月", Locale.CHINA)
            LocalizationManager.Language.EN ->
                DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)
        }
    }
    val suffix = if (viewingCurrentMonth) LocalizedStrings.get("view_month_this_month") else ""
    val label = yearMonth.atDay(1).format(monthFmt) + suffix
    val chrome = LocalViewChrome.current
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onPreviousMonth,
            modifier = Modifier.size(40.dp),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = chrome.icon,
                modifier = Modifier.size(20.dp),
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = label,
                color = chrome.secondary,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable(onClick = onJumpToCurrentMonth),
            )
        }
        IconButton(
            onClick = onNextMonth,
            modifier = Modifier.size(40.dp),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = chrome.icon,
                modifier = Modifier.size(20.dp),
            )
        }
    }
}
