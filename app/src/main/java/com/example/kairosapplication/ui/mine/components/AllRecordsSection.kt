package com.example.kairosapplication.ui.mine.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.mine.records.CheckInViewMode
import com.example.kairosapplication.ui.mine.records.MineRecordsMetric
import com.example.kairosapplication.ui.mine.records.MineRecordsOverview
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Task

private val CheckInCardStroke = Color(0xFFE8E8EC)

@Composable
fun AllRecordsSection(
    overview: MineRecordsOverview,
    metrics: List<MineRecordsMetric>,
    tasks: List<Task>,
    publishedNotes: List<Note>,
    checkInViewMode: CheckInViewMode,
    onCheckInViewModeChange: (CheckInViewMode) -> Unit,
    onOpenAllRecords: () -> Unit,
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
            modifier = Modifier.padding(bottom = 6.dp),
        )
        MineRecordsMetricsCard(
            overview = overview,
            metrics = metrics.ifEmpty { MineRecordsMetric.defaultSelection() },
            modifier = Modifier.fillMaxWidth(),
            onClick = onCustomizeClick,
        )
        Spacer(Modifier.height(AppSpacing.GlassSectionStack))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(AppShapes.FeaturePanelRadius),
                    ambientColor = Color.Black.copy(alpha = 0.06f),
                    spotColor = Color.Black.copy(alpha = 0.08f),
                )
                .background(AppColors.GlassFill, RoundedCornerShape(AppShapes.FeaturePanelRadius))
                .border(1.dp, CheckInCardStroke, RoundedCornerShape(AppShapes.FeaturePanelRadius))
                .padding(horizontal = 18.dp, vertical = 18.dp),
        ) {
            CheckInStatsSection(
                tasks = tasks,
                publishedNotes = publishedNotes,
                viewMode = checkInViewMode,
                onViewModeChange = onCheckInViewModeChange,
                showHeadingRow = true,
                onDayClick = null,
                showMonthSummaryFooter = false,
                onViewAllRecordsClick = onOpenAllRecords,
            )
        }
    }
}
