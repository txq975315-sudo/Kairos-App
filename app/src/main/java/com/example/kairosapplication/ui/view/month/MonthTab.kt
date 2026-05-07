package com.example.kairosapplication.ui.view.month

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.ui.view.ViewUiState
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun MonthTab(
    uiState: ViewUiState,
    yearMonth: YearMonth,
    onDateClick: (LocalDate) -> Unit,
    onMonthChange: (YearMonth) -> Unit,
    monthOverviewMetrics: List<MonthOverviewMetric>,
    onMonthOverviewMetricsChange: (List<MonthOverviewMetric>) -> Unit,
    modifier: Modifier = Modifier,
) {
    var customizeOverviewOpen by remember { mutableStateOf(false) }
    val scroll = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scroll),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            color = AppColors.CardBackground,
            shadowElevation = 2.dp,
        ) {
            MonthCalendar(
                yearMonth = yearMonth,
                calendarData = uiState.monthCalendarData,
                onDateClick = onDateClick,
                onMonthChange = onMonthChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            color = AppColors.CardBackground,
            shadowElevation = 2.dp,
        ) {
            OverviewSection(
                overview = uiState.monthOverview,
                metrics = monthOverviewMetrics,
                onCustomizeClick = { customizeOverviewOpen = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }

    if (customizeOverviewOpen) {
        MonthOverviewCustomizeSheet(
            selected = monthOverviewMetrics,
            onDismiss = { customizeOverviewOpen = false },
            onConfirm = {
                onMonthOverviewMetricsChange(it)
                customizeOverviewOpen = false
            },
        )
    }
}
