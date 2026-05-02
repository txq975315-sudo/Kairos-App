package com.example.kairosapplication.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.components.NoteCard
import com.example.kairosapplication.ui.components.NoteCardVariant
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Note
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

private val topicLabels = mapOf(
    NotePrimaryCategory.FREESTYLE to "Freestyle",
    NotePrimaryCategory.SELF_AWARENESS to "Self-Awareness",
    NotePrimaryCategory.INTERPERSONAL to "Interpersonal",
    NotePrimaryCategory.INTIMACY_FAMILY to "Intimacy & Family",
    NotePrimaryCategory.SOMATIC_ENERGY to "Health & Energy",
    NotePrimaryCategory.MEANING to "Meaning"
)

private val categoryFilterOrder = listOf(
    NotePrimaryCategory.FREESTYLE,
    NotePrimaryCategory.SELF_AWARENESS,
    NotePrimaryCategory.INTERPERSONAL,
    NotePrimaryCategory.INTIMACY_FAMILY,
    NotePrimaryCategory.SOMATIC_ENERGY,
    NotePrimaryCategory.MEANING
)

private val moodOptions = listOf("😊", "😃", "😄", "😆", "😐", "😢", "😡", "🤔", "😌", "❤️")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    taskViewModel: TaskViewModel,
    onBackClick: () -> Unit,
    onNoteClick: (Long) -> Unit
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

    val hasActiveFilters =
        selectedCategory != null ||
            selectedDateRange != DateRangeFilter.ANY ||
            selectedImageFilter != ImageFilter.ANY ||
            selectedMood != null
    val hasSearchQuery = searchQuery.isNotBlank()
    val showResults = hasSearchQuery || hasActiveFilters

    Scaffold(containerColor = BackgroundColor) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(BackgroundColor)
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onBackClick = onBackClick,
                onClear = { searchQuery = "" },
                onSearchSubmit = {
                    scope.launch {
                        addRecentSearch(context, searchQuery)
                    }
                }
            )

            if (!showResults) {
                RecentSearchesSection(
                    modifier = Modifier.weight(1f),
                    recentSearches = recentSearches,
                    onSearchClick = { searchQuery = it },
                    onDeleteSearch = { s ->
                        scope.launch { removeRecentSearch(context, s) }
                    },
                    onClearAll = {
                        scope.launch { clearRecentSearches(context) }
                    },
                    onReplaceSearch = { old, new ->
                        scope.launch { replaceRecentSearch(context, old, new) }
                    }
                )
            } else {
                FilterOptionsSection(
                    selectedCategory = selectedCategory,
                    onCategoryChange = { selectedCategory = it },
                    selectedDateRange = selectedDateRange,
                    onDateRangeChange = { selectedDateRange = it },
                    selectedImageFilter = selectedImageFilter,
                    onImageFilterChange = { selectedImageFilter = it },
                    selectedMood = selectedMood,
                    onMoodChange = { selectedMood = it }
                )
                SearchResultsSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    results = filteredNotes,
                    onNoteClick = onNoteClick
                )
            }
        }
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
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = PrimaryTextColor
                )
            }
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = SecondaryTextColor,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            TextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text(
                        "Search notes...",
                        color = SecondaryTextColor
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = PrimaryTextColor,
                    focusedTextColor = PrimaryTextColor,
                    unfocusedTextColor = PrimaryTextColor
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchSubmit()
                        keyboard?.hide()
                    }
                )
            )
            if (query.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear",
                        tint = SecondaryTextColor
                    )
                }
            }
        }
    }
}

@Composable
private fun RecentSearchesSection(
    modifier: Modifier = Modifier,
    recentSearches: List<String>,
    onSearchClick: (String) -> Unit,
    onDeleteSearch: (String) -> Unit,
    onClearAll: () -> Unit,
    onReplaceSearch: (String, String) -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Searches",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = PrimaryTextColor
            )
            if (recentSearches.isNotEmpty()) {
                TextButton(onClick = onClearAll) {
                    Text("Clear all", fontSize = 12.sp, color = SecondaryTextColor)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Long press to edit history",
            fontSize = 12.sp,
            color = SecondaryTextColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (recentSearches.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No recent searches",
                    fontSize = 14.sp,
                    color = SecondaryTextColor
                )
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(0.dp)) {
                items(recentSearches, key = { it }) { search ->
                    SearchHistoryItem(
                        search = search,
                        onClick = { onSearchClick(search) },
                        onDelete = { onDeleteSearch(search) },
                        onReplace = { old, new -> onReplaceSearch(old, new) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchHistoryItem(
    search: String,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    onReplace: (String, String) -> Unit
) {
    var showEditDialog by remember { mutableStateOf(false) }
    var editedText by remember { mutableStateOf(search) }

    LaunchedEffect(showEditDialog) {
        if (showEditDialog) editedText = search
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = { showEditDialog = true }
            )
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.History,
            contentDescription = null,
            tint = SecondaryTextColor,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = search,
            fontSize = 14.sp,
            color = PrimaryTextColor,
            modifier = Modifier.weight(1f),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Delete",
                tint = SecondaryTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
    }

    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = { Text("Edit search", color = PrimaryTextColor) },
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
                        onReplace(search, editedText)
                        showEditDialog = false
                    }
                ) {
                    Text("Save", color = PrimaryTextColor)
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = false }) {
                    Text("Cancel", color = SecondaryTextColor)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterOptionsSection(
    selectedCategory: String?,
    onCategoryChange: (String?) -> Unit,
    selectedDateRange: DateRangeFilter,
    onDateRangeChange: (DateRangeFilter) -> Unit,
    selectedImageFilter: ImageFilter,
    onImageFilterChange: (ImageFilter) -> Unit,
    selectedMood: String?,
    onMoodChange: (String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Filter Options",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = SecondaryTextColor
        )

        CategoryFilterDropdown(
            selectedCategory = selectedCategory,
            onCategoryChange = onCategoryChange
        )
        DateRangeFilterDropdown(
            selected = selectedDateRange,
            onChange = onDateRangeChange
        )
        ImageFilterDropdown(
            selected = selectedImageFilter,
            onChange = onImageFilterChange
        )
        MoodFilterDropdown(
            selectedMood = selectedMood,
            onMoodChange = onMoodChange
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryFilterDropdown(
    selectedCategory: String?,
    onCategoryChange: (String?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val display = selectedCategory?.let { topicLabels[it] } ?: "All"
    val options = buildList {
        add(null to "All")
        categoryFilterOrder.forEach { key ->
            add(key to (topicLabels[key] ?: key))
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Category", fontSize = 14.sp, color = PrimaryTextColor)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = display,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .width(180.dp)
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                textStyle = TextStyle(fontSize = 14.sp, color = PrimaryTextColor),
                singleLine = true
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { (value, label) ->
                    DropdownMenuItem(
                        text = { Text(label) },
                        onClick = {
                            onCategoryChange(value)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateRangeFilterDropdown(
    selected: DateRangeFilter,
    onChange: (DateRangeFilter) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val display = when (selected) {
        DateRangeFilter.ANY -> "Any"
        DateRangeFilter.TODAY -> "Today"
        DateRangeFilter.THIS_WEEK -> "This week"
        DateRangeFilter.THIS_MONTH -> "This month"
    }
    val options = listOf(
        DateRangeFilter.ANY to "Any",
        DateRangeFilter.TODAY to "Today",
        DateRangeFilter.THIS_WEEK to "This week",
        DateRangeFilter.THIS_MONTH to "This month"
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Date range", fontSize = 14.sp, color = PrimaryTextColor)
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                value = display,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .width(180.dp)
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                textStyle = TextStyle(fontSize = 14.sp, color = PrimaryTextColor),
                singleLine = true
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { (value, label) ->
                    DropdownMenuItem(
                        text = { Text(label) },
                        onClick = {
                            onChange(value)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ImageFilterDropdown(
    selected: ImageFilter,
    onChange: (ImageFilter) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val display = when (selected) {
        ImageFilter.ANY -> "Any"
        ImageFilter.WITH_IMAGES -> "With images"
        ImageFilter.WITHOUT_IMAGES -> "Without images"
    }
    val options = listOf(
        ImageFilter.ANY to "Any",
        ImageFilter.WITH_IMAGES to "With images",
        ImageFilter.WITHOUT_IMAGES to "Without images"
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Has images", fontSize = 14.sp, color = PrimaryTextColor)
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                value = display,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .width(180.dp)
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                textStyle = TextStyle(fontSize = 14.sp, color = PrimaryTextColor),
                singleLine = true
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { (value, label) ->
                    DropdownMenuItem(
                        text = { Text(label) },
                        onClick = {
                            onChange(value)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MoodFilterDropdown(
    selectedMood: String?,
    onMoodChange: (String?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val display = selectedMood ?: "Any"
    val options = buildList {
        add(null to "Any")
        moodOptions.forEach { add(it to it) }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Mood", fontSize = 14.sp, color = PrimaryTextColor)
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                value = display,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .width(180.dp)
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                textStyle = TextStyle(fontSize = 14.sp, color = PrimaryTextColor),
                singleLine = true
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { (value, label) ->
                    DropdownMenuItem(
                        text = { Text(label, fontSize = 18.sp) },
                        onClick = {
                            onMoodChange(value)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchResultsSection(
    modifier: Modifier = Modifier,
    results: List<Note>,
    onNoteClick: (Long) -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Results (${results.size})",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = SecondaryTextColor
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (results.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "🔍", fontSize = 48.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No results found",
                        fontSize = 16.sp,
                        color = PrimaryTextColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Try different keywords or filters",
                        fontSize = 14.sp,
                        color = SecondaryTextColor
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(results, key = { it.id }) { note ->
                    NoteCard(
                        note = note,
                        variant = NoteCardVariant.TOPIC,
                        onNoteClick = onNoteClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
