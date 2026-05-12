package com.example.kairosapplication.ui.mine

import android.content.Intent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.CommonBackButton
import com.example.kairosapplication.i18n.weekShortHeadersMondayFirst
import com.example.kairosapplication.ui.mine.components.MoodStoredIcon
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.taskmodel.model.MoodRecord
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

private val TitleColor = Color(0xFF1A1A1A)
private val SubGray = Color(0xFF9E9E9E)
private val TodayBorder = Color(0xFF9E9E9E)
private val SelectedBg = Color(0xFFF0F4FF)
@Composable
fun MoodCalendarScreen(
    mineViewModel: MineViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val weekHeaders = remember(lang, context) { weekShortHeadersMondayFirst(context, lang) }
    val allMoods by mineViewModel.allMoods.collectAsState()
    var yearMonth by remember { mutableStateOf(YearMonth.now()) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val titleFmt = remember(lang) {
        if (lang == LocalizationManager.Language.ZH) {
            DateTimeFormatter.ofPattern("yyyy年M月", Locale.CHINA)
        } else {
            DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)
        }
    }
    val scroll = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundColor)
                .padding(horizontal = 4.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CommonBackButton(onClick = onBack)
            Text(
                text = LocalizedStrings.get("mood_record"),
                color = TitleColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            TextButton(onClick = {
                val snap = mineViewModel.monthMoodStatsSnapshot(yearMonth)
                val sep = if (lang == LocalizationManager.Language.ZH) "，" else ", "
                val times = LocalizedStrings.moodStatTimesFor(lang, context)
                val lines = snap.entries.sortedByDescending { e -> e.value }
                    .joinToString(sep) { e -> "${e.key}×${e.value}$times" }
                val summary = LocalizedStrings.moodShareSummaryFor(
                    context,
                    lang,
                    yearMonth.format(titleFmt),
                    lines
                )
                val send = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, summary)
                }
                context.startActivity(
                    Intent.createChooser(send, LocalizedStrings.stringFor(lang, "share", context))
                )
            }) {
                Text(LocalizedStrings.get("share"), color = Color(0xFF2196F3), fontSize = 14.sp)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { yearMonth = yearMonth.minusMonths(1) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = SubGray
                )
            }
            Text(
                text = yearMonth.format(titleFmt),
                color = SubGray,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            IconButton(onClick = { yearMonth = yearMonth.plusMonths(1) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = SubGray
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(scroll)
                .padding(horizontal = 12.dp)
        ) {
            AnimatedContent(
                targetState = yearMonth,
                transitionSpec = {
                    slideInHorizontally { it / 3 } togetherWith slideOutHorizontally { -it / 3 }
                },
                label = "monthAnim"
            ) { ym ->
                val moodsMap = remember(ym, allMoods) {
                    allMoods.filter { YearMonth.from(it.date) == ym }.associateBy { it.date }
                }
                val statsLocal = remember(ym, allMoods) {
                    mineViewModel.monthMoodStatsSnapshot(ym)
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        weekHeaders.forEach { w ->
                            Text(
                                text = w,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center,
                                color = SubGray,
                                fontSize = 11.sp
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    MonthGrid(
                        yearMonth = ym,
                        moodsByDate = moodsMap,
                        selectedDate = selectedDate,
                        onDayClick = { selectedDate = it }
                    )
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = LocalizedStrings.monthMoodDistributionTitle(ym.monthValue),
                        color = SubGray,
                        fontSize = 12.sp
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        statsLocal.entries.sortedByDescending { it.value }.forEach { (emojiId, count) ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                MoodStoredIcon(
                                    moodIcon = emojiId,
                                    imageSize = 18.dp,
                                    textSize = 14.sp
                                )
                                Text(
                                    text = "× $count",
                                    fontSize = 14.sp,
                                    color = TitleColor
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MonthGrid(
    yearMonth: YearMonth,
    moodsByDate: Map<LocalDate, MoodRecord>,
    selectedDate: LocalDate?,
    onDayClick: (LocalDate) -> Unit
) {
    val firstDow = yearMonth.atDay(1).dayOfWeek.value
    val leading = firstDow - 1
    val daysInMonth = yearMonth.lengthOfMonth()
    val totalSlots = leading + daysInMonth
    val rows = (totalSlots + 6) / 7
    val today = LocalDate.now()
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for (r in 0 until rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                for (c in 0 until 7) {
                    val idx = r * 7 + c
                    val dayNum = idx - leading + 1
                    if (idx < leading || dayNum > daysInMonth) {
                        Box(modifier = Modifier.weight(1f).height(52.dp))
                    } else {
                        val date = yearMonth.atDay(dayNum)
                        val mood = moodsByDate[date]
                        val has = mood != null
                        val isToday = date == today
                        val isSel = date == selectedDate
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .height(52.dp)
                                .then(
                                    if (isToday) Modifier.border(2.dp, TodayBorder, RoundedCornerShape(AppShapes.CompactRadius))
                                    else Modifier
                                )
                                .background(
                                    if (isSel) SelectedBg else Color.Transparent,
                                    RoundedCornerShape(AppShapes.CompactRadius)
                                )
                                .clickable { onDayClick(date) }
                                .padding(2.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = dayNum.toString(),
                                fontSize = 12.sp,
                                color = if (has) TitleColor else SubGray
                            )
                            mood?.let { m ->
                                MoodStoredIcon(
                                    moodIcon = m.moodIcon,
                                    imageSize = 18.dp,
                                    textSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
