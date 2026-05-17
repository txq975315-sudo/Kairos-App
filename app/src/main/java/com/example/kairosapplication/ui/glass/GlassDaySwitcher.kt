package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.clickable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Glass-style day switcher with large date display.
 *
 * Design reference: "Titles use large bold serif, subtitle uses
 * gray-weakened medium-thin font."
 *
 * - Date: White 100%, large bold
 * - "Today" suffix: White 50%
 * - Arrows: GlassIconButton style
 */
@Composable
fun GlassDaySwitcher(
    currentDate: LocalDate,
    onPreviousDay: () -> Unit,
    onNextDay: () -> Unit,
    onJumpToToday: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val lang = LocalCurrentLanguage.current.value
    val viewingToday = currentDate == LocalDate.now()
    val formatter = remember(lang) {
        when (lang) {
            LocalizationManager.Language.ZH ->
                DateTimeFormatter.ofPattern("M月d日", Locale.CHINA)
            LocalizationManager.Language.EN ->
                DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH)
        }
    }
    val suffix = if (viewingToday) LocalizedStrings.get("view_day_today") else ""
    val dateLabel = currentDate.format(formatter) + suffix

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Previous day arrow
        GlassIconButton(
            icon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = LocalizedStrings.get("cd_prev_day"),
            onClick = onPreviousDay,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = dateLabel,
            color = GlassConstants.TextPrimary,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.3).sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .clickable(onClick = onJumpToToday),
        )

        GlassIconButton(
            icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = LocalizedStrings.get("cd_next_day"),
            onClick = onNextDay,
        )
    }
}
