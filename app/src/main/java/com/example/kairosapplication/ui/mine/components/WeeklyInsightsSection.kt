package com.example.kairosapplication.ui.mine.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.i18n.weekShortHeadersMondayFirst
import com.example.taskmodel.model.WeeklyInsights
import java.time.LocalDate

private val CardWhite = Color(0xFFFFFFFF)
private val TitleColor = Color(0xFF1A1A1A)
private val LabelGray = Color(0xFF9E9E9E)
private val DividerMine = Color(0xFFE8E5E0)
private val DotGreen = Color(0xFF4CAF50)
private val DotGray = Color(0xFFE0E0E0)
private val DotBgGray = Color(0xFFF5F5F5)
private val SwitchBlue = Color(0xFF2196F3)
private val SwitchThumbOff = Color(0xFFE0E0E0)

@Composable
fun WeeklyInsightsSection(
    insights: WeeklyInsights,
    weekDaysWithRecord: List<LocalDate>,
    onToggleEnabled: (Boolean) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val weekShortLabels = remember(lang, context) { weekShortHeadersMondayFirst(context, lang) }
    val monday = mondayOfWeekContaining(LocalDate.now())
    val daysInWeek = (0..6).map { monday.plusDays(it.toLong()) }
    val recordSet = weekDaysWithRecord.toSet()
    val today = LocalDate.now()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = Color.Black.copy(alpha = 0.08f),
                spotColor = Color.Black.copy(alpha = 0.08f)
            )
            .background(CardWhite, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = LocalizedStrings.get("weekly_insights"),
                color = TitleColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Switch(
                checked = enabled,
                onCheckedChange = onToggleEnabled,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = SwitchBlue,
                    uncheckedThumbColor = SwitchThumbOff,
                    uncheckedTrackColor = Color(0xFFBDBDBD),
                    uncheckedBorderColor = SwitchThumbOff
                )
            )
        }
        Spacer(Modifier.height(4.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 0.5.dp,
            color = DividerMine
        )
        Spacer(Modifier.height(16.dp))
        if (!enabled) {
            Text(
                text = LocalizedStrings.get("insights_off_today"),
                color = LabelGray,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                fontWeight = FontWeight.Normal
            )
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Top
            ) {
                daysInWeek.forEachIndexed { index, date ->
                    val hasRecord = recordSet.contains(date)
                    val isToday = date == today
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = weekShortLabels[index],
                            color = LabelGray,
                            fontSize = 10.sp
                        )
                        Spacer(Modifier.height(6.dp))
                        WeekDot(
                            hasRecord = hasRecord,
                            isToday = isToday
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${LocalizedStrings.get("consecutive_days")}${insights.consecutiveDays}${LocalizedStrings.get("consecutive_days_suffix")}",
                    color = LabelGray,
                    fontSize = 12.sp
                )
                Text(
                    text = "${LocalizedStrings.get("week_recorded_days")}${insights.daysWithRecord}${LocalizedStrings.get("week_recorded_days_suffix")}",
                    color = LabelGray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun WeekDot(
    hasRecord: Boolean,
    isToday: Boolean,
    modifier: Modifier = Modifier
) {
    val inner = if (hasRecord) DotGreen else DotGray
    if (isToday) {
        Box(
            modifier = modifier
                .size(18.dp)
                .border(2.dp, DotGreen, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(inner)
            )
        }
    } else {
        Box(
            modifier = modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(if (hasRecord) DotGreen else DotBgGray),
            contentAlignment = Alignment.Center
        ) {
            if (!hasRecord) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(DotGray)
                )
            }
        }
    }
}

private fun mondayOfWeekContaining(date: LocalDate): LocalDate =
    date.minusDays((date.dayOfWeek.value - 1).toLong())
