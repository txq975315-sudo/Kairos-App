package com.example.kairosapplication.ui.mine

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import com.example.kairosapplication.ui.mine.components.CheckInStatsSection
import com.example.kairosapplication.ui.mine.components.MineRecordsMetricCustomizeSection
import com.example.kairosapplication.ui.mine.components.MineRecordsMetricsCard
import com.example.kairosapplication.ui.mine.records.MineRecordsMetric
import com.example.kairosapplication.ui.mine.records.MineRecordsScope
import com.example.kairosapplication.ui.mine.settings.rememberSettingsChrome
import com.example.taskmodel.constants.NoteStatus
import java.time.LocalDate

enum class MineAllRecordsTab {
    Calendar,
    Stats,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MineAllRecordsScreen(
    mineViewModel: MineViewModel,
    onBack: () -> Unit,
    onGoTodayForTasks: () -> Unit,
    modifier: Modifier = Modifier,
    initialTab: MineAllRecordsTab = MineAllRecordsTab.Calendar,
) {
    var mainTab by remember(initialTab) { mutableStateOf(initialTab) }
    var sheetDay by remember { mutableStateOf<LocalDate?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val tasks by mineViewModel.tasks.collectAsState()
    val publishedNotes by mineViewModel.publishedNotes.collectAsState()
    val checkInViewMode by mineViewModel.checkInViewMode.collectAsState()
    val overview by mineViewModel.mineRecordsOverview.collectAsState()
    val metrics by mineViewModel.mineRecordsMetrics.collectAsState()
    val scope by mineViewModel.mineRecordsScope.collectAsState()
    val scroll = rememberScrollState()
    val chrome = rememberSettingsChrome()
    val scopeAccent = Color(0xFF5B7CFA)

    MineDetailScaffold(
        title = LocalizedStrings.get("mine_all_records_title"),
        onBack = onBack,
        modifier = modifier,
    ) { padding ->
        val cardText = LocalGlassTextColors.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            TabRow(
                selectedTabIndex = mainTab.ordinal,
                containerColor = chrome.pageBackground,
                contentColor = chrome.title,
                indicator = { tabPositions ->
                    if (mainTab.ordinal < tabPositions.size) {
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[mainTab.ordinal]),
                            color = scopeAccent,
                        )
                    }
                },
                divider = {},
            ) {
                Tab(
                    selected = mainTab == MineAllRecordsTab.Calendar,
                    onClick = { mainTab = MineAllRecordsTab.Calendar },
                    text = {
                        Text(
                            LocalizedStrings.get("mine_all_records_tab_calendar"),
                            color = if (mainTab == MineAllRecordsTab.Calendar) {
                                chrome.title
                            } else {
                                chrome.subtitle
                            },
                        )
                    },
                )
                Tab(
                    selected = mainTab == MineAllRecordsTab.Stats,
                    onClick = { mainTab = MineAllRecordsTab.Stats },
                    text = {
                        Text(
                            LocalizedStrings.get("mine_all_records_tab_stats"),
                            color = if (mainTab == MineAllRecordsTab.Stats) {
                                chrome.title
                            } else {
                                chrome.subtitle
                            },
                        )
                    },
                )
            }
            when (mainTab) {
                MineAllRecordsTab.Calendar -> {
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
                MineAllRecordsTab.Stats -> {
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
                            color = cardText.muted,
                        )
                        Spacer(Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            MineRecordsScopeChip(
                                label = LocalizedStrings.get("mine_records_scope_all"),
                                selected = scope == MineRecordsScope.ALL_TIME,
                                onClick = { mineViewModel.setMineRecordsScope(MineRecordsScope.ALL_TIME) },
                            )
                            MineRecordsScopeChip(
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
                        )
                        Spacer(Modifier.height(20.dp))
                        MineRecordsMetricCustomizeSection(mineViewModel = mineViewModel)
                        Spacer(Modifier.height(24.dp))
                    }
                }
            }
        }

        val day = sheetDay
        if (day != null) {
            val today = LocalDate.now()
            val dayTasks = tasks.filter { it.taskDate == day }
            val dayNotes = publishedNotes.filter {
                it.status == NoteStatus.PUBLISHED && it.recordedDate == day
            }
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
                        color = cardText.primary,
                    )
                    Spacer(Modifier.height(10.dp))
                    if (day.isAfter(today)) {
                        if (dayTasks.isEmpty()) {
                            Text(
                                LocalizedStrings.get("mine_all_records_future_no_tasks"),
                                color = cardText.secondary,
                            )
                        } else {
                            Text(
                                LocalizedStrings.get("mine_all_records_future_tasks_preview"),
                                color = cardText.secondary,
                            )
                            dayTasks.take(8).forEach { t ->
                                Text(
                                    "· ${t.title} ${if (t.isCompleted) "✓" else ""}",
                                    fontSize = 14.sp,
                                    color = cardText.primary,
                                )
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                        TextButton(onClick = {
                            sheetDay = null
                            onGoTodayForTasks()
                        }) {
                            Text(
                                LocalizedStrings.get("mine_all_records_add_task_shortcut"),
                                color = scopeAccent,
                            )
                        }
                    } else {
                        val done = dayTasks.count { it.isCompleted }
                        val total = dayTasks.size
                        Text(
                            LocalizedStrings.get("mine_all_records_day_tasks_line")
                                .replace("{done}", done.toString())
                                .replace("{total}", total.toString()),
                            color = cardText.secondary,
                        )
                        Text(
                            LocalizedStrings.get("mine_all_records_day_notes_line")
                                .replace("{n}", dayNotes.size.toString()),
                            color = cardText.secondary,
                        )
                        val tags = dayNotes.flatMap { it.sceneTags }.distinct().take(6)
                        if (tags.isNotEmpty()) {
                            Spacer(Modifier.height(8.dp))
                            Text(tags.joinToString(" · "), fontSize = 13.sp, color = cardText.primary)
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}
