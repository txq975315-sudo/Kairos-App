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
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalizedStrings
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

    fun toggleMetric(metric: MineRecordsMetric) {
        val next = metrics.toMutableSet()
        if (metric in next) {
            if (next.size > 1) next.remove(metric)
        } else {
            next.add(metric)
        }
        mineViewModel.setMineRecordsMetrics(next.toList())
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = LocalizedStrings.get("back"))
            }
            Text(
                text = LocalizedStrings.get("mine_records_customize_title"),
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
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
                    label = LocalizedStrings.get("mine_records_scope_year"),
                    selected = scope == MineRecordsScope.THIS_YEAR,
                    onClick = { mineViewModel.setMineRecordsScope(MineRecordsScope.THIS_YEAR) }
                )
                ScopeChip(
                    label = LocalizedStrings.get("mine_records_scope_all"),
                    selected = scope == MineRecordsScope.ALL_TIME,
                    onClick = { mineViewModel.setMineRecordsScope(MineRecordsScope.ALL_TIME) }
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
                text = LocalizedStrings.get("mine_records_metrics_heading"),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(8.dp))

            enumValues<MineRecordsMetric>().forEach { metric ->
                val checked = metric in metrics
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .toggleable(
                            value = checked,
                            role = Role.Checkbox,
                            onValueChange = { toggleMetric(metric) }
                        )
                        .padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = checked, onCheckedChange = { toggleMetric(metric) })
                    Text(
                        text = LocalizedStrings.get(metric.labelKey()),
                        fontSize = 15.sp,
                        modifier = Modifier.padding(start = 8.dp)
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
                metrics = metrics.ifEmpty { MineRecordsMetric.defaultSelection() },
                modifier = Modifier.fillMaxWidth(),
                onClick = null
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
