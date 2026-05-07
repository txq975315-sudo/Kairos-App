package com.example.kairosapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.i18n.LocalizedStrings

private val PublishedActionBlue = Color(0xFF1976D2)
private val PublishedActionBlueOnDark = Color(0xFF90CAF9)
private val PublishedActionRed = Color(0xFFD32F2F)
private val PublishedActionRedOnDark = Color(0xFFFF8A80)

private val CompactActionText = 10.sp
private val CompactIconSize = 14.dp
private val CompactVerticalPad = 2.dp

/** One compact row: Topic · Project or Continue · Comment · Delete. */
@Composable
internal fun PublishedNoteActionsRow(
    actions: PublishedNoteCardActions,
    hasProjects: Boolean,
    modifier: Modifier = Modifier,
    showDividerAbove: Boolean = true,
    horizontalContentPadding: Dp = 8.dp,
    lightForeground: Boolean = false,
) {
    val blue = if (lightForeground) PublishedActionBlueOnDark else PublishedActionBlue
    val red = if (lightForeground) PublishedActionRedOnDark else PublishedActionRed
    val dividerColor =
        if (lightForeground) Color.White.copy(alpha = 0.35f) else AppColors.Divider.copy(alpha = 0.55f)
    if (showDividerAbove) {
        HorizontalDivider(
            color = dividerColor,
            thickness = 0.5.dp
        )
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalContentPadding)
            .padding(top = if (showDividerAbove) 3.dp else 0.dp, bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
            CompactActionIconLabel(
                icon = Icons.Outlined.Category,
                label = LocalizedStrings.get("essay_card_action_topic"),
                tint = blue,
                onClick = actions.onChangeTopic
            )
        }
        Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
            if (hasProjects) {
                CompactActionIconLabel(
                    icon = Icons.Outlined.FolderOpen,
                    label = LocalizedStrings.get("essay_card_action_project"),
                    tint = blue,
                    onClick = actions.onChangeProject
                )
            } else {
                CompactActionIconLabel(
                    icon = Icons.Outlined.AutoAwesome,
                    label = LocalizedStrings.get("essay_card_action_continue"),
                    tint = blue,
                    onClick = actions.onContinueCreate
                )
            }
        }
        Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
            CompactActionIconLabel(
                icon = Icons.Outlined.ChatBubbleOutline,
                label = LocalizedStrings.get("essay_card_action_comment"),
                tint = blue,
                onClick = actions.onComment
            )
        }
        Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
            CompactActionIconLabel(
                icon = Icons.Outlined.DeleteOutline,
                label = LocalizedStrings.get("essay_card_action_delete"),
                tint = red,
                onClick = actions.onDelete
            )
        }
    }
}

@Composable
private fun CompactActionIconLabel(
    icon: ImageVector,
    label: String,
    tint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = CompactVerticalPad, horizontal = 1.dp)
            .widthIn(max = 86.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(CompactIconSize)
        )
        Text(
            text = label,
            color = tint,
            fontSize = CompactActionText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 2.dp)
        )
    }
}
