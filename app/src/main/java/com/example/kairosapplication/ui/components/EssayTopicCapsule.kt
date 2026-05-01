package com.example.kairosapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmodel.constants.EssayConstants
import com.example.taskmodel.model.EssayTopic

@Composable
fun EssayTopicCapsule(
    topic: EssayTopic,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    lockedSelected: Boolean = false,
    /** 若提供则替代默认中文课题名（如 Essay 列表英文化） */
    labelOverride: String? = null
) {
    val shape = RoundedCornerShape(8.dp)
    val canClick = enabled && !(lockedSelected && selected)
    val bg = when {
        selected -> Color(0xFF1A1A1A)
        else -> Color.White
    }
    val fg = when {
        selected -> Color.White
        else -> Color(0xFF1A1A1A)
    }
    val borderColor = if (selected) Color(0xFF1A1A1A) else Color(0xFF1A1A1A)
    Box(
        modifier = modifier
            .clip(shape)
            .border(1.dp, borderColor, shape)
            .background(bg)
            .then(
                if (canClick) Modifier.clickable { onClick() }
                else Modifier
            )
            .padding(horizontal = 9.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = labelOverride ?: EssayConstants.topicLabel(topic),
            fontSize = 9.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
            color = fg
        )
    }
}
