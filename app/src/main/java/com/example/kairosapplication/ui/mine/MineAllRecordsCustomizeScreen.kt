package com.example.kairosapplication.ui.mine

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.core.ui.CommonBackButton
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.mine.components.MineRecordsMetricsCard
import com.example.kairosapplication.ui.mine.records.MineRecordsMetric
import com.example.kairosapplication.ui.mine.records.MineRecordsScope
import java.time.LocalDate

@Composable
fun MineAllRecordsCustomizeScreen(
    mineViewModel: MineViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val overview by mineViewModel.mineRecordsOverview.collectAsState()
    val metrics by mineViewModel.mineRecordsMetrics.collectAsState()
    val scope by mineViewModel.mineRecordsScope.collectAsState()
    val year by mineViewModel.mineRecordsYear.collectAsState()

    val scroll = rememberScrollState()
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundColor)
                .padding(horizontal = 4.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommonBackButton(onClick = onBack)
            Text(
                text = LocalizedStrings.get("mine_records_customize_title"),
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            TextButton(onClick = onBack) {
                Text(LocalizedStrings.get("view_month_customize_done"))
            }
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scroll)
                .padding(horizontal = 18.dp, vertical = 16.dp)
        ) {
            Text(
                text = LocalizedStrings.get("mine_records_scope_heading"),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ScopeChip(
                    label = LocalizedStrings.get("mine_records_scope_all"),
                    selected = scope == MineRecordsScope.ALL_TIME,
                    onClick = { mineViewModel.setMineRecordsScope(MineRecordsScope.ALL_TIME) },
                )
                ScopeChip(
                    label = LocalizedStrings.get("mine_records_scope_year"),
                    selected = scope == MineRecordsScope.THIS_YEAR,
                    onClick = { mineViewModel.setMineRecordsScope(MineRecordsScope.THIS_YEAR) },
                )
            }
            if (scope == MineRecordsScope.THIS_YEAR) {
                Spacer(Modifier.height(12.dp))
                Text(
                    text = LocalizedStrings.get("mine_records_year_heading"),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = {
                            val next = (year - 1).coerceAtLeast(minYear)
                            mineViewModel.setMineRecordsYear(next)
                        },
                        enabled = year > minYear
                    ) {
                        Text("‹")
                    }
                    Text(
                        text = year.toString(),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    TextButton(
                        onClick = {
                            val next = (year + 1).coerceAtMost(currentYear)
                            mineViewModel.setMineRecordsYear(next)
                        },
                        enabled = year < currentYear
                    ) {
                        Text("›")
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = LocalizedStrings.get("mine_records_shown_heading"),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = LocalizedStrings.get("mine_records_shown_max_hint"),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.85f),
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
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = null)
                    }
                    IconButton(
                        onClick = { mineViewModel.reorderMineMetrics(index, (index + 1).coerceAtMost(shown.lastIndex)) },
                        enabled = index < shown.lastIndex,
                    ) {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                    }
                    Text(
                        text = LocalizedStrings.get(metric.labelKey()),
                        fontSize = 15.sp,
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                    )
                    TextButton(
                        onClick = { removeAt(index) },
                        enabled = shown.size > 1,
                    ) {
                        Text(LocalizedStrings.get("mine_records_remove_metric"))
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            Text(
                text = LocalizedStrings.get("mine_records_pool_heading"),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
                        color = if (shown.size < 8) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
            Text(
                text = LocalizedStrings.get("mine_records_preview_heading"),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(10.dp))
            MineRecordsMetricsCard(
                overview = overview,
                metrics = shown,
                modifier = Modifier.fillMaxWidth(),
                onClick = null,
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ScopeChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val borderColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
    }
    val bg = if (selected) {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
    } else {
        MaterialTheme.colorScheme.surface
    }
    Text(
        text = label,
        fontSize = 14.sp,
        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .background(bg, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    )
}
