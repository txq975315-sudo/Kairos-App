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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.i18n.weekShortHeadersMondayFirst
import com.example.kairosapplication.ui.mine.EmojiConstants
import com.example.kairosapplication.ui.mine.MineViewModel
import com.example.taskmodel.model.MoodRecord
import java.time.LocalDate
import kotlin.math.hypot
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

private val CardSurface = Color(0xFFFFFFFF)
private val CardStroke = Color(0xFFE8E8EC)
private val TitleColor = Color(0xFF1A1A1A)
private val SubGray = Color(0xFF9E9E9E)
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
    val context = LocalContext.current
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
    val lang = LocalCurrentLanguage.current.value
    val weekLabels = remember(lang, context) { weekShortHeadersMondayFirst(context, lang) }
    val monday = remember { MineViewModel.mondayOfWeekContaining(LocalDate.now()) }
    val weekDates = remember(monday) { (0..6).map { monday.plusDays(it.toLong()) } }
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
                    elevation = 5.dp,
                    shape = RoundedCornerShape(20.dp),
                    ambientColor = Color.Black.copy(alpha = 0.06f),
                    spotColor = Color.Black.copy(alpha = 0.08f)
                )
                .background(CardSurface, RoundedCornerShape(20.dp))
                .border(1.dp, CardStroke, RoundedCornerShape(20.dp))
                .padding(horizontal = 18.dp, vertical = 18.dp)
        ) {
            Text(
                text = LocalizedStrings.get("mine_mood_card_title"),
                color = TitleColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Serif,
                lineHeight = 22.sp
            )
            Spacer(Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(108.dp)
                    .padding(vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                val clusterW = 256.dp
                val clusterH = 102.dp
                Box(
                    modifier = Modifier
                        .width(clusterW)
                        .height(clusterH)
                ) {
                    EmojiConstants.CUSTOM_EMOJIS.forEachIndexed { i, emoji ->
                        val (xo, yo) = moodEmojiClusterSlotsScaled().getOrNull(i) ?: return@forEachIndexed
                        Box(
                            modifier = Modifier
                                .offset(xo, yo)
                                .size(62.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            DraggableCustomEmojiItem(
                                emojiItem = emoji,
                                modifier = Modifier.size(58.dp),
                                displaySize = 56.dp,
                                onDragStart = { root ->
                                    draggingIcon = emoji.id
                                    dragFingerRoot = root
                                    dragTotal = 0f
                                    hoveredDate = null
                                    scope.launch { floatScale.snapTo(0.82f) }
                                    floatAlpha = 0.75f
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
                                            floatScale.animateTo(1.15f, spring())
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
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
                                        .size(32.dp)
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
                                val todayEmpty = isToday
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .then(
                                            if (todayEmpty) {
                                                Modifier.background(Color(0xFF1A1A1A))
                                            } else {
                                                Modifier
                                                    .background(EmptyDayFill)
                                                    .border(1.dp, RingGray, CircleShape)
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "+",
                                        color = if (todayEmpty) Color.White else slotColor,
                                        fontSize = if (todayEmpty) 17.sp else 15.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = LocalizedStrings.get("view_history"),
                color = LinkBlue,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clickable { onViewHistory() }
                    .padding(vertical = 6.dp)
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
                    modifier = dragMod.size(68.dp)
                )
            } else {
                Text(
                    text = draggingIcon!!,
                    fontSize = 34.sp,
                    modifier = dragMod
                )
            }
        }
    }
}

/**
 * Staggered slots in the fixed 256×102 cluster (~1.4× larger emoji than legacy 48×48 layout;
 * second row overlaps slightly so everything stays inside the magic area height).
 */
/** Tighter positions; ~1.4× emoji with slight row overlap so both rows stay inside the fixed 256×102 cluster. */
private fun moodEmojiClusterSlotsScaled(): List<Pair<Dp, Dp>> = listOf(
    0.dp to 0.dp,
    46.dp to 2.dp,
    92.dp to 0.dp,
    138.dp to 3.dp,
    184.dp to 1.dp,
    22.dp to 40.dp,
    68.dp to 40.dp,
    114.dp to 40.dp,
    160.dp to 40.dp
)
