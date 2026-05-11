package com.example.kairosapplication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Collapsed: comment text only. Expanded: comment, then timestamp below (no “复盘” label).
 */
@Composable
fun NoteReviewSection(
    review: NoteCardConstants.ParsedReview?,
    expanded: Boolean,
    collapsedMaxLines: Int,
    textColor: Color,
    timestampColor: Color,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 13.sp,
    lineHeight: TextUnit = 18.sp,
) {
    val r = review ?: return
    Column(modifier = modifier) {
        Text(
            text = r.comment,
            fontSize = textSize,
            color = textColor,
            maxLines = if (expanded) Int.MAX_VALUE else collapsedMaxLines,
            overflow = TextOverflow.Ellipsis,
            lineHeight = lineHeight,
        )
        if (expanded) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = r.timestampLine,
                fontSize = 12.sp,
                color = timestampColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
