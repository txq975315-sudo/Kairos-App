package com.example.kairosapplication.ui.editor

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Project
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.LocalDate
import kotlinx.coroutines.launch

private val EditorPrimaryPurple = Color(0xFF8A7CF8)

private val editorCategoryOrder = listOf(
    NotePrimaryCategory.FREESTYLE,
    NotePrimaryCategory.SELF_AWARENESS,
    NotePrimaryCategory.INTERPERSONAL,
    NotePrimaryCategory.INTIMACY_FAMILY,
    NotePrimaryCategory.SOMATIC_ENERGY,
    NotePrimaryCategory.MEANING
)

private val categoryPlaceholders = mapOf(
    NotePrimaryCategory.FREESTYLE to "Write anything you want to remember...",
    NotePrimaryCategory.SELF_AWARENESS to "Does this make me re-evaluate myself?\n(e.g. emotional patterns, behavioral habits, thinking blind spots)",
    NotePrimaryCategory.INTERPERSONAL to "Does this involve specific interactions with others?\n(friends/colleagues/strangers, excluding partners/family)",
    NotePrimaryCategory.INTIMACY_FAMILY to "Does this trigger boundary conflicts in blood/marriage ties?\n(partner arguments, parenting disagreements, family intervention)",
    NotePrimaryCategory.SOMATIC_ENERGY to "Does this directly relate to physical signals or energy fluctuations?\n(fatigue/pain/emotional physicalization, excluding psychological analysis)",
    NotePrimaryCategory.MEANING to "Does this question 'why am I doing this'?\n(career meaning, existence value, end-of-life reflection)"
)

private val categoryLabels = mapOf(
    NotePrimaryCategory.FREESTYLE to "Freestyle",
    NotePrimaryCategory.SELF_AWARENESS to "Self-Awareness",
    NotePrimaryCategory.INTERPERSONAL to "Interpersonal",
    NotePrimaryCategory.INTIMACY_FAMILY to "Intimacy & Family",
    NotePrimaryCategory.SOMATIC_ENERGY to "Health & Energy",
    NotePrimaryCategory.MEANING to "Meaning"
)

private val categoryEmojis = mapOf(
    NotePrimaryCategory.FREESTYLE to "✨",
    NotePrimaryCategory.SELF_AWARENESS to "🧠",
    NotePrimaryCategory.INTERPERSONAL to "👥",
    NotePrimaryCategory.INTIMACY_FAMILY to "❤️",
    NotePrimaryCategory.SOMATIC_ENERGY to "💪",
    NotePrimaryCategory.MEANING to "✨"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditorScreen(
    noteId: Long?,
    taskViewModel: TaskViewModel,
    onBackClick: () -> Unit,
    onSaveComplete: () -> Unit
) {
    val uiState by taskViewModel.uiState.collectAsState()
    val existingNote = remember(noteId, uiState.notePublished, uiState.noteInbox, uiState.noteTrash) {
        noteId?.let { id ->
            uiState.notePublished.find { it.id == id }
                ?: uiState.noteInbox.find { it.id == id }
                ?: uiState.noteTrash.find { it.id == id }
        }
    }

    var primaryCategory by remember { mutableStateOf(NotePrimaryCategory.FREESTYLE) }
    var secondaryCategory by remember { mutableStateOf("") }
    var behaviorSummary by remember { mutableStateOf("") }
    var bodyText by remember { mutableStateOf("") }
    var sceneTags by remember { mutableStateOf<List<String>>(emptyList()) }
    var moodIcon by remember { mutableStateOf<String?>(null) }
    var selectedProjectIds by remember { mutableStateOf<List<Long>>(emptyList()) }
    var imageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }

    LaunchedEffect(noteId, existingNote?.id, existingNote?.updatedAt) {
        val n = existingNote
        if (n != null) {
            primaryCategory = n.primaryCategory
            secondaryCategory = n.secondaryCategory
            behaviorSummary = n.behaviorSummary
            bodyText = n.body
            sceneTags = n.sceneTags
            moodIcon = n.moodIcon
            selectedProjectIds = n.projectIds
            imageUris = n.imageUris.mapNotNull { s ->
                runCatching { Uri.parse(s) }.getOrNull()
            }.take(4)
        } else if (noteId == null) {
            primaryCategory = NotePrimaryCategory.FREESTYLE
            secondaryCategory = ""
            behaviorSummary = ""
            bodyText = ""
            sceneTags = emptyList()
            moodIcon = null
            selectedProjectIds = emptyList()
            imageUris = emptyList()
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        taskViewModel.noteValidationErrors.collect { msg ->
            snackbarHostState.showSnackbar(msg)
        }
    }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri ?: return@rememberLauncherForActivityResult
        if (imageUris.size >= 4) return@rememberLauncherForActivityResult
        try {
            context.contentResolver.takePersistableUriPermission(
                uri,
                android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        } catch (_: SecurityException) {
        }
        imageUris = imageUris + uri
    }

    val scrollState = rememberScrollState()

    Scaffold(
        containerColor = BackgroundColor,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            EditorTopBar(
                onBackClick = onBackClick,
                onSaveClick = {
                    if (behaviorSummary.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Please add a short summary (What did I do)")
                        }
                    } else {
                        val now = System.currentTimeMillis()
                        val n = existingNote
                        val wasDeleted = n?.status == NoteStatus.DELETED
                        val toSave = Note(
                            id = n?.id ?: (noteId ?: 0L),
                            primaryCategory = primaryCategory,
                            secondaryCategory = secondaryCategory.trim(),
                            behaviorSummary = behaviorSummary.trim(),
                            body = bodyText.trim(),
                            sceneTags = sceneTags,
                            moodIcon = moodIcon,
                            linkedCategories = n?.linkedCategories ?: emptyList(),
                            projectIds = selectedProjectIds,
                            imageUris = imageUris.map { it.toString() },
                            recordedDate = n?.recordedDate ?: LocalDate.now(),
                            createdAt = n?.createdAt ?: 0L,
                            updatedAt = now,
                            status = if (wasDeleted) NoteStatus.PUBLISHED else (n?.status ?: NoteStatus.PUBLISHED),
                            deadline = if (wasDeleted) null else n?.deadline,
                            needsManualClassification = primaryCategory == NotePrimaryCategory.FREESTYLE
                        )
                        val isNew = noteId == null
                        taskViewModel.saveRoomNoteForEditor(
                            note = toSave,
                            isNew = isNew,
                            onSuccess = onSaveComplete
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            CategoryTabBar(
                selectedCategory = primaryCategory,
                onCategorySelected = { primaryCategory = it }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = behaviorSummary,
                    onValueChange = { if (it.length <= 50) behaviorSummary = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("What did I do:", color = PrimaryTextColor) },
                    placeholder = { Text("Brief description...", color = SecondaryTextColor) },
                    supportingText = { Text("${behaviorSummary.length}/50", color = SecondaryTextColor) },
                    singleLine = false,
                    maxLines = 3
                )

                var secondaryExpanded by remember { mutableStateOf(false) }
                val secondaryOptions = remember(primaryCategory) {
                    getSecondaryCategories(primaryCategory)
                }
                ExposedDropdownMenuBox(
                    expanded = secondaryExpanded,
                    onExpandedChange = { secondaryExpanded = !secondaryExpanded }
                ) {
                    OutlinedTextField(
                        value = secondaryCategory,
                        onValueChange = { secondaryCategory = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(MenuAnchorType.PrimaryEditable, enabled = true),
                        readOnly = false,
                        label = { Text("Secondary Category", color = PrimaryTextColor) },
                        placeholder = { Text("Select or enter...", color = SecondaryTextColor) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = secondaryExpanded)
                        }
                    )
                    ExposedDropdownMenu(
                        expanded = secondaryExpanded,
                        onDismissRequest = { secondaryExpanded = false }
                    ) {
                        secondaryOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    secondaryCategory = option
                                    secondaryExpanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = bodyText,
                    onValueChange = { bodyText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 150.dp),
                    placeholder = {
                        Text(
                            text = categoryPlaceholders[primaryCategory].orEmpty(),
                            color = SecondaryTextColor.copy(alpha = 0.7f)
                        )
                    },
                    maxLines = Int.MAX_VALUE
                )

                ImageModule(
                    imageUris = imageUris,
                    maxImages = 4,
                    onAddImage = {
                        if (imageUris.size < 4) {
                            imagePickerLauncher.launch("image/*")
                        }
                    },
                    onRemoveImage = { index ->
                        imageUris = imageUris.toMutableList().apply { removeAt(index) }
                    }
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding()
                    .navigationBarsPadding(),
                shadowElevation = 8.dp,
                color = Color.White
            ) {
                BottomToolbar(
                    tags = sceneTags,
                    mood = moodIcon,
                    onAddTag = { tag -> sceneTags = sceneTags + tag },
                    onRemoveTag = { tag -> sceneTags = sceneTags - tag },
                    onMoodSelected = { moodIcon = it },
                    projects = uiState.noteProjects,
                    selectedProjectIds = selectedProjectIds,
                    onProjectToggled = { id ->
                        selectedProjectIds = if (id in selectedProjectIds) {
                            selectedProjectIds - id
                        } else {
                            selectedProjectIds + id
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditorTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = PrimaryTextColor
                )
            }
        },
        actions = {
            TextButton(onClick = onSaveClick) {
                Text(
                    text = "Save",
                    color = EditorPrimaryPurple,
                    fontWeight = FontWeight.Medium
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}

@Composable
private fun CategoryTabBar(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(editorCategoryOrder) { _, category ->
            val selected = category == selectedCategory
            val chipModifier = Modifier
                .then(
                    if (selected) Modifier
                    else Modifier.border(
                        1.dp,
                        SecondaryTextColor.copy(alpha = 0.25f),
                        RoundedCornerShape(20.dp)
                    )
                )
                .clip(RoundedCornerShape(20.dp))
                .clickable { onCategorySelected(category) }
            Surface(
                modifier = chipModifier,
                shape = RoundedCornerShape(20.dp),
                color = if (selected) EditorPrimaryPurple.copy(alpha = 0.12f) else Color.Transparent
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = categoryEmojis[category] ?: "📝",
                        fontSize = 14.sp
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = categoryLabels[category] ?: category,
                        fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal,
                        color = if (selected) EditorPrimaryPurple else SecondaryTextColor,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun ImageModule(
    imageUris: List<Uri>,
    maxImages: Int,
    onAddImage: () -> Unit,
    onRemoveImage: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Images",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = PrimaryTextColor
        )
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            imageUris.chunked(2).forEachIndexed { rowIndex, rowImages ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowImages.forEachIndexed { colIndex, uri ->
                        val globalIndex = rowIndex * 2 + colIndex
                        ImageSlot(
                            uri = uri,
                            onRemove = { onRemoveImage(globalIndex) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (rowImages.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            if (imageUris.size < maxImages) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AddImageSlot(
                        onClick = onAddImage,
                        modifier = Modifier.weight(1f)
                    )
                    if (imageUris.size == maxImages - 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
        Text(
            text = "${imageUris.size}/$maxImages images",
            fontSize = 12.sp,
            color = SecondaryTextColor
        )
    }
}

@Composable
private fun ImageSlot(
    uri: Uri,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
    ) {
        AsyncImage(
            model = uri,
            contentDescription = "Attached image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        IconButton(
            onClick = onRemove,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .size(28.dp)
                .background(Color.Black.copy(alpha = 0.45f), CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove image",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun AddImageSlot(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, SecondaryTextColor.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.AddPhotoAlternate,
            contentDescription = "Add image",
            tint = SecondaryTextColor,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
private fun BottomToolbar(
    tags: List<String>,
    mood: String?,
    onAddTag: (String) -> Unit,
    onRemoveTag: (String) -> Unit,
    onMoodSelected: (String?) -> Unit,
    projects: List<Project>,
    selectedProjectIds: List<Long>,
    onProjectToggled: (Long) -> Unit
) {
    var showTagInput by remember { mutableStateOf(false) }
    var newTag by remember { mutableStateOf("") }
    var showMoodPicker by remember { mutableStateOf(false) }
    var showProjectPicker by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                tags.forEach { tag ->
                    InputChip(
                        selected = false,
                        onClick = { onRemoveTag(tag) },
                        label = { Text(tag, fontSize = 12.sp) },
                        trailingIcon = {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Remove",
                                modifier = Modifier.size(14.dp)
                            )
                        },
                        modifier = Modifier.padding(end = 4.dp)
                    )
                }
            }
            if (showTagInput) {
                OutlinedTextField(
                    value = newTag,
                    onValueChange = { newTag = it },
                    modifier = Modifier.width(120.dp),
                    placeholder = { Text("Tag name", fontSize = 12.sp) },
                    singleLine = true
                )
                IconButton(onClick = {
                    if (newTag.isNotBlank()) {
                        onAddTag(newTag.trim())
                        newTag = ""
                    }
                    showTagInput = false
                }) {
                    Icon(Icons.Default.Check, contentDescription = "Add")
                }
            } else {
                TextButton(onClick = { showTagInput = true }) {
                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Add Tag", fontSize = 12.sp)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.clickable { showMoodPicker = !showMoodPicker },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = mood ?: "😊", fontSize = 20.sp)
                Spacer(Modifier.width(4.dp))
                Text("Mood", fontSize = 12.sp, color = SecondaryTextColor)
            }
            Row(
                modifier = Modifier.clickable { showProjectPicker = !showProjectPicker },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "📁", fontSize = 20.sp)
                Spacer(Modifier.width(4.dp))
                Text(
                    text = if (selectedProjectIds.isEmpty()) "Add Project" else "${selectedProjectIds.size} Projects",
                    fontSize = 12.sp,
                    color = SecondaryTextColor
                )
            }
        }

        if (showMoodPicker) {
            MoodPickerRow(
                selectedMood = mood,
                onMoodSelected = {
                    onMoodSelected(it)
                    showMoodPicker = false
                }
            )
        }
        if (showProjectPicker) {
            ProjectPickerColumn(
                projects = projects,
                selectedIds = selectedProjectIds,
                onToggle = onProjectToggled
            )
        }
    }
}

@Composable
private fun MoodPickerRow(
    selectedMood: String?,
    onMoodSelected: (String?) -> Unit
) {
    val moods = listOf("😊", "😃", "😄", "😆", "😐", "😢", "😡", "🤔", "😌", "❤️", "🔥", "💡")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        moods.forEach { m ->
            FilterChip(
                selected = selectedMood == m,
                onClick = {
                    onMoodSelected(if (selectedMood == m) null else m)
                },
                label = { Text(m, fontSize = 20.sp) }
            )
        }
    }
}

@Composable
private fun ProjectPickerColumn(
    projects: List<Project>,
    selectedIds: List<Long>,
    onToggle: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .heightIn(max = 200.dp)
    ) {
        projects.forEach { project ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = project.id in selectedIds,
                    onCheckedChange = { onToggle(project.id) }
                )
                Spacer(Modifier.width(8.dp))
                Text("📁 ${project.name}", color = PrimaryTextColor)
            }
        }
    }
}

private fun getSecondaryCategories(primaryCategory: String): List<String> = when (primaryCategory) {
    NotePrimaryCategory.SELF_AWARENESS -> listOf(
        "Emotional Trigger Event",
        "Behavioral Pattern",
        "Thinking Blind Spot",
        "Self-Discovery",
        "Habit Reflection"
    )
    NotePrimaryCategory.INTERPERSONAL -> listOf(
        "Friend Interaction",
        "Colleague Communication",
        "Stranger Encounter",
        "Social Event",
        "Conflict Resolution"
    )
    NotePrimaryCategory.INTIMACY_FAMILY -> listOf(
        "Partner Conflict",
        "Parenting Moment",
        "Family Gathering",
        "Boundary Setting",
        "Emotional Support"
    )
    NotePrimaryCategory.SOMATIC_ENERGY -> listOf(
        "Fatigue Signal",
        "Pain Point",
        "Energy Level",
        "Sleep Quality",
        "Physical Sensation"
    )
    NotePrimaryCategory.MEANING -> listOf(
        "Career Questioning",
        "Existential Reflection",
        "Life Purpose",
        "Value Clarification",
        "Legacy Thinking"
    )
    else -> listOf(
        "Daily Note",
        "Idea",
        "Observation",
        "Reflection"
    )
}
