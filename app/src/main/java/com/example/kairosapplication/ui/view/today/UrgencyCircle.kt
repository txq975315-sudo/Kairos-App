package com.example.kairosapplication.ui.view.today

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.AppColors

@Composable
fun UrgencyCircle(
    urgency: Int,
    isCompleted: Boolean,
    modifier: Modifier = Modifier,
) {
    val borderColor = when (urgency.coerceIn(0, 3)) {
        0 -> AppColors.Urgent
        1 -> AppColors.High
        2 -> AppColors.Normal
        else -> AppColors.Low
    }
    if (isCompleted) {
        Box(
            modifier = modifier
                .size(16.dp)
                .background(Color(0xFFBDBDBD), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(10.dp),
            )
        }
    } else {
        Box(
            modifier = modifier
                .size(16.dp)
                .border(width = 1.5.dp, color = borderColor, shape = CircleShape),
        )
    }
}
