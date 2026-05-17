package com.example.kairosapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppShapes

/** Unified primary-category emoji tile (timeline / topic / project note cards). */
@Composable
fun NoteCategoryIconTile(
    emoji: String,
    modifier: Modifier = Modifier,
    fontSizeSp: Int = 20,
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(RoundedCornerShape(AppShapes.EmbedRadius))
            .background(NoteCardConstants.categoryIconTileBackground()),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = emoji, fontSize = fontSizeSp.sp)
    }
}
