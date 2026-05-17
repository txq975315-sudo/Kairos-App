package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.constants.GlassConstants

/**
 * Semi-transparent icon button for Glass UI.
 *
 * Design reference: "Icons are no longer solid blocks, but use
 * semi-transparent backgrounds (#ffffff20) with linear icons."
 *
 * - Unselected: white 12% background
 * - Selected: white 20% background
 * - Icon color: white-based (primary by default)
 * - Shape: circle or pill (configurable)
 */
@Composable
fun GlassIconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    tint: Color = GlassConstants.TextPrimary,
    shape: androidx.compose.ui.graphics.Shape = CircleShape,
) {
    val bgAlpha = if (selected) GlassConstants.IconSelectedAlpha else GlassConstants.IconBackgroundAlpha

    Box(
        modifier = modifier
            .clip(shape)
            .background(Color.White.copy(alpha = bgAlpha), shape)
            .clickable(onClick = onClick)
            .padding(6.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(GlassConstants.IconSize),
        )
    }
}

/**
 * Pill-shaped variant of [GlassIconButton] for tab-like usage.
 */
@Composable
fun GlassIconPill(
    icon: ImageVector,
    label: String,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
) {
    val bgAlpha = if (selected) GlassConstants.IconSelectedAlpha else GlassConstants.IconBackgroundAlpha
    val textColor = if (selected) GlassConstants.TextPrimary else GlassConstants.TextMuted

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(GlassConstants.CornerRadius))
            .background(Color.White.copy(alpha = bgAlpha), RoundedCornerShape(GlassConstants.CornerRadius))
            .clickable(onClick = onClick)
            .padding(horizontal = GlassConstants.BottomNavTabPaddingHorizontal, vertical = GlassConstants.BottomNavTabPaddingVertical),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = textColor,
            modifier = Modifier.size(GlassConstants.IconSize),
        )
    }
}
