package com.example.kairosapplication.ui.search

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.ui.PublishedNoteActionDialogsHost
import com.example.kairosapplication.ui.components.NoteCard
import com.example.kairosapplication.ui.components.NoteCardConstants
import com.example.kairosapplication.ui.components.NoteCardVariant
import com.example.kairosapplication.ui.components.PublishedNoteCardActions
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

private val SearchFieldPurple = Color(0xFFD4C8E8)

/** Filter / list dividers (aligned with app theme) */
private val SearchRowDividerColor = AppColors.Divider

/** Recent chip: hairline border, alpha 0.1 on primary text */
private val RecentChipBorderColor = AppColors.PrimaryText.copy(alpha = 0.1f)

private const val CategoryAllKey = "__ALL__"
private const val MoodAnyKey = "__ANY__"

private const val MaxRecentChips = 8
private const val RecentChipsPerRow = 4

@Composable
fun SearchScreen(
    taskViewModel: TaskViewModel,
    onBackClick: () -> Unit,
    onNoteClick: (Long) -> Unit,
    onNavigateToNewNote: () -> Unit
) {
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

    val categoryDialogOptions = remember {
        listOf(CategoryAllKey to "All") +
            categoryFilterOrder.map { key ->
                key to NoteCardConstants.primaryCategoryLabel(key)
            }
    }

    val dateDialogOptions = remember {
        listOf(
            DateRangeFilter.ANY to "Any",
            DateRangeFilter.TODAY to "Today",
            DateRangeFilter.THIS_WEEK to "This week",
            DateRangeFilter.THIS_MONTH to "This month"
        )
    }

    val imageDialogOptions = remember {
        listOf(
            ImageFilter.ANY to "Any",
            ImageFilter.WITH_IMAGES to "Yes",
            ImageFilter.WITHOUT_IMAGES to "No"
        )
    }

    val moodDialogOptions = remember {
        listOf(MoodAnyKey to "Any") + moodOptions.map { emoji -> emoji to emoji }
    }

    PublishedNoteActionDialogsHost(
        resolveNote = { id ->
            filteredNotes.find { it.id == id }
                ?: uiState.notePublished.find { it.id == id }
        },
        noteProjects = uiState.noteProjects,
        customSecondaryCategories = uiState.customSecondaryCategories,
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

    Scaffold(containerColor = AppColors.ScreenBackground) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(AppColors.ScreenBackground)
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
                }
            )

            HorizontalDivider(color = SearchRowDividerColor, thickness = 1.dp)

            if (recentSearches.isNotEmpty()) {
                RecentSearchChipsSection(
                    recentSearches = recentSearches,
                    onChipClick = { searchQuery = it },
                    onReplaceSearch = { old, new ->
                        scope.launch { replaceRecentSearch(context, old, new) }
                    },
                    onDeleteSearch = { term ->
                        scope.launch { removeRecentSearch(context, term) }
                    }
                )
                HorizontalDivider(color = SearchRowDividerColor, thickness = 1.dp)
            }

            LazyColumn(modifier = Modifier.weight(1f)) {
                item {
                    HorizontalDivider(color = SearchRowDividerColor, thickness = 1.dp)
                    FilterRow(
                        label = "Category",
                        value = selectedCategory?.let { NoteCardConstants.primaryCategoryLabel(it) } ?: "All",
                        onClick = { showCategoryDialog = true }
                    )
                    HorizontalDivider(color = SearchRowDividerColor, thickness = 1.dp)
                }

                item {
                    FilterRow(
                        label = "Date range",
                        value = dateRangeDisplayLabel(selectedDateRange),
                        onClick = { showDateRangeDialog = true }
                    )
                    HorizontalDivider(color = SearchRowDividerColor, thickness = 1.dp)
                }

                item {
                    FilterRow(
                        label = "Has images",
                        value = imageFilterDisplayLabel(selectedImageFilter),
                        onClick = { showHasImagesDialog = true }
                    )
                    HorizontalDivider(color = SearchRowDividerColor, thickness = 1.dp)
                }

                item {
                    FilterRow(
                        label = "Mood",
                        value = selectedMood ?: "Any",
                        onClick = { showMoodDialog = true }
                    )
                    HorizontalDivider(color = SearchRowDividerColor, thickness = 1.dp)
                }

                item {
                    Text(
                        text = "Results (${filteredNotes.size})",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppColors.PrimaryText,
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
                                text = "No notes found",
                                fontSize = 16.sp,
                                color = AppColors.SecondaryText
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Try different keywords or filters",
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
                                        }
                                    )
                                } else {
                                    null
                                }
                            )
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = AppSpacing.PageHorizontal),
                                color = SearchRowDividerColor,
                                thickness = 1.dp
                            )
                        }
                    }
                }
            }
        }
    }

    if (showCategoryDialog) {
        KeyLabelFilterDialog(
            title = "Category",
            options = categoryDialogOptions,
            selectedKey = selectedCategory ?: CategoryAllKey,
            onSelect = { key ->
                selectedCategory = if (key == CategoryAllKey) null else key
                showCategoryDialog = false
            },
            onDismiss = { showCategoryDialog = false }
        )
    }

    if (showDateRangeDialog) {
        EnumFilterDialog(
            title = "Date range",
            options = dateDialogOptions,
            selected = selectedDateRange,
            onSelect = {
                selectedDateRange = it
                showDateRangeDialog = false
            },
            onDismiss = { showDateRangeDialog = false }
        )
    }

    if (showHasImagesDialog) {
        EnumFilterDialog(
            title = "Has images",
            options = imageDialogOptions,
            selected = selectedImageFilter,
            onSelect = {
                selectedImageFilter = it
                showHasImagesDialog = false
            },
            onDismiss = { showHasImagesDialog = false }
        )
    }

    if (showMoodDialog) {
        KeyLabelFilterDialog(
            title = "Mood",
            options = moodDialogOptions,
            selectedKey = selectedMood ?: MoodAnyKey,
            onSelect = { key ->
                selectedMood = if (key == MoodAnyKey) null else key
                showMoodDialog = false
            },
            onDismiss = { showMoodDialog = false }
        )
    }
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onClear: () -> Unit,
    onSearchSubmit: () -> Unit
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val fieldText = Color.White
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.ScreenBackground)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = AppColors.PrimaryText,
                modifier = Modifier.size(24.dp)
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
                .background(SearchFieldPurple)
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier.weight(1f),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = fieldText
                    ),
                    cursorBrush = SolidColor(fieldText),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchSubmit()
                            keyboard?.hide()
                        }
                    ),
                    decorationBox = { inner ->
                        Box(modifier = Modifier.fillMaxWidth()) {
                            if (query.isEmpty()) {
                                Text(
                                    text = "Search notes...",
                                    fontSize = 16.sp,
                                    color = fieldText.copy(alpha = 0.8f),
                                    modifier = Modifier.align(Alignment.CenterStart)
                                )
                            }
                            inner()
                        }
                    }
                )
                if (query.isNotEmpty()) {
                    IconButton(
                        onClick = onClear,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = fieldText.copy(alpha = 0.8f),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RecentSearchChipsSection(
    recentSearches: List<String>,
    onChipClick: (String) -> Unit,
    onReplaceSearch: (String, String) -> Unit,
    onDeleteSearch: (String) -> Unit
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
                    onLongClick = { editTarget = term }
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
                        onLongClick = { editTarget = term }
                    )
                }
            }
        }
    }

    editTarget?.let { target ->
        AlertDialog(
            onDismissRequest = { editTarget = null },
            title = { Text("Edit search", color = AppColors.PrimaryText) },
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
                    Text("Save", color = AppColors.PrimaryText)
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
                        Text("Delete", color = AppColors.SecondaryText)
                    }
                    TextButton(onClick = { editTarget = null }) {
                        Text("Cancel", color = AppColors.SecondaryText)
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
    onLongClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(2.dp))
            .background(AppColors.SurfaceWhite, RoundedCornerShape(2.dp))
            .border(1.dp, RecentChipBorderColor, RoundedCornerShape(2.dp))
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .padding(horizontal = 12.dp, vertical = 1.dp)
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            color = AppColors.SecondaryText
        )
    }
}

@Composable
private fun FilterRow(
    label: String,
    value: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 1.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = AppColors.PrimaryText,
            modifier = Modifier.padding(start = AppSpacing.PageHorizontal)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = value,
                fontSize = 14.sp,
                color = AppColors.SecondaryText
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Select",
                tint = AppColors.HintText,
                modifier = Modifier
                    .size(16.dp)
                    .padding(end = AppSpacing.PageHorizontal)
            )
        }
    }
}

@Composable
private fun KeyLabelFilterDialog(
    title: String,
    options: List<Pair<String, String>>,
    selectedKey: String,
    onSelect: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val accent = NoteCardConstants.categoryColor(NotePrimaryCategory.SELF_AWARENESS)
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AppColors.SurfaceWhite,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.PrimaryText,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                options.forEach { (key, label) ->
                    val selected = key == selectedKey
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelect(key)
                            }
                            .background(
                                if (selected) accent.copy(alpha = 0.1f) else Color.Transparent
                            )
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = label,
                            fontSize = 16.sp,
                            color = if (selected) accent else AppColors.PrimaryText,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun <T> EnumFilterDialog(
    title: String,
    options: List<Pair<T, String>>,
    selected: T,
    onSelect: (T) -> Unit,
    onDismiss: () -> Unit
) {
    val accent = NoteCardConstants.categoryColor(NotePrimaryCategory.SELF_AWARENESS)
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AppColors.SurfaceWhite,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.PrimaryText,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                options.forEach { (value, label) ->
                    val isSelected = value == selected
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(value) }
                            .background(
                                if (isSelected) accent.copy(alpha = 0.1f) else Color.Transparent
                            )
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = label,
                            fontSize = 16.sp,
                            color = if (isSelected) accent else AppColors.PrimaryText,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

private fun dateRangeDisplayLabel(selected: DateRangeFilter): String = when (selected) {
    DateRangeFilter.ANY -> "Any"
    DateRangeFilter.TODAY -> "Today"
    DateRangeFilter.THIS_WEEK -> "This week"
    DateRangeFilter.THIS_MONTH -> "This month"
}

private fun imageFilterDisplayLabel(selected: ImageFilter): String = when (selected) {
    ImageFilter.ANY -> "Any"
    ImageFilter.WITH_IMAGES -> "Yes"
    ImageFilter.WITHOUT_IMAGES -> "No"
}
