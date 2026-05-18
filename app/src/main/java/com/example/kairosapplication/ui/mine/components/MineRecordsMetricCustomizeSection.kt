package com.example.kairosapplication.ui.mine.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import com.example.kairosapplication.ui.mine.MineViewModel
import com.example.kairosapplication.ui.mine.records.MineRecordsMetric
import com.example.kairosapplication.ui.mine.records.MineRecordsScope
import java.time.LocalDate

@Composable
fun MineRecordsMetricCustomizeSection(
    mineViewModel: MineViewModel,
    modifier: Modifier = Modifier,
) {
    val metrics by mineViewModel.mineRecordsMetrics.collectAsState()
    val scope by mineViewModel.mineRecordsScope.collectAsState()
    val year by mineViewModel.mineRecordsYear.collectAsState()
    val cardText = LocalGlassTextColors.current
    val accent = Color(0xFF5B7CFA)
    val currentYear = LocalDate.now().year
    val minYear = (currentYear - 5).coerceAtLeast(2020)
    val shown = metrics.ifEmpty { MineRecordsMetric.defaultSelection() }
    val pool = enumValues<MineRecordsMetric>().filter { it !in shown }

    fun addMetric(m: MineRecordsMetric) {
        val cur = mineViewModel.mineRecordsMetrics.value.ifEmpty { MineRecordsMetric.defaultSelection() }
        if (cur.size >= 8 || m in cur) return
        mineViewModel.setMineRecordsMetrics(cur + m)
    }

    fun removeAt(index: Int) {
        val cur = mineViewModel.mineRecordsMetrics.value.ifEmpty { MineRecordsMetric.defaultSelection() }
        if (cur.size <= 1) return
        mineViewModel.setMineRecordsMetrics(cur.filterIndexed { i, _ -> i != index })
    }

    Column(modifier = modifier.fillMaxWidth()) {
        if (scope == MineRecordsScope.THIS_YEAR) {
            Text(
                text = LocalizedStrings.get("mine_records_year_heading"),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = cardText.muted,
            )
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                TextButton(
                    onClick = {
                        val next = (year - 1).coerceAtLeast(minYear)
                        mineViewModel.setMineRecordsYear(next)
                    },
                    enabled = year > minYear,
                ) {
                    Text("‹", color = cardText.primary)
                }
                Text(
                    text = year.toString(),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = cardText.primary,
                )
                TextButton(
                    onClick = {
                        val next = (year + 1).coerceAtMost(currentYear)
                        mineViewModel.setMineRecordsYear(next)
                    },
                    enabled = year < currentYear,
                ) {
                    Text("›", color = cardText.primary)
                }
            }
            Spacer(Modifier.height(16.dp))
        }

        Text(
            text = LocalizedStrings.get("mine_records_shown_heading"),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = cardText.muted,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = LocalizedStrings.get("mine_records_shown_max_hint"),
            fontSize = 12.sp,
            color = cardText.muted.copy(alpha = 0.85f),
        )
        Spacer(Modifier.height(8.dp))
        shown.forEachIndexed { index, metric ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = { mineViewModel.reorderMineMetrics(index, (index - 1).coerceAtLeast(0)) },
                    enabled = index > 0,
                ) {
                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = null, tint = cardText.primary)
                }
                IconButton(
                    onClick = {
                        mineViewModel.reorderMineMetrics(index, (index + 1).coerceAtMost(shown.lastIndex))
                    },
                    enabled = index < shown.lastIndex,
                ) {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = cardText.primary)
                }
                Text(
                    text = LocalizedStrings.get(metric.labelKey()),
                    fontSize = 15.sp,
                    color = cardText.primary,
                    modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                )
                TextButton(
                    onClick = { removeAt(index) },
                    enabled = shown.size > 1,
                ) {
                    Text(LocalizedStrings.get("mine_records_remove_metric"), color = accent)
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        Text(
            text = LocalizedStrings.get("mine_records_pool_heading"),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = cardText.muted,
        )
        Spacer(Modifier.height(8.dp))
        pool.forEach { metric ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = shown.size < 8) { addMetric(metric) }
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "+ ${LocalizedStrings.get(metric.labelKey())}",
                    fontSize = 15.sp,
                    color = if (shown.size < 8) accent else cardText.muted.copy(alpha = 0.5f),
                )
            }
        }
    }
}
