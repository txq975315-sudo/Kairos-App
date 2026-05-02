package com.example.kairosapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import java.util.Locale

@Composable
fun NoteCardProject(
    note: Note,
    onNoteClick: (Long) -> Unit,
    projectsById: Map<Long, String>,
    modifier: Modifier = Modifier
) {
    val zone = ZoneId.systemDefault()
    val updatedStr = remember(note.updatedAt) {
        val z = Instant.ofEpochMilli(note.updatedAt).atZone(zone)
        DateTimeFormatter.ofPattern("MM/dd HH:mm", Locale.ENGLISH).format(z)
    }
    val projectTitle = remember(note.projectIds, projectsById) {
        note.projectIds.firstOrNull()?.let { projectsById[it] } ?: "Project"
    }
    val headline = remember(projectTitle, note.behaviorSummary) {
        val summary = note.behaviorSummary.ifBlank { "—" }
        "$projectTitle · $summary"
    }
    val projectCount = note.projectIds.size
    val categoryLabel = remember(note.primaryCategory) {
        NoteCardConstants.primaryCategoryLabel(note.primaryCategory)
    }
    val emoji = remember(note.primaryCategory) {
        NoteCardConstants.categoryEmoji(note.primaryCategory)
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(AppColors.MorningBackground.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = emoji,
                        fontSize = 20.sp
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = categoryLabel,
                        fontSize = 12.sp,
                        color = AppColors.HintText
                    )
                    Text(
                        text = headline,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppColors.PrimaryText,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = note.body.ifBlank { " " },
                fontSize = 14.sp,
                color = AppColors.SecondaryText,
                maxLines = 3,
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
                                .background(
                                    AppColors.EveningBackground.copy(alpha = 0.35f),
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Updated $updatedStr · Topics $projectCount",
                fontSize = 12.sp,
                color = AppColors.HintText
            )
        }
    }
}
