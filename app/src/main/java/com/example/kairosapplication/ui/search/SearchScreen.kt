package com.example.kairosapplication.ui.search

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.core.ui.CommonBackButton
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.topic.TopicDisplayStrings
import com.example.kairosapplication.ui.PublishedNoteActionDialogsHost
import com.example.kairosapplication.ui.components.NoteCard
import com.example.kairosapplication.ui.components.NoteCardConstants
import com.example.kairosapplication.ui.components.NoteCardVariant
import com.example.kairosapplication.ui.components.NoteCommentBottomSheet
import com.example.kairosapplication.ui.components.PublishedNoteCardActions
import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.ui.glass.GlassSurface
import com.example.kairosapplication.ui.glass.LocalGlassAtmosphereUi
import com.example.kairosapplication.ui.glass.glassChromeTextStyle
import com.example.kairosapplication.ui.glass.quoteDividerColor
import com.example.kairosapplication.ui.components.appendReviewCommentToNote
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.LocalDate
import kotlinx.coroutines.launch

private enum class DateRangeFilter {
    ANY,
    TODAY,
    THIS_WEEK,
    THIS_MONTH
}

private enum class ImageFilter {
    ANY,
    WITH_IMAGES,
    WITHOUT_IMAGES
}

private val categoryFilterOrder = listOf(
    NotePrimaryCategory.FREESTYLE,
    NotePrimaryCategory.SELF_AWARENESS,
    NotePrimaryCategory.INTERPERSONAL,
    NotePrimaryCategory.INTIMACY_FAMILY,
    NotePrimaryCategory.SOMATIC_ENERGY,
    NotePrimaryCategory.MEANING
)

private val moodOptions = listOf("😊", "😃", "😄", "😆", "😐", "😢", "😡", "🤔", "😌", "❤️")

private val SearchInsetDividerHorizontal = 20.dp

private const val CategoryAllKey = "__ALL__"
private const val MoodAnyKey = "__ANY__"

private const val MaxRecentChips = 8
private const val RecentChipsPerRow = 4

@Composable
fun SearchScreen(
    taskViewModel: TaskViewModel,
    onBackClick: () -> Unit,
    onNoteClick: (Long) -> Unit,
    onNavigateToNewNote: () -> Unit,
    mainBottomBarInset: Dp = 0.dp,
) {
    val atmosphere = LocalGlassAtmosphereUi.current
    val chrome = atmosphere.topChrome
    val useLightChrome = !atmosphere.zones.topIsLight
    val dividerColor = atmosphere.quoteDividerColor()
    val uiState by taskViewModel.uiState.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val recentSearches by recentSearchesFlow(context).collectAsState(initial = emptyList())

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var selectedDateRange by remember { mutableStateOf(DateRangeFilter.ANY) }
    var selectedImageFilter by remember { mutableStateOf(ImageFilter.ANY) }
    var selectedMood by remember { mutableStateOf<String?>(null) }

    var showCategoryDialog by remember { mutableStateOf(false) }
    var showDateRangeDialog by remember { mutableStateOf(false) }
    var showHasImagesDialog by remember { mutableStateOf(false) }
    var showMoodDialog by remember { mutableStateOf(false) }

    var expandedNoteId by remember { mutableStateOf<Long?>(null) }
    var changeTopicNoteId by remember { mutableStateOf<Long?>(null) }
    var changeProjectNoteId by remember { mutableStateOf<Long?>(null) }
    var continueCreateNoteId by remember { mutableStateOf<Long?>(null) }
    var deleteConfirmNoteId by remember { mutableStateOf<Long?>(null) }
    var commentNoteId by remember { mutableStateOf<Long?>(null) }

    val filteredNotes = remember(
        searchQuery,
        selectedCategory,
        selectedDateRange,
        selectedImageFilter,
        selectedMood,
        uiState.notePublished
    ) {
        val q = searchQuery.trim()
        uiState.notePublished
            .filter { it.status != NoteStatus.DELETED }
            .filter { note ->
                val matchesKeyword = q.isEmpty() ||
                    note.behaviorSummary.contains(q, ignoreCase = true) ||
                    note.body.contains(q, ignoreCase = true)

                val matchesCategory = selectedCategory == null ||
                    note.primaryCategory == selectedCategory

                val matchesDateRange = when (selectedDateRange) {
                    DateRangeFilter.ANY -> true
                    DateRangeFilter.TODAY -> note.recordedDate == LocalDate.now()
                    DateRangeFilter.THIS_WEEK -> {
                        val weekAgo = LocalDate.now().minusDays(7)
                        note.recordedDate >= weekAgo
                    }
                    DateRangeFilter.THIS_MONTH -> {
                        val monthAgo = LocalDate.now().minusMonths(1)
                        note.recordedDate >= monthAgo
                    }
                }

                val matchesImage = when (selectedImageFilter) {
                    ImageFilter.ANY -> true
                    ImageFilter.WITH_IMAGES -> note.imageUris.isNotEmpty()
                    ImageFilter.WITHOUT_IMAGES -> note.imageUris.isEmpty()
                }

                val matchesMood = selectedMood == null || note.moodIcon == selectedMood

                matchesKeyword && matchesCategory && matchesDateRange && matchesImage && matchesMood
            }
            .sortedByDescending { it.updatedAt }
    }

    val projectsByIdMap = remember(uiState.noteProjects) {
        uiState.noteProjects.associate { it.id to it.name }
    }

    val lang = LocalCurrentLanguage.current.value
    val categoryDialogOptions = remember(lang, context) {
        listOf(CategoryAllKey to LocalizedStrings.stringFor(lang, "search_filter_all", context)) +
            categoryFilterOrder.map { key ->
                key to TopicDisplayStrings.primaryLabel(key, lang, context)
            }
    }

    val dateDialogOptions = remember(lang, context) {
        listOf(
            DateRangeFilter.ANY to LocalizedStrings.stringFor(lang, "search_filter_any", context),
            DateRangeFilter.TODAY to LocalizedStrings.stringFor(lang, "search_filter_today", context),
            DateRangeFilter.THIS_WEEK to LocalizedStrings.stringFor(lang, "search_filter_this_week", context),
            DateRangeFilter.THIS_MONTH to LocalizedStrings.stringFor(lang, "search_filter_this_month", context),
        )
    }

    val imageDialogOptions = remember(lang, context) {
        listOf(
            ImageFilter.ANY to LocalizedStrings.stringFor(lang, "search_filter_any", context),
            ImageFilter.WITH_IMAGES to LocalizedStrings.stringFor(lang, "search_filter_yes", context),
            ImageFilter.WITHOUT_IMAGES to LocalizedStrings.stringFor(lang, "search_filter_no", context),
        )
    }

    val moodDialogOptions = remember(lang, context) {
        listOf(MoodAnyKey to LocalizedStrings.stringFor(lang, "search_filter_any", context)) +
            moodOptions.map { emoji -> emoji to emoji }
    }

    LaunchedEffect(commentNoteId, filteredNotes) {
        val id = commentNoteId ?: return@LaunchedEffect
        if (filteredNotes.none { it.id == id }) commentNoteId = null
    }
    val searchCommentNote = commentNoteId?.let { id ->
        filteredNotes.find { it.id == id }
            ?: uiState.notePublished.find { it.id == id }
    }
    searchCommentNote?.let { n ->
        NoteCommentBottomSheet(
            note = n,
            onDismiss = { commentNoteId = null },
            onAppendComment = { note, text ->
                taskViewModel.updateNote(appendReviewCommentToNote(note, text, lang, context))
            }
        )
    }

    PublishedNoteActionDialogsHost(
        resolveNote = { id ->
            filteredNotes.find { it.id == id }
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
        onNoteDeleted = { nid -> if (expandedNoteId == nid) expandedNoteId = null }
    )

    Scaffold(containerColor = Color.Transparent) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(bottom = mainBottomBarInset),
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onBackClick = onBackClick,
                onClear = { searchQuery = "" },
                onSearchSubmit = {
                    val t = searchQuery.trim()
                    if (t.isNotEmpty()) {
                        scope.launch { addRecentSearch(context, t) }
                    }
                },
                chromePrimary = chrome.primary,
                chromeSecondary = chrome.secondary,
                useLightChrome = useLightChrome,
            )

            if (recentSearches.isNotEmpty()) {
                RecentSearchChipsSection(
                    recentSearches = recentSearches,
                    onChipClick = { searchQuery = it },
                    onReplaceSearch = { old, new ->
                        scope.launch { replaceRecentSearch(context, old, new) }
                    },
                    onDeleteSearch = { term ->
                        scope.launch { removeRecentSearch(context, term) }
                    },
                    chipTextColor = chrome.secondary,
                )
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 16.dp),
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = AppSpacing.PageHorizontal, vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        FilterRow(
                            label = LocalizedStrings.get("search_filter_category"),
                            value = selectedCategory?.let { TopicDisplayStrings.primaryLabel(it, lang, context) }
                                ?: LocalizedStrings.get("search_filter_all"),
                            onClick = { showCategoryDialog = true },
                            labelColor = chrome.primary,
                            valueColor = chrome.secondary,
                        )
                        SearchInsetDivider(dividerColor)
                        FilterRow(
                            label = LocalizedStrings.get("search_filter_date_range"),
                            value = dateRangeDisplayLabel(selectedDateRange, lang, context),
                            onClick = { showDateRangeDialog = true },
                            labelColor = chrome.primary,
                            valueColor = chrome.secondary,
                        )
                        SearchInsetDivider(dividerColor)
                        FilterRow(
                            label = LocalizedStrings.get("search_filter_has_images"),
                            value = imageFilterDisplayLabel(selectedImageFilter, lang, context),
                            onClick = { showHasImagesDialog = true },
                            labelColor = chrome.primary,
                            valueColor = chrome.secondary,
                        )
                        SearchInsetDivider(dividerColor)
                        FilterRow(
                            label = LocalizedStrings.get("search_filter_mood"),
                            value = selectedMood ?: LocalizedStrings.get("search_filter_any"),
                            onClick = { showMoodDialog = true },
                            labelColor = chrome.primary,
                            valueColor = chrome.secondary,
                        )
                    }
                }

                item {
                    Text(
                        text = LocalizedStrings.get("search_results", filteredNotes.size),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = chrome.primary,
                        style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(AppSpacing.PageHorizontal, 12.dp)
                    )
                }

                if (filteredNotes.isEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 48.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = LocalizedStrings.get("search_no_results_title"),
                                fontSize = 16.sp,
                                color = AppColors.SecondaryText
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = LocalizedStrings.get("search_no_results_hint"),
                                fontSize = 14.sp,
                                color = AppColors.HintText
                            )
                        }
                    }
                } else {
                    items(filteredNotes, key = { it.id }) { note ->
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            NoteCard(
                                note = note,
                                variant = NoteCardVariant.TOPIC,
                                onNoteClick = onNoteClick,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = AppSpacing.PageHorizontal, vertical = 8.dp),
                                expandable = true,
                                expanded = note.id == expandedNoteId,
                                onToggleExpand = {
                                    expandedNoteId =
                                        if (expandedNoteId == note.id) null else note.id
                                },
                                projectsById = projectsByIdMap,
                                publishedActions = if (note.status == NoteStatus.PUBLISHED) {
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
                                } else {
                                    null
                                }
                            )
                            SearchInsetDivider(dividerColor)
                        }
                    }
                }
            }
        }
    }

    if (showCategoryDialog) {
        GlassFilterDialog(
            title = LocalizedStrings.get("search_filter_category"),
            onDismiss = { showCategoryDialog = false },
            chromePrimary = chrome.primary,
            useLightChrome = useLightChrome,
        ) {
            categoryDialogOptions.forEach { (key, label) ->
                val selected = (selectedCategory ?: CategoryAllKey) == key
                GlassFilterOptionRow(
                    label = label,
                    selected = selected,
                    onClick = {
                        selectedCategory = if (key == CategoryAllKey) null else key
                        showCategoryDialog = false
                    },
                    chromePrimary = chrome.primary,
                    useLightChrome = useLightChrome,
                )
            }
        }
    }

    if (showDateRangeDialog) {
        GlassFilterDialog(
            title = LocalizedStrings.get("search_filter_date_range"),
            onDismiss = { showDateRangeDialog = false },
            chromePrimary = chrome.primary,
            useLightChrome = useLightChrome,
        ) {
            dateDialogOptions.forEach { (value, label) ->
                GlassFilterOptionRow(
                    label = label,
                    selected = selectedDateRange == value,
                    onClick = {
                        selectedDateRange = value
                        showDateRangeDialog = false
                    },
                    chromePrimary = chrome.primary,
                    useLightChrome = useLightChrome,
                )
            }
        }
    }

    if (showHasImagesDialog) {
        GlassFilterDialog(
            title = LocalizedStrings.get("search_filter_has_images"),
            onDismiss = { showHasImagesDialog = false },
            chromePrimary = chrome.primary,
            useLightChrome = useLightChrome,
        ) {
            imageDialogOptions.forEach { (value, label) ->
                GlassFilterOptionRow(
                    label = label,
                    selected = selectedImageFilter == value,
                    onClick = {
                        selectedImageFilter = value
                        showHasImagesDialog = false
                    },
                    chromePrimary = chrome.primary,
                    useLightChrome = useLightChrome,
                )
            }
        }
    }

    if (showMoodDialog) {
        GlassFilterDialog(
            title = LocalizedStrings.get("search_filter_mood"),
            onDismiss = { showMoodDialog = false },
            chromePrimary = chrome.primary,
            useLightChrome = useLightChrome,
        ) {
            moodDialogOptions.forEach { (key, label) ->
                GlassFilterOptionRow(
                    label = label,
                    selected = (selectedMood ?: MoodAnyKey) == key,
                    onClick = {
                        selectedMood = if (key == MoodAnyKey) null else key
                        showMoodDialog = false
                    },
                    chromePrimary = chrome.primary,
                    useLightChrome = useLightChrome,
                )
            }
        }
    }
}

@Composable
private fun SearchInsetDivider(color: Color) {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = SearchInsetDividerHorizontal),
        color = color,
        thickness = 0.5.dp,
    )
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onClear: () -> Unit,
    onSearchSubmit: () -> Unit,
    chromePrimary: Color,
    chromeSecondary: Color,
    useLightChrome: Boolean,
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val fieldShape = RoundedCornerShape(AppShapes.ProminentRadius)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        CommonBackButton(onClick = onBackClick)
        GlassSurface(
            modifier = Modifier
                .weight(1f)
                .height(44.dp),
            shape = fieldShape,
            fillAlpha = GlassConstants.PillFillAlpha,
            wrapHazeToContent = false,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier.weight(1f),
                    textStyle = glassChromeTextStyle(
                        TextStyle(fontSize = 16.sp, color = chromePrimary),
                        useLightChrome,
                    ),
                    cursorBrush = SolidColor(chromePrimary),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchSubmit()
                            keyboard?.hide()
                        },
                    ),
                    decorationBox = { inner ->
                        Box(modifier = Modifier.fillMaxWidth()) {
                            if (query.isEmpty()) {
                                Text(
                                    text = LocalizedStrings.get("search_placeholder"),
                                    fontSize = 16.sp,
                                    color = chromeSecondary,
                                    modifier = Modifier.align(Alignment.CenterStart),
                                )
                            }
                            inner()
                        }
                    },
                )
                if (query.isNotEmpty()) {
                    IconButton(
                        onClick = onClear,
                        modifier = Modifier.size(36.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = LocalizedStrings.get("search_cd_clear"),
                            tint = chromeSecondary,
                            modifier = Modifier.size(18.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GlassFilterDialog(
    title: String,
    onDismiss: () -> Unit,
    chromePrimary: Color,
    useLightChrome: Boolean,
    content: @Composable () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        GlassSurface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            shape = RoundedCornerShape(AppShapes.CardRadius),
            fillAlpha = GlassConstants.TaskCardFillAlpha,
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                    color = chromePrimary,
                    modifier = Modifier.padding(bottom = 10.dp),
                )
                content()
            }
        }
    }
}

@Composable
private fun GlassFilterOptionRow(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    chromePrimary: Color,
    useLightChrome: Boolean,
) {
    val accent = NoteCardConstants.categoryColor(NotePrimaryCategory.SELF_AWARENESS)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(if (selected) accent.copy(alpha = 0.14f) else Color.Transparent)
            .padding(vertical = 10.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = if (selected) accent else chromePrimary,
            style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RecentSearchChipsSection(
    recentSearches: List<String>,
    onChipClick: (String) -> Unit,
    onReplaceSearch: (String, String) -> Unit,
    onDeleteSearch: (String) -> Unit,
    chipTextColor: Color,
) {
    val display = recentSearches.take(MaxRecentChips)
    val row1 = display.take(RecentChipsPerRow)
    val row2 = display.drop(RecentChipsPerRow).take(RecentChipsPerRow)
    var editTarget by remember { mutableStateOf<String?>(null) }
    var editedText by remember { mutableStateOf("") }

    LaunchedEffect(editTarget) {
        if (editTarget != null) editedText = editTarget!!
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppSpacing.PageHorizontal, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            row1.forEach { term ->
                RecentSearchChip(
                    text = term,
                    onClick = { onChipClick(term) },
                    onLongClick = { editTarget = term },
                    textColor = chipTextColor,
                )
            }
        }
        if (row2.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                row2.forEach { term ->
                    RecentSearchChip(
                        text = term,
                        onClick = { onChipClick(term) },
                        onLongClick = { editTarget = term },
                        textColor = chipTextColor,
                    )
                }
            }
        }
    }

    editTarget?.let { target ->
        AlertDialog(
            onDismissRequest = { editTarget = null },
            title = { Text(LocalizedStrings.get("search_dialog_edit_title"), color = AppColors.PrimaryText) },
            text = {
                OutlinedTextField(
                    value = editedText,
                    onValueChange = { editedText = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onReplaceSearch(target, editedText)
                        editTarget = null
                    }
                ) {
                    Text(LocalizedStrings.get("search_dialog_save"), color = AppColors.PrimaryText)
                }
            },
            dismissButton = {
                Row {
                    TextButton(
                        onClick = {
                            onDeleteSearch(target)
                            editTarget = null
                        }
                    ) {
                        Text(LocalizedStrings.get("search_dialog_delete"), color = AppColors.SecondaryText)
                    }
                    TextButton(onClick = { editTarget = null }) {
                        Text(LocalizedStrings.get("cancel"), color = AppColors.SecondaryText)
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RecentSearchChip(
    text: String,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    textColor: Color,
) {
    val chipShape = RoundedCornerShape(GlassConstants.CornerRadius)
    GlassSurface(
        modifier = Modifier.combinedClickable(onClick = onClick, onLongClick = onLongClick),
        shape = chipShape,
        fillAlpha = GlassConstants.PillFillAlpha,
        wrapHazeToContent = true,
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            color = textColor,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
        )
    }
}

@Composable
private fun FilterRow(
    label: String,
    value: String,
    onClick: () -> Unit,
    labelColor: Color,
    valueColor: Color,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = labelColor,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = value,
                fontSize = 14.sp,
                color = valueColor,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = LocalizedStrings.get("search_cd_select"),
                tint = valueColor.copy(alpha = 0.7f),
                modifier = Modifier.size(16.dp),
            )
        }
    }
}

private fun dateRangeDisplayLabel(
    selected: DateRangeFilter,
    lang: LocalizationManager.Language,
    context: Context,
): String = when (selected) {
    DateRangeFilter.ANY -> LocalizedStrings.stringFor(lang, "search_filter_any", context)
    DateRangeFilter.TODAY -> LocalizedStrings.stringFor(lang, "search_filter_today", context)
    DateRangeFilter.THIS_WEEK -> LocalizedStrings.stringFor(lang, "search_filter_this_week", context)
    DateRangeFilter.THIS_MONTH -> LocalizedStrings.stringFor(lang, "search_filter_this_month", context)
}

private fun imageFilterDisplayLabel(
    selected: ImageFilter,
    lang: LocalizationManager.Language,
    context: Context,
): String = when (selected) {
    ImageFilter.ANY -> LocalizedStrings.stringFor(lang, "search_filter_any", context)
    ImageFilter.WITH_IMAGES -> LocalizedStrings.stringFor(lang, "search_filter_yes", context)
    ImageFilter.WITHOUT_IMAGES -> LocalizedStrings.stringFor(lang, "search_filter_no", context)
}
