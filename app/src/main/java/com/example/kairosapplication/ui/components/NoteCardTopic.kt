package com.example.kairosapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.taskmodel.model.Note
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun NoteCardTopic(
    note: Note,
    onNoteClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val zone = ZoneId.systemDefault()
    val timeStr = remember(note.createdAt) {
        val z = Instant.ofEpochMilli(note.createdAt).atZone(zone)
        DateTimeFormatter.ofPattern("HH:mm").format(z)
    }
    val categoryLabel = remember(note.primaryCategory) {
        NoteCardConstants.primaryCategoryLabel(note.primaryCategory)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .clickable { onNoteClick(note.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.CardHorizontal, AppSpacing.CardVertical)
        ) {
            Text(
                text = categoryLabel,
                fontSize = 12.sp,
                color = AppColors.HintText
            )
            Text(
                text = note.behaviorSummary.ifBlank { "—" },
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppColors.PrimaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = note.body.ifBlank { " " },
                fontSize = 14.sp,
                color = AppColors.SecondaryText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp
            )
            if (note.imageUris.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                NoteImageRow(imageUris = note.imageUris, maxImages = 4)
            }
            if (note.sceneTags.isNotEmpty()) {
                Spacer(Modifier.height(10.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(note.sceneTags) { _, tag ->
                        Text(
                            text = tag,
                            fontSize = 12.sp,
                            color = AppColors.SecondaryText,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(AppColors.BottomBarSelectedContainer)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = timeStr,
                    fontSize = 12.sp,
                    color = AppColors.HintText
                )
            }
        }
    }
}
