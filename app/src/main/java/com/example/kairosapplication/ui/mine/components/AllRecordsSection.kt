package com.example.kairosapplication.ui.mine.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.mine.records.CheckInViewMode
import com.example.kairosapplication.ui.mine.records.MineRecordsMetric
import com.example.kairosapplication.ui.mine.records.MineRecordsOverview
import com.example.taskmodel.model.Task

@Composable
fun AllRecordsSection(
    overview: MineRecordsOverview,
    metrics: List<MineRecordsMetric>,
    tasks: List<Task>,
    checkInViewMode: CheckInViewMode,
    onCheckInViewModeChange: (CheckInViewMode) -> Unit,
    onCustomizeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = LocalizedStrings.get("mine_all_records_title"),
            color = Color(0xFF1A1A1A),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        MineRecordsMetricsCard(
            overview = overview,
            metrics = metrics.ifEmpty { MineRecordsMetric.defaultSelection() },
            modifier = Modifier.fillMaxWidth(),
            onClick = onCustomizeClick
        )
        Spacer(Modifier.height(14.dp))
        CheckInStatsSection(
            tasks = tasks,
            viewMode = checkInViewMode,
            onViewModeChange = onCheckInViewModeChange
        )
    }
}
