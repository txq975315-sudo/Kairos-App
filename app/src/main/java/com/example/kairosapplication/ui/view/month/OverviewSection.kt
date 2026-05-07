package com.example.kairosapplication.ui.view.month

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.view.Overview

@Composable
fun OverviewSection(
    overview: Overview,
    metrics: List<MonthOverviewMetric>,
    onCustomizeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lang = LocalCurrentLanguage.current.value
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onCustomizeClick)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = LocalizedStrings.stringFor(lang, "view_month_summary"),
                    color = AppColors.HintText,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(
                text = "›",
                color = AppColors.HintText,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 4.dp),
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 0.5.dp,
            color = AppColors.Divider,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            metrics.distinct().forEachIndexed { index, metric ->
                if (index > 0) {
                    OverviewVerticalDivider()
                }
                OverviewMetricCell(
                    valueText = formatOverviewMetricValue(overview, metric, lang),
                    label = LocalizedStrings.stringFor(lang, metric.labelKey()),
                    modifier = Modifier.widthIn(min = 76.dp),
                )
            }
        }
    }
}

@Composable
private fun OverviewMetricCell(
    valueText: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = valueText,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.PrimaryText,
            textAlign = TextAlign.Center,
            maxLines = 2,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Normal,
            color = AppColors.HintText,
            textAlign = TextAlign.Center,
            maxLines = 2,
        )
    }
}

private fun formatOverviewMetricValue(
    overview: Overview,
    metric: MonthOverviewMetric,
    lang: LocalizationManager.Language,
): String {
    val raw = overview.displayValue(metric)
    return when (metric) {
        MonthOverviewMetric.STREAK_RECORD,
        MonthOverviewMetric.STREAK_ALL_TASKS_DONE,
        MonthOverviewMetric.STREAK_BOTH_MODULES,
        -> {
            val n = raw.toIntOrNull() ?: 0
            if (n == 1) LocalizedStrings.stringFor(lang, "view_streak_one")
            else LocalizedStrings.stringFor(lang, "view_streak_many").replace("{n}", n.toString())
        }
        else -> raw
    }
}

@Composable
private fun OverviewVerticalDivider() {
    Box(
        modifier = Modifier
            .width(0.5.dp)
            .height(40.dp)
            .padding(horizontal = 6.dp)
            .background(AppColors.Divider),
    )
}
