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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.view.Overview

private val Muted = Color(0xFF9E9E9E)
private val DividerC = Color(0xFFE8E5E0)
private val NumberC = Color(0xFF1A1A1A)

@Composable
fun OverviewSection(
    overview: Overview,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "This month",
                color = Muted,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 0.5.dp,
            color = DividerC,
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
                label = "Notes",
                modifier = Modifier.weight(1f),
            )
            OverviewVerticalDivider()
            OverviewMetricCell(
                valueText = overview.totalTasks.toString(),
                label = "Tasks",
                modifier = Modifier.weight(1f),
            )
            OverviewVerticalDivider()
            OverviewMetricCell(
                valueText = "${overview.completionRate}%",
                label = "Done",
                modifier = Modifier.weight(1f),
            )
            OverviewVerticalDivider()
            OverviewMetricCell(
                valueText = when (overview.consecutiveDays) {
                    1 -> "1 day"
                    else -> "${overview.consecutiveDays} days"
                },
                label = "Streak",
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
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = NumberC,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Normal,
            color = Muted,
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
            .background(DividerC),
    )
}
