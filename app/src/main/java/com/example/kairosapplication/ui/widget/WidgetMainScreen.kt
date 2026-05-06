package com.example.kairosapplication.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.widget.data.WidgetSize
import com.example.taskmodel.model.Task
import com.example.taskmodel.util.TaskUtils
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

private val WidgetScreenBg = Color(0xFFF2F2F2)
private val PreviewCardBg = Color.White
private val WidgetPurpleTrack = Color(0xFFE8DEF8)
private val WidgetPurpleProgress = Color(0xFF7B61FF)
private val WidgetPurpleAccent = Color(0xFF7B61FF)
private val TextPrimary = Color(0xFF1A1A1A)
private val TextMuted = Color(0xFF9E9E9E)
private val TextQuote = Color(0xFF333333)

@Composable
fun WidgetMainScreen(
    taskViewModel: TaskViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by taskViewModel.uiState.collectAsState()
    val today = LocalDate.now()
    val lang = LocalCurrentLanguage.current.value
    val isZh = lang == LocalizationManager.Language.ZH
    val todayTasks = remember(uiState.tasks, today) {
        uiState.tasks.filter { it.taskDate == today }
    }
    val sortedToday = remember(todayTasks) { TaskUtils.sortTasks(todayTasks) }
    val completed = todayTasks.count { it.isCompleted }
    val total = todayTasks.size
    val quote = taskViewModel.dailyQuoteDisplayText(
        if (isZh) "纵有疾风起，人生不言弃" else "Even if the gale rises, life does not give up."
    )
    val dayName = remember(today, isZh) {
        if (isZh) {
            DateTimeFormatter.ofPattern("EEEE", Locale.CHINA).format(today)
        } else {
            today.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        }
    }
    val dayNum = today.dayOfMonth.toString()
    val dateLineMedium = remember(today, isZh) {
        if (isZh) {
            DateTimeFormatter.ofPattern("MM-dd EEEE", Locale.CHINA).format(today)
        } else {
            DateTimeFormatter.ofPattern("MM-dd EEEE", Locale.ENGLISH).format(today)
        }
    }
    val previewTasks = remember(sortedToday) { sortedToday.take(5) }
    val previewTasks1b = remember(sortedToday) { sortedToday.take(3) }

    var selectedSizeKey by rememberSaveable { mutableStateOf(WidgetSize._1X1.name) }
    val selectedSize = WidgetSize.valueOf(selectedSizeKey)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WidgetScreenBg)
            .statusBarsPadding()
    ) {
        WidgetSizeTabRow(
            selected = selectedSize,
            onSelect = { selectedSizeKey = it.name }
        )
        HorizontalDivider(thickness = 0.5.dp, color = Color(0xFFE0E0E0))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            when (selectedSize) {
                WidgetSize._1X1 -> SmallPreviewsRow(
                    dayName = dayName,
                    dayNum = dayNum,
                    completed = completed,
                    total = total,
                    quote = quote,
                    tasks1b = previewTasks1b
                )
                WidgetSize._2X2 -> MediumPreviewCard(
                    dateLine = dateLineMedium,
                    completed = completed,
                    total = total,
                    quote = quote,
                    tasks = previewTasks,
                    isZh = isZh
                )
                WidgetSize._3X1 -> LargePreviewCard(
                    dayName = dayName,
                    dayNum = dayNum,
                    completed = completed,
                    total = total,
                    tasks = previewTasks.take(4)
                )
                WidgetSize._3X3 -> SuperLargePreviewCard(
                    today = today,
                    completed = completed,
                    total = total,
                    quote = quote,
                    tasks = previewTasks.take(5),
                    isZh = isZh
                )
            }
        }
    }
}

@Composable
private fun WidgetSizeTabRow(
    selected: WidgetSize,
    onSelect: (WidgetSize) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        WidgetSize.entries.forEach { size ->
            val label = when (size) {
                WidgetSize._1X1 -> LocalizedStrings.get("widget_main_tab_small")
                WidgetSize._2X2 -> LocalizedStrings.get("widget_main_tab_medium")
                WidgetSize._3X1 -> LocalizedStrings.get("widget_main_tab_large")
                WidgetSize._3X3 -> LocalizedStrings.get("widget_main_tab_super")
            }
            val isSelected = size == selected
            val interaction = remember { MutableInteractionSource() }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = interaction,
                        indication = null
                    ) { onSelect(size) }
                    .padding(vertical = 8.dp, horizontal = 4.dp)
            ) {
                Text(
                    text = label,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) TextPrimary else TextMuted,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Box(
                    modifier = Modifier
                        .height(3.dp)
                        .fillMaxWidth(0.55f)
                        .background(if (isSelected) TextPrimary else Color.Transparent)
                )
            }
        }
    }
}

@Composable
private fun SmallPreviewsRow(
    dayName: String,
    dayNum: String,
    completed: Int,
    total: Int,
    quote: String,
    tasks1b: List<Task>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Preview1ACard(
                dayName = dayName,
                dayNum = dayNum,
                completed = completed,
                total = total,
                quote = quote,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            Preview1BCard(
                completed = completed,
                total = total,
                quote = quote,
                tasks = tasks1b,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun Preview1ACard(
    dayName: String,
    dayNum: String,
    completed: Int,
    total: Int,
    quote: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .shadow(6.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(PreviewCardBg)
            .padding(12.dp)
            .aspectRatio(1f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(dayName, fontSize = 11.sp, color = TextMuted, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(dayNum, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        }
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val frac = if (total <= 0) 0f else (completed.toFloat() / total.toFloat()).coerceIn(0f, 1f)
            Box(contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.size(88.dp)) {
                    val stroke = 6.dp.toPx()
                    val diameter = size.minDimension
                    drawArc(
                        color = WidgetPurpleTrack,
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        topLeft = androidx.compose.ui.geometry.Offset.Zero,
                        size = Size(diameter, diameter),
                        style = Stroke(width = stroke, cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = WidgetPurpleProgress,
                        startAngle = -90f,
                        sweepAngle = 360f * frac,
                        useCenter = false,
                        topLeft = androidx.compose.ui.geometry.Offset.Zero,
                        size = Size(diameter, diameter),
                        style = Stroke(width = stroke, cap = StrokeCap.Round)
                    )
                }
                Text(
                    text = if (total > 0) "$completed / $total" else "0 / 0",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary
                )
            }
        }
        Text(
            text = quote,
            fontSize = 11.sp,
            color = TextQuote,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun Preview1BCard(
    completed: Int,
    total: Int,
    quote: String,
    tasks: List<Task>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .shadow(6.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(PreviewCardBg)
            .padding(12.dp)
            .aspectRatio(1f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (total > 0) "$completed/$total" else "0/0",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = WidgetPurpleAccent,
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            val rows = if (tasks.isEmpty()) {
                List(3) { null as Task? }
            } else {
                (tasks + List(3) { null }).take(3)
            }
            rows.forEach { task ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = task?.title?.takeIf { it.isNotBlank() } ?: "—",
                        fontSize = 12.sp,
                        color = TextPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    val ringColor = if (task == null) {
                        TextMuted
                    } else if (task.isCompleted) {
                        WidgetPurpleProgress
                    } else {
                        TaskUtils.getUrgencyColor(task.urgency)
                    }
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .border(2.dp, ringColor, CircleShape)
                    )
                }
            }
        }
        Text(
            text = quote,
            fontSize = 11.sp,
            color = TextQuote,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun MediumPreviewCard(
    dateLine: String,
    completed: Int,
    total: Int,
    quote: String,
    tasks: List<Task>,
    isZh: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(PreviewCardBg)
            .padding(16.dp)
    ) {
        Text(dateLine, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Spacer(Modifier.height(4.dp))
        Text(
            if (isZh) "已完成${completed}/总${total}" else "${completed} of ${total} done",
            fontSize = 12.sp,
            color = TextMuted
        )
        Spacer(Modifier.height(12.dp))
        tasks.take(5).forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    if (task.isCompleted) "✓" else "○",
                    fontSize = 12.sp,
                    color = if (task.isCompleted) WidgetPurpleProgress else TextMuted
                )
                Text(
                    task.title,
                    fontSize = 13.sp,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        if (tasks.isEmpty()) {
            Text(LocalizedStrings.get("widget_main_no_tasks"), fontSize = 13.sp, color = TextMuted)
        }
        Spacer(Modifier.height(12.dp))
        Text(quote, fontSize = 12.sp, color = TextMuted, maxLines = 2, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
private fun LargePreviewCard(
    dayName: String,
    dayNum: String,
    completed: Int,
    total: Int,
    tasks: List<Task>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(132.dp)
            .shadow(6.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(PreviewCardBg)
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(88.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(dayName, fontSize = 12.sp, color = TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(6.dp))
            val frac = if (total <= 0) 0f else (completed.toFloat() / total.toFloat()).coerceIn(0f, 1f)
            Box(contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.size(52.dp)) {
                    val stroke = 4.dp.toPx()
                    val d = size.minDimension
                    drawArc(
                        color = WidgetPurpleTrack,
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        topLeft = androidx.compose.ui.geometry.Offset.Zero,
                        size = Size(d, d),
                        style = Stroke(width = stroke, cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = WidgetPurpleProgress,
                        startAngle = -90f,
                        sweepAngle = 360f * frac,
                        useCenter = false,
                        topLeft = androidx.compose.ui.geometry.Offset.Zero,
                        size = Size(d, d),
                        style = Stroke(width = stroke, cap = StrokeCap.Round)
                    )
                }
                Text(
                    if (total > 0) "$completed/$total" else "0/0",
                    fontSize = 10.sp,
                    color = TextPrimary
                )
            }
        }
        Spacer(Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Text(dayNum, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Spacer(Modifier.height(4.dp))
            tasks.forEach { task ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        if (task.isCompleted) "✓" else "○",
                        fontSize = 11.sp,
                        color = if (task.isCompleted) WidgetPurpleProgress else TextMuted
                    )
                    Text(
                        task.title,
                        fontSize = 11.sp,
                        color = TextPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            if (tasks.isEmpty()) {
                Text(LocalizedStrings.get("widget_main_no_tasks"), fontSize = 11.sp, color = TextMuted)
            }
        }
    }
}

@Composable
private fun SuperLargePreviewCard(
    today: LocalDate,
    completed: Int,
    total: Int,
    quote: String,
    tasks: List<Task>,
    isZh: Boolean
) {
    val monthTitle = remember(today, isZh) {
        if (isZh) DateTimeFormatter.ofPattern("yyyy年M月", Locale.CHINA).format(today)
        else DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH).format(today)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(PreviewCardBg)
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(monthTitle, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Text("‹  ›", fontSize = 14.sp, color = TextMuted)
        }
        Spacer(Modifier.height(8.dp))
        Text(
            LocalizedStrings.get("widget_main_week_row"),
            fontSize = 10.sp,
            color = TextMuted
        )
        Spacer(Modifier.height(4.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            val firstOfMonth = today.withDayOfMonth(1)
            repeat(7) { i ->
                val d = firstOfMonth.plusDays(i.toLong())
                Text(
                    d.dayOfMonth.toString(),
                    fontSize = 10.sp,
                    color = if (d == today) WidgetPurpleProgress else TextPrimary,
                    modifier = Modifier.width(22.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(Modifier.height(12.dp))
        Text(LocalizedStrings.get("widget_main_today_tasks"), fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Spacer(Modifier.height(6.dp))
        tasks.forEach { task ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    if (task.isCompleted) "✓" else "○",
                    fontSize = 12.sp,
                    color = if (task.isCompleted) WidgetPurpleProgress else TextMuted
                )
                Text(task.title, fontSize = 12.sp, color = TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
        if (tasks.isEmpty()) {
            Text(LocalizedStrings.get("widget_main_no_tasks"), fontSize = 12.sp, color = TextMuted)
        }
        Spacer(Modifier.height(10.dp))
        Text(quote, fontSize = 11.sp, color = TextMuted, maxLines = 2, overflow = TextOverflow.Ellipsis)
    }
}
