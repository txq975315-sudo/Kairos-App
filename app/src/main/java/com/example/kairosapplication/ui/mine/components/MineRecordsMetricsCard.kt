package com.example.kairosapplication.ui.mine.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.mine.records.MineRecordsMetric
import com.example.kairosapplication.ui.mine.records.MineRecordsOverview

private val GradientStart = Color(0xFF9F8CF7)
private val GradientEnd = Color(0xFFC8BCFA)
private val OnGradient = Color.White
private val OnGradientMuted = Color.White.copy(alpha = 0.82f)

@Composable
fun MineRecordsMetricsCard(
    overview: MineRecordsOverview,
    metrics: List<MineRecordsMetric>,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    val lang = LocalCurrentLanguage.current.value
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(18.dp),
                ambientColor = Color.Black.copy(alpha = 0.12f),
                spotColor = Color.Black.copy(alpha = 0.12f)
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(GradientStart, GradientEnd),
                    start = Offset(0f, 0f),
                    end = Offset(600f, 280f)
                ),
                shape = RoundedCornerShape(18.dp)
            )
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(vertical = 22.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            metrics.distinct().forEachIndexed { index, metric ->
                if (index > 0) {
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(52.dp)
                            .background(OnGradient.copy(alpha = 0.45f))
                    )
                }
                GradientMetricCell(
                    valueText = formatMineMetricValue(overview, metric, lang),
                    label = LocalizedStrings.stringFor(lang, metric.labelKey()),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
        }
    }
}

@Composable
private fun GradientMetricCell(
    valueText: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = valueText,
            color = OnGradient,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = label,
            color = OnGradientMuted,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}

private fun formatMineMetricValue(
    overview: MineRecordsOverview,
    metric: MineRecordsMetric,
    lang: LocalizationManager.Language,
): String {
    val raw = overview.rawDisplay(metric)
    return when (metric) {
        MineRecordsMetric.STREAK_RECORD,
        MineRecordsMetric.STREAK_ALL_TASKS_DONE,
        MineRecordsMetric.STREAK_BOTH_MODULES,
        MineRecordsMetric.MAX_STREAK_RECORD,
        -> {
            val n = raw.toIntOrNull() ?: 0
            if (n == 1) LocalizedStrings.stringFor(lang, "view_streak_one")
            else LocalizedStrings.stringFor(lang, "view_streak_many").replace("{n}", n.toString())
        }
        else -> raw
    }
}
