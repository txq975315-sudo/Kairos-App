package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.i18n.LocalizedStrings

/**
 * Minimal glass top bar: **✨ 3/5** glass chip (classic底纹) + icon actions (B layout).
 */
@Composable
fun GlassTopBar(
    completedCount: Int,
    totalCount: Int,
    onCreateClick: () -> Unit,
    onDailyReviewClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val chipShape = RoundedCornerShape(GlassConstants.CornerRadius)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlassSurface(
            modifier = Modifier,
            shape = chipShape,
            fillAlpha = GlassConstants.BottomNavSelectedFillAlpha,
            blurRadius = GlassConstants.BottomNavSelectedBlurRadius,
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = GlassConstants.TextSecondary,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "$completedCount / $totalCount",
                    color = GlassConstants.TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Row(verticalAlignment = Alignment.CenterVertically) {
            GlassIconButton(
                icon = Icons.Default.MenuBook,
                contentDescription = LocalizedStrings.get("cd_daily_review"),
                onClick = onDailyReviewClick,
            )
            Spacer(Modifier.width(4.dp))
            GlassIconButton(
                icon = Icons.Default.Add,
                contentDescription = LocalizedStrings.get("cd_add_task"),
                onClick = onCreateClick,
            )
        }
    }
}
