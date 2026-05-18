package com.example.kairosapplication.ui.mine

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import com.example.kairosapplication.ui.mine.components.AllRecordsSection
import com.example.kairosapplication.ui.mine.components.MoodCard
import com.example.kairosapplication.ui.mine.components.PlanningStatsSection
import com.example.kairosapplication.ui.mine.components.ProfileEditSheet
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.mine.components.MineCardShell
import com.example.kairosapplication.ui.view.LocalViewChrome
import com.example.kairosapplication.ui.view.rememberViewChromeColors
import com.example.kairosapplication.ui.glass.LocalGlassTextColors

private val MoonTintClassic = Color(0xFFFFC107)

@Composable
fun MineScreen(
    mineViewModel: MineViewModel,
    onNavigateToMoodCalendar: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenTheme: () -> Unit,
    onOpenAllRecords: () -> Unit,
    onCustomizeAllRecords: () -> Unit,
    modifier: Modifier = Modifier
) {
    val profile by mineViewModel.profileState.collectAsState()
    val mineRecordsOverview by mineViewModel.mineRecordsOverview.collectAsState()
    val mineRecordsMetrics by mineViewModel.mineRecordsMetrics.collectAsState()
    val checkInViewMode by mineViewModel.checkInViewMode.collectAsState()
    val tasks by mineViewModel.tasks.collectAsState()
    val publishedNotes by mineViewModel.publishedNotes.collectAsState()
    val weeklyInsights by mineViewModel.weeklyInsights.collectAsState()
    val weeklyInsightsEnabled by mineViewModel.weeklyInsightsEnabled.collectAsState()
    val todayMood by mineViewModel.todayMood.collectAsState()
    val weekMoods by mineViewModel.weekMoods.collectAsState()
    var showEditSheet by remember { mutableStateOf(false) }
    val scroll = rememberScrollState()
    val openEdit = { showEditSheet = true }
    val nicknameMaxWidth = (LocalConfiguration.current.screenWidthDp / 2).dp
    val chrome = rememberViewChromeColors()
    val density = LocalDensity.current
    var nicknameChipHeight by remember { mutableStateOf(0.dp) }
    val topIconCircleSize = if (nicknameChipHeight > 0.dp) {
        nicknameChipHeight * (2f / 3f) * 1.25f
    } else {
        44.dp * 1.25f
    }

    CompositionLocalProvider(LocalViewChrome provides chrome) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MineCardShell(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .widthIn(max = nicknameMaxWidth)
                        .onSizeChanged { nicknameChipHeight = with(density) { it.height.toDp() } }
                        .clickable(onClick = openEdit),
                    shape = RoundedCornerShape(AppShapes.FeaturePanelRadius),
                    wrapHazeToContent = true,
                ) {
                    Box(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                    Text(
                        text = profile.displayName.ifBlank { LocalizedStrings.get("user_nickname") },
                        color = LocalGlassTextColors.current.primary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MineCardShell(
                        modifier = Modifier
                            .size(topIconCircleSize)
                            .clickable(onClick = onOpenTheme),
                        shape = CircleShape,
                        wrapHazeToContent = false,
                    ) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Filled.DarkMode,
                                contentDescription = LocalizedStrings.get("dark_mode"),
                                tint = if (LocalAppUiTheme.current == AppUiTheme.Glass) {
                                    chrome.icon
                                } else {
                                    MoonTintClassic
                                },
                                modifier = Modifier.size(topIconCircleSize * 0.52f),
                            )
                        }
                    }
                    MineCardShell(
                        modifier = Modifier
                            .size(topIconCircleSize)
                            .clickable(onClick = onOpenSettings),
                        shape = CircleShape,
                        wrapHazeToContent = false,
                    ) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = LocalizedStrings.get("mine_settings"),
                                tint = chrome.icon,
                                modifier = Modifier.size(topIconCircleSize * 0.52f),
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(18.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scroll)
            ) {
                Text(
                    text = LocalizedStrings.get("weekly_insights"),
                    color = chrome.primary,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Serif,
                    lineHeight = 34.sp,
                    modifier = Modifier.padding(bottom = 14.dp)
                )
                MoodCard(
                    todayMood = todayMood,
                    weekMoods = weekMoods,
                    onMoodSelected = { date: LocalDate, icon: String ->
                        mineViewModel.saveMood(icon, date)
                    },
                    onViewHistory = onNavigateToMoodCalendar
                )
                Spacer(Modifier.height(16.dp))
                AllRecordsSection(
                    overview = mineRecordsOverview,
                    metrics = mineRecordsMetrics,
                    tasks = tasks,
                    publishedNotes = publishedNotes,
                    checkInViewMode = checkInViewMode,
                    onCheckInViewModeChange = { mineViewModel.setCheckInViewMode(it) },
                    onOpenAllRecords = onOpenAllRecords,
                    onCustomizeClick = onCustomizeAllRecords,
                )
                Spacer(Modifier.height(24.dp))
                PlanningStatsSection(
                    insights = weeklyInsights,
                    enabled = weeklyInsightsEnabled,
                    onToggleEnabled = { mineViewModel.toggleWeeklyInsights(it) }
                )
                Spacer(Modifier.height(32.dp))
            }
        }
    }

    if (showEditSheet) {
        ProfileEditSheet(
            profile = profile,
            onDismiss = { showEditSheet = false },
            onSave = { updated ->
                mineViewModel.saveProfile(updated)
                showEditSheet = false
            }
        )
    }
    }
}
