package com.example.kairosapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DailyReviewScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .statusBarsPadding()
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .padding(8.dp)
                .size(36.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back",
                tint = Color(0xFF1A1A1A),
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = "let's",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1A1A),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
