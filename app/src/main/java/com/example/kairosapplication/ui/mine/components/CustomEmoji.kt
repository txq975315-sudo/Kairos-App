package com.example.kairosapplication.ui.mine.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.border
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.ui.mine.EmojiConstants

private val SelectGreen = Color(0xFF4CAF50)

@Composable
fun CustomEmoji(
    emojiItem: EmojiConstants.EmojiItem,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp
) {
    Image(
        painter = painterResource(id = emojiItem.drawableResId),
        contentDescription = LocalizedStrings.emojiLabel(emojiItem.id),
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(AppShapes.DenseInsetRadius))
            .clickable(onClick = onClick)
            .border(
                width = if (selected) 2.dp else 0.dp,
                color = if (selected) SelectGreen else Color.Transparent,
                shape = RoundedCornerShape(AppShapes.DenseInsetRadius)
            )
    )
}

@Composable
fun DraggableCustomEmojiItem(
    emojiItem: EmojiConstants.EmojiItem,
    modifier: Modifier = Modifier,
    displaySize: Dp = 40.dp,
    onDragStart: (startInRoot: Offset) -> Unit,
    onDrag: (dragAmount: Offset) -> Unit,
    onDragEnd: (fingerInRoot: Offset) -> Unit,
    onDragCancel: () -> Unit
) {
    DraggableMoodGestureHost(
        dragKey = emojiItem.id,
        modifier = modifier,
        onDragStart = onDragStart,
        onDrag = onDrag,
        onDragEnd = onDragEnd,
        onDragCancel = onDragCancel
    ) {
        Image(
            painter = painterResource(id = emojiItem.drawableResId),
            contentDescription = LocalizedStrings.emojiLabel(emojiItem.id),
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(displaySize)
        )
    }
}

@Composable
fun MoodStoredIcon(
    moodIcon: String,
    modifier: Modifier = Modifier,
    imageSize: Dp,
    textSize: TextUnit
) {
    val item = EmojiConstants.emojiById(moodIcon)
    if (item != null) {
        Image(
            painter = painterResource(id = item.drawableResId),
            contentDescription = LocalizedStrings.emojiLabel(moodIcon),
            contentScale = ContentScale.Fit,
            modifier = modifier.size(imageSize)
        )
    } else {
        Text(text = moodIcon, fontSize = textSize, modifier = modifier)
    }
}
