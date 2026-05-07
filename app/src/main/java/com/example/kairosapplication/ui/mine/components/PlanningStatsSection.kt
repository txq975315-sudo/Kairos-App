package com.example.kairosapplication.ui.mine.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.taskmodel.model.WeeklyInsights

private val CardWhite = Color(0xFFFFFFFF)
private val TitleSans = Color(0xFF1A1A1A)
private val BlockStreakTint = Color(0xFFEDE7F6)
private val BlockTotalSurface = Color(0xFFFFFFFF)
private val BlockTotalBorder = Color(0xFFE6E6EA)
private val LabelCaps = Color(0xFF424242)
private val SwitchBlue = Color(0xFF2196F3)
private val SwitchThumbOff = Color(0xFFE0E0E0)

@Composable
fun PlanningStatsSection(
    insights: WeeklyInsights,
    enabled: Boolean,
    onToggleEnabled: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val planningHeading = LocalizedStrings.get("mine_planning_heading")
    val shareLabel = LocalizedStrings.get("share")
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color.Black.copy(alpha = 0.06f),
                spotColor = Color.Black.copy(alpha = 0.08f)
            )
            .background(CardWhite, RoundedCornerShape(20.dp))
            .border(1.dp, Color(0xFFE8E8EC), RoundedCornerShape(20.dp))
            .padding(horizontal = 18.dp, vertical = 18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = LocalizedStrings.get("mine_planning_heading"),
                color = TitleSans,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Serif,
                lineHeight = 22.sp,
                modifier = Modifier.weight(1f)
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
        Spacer(Modifier.height(12.dp))
        if (!enabled) {
            Text(
                text = LocalizedStrings.get("insights_off_today"),
                color = LabelCaps.copy(alpha = 0.7f),
                fontSize = 13.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatBlock(
                    value = insights.consecutiveDays,
                    caption = LocalizedStrings.get("mine_stat_days_in_a_row"),
                    background = BlockStreakTint,
                    outlined = false,
                    modifier = Modifier.weight(1f)
                )
                StatBlock(
                    value = insights.daysWithRecord,
                    caption = LocalizedStrings.get("mine_stat_total_days"),
                    background = BlockTotalSurface,
                    outlined = true,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val body = buildString {
                            append(planningHeading)
                            append("\n")
                            append(insights.consecutiveDays)
                            append(" · ")
                            append(insights.daysWithRecord)
                        }
                        val send = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, body)
                        }
                        context.startActivity(Intent.createChooser(send, shareLabel))
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = null,
                    tint = TitleSans.copy(alpha = 0.65f),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.size(6.dp))
                Text(
                    text = LocalizedStrings.get("share"),
                    color = TitleSans.copy(alpha = 0.65f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }
}

@Composable
private fun StatBlock(
    value: Int,
    caption: String,
    background: Color,
    outlined: Boolean,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(16.dp)
    Column(
        modifier = modifier
            .clip(shape)
            .then(
                if (outlined) {
                    Modifier
                        .border(1.dp, BlockTotalBorder, shape)
                        .background(background, shape)
                } else {
                    Modifier.background(background, shape)
                }
            )
            .padding(vertical = 20.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value.toString(),
            color = TitleSans,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = caption,
            color = LabelCaps,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 0.4.sp,
            lineHeight = 14.sp
        )
    }
}
