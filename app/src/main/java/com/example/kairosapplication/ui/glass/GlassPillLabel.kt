package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.constants.GlassConstants

/**
 * Floating pill label for Glass UI.
 *
 * Design reference: "Information floats out using suspended capsules (pill),
 * like '5 Days', '2 Places Available', 'Morning Yoga'."
 *
 * - Shape: full pill (999dp corner radius)
 * - Fill: white 10%
 * - Border: 0.5dp white 8%
 * - Text: white 70%, small size
 * - Optional leading icon
 */
@Composable
fun GlassPillLabel(
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    iconTint: androidx.compose.ui.graphics.Color = GlassConstants.TextSecondary,
    trailingContent: @Composable (() -> Unit)? = null,
) {
    val shape = RoundedCornerShape(GlassConstants.PillCornerRadius)

    Row(
        modifier = modifier
            .clip(shape)
            .background(Color.White.copy(alpha = GlassConstants.PillFillAlpha), shape)
            .border(
                width = GlassConstants.GlassBorderWidth,
                color = Color.White.copy(alpha = GlassConstants.PillBorderAlpha),
                shape = shape,
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(14.dp),
            )
            Box(modifier = Modifier.size(width = 6.dp, height = 0.dp))
        }
        Text(
            text = text,
            color = GlassConstants.TextSecondary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.5.sp,
        )
        if (trailingContent != null) {
            Box(modifier = Modifier.size(width = 6.dp, height = 0.dp))
            trailingContent()
        }
    }
}
