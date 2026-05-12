package com.example.kairosapplication.ui.mine.components

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
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
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.mine.records.MineRecordsMetric
import com.example.kairosapplication.ui.mine.records.MineRecordsOverview
import kotlin.math.max

private val GradientStart = Color(0xFF9F8CF7)
private val GradientEnd = Color(0xFFC8BCFA)
private val OnGradient = Color.White
private val OnGradientMuted = Color.White.copy(alpha = 0.82f)

private val MinMetricCellWidth = 52.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MineRecordsMetricsCard(
    overview: MineRecordsOverview,
    metrics: List<MineRecordsMetric>,
    modifier: Modifier = Modifier,
    /** Whole-card tap (e.g. open hub) when per-metric taps are not used. */
    onClick: (() -> Unit)? = null,
    /** Per-metric tap: cycle metric in pool (index in [metrics] list). */
    onMetricIndexClick: ((Int) -> Unit)? = null,
    onMetricsLongPress: (() -> Unit)? = null,
) {
    val ctx = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val list = metrics.distinct()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(AppShapes.CardRadius),
                ambientColor = Color.Black.copy(alpha = 0.12f),
                spotColor = Color.Black.copy(alpha = 0.12f)
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(GradientStart, GradientEnd),
                    start = Offset(0f, 0f),
                    end = Offset(600f, 280f)
                ),
                shape = RoundedCornerShape(AppShapes.CardRadius)
            )
            .then(
                if (onClick != null && onMetricIndexClick == null) {
                    if (onMetricsLongPress != null) {
                        Modifier.combinedClickable(
                            onClick = onClick,
                            onLongClick = onMetricsLongPress,
                        )
                    } else {
                        Modifier.clickable(onClick = onClick)
                    }
                } else {
                    Modifier
                },
            )
            .padding(vertical = 22.dp, horizontal = 8.dp)
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val maxPerRow = max(1, (maxWidth / MinMetricCellWidth).toInt())
            val useTwoRows = list.size > maxPerRow
            val chunks: List<List<MineRecordsMetric>> = if (!useTwoRows) {
                listOf(list)
            } else {
                val mid = (list.size + 1) / 2
                listOf(list.take(mid), list.drop(mid))
            }
            var indexOffset = 0
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                chunks.forEach { row ->
                    MetricsMetricRow(
                        rowMetrics = row,
                        overview = overview,
                        lang = lang,
                        ctx = ctx,
                        globalIndexOffset = indexOffset,
                        onMetricIndexClick = onMetricIndexClick,
                        onMetricsLongPress = onMetricsLongPress,
                    )
                    indexOffset += row.size
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MetricsMetricRow(
    rowMetrics: List<MineRecordsMetric>,
    overview: MineRecordsOverview,
    lang: LocalizationManager.Language,
    ctx: Context,
    globalIndexOffset: Int,
    onMetricIndexClick: ((Int) -> Unit)?,
    onMetricsLongPress: (() -> Unit)?,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        rowMetrics.forEachIndexed { index, metric ->
            if (index > 0) {
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(52.dp)
                        .background(OnGradient.copy(alpha = 0.45f))
                )
            }
            val globalIndex = globalIndexOffset + index
            val cellModifier = if (onMetricIndexClick != null) {
                Modifier.combinedClickable(
                    onClick = { onMetricIndexClick(globalIndex) },
                    onLongClick = { onMetricsLongPress?.invoke() },
                )
            } else {
                Modifier
            }
            Box(modifier = Modifier.weight(1f)) {
                GradientMetricCell(
                    valueText = formatMineMetricValue(overview, metric, lang, ctx),
                    label = LocalizedStrings.stringFor(lang, metric.labelKey(), ctx),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp)
                        .then(cellModifier),
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
            fontFamily = FontFamily.SansSerif,
            maxLines = 1,
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = label,
            color = OnGradientMuted,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp,
            maxLines = 2,
        )
    }
}

private fun formatMineMetricValue(
    overview: MineRecordsOverview,
    metric: MineRecordsMetric,
    lang: LocalizationManager.Language,
    context: Context,
): String {
    val raw = overview.rawDisplay(metric)
    return when (metric) {
        MineRecordsMetric.STREAK_RECORD,
        MineRecordsMetric.STREAK_ALL_TASKS_DONE,
        MineRecordsMetric.STREAK_BOTH_MODULES,
        MineRecordsMetric.MAX_STREAK_RECORD,
        MineRecordsMetric.LONGEST_ALL_TASKS_STREAK,
        MineRecordsMetric.LONGEST_BOTH_STREAK,
        MineRecordsMetric.PEAK_CONTINUITY_STREAK,
        -> {
            val n = raw.toIntOrNull() ?: 0
            if (n == 1) LocalizedStrings.stringFor(lang, "view_streak_one", context)
            else LocalizedStrings.stringFor(lang, "view_streak_many", context, n)
        }
        else -> raw
    }
}
