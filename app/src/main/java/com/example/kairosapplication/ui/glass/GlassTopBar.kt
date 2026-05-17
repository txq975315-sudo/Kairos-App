package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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

@Composable
fun GlassTopBar(
    completedCount: Int,
    totalCount: Int,
    onCreateClick: () -> Unit,
    onDailyReviewClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardText = LocalGlassTextColors.current
    val chrome = LocalGlassAtmosphereUi.current.topChrome

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlassTopBarChip(onClick = null) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = cardText.secondary,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "$completedCount / $totalCount",
                    color = cardText.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Row(verticalAlignment = Alignment.CenterVertically) {
            GlassTopBarChip(onClick = onDailyReviewClick) {
                Icon(
                    imageVector = Icons.Default.MenuBook,
                    contentDescription = LocalizedStrings.get("cd_daily_review"),
                    tint = chrome.primary,
                    modifier = Modifier.size(GlassConstants.IconSize),
                )
            }
            Spacer(Modifier.width(6.dp))
            GlassTopBarChip(onClick = onCreateClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = LocalizedStrings.get("cd_add_task"),
                    tint = chrome.primary,
                    modifier = Modifier.size(GlassConstants.IconSize),
                )
            }
        }
    }
}
