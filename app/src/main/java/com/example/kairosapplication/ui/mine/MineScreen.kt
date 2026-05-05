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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import com.example.kairosapplication.ui.mine.components.AllRecordsSection
import com.example.kairosapplication.ui.mine.components.MoodCard
import com.example.kairosapplication.ui.mine.components.ProfileCard
import com.example.kairosapplication.ui.mine.components.ProfileEditSheet
import com.example.kairosapplication.ui.mine.components.WeeklyInsightsSection
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.theme.BackgroundColor

private val NicknameTopBarColor = Color(0xFF1A1A1A)
private val LinkBlue = Color(0xFF2196F3)

@Composable
fun MineScreen(
    mineViewModel: MineViewModel,
    onNavigateToMoodCalendar: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val profile by mineViewModel.profileState.collectAsState()
    val recordDays by mineViewModel.recordDays.collectAsState()
    val allRecords by mineViewModel.allRecords.collectAsState()
    val weeklyInsights by mineViewModel.weeklyInsights.collectAsState()
    val weekDaysWithRecord by mineViewModel.weekDaysWithRecord.collectAsState()
    val weeklyInsightsEnabled by mineViewModel.weeklyInsightsEnabled.collectAsState()
    val todayMood by mineViewModel.todayMood.collectAsState()
    val weekMoods by mineViewModel.weekMoods.collectAsState()
    var showEditSheet by remember { mutableStateOf(false) }
    val scroll = rememberScrollState()
    val openEdit = { showEditSheet = true }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = profile.displayName.ifBlank { LocalizedStrings.get("user_nickname") },
                    color = NicknameTopBarColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable(onClick = openEdit)
                )
                Text(
                    text = LocalizedStrings.get("mine_settings"),
                    color = LinkBlue,
                    fontSize = 15.sp,
                    modifier = Modifier.clickable(onClick = onOpenSettings)
                )
            }
            Spacer(Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scroll)
            ) {
                ProfileCard(
                    profile = profile,
                    recordDays = recordDays,
                    onClick = openEdit
                )
                Spacer(Modifier.height(16.dp))
                AllRecordsSection(
                    completedTasks = allRecords.completedTasks,
                    uncompletedTasks = allRecords.uncompletedTasks,
                    todayCompletedTasks = allRecords.todayCompletedTasks
                )
                Spacer(Modifier.height(16.dp))
                WeeklyInsightsSection(
                    insights = weeklyInsights,
                    weekDaysWithRecord = weekDaysWithRecord,
                    onToggleEnabled = { mineViewModel.toggleWeeklyInsights(it) },
                    enabled = weeklyInsightsEnabled
                )
                Spacer(Modifier.height(16.dp))
                MoodCard(
                    todayMood = todayMood,
                    weekMoods = weekMoods,
                    onMoodSelected = { date: LocalDate, icon: String ->
                        mineViewModel.saveMood(icon, date)
                    },
                    onViewHistory = onNavigateToMoodCalendar
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
