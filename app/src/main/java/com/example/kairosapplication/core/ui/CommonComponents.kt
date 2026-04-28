package com.example.kairosapplication.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.ui.common.CommonBackButton as LegacyCommonBackButton

/**
 * 全局可复用通用组件库。
 *
 * 注意：
 * - 为了兼容现有页面，CommonBackButton 复用已存在的实现；
 * - 页面迁移时优先从本文件引用，避免组件分散。
 */

@Composable
fun CommonBackButton(onClick: () -> Unit) {
    LegacyCommonBackButton(onClick = onClick)
}

@Composable
fun CommonCard(
    modifier: Modifier = Modifier,
    containerColor: Color = AppColors.SurfaceWhite,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(AppShapes.CardRadius),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        content()
    }
}

@Composable
fun CommonClickableItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()

    Box(
        modifier = modifier
            .clickable(interactionSource = interaction, indication = null, onClick = onClick)
            .background(Color.Transparent)
            .padding(0.dp)
            .let {
                if (pressed) it else it
            }
    ) {
        content()
    }
}

@Composable
fun CommonTimeBlock(
    label: String,
    count: Int,
    icon: ImageVector,
    backgroundColor: Color,
    expanded: Boolean,
    onToggle: () -> Unit,
    onCreateClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val hasTasks = count > 0

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(AppShapes.TimeBlockRadius),
                colors = CardDefaults.cardColors(containerColor = backgroundColor.copy(alpha = 0.8f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .height(AppSize.TimeBlockHeaderHeight)
                        .clickable(enabled = hasTasks) { if (hasTasks) onToggle() }
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = AppColors.SecondaryText,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = " $label ($count)",
                        color = AppColors.PrimaryText,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = AppColors.SecondaryText,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            Box(modifier = Modifier.weight(1f))
            if (hasTasks) {
                IconButton(onClick = onCreateClick, modifier = Modifier.size(24.dp)) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color.Black.copy(alpha = 0.05f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add task",
                            tint = Color.Black.copy(alpha = 0.18f),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }

        AnimatedVisibility(visible = expanded) {
            Column(verticalArrangement = Arrangement.spacedBy(AppSpacing.IconTextGap)) {
                content()
            }
        }
    }
}

