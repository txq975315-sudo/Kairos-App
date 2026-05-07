package com.example.kairosapplication.ui.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.ui.PublishedNoteActionDialogsHost
import com.example.kairosapplication.ui.components.NoteCard
import com.example.kairosapplication.ui.components.NoteCardVariant
import com.example.kairosapplication.ui.components.NoteTimelineIntegrated
import com.example.kairosapplication.ui.components.PublishedNoteCardActions
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Note
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectTimelineScreen(
    projectId: Long,
    taskViewModel: TaskViewModel,
    onBack: () -> Unit,
    onNoteClick: (Long) -> Unit,
    onNavigateToNewNote: () -> Unit,
    /** When true: no [Scaffold] top bar; use under Essay tabs with external navigation. */
    embedded: Boolean = false,
    integratedNotes: Boolean = false,
    accentColor: Color = Color(0xFF5C6BC0),
    contentPrimary: Color = PrimaryTextColor,
    contentSecondary: Color = SecondaryTextColor,
    lightForeground: Boolean = false,
    /** When set (e.g. Essay tab), used for note action row; otherwise falls back to publish-only actions. */
    notePublishedActions: ((Note) -> PublishedNoteCardActions)? = null,
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
    val essayCountByYear = remember(projectNotes) {
        projectNotes.groupingBy { it.recordedDate.year }.eachCount()
    }
    val essayCountByYearMonth = remember(projectNotes) {
        projectNotes.groupingBy { it.recordedDate.year to it.recordedDate.monthValue }.eachCount()
    }

    var expandedYears by remember(projectNotes) {
        mutableStateOf(projectNotes.map { it.recordedDate.year }.toSet())
    }
    var expandedMonths by remember(projectNotes) {
        mutableStateOf(
            projectNotes.map { it.recordedDate.year to it.recordedDate.monthValue }.toSet()
        )
    }
    var expandedPublishedNoteId by remember { mutableStateOf<Long?>(null) }
    var changeTopicNoteId by remember { mutableStateOf<Long?>(null) }
    var changeProjectNoteId by remember { mutableStateOf<Long?>(null) }
    var continueCreateNoteId by remember { mutableStateOf<Long?>(null) }
    var deleteConfirmNoteId by remember { mutableStateOf<Long?>(null) }

    PublishedNoteActionDialogsHost(
        resolveNote = { id ->
            projectNotes.find { it.id == id }
                ?: uiState.notePublished.find { it.id == id }
        },
        noteProjects = uiState.noteProjects,
        essayCategoryConfig = uiState.essayCategoryConfig,
        taskViewModel = taskViewModel,
        onNavigateToNewNote = onNavigateToNewNote,
        changeTopicNoteId = changeTopicNoteId,
        onChangeTopicNoteId = { changeTopicNoteId = it },
        changeProjectNoteId = changeProjectNoteId,
        onChangeProjectNoteId = { changeProjectNoteId = it },
        continueCreateNoteId = continueCreateNoteId,
        onContinueCreateNoteId = { continueCreateNoteId = it },
        deleteConfirmNoteId = deleteConfirmNoteId,
        onDeleteConfirmNoteId = { deleteConfirmNoteId = it },
        onNoteDeleted = { nid ->
            if (expandedPublishedNoteId == nid) expandedPublishedNoteId = null
        }
    )

    fun resolvePublishedActions(note: Note): PublishedNoteCardActions? {
        notePublishedActions?.invoke(note)?.let { return it }
        return if (note.status == NoteStatus.PUBLISHED) {
            PublishedNoteCardActions(
                onChangeTopic = {
                    changeTopicNoteId = note.id
                    expandedPublishedNoteId = null
                },
                onChangeProject = {
                    changeProjectNoteId = note.id
                    expandedPublishedNoteId = null
                },
                onContinueCreate = {
                    continueCreateNoteId = note.id
                    expandedPublishedNoteId = null
                },
                onDelete = {
                    deleteConfirmNoteId = note.id
                    expandedPublishedNoteId = null
                },
                onComment = { onNoteClick(note.id) }
            )
        } else {
            null
        }
    }

    val listContent: @Composable (Modifier) -> Unit = { mod ->
        LazyColumn(
            modifier = mod,
            contentPadding = if (embedded) {
                PaddingValues(bottom = 12.dp)
            } else {
                PaddingValues(horizontal = AppSpacing.PageHorizontal)
            },
            verticalArrangement = Arrangement.spacedBy(0.dp)
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
                    val yearEssayCount = essayCountByYear[year] ?: 0
                    item(key = "year_$year") {
                        YearHeader(
                            year = year,
                            essayCount = yearEssayCount,
                            isExpanded = yearExpanded,
                            onToggle = {
                                expandedYears = if (year in expandedYears) {
                                    expandedYears - year
                                } else {
                                    expandedYears + year
                                }
                            },
                            titleColor = contentPrimary,
                            iconTint = contentSecondary,
                            useGutter = !integratedNotes
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
                            val monthEssayCount = essayCountByYearMonth[year to month] ?: 0
                            item(key = "month_${year}_$month") {
                                MonthHeader(
                                    month = month,
                                    essayCount = monthEssayCount,
                                    isExpanded = monthExpanded,
                                    onToggle = {
                                        expandedMonths = if (monthKey in expandedMonths) {
                                            expandedMonths - monthKey
                                        } else {
                                            expandedMonths + monthKey
                                        }
                                    },
                                    titleColor = contentPrimary,
                                    iconTint = contentSecondary,
                                    useGutter = !integratedNotes,
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
                                        ProjectTimelineDateHeader(
                                            date = date,
                                            titleColor = contentPrimary,
                                            useGutter = !integratedNotes,
                                            integratedNotesLayout = integratedNotes,
                                        )
                                    }
                                    items(
                                        items = dayNotes.sortedByDescending { it.createdAt },
                                        key = { it.id }
                                    ) { note ->
                                        if (integratedNotes) {
                                            NoteTimelineIntegrated(
                                                note = note,
                                                accentColor = accentColor,
                                                onNoteClick = onNoteClick,
                                                projectsById = projectsById,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(bottom = 12.dp),
                                                expandable = true,
                                                expanded = note.id == expandedPublishedNoteId,
                                                onToggleExpand = {
                                                    expandedPublishedNoteId =
                                                        if (expandedPublishedNoteId == note.id) {
                                                            null
                                                        } else {
                                                            note.id
                                                        }
                                                },
                                                publishedActions = resolvePublishedActions(note),
                                                lightForeground = lightForeground
                                            )
                                        } else {
                                            ProjectTimelineGutterRow {
                                                NoteCard(
                                                    note = note,
                                                    variant = NoteCardVariant.PROJECT,
                                                    onNoteClick = onNoteClick,
                                                    projectsById = projectsById,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(bottom = AppSpacing.BlockGap),
                                                    expandable = true,
                                                    expanded = note.id == expandedPublishedNoteId,
                                                    onToggleExpand = {
                                                        expandedPublishedNoteId =
                                                            if (expandedPublishedNoteId == note.id) {
                                                                null
                                                            } else {
                                                                note.id
                                                            }
                                                    },
                                                    publishedActions = resolvePublishedActions(note)
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
    }

    if (embedded) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = contentPrimary
                    )
                }
                Text(
                    text = "$projectName (${projectNotes.size})",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = contentPrimary,
                    modifier = Modifier.weight(1f),
                )
            }
            listContent(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(
                        start = 8.dp,
                        end = AppSpacing.PageHorizontal,
                        top = 4.dp,
                    )
            )
        }
    } else {
        Scaffold(
            containerColor = BackgroundColor,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "$projectName (${projectNotes.size})",
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
            listContent(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        }
    }
}

@Composable
private fun ProjectTimelineGutterRow(
    modifier: Modifier = Modifier,
    showGutter: Boolean = true,
    content: @Composable () -> Unit
) {
    if (!showGutter) {
        Box(modifier = modifier.fillMaxWidth()) {
            content()
        }
        return
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .width(14.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(AppColors.TimelineLine)
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            content()
        }
    }
}

@Composable
private fun YearHeader(
    year: Int,
    essayCount: Int,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    titleColor: Color = PrimaryTextColor,
    iconTint: Color = SecondaryTextColor,
    useGutter: Boolean = true,
) {
    ProjectTimelineGutterRow(showGutter = useGutter) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onToggle)
                .padding(vertical = AppSpacing.SectionSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${year} ($essayCount)",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = titleColor
            )
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown else Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = iconTint
            )
        }
    }
}

@Composable
private fun MonthHeader(
    month: Int,
    essayCount: Int,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    titleColor: Color = PrimaryTextColor,
    iconTint: Color = SecondaryTextColor,
    useGutter: Boolean = true,
) {
    val lang = LocalCurrentLanguage.current.value
    val locale = if (lang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.US
    val monthLabel = remember(month, lang, locale) {
        val style = if (lang == LocalizationManager.Language.ZH) TextStyle.FULL else TextStyle.SHORT
        Month.of(month).getDisplayName(style, locale)
    }
    ProjectTimelineGutterRow(showGutter = useGutter) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onToggle)
                .padding(start = 8.dp, top = AppSpacing.SectionSmall, bottom = AppSpacing.SectionSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$monthLabel ($essayCount)",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = titleColor
            )
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown else Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = iconTint
            )
        }
    }
}

@Composable
private fun ProjectTimelineDateHeader(
    date: LocalDate,
    titleColor: Color = PrimaryTextColor,
    useGutter: Boolean = true,
    /** Matches [NoteTimelineIntegrated] rail width: day num column aligns with the timeline. */
    integratedNotesLayout: Boolean = false,
) {
    val lang = LocalCurrentLanguage.current.value
    val locale = if (lang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.US
    val monthPart = remember(date, locale, lang) {
        val style = if (lang == LocalizationManager.Language.ZH) TextStyle.FULL else TextStyle.SHORT
        date.month.getDisplayName(style, locale)
    }
    val dayStr = remember(date) { date.dayOfMonth.toString() }
    val monthYear = remember(monthPart, date.year) { "$monthPart/${date.year}" }

    @Composable
    fun dateRow(modifier: Modifier = Modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = if (integratedNotesLayout) 0.dp else 8.dp,
                    top = AppSpacing.SectionSmall,
                    bottom = AppSpacing.SectionSmall
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (integratedNotesLayout) {
                Box(
                    modifier = Modifier.width(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = dayStr,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = titleColor
                    )
                }
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = monthYear,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = titleColor
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = dayStr,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = titleColor
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = monthYear,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = titleColor
                    )
                }
            }
        }
    }

    if (integratedNotesLayout) {
        dateRow()
    } else {
        ProjectTimelineGutterRow(showGutter = useGutter) {
            dateRow()
        }
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
