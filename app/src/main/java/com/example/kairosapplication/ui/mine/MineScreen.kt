package com.example.kairosapplication.ui.mine

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import com.example.kairosapplication.ui.mine.components.AllRecordsSection
import com.example.kairosapplication.ui.mine.components.MoodCard
import com.example.kairosapplication.ui.mine.components.PlanningStatsSection
import com.example.kairosapplication.ui.mine.components.ProfileCard
import com.example.kairosapplication.ui.mine.components.ProfileEditSheet
import com.example.kairosapplication.i18n.LocalizedStrings

private val MineScreenBg = Color(0xFFF3F3F5)
private val MoonTint = Color(0xFFFFC107)
private val SettingsIconTint = Color(0xFF616161)

@Composable
fun MineScreen(
    mineViewModel: MineViewModel,
    onNavigateToMoodCalendar: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenTheme: () -> Unit,
    onCustomizeAllRecords: () -> Unit,
    modifier: Modifier = Modifier
) {
    val profile by mineViewModel.profileState.collectAsState()
    val recordDays by mineViewModel.recordDays.collectAsState()
    val mineRecordsOverview by mineViewModel.mineRecordsOverview.collectAsState()
    val mineRecordsMetrics by mineViewModel.mineRecordsMetrics.collectAsState()
    val checkInViewMode by mineViewModel.checkInViewMode.collectAsState()
    val tasks by mineViewModel.tasks.collectAsState()
    val weeklyInsights by mineViewModel.weeklyInsights.collectAsState()
    val weeklyInsightsEnabled by mineViewModel.weeklyInsightsEnabled.collectAsState()
    val todayMood by mineViewModel.todayMood.collectAsState()
    val weekMoods by mineViewModel.weekMoods.collectAsState()
    var showEditSheet by remember { mutableStateOf(false) }
    val scroll = rememberScrollState()
    val openEdit = { showEditSheet = true }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MineScreenBg)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
        ) {
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .shadow(2.dp, RoundedCornerShape(50))
                        .clip(RoundedCornerShape(50))
                        .background(Color.White)
                        .clickable(onClick = onOpenTheme),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.DarkMode,
                        contentDescription = null,
                        tint = MoonTint,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .shadow(2.dp, CircleShape)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable(onClick = onOpenSettings),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = LocalizedStrings.get("mine_settings"),
                        tint = SettingsIconTint,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scroll)
            ) {
                Text(
                    text = LocalizedStrings.get("weekly_insights"),
                    color = Color(0xFF1A1A1A),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                MoodCard(
                    todayMood = todayMood,
                    weekMoods = weekMoods,
                    onMoodSelected = { date: LocalDate, icon: String ->
                        mineViewModel.saveMood(icon, date)
                    },
                    onViewHistory = onNavigateToMoodCalendar
                )
                Spacer(Modifier.height(20.dp))
                PlanningStatsSection(
                    insights = weeklyInsights,
                    enabled = weeklyInsightsEnabled,
                    onToggleEnabled = { mineViewModel.toggleWeeklyInsights(it) }
                )
                Spacer(Modifier.height(20.dp))
                AllRecordsSection(
                    overview = mineRecordsOverview,
                    metrics = mineRecordsMetrics,
                    tasks = tasks,
                    checkInViewMode = checkInViewMode,
                    onCheckInViewModeChange = { mineViewModel.setCheckInViewMode(it) },
                    onCustomizeClick = onCustomizeAllRecords
                )
                Spacer(Modifier.height(20.dp))
                ProfileCard(
                    profile = profile,
                    recordDays = recordDays,
                    onClick = openEdit
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
