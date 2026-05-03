package com.example.kairosapplication.ui.view.week

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.components.NoteCardConstants
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.model.Note

@Composable
fun WeekNoteRow(
    note: Note,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val isFreestyle = note.primaryCategory == NotePrimaryCategory.FREESTYLE
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .height(28.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(color = Color(0xFFF5F5F5), bounded = true, radius = 4.dp),
                onClick = onClick,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (!isFreestyle) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(14.dp)
                    .background(
                        NoteCardConstants.categoryColor(note.primaryCategory),
                        RoundedCornerShape(2.dp),
                    ),
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
        Text(
            text = if (isFreestyle) {
                NoteCardConstants.primaryCategoryLabel(NotePrimaryCategory.FREESTYLE)
            } else {
                note.secondaryCategory.trim().ifEmpty {
                    NoteCardConstants.primaryCategoryLabel(note.primaryCategory)
                }
            },
            color = Color(0xFF9E9E9E),
            fontSize = 12.sp,
            maxLines = 1,
        )
    }
}
