package com.example.kairosapplication.ui

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.material3.Surface
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
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.ui.components.EssayCircleIconButton
import com.example.kairosapplication.ui.components.NoteCard
import com.example.kairosapplication.ui.components.NoteCardConstants
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

private val topicTabEmojiIcon: Map<String, String> = mapOf(
    NotePrimaryCategory.FREESTYLE to "🎨",
    NotePrimaryCategory.SELF_AWARENESS to "🧠",
    NotePrimaryCategory.INTERPERSONAL to "👥",
    NotePrimaryCategory.INTIMACY_FAMILY to "❤️",
    NotePrimaryCategory.SOMATIC_ENERGY to "💪",
    NotePrimaryCategory.MEANING to "✨"
)

/** Secondary topic labels (English); must match stored [Note.secondaryCategory] for grouping. */
private const val UncategorizedSecondary = "Uncategorized"

private val secondaryCategoryHierarchy: Map<String, List<String>> = mapOf(
    NotePrimaryCategory.SELF_AWARENESS to listOf(
        "Emotional awareness",
        "Behavioral habits",
        "Thinking blind spots",
        "Value exploration"
    ),
    NotePrimaryCategory.INTERPERSONAL to listOf(
        "Communication style",
        "Social skills",
        "Boundary setting",
        "Relationship maintenance"
    ),
    NotePrimaryCategory.INTIMACY_FAMILY to listOf(
        "Partner relationship",
        "Parent-child interaction",
        "Family communication",
        "Family-of-origin impact"
    ),
    NotePrimaryCategory.SOMATIC_ENERGY to listOf(
        "Body sensations",
        "Energy management",
        "Exercise & rest",
        "Sleep quality"
    ),
    NotePrimaryCategory.MEANING to listOf(
        "Career direction",
        "Life goals",
        "Meaning questions",
        "Self-actualization"
    ),
    NotePrimaryCategory.FREESTYLE to listOf(
        "Daily log",
        "Inspiration notes",
        "Other"
    )
)

private val secondaryCategoryEmojis: Map<String, String> = mapOf(
    "Emotional awareness" to "📝",
    "Behavioral habits" to "📊",
    "Thinking blind spots" to "🧩",
    "Value exploration" to "💎",
    "Communication style" to "💬",
    "Social skills" to "🤝",
    "Boundary setting" to "🛡️",
    "Relationship maintenance" to "🌸",
    "Partner relationship" to "💑",
    "Parent-child interaction" to "👶",
    "Family communication" to "🏠",
    "Family-of-origin impact" to "🔗",
    "Body sensations" to "🫀",
    "Energy management" to "⚡",
    "Exercise & rest" to "🏃",
    "Sleep quality" to "😴",
    "Career direction" to "💼",
    "Life goals" to "🎯",
    "Meaning questions" to "🔍",
    "Self-actualization" to "🌟",
    "Daily log" to "📅",
    "Inspiration notes" to "💡",
    "Other" to "📎",
    UncategorizedSecondary to "🏷️"
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
        EssayTab.TIMELINE to "Timeline",
        EssayTab.TOPIC to "Topic",
        EssayTab.PROJECT to "Project"
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
            items(count = 1, key = { _ -> "header_$date" }) { _ ->
                DateGroupHeader(date = date, showYear = true)
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
private fun DateGroupHeader(
    date: LocalDate,
    indent: Boolean = false,
    showYear: Boolean = false
) {
    val dateStr = remember(date, showYear) {
        val pattern = if (showYear) "MMMM d, yyyy" else "MMMM d"
        DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH).format(date)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = if (indent) 48.dp else 0.dp,
                top = AppSpacing.SectionSmall,
                bottom = AppSpacing.SectionSmall
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
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

@OptIn(ExperimentalFoundationApi::class)
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
    var expandedSecondaries by remember {
        mutableStateOf(emptySet<String>())
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        topicTabCategoryOrder.forEachIndexed { index, primaryKey ->
            val notesForPrimary = groupedByTopic[primaryKey].orEmpty()
            val isPrimaryExpanded = primaryKey in expandedTopics
            val secondaryList = secondaryCategoryHierarchy[primaryKey].orEmpty()
            val hasSecondaryCategories =
                secondaryList.isNotEmpty() && primaryKey != NotePrimaryCategory.FREESTYLE

            stickyHeader(key = "primary_sticky_$primaryKey") {
                Surface(
                    color = AppColors.ScreenBackground,
                    shadowElevation = if (index > 0) 2.dp else 0.dp
                ) {
                    TopicGroupHeader(
                        label = NoteCardConstants.topicTabEnglishLabel(primaryKey),
                        icon = topicTabEmojiIcon[primaryKey] ?: "📝",
                        count = notesForPrimary.size,
                        isExpanded = isPrimaryExpanded,
                        onToggle = {
                            expandedTopics = if (primaryKey in expandedTopics) {
                                expandedTopics - primaryKey
                            } else {
                                expandedTopics + primaryKey
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = if (index > 0) AppSpacing.SectionLarge else 0.dp)
                    )
                }
            }

            if (isPrimaryExpanded && notesForPrimary.isNotEmpty()) {
                if (hasSecondaryCategories) {
                    val groupedBySecondary = notesForPrimary.groupBy { note ->
                        note.secondaryCategory.takeIf { it.isNotBlank() } ?: UncategorizedSecondary
                    }
                    val secondaryOrder = secondaryList + UncategorizedSecondary

                    secondaryOrder.forEach { secondaryKey ->
                        val notesForSecondary = groupedBySecondary[secondaryKey].orEmpty()
                        val secondaryKeyFull = "$primaryKey:$secondaryKey"
                        val isSecondaryExpanded = secondaryKeyFull in expandedSecondaries
                        val isUngrouped = secondaryKey == UncategorizedSecondary

                        if (!isUngrouped && notesForSecondary.isEmpty()) {
                            return@forEach
                        }

                        stickyHeader(key = "secondary_sticky_$secondaryKeyFull") {
                            Surface(
                                color = AppColors.ScreenBackground,
                                shadowElevation = 1.dp
                            ) {
                                SecondaryTopicGroupHeader(
                                    secondaryKey = secondaryKey,
                                    label = secondaryKey,
                                    count = notesForSecondary.size,
                                    isExpanded = isSecondaryExpanded,
                                    isUngrouped = isUngrouped,
                                    onToggle = {
                                        expandedSecondaries = if (secondaryKeyFull in expandedSecondaries) {
                                            expandedSecondaries - secondaryKeyFull
                                        } else {
                                            expandedSecondaries + secondaryKeyFull
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        if (isSecondaryExpanded && notesForSecondary.isNotEmpty()) {
                            val groupedByDate = notesForSecondary
                                .groupBy { it.recordedDate }
                                .toList()
                                .sortedByDescending { (date, _) -> date }

                            groupedByDate.forEach { (date, dateNotes) ->
                                items(
                                    count = 1,
                                    key = { _ -> "date_header_${secondaryKeyFull}_$date" }
                                ) { _ ->
                                    DateGroupHeader(date = date, indent = true)
                                }
                                items(
                                    items = dateNotes,
                                    key = { it.id }
                                ) { note ->
                                    NoteCard(
                                        note = note,
                                        variant = NoteCardVariant.TOPIC,
                                        onNoteClick = onOpenNote,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                start = 48.dp,
                                                bottom = AppSpacing.BlockGap
                                            )
                                    )
                                }
                            }
                        }
                    }
                } else {
                    val groupedByDate = notesForPrimary
                        .groupBy { it.recordedDate }
                        .toList()
                        .sortedByDescending { (date, _) -> date }

                    groupedByDate.forEach { (date, dateNotes) ->
                        items(
                            count = 1,
                            key = { _ -> "date_header_${primaryKey}_$date" }
                        ) { _ ->
                            DateGroupHeader(date = date, indent = true)
                        }
                        items(
                            items = dateNotes,
                            key = { it.id }
                        ) { note ->
                            NoteCard(
                                note = note,
                                variant = NoteCardVariant.TOPIC,
                                onNoteClick = onOpenNote,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 24.dp,
                                        bottom = AppSpacing.BlockGap
                                    )
                            )
                        }
                    }
                }
            }

            if (index < topicTabCategoryOrder.lastIndex) {
                items(
                    count = 1,
                    key = { _ -> "spacer_$primaryKey" }
                ) { _ ->
                    Spacer(modifier = Modifier.height(AppSpacing.SectionLarge))
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
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onToggle),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.CardBackground),
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
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight,
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                tint = SecondaryTextColor,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun SecondaryTopicGroupHeader(
    secondaryKey: String,
    label: String,
    count: Int,
    isExpanded: Boolean,
    isUngrouped: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val icon = secondaryCategoryEmojis[secondaryKey] ?: "📎"
    val arrowIcon =
        if (isExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 24.dp,
                top = AppSpacing.BlockGap,
                end = 0.dp,
                bottom = 0.dp
            )
            .clickable(onClick = onToggle),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUngrouped) {
                AppColors.MorningBackground.copy(alpha = 0.3f)
            } else {
                AppColors.CardBackground
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 14.dp,
                    vertical = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = arrowIcon,
                    contentDescription = null,
                    tint = AppColors.SecondaryText,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = icon, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = label,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.PrimaryText
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "($count)",
                    fontSize = 13.sp,
                    color = AppColors.SecondaryText
                )
            }
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
