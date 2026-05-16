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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
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
import com.example.kairosapplication.core.ui.AppInteraction
import com.example.kairosapplication.core.ui.AppMaterials
import com.example.kairosapplication.core.ui.AppShapes
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
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.UserVisibleStrings
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
    var showCardBgSheet by remember { mutableStateOf(false) }

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

    // Card-mode background state
    val cardBgType by dataStore.getEssayCardBgType().collectAsState(initial = "none")
    val cardBgColor by dataStore.getEssayCardBgColor().collectAsState(initial = "#FFFFFF")
    val cardBgImageUri by dataStore.getEssayCardBgImageUri().collectAsState(initial = null)
    val cardBgOpacity by dataStore.getEssayCardBgOpacity().collectAsState(initial = 1f)

    val pickEssayWallpaper = rememberLauncherForActivityResult(
        contract = PickVisualMedia()
    ) { uri ->
        uri?.let { u -> scope.launch { dataStore.setEssayIntegratedWallpaperUri(u.toString()) } }
    }
    val pickCardBgImage = rememberLauncherForActivityResult(
        contract = PickVisualMedia()
    ) { uri ->
        uri?.let { u ->
            scope.launch {
                dataStore.setEssayCardBgImageUri(u.toString())
                dataStore.setEssayCardBgType("image")
            }
        }
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
        if (essayWallpaperActive) Color.Transparent else AppMaterials.StripOverAtmosphere

    // Compute card background override
    val cardBackgroundOverride = remember(cardBgType, cardBgColor, cardBgImageUri, cardBgOpacity) {
        when (cardBgType) {
            "color" -> try {
                Color(android.graphics.Color.parseColor(cardBgColor)).copy(alpha = cardBgOpacity)
            } catch (_: Exception) { null }
            "image" -> Color.White.copy(alpha = cardBgOpacity)
            else -> null
        }
    }
    val inboxCount = uiState.noteInbox.size

    val allTimelineNotes = remember(uiState.notePublished) {
        uiState.notePublished
            .filter { it.status != NoteStatus.DELETED }
            .distinctBy { it.id }
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
                    Text(LocalizedStrings.get("confirm"), color = PrimaryTextColor)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(LocalizedStrings.get("cancel"), color = SecondaryTextColor)
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
                taskViewModel.updateNote(appendReviewCommentToNote(n, text, essayLang, context))
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

    if (showCardBgSheet) {
        CardBackgroundSettingsSheet(
            currentType = cardBgType,
            currentColor = cardBgColor,
            currentImageUri = cardBgImageUri,
            currentOpacity = cardBgOpacity,
            onDismiss = { showCardBgSheet = false },
            onTypeChange = { t -> scope.launch { dataStore.setEssayCardBgType(t) } },
            onColorChange = { c -> scope.launch { dataStore.setEssayCardBgColor(c) } },
            onPickImage = { pickCardBgImage.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly)) },
            onClearImage = { scope.launch { dataStore.setEssayCardBgImageUri(null) } },
            onOpacityChange = { o -> scope.launch { dataStore.setEssayCardBgOpacity(o) } },
            onClearAll = {
                scope.launch {
                    dataStore.setEssayCardBgType("none")
                    dataStore.setEssayCardBgColor("#FFFFFF")
                    dataStore.setEssayCardBgImageUri(null)
                    dataStore.setEssayCardBgOpacity(1f)
                }
            }
        )
    }

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
            modifier = Modifier.fillMaxSize().statusBarsPadding(),
            topBar = {
                val shadowElevation = 4.dp
                val shadowColor = Color.Black.copy(alpha = AppInteraction.ShadowAlpha)
                val topBarGlassBrush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0f to AppColors.TopBarGlassTop,
                        0.42f to AppColors.TopBarGlassMid,
                        1f to AppColors.TopBarGlassBottom,
                    ),
                )
                val topBarInnerGlowBrush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0f to AppColors.TopBarGlassInnerGlow,
                        0.3f to Color.Transparent,
                        0.7f to Color.Transparent,
                        1f to AppColors.TopBarGlassInnerGlow.copy(alpha = 0.2f),
                    ),
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = AppSpacing.PageHorizontal),
                ) {
                    // TopBar with glass effect (aligned with TodayScreen)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                // Left: Date card with glass effect
                Card(
                    modifier = Modifier
                        .shadow(
                            elevation = shadowElevation,
                            shape = RoundedCornerShape(AppShapes.CardRadius),
                            ambientColor = shadowColor,
                            spotColor = Color(0xFF1A2850).copy(alpha = 0.15f)
                        )
                        .clip(RoundedCornerShape(AppShapes.CardRadius))
                        .border(BorderStroke(1.1.dp, AppColors.TopBarGlassHairline), RoundedCornerShape(AppShapes.CardRadius)),
                    shape = RoundedCornerShape(AppShapes.CardRadius),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Box {
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clip(RoundedCornerShape(AppShapes.CardRadius))
                                .background(topBarGlassBrush)
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clip(RoundedCornerShape(AppShapes.CardRadius))
                                .blur(AppColors.TaskCardBlurRadius)
                                .background(AppColors.TopBarGrayMist)
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clip(RoundedCornerShape(AppShapes.CardRadius))
                                .blur(AppColors.TaskCardBlurRadius)
                                .background(topBarInnerGlowBrush)
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clip(RoundedCornerShape(AppShapes.CardRadius))
                                .background(AppColors.GlassFill.copy(alpha = 0.07f))
                        )
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clickable { showDatePicker = true },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = dateLabel,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = essayHeaderPrimary
                            )
                            Text(
                                text = " ▼",
                                fontSize = 12.sp,
                                color = essayHeaderSecondary
                            )
                        }
                    }
                }
                
                Spacer(Modifier.weight(1f))
                
                // Center: Inbox button with glass effect
                BadgedBox(
                    badge = {
                        if (inboxCount > 0) {
                            Badge { Text(text = inboxCount.toString()) }
                        }
                    },
                    modifier = Modifier.clickable { onNavigateToInbox() }
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .shadow(
                                elevation = shadowElevation,
                                shape = CircleShape,
                                ambientColor = shadowColor,
                                spotColor = Color(0xFF1A2850).copy(alpha = 0.10f)
                            )
                            .clip(CircleShape)
                            .border(BorderStroke(1.1.dp, AppColors.TopBarGlassHairline), CircleShape)
                            .background(topBarGlassBrush),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .blur(AppColors.TaskCardBlurRadius)
                                .background(AppColors.TopBarGrayMist)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .blur(AppColors.TaskCardBlurRadius)
                                .background(topBarInnerGlowBrush)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(AppColors.GlassFill.copy(alpha = 0.07f))
                        )
                        Icon(
                            imageVector = Icons.Default.Inbox,
                            contentDescription = LocalizedStrings.get("cd_inbox"),
                            tint = essayHeaderPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                
                Spacer(Modifier.width(8.dp))
                
                // Right: Search button with glass effect
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .shadow(
                            elevation = shadowElevation,
                            shape = CircleShape,
                            ambientColor = shadowColor,
                            spotColor = Color(0xFF1A2850).copy(alpha = 0.10f)
                        )
                        .clip(CircleShape)
                        .border(BorderStroke(1.1.dp, AppColors.TopBarGlassHairline), CircleShape)
                        .background(topBarGlassBrush)
                        .clickable { onNavigateToSearch() },
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(AppColors.TaskCardBlurRadius)
                            .background(AppColors.TopBarGrayMist)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(AppColors.TaskCardBlurRadius)
                            .background(topBarInnerGlowBrush)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(AppColors.GlassFill.copy(alpha = 0.07f))
                    )
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = LocalizedStrings.get("cd_search"),
                        tint = essayHeaderPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }
                
                Spacer(Modifier.width(8.dp))
                
                // More options button with glass effect
                Box {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .shadow(
                                elevation = shadowElevation,
                                shape = CircleShape,
                                ambientColor = shadowColor,
                                spotColor = Color(0xFF1A2850).copy(alpha = 0.10f)
                            )
                            .clip(CircleShape)
                            .border(BorderStroke(1.1.dp, AppColors.TopBarGlassHairline), CircleShape)
                            .background(topBarGlassBrush)
                            .clickable { menuOpen = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .blur(AppColors.TaskCardBlurRadius)
                                .background(AppColors.TopBarGrayMist)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .blur(AppColors.TaskCardBlurRadius)
                                .background(topBarInnerGlowBrush)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(AppColors.GlassFill.copy(alpha = 0.07f))
                        )
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = LocalizedStrings.get("cd_more_options"),
                            tint = essayHeaderPrimary,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    val menuText = Color(0xFF333333)
                    val menuIcon = Color(0xFF555555)
                    DropdownMenu(
                        expanded = menuOpen,
                        onDismissRequest = { menuOpen = false },
                        containerColor = Color(0xFFFCFCFC),
                    ) {
                            DropdownMenuItem(
                                text = { Text(LocalizedStrings.get("topic_manage"), color = menuText) },
                                onClick = {
                                    menuOpen = false
                                    onOpenTopicManage()
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(LocalizedStrings.get("essay_menu_trash"), color = menuText) },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null,
                                        tint = menuIcon
                                    )
                                },
                                onClick = {
                                    menuOpen = false
                                    onNavigateToTrash()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(LocalizedStrings.get("essay_menu_layout_card"), color = menuText) },
                                leadingIcon = if (timelineLayout == "card") {
                                    {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = menuIcon
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
                                text = { Text(LocalizedStrings.get("essay_menu_layout_integrated"), color = menuText) },
                                leadingIcon = if (timelineLayout == "integrated") {
                                    {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = menuIcon
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
                                    text = { Text(LocalizedStrings.get("essay_menu_wallpaper"), color = menuText) },
                                    onClick = {
                                        menuOpen = false
                                        pickEssayWallpaper.launch(
                                            PickVisualMediaRequest(PickVisualMedia.ImageOnly)
                                        )
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text(LocalizedStrings.get("essay_menu_clear_wallpaper"), color = menuText) },
                                    onClick = {
                                        menuOpen = false
                                        scope.launch { dataStore.setEssayIntegratedWallpaperUri(null) }
                                    },
                                    enabled = essayWallpaperUri != null
                                )
                            }
                            if (!integratedLayoutActive) {
                                DropdownMenuItem(
                                    text = { Text(LocalizedStrings.get("essay_menu_card_bg"), color = menuText) },
                                    onClick = {
                                        menuOpen = false
                                        showCardBgSheet = true
                                    }
                                )
                            }
                        }
                    }
                }
            }

            },
            containerColor = Color.Transparent,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onNavigateToEditor(null) },
                    containerColor = NoteCardConstants.categoryColor(NotePrimaryCategory.SELF_AWARENESS),
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_feather),
                        contentDescription = LocalizedStrings.get("cd_new_note"),
                        tint = Color.Unspecified,
                        modifier = Modifier.size(32.dp)
                    )
                }
            },
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                // Tab switching bar (moved from topBar to content area for visual separation)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = AppSpacing.PageHorizontal),
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

                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = AppSpacing.PageHorizontal),
                    thickness = 0.5.dp,
                    color = Color.White.copy(alpha = 0.22f)
                )

                AnimatedContent(
                    targetState = selectedTab,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(200)) togetherWith
                            fadeOut(animationSpec = tween(200))
                    },
                    label = "essayTabContent",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = AppSpacing.PageHorizontal),
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
                        },
                        cardBackgroundOverride = cardBackgroundOverride
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
    publishedNoteActions: (Note) -> PublishedNoteCardActions,
    cardBackgroundOverride: Color? = null,
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
            contentPadding = PaddingValues(bottom = 88.dp),
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
            contentPadding = PaddingValues(bottom = 88.dp),
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
                        publishedActions = publishedNoteActions(note),
                        cardBackgroundOverride = cardBackgroundOverride
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

// ── Card Background Settings Bottom Sheet ──────────────────────────

private val presetCardBgColors = listOf(
    "#FFFFFF", "#FFF8F0", "#FFF0F5", "#F0F0FF",
    "#F0FFF4", "#FFFFF0", "#F5F5F5", "#E8E4F5"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardBackgroundSettingsSheet(
    currentType: String,
    currentColor: String,
    currentImageUri: String?,
    currentOpacity: Float,
    onDismiss: () -> Unit,
    onTypeChange: (String) -> Unit,
    onColorChange: (String) -> Unit,
    onPickImage: () -> Unit,
    onClearImage: () -> Unit,
    onOpacityChange: (Float) -> Unit,
    onClearAll: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = AppShapes.SheetTopRadius, topEnd = AppShapes.SheetTopRadius)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Title
            Text(
                text = LocalizedStrings.get("essay_menu_card_bg"),
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryTextColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Background type selector
            Text(
                text = LocalizedStrings.get("essay_menu_card_bg"),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = SecondaryTextColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BgTypeChip(
                    label = LocalizedStrings.get("essay_menu_card_bg_none"),
                    selected = currentType == "none",
                    onClick = { onTypeChange("none") }
                )
                BgTypeChip(
                    label = LocalizedStrings.get("essay_menu_card_bg_color"),
                    selected = currentType == "color",
                    onClick = { onTypeChange("color") }
                )
                BgTypeChip(
                    label = LocalizedStrings.get("essay_menu_card_bg_image"),
                    selected = currentType == "image",
                    onClick = { onTypeChange("image") }
                )
            }

            // Color picker (visible when type == "color")
            if (currentType == "color") {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = LocalizedStrings.get("essay_menu_card_bg_color"),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = SecondaryTextColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    presetCardBgColors.forEach { hex ->
                        val parsed = try { Color(android.graphics.Color.parseColor(hex)) } catch (_: Exception) { Color.White }
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(AppShapes.EmbedRadius))
                                .background(parsed)
                                .then(
                                    if (currentColor.equals(hex, ignoreCase = true)) Modifier.padding(3.dp).background(Color.Transparent, RoundedCornerShape(AppShapes.DenseInsetRadius)).padding(3.dp)
                                    else Modifier
                                )
                                .clickable { onColorChange(hex) },
                            contentAlignment = Alignment.Center
                        ) {
                            if (currentColor.equals(hex, ignoreCase = true)) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Image picker (visible when type == "image")
            if (currentType == "image") {
                Spacer(modifier = Modifier.height(16.dp))
                if (currentImageUri != null) {
                    AsyncImage(
                        model = currentImageUri,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(AppShapes.InsetContentRadius)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(onClick = onClearImage) {
                        Text(LocalizedStrings.get("essay_menu_card_bg_clear"), color = SecondaryTextColor)
                    }
                } else {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .clickable { onPickImage() },
                        shape = RoundedCornerShape(AppShapes.InsetContentRadius),
                        color = AppColors.ScreenBackground
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "+",
                                fontSize = 28.sp,
                                color = SecondaryTextColor
                            )
                        }
                    }
                }
            }

            // Opacity slider (visible when type != "none")
            if (currentType != "none") {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = LocalizedStrings.get("essay_menu_card_bg_opacity"),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = SecondaryTextColor
                    )
                    Text(
                        text = "${(currentOpacity * 100).toInt()}%",
                        fontSize = 14.sp,
                        color = PrimaryTextColor
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Slider(
                    value = currentOpacity,
                    onValueChange = onOpacityChange,
                    valueRange = 0.1f..1f,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Clear all button
            if (currentType != "none") {
                Spacer(modifier = Modifier.height(12.dp))
                TextButton(
                    onClick = { onClearAll(); onDismiss() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(LocalizedStrings.get("essay_menu_card_bg_clear"), color = SecondaryTextColor)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun BgTypeChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(AppShapes.FeaturePanelRadius),
        color = if (selected) PrimaryTextColor.copy(alpha = 0.12f) else Color(0xFFF0F0F0),
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            fontSize = 13.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (selected) PrimaryTextColor else SecondaryTextColor
        )
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
                            categoryColor = NoteCardConstants.categoryColor(primaryKey),
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
                                categoryColor = NoteCardConstants.categoryColor(primaryKey),
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
    categoryColor: Color,
    primaryText: Color,
    secondaryText: Color,
    onToggleExpand: () -> Unit,
    onSelect: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(AppShapes.DenseInsetRadius),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) categoryColor.copy(alpha = 0.12f) else Color.White
        ),
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Text with underline beneath it
                    Column {
                        Text(
                            text = labelShort,
                            fontSize = 12.sp,
                            lineHeight = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryText,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        // Underline that adapts to text width (no gap, tight to text)
                        Box(
                            modifier = Modifier
                                .height(1.5.dp)
                                .background(if (selected) categoryColor else Color.Transparent)
                                .align(Alignment.Start)
                        )
                    }
                }
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
    categoryColor: Color,
    primaryText: Color,
    secondaryText: Color,
    hintText: Color,
    onSelect: () -> Unit,
) {
    val label = rememberTopicSecondaryLabelWithConfig(primaryKey, secondaryKey, essayCategoryConfig)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppShapes.CompactRadius))
            .background(
                if (selected) categoryColor.copy(alpha = 0.12f) else Color.Transparent
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
                shape = RoundedCornerShape(AppShapes.EmbedRadius),
                colors = CardDefaults.cardColors(
                    containerColor = if (selected) NoteCardConstants.categoryColor(NotePrimaryCategory.SELF_AWARENESS).copy(alpha = 0.12f) else Color.White
                ),
                border = if (selected) {
                    BorderStroke(1.dp, NoteCardConstants.categoryColor(NotePrimaryCategory.SELF_AWARENESS).copy(alpha = 0.45f))
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
        contentPadding = PaddingValues(bottom = 88.dp),
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
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val noteCountLine = LocalizedStrings.stringFor(lang, "essay_project_note_count", context, noteCount)
    val relative = UserVisibleStrings.relativeTimeAgo(project.updatedAt, lang, context)
    val updatedLine = LocalizedStrings.stringFor(lang, "essay_project_updated_line", context, relative)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(AppShapes.InsetContentRadius),
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
                    text = noteCountLine,
                    fontSize = 14.sp,
                    color = SecondaryTextColor
                )
                Text(
                    text = updatedLine,
                    fontSize = 12.sp,
                    color = SecondaryTextColor.copy(alpha = 0.7f)
                )
            }
        }
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
                text = LocalizedStrings.get("essay_projects_empty_title"),
                fontSize = 16.sp,
                color = SecondaryTextColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = LocalizedStrings.get("essay_projects_empty_subtitle"),
                fontSize = 14.sp,
                color = SecondaryTextColor.copy(alpha = 0.7f)
            )
        }
    }
}
