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

import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.ui.glass.GlassNoteCardShell
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.topic.rememberTopicPrimaryLabel
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
    modifier: Modifier = Modifier,
    expandable: Boolean = false,
    expanded: Boolean = false,
    onToggleExpand: () -> Unit = {},
    publishedActions: PublishedNoteCardActions? = null
) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val zone = ZoneId.systemDefault()
    val updatedStr = remember(note.updatedAt, lang) {
        val z = Instant.ofEpochMilli(note.updatedAt).atZone(zone)
        val loc = if (lang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.ENGLISH
        val pattern = if (lang == LocalizationManager.Language.ZH) "M月d日 HH:mm" else "MM/dd HH:mm"
        DateTimeFormatter.ofPattern(pattern, loc).format(z)
    }
    val projectDefault = remember(lang, context) {
        LocalizedStrings.stringFor(lang, "inbox_action_project", context)
    }
    val projectTitle = remember(note.projectIds, projectsById, projectDefault) {
        note.projectIds.firstOrNull()?.let { projectsById[it] } ?: projectDefault
    }
    val headline = remember(projectTitle, note.behaviorSummary) {
        val summary = note.behaviorSummary.ifBlank { "—" }
        "$projectTitle · $summary"
    }
    val projectCount = note.projectIds.size
    val categoryLabel = rememberTopicPrimaryLabel(note.primaryCategory)
    val emoji = remember(note.primaryCategory) {
        NoteCardConstants.categoryEmoji(note.primaryCategory)
    }
    val bodyMaxLines = if (expandable && !expanded) 3 else Int.MAX_VALUE

    val cardText = LocalGlassTextColors.current
    GlassNoteCardShell(
        modifier = modifier.fillMaxWidth(),
        onClick = { if (expandable) onToggleExpand() else onNoteClick(note.id) },
    ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                NoteCategoryIconTile(emoji = emoji, fontSizeSp = 20)
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = categoryLabel,
                        fontSize = 12.sp,
                        color = cardText.muted
                    )
                    Text(
                        text = headline,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = cardText.primary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(Modifier.height(6.dp))
            val (projectMainBody, projectParsedReview) = NoteCardConstants.splitReviewFromBody(note.body)
            Text(
                text = projectMainBody.ifBlank { " " },
                fontSize = 14.sp,
                color = cardText.secondary,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp
            )
            if (note.imageUris.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                NoteImageRow(imageUris = note.imageUris, maxImages = 4)
            }
            if (projectParsedReview != null) {
                Spacer(Modifier.height(8.dp))
                NoteReviewSection(
                    review = projectParsedReview,
                    expanded = expanded || !expandable,
                    collapsedMaxLines = if (expandable && !expanded) 3 else Int.MAX_VALUE,
                    textColor = cardText.muted,
                    timestampColor = cardText.muted,
                )
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
                            color = cardText.secondary,
                            modifier = Modifier
                                .background(
                                    AppColors.EveningBackground.copy(alpha = 0.35f),
                                    RoundedCornerShape(AppShapes.DenseInsetRadius)
                                )
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = LocalizedStrings.stringFor(
                    lang,
                    "essay_note_card_updated_topics",
                    context,
                    updatedStr,
                    projectCount,
                ),
                fontSize = 12.sp,
                color = AppColors.HintText
            )
            if (expandable && expanded) {
                Spacer(Modifier.height(8.dp))
                if (publishedActions != null) {
                    PublishedNoteActionsRow(
                        actions = publishedActions,
                        hasProjects = note.projectIds.isNotEmpty()
                    )
                } else {
                    TextButton(
                        onClick = { onNoteClick(note.id) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(LocalizedStrings.get("note_card_edit"), color = cardText.primary)
                    }
                }
            }
    }
}
