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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.mine.EmojiConstants
import com.example.taskmodel.model.MoodRecord
import java.time.LocalDate
import kotlin.math.hypot
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

private val CardSurface = Color(0xFFFFFFFF)
private val CardStroke = Color.Black.copy(alpha = 0.2f)
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
    val weekLabels = remember(lang) {
        if (lang == LocalizationManager.Language.ZH) {
            listOf("日", "一", "二", "三", "四", "五", "六")
        } else {
            listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        }
    }
    val sunday = remember { sundayStartOfWeekContaining(LocalDate.now()) }
    val weekDates = remember(sunday) { (0..6).map { sunday.plusDays(it.toLong()) } }
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
                    elevation = 3.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = Color.Black.copy(alpha = 0.1f),
                    spotColor = Color.Black.copy(alpha = 0.1f)
                )
                .background(CardSurface, RoundedCornerShape(16.dp))
                .border(0.5.dp, CardStroke, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Text(
                text = LocalizedStrings.get("mine_mood_card_title"),
                color = TitleColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(Modifier.height(10.dp))
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
                        val (xo, yo) = moodEmojiClusterSlots().getOrNull(i) ?: return@forEachIndexed
                        Box(
                            modifier = Modifier
                                .offset(xo, yo)
                                .size(48.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            DraggableCustomEmojiItem(
                                emojiItem = emoji,
                                modifier = Modifier.size(44.dp),
                                displaySize = 40.dp,
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
            Spacer(Modifier.height(18.dp))
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
                    modifier = dragMod.size(48.dp)
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

private fun sundayStartOfWeekContaining(d: LocalDate): LocalDate {
    val dow = d.dayOfWeek.value
    return d.minusDays((dow % 7).toLong())
}

/** Two loose rows: varied X gaps and ≥4 distinct top Y anchors (staggered). */
/**
 * Non-overlapping 48×48 slots in a centered 256×102 region: five + four, second row staggered.
 * Distinct top Y values: 0 and 54 (row gap); slight vertical jitter on a few slots for ≥4 top anchors.
 */
private fun moodEmojiClusterSlots(): List<Pair<Dp, Dp>> = listOf(
    0.dp to 0.dp,
    52.dp to 2.dp,
    104.dp to 0.dp,
    156.dp to 3.dp,
    208.dp to 1.dp,
    26.dp to 54.dp,
    78.dp to 56.dp,
    130.dp to 54.dp,
    182.dp to 55.dp
)
