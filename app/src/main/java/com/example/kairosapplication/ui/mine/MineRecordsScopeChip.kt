package com.example.kairosapplication.ui.mine

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.ui.glass.LocalGlassTextColors

@Composable
fun MineRecordsScopeChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardText = LocalGlassTextColors.current
    val isGlass = LocalAppUiTheme.current == AppUiTheme.Glass
    val accent = Color(0xFF5B7CFA)
    val borderColor = if (selected) accent else cardText.muted.copy(alpha = if (isGlass) 0.35f else 0.5f)
    val bg = when {
        selected && isGlass -> accent.copy(alpha = 0.22f)
        selected -> accent.copy(alpha = 0.12f)
        isGlass -> Color.White.copy(alpha = 0.08f)
        else -> Color.White
    }
    Text(
        text = label,
        fontSize = 14.sp,
        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
        color = if (selected) accent else cardText.primary,
        modifier = modifier
            .border(1.dp, borderColor, RoundedCornerShape(AppShapes.FeaturePanelRadius))
            .background(bg, RoundedCornerShape(AppShapes.FeaturePanelRadius))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp),
    )
}
