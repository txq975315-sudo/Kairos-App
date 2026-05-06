package com.example.kairosapplication.ui.view.month

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.view.Overview

@Composable
fun OverviewSection(
    overview: Overview,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = LocalizedStrings.get("view_month_summary"),
                color = AppColors.HintText,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(4.dp))
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
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OverviewMetricCell(
                valueText = overview.totalNotes.toString(),
                label = LocalizedStrings.get("view_metric_notes"),
                modifier = Modifier.weight(1f),
            )
            OverviewVerticalDivider()
            OverviewMetricCell(
                valueText = overview.totalTasks.toString(),
                label = LocalizedStrings.get("view_metric_tasks"),
                modifier = Modifier.weight(1f),
            )
            OverviewVerticalDivider()
            OverviewMetricCell(
                valueText = "${overview.completionRate}%",
                label = LocalizedStrings.get("view_metric_done"),
                modifier = Modifier.weight(1f),
            )
            OverviewVerticalDivider()
            OverviewMetricCell(
                valueText = if (overview.consecutiveDays == 1) {
                    LocalizedStrings.get("view_streak_one")
                } else {
                    LocalizedStrings.get("view_streak_many").replace("{n}", overview.consecutiveDays.toString())
                },
                label = LocalizedStrings.get("view_metric_streak"),
                modifier = Modifier.weight(1f),
            )
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
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = valueText,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.PrimaryText,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Normal,
            color = AppColors.HintText,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun OverviewVerticalDivider() {
    Box(
        modifier = Modifier
            .width(0.5.dp)
            .height(40.dp)
            .background(AppColors.Divider),
    )
}
