package com.example.kairosapplication.ui.view.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.view.viewClickable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

private val ArrowGray = Color(0xFF9E9E9E)
private val DateBlack = Color(0xFF1A1A1A)

@Composable
fun DateSelector(
    focusedDate: LocalDate,
    onPreviousDay: () -> Unit,
    onNextDay: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val fmt = remember {
        DateTimeFormatter.ofPattern("yyyy年M月d日", Locale.CHINA)
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "◀",
            color = ArrowGray,
            fontSize = 20.sp,
            modifier = Modifier
                .viewClickable(onPreviousDay)
                .padding(horizontal = 12.dp, vertical = 8.dp),
        )
        Text(
            text = focusedDate.format(fmt),
            color = DateBlack,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        )
        Text(
            text = "▶",
            color = ArrowGray,
            fontSize = 20.sp,
            modifier = Modifier
                .viewClickable(onNextDay)
                .padding(horizontal = 12.dp, vertical = 8.dp),
        )
    }
}
