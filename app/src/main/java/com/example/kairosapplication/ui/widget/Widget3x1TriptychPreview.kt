package com.example.kairosapplication.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * In-app / Studio preview for 3×1 triptych widget (`widget_3x1_3a`).
 * Center uses a 2×N task grid with vertical scroll in preview only; launcher uses fixed 10 cells + hint.
 */
private val DateSecondary = Color(0xFF757575)
private val TodayLinePrimary = Color(0xFF1A1A1A)
private val TaskMuted = Color(0xFF888888)

@Composable
private fun TodayGridCell(
    slot: Pair<String, Boolean>?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 2.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        if (slot == null) {
            Spacer(Modifier.weight(1f))
        } else {
            val (title, done) = slot
            Text(
                text = if (done) "✓" else "○",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = TaskMuted,
                modifier = Modifier.width(12.dp)
            )
            Text(
                text = title,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = TaskMuted,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun Widget3x1TriptychStrip(
    yesterdayDay: Int,
    yesterdayTasksAnnotated: AnnotatedString,
    mergedDateLine: String,
    gridSlots: List<Pair<String, Boolean>?>,
    overflowHint: String?,
    tomorrowDay: Int,
    tomorrowTasksAnnotated: AnnotatedString,
    modifier: Modifier = Modifier
) {
    val gridScroll = rememberScrollState()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(132.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxHeight()
                .padding(horizontal = 2.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = yesterdayDay.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = DateSecondary
            )
            Text(
                text = yesterdayTasksAnnotated,
                fontSize = 8.sp,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
        Spacer(Modifier.width(4.dp))
        Column(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight()
                .padding(horizontal = 2.dp, vertical = 2.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = mergedDateLine,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = TodayLinePrimary,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            if (!overflowHint.isNullOrBlank()) {
                Text(
                    text = overflowHint,
                    fontSize = 8.sp,
                    color = DateSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .heightIn(max = 108.dp)
                    .verticalScroll(gridScroll),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                repeat(5) { r ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        TodayGridCell(
                            slot = gridSlots.getOrNull(r * 2),
                            modifier = Modifier.weight(1f)
                        )
                        TodayGridCell(
                            slot = gridSlots.getOrNull(r * 2 + 1),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
        Spacer(Modifier.width(4.dp))
        Column(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxHeight()
                .padding(horizontal = 2.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = tomorrowDay.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = DateSecondary
            )
            Text(
                text = tomorrowTasksAnnotated,
                fontSize = 8.sp,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 150)
@Composable
private fun Widget3x1TriptychStripPreview() {
    val side = buildAnnotatedString {
        append("x")
        addStyle(SpanStyle(color = TaskMuted), 0, 1)
    }
    val slots = listOf(
        "晨跑" to false,
        "阅读" to true,
        "写作" to false,
        "冥想" to false,
        null,
        null,
        "长跑训练" to false,
        null
    )
    Widget3x1TriptychStrip(
        yesterdayDay = 5,
        yesterdayTasksAnnotated = side,
        mergedDateLine = "6 · 今日 · 周三 · 05-06",
        gridSlots = slots,
        overflowHint = "还有3条，在应用内查看",
        tomorrowDay = 7,
        tomorrowTasksAnnotated = side,
        modifier = Modifier.padding(8.dp)
    )
}
