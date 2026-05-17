package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.constants.GlassConstants

@Composable
fun GlassPillLabel(
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    iconTint: Color = GlassConstants.TextSecondary,
    trailingContent: @Composable (() -> Unit)? = null,
) {
    val shape = RoundedCornerShape(GlassConstants.PillCornerRadius)
    val flatFrost = GlassConstants.usesBackdropBlur

    Box(
        modifier = modifier
            .wrapContentSize(Alignment.Center, unbounded = true)
            .then(
                if (flatFrost) {
                    Modifier
                } else {
                    Modifier.shadow(
                        elevation = 4.dp,
                        shape = shape,
                        ambientColor = Color.Black.copy(alpha = GlassConstants.ShadowAmbientAlpha),
                        spotColor = Color.Black.copy(alpha = GlassConstants.ShadowSpotAlpha),
                    )
                },
            )
            .clip(shape)
            .glassBubbleBackdrop()
            .then(
                if (!flatFrost && GlassConstants.PillFillAlpha > 0f) {
                    Modifier.background(Color.Black.copy(alpha = GlassConstants.PillFillAlpha), shape)
                } else {
                    Modifier
                },
            )
            .then(
                if (!flatFrost && GlassConstants.TaskCardSheenAlpha > 0f) {
                    Modifier.background(
                        Color.White.copy(alpha = GlassConstants.TaskCardSheenAlpha),
                        shape,
                    )
                } else {
                    Modifier
                },
            ),
    ) {
        GlassFrostOverlays(shape = shape)
        Box(
            modifier = Modifier
                .matchParentSize()
                .border(
                    width = GlassConstants.GlassBorderWidth,
                    color = Color.White.copy(alpha = GlassConstants.PillBorderAlpha),
                    shape = shape,
                ),
        )
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
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
}
