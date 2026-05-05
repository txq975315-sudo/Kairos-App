package com.example.kairosapplication.ui.mine.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalizedStrings

private val CardWhite = Color(0xFFFFFFFF)
private val TitleColor = Color(0xFF1A1A1A)
private val LabelGray = Color(0xFF9E9E9E)
private val DividerMine = Color(0xFFE8E5E0)

@Composable
fun AllRecordsSection(
    completedTasks: Int,
    uncompletedTasks: Int,
    todayCompletedTasks: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = Color.Black.copy(alpha = 0.08f),
                spotColor = Color.Black.copy(alpha = 0.08f)
            )
            .background(CardWhite, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "全部记录",
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
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AllRecordMetric(
                value = completedTasks,
                label = LocalizedStrings.get("completed"),
                modifier = Modifier.weight(1f)
            )
            AllRecordMetric(
                value = uncompletedTasks,
                label = LocalizedStrings.get("uncompleted"),
                modifier = Modifier.weight(1f)
            )
            AllRecordMetric(
                value = todayCompletedTasks,
                label = LocalizedStrings.get("today_completed"),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun AllRecordMetric(
    value: Int,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value.toString(),
            color = TitleColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            color = LabelGray,
            fontSize = 11.sp
        )
    }
}
