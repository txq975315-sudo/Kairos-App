package com.example.kairosapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import com.example.taskmodel.model.Note
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

/** Padding between time/day label and the vertical rail segment. */
val TimelineRailLabelPadding = 12.dp

val TimelineRailWidth = 40.dp

fun noteTimeLabel(note: Note, zone: ZoneId = ZoneId.systemDefault()): String =
    DateTimeFormatter.ofPattern("HH:mm")
        .format(Instant.ofEpochMilli(note.createdAt).atZone(zone))

fun notesShareSameMinute(a: Note, b: Note, zone: ZoneId = ZoneId.systemDefault()): Boolean =
    noteTimeLabel(a, zone) == noteTimeLabel(b, zone)

/**
 * Left timeline gutter: continuous 1dp vertical line with optional centered label.
 * When [label] is null the line spans the full row height (same-minute continuation).
 */
@Composable
fun TimelineRailGutter(
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
) {
    val lineColor = NoteCardConstants.TimelineConnectorColor
    Column(
        modifier = modifier
            .width(TimelineRailWidth)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (label != null) {
            RailLineSegment(Modifier.weight(1f), lineColor)
            Box(modifier = Modifier.padding(vertical = TimelineRailLabelPadding)) {
                label()
            }
            RailLineSegment(Modifier.weight(1f), lineColor)
        } else {
            RailLineSegment(Modifier.fillMaxSize(), lineColor)
        }
    }
}

@Composable
private fun RailLineSegment(modifier: Modifier, color: Color) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(color),
        )
    }
}

@Composable
fun TimelineRailTimeLabel(
    text: String,
    color: Color,
    fontSizeSp: Int = 12,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text,
        fontSize = fontSizeSp.sp,
        fontWeight = fontWeight,
        color = color,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun TimelineCardDayBlock(
    date: LocalDate,
    notes: List<Note>,
    showYearOnHeader: Boolean,
    onOpenNoteEditor: (Long) -> Unit,
    expandedNoteId: Long?,
    onToggleExpand: (Long) -> Unit,
    projectsById: Map<Long, String>,
    publishedNoteActions: (Note) -> PublishedNoteCardActions,
    modifier: Modifier = Modifier,
) {
    val scrollText = LocalGlassTextColors.current
    val zone = ZoneId.systemDefault()
    Column(modifier = modifier.fillMaxWidth()) {
        TimelineCardDateHeader(
            date = date,
            showYear = showYearOnHeader,
            titleColor = scrollText.primary,
        )
        notes.forEachIndexed { index, note ->
            val showTime = index == 0 || !notesShareSameMinute(notes[index - 1], note, zone)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.Top,
            ) {
                TimelineRailGutter(
                    label = if (showTime) {
                        {
                            TimelineRailTimeLabel(
                                text = noteTimeLabel(note, zone),
                                color = scrollText.muted,
                            )
                        }
                    } else {
                        null
                    },
                )
                Spacer(Modifier.width(12.dp))
                NoteCard(
                    note = note,
                    variant = NoteCardVariant.TIMELINE,
                    onNoteClick = onOpenNoteEditor,
                    expandable = true,
                    expanded = note.id == expandedNoteId,
                    onToggleExpand = { onToggleExpand(note.id) },
                    modifier = Modifier.weight(1f),
                    projectsById = projectsById,
                    publishedActions = publishedNoteActions(note),
                    externalTimeRail = true,
                )
            }
        }
        Spacer(Modifier.height(AppSpacing.SectionLarge))
    }
}

@Composable
fun TimelineCardDateHeader(
    date: LocalDate,
    showYear: Boolean,
    titleColor: Color,
) {
    val lang = LocalCurrentLanguage.current.value
    val loc = if (lang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.US
    val dateStr = remember(date, showYear, lang, loc) {
        val pattern = when {
            lang == LocalizationManager.Language.ZH && showYear -> "yyyy年M月d日"
            lang == LocalizationManager.Language.ZH -> "M月d日"
            showYear -> "MMMM d, yyyy"
            else -> "MMMM d"
        }
        DateTimeFormatter.ofPattern(pattern, loc).format(date)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(
                top = AppSpacing.SectionSmall,
                bottom = AppSpacing.SectionSmall,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TimelineRailGutter(
            label = {
                TimelineRailTimeLabel(
                    text = date.dayOfMonth.toString(),
                    color = titleColor,
                    fontSizeSp = 14,
                    fontWeight = FontWeight.SemiBold,
                )
            },
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = dateStr,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = titleColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun TimelineIntegratedDayBlock(
    date: LocalDate,
    notes: List<Note>,
    accentColor: Color,
    onOpenNoteEditor: (Long) -> Unit,
    expandedNoteId: Long?,
    onToggleExpand: (Long) -> Unit,
    projectsById: Map<Long, String>,
    publishedNoteActions: (Note) -> PublishedNoteCardActions,
    modifier: Modifier = Modifier,
) {
    val scrollText = LocalGlassTextColors.current
    val zone = ZoneId.systemDefault()
    val lang = LocalCurrentLanguage.current.value
    val loc = if (lang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.US
    val month = remember(date, loc) { date.month.getDisplayName(TextStyle.FULL, loc) }
    val dow = remember(date, loc, lang) {
        val raw = date.dayOfWeek.getDisplayName(TextStyle.FULL, loc)
        if (lang == LocalizationManager.Language.ZH) raw else raw.lowercase(loc)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(top = 2.dp, bottom = 1.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            TimelineRailGutter(
                label = {
                    TimelineRailTimeLabel(
                        text = date.dayOfMonth.toString(),
                        color = scrollText.primary,
                        fontSizeSp = 18,
                        fontWeight = FontWeight.Bold,
                    )
                },
            )
            Spacer(Modifier.width(4.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "$month / $dow ${date.year}",
                    fontSize = 14.sp,
                    color = scrollText.secondary,
                )
            }
        }
        notes.forEachIndexed { index, note ->
            val showTime = index == 0 || !notesShareSameMinute(notes[index - 1], note, zone)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.Top,
            ) {
                TimelineRailGutter(
                    label = if (showTime) {
                        {
                            TimelineRailTimeLabel(
                                text = noteTimeLabel(note, zone),
                                color = accentColor,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    } else {
                        null
                    },
                )
                NoteTimelineIntegrated(
                    note = note,
                    accentColor = accentColor,
                    onNoteClick = onOpenNoteEditor,
                    projectsById = projectsById,
                    modifier = Modifier.weight(1f),
                    expandable = true,
                    expanded = note.id == expandedNoteId,
                    onToggleExpand = { onToggleExpand(note.id) },
                    publishedActions = publishedNoteActions(note),
                    externalTimeRail = true,
                )
            }
        }
        Spacer(Modifier.height(12.dp))
    }
}
