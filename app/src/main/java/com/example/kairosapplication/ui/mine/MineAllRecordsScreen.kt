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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.core.ui.CommonBackButton
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.mine.components.CheckInStatsSection
import com.example.kairosapplication.ui.mine.components.MineRecordsMetricsCard
import com.example.kairosapplication.ui.mine.records.MineRecordsMetric
import com.example.kairosapplication.ui.mine.records.MineRecordsScope
import com.example.taskmodel.constants.NoteStatus
import java.time.LocalDate

private enum class RecordsMainTab { Calendar, Stats }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MineAllRecordsScreen(
    mineViewModel: MineViewModel,
    onBack: () -> Unit,
    onOpenCustomize: () -> Unit,
    onGoTodayForTasks: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var mainTab by remember { mutableStateOf(RecordsMainTab.Calendar) }
    var sheetDay by remember { mutableStateOf<LocalDate?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val tasks by mineViewModel.tasks.collectAsState()
    val publishedNotes by mineViewModel.publishedNotes.collectAsState()
    val checkInViewMode by mineViewModel.checkInViewMode.collectAsState()
    val overview by mineViewModel.mineRecordsOverview.collectAsState()
    val metrics by mineViewModel.mineRecordsMetrics.collectAsState()
    val scope by mineViewModel.mineRecordsScope.collectAsState()
    val scroll = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .statusBarsPadding(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundColor)
                .padding(horizontal = 4.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CommonBackButton(onClick = onBack)
            Text(
                text = LocalizedStrings.get("mine_all_records_title"),
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f),
            )
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f))
        TabRow(selectedTabIndex = mainTab.ordinal) {
            Tab(
                selected = mainTab == RecordsMainTab.Calendar,
                onClick = { mainTab = RecordsMainTab.Calendar },
                text = { Text(LocalizedStrings.get("mine_all_records_tab_calendar")) },
            )
            Tab(
                selected = mainTab == RecordsMainTab.Stats,
                onClick = { mainTab = RecordsMainTab.Stats },
                text = { Text(LocalizedStrings.get("mine_all_records_tab_stats")) },
            )
        }
        when (mainTab) {
            RecordsMainTab.Calendar -> {
                CheckInStatsSection(
                    tasks = tasks,
                    publishedNotes = publishedNotes,
                    viewMode = checkInViewMode,
                    onViewModeChange = { mineViewModel.setCheckInViewMode(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    showHeadingRow = true,
                    onDayClick = { sheetDay = it },
                    showMonthSummaryFooter = true,
                )
            }
            RecordsMainTab.Stats -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scroll)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                ) {
                    Text(
                        text = LocalizedStrings.get("mine_records_scope_heading"),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                    Spacer(Modifier.height(16.dp))
                    MineRecordsMetricsCard(
                        overview = overview,
                        metrics = metrics.ifEmpty { MineRecordsMetric.defaultSelection() },
                        modifier = Modifier.fillMaxWidth(),
                        onMetricIndexClick = { idx -> mineViewModel.cycleMetricAtDisplayIndex(idx) },
                        onMetricsLongPress = onOpenCustomize,
                    )
                    Spacer(Modifier.height(12.dp))
                    TextButton(onClick = onOpenCustomize, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(LocalizedStrings.get("mine_all_records_customize_metrics"))
                    }
                }
            }
        }
    }

    val day = sheetDay
    if (day != null) {
        val today = LocalDate.now()
        val dayTasks = tasks.filter { it.taskDate == day }
        val dayNotes = publishedNotes.filter { it.status == NoteStatus.PUBLISHED && it.recordedDate == day }
        ModalBottomSheet(
            onDismissRequest = { sheetDay = null },
            sheetState = sheetState,
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
            ) {
                Text(
                    text = day.toString(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                )
                Spacer(Modifier.height(10.dp))
                if (day.isAfter(today)) {
                    if (dayTasks.isEmpty()) {
                        Text(LocalizedStrings.get("mine_all_records_future_no_tasks"))
                    } else {
                        Text(LocalizedStrings.get("mine_all_records_future_tasks_preview"))
                        dayTasks.take(8).forEach { t ->
                            Text("· ${t.title} ${if (t.isCompleted) "✓" else ""}", fontSize = 14.sp)
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    TextButton(onClick = {
                        sheetDay = null
                        onGoTodayForTasks()
                    }) {
                        Text(LocalizedStrings.get("mine_all_records_add_task_shortcut"))
                    }
                } else {
                    val done = dayTasks.count { it.isCompleted }
                    val total = dayTasks.size
                    Text(
                        LocalizedStrings.get("mine_all_records_day_tasks_line")
                            .replace("{done}", done.toString())
                            .replace("{total}", total.toString()),
                    )
                    Text(
                        LocalizedStrings.get("mine_all_records_day_notes_line")
                            .replace("{n}", dayNotes.size.toString()),
                    )
                    val tags = dayNotes.flatMap { it.sceneTags }.distinct().take(6)
                    if (tags.isNotEmpty()) {
                        Spacer(Modifier.height(8.dp))
                        Text(tags.joinToString(" · "), fontSize = 13.sp)
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
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
            .border(1.dp, borderColor, RoundedCornerShape(AppShapes.FeaturePanelRadius))
            .background(bg, RoundedCornerShape(AppShapes.FeaturePanelRadius))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp),
    )
}
