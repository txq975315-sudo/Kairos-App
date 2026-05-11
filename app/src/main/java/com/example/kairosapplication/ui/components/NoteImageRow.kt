package com.example.kairosapplication.ui.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kairosapplication.i18n.LocalizedStrings

/**
 * Fixed 2×2 slot grid (same footprint as four thumbnails): 1–4 images fill slots in reading order;
 * empty slots stay blank so height stays consistent across image counts.
 */
@Composable
fun NoteImageRow(
    imageUris: List<String>,
    modifier: Modifier = Modifier,
    maxImages: Int = 4
) {
    if (imageUris.isEmpty()) return
    val uris = imageUris.take(maxImages)
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val spacing = 4.dp
        val cell: Dp = ((maxWidth - spacing) / 2).coerceAtLeast(0.dp)
        val gridHeight = cell * 2 + spacing
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(gridHeight),
            verticalArrangement = Arrangement.spacedBy(spacing)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                ImageGridCell(uriString = uris.getOrNull(0), cell = cell)
                ImageGridCell(uriString = uris.getOrNull(1), cell = cell)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                ImageGridCell(uriString = uris.getOrNull(2), cell = cell)
                ImageGridCell(uriString = uris.getOrNull(3), cell = cell)
            }
        }
    }
}

@Composable
private fun ImageGridCell(uriString: String?, cell: Dp) {
    Box(
        modifier = Modifier.size(cell),
        contentAlignment = Alignment.Center
    ) {
        val u = uriString ?: return@Box
        AsyncImage(
            model = Uri.parse(u),
            contentDescription = LocalizedStrings.get("cd_note_image"),
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }
}
