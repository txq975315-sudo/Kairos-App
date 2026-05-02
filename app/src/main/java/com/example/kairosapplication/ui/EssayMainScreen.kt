package com.example.kairosapplication.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.R
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.ui.components.EssayCircleIconButton
import com.example.kairosapplication.ui.components.NoteCard
import com.example.kairosapplication.ui.components.NoteCardVariant
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Project
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

enum class EssayTab {
    TIMELINE,
    TOPIC,
    PROJECT
}

/** 课题 Tab 固定顺序（与需求一致） */
private val topicTabCategoryOrder = listOf(
    NotePrimaryCategory.SELF_AWARENESS,
    NotePrimaryCategory.INTERPERSONAL,
    NotePrimaryCategory.INTIMACY_FAMILY,
    NotePrimaryCategory.SOMATIC_ENERGY,
    NotePrimaryCategory.MEANING,
    NotePrimaryCategory.FREESTYLE
)

private val topicTabEnglishLabel: Map<String, String> = mapOf(
    NotePrimaryCategory.FREESTYLE to "Freestyle",
    NotePrimaryCategory.SELF_AWARENESS to "Self-Awareness",
    NotePrimaryCategory.INTERPERSONAL to "Interpersonal",
    NotePrimaryCategory.INTIMACY_FAMILY to "Intimacy & Family",
    NotePrimaryCategory.SOMATIC_ENERGY to "Health & Energy",
    NotePrimaryCategory.MEANING to "Meaning"
)

private val topicTabEmojiIcon: Map<String, String> = mapOf(
    NotePrimaryCategory.FREESTYLE to "🎨",
    NotePrimaryCategory.SELF_AWARENESS to "🧠",
    NotePrimaryCategory.INTERPERSONAL to "👥",
    NotePrimaryCategory.INTIMACY_FAMILY to "❤️",
    NotePrimaryCategory.SOMATIC_ENERGY to "💪",
    NotePrimaryCategory.MEANING to "✨"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EssayMainScreen(
    taskViewModel: TaskViewModel,
    onNavigateToInbox: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToTrash: () -> Unit,
    onNavigateToEditor: (Long?) -> Unit,
    onNavigateToProject: (Long) -> Unit
) {
    var selectedTab by remember { mutableStateOf(EssayTab.TIMELINE) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showDatePicker by remember { mutableStateOf(false) }
    var menuOpen by remember { mutableStateOf(false) }

    val uiState by taskViewModel.uiState.collectAsState()
    val inboxCount = uiState.noteInbox.size

    val allTimelineNotes = remember(uiState.notePublished) {
        uiState.notePublished.filter { it.status != NoteStatus.DELETED }
    }
    val zone = ZoneId.systemDefault()
    val dateLabel = remember(selectedDate) {
        DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH).format(selectedDate)
    }

    val tabs = listOf(
        EssayTab.TIMELINE to "时间轴",
        EssayTab.TOPIC to "课题",
        EssayTab.PROJECT to "项目"
    )

    if (showDatePicker) {
        val millis = selectedDate.atStartOfDay(zone).toInstant().toEpochMilli()
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = millis)
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { m ->
                            selectedDate = Instant.ofEpochMilli(m).atZone(zone).toLocalDate()
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK", color = PrimaryTextColor)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel", color = SecondaryTextColor)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = BackgroundColor,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateToEditor(null) },
                containerColor = Color(0xFF8A7CF8),
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_feather),
                    contentDescription = "New note",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = AppSpacing.PageHorizontal)
        ) {
            Spacer(modifier = Modifier.height(AppSpacing.SectionSmall))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.clickable { showDatePicker = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = dateLabel,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryTextColor
                    )
                    Text(
                        text = " ▼",
                        fontSize = 14.sp,
                        color = SecondaryTextColor
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BadgedBox(
                        badge = {
                            if (inboxCount > 0) {
                                Badge { Text(text = inboxCount.toString()) }
                            }
                        }
                    ) {
                        EssayCircleIconButton(
                            imageVector = Icons.Default.Inbox,
                            contentDescription = "Inbox",
                            onClick = onNavigateToInbox
                        )
                    }
                    EssayCircleIconButton(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        onClick = onNavigateToSearch
                    )
                    Box {
                        EssayCircleIconButton(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options",
                            onClick = { menuOpen = true }
                        )
                        DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
                            DropdownMenuItem(
                                text = { Text("Trash", color = PrimaryTextColor) },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null,
                                        tint = SecondaryTextColor
                                    )
                                },
                                onClick = {
                                    menuOpen = false
                                    onNavigateToTrash()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("View settings", color = PrimaryTextColor) },
                                onClick = { menuOpen = false }
                            )
                            DropdownMenuItem(
                                text = { Text("Sort by date", color = PrimaryTextColor) },
                                onClick = { menuOpen = false }
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = AppSpacing.SectionLarge),
                horizontalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                tabs.forEach { (tab, label) ->
                    val selected = selectedTab == tab
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedTab = tab }
                            .padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = label,
                            fontSize = 15.sp,
                            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                            color = if (selected) PrimaryTextColor else SecondaryTextColor
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(
                                    if (selected) PrimaryTextColor else Color.Transparent
                                )
                        )
                    }
                }
            }

            AnimatedContent(
                targetState = selectedTab,
                transitionSpec = {
                    fadeIn(animationSpec = tween(200)) togetherWith
                        fadeOut(animationSpec = tween(200))
                },
                label = "essayTabContent",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { tab ->
                when (tab) {
                    EssayTab.TIMELINE -> TimelineTabContent(
                        selectedDate = selectedDate,
                        allNotes = allTimelineNotes,
                        onOpenNote = { id -> onNavigateToEditor(id) }
                    )
                    EssayTab.TOPIC -> TopicTabContent(
                        allNotes = allTimelineNotes,
                        onOpenNote = { id -> onNavigateToEditor(id) }
                    )
                    EssayTab.PROJECT -> ProjectTabContent(
                        projects = uiState.noteProjects.sortedByDescending { it.updatedAt },
                        notes = allTimelineNotes,
                        onProjectClick = onNavigateToProject
                    )
                }
            }
        }
    }
}

@Composable
private fun TimelineTabContent(
    selectedDate: LocalDate,
    allNotes: List<Note>,
    onOpenNote: (Long) -> Unit
) {
    val groupedNoteDays = remember(allNotes) {
        allNotes
            .groupBy { it.recordedDate }
            .mapValues { (_, notes) -> notes.sortedByDescending { it.createdAt } }
            .entries
            .sortedByDescending { it.key }
    }

    val listState = rememberLazyListState()

    LaunchedEffect(selectedDate, groupedNoteDays) {
        val keys = groupedNoteDays.map { it.key }
        val targetPos = keys.indexOf(selectedDate)
        if (targetPos < 0) return@LaunchedEffect
        var scrollToIndex = 0
        for (i in 0 until targetPos) {
            scrollToIndex += 1
            scrollToIndex += groupedNoteDays[i].value.size
        }
        val totalItems = groupedNoteDays.sumOf { 1 + it.value.size }
        if (totalItems == 0) return@LaunchedEffect
        val maxIndex = totalItems - 1
        listState.animateScrollToItem(scrollToIndex.coerceIn(0, maxIndex))
    }

    if (groupedNoteDays.isEmpty()) {
        EmptyTimelineState(modifier = Modifier.fillMaxSize())
        return
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.SectionLarge)
    ) {
        groupedNoteDays.forEach { (date, notes) ->
            item(key = "header_$date") {
                DateGroupHeader(date = date)
            }
            items(
                items = notes,
                key = { it.id }
            ) { note ->
                NoteCard(
                    note = note,
                    variant = NoteCardVariant.TIMELINE,
                    onNoteClick = onOpenNote,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun DateGroupHeader(date: LocalDate) {
    val dateStr = remember(date) {
        DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH).format(date)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AppSpacing.SectionSmall),
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
private fun EmptyTimelineState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "📝", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No notes yet",
                fontSize = 16.sp,
                color = SecondaryTextColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Start your first entry",
                fontSize = 14.sp,
                color = SecondaryTextColor.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun TopicTabContent(
    allNotes: List<Note>,
    onOpenNote: (Long) -> Unit
) {
    if (allNotes.isEmpty()) {
        EmptyTopicState(modifier = Modifier.fillMaxSize())
        return
    }

    val groupedByTopic = remember(allNotes) {
        allNotes
            .filter { it.primaryCategory in topicTabCategoryOrder }
            .groupBy { it.primaryCategory }
            .mapValues { (_, list) -> list.sortedByDescending { it.createdAt } }
    }

    var expandedTopics by remember {
        mutableStateOf(topicTabCategoryOrder.toSet())
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.SectionLarge)
    ) {
        topicTabCategoryOrder.forEach { topicKey ->
            val notesForTopic = groupedByTopic[topicKey].orEmpty()
            val isExpanded = topicKey in expandedTopics

            item(key = "topic_header_$topicKey") {
                TopicGroupHeader(
                    label = topicTabEnglishLabel[topicKey] ?: topicKey,
                    icon = topicTabEmojiIcon[topicKey] ?: "📝",
                    count = notesForTopic.size,
                    isExpanded = isExpanded,
                    onToggle = {
                        expandedTopics = if (topicKey in expandedTopics) {
                            expandedTopics - topicKey
                        } else {
                            expandedTopics + topicKey
                        }
                    }
                )
            }

            if (isExpanded && notesForTopic.isNotEmpty()) {
                items(
                    items = notesForTopic,
                    key = { it.id }
                ) { note ->
                    NoteCard(
                        note = note,
                        variant = NoteCardVariant.TOPIC,
                        onNoteClick = onOpenNote,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = AppSpacing.BlockGap)
                    )
                }
            }
        }
    }
}

@Composable
private fun TopicGroupHeader(
    label: String,
    icon: String,
    count: Int,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onToggle),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = icon, fontSize = 20.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = label,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = PrimaryTextColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "($count)",
                    fontSize = 14.sp,
                    color = SecondaryTextColor
                )
            }
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                tint = SecondaryTextColor
            )
        }
    }
}

@Composable
private fun EmptyTopicState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "📚", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No topic notes yet",
                fontSize = 16.sp,
                color = SecondaryTextColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Start your first topic entry",
                fontSize = 14.sp,
                color = SecondaryTextColor.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun ProjectTabContent(
    projects: List<Project>,
    notes: List<Note>,
    onProjectClick: (Long) -> Unit
) {
    val projectNoteCounts = remember(notes) {
        notes
            .asSequence()
            .filter { it.status != NoteStatus.DELETED }
            .flatMap { note -> note.projectIds.map { pid -> pid to note } }
            .groupBy({ it.first }, { it.second })
            .mapValues { it.value.distinctBy { n -> n.id }.size }
    }

    if (projects.isEmpty()) {
        EmptyProjectState(modifier = Modifier.fillMaxSize())
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.BlockGap)
    ) {
        items(
            items = projects,
            key = { it.id }
        ) { project ->
            ProjectSummaryCard(
                project = project,
                noteCount = projectNoteCounts[project.id] ?: 0,
                onClick = { onProjectClick(project.id) }
            )
        }
    }
}

@Composable
private fun ProjectSummaryCard(
    project: Project,
    noteCount: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "📁", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = project.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = PrimaryTextColor
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$noteCount notes",
                    fontSize = 14.sp,
                    color = SecondaryTextColor
                )
                Text(
                    text = "Updated ${formatRelativeTime(project.updatedAt)}",
                    fontSize = 12.sp,
                    color = SecondaryTextColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}

private fun formatRelativeTime(updatedAt: Long): String {
    val now = System.currentTimeMillis()
    val diffMs = (now - updatedAt).coerceAtLeast(0L)
    val minutes = diffMs / (60 * 1000)
    val hours = diffMs / (60 * 60 * 1000)
    val days = diffMs / (24 * 60 * 60 * 1000)
    return when {
        days >= 1 -> "${days}d ago"
        hours >= 1 -> "${hours}h ago"
        minutes >= 1 -> "${minutes}m ago"
        else -> "just now"
    }
}

@Composable
private fun EmptyProjectState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "📂", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No projects yet",
                fontSize = 16.sp,
                color = SecondaryTextColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Add projects to organize your notes",
                fontSize = 14.sp,
                color = SecondaryTextColor.copy(alpha = 0.7f)
            )
        }
    }
}
