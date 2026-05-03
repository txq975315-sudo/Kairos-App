package com.example.kairosapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors

private val PublishedActionBlue = Color(0xFF1976D2)
private val PublishedActionRed = Color(0xFFD32F2F)

/** Single-row actions when a published note is expanded: topic / project or continue / delete. */
@Composable
internal fun PublishedNoteActionsRow(
    actions: PublishedNoteCardActions,
    hasProjects: Boolean,
    modifier: Modifier = Modifier,
    showDividerAbove: Boolean = true
) {
    if (showDividerAbove) {
        HorizontalDivider(color = AppColors.Divider, thickness = 1.dp)
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = if (showDividerAbove) 8.dp else 0.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Change topic",
            modifier = Modifier
                .weight(1f)
                .clickable { actions.onChangeTopic() }
                .padding(vertical = 8.dp),
            color = PublishedActionBlue,
            fontSize = 13.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = if (hasProjects) "Change project" else "Continue",
            modifier = Modifier
                .weight(1f)
                .clickable {
                    if (hasProjects) actions.onChangeProject() else actions.onContinueCreate()
                }
                .padding(vertical = 8.dp),
            color = PublishedActionBlue,
            fontSize = 13.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Delete",
            modifier = Modifier
                .weight(1f)
                .clickable { actions.onDelete() }
                .padding(vertical = 8.dp),
            color = PublishedActionRed,
            fontSize = 13.sp,
            textAlign = TextAlign.Center
        )
    }
}
