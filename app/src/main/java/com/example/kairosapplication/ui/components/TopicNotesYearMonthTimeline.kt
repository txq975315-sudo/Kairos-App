package com.example.kairosapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.taskmodel.model.Note
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

/**
 * Year → month (collapsible) → day → notes, for Essay Topic tab right pane.
 * Card mode uses timeline [NoteCard]; integrated uses [NoteTimelineIntegrated].
 */
@Composable
fun TopicNotesYearMonthTimeline(
    notes: List<Note>,
    integratedLayout: Boolean,
    accentColor: Color,
    lightForeground: Boolean,
    headerPrimary: Color,
    headerSecondary: Color,
    projectsById: Map<Long, String>,
    expandedNoteId: Long?,
    onToggleExpand: (Long) -> Unit,
    onOpenNoteEditor: (Long) -> Unit,
    publishedNoteActions: (Note) -> PublishedNoteCardActions,
    modifier: Modifier = Modifier,
) {
    val sorted = remember(notes) {
        notes.sortedWith(
            compareByDescending<Note> { it.recordedDate }
                .thenByDescending { it.createdAt }
        )
    }
    val years = remember(sorted) {
        sorted.map { it.recordedDate.year }.distinct().sortedDescending()
    }
    val essayCountByYear = remember(sorted) {
        sorted.groupingBy { it.recordedDate.year }.eachCount()
    }
    val essayCountByYearMonth = remember(sorted) {
        sorted.groupingBy { it.recordedDate.year to it.recordedDate.monthValue }.eachCount()
    }

    var expandedYears by remember(sorted) {
        mutableStateOf(sorted.map { it.recordedDate.year }.toSet())
    }
    var expandedMonths by remember(sorted) {
        mutableStateOf(
            sorted.map { it.recordedDate.year to it.recordedDate.monthValue }.toSet()
        )
    }

    val topicLang = LocalCurrentLanguage.current.value
    val topicLocale =
        if (topicLang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.US

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 88.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        if (sorted.isEmpty()) {
            item(key = "empty") {
                Text(
                    text = LocalizedStrings.get("essay_topic_empty_notes"),
                    fontSize = 14.sp,
                    color = headerSecondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        } else {
            years.forEach { year ->
                val yearExpanded = year in expandedYears
                val yearCount = essayCountByYear[year] ?: 0
                item(key = "ty_$year") {
                    TopicTimelineYearRow(
                        year = year,
                        count = yearCount,
                        expanded = yearExpanded,
                        onToggle = {
                            expandedYears =
                                if (year in expandedYears) expandedYears - year else expandedYears + year
                        },
                        titleColor = headerPrimary,
                        iconTint = headerSecondary
                    )
                }
                if (yearExpanded) {
                    val monthsInYear = sorted
                        .filter { it.recordedDate.year == year }
                        .map { it.recordedDate.monthValue }
                        .distinct()
                        .sortedDescending()
                    monthsInYear.forEach { month ->
                        val monthKey = year to month
                        val monthExpanded = monthKey in expandedMonths
                        val monthCount = essayCountByYearMonth[monthKey] ?: 0
                        item(key = "tm_${year}_$month") {
                            TopicTimelineMonthRow(
                                month = month,
                                count = monthCount,
                                expanded = monthExpanded,
                                onToggle = {
                                    expandedMonths =
                                        if (monthKey in expandedMonths) {
                                            expandedMonths - monthKey
                                        } else {
                                            expandedMonths + monthKey
                                        }
                                },
                                titleColor = headerPrimary,
                                iconTint = headerSecondary,
                                locale = topicLocale
                            )
                        }
                        if (monthExpanded) {
                            val dateGroups = sorted
                                .filter {
                                    it.recordedDate.year == year &&
                                        it.recordedDate.monthValue == month
                                }
                                .groupBy { it.recordedDate }
                                .entries
                                .sortedByDescending { it.key }
                            dateGroups.forEach { (date, dayNotes) ->
                                item(key = "td_${year}_${month}_$date") {
                                    TopicTimelineDateRow(
                                        date = date,
                                        titleColor = headerPrimary,
                                        locale = topicLocale,
                                        useChineseDates = topicLang == LocalizationManager.Language.ZH
                                    )
                                }
                                items(
                                    items = dayNotes.sortedByDescending { it.createdAt },
                                    key = { it.id }
                                ) { note ->
                                    if (integratedLayout) {
                                        NoteTimelineIntegrated(
                                            note = note,
                                            accentColor = accentColor,
                                            onNoteClick = onOpenNoteEditor,
                                            projectsById = projectsById,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 12.dp),
                                            expandable = true,
                                            expanded = note.id == expandedNoteId,
                                            onToggleExpand = { onToggleExpand(note.id) },
                                            publishedActions = publishedNoteActions(note),
                                            lightForeground = lightForeground
                                        )
                                    } else {
                                        NoteCard(
                                            note = note,
                                            variant = NoteCardVariant.TIMELINE,
                                            onNoteClick = onOpenNoteEditor,
                                            projectsById = projectsById,
                                            expandable = true,
                                            expanded = note.id == expandedNoteId,
                                            onToggleExpand = { onToggleExpand(note.id) },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = AppSpacing.BlockGap),
                                            publishedActions = publishedNoteActions(note)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TopicTimelineYearRow(
    year: Int,
    count: Int,
    expanded: Boolean,
    onToggle: () -> Unit,
    titleColor: Color,
    iconTint: Color,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onToggle)
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$year ($count)",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = titleColor
        )
        Icon(
            imageVector = if (expanded) Icons.Default.KeyboardArrowDown else Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = iconTint
        )
    }
}

@Composable
private fun TopicTimelineMonthRow(
    month: Int,
    count: Int,
    expanded: Boolean,
    onToggle: () -> Unit,
    titleColor: Color,
    iconTint: Color,
    locale: Locale,
) {
    val monthLabel = remember(month, locale) {
        Month.of(month).getDisplayName(TextStyle.SHORT_STANDALONE, locale)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onToggle)
            .padding(start = 8.dp, top = 6.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$monthLabel ($count)",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = titleColor
        )
        Icon(
            imageVector = if (expanded) Icons.Default.KeyboardArrowDown else Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = iconTint
        )
    }
}

@Composable
private fun TopicTimelineDateRow(
    date: LocalDate,
    titleColor: Color,
    locale: Locale,
    useChineseDates: Boolean,
) {
    val month = remember(date, locale) { date.month.getDisplayName(TextStyle.FULL, locale) }
    val dow = remember(date, locale, useChineseDates) {
        val raw = date.dayOfWeek.getDisplayName(TextStyle.FULL, locale)
        if (useChineseDates) raw else raw.lowercase(locale)
    }
    val subtitle = remember(date, month, dow) { "$month / $dow ${date.year}" }
    val subtitleColor = titleColor.copy(alpha = 0.68f)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp, top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = titleColor,
            modifier = Modifier.width(18.dp),
            lineHeight = 15.sp
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = subtitle,
            fontSize = 9.sp,
            fontWeight = FontWeight.Normal,
            color = subtitleColor,
            maxLines = 2,
            lineHeight = 10.sp
        )
    }
}

