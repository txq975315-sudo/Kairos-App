package com.example.kairosapplication.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.R
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.core.ui.ThemeAccentColors
import coil.compose.AsyncImage
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.ui.essay.computeWallpaperIsDark
import com.example.kairosapplication.ui.components.EssayCircleIconButton
import com.example.kairosapplication.ui.components.NoteCard
import com.example.kairosapplication.ui.components.NoteCardConstants
import com.example.kairosapplication.ui.components.NoteCardVariant
import com.example.kairosapplication.ui.components.NoteCommentBottomSheet
import com.example.kairosapplication.ui.components.NoteTimelineIntegrated
import com.example.kairosapplication.ui.components.PublishedNoteCardActions
import com.example.kairosapplication.ui.components.TopicNotesYearMonthTimeline
import com.example.kairosapplication.ui.topic.EssayCategoryUi
import com.example.kairosapplication.ui.topic.rememberTopicPrimaryNavShortWithConfig
import com.example.kairosapplication.ui.topic.rememberTopicSecondaryLabelWithConfig
import com.example.kairosapplication.ui.components.appendReviewCommentToNote
import com.example.kairosapplication.ui.project.ProjectTimelineScreen
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.EssayCategoryConfig
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Project
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class EssayTab {
    TIMELINE,
    TOPIC,
    PROJECT
}

/** Fixed order of primary topics on the Topic tab (product spec). */
private val topicTabCategoryOrder = listOf(
    NotePrimaryCategory.SELF_AWARENESS,
    NotePrimaryCategory.INTERPERSONAL,
    NotePrimaryCategory.INTIMACY_FAMILY,
    NotePrimaryCategory.SOMATIC_ENERGY,
    NotePrimaryCategory.MEANING,
    NotePrimaryCategory.FREESTYLE
)

/** Stable bucket for notes without a secondary; official preset spellings live in task-model `NoteSecondaryCategories` and are canonicalized on load/save. */
private const val UncategorizedSecondary = "Uncategorized"

/**
 * Preset secondaries first, then any [groupedBySecondary] keys not in presets (e.g. legacy/custom labels),
 * then [UncategorizedSecondary], so every note appears under some row.
 */
private fun topicSecondaryDisplayOrder(
    presetSecondaries: List<String>,
    groupedBySecondary: Map<String, List<Note>>
): List<String> {
    val presetSet = presetSecondaries.toSet()
    val stray = groupedBySecondary.keys
        .filter { it != UncategorizedSecondary && it !in presetSet }
        .sortedWith { a, b -> a.compareTo(b, ignoreCase = true) }
    return presetSecondaries + stray + UncategorizedSecondary
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EssayMainScreen(
    taskViewModel: TaskViewModel,
    onNavigateToInbox: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToTrash: () -> Unit,
    onNavigateToEditor: (Long?) -> Unit,
    /** Topic tab: create essay with this primary (non-freestyle); editor locks primary. */
    onNavigateToNewNoteFromTopic: (String) -> Unit,
    openTopicTabWithPrimary: String? = null,
    onOpenTopicTabConsumed: () -> Unit = {},
    onOpenTopicManage: () -> Unit = {},
) {
    var selectedTab by remember { mutableStateOf(EssayTab.TIMELINE) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showDatePicker by remember { mutableStateOf(false) }
    var menuOpen by remember { mutableStateOf(false) }
    var expandedNoteId by remember { mutableStateOf<Long?>(null) }
    var changeTopicNoteId by remember { mutableStateOf<Long?>(null) }
    var changeProjectNoteId by remember { mutableStateOf<Long?>(null) }
    var continueCreateNoteId by remember { mutableStateOf<Long?>(null) }
    var deleteConfirmNoteId by remember { mutableStateOf<Long?>(null) }
    var commentNoteId by remember { mutableStateOf<Long?>(null) }
    var topicPrimaryFilter by remember { mutableStateOf<String?>(null) }
    var selectedEssayProjectId by remember { mutableStateOf<Long?>(null) }

    val uiState by taskViewModel.uiState.collectAsState()
    val context = LocalContext.current
    val dataStore = remember(context) { DataStoreManager(context.applicationContext) }
    val timelineLayout by dataStore.getEssayTimelineLayout().collectAsState(initial = "card")
    val integratedLayoutActive = timelineLayout == "integrated"
    val essayWallpaperUri by dataStore.getEssayIntegratedWallpaperUri().collectAsState(initial = null)
    var essayWallpaperIsDark by remember { mutableStateOf(false) }
    val themeColorKey by dataStore.getThemeColor().collectAsState(initial = "blue")
    val timelineAccent = remember(themeColorKey) { ThemeAccentColors.fromSettingsKey(themeColorKey) }
    val scope = rememberCoroutineScope()
    val pickEssayWallpaper = rememberLauncherForActivityResult(
        contract = PickVisualMedia()
    ) { uri ->
        uri?.let { u -> scope.launch { dataStore.setEssayIntegratedWallpaperUri(u.toString()) } }
    }
    LaunchedEffect(essayWallpaperUri, integratedLayoutActive) {
        essayWallpaperIsDark = if (!integratedLayoutActive || essayWallpaperUri.isNullOrBlank()) {
            false
        } else {
            withContext(Dispatchers.IO) {
                computeWallpaperIsDark(context, essayWallpaperUri!!)
            }
        }
    }
    val essayWallpaperActive = integratedLayoutActive && !essayWallpaperUri.isNullOrBlank()
    val essayHeaderPrimary =
        if (essayWallpaperActive && essayWallpaperIsDark) Color.White else PrimaryTextColor
    val essayHeaderSecondary =
        if (essayWallpaperActive && essayWallpaperIsDark) Color.White.copy(alpha = 0.82f)
        else SecondaryTextColor
    val essayTopicSurfaceColor =
        if (essayWallpaperActive) Color.Transparent else AppColors.ScreenBackground
    val inboxCount = uiState.noteInbox.size

    val allTimelineNotes = remember(uiState.notePublished) {
        uiState.notePublished.filter { it.status != NoteStatus.DELETED }
    }
    val projectsByIdMap = remember(uiState.noteProjects) {
        uiState.noteProjects.associate { it.id to it.name }
    }
    val zone = ZoneId.systemDefault()
    val essayLang = LocalCurrentLanguage.current.value
    val essayLocale = if (essayLang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.US
    val dateLabel = remember(selectedDate, essayLang, essayLocale) {
        val pattern =
            if (essayLang == LocalizationManager.Language.ZH) "yyyy年M月d日" else "MMMM d, yyyy"
        DateTimeFormatter.ofPattern(pattern, essayLocale).format(selectedDate)
    }

    val tabs = listOf(
        EssayTab.TIMELINE to LocalizedStrings.get("essay_tab_timeline"),
        EssayTab.TOPIC to LocalizedStrings.get("essay_tab_topic"),
        EssayTab.PROJECT to LocalizedStrings.get("essay_tab_project")
    )

    LaunchedEffect(selectedTab) {
        expandedNoteId = null
        if (selectedTab != EssayTab.PROJECT) {
            selectedEssayProjectId = null
        }
    }

    LaunchedEffect(openTopicTabWithPrimary) {
        val p = openTopicTabWithPrimary ?: return@LaunchedEffect
        if (p in topicTabCategoryOrder) {
            selectedTab = EssayTab.TOPIC
            topicPrimaryFilter = p
            onOpenTopicTabConsumed()
        }
    }

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

    LaunchedEffect(commentNoteId, allTimelineNotes) {
        val id = commentNoteId ?: return@LaunchedEffect
        if (allTimelineNotes.none { it.id == id }) commentNoteId = null
    }
    val commentSheetNote = commentNoteId?.let { id -> allTimelineNotes.find { it.id == id } }
    commentSheetNote?.let { noteForComment ->
        NoteCommentBottomSheet(
            note = noteForComment,
            onDismiss = { commentNoteId = null },
            onAppendComment = { n, text ->
                taskViewModel.updateNote(appendReviewCommentToNote(n, text))
            }
        )
    }

    PublishedNoteActionDialogsHost(
        resolveNote = { id -> allTimelineNotes.find { it.id == id } },
        noteProjects = uiState.noteProjects,
        essayCategoryConfig = uiState.essayCategoryConfig,
        taskViewModel = taskViewModel,
        onNavigateToNewNote = { onNavigateToEditor(null) },
        changeTopicNoteId = changeTopicNoteId,
        onChangeTopicNoteId = { changeTopicNoteId = it },
        changeProjectNoteId = changeProjectNoteId,
        onChangeProjectNoteId = { changeProjectNoteId = it },
        continueCreateNoteId = continueCreateNoteId,
        onContinueCreateNoteId = { continueCreateNoteId = it },
        deleteConfirmNoteId = deleteConfirmNoteId,
        onDeleteConfirmNoteId = { deleteConfirmNoteId = it },
        onNoteDeleted = { nid -> if (expandedNoteId == nid) expandedNoteId = null }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        if (essayWallpaperActive) {
            AsyncImage(
                model = essayWallpaperUri,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = if (essayWallpaperActive) Color.Transparent else BackgroundColor,
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
                        color = essayHeaderPrimary
                    )
                    Text(
                        text = " ▼",
                        fontSize = 14.sp,
                        color = essayHeaderSecondary
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
                                text = { Text(LocalizedStrings.get("topic_manage"), color = PrimaryTextColor) },
                                onClick = {
                                    menuOpen = false
                                    onOpenTopicManage()
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(LocalizedStrings.get("essay_menu_trash"), color = PrimaryTextColor) },
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
                                text = { Text(LocalizedStrings.get("essay_menu_layout_card"), color = essayHeaderPrimary) },
                                leadingIcon = if (timelineLayout == "card") {
                                    {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = essayHeaderPrimary
                                        )
                                    }
                                } else {
                                    null
                                },
                                onClick = {
                                    menuOpen = false
                                    scope.launch { dataStore.setEssayTimelineLayout("card") }
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(LocalizedStrings.get("essay_menu_layout_integrated"), color = essayHeaderPrimary) },
                                leadingIcon = if (timelineLayout == "integrated") {
                                    {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = essayHeaderPrimary
                                        )
                                    }
                                } else {
                                    null
                                },
                                onClick = {
                                    menuOpen = false
                                    scope.launch { dataStore.setEssayTimelineLayout("integrated") }
                                }
                            )
                            if (integratedLayoutActive) {
                                DropdownMenuItem(
                                    text = { Text(LocalizedStrings.get("essay_menu_wallpaper"), color = essayHeaderPrimary) },
                                    onClick = {
                                        menuOpen = false
                                        pickEssayWallpaper.launch(
                                            PickVisualMediaRequest(PickVisualMedia.ImageOnly)
                                        )
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text(LocalizedStrings.get("essay_menu_clear_wallpaper"), color = essayHeaderPrimary) },
                                    onClick = {
                                        menuOpen = false
                                        scope.launch { dataStore.setEssayIntegratedWallpaperUri(null) }
                                    },
                                    enabled = essayWallpaperUri != null
                                )
                            }
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
                            color = if (selected) essayHeaderPrimary else essayHeaderSecondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(
                                    if (selected) essayHeaderPrimary else Color.Transparent
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
                        integratedLayout = integratedLayoutActive,
                        accentColor = timelineAccent,
                        lightForeground = essayWallpaperActive && essayWallpaperIsDark,
                        dayHeaderDayColor = essayHeaderPrimary,
                        dayHeaderSubtitleColor = essayHeaderSecondary,
                        expandedNoteId = expandedNoteId,
                        onToggleExpand = { id ->
                            expandedNoteId = if (expandedNoteId == id) null else id
                        },
                        onOpenNoteEditor = { id -> onNavigateToEditor(id) },
                        projectsById = projectsByIdMap,
                        publishedNoteActions = { note ->
                            PublishedNoteCardActions(
                                onChangeTopic = {
                                    changeTopicNoteId = note.id
                                    expandedNoteId = null
                                },
                                onChangeProject = {
                                    changeProjectNoteId = note.id
                                    expandedNoteId = null
                                },
                                onContinueCreate = {
                                    continueCreateNoteId = note.id
                                    expandedNoteId = null
                                },
                                onDelete = {
                                    deleteConfirmNoteId = note.id
                                    expandedNoteId = null
                                },
                                onComment = {
                                    commentNoteId = note.id
                                    expandedNoteId = null
                                }
                            )
                        }
                    )
                    EssayTab.TOPIC -> TopicTabContent(
                        allNotes = allTimelineNotes,
                        essayCategoryConfig = uiState.essayCategoryConfig,
                        onCreateEssayForPrimary = onNavigateToNewNoteFromTopic,
                        projectsById = projectsByIdMap,
                        integratedLayout = integratedLayoutActive,
                        accentColor = timelineAccent,
                        topicSurfaceColor = essayTopicSurfaceColor,
                        headerPrimary = essayHeaderPrimary,
                        headerSecondary = essayHeaderSecondary,
                        lightForeground = essayWallpaperActive && essayWallpaperIsDark,
                        expandedNoteId = expandedNoteId,
                        onToggleExpand = { id ->
                            expandedNoteId = if (expandedNoteId == id) null else id
                        },
                        onOpenNoteEditor = { id -> onNavigateToEditor(id) },
                        publishedNoteActions = { note ->
                            PublishedNoteCardActions(
                                onChangeTopic = {
                                    changeTopicNoteId = note.id
                                    expandedNoteId = null
                                },
                                onChangeProject = {
                                    changeProjectNoteId = note.id
                                    expandedNoteId = null
                                },
                                onContinueCreate = {
                                    continueCreateNoteId = note.id
                                    expandedNoteId = null
                                },
                                onDelete = {
                                    deleteConfirmNoteId = note.id
                                    expandedNoteId = null
                                },
                                onComment = {
                                    commentNoteId = note.id
                                    expandedNoteId = null
                                }
                            )
                        },
                        primaryFilter = topicPrimaryFilter,
                        onPrimaryFilterChange = { topicPrimaryFilter = it },
                    )
                    EssayTab.PROJECT -> EssayProjectTabPane(
                        projects = uiState.noteProjects.sortedByDescending { it.updatedAt },
                        notes = allTimelineNotes,
                        selectedProjectId = selectedEssayProjectId,
                        onSelectProject = { selectedEssayProjectId = it },
                        onClearProjectSelection = { selectedEssayProjectId = null },
                        taskViewModel = taskViewModel,
                        onOpenNoteEditor = { id -> onNavigateToEditor(id) },
                        onNavigateToNewNote = { onNavigateToEditor(null) },
                        integratedNotes = integratedLayoutActive,
                        accentColor = timelineAccent,
                        contentPrimary = essayHeaderPrimary,
                        contentSecondary = essayHeaderSecondary,
                        lightForeground = essayWallpaperActive && essayWallpaperIsDark,
                        publishedNoteActions = { note ->
                            PublishedNoteCardActions(
                                onChangeTopic = {
                                    changeTopicNoteId = note.id
                                    expandedNoteId = null
                                },
                                onChangeProject = {
                                    changeProjectNoteId = note.id
                                    expandedNoteId = null
                                },
                                onContinueCreate = {
                                    continueCreateNoteId = note.id
                                    expandedNoteId = null
                                },
                                onDelete = {
                                    deleteConfirmNoteId = note.id
                                    expandedNoteId = null
                                },
                                onComment = {
                                    commentNoteId = note.id
                                    expandedNoteId = null
                                }
                            )
                        },
                    )
                }
            }
        }
    }
    }
}

@Composable
private fun TimelineTabContent(
    selectedDate: LocalDate,
    allNotes: List<Note>,
    integratedLayout: Boolean,
    accentColor: Color,
    lightForeground: Boolean,
    dayHeaderDayColor: Color,
    dayHeaderSubtitleColor: Color,
    expandedNoteId: Long?,
    onToggleExpand: (Long) -> Unit,
    onOpenNoteEditor: (Long) -> Unit,
    projectsById: Map<Long, String>,
    publishedNoteActions: (Note) -> PublishedNoteCardActions
) {
    val groupedNoteDays = remember(allNotes) {
        allNotes
            .groupBy { it.recordedDate }
            .mapValues { (_, notes) -> notes.sortedByDescending { it.createdAt } }
            .entries
            .sortedByDescending { it.key }
    }

    val listState = remember(integratedLayout) { LazyListState() }

    LaunchedEffect(selectedDate, groupedNoteDays, integratedLayout) {
        val keys = groupedNoteDays.map { it.key }
        val targetPos = keys.indexOf(selectedDate)
        if (targetPos < 0) return@LaunchedEffect
        if (integratedLayout) {
            if (groupedNoteDays.isEmpty()) return@LaunchedEffect
            listState.animateScrollToItem(targetPos.coerceIn(0, groupedNoteDays.lastIndex))
        } else {
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
    }

    if (groupedNoteDays.isEmpty()) {
        EmptyTimelineState(modifier = Modifier.fillMaxSize())
        return
    }

    if (integratedLayout) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(
                count = groupedNoteDays.size,
                key = { index -> "integrated_day_${groupedNoteDays[index].key}" }
            ) { index ->
                val date = groupedNoteDays[index].key
                val notes = groupedNoteDays[index].value
                Column(modifier = Modifier.fillMaxWidth()) {
                    IntegratedDayHeader(
                        date = date,
                        dayNumberColor = dayHeaderDayColor,
                        subtitleColor = dayHeaderSubtitleColor
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    notes.forEachIndexed { noteIndex, note ->
                        NoteTimelineIntegrated(
                            note = note,
                            accentColor = accentColor,
                            onNoteClick = onOpenNoteEditor,
                            expandable = true,
                            expanded = note.id == expandedNoteId,
                            onToggleExpand = { onToggleExpand(note.id) },
                            modifier = Modifier.fillMaxWidth(),
                            projectsById = projectsById,
                            publishedActions = publishedNoteActions(note),
                            lightForeground = lightForeground
                        )
                        if (noteIndex < notes.lastIndex) {
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    } else {
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
                        onNoteClick = onOpenNoteEditor,
                        expandable = true,
                        expanded = note.id == expandedNoteId,
                        onToggleExpand = { onToggleExpand(note.id) },
                        modifier = Modifier.fillMaxWidth(),
                        projectsById = projectsById,
                        publishedActions = publishedNoteActions(note)
                    )
                }
            }
        }
    }
}

@Composable
private fun IntegratedDayHeader(
    date: LocalDate,
    dayNumberColor: Color = PrimaryTextColor,
    subtitleColor: Color = SecondaryTextColor,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 1.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = dayNumberColor,
            modifier = Modifier.width(32.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(modifier = Modifier.weight(1f)) {
            val lang = LocalCurrentLanguage.current.value
            val loc = if (lang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.US
            val month = remember(date, loc) { date.month.getDisplayName(TextStyle.FULL, loc) }
            val dow = remember(date, loc, lang) {
                val raw = date.dayOfWeek.getDisplayName(TextStyle.FULL, loc)
                if (lang == LocalizationManager.Language.ZH) raw else raw.lowercase(loc)
            }
            Text(
                text = "$month / $dow ${date.year}",
                fontSize = 14.sp,
                color = subtitleColor
            )
        }
    }
}

@Composable
private fun DateGroupHeader(
    date: LocalDate,
    indent: Boolean = false,
    showYear: Boolean = false,
    titleColor: Color = PrimaryTextColor,
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
            color = titleColor
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
                text = LocalizedStrings.get("essay_timeline_empty_title"),
                fontSize = 16.sp,
                color = SecondaryTextColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = LocalizedStrings.get("essay_timeline_empty_subtitle"),
                fontSize = 14.sp,
                color = SecondaryTextColor.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun TopicTabContent(
    allNotes: List<Note>,
    essayCategoryConfig: EssayCategoryConfig,
    onCreateEssayForPrimary: (String) -> Unit,
    projectsById: Map<Long, String>,
    integratedLayout: Boolean,
    accentColor: Color,
    topicSurfaceColor: Color,
    headerPrimary: Color,
    headerSecondary: Color,
    lightForeground: Boolean,
    expandedNoteId: Long?,
    onToggleExpand: (Long) -> Unit,
    onOpenNoteEditor: (Long) -> Unit,
    publishedNoteActions: (Note) -> PublishedNoteCardActions,
    primaryFilter: String?,
    onPrimaryFilterChange: (String?) -> Unit,
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

    var selectedPrimary by remember {
        mutableStateOf(
            topicTabCategoryOrder.firstOrNull { key ->
                groupedByTopic[key].orEmpty().isNotEmpty()
            } ?: topicTabCategoryOrder.first()
        )
    }
    var selectedSecondary by remember { mutableStateOf<String?>(null) }
    var expandedTopicNavPrimaries by remember {
        mutableStateOf(topicTabCategoryOrder.toSet())
    }

    LaunchedEffect(primaryFilter) {
        val p = primaryFilter
        if (p != null && p in topicTabCategoryOrder) {
            selectedPrimary = p
            selectedSecondary = null
            onPrimaryFilterChange(null)
        }
    }

    val filteredNotes = remember(selectedPrimary, selectedSecondary, groupedByTopic) {
        val list = groupedByTopic[selectedPrimary].orEmpty()
        if (selectedSecondary == null) list
        else {
            list.filter { note ->
                val sec = note.secondaryCategory.takeIf { it.isNotBlank() } ?: UncategorizedSecondary
                sec == selectedSecondary
            }
        }
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            color = topicSurfaceColor,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.25f)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(end = 4.dp, top = 4.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                topicTabCategoryOrder.forEach { primaryKey ->
                    val notesForPrimary = groupedByTopic[primaryKey].orEmpty()
                    val secondaryList =
                        EssayCategoryUi.mergedSecondaryIds(primaryKey, essayCategoryConfig)
                    val hasSecondaryCategories =
                        secondaryList.isNotEmpty() && primaryKey != NotePrimaryCategory.FREESTYLE
                    val groupedBySecondary =
                        if (hasSecondaryCategories) {
                            notesForPrimary.groupBy { note ->
                                note.secondaryCategory.takeIf { it.isNotBlank() }
                                    ?: UncategorizedSecondary
                            }
                        } else {
                            emptyMap()
                        }
                    val secondaryOrder =
                        if (hasSecondaryCategories) {
                            topicSecondaryDisplayOrder(secondaryList, groupedBySecondary)
                        } else {
                            emptyList()
                        }

                    item(key = "nav_primary_$primaryKey") {
                        val navExpanded = primaryKey in expandedTopicNavPrimaries
                        TopicNavPrimaryRow(
                            labelShort = rememberTopicPrimaryNavShortWithConfig(primaryKey, essayCategoryConfig),
                            count = notesForPrimary.size,
                            selected = selectedPrimary == primaryKey && selectedSecondary == null,
                            expanded = navExpanded,
                            showExpandToggle = hasSecondaryCategories,
                            primaryText = headerPrimary,
                            secondaryText = headerSecondary,
                            onToggleExpand = {
                                expandedTopicNavPrimaries =
                                    if (primaryKey in expandedTopicNavPrimaries) {
                                        expandedTopicNavPrimaries - primaryKey
                                    } else {
                                        expandedTopicNavPrimaries + primaryKey
                                    }
                            },
                            onSelect = {
                                selectedPrimary = primaryKey
                                selectedSecondary = null
                            },
                        )
                    }

                    if (hasSecondaryCategories && primaryKey in expandedTopicNavPrimaries) {
                        items(
                            items = secondaryOrder,
                            key = { sec -> "nav_sec_${primaryKey}_$sec" }
                        ) { secondaryKey ->
                            val count = groupedBySecondary[secondaryKey].orEmpty().size
                            TopicNavSecondaryRow(
                                primaryKey = primaryKey,
                                secondaryKey = secondaryKey,
                                essayCategoryConfig = essayCategoryConfig,
                                count = count,
                                selected = selectedPrimary == primaryKey && selectedSecondary == secondaryKey,
                                isUngrouped = secondaryKey == UncategorizedSecondary,
                                primaryText = headerPrimary,
                                secondaryText = headerSecondary,
                                hintText = headerSecondary.copy(alpha = 0.75f),
                                onSelect = {
                                    selectedPrimary = primaryKey
                                    selectedSecondary = secondaryKey
                                }
                            )
                        }
                    }
                    item(key = "nav_gap_$primaryKey") {
                        Spacer(Modifier.height(4.dp))
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(Color(0xFFE8E8E8))
        )
        Box(
            modifier = Modifier
                .weight(0.75f)
                .fillMaxHeight()
        ) {
            TopicNotesYearMonthTimeline(
                notes = filteredNotes,
                integratedLayout = integratedLayout,
                accentColor = accentColor,
                lightForeground = lightForeground,
                headerPrimary = headerPrimary,
                headerSecondary = headerSecondary,
                projectsById = projectsById,
                expandedNoteId = expandedNoteId,
                onToggleExpand = onToggleExpand,
                onOpenNoteEditor = onOpenNoteEditor,
                publishedNoteActions = publishedNoteActions,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun TopicNavPrimaryRow(
    labelShort: String,
    count: Int,
    selected: Boolean,
    expanded: Boolean,
    showExpandToggle: Boolean,
    primaryText: Color,
    secondaryText: Color,
    onToggleExpand: () -> Unit,
    onSelect: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) Color(0xFFEEF2FF) else Color.White
        ),
        border = if (selected) {
            BorderStroke(1.dp, Color(0xFF8A7CF8).copy(alpha = 0.45f))
        } else {
            null
        },
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 2.dp, end = 4.dp, top = 2.dp, bottom = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showExpandToggle) {
                IconButton(
                    onClick = onToggleExpand,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = if (expanded) {
                            Icons.Default.KeyboardArrowDown
                        } else {
                            Icons.AutoMirrored.Filled.KeyboardArrowRight
                        },
                        contentDescription = if (expanded) "Collapse" else "Expand",
                        tint = secondaryText,
                        modifier = Modifier.size(18.dp)
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(4.dp))
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = onSelect)
            ) {
                Text(
                    text = labelShort,
                    fontSize = 12.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = primaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "($count)",
                    fontSize = 8.sp,
                    color = secondaryText
                )
            }
        }
    }
}

@Composable
private fun TopicNavSecondaryRow(
    primaryKey: String,
    secondaryKey: String,
    essayCategoryConfig: EssayCategoryConfig,
    count: Int,
    selected: Boolean,
    isUngrouped: Boolean,
    primaryText: Color,
    secondaryText: Color,
    hintText: Color,
    onSelect: () -> Unit,
) {
    val label = rememberTopicSecondaryLabelWithConfig(primaryKey, secondaryKey, essayCategoryConfig)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(
                if (selected) Color(0xFFEEF2FF).copy(alpha = 0.65f) else Color.Transparent
            )
            .clickable(onClick = onSelect)
            .padding(start = 8.dp, end = 4.dp, top = 2.dp, bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            lineHeight = 12.sp,
            fontWeight = when {
                selected -> FontWeight.SemiBold
                isUngrouped -> FontWeight.Normal
                else -> FontWeight.Medium
            },
            color = if (isUngrouped) hintText else primaryText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "($count)",
            fontSize = 8.sp,
            color = secondaryText,
            maxLines = 1
        )
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
                text = LocalizedStrings.get("essay_topic_empty_title"),
                fontSize = 16.sp,
                color = SecondaryTextColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = LocalizedStrings.get("essay_topic_empty_subtitle"),
                fontSize = 14.sp,
                color = SecondaryTextColor.copy(alpha = 0.7f)
            )
        }
    }
}

private fun projectNoteCountsByProject(notes: List<Note>): Map<Long, Int> =
    notes
        .asSequence()
        .filter { it.status != NoteStatus.DELETED }
        .flatMap { note -> note.projectIds.map { pid -> pid to note } }
        .groupBy({ it.first }, { it.second })
        .mapValues { it.value.distinctBy { n -> n.id }.size }

@Composable
private fun EssayProjectTabPane(
    projects: List<Project>,
    notes: List<Note>,
    selectedProjectId: Long?,
    onSelectProject: (Long) -> Unit,
    onClearProjectSelection: () -> Unit,
    taskViewModel: TaskViewModel,
    onOpenNoteEditor: (Long) -> Unit,
    onNavigateToNewNote: () -> Unit,
    integratedNotes: Boolean,
    accentColor: Color,
    contentPrimary: Color,
    contentSecondary: Color,
    lightForeground: Boolean,
    publishedNoteActions: (Note) -> PublishedNoteCardActions,
) {
    val projectNoteCounts = remember(notes) { projectNoteCountsByProject(notes) }
    if (projects.isEmpty()) {
        EmptyProjectState(modifier = Modifier.fillMaxSize())
        return
    }
    val openId = selectedProjectId
    if (openId == null) {
        ProjectTabContent(
            projects = projects,
            noteCounts = projectNoteCounts,
            onProjectClick = onSelectProject
        )
    } else {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) {
            ProjectSidebarRail(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.22f),
                projects = projects,
                noteCounts = projectNoteCounts,
                selectedId = openId,
                onSelectProject = onSelectProject,
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(Color(0xFFE8E8E8))
            )
            Box(
                modifier = Modifier
                    .weight(0.78f)
                    .fillMaxHeight()
            ) {
                ProjectTimelineScreen(
                    projectId = openId,
                    taskViewModel = taskViewModel,
                    onBack = onClearProjectSelection,
                    onNoteClick = onOpenNoteEditor,
                    onNavigateToNewNote = onNavigateToNewNote,
                    embedded = true,
                    integratedNotes = integratedNotes,
                    accentColor = accentColor,
                    contentPrimary = contentPrimary,
                    contentSecondary = contentSecondary,
                    lightForeground = lightForeground,
                    notePublishedActions = publishedNoteActions,
                )
            }
        }
    }
}

@Composable
private fun ProjectSidebarRail(
    modifier: Modifier,
    projects: List<Project>,
    noteCounts: Map<Long, Int>,
    selectedId: Long,
    onSelectProject: (Long) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(end = 6.dp, top = 2.dp, bottom = 12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(
            items = projects,
            key = { it.id }
        ) { project ->
            val selected = project.id == selectedId
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectProject(project.id) },
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (selected) Color(0xFFEEF2FF) else Color.White
                ),
                border = if (selected) {
                    BorderStroke(1.dp, Color(0xFF8A7CF8).copy(alpha = 0.45f))
                } else {
                    null
                },
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(text = "📁", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = project.name,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = PrimaryTextColor,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${noteCounts[project.id] ?: 0} notes",
                        fontSize = 11.sp,
                        color = SecondaryTextColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectTabContent(
    projects: List<Project>,
    noteCounts: Map<Long, Int>,
    onProjectClick: (Long) -> Unit
) {
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
                noteCount = noteCounts[project.id] ?: 0,
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
