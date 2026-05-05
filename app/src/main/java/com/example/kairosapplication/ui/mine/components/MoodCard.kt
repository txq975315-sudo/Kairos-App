package com.example.kairosapplication.ui.mine.components

import android.os.Build
import android.view.HapticFeedbackConstants
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.mine.EmojiConstants
import com.example.kairosapplication.ui.mine.MineViewModel
import com.example.taskmodel.model.MoodRecord
import java.time.LocalDate
import kotlin.math.hypot
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

private val CardSurface = Color(0xFFF8F8F8)
private val CardStroke = Color.Black.copy(alpha = 0.2f)
private val TitleColor = Color(0xFF1A1A1A)
private val SubGray = Color(0xFF9E9E9E)
private val DividerMine = Color(0xFFE8E5E0)
private val Green = Color(0xFF4CAF50)
private val LinkBlue = Color(0xFF2196F3)
private val RingGray = Color(0xFFE0E0E0)
private val EmptyDayFill = Color(0xFFF0F0F0)

@Composable
fun MoodCard(
    todayMood: MoodRecord?,
    weekMoods: List<MoodRecord>,
    onMoodSelected: (LocalDate, String) -> Unit,
    onViewHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    val view = LocalView.current
    val density = LocalDensity.current
    val hoverPadPx = with(density) { 48.dp.toPx() }
    val dropPadPx = with(density) { 12.dp.toPx() }
    val scope = rememberCoroutineScope()
    var cardRoot by remember { mutableStateOf(Offset.Zero) }
    val dayBounds = remember { mutableStateMapOf<LocalDate, Rect>() }
    var hoveredDate by remember { mutableStateOf<LocalDate?>(null) }
    var draggingIcon by remember { mutableStateOf<String?>(null) }
    var dragFingerRoot by remember { mutableStateOf(Offset.Zero) }
    var dragTotal by remember { mutableFloatStateOf(0f) }
    val floatScale = remember { Animatable(1f) }
    var floatAlpha by remember { mutableFloatStateOf(1f) }
    val weekMap = remember(weekMoods) { weekMoods.associateBy { it.date } }
    val monday = remember { MineViewModel.mondayOfWeekContaining(LocalDate.now()) }
    val weekDates = remember(monday) { (0..6).map { monday.plusDays(it.toLong()) } }
    val weekLabels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    val today = LocalDate.now()

    fun rectContainsPad(r: Rect, p: Offset, pad: Float): Boolean {
        return p.x >= r.left - pad && p.x <= r.right + pad &&
            p.y >= r.top - pad && p.y <= r.bottom + pad
    }

    fun hitHoverDate(p: Offset): LocalDate? {
        for (d in weekDates) {
            val r = dayBounds[d] ?: continue
            if (rectContainsPad(r, p, hoverPadPx)) return d
        }
        return null
    }

    fun hitDropDate(p: Offset): LocalDate? {
        for (d in weekDates) {
            val r = dayBounds[d] ?: continue
            if (rectContainsPad(r, p, dropPadPx)) return d
        }
        return null
    }

    fun performConfirm() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
        } else {
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coords ->
                cardRoot = coords.positionInRoot()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(12.dp),
                    ambientColor = Color.Black.copy(alpha = 0.12f),
                    spotColor = Color.Black.copy(alpha = 0.12f)
                )
                .background(CardSurface, RoundedCornerShape(12.dp))
                .border(0.5.dp, CardStroke, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text(
                text = LocalizedStrings.get("my_mood"),
                color = TitleColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 0.5.dp,
                color = DividerMine
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = LocalizedStrings.get("today_mood"),
                color = SubGray,
                fontSize = 12.sp
            )
            Spacer(Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFE8E8E8), RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    EmojiConstants.CUSTOM_EMOJIS.forEach { emoji ->
                        val selected = todayMood?.moodIcon == emoji.id
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    if (selected) Green.copy(alpha = 0.1f) else Color.Transparent
                                )
                                .then(
                                    if (selected) Modifier.border(2.dp, Green, RoundedCornerShape(8.dp))
                                    else Modifier
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            DraggableCustomEmojiItem(
                                emojiItem = emoji,
                                modifier = Modifier.size(36.dp),
                                onDragStart = { root ->
                                    draggingIcon = emoji.id
                                    dragFingerRoot = root
                                    dragTotal = 0f
                                    hoveredDate = null
                                    scope.launch { floatScale.snapTo(0.8f) }
                                    floatAlpha = 0.7f
                                },
                                onDrag = { delta ->
                                    dragTotal += hypot(delta.x, delta.y)
                                    dragFingerRoot += delta
                                    val nh = hitHoverDate(dragFingerRoot)
                                    if (nh != hoveredDate) {
                                        hoveredDate = nh
                                        if (nh != null) {
                                            view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                                        }
                                    }
                                },
                                onDragEnd = { endRoot ->
                                    dragFingerRoot = endRoot
                                    val hit = hitDropDate(endRoot)
                                    val id = draggingIcon
                                    if (id != null && hit != null) {
                                        performConfirm()
                                        scope.launch {
                                            floatScale.animateTo(1.2f, spring())
                                            floatScale.animateTo(1f, spring())
                                        }
                                        onMoodSelected(hit, id)
                                    } else if (id != null && hit == null && dragTotal < 24f) {
                                        onMoodSelected(today, id)
                                    }
                                    draggingIcon = null
                                    dragTotal = 0f
                                    floatAlpha = 1f
                                    hoveredDate = null
                                    scope.launch { floatScale.snapTo(1f) }
                                },
                                onDragCancel = {
                                    draggingIcon = null
                                    dragTotal = 0f
                                    floatAlpha = 1f
                                    hoveredDate = null
                                    scope.launch { floatScale.snapTo(1f) }
                                }
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Text(
                text = LocalizedStrings.get("this_week"),
                color = SubGray,
                fontSize = 12.sp
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                weekDates.forEachIndexed { index, date ->
                    val record = weekMap[date]
                    val has = record != null
                    val isToday = date == today
                    val isFuture = date.isAfter(today)
                    val labelColor = if (isFuture) SubGray.copy(alpha = 0.45f) else SubGray
                    val slotColor = if (isFuture) SubGray.copy(alpha = 0.4f) else SubGray
                    MoodDragTarget(
                        targetDate = date,
                        onDragEnter = {},
                        onDragExit = {},
                        onDrop = { _, _ -> },
                        modifier = Modifier.weight(1f),
                        isHighlighted = hoveredDate == date,
                        onBoundsChanged = { d, rect -> dayBounds[d] = rect }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = weekLabels[index],
                                color = labelColor,
                                fontSize = 10.sp
                            )
                            Spacer(Modifier.height(6.dp))
                            if (has) {
                                Box(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .then(
                                            if (isToday) Modifier.border(2.dp, Green, CircleShape)
                                            else Modifier
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    MoodStoredIcon(
                                        moodIcon = record!!.moodIcon,
                                        imageSize = if (isToday) 26.dp else 22.dp,
                                        textSize = if (isToday) 20.sp else 16.sp
                                    )
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                        .background(EmptyDayFill)
                                        .border(1.dp, RingGray, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "+",
                                        color = slotColor,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = LocalizedStrings.get("view_history"),
                color = LinkBlue,
                fontSize = 12.sp,
                modifier = Modifier
                    .clickable { onViewHistory() }
                    .padding(vertical = 4.dp)
            )
        }
        if (draggingIcon != null) {
            val rel = dragFingerRoot - cardRoot
            val dragMod = Modifier
                .align(Alignment.TopStart)
                .offset {
                    IntOffset(rel.x.roundToInt(), rel.y.roundToInt())
                }
                .scale(floatScale.value)
                .alpha(floatAlpha)
                .shadow(6.dp, CircleShape)
            val item = EmojiConstants.emojiById(draggingIcon!!)
            if (item != null) {
                Image(
                    painter = painterResource(id = item.drawableResId),
                    contentDescription = LocalizedStrings.emojiLabel(item.id),
                    contentScale = ContentScale.Fit,
                    modifier = dragMod.size(32.dp)
                )
            } else {
                Text(
                    text = draggingIcon!!,
                    fontSize = 28.sp,
                    modifier = dragMod
                )
            }
        }
    }
}
