package com.example.kairosapplication.ui.editor

import android.graphics.Color as AndroidColor
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.ui.components.NoteCardConstants
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Note
import com.example.taskmodel.model.Project
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.LocalDate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val DividerLight = Color(0xFFE8E8E8)
private val SummaryFocusBg = Color.Transparent

private const val MaxLinkedCategories = 2

/**
 * Primary chip: corner radius 3 (Figma). [padding(1.dp)] between neighbours; [clip] clips fills;
 * tight [shadow] per design (blur ~1, black 25%).
 */
private val PrimaryCategoryChipShape = RoundedCornerShape(3.dp)

/** Figma: inside stroke ~0.1 — use hairline in dp. */
private val PrimaryChipStrokeWidth = 0.5.dp

private val PrimaryChipShadowElevation = 1.dp

private val PrimaryChipShadowColor = Color.Black.copy(alpha = 0.25f)

/** Selected chip interior: deep enough for white type (was 20% / 10% + heavy sheen → looked empty). */
private const val PrimaryChipBaseFillAlpha = 0.80f

private const val PrimaryChipRadialLayerAlpha = 0.68f

/** Radial center: slightly more brand so the glow isn’t pure white. */
private const val PrimaryChipRadialCenterBrandMix = 0.48f

/** Vertical glass sheen (top/bottom); high values read as “milky white” over the fill. */
private const val PrimaryChipSheenTopAlpha = 0.08f

private const val PrimaryChipSheenBottomAlpha = 0.05f

/** Vertical padding between [HorizontalDivider] and module content (1dp). */
private val EditorSectionVerticalPad = 1.dp

/** Extra gap between scroll blocks (body, tags, images, …). */
private val EditorBlockGap = 1.dp

/** White label on tinted chips (secondary / linked). */
private val TopicChipWhiteTextStyle = TextStyle(
    color = Color.White,
    fontWeight = FontWeight.SemiBold,
    shadow = Shadow(
        color = Color.Black.copy(alpha = 0.42f),
        offset = Offset(0f, 1f),
        blurRadius = 2.5f
    )
)

private val XiaohongshuPostRed = Color(0xFFFF2D55)
private val XiaohongshuPostRedDisabled = Color(0xFFFFCCDD)

private val primaryCategoryOrder = listOf(
    NotePrimaryCategory.FREESTYLE,
    NotePrimaryCategory.SELF_AWARENESS,
    NotePrimaryCategory.INTERPERSONAL,
    NotePrimaryCategory.INTIMACY_FAMILY,
    NotePrimaryCategory.SOMATIC_ENERGY,
    NotePrimaryCategory.MEANING
)

private val linkedCategoriesOrder = listOf(
    NotePrimaryCategory.SELF_AWARENESS,
    NotePrimaryCategory.INTERPERSONAL,
    NotePrimaryCategory.INTIMACY_FAMILY,
    NotePrimaryCategory.SOMATIC_ENERGY,
    NotePrimaryCategory.MEANING
)

private fun colorToHsv(color: Color): FloatArray {
    val hsv = FloatArray(3)
    AndroidColor.colorToHSV(color.toArgb(), hsv)
    return hsv
}

/**
 * Secondary + linked selected fill: derived from **current primary** brand color —
 * saturation −0.35, value +0.20, then slight cool shift, fully opaque.
 */
private fun secondaryLinkedFillFromPrimary(primaryBrand: Color): Color {
    val hsv = colorToHsv(primaryBrand)
    hsv[1] = (hsv[1] - 0.35f).coerceIn(0.08f, 1f)
    hsv[2] = (hsv[2] + 0.20f).coerceIn(0f, 1f)
    val rgb = AndroidColor.HSVToColor(hsv)
    val r = (AndroidColor.red(rgb) * 0.94f - 4f).toInt().coerceIn(0, 255)
    val g = (AndroidColor.green(rgb) * 0.98f).toInt().coerceIn(0, 255)
    val b = (AndroidColor.blue(rgb) * 1.06f + 4f).toInt().coerceIn(0, 255)
    return Color(r, g, b)
}

private fun summaryPlaceholder(category: String): String = when (category) {
    NotePrimaryCategory.FREESTYLE -> "Optional: briefly summarize…"
    NotePrimaryCategory.SELF_AWARENESS -> "What did I do?"
    NotePrimaryCategory.INTERPERSONAL -> "What did I do?"
    NotePrimaryCategory.INTIMACY_FAMILY -> "What did I do?"
    NotePrimaryCategory.SOMATIC_ENERGY -> "What are my body signals?"
    NotePrimaryCategory.MEANING -> "What did I do and why?"
    else -> "Optional…"
}

private val BodyHashTagRegex = Regex("#([^\\s#@]+)")
private val BodyMentionRegex = Regex("@([^\\s#@]+)")

/**
 * Removes `#topic` / `@person` spans from [body], returns cleaned body plus labels for [sceneTags]
 * (tags without `#`, mentions stored as `@name`).
 */
private fun stripHashTagsAndMentionsFromBody(body: String): Triple<String, List<String>, List<String>> {
    val tags = BodyHashTagRegex.findAll(body)
        .mapNotNull { it.groupValues.getOrNull(1)?.trim()?.takeIf { s -> s.isNotEmpty() } }
        .distinct()
        .toList()
    val mentionNames = BodyMentionRegex.findAll(body)
        .mapNotNull { it.groupValues.getOrNull(1)?.trim()?.takeIf { s -> s.isNotEmpty() } }
        .distinct()
        .toList()
    var stripped = BodyHashTagRegex.replace(body, "")
    stripped = BodyMentionRegex.replace(stripped, "")
    stripped = stripped.replace(Regex("[ \\t]{2,}"), " ")
    stripped = stripped.replace(Regex("#+$"), "").replace(Regex("@+$"), "").trimEnd()
    val mentions = mentionNames.map { "@$it" }
    return Triple(stripped, tags, mentions)
}

private fun bodyPlaceholder(category: String): String = when (category) {
    NotePrimaryCategory.FREESTYLE -> "Write anything you want…"
    NotePrimaryCategory.SELF_AWARENESS -> "Describe emotions, behaviors, or discoveries…"
    NotePrimaryCategory.INTERPERSONAL -> "Record interaction details and feelings…"
    NotePrimaryCategory.INTIMACY_FAMILY -> "Record conflicts or warm moments…"
    NotePrimaryCategory.SOMATIC_ENERGY -> "Describe body feelings or energy changes…"
    NotePrimaryCategory.MEANING -> "Write your value questions or thoughts…"
    else -> "Write…"
}

private fun isTopicMode(primary: String): Boolean =
    primary != NotePrimaryCategory.FREESTYLE

private fun canPost(primary: String, summary: String, secondary: String, body: String): Boolean {
    if (body.trim().isEmpty()) return false
    return if (isTopicMode(primary)) {
        summary.trim().isNotEmpty() && secondary.trim().isNotEmpty()
    } else {
        true
    }
}

private fun canSaveDraft(primary: String, summary: String, secondary: String): Boolean {
    return if (isTopicMode(primary)) {
        summary.trim().isNotEmpty() && secondary.trim().isNotEmpty()
    } else {
        true
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditorScreen(
    noteId: Long?,
    taskViewModel: TaskViewModel,
    onBackClick: () -> Unit,
    onSaveComplete: () -> Unit,
    /** When set (new note only), primary topic is fixed to this key; user cannot change it. */
    lockedPrimaryCategory: String? = null
) {
    val uiState by taskViewModel.uiState.collectAsState()
    var lastPrimaryForNewNote by rememberSaveable { mutableStateOf(NotePrimaryCategory.FREESTYLE) }

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
    var linkedCategories by remember { mutableStateOf<List<String>>(emptyList()) }
    var sceneTags by remember { mutableStateOf<List<String>>(emptyList()) }
    var moodIcon by remember { mutableStateOf<String?>(null) }
    var selectedProjectIds by remember { mutableStateOf<List<Long>>(emptyList()) }
    var imageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var showAddSecondaryDialog by remember { mutableStateOf(false) }
    var newSecondaryDialogText by remember { mutableStateOf("") }
    var summaryFocused by remember { mutableStateOf(false) }
    var bodyFocused by remember { mutableStateOf(false) }
    var primaryCategoryLocked by remember { mutableStateOf(false) }

    LaunchedEffect(noteId, existingNote?.id, existingNote?.updatedAt, lockedPrimaryCategory) {
        val n = existingNote
        if (n != null) {
            primaryCategoryLocked = false
            primaryCategory = n.primaryCategory
            secondaryCategory = n.secondaryCategory
            behaviorSummary = n.behaviorSummary
            bodyText = n.body
            linkedCategories = n.linkedCategories
                .distinct()
                .filter {
                    it != n.primaryCategory && it != NotePrimaryCategory.FREESTYLE
                }
                .take(MaxLinkedCategories)
            sceneTags = n.sceneTags
            moodIcon = n.moodIcon
            selectedProjectIds = n.projectIds
            imageUris = n.imageUris.mapNotNull { s -> runCatching { Uri.parse(s) }.getOrNull() }.take(4)
        } else if (noteId == null) {
            val locked = lockedPrimaryCategory?.takeIf { NotePrimaryCategory.isTopic(it) }
            if (locked != null) {
                primaryCategoryLocked = true
                primaryCategory = locked
                lastPrimaryForNewNote = locked
                secondaryCategory = ""
                behaviorSummary = ""
                bodyText = ""
                linkedCategories = emptyList()
                sceneTags = emptyList()
                moodIcon = null
                selectedProjectIds = emptyList()
                imageUris = emptyList()
                val opts =
                    NoteCardConstants.mergedSecondaryLabels(locked, uiState.customSecondaryCategories)
                secondaryCategory = opts.firstOrNull().orEmpty()
                val prefillProjects = taskViewModel.takePendingNewNoteProjectIds()
                if (!prefillProjects.isNullOrEmpty()) {
                    selectedProjectIds = prefillProjects
                }
                return@LaunchedEffect
            }
            primaryCategoryLocked = false
            primaryCategory = lastPrimaryForNewNote
            secondaryCategory = ""
            behaviorSummary = ""
            bodyText = ""
            linkedCategories = emptyList()
            sceneTags = emptyList()
            moodIcon = null
            selectedProjectIds = emptyList()
            imageUris = emptyList()
            val prefillProjects = taskViewModel.takePendingNewNoteProjectIds()
            if (!prefillProjects.isNullOrEmpty()) {
                selectedProjectIds = prefillProjects
            }
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
    val bodyFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

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

    val mergedSecondaryOptions = remember(primaryCategory, uiState.customSecondaryCategories) {
        NoteCardConstants.mergedSecondaryLabels(primaryCategory, uiState.customSecondaryCategories)
    }
    val secondaryDistinctCount =
        mergedSecondaryOptions.distinctBy { it.lowercase() }.size
    val canAddMoreSecondary =
        isTopicMode(primaryCategory) && secondaryDistinctCount < 8

    fun buildNoteToSave(): Note {
        val now = System.currentTimeMillis()
        val n = existingNote
        val wasDeleted = n?.status == NoteStatus.DELETED
        val topic = isTopicMode(primaryCategory)
        val safeLinked = if (topic) {
            linkedCategories.filter {
                it != primaryCategory && it != NotePrimaryCategory.FREESTYLE
            }.distinct()
                .take(MaxLinkedCategories)
        } else {
            emptyList()
        }
        return Note(
            id = n?.id ?: (noteId ?: 0L),
            primaryCategory = primaryCategory,
            secondaryCategory = if (topic) secondaryCategory.trim() else "",
            behaviorSummary = behaviorSummary.trim(),
            body = bodyText.trim(),
            sceneTags = sceneTags,
            moodIcon = moodIcon,
            linkedCategories = safeLinked,
            projectIds = selectedProjectIds,
            imageUris = imageUris.map { it.toString() },
            recordedDate = n?.recordedDate ?: LocalDate.now(),
            createdAt = n?.createdAt ?: 0L,
            updatedAt = now,
            status = if (wasDeleted) NoteStatus.PUBLISHED else (n?.status ?: NoteStatus.PUBLISHED),
            deadline = if (wasDeleted) null else n?.deadline,
            needsManualClassification = primaryCategory == NotePrimaryCategory.FREESTYLE
        )
    }

    fun performSave(requireBodyForPost: Boolean) {
        val topic = isTopicMode(primaryCategory)
        if (topic) {
            if (behaviorSummary.isBlank()) {
                scope.launch { snackbarHostState.showSnackbar("Please add a short summary") }
                return
            }
            if (secondaryCategory.isBlank()) {
                scope.launch { snackbarHostState.showSnackbar("Please select a secondary category") }
                return
            }
        }
        if (requireBodyForPost && bodyText.isBlank()) {
            scope.launch { snackbarHostState.showSnackbar("Please add note content") }
            return
        }
        if (existingNote?.projectIds?.isNotEmpty() == true &&
            primaryCategory == NotePrimaryCategory.FREESTYLE
        ) {
            scope.launch {
                snackbarHostState.showSnackbar("This note is in a project — choose a topic first")
            }
            return
        }
        val toSave = buildNoteToSave()
        val isNew = noteId == null
        taskViewModel.saveRoomNoteForEditor(
            note = toSave,
            isNew = isNew,
            onSuccess = onSaveComplete
        )
    }

    val isPublishedDeepLink =
        noteId != null && existingNote != null && existingNote.status == NoteStatus.PUBLISHED
    if (isPublishedDeepLink) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.ScreenBackground)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "This note is published. Open it from the timeline or topic view, expand the card, and use Change topic, Change project, or Continue. Full-body editing is not available here.",
                color = AppColors.PrimaryText,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextButton(onClick = onBackClick) {
                Text("Back", color = AppColors.PrimaryText)
            }
        }
        return
    }

    if (showAddSecondaryDialog) {
        AlertDialog(
            onDismissRequest = {
                showAddSecondaryDialog = false
                newSecondaryDialogText = ""
            },
            title = { Text("New category", color = PrimaryTextColor) },
            text = {
                OutlinedTextField(
                    value = newSecondaryDialogText,
                    onValueChange = { newSecondaryDialogText = it },
                    label = { Text("Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        taskViewModel.addCustomSecondaryCategory(primaryCategory, newSecondaryDialogText)
                        secondaryCategory = newSecondaryDialogText.trim()
                        showAddSecondaryDialog = false
                        newSecondaryDialogText = ""
                    }
                ) {
                    Text("Add", color = Color(0xFF8A7CF8))
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddSecondaryDialog = false }) {
                    Text("Cancel", color = SecondaryTextColor)
                }
            }
        )
    }

    val scrollState = rememberScrollState()
    val postEnabled = canPost(primaryCategory, behaviorSummary, secondaryCategory, bodyText)
    val draftEnabled = canSaveDraft(primaryCategory, behaviorSummary, secondaryCategory)

    Scaffold(
        containerColor = AppColors.ScreenBackground,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            EditorTopBar(
                onBackClick = onBackClick,
                onSaveDraft = {
                    if (!draftEnabled && isTopicMode(primaryCategory)) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Add summary and secondary category first")
                        }
                    } else {
                        performSave(requireBodyForPost = false)
                    }
                },
                onPost = {
                    if (!postEnabled) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                if (bodyText.isBlank()) "Please add note content"
                                else "Add summary and secondary category"
                            )
                        }
                    } else {
                        performSave(requireBodyForPost = true)
                    }
                },
                draftEnabled = draftEnabled,
                postEnabled = postEnabled
            )
        }
    ) { padding ->
        val layoutDirection = LocalLayoutDirection.current
        // Scaffold bottom padding already follows system bars; adding imePadding stacks gaps.
        // Consume navigationBars ∪ ime once so the toolbar sits flush above the keyboard.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    PaddingValues(
                        top = padding.calculateTopPadding(),
                        start = padding.calculateStartPadding(layoutDirection),
                        end = padding.calculateEndPadding(layoutDirection),
                        bottom = 0.dp
                    )
                )
                .windowInsetsPadding(WindowInsets.navigationBars.union(WindowInsets.ime))
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .fillMaxWidth()
            ) {
                HorizontalDivider(color = DividerLight, thickness = 1.dp)

                Column(Modifier.padding(horizontal = 12.dp)) {
                if (primaryCategoryLocked) {
                    PrimaryCategoryLockedBanner(primaryCategory = primaryCategory)
                } else {
                    PrimaryCategoryTextRow(
                        selected = primaryCategory,
                        onSelect = { cat ->
                            primaryCategory = cat
                            if (noteId == null) lastPrimaryForNewNote = cat
                            if (cat == NotePrimaryCategory.FREESTYLE) {
                                secondaryCategory = ""
                                linkedCategories = emptyList()
                            } else {
                                val opts =
                                    NoteCardConstants.mergedSecondaryLabels(
                                        cat,
                                        uiState.customSecondaryCategories
                                    )
                                if (secondaryCategory.isBlank() || secondaryCategory !in opts) {
                                    secondaryCategory = opts.firstOrNull().orEmpty()
                                }
                                linkedCategories = linkedCategories
                                    .filter { it != cat && it != NotePrimaryCategory.FREESTYLE }
                                    .distinct()
                                    .take(MaxLinkedCategories)
                            }
                        }
                    )
                }
                }

                HorizontalDivider(color = DividerLight, thickness = 1.dp)

                Column(Modifier.padding(horizontal = 12.dp)) {
                BehaviorSummaryField(
                    text = behaviorSummary,
                    onTextChange = { if (it.length <= 50) behaviorSummary = it },
                    placeholder = summaryPlaceholder(primaryCategory),
                    focused = summaryFocused,
                    onFocusChange = { summaryFocused = it },
                    counter = "${behaviorSummary.length}/50"
                )
                }

                if (!isTopicMode(primaryCategory)) {
                    HorizontalDivider(color = DividerLight, thickness = 1.dp)
                }

                if (isTopicMode(primaryCategory)) {
                    HorizontalDivider(color = DividerLight, thickness = 1.dp)

                    Column(Modifier.padding(horizontal = 12.dp)) {
                    SecondaryCategoryTwoRows(
                        options = mergedSecondaryOptions,
                        selected = secondaryCategory,
                        primaryColor = NoteCardConstants.categoryColor(primaryCategory),
                        showAdd = canAddMoreSecondary,
                        onSelect = { secondaryCategory = it },
                        onAddClick = { showAddSecondaryDialog = true }
                    )
                    }

                    HorizontalDivider(color = DividerLight, thickness = 1.dp)

                    Column(Modifier.padding(horizontal = 12.dp)) {
                    LinkedCategoriesTextRow(
                        primaryCategory = primaryCategory,
                        selected = linkedCategories,
                        onToggle = { key ->
                            if (key in linkedCategories) {
                                linkedCategories = linkedCategories - key
                            } else if (linkedCategories.size < MaxLinkedCategories) {
                                linkedCategories = linkedCategories + key
                            } else {
                                scope.launch {
                                    snackbarHostState.showSnackbar("You can link at most 2 topic categories")
                                }
                            }
                        }
                    )
                    }

                    HorizontalDivider(color = DividerLight, thickness = 1.dp)
                }

                Spacer(Modifier.height(EditorBlockGap))

                Column(Modifier.padding(horizontal = 12.dp)) {
                BodyEditorField(
                    text = bodyText,
                    onTextChange = { bodyText = it },
                    placeholder = bodyPlaceholder(primaryCategory),
                    focused = bodyFocused,
                    onFocusChange = { bodyFocused = it },
                    focusRequester = bodyFocusRequester
                )
                }

                if (sceneTags.isNotEmpty()) {
                    Spacer(Modifier.height(EditorBlockGap))
                    Column(Modifier.padding(horizontal = 12.dp)) {
                    TagsRow(tags = sceneTags, onRemove = { t -> sceneTags = sceneTags - t })
                    }
                }

                Spacer(Modifier.height(EditorBlockGap))

                Column(Modifier.padding(horizontal = 12.dp)) {
                ImageSectionCompact(
                    imageUris = imageUris,
                    onAdd = {
                        if (imageUris.size < 4) imagePickerLauncher.launch("image/*")
                    },
                    onRemove = { idx -> imageUris = imageUris.toMutableList().apply { removeAt(idx) } }
                )
                }

            }

            HorizontalDivider(color = DividerLight, thickness = 1.dp)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = AppColors.ScreenBackground,
                shadowElevation = 0.dp
            ) {
                EditorBottomToolBar(
                    mood = moodIcon,
                    onMoodSelected = { m -> moodIcon = if (moodIcon == m) null else m },
                    projects = uiState.noteProjects,
                    selectedProjectIds = selectedProjectIds,
                    onProjectToggled = { id ->
                        selectedProjectIds =
                            if (id in selectedProjectIds) selectedProjectIds - id
                            else selectedProjectIds + id
                    },
                    onAppendToBody = { suffix ->
                        bodyText += suffix
                        scope.launch {
                            delay(32)
                            bodyFocusRequester.requestFocus()
                        }
                    },
                    onConfirm = {
                        val (newBody, newTags, newMentions) = stripHashTagsAndMentionsFromBody(bodyText)
                        bodyText = newBody
                        var next = sceneTags
                        for (t in newTags) {
                            if (t !in next) next = next + t
                        }
                        for (m in newMentions) {
                            if (m !in next) next = next + m
                        }
                        sceneTags = next
                        keyboardController?.hide()
                        focusManager.clearFocus(true)
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
    onSaveDraft: () -> Unit,
    onPost: () -> Unit,
    draftEnabled: Boolean,
    postEnabled: Boolean
) {
    Surface(color = AppColors.ScreenBackground, shadowElevation = 0.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 4.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AppColors.PrimaryText
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = onSaveDraft,
                    enabled = draftEnabled,
                    modifier = Modifier.padding(0.dp)
                ) {
                    Text("Save draft", fontSize = 13.sp, color = AppColors.SecondaryText)
                }
                Button(
                    onClick = onPost,
                    enabled = postEnabled,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = XiaohongshuPostRed,
                        disabledContainerColor = XiaohongshuPostRedDisabled,
                        contentColor = Color.White,
                        disabledContentColor = Color.White.copy(alpha = 0.7f)
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Text("Post", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

/**
 * Selected chip overlays: radial + sheen only. **Solid base is drawn in [drawBehind]** on the chip
 * so it cannot disappear under border/clip/ripple measurement quirks.
 */
@Composable
private fun PrimaryCategorySelectionLayers(brandColor: Color, shape: RoundedCornerShape) {
    val radialCenter = lerp(Color.White, brandColor, PrimaryChipRadialCenterBrandMix)
    Box(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val w = constraints.maxWidth.toFloat().coerceAtLeast(1f)
            val h = constraints.maxHeight.toFloat().coerceAtLeast(1f)
            val radius = kotlin.math.max(w, h) * 0.55f
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = PrimaryChipRadialLayerAlpha }
                    .background(
                        brush = Brush.radialGradient(
                            colorStops = arrayOf(
                                0f to radialCenter,
                                1f to brandColor
                            ),
                            center = Offset(w / 2f, h / 2f),
                            radius = radius
                        ),
                        shape = shape
                    )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = PrimaryChipSheenTopAlpha),
                            Color.Transparent,
                            Color.White.copy(alpha = PrimaryChipSheenBottomAlpha)
                        )
                    ),
                    shape = shape
                )
        )
    }
}

@Composable
private fun PrimaryCategoryLockedBanner(primaryCategory: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = EditorSectionVerticalPad),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = NoteCardConstants.categoryEmoji(primaryCategory),
            fontSize = 20.sp,
            modifier = Modifier.padding(end = 10.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Primary topic",
                fontSize = 11.sp,
                color = AppColors.SecondaryText,
                lineHeight = 13.sp
            )
            Text(
                text = NoteCardConstants.primaryCategoryLabel(primaryCategory),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = NoteCardConstants.categoryColor(primaryCategory)
            )
        }
        Text(
            text = "Fixed",
            fontSize = 11.sp,
            color = AppColors.SecondaryText,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun PrimaryCategoryTextRow(
    selected: String,
    onSelect: (String) -> Unit
) {
    val chipShape = PrimaryCategoryChipShape
    val density = LocalDensity.current
    val cornerPx = with(density) { 3.dp.toPx() }
    val selectedWordStyle = TextStyle(
        color = Color.White,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 12.sp,
        textAlign = TextAlign.Center,
        shadow = Shadow(
            color = Color.Black.copy(alpha = 0.45f),
            offset = Offset(0f, 1.1f),
            blurRadius = 3f
        )
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = EditorSectionVerticalPad),
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        primaryCategoryOrder.forEach { key ->
            val interactionSource = remember(key) { MutableInteractionSource() }
            val isSel = key == selected
            val color = NoteCardConstants.categoryColor(key)
            val words = NoteCardConstants.primaryCategoryLabel(key).split(" ")
            Box(
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 36.dp)
                    .padding(1.dp)
                    .then(
                        if (isSel) {
                            Modifier.shadow(
                                elevation = PrimaryChipShadowElevation,
                                shape = chipShape,
                                clip = false,
                                ambientColor = PrimaryChipShadowColor,
                                spotColor = PrimaryChipShadowColor
                            )
                        } else {
                            Modifier
                        }
                    )
                    .clip(chipShape)
                    .drawBehind {
                        if (isSel) {
                            drawRoundRect(
                                color = color.copy(alpha = PrimaryChipBaseFillAlpha),
                                cornerRadius = CornerRadius(cornerPx, cornerPx)
                            )
                        }
                    }
                    .then(
                        if (isSel) {
                            Modifier.border(
                                width = PrimaryChipStrokeWidth,
                                color = color,
                                shape = chipShape
                            )
                        } else {
                            Modifier
                        }
                    )
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { onSelect(key) }
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isSel) {
                    PrimaryCategorySelectionLayers(brandColor = color, shape = chipShape)
                }
                Column(
                    modifier = Modifier.padding(horizontal = 2.dp, vertical = 3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    words.forEach { word ->
                        if (isSel) {
                            Text(
                                text = word,
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = selectedWordStyle
                            )
                        } else {
                            Text(
                                text = word,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal,
                                color = AppColors.SecondaryText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                lineHeight = 11.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BehaviorSummaryField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    focused: Boolean,
    onFocusChange: (Boolean) -> Unit,
    counter: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = EditorSectionVerticalPad),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .background(if (focused) SummaryFocusBg else Color.Transparent)
                .padding(horizontal = 4.dp, vertical = 4.dp)
        ) {
            BasicTextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { onFocusChange(it.isFocused) },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = AppColors.PrimaryText
                ),
                decorationBox = { inner ->
                    if (text.isEmpty()) {
                        Text(
                            text = placeholder,
                            fontSize = 14.sp,
                            color = AppColors.HintText
                        )
                    }
                    inner()
                }
            )
        }
        Spacer(Modifier.width(12.dp))
        Text(
            text = counter,
            fontSize = 12.sp,
            color = if (text.length >= 50) AppColors.High else AppColors.HintText
        )
    }
}

@Composable
private fun SecondaryCategoryTwoRows(
    options: List<String>,
    selected: String,
    primaryColor: Color,
    showAdd: Boolean,
    onSelect: (String) -> Unit,
    onAddClick: () -> Unit
) {
    val distinctOptions = remember(options) { options.distinct() }
    val showAddButton = showAdd && distinctOptions.size < 8
    val itemsPerRow = 4
    val firstRowItems = if (showAddButton && distinctOptions.size >= itemsPerRow) {
        distinctOptions.take(itemsPerRow - 1)
    } else {
        distinctOptions.take(itemsPerRow)
    }
    val secondRowItems = distinctOptions.drop(firstRowItems.size)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = EditorSectionVerticalPad),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            firstRowItems.forEach { option ->
                val isSelected = selected == option
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onSelect(option) }
                        .background(
                            if (isSelected) {
                                secondaryLinkedFillFromPrimary(primaryColor)
                            } else {
                                Color.Transparent
                            }
                        )
                        .padding(horizontal = 4.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = option,
                        maxLines = 1,
                        overflow = TextOverflow.Visible,
                        style = if (isSelected) {
                            TopicChipWhiteTextStyle.merge(
                                TextStyle(fontSize = 10.sp, textAlign = TextAlign.Center)
                            )
                        } else {
                            TextStyle(
                                fontSize = 10.sp,
                                color = AppColors.SecondaryText,
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                }
            }
            if (showAddButton && firstRowItems.size < itemsPerRow) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onAddClick() }
                        .padding(vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = AppColors.SecondaryText,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        if (secondRowItems.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                secondRowItems.forEach { option ->
                    val isSelected = selected == option
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onSelect(option) }
                            .background(
                                if (isSelected) {
                                    secondaryLinkedFillFromPrimary(primaryColor)
                                } else {
                                    Color.Transparent
                                }
                            )
                            .padding(horizontal = 4.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = option,
                            maxLines = 1,
                            overflow = TextOverflow.Visible,
                            style = if (isSelected) {
                                TopicChipWhiteTextStyle.merge(
                                    TextStyle(fontSize = 10.sp, textAlign = TextAlign.Center)
                                )
                            } else {
                                TextStyle(
                                    fontSize = 10.sp,
                                    color = AppColors.SecondaryText,
                                    textAlign = TextAlign.Center
                                )
                            }
                        )
                    }
                }
                repeat(itemsPerRow - secondRowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun LinkedCategoriesTextRow(
    primaryCategory: String,
    selected: List<String>,
    onToggle: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = EditorSectionVerticalPad),
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        val primaryBrand = NoteCardConstants.categoryColor(primaryCategory)
        val linkedFill = secondaryLinkedFillFromPrimary(primaryBrand)
        linkedCategoriesOrder.forEach { key ->
            val isPrimary = key == primaryCategory
            val isSel = key in selected
            val words = NoteCardConstants.primaryCategoryLabel(key).split(" ")
            val bg = when {
                isPrimary -> linkedFill
                isSel -> linkedFill
                else -> Color.Transparent
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(bg)
                    .then(
                        if (isPrimary) Modifier
                        else Modifier.clickable { onToggle(key) }
                    )
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    words.forEach { word ->
                        Text(
                            text = word,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = if (isPrimary || isSel) {
                                TopicChipWhiteTextStyle.merge(
                                    TextStyle(
                                        fontSize = 10.sp,
                                        lineHeight = 11.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            } else {
                                TextStyle(
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = AppColors.SecondaryText,
                                    lineHeight = 11.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BodyEditorField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    focused: Boolean,
    onFocusChange: (Boolean) -> Unit,
    focusRequester: FocusRequester? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (focused) SummaryFocusBg else Color.Transparent)
            .padding(horizontal = 6.dp, vertical = 6.dp)
    ) {
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 72.dp)
                .then(
                    if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier
                )
                .onFocusChanged { onFocusChange(it.isFocused) },
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = AppColors.PrimaryText,
                lineHeight = 24.sp
            ),
            decorationBox = { inner ->
                if (text.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontSize = 16.sp,
                        color = AppColors.HintText,
                        lineHeight = 24.sp
                    )
                }
                inner()
            }
        )
    }
}

@Composable
private fun TagsRow(tags: List<String>, onRemove: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        tags.forEach { tag ->
            Row(
                modifier = Modifier
                    .border(1.dp, DividerLight, RoundedCornerShape(14.dp))
                    .padding(start = 8.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (tag.startsWith("@")) tag else "#$tag",
                    fontSize = 13.sp,
                    color = AppColors.PrimaryText
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    "×",
                    fontSize = 14.sp,
                    color = AppColors.SecondaryText,
                    modifier = Modifier.clickable { onRemove(tag) }
                )
            }
        }
    }
}

@Composable
private fun ImageSectionCompact(
    imageUris: List<Uri>,
    onAdd: () -> Unit,
    onRemove: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        imageUris.forEachIndexed { index, uri ->
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(6.dp))
            ) {
                AsyncImage(
                    model = uri,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = { onRemove(index) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(26.dp)
                        .background(Color.Black.copy(alpha = 0.45f), CircleShape)
                ) {
                    Icon(
                        Icons.Default.Close,
                        null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
        if (imageUris.size < 4) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .border(1.dp, DividerLight, RoundedCornerShape(6.dp))
                    .clickable(onClick = onAdd),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.AddPhotoAlternate,
                    contentDescription = "Add",
                    tint = AppColors.HintText,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

private val moodOptions = listOf("😊", "😐", "😢", "😡", "🤗", "😌", "🤔", "😴", "😎", "🥳")

@Composable
private fun EditorBottomBarSymbolButton(
    symbol: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .border(1.dp, DividerLight, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = AppColors.PrimaryText
        )
    }
}

@Composable
private fun EditorBottomToolBar(
    mood: String?,
    onMoodSelected: (String) -> Unit,
    projects: List<Project>,
    selectedProjectIds: List<Long>,
    onProjectToggled: (Long) -> Unit,
    onAppendToBody: (String) -> Unit,
    onConfirm: () -> Unit
) {
    var showMoodExpand by remember { mutableStateOf(false) }
    var showProjectPicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp, top = 2.dp, bottom = 0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            EditorBottomBarSymbolButton(symbol = "#") {
                showMoodExpand = false
                showProjectPicker = false
                onAppendToBody("#")
            }
            EditorBottomBarSymbolButton(symbol = "@") {
                showMoodExpand = false
                showProjectPicker = false
                onAppendToBody("@")
            }
            Text(
                text = if (showMoodExpand) "Mood ▲" else "Mood",
                fontSize = 13.sp,
                color = AppColors.SecondaryText,
                modifier = Modifier
                    .clickable {
                        showProjectPicker = false
                        showMoodExpand = !showMoodExpand
                    }
                    .padding(horizontal = 6.dp, vertical = 6.dp)
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .border(1.dp, DividerLight, RoundedCornerShape(8.dp))
                        .clickable {
                            showMoodExpand = false
                            showProjectPicker = !showProjectPicker
                        }
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("📁", fontSize = 15.sp)
                    Text(
                        text = if (selectedProjectIds.isEmpty()) "Project" else "${selectedProjectIds.size}",
                        fontSize = 13.sp,
                        color = AppColors.SecondaryText
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable(onClick = onConfirm),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Done",
                    tint = AppColors.PrimaryText,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        if (showMoodExpand) {
            Spacer(Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                moodOptions.forEach { m ->
                    val sel = mood == m
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (sel) Color(0xFFFFE76F) else Color.Transparent,
                                RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (sel) Color(0xFFFFA500) else DividerLight,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                onMoodSelected(m)
                                showMoodExpand = false
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(m, fontSize = 16.sp)
                    }
                }
            }
        }

        if (showProjectPicker) {
            Spacer(Modifier.height(4.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 140.dp)
            ) {
                projects.forEach { project ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = project.id in selectedProjectIds,
                            onCheckedChange = { onProjectToggled(project.id) }
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(project.name, fontSize = 13.sp, color = AppColors.PrimaryText)
                    }
                }
            }
        }
    }
}
