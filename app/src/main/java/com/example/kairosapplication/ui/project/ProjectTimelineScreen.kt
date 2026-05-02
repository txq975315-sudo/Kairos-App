package com.example.kairosapplication.ui.project

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.ui.components.NoteCard
import com.example.kairosapplication.ui.components.NoteCardVariant
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Note
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectTimelineScreen(
    projectId: Long,
    taskViewModel: TaskViewModel,
    onBack: () -> Unit,
    onNoteClick: (Long) -> Unit
) {
    val uiState by taskViewModel.uiState.collectAsState()
    val projectName = uiState.noteProjects.find { it.id == projectId }?.name ?: "Project"
    val projectsById = remember(uiState.noteProjects) {
        uiState.noteProjects.associate { it.id to it.name }
    }

    val projectNotes = remember(uiState.notePublished, projectId) {
        uiState.notePublished
            .filter { it.status != NoteStatus.DELETED && projectId in it.projectIds }
            .sortedWith(
                compareByDescending<Note> { it.recordedDate }
                    .thenByDescending { it.createdAt }
            )
    }

    val years = remember(projectNotes) {
        projectNotes.map { it.recordedDate.year }.distinct().sortedDescending()
    }

    var expandedYears by remember(projectNotes) {
        mutableStateOf(projectNotes.map { it.recordedDate.year }.toSet())
    }
    var expandedMonths by remember(projectNotes) {
        mutableStateOf(
            projectNotes.map { it.recordedDate.year to it.recordedDate.monthValue }.toSet()
        )
    }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = projectName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryTextColor
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PrimaryTextColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundColor)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(horizontal = AppSpacing.PageHorizontal),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.SectionLarge)
        ) {
            if (projectNotes.isEmpty()) {
                item(key = "empty") {
                    EmptyProjectTimelineState(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 400.dp)
                    )
                }
            } else {
                years.forEach { year ->
                    val yearExpanded = year in expandedYears
                    item(key = "year_$year") {
                        YearHeader(
                            year = year,
                            isExpanded = yearExpanded,
                            onToggle = {
                                expandedYears = if (year in expandedYears) {
                                    expandedYears - year
                                } else {
                                    expandedYears + year
                                }
                            }
                        )
                    }
                    if (yearExpanded) {
                        val monthsInYear = projectNotes
                            .filter { it.recordedDate.year == year }
                            .map { it.recordedDate.monthValue }
                            .distinct()
                            .sortedDescending()

                        monthsInYear.forEach { month ->
                            val monthKey = year to month
                            val monthExpanded = monthKey in expandedMonths
                            item(key = "month_${year}_$month") {
                                MonthHeader(
                                    month = month,
                                    isExpanded = monthExpanded,
                                    onToggle = {
                                        expandedMonths = if (monthKey in expandedMonths) {
                                            expandedMonths - monthKey
                                        } else {
                                            expandedMonths + monthKey
                                        }
                                    }
                                )
                            }
                            if (monthExpanded) {
                                val dateGroups = projectNotes
                                    .filter {
                                        it.recordedDate.year == year &&
                                            it.recordedDate.monthValue == month
                                    }
                                    .groupBy { it.recordedDate }
                                    .entries
                                    .sortedByDescending { it.key }

                                dateGroups.forEach { (date, dayNotes) ->
                                    item(key = "date_${year}_${month}_$date") {
                                        ProjectTimelineDateHeader(date = date)
                                    }
                                    items(
                                        items = dayNotes.sortedByDescending { it.createdAt },
                                        key = { it.id }
                                    ) { note ->
                                        NoteCard(
                                            note = note,
                                            variant = NoteCardVariant.PROJECT,
                                            onNoteClick = onNoteClick,
                                            projectsById = projectsById,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = AppSpacing.BlockGap)
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
private fun YearHeader(
    year: Int,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onToggle)
            .padding(vertical = AppSpacing.SectionSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = year.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryTextColor
        )
        Icon(
            imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown else Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = SecondaryTextColor
        )
    }
}

@Composable
private fun MonthHeader(
    month: Int,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onToggle)
            .padding(start = 16.dp, top = AppSpacing.SectionSmall, bottom = AppSpacing.SectionSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = monthName(month),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = PrimaryTextColor
        )
        Icon(
            imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown else Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = SecondaryTextColor
        )
    }
}

private fun monthName(month: Int): String = when (month) {
    1 -> "January"
    2 -> "February"
    3 -> "March"
    4 -> "April"
    5 -> "May"
    6 -> "June"
    7 -> "July"
    8 -> "August"
    9 -> "September"
    10 -> "October"
    11 -> "November"
    12 -> "December"
    else -> "Unknown"
}

@Composable
private fun ProjectTimelineDateHeader(date: LocalDate) {
    val dateStr = remember(date) {
        DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH).format(date)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = AppSpacing.SectionSmall, bottom = AppSpacing.SectionSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "📅", fontSize = 14.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = dateStr,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = PrimaryTextColor
        )
    }
}

@Composable
private fun EmptyProjectTimelineState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "📝", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No notes in this project",
                fontSize = 16.sp,
                color = SecondaryTextColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Add notes to this project",
                fontSize = 14.sp,
                color = SecondaryTextColor.copy(alpha = 0.7f)
            )
        }
    }
}
