package com.example.kairosapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ArrowDirection { LEFT, RIGHT }

@Composable
fun ArrowButton(
    onClick: () -> Unit,
    direction: ArrowDirection,
    size: Dp = 32.dp,
    tint: Color = Color(0xFF1A1A1A),
    contentDescription: String? = null
) {
    Box(
        modifier = Modifier
            .size(size)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = when (direction) {
                ArrowDirection.LEFT -> Icons.AutoMirrored.Filled.KeyboardArrowLeft
                ArrowDirection.RIGHT -> Icons.AutoMirrored.Filled.KeyboardArrowRight
            },
            contentDescription = contentDescription,
            tint = tint
        )
    }
}
