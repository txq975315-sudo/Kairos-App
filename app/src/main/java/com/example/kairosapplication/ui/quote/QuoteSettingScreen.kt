package com.example.kairosapplication.ui.quote

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.core.ui.CommonBackButton
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.taskmodel.model.Note
import com.example.taskmodel.util.NoteFilterUtils
import com.example.taskmodel.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

// ─────────────────────────────────────────────────────────────────────────────
// Design tokens — aligned with project StyleConstants
// ─────────────────────────────────────────────────────────────────────────────
private val ScreenBg = AppColors.ScreenBackground
private val TitleC = AppColors.PrimaryText
private val HintC = AppColors.HintText
private val AccentC = Color(0xFF2196F3)
private val DividerC = Color(0xFFE8E8E8)
private val SelectedBg = Color(0xFFE3F2FD)
private val DangerC = Color(0xFFE53935)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteSettingScreen(
    taskViewModel: TaskViewModel,
    onBack: () -> Unit
) {
    val uiState by taskViewModel.uiState.collectAsState()
    val lang = LocalCurrentLanguage.current.value
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var customText by remember { mutableStateOf("") }
    var showOptionsDropdown by remember { mutableStateOf(false) }
    var noteSearchQuery by remember { mutableStateOf("") }

    LaunchedEffect(uiState.dailyQuoteCustomText) {
        if (customText.isEmpty() && uiState.dailyQuoteCustomText.isNotEmpty()) {
            customText = uiState.dailyQuoteCustomText
        }
    }

    val currentQuoteText = taskViewModel.dailyQuoteDisplayText(
        LocalizedStrings.get("widget_quote_default")
    )
    val sourceType = taskViewModel.dailyQuoteSourceType()
    val sourceLabel = when (sourceType) {
        "custom" -> LocalizedStrings.get("quote_settings_source_custom")
        "essay", "note" -> LocalizedStrings.get("quote_settings_source_note")
        else -> LocalizedStrings.get("quote_settings_source_default")
    }

    fun showSaved() {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = LocalizedStrings.stringFor(lang, "quote_settings_saved", context),
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        containerColor = ScreenBg,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = LocalizedStrings.get("quote_settings_title"),
                        color = TitleC,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    CommonBackButton(onClick = onBack)
                },
                actions = {
                    Box {
                        IconButton(onClick = { showOptionsDropdown = true }) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = null,
                                tint = TitleC
                            )
                        }
                        DropdownMenu(
                            expanded = showOptionsDropdown,
                            onDismissRequest = { showOptionsDropdown = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text(LocalizedStrings.get("quote_settings_options_reset"), color = DangerC) },
                                onClick = {
                                    showOptionsDropdown = false
                                    taskViewModel.resetDailyQuote()
                                    customText = ""
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = LocalizedStrings.stringFor(lang, "quote_settings_cleared", context),
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = ScreenBg)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = AppSpacing.PageHorizontal)
        ) {
            // ── 1. Current quote preview — no card, just text ──
            Spacer(Modifier.height(AppSpacing.SectionMedium))
            Text(
                text = currentQuoteText,
                color = TitleC,
                fontSize = 18.sp,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                lineHeight = 28.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "— $sourceLabel",
                color = HintC,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = AppSpacing.SectionXLarge),
                color = DividerC,
                thickness = 0.5.dp
            )

            // ── 2. Custom text input — underline style ──
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = LocalizedStrings.get("quote_settings_custom"),
                    color = TitleC,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
                if (sourceType == "custom") {
                    Text("✓", color = AccentC, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.height(AppSpacing.SectionSmall))
            BasicTextField(
                value = customText,
                onValueChange = { customText = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = TitleC,
                    fontSize = 15.sp,
                    lineHeight = 22.sp
                ),
                cursorBrush = SolidColor(AccentC),
                maxLines = 3
            )
            if (customText.isEmpty()) {
                Text(
                    text = LocalizedStrings.get("quote_settings_custom_hint"),
                    color = HintC.copy(alpha = 0.5f),
                    fontSize = 15.sp
                )
            }
            Spacer(Modifier.height(2.dp))
            HorizontalDivider(color = DividerC, thickness = 0.5.dp)
            Spacer(Modifier.height(AppSpacing.SectionSmall))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = {
                        val trimmed = customText.trim()
                        if (trimmed.isNotEmpty()) {
                            taskViewModel.setDailyQuoteCustomText(trimmed)
                            showSaved()
                        }
                    },
                    enabled = customText.trim().isNotEmpty()
                ) {
                    Text(
                        text = LocalizedStrings.get("quote_settings_save"),
                        color = if (customText.trim().isNotEmpty()) AccentC else HintC,
                        fontSize = 14.sp
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = AppSpacing.SectionXLarge),
                color = DividerC,
                thickness = 0.5.dp
            )

            // ── 3. From notes — flat list with inline search ──
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = LocalizedStrings.get("quote_settings_from_note"),
                    color = TitleC,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
                if (sourceType == "note") {
                    Text("✓", color = AccentC, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }

            if (uiState.notePublished.isEmpty()) {
                Spacer(Modifier.height(AppSpacing.SectionMedium))
                Text(
                    text = LocalizedStrings.get("quote_settings_from_note_empty"),
                    color = HintC,
                    fontSize = 13.sp
                )
            } else {
                Spacer(Modifier.height(AppSpacing.SectionSmall))

                // Inline search
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF5F5F5))
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = HintC,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    BasicTextField(
                        value = noteSearchQuery,
                        onValueChange = { noteSearchQuery = it },
                        modifier = Modifier.weight(1f),
                        textStyle = MaterialTheme.typography.bodySmall.copy(
                            color = TitleC,
                            fontSize = 13.sp
                        ),
                        cursorBrush = SolidColor(AccentC),
                        singleLine = true
                    )
                    if (noteSearchQuery.isNotEmpty()) {
                        IconButton(onClick = { noteSearchQuery = "" }, modifier = Modifier.size(20.dp)) {
                            Icon(Icons.Default.Close, contentDescription = null, tint = HintC, modifier = Modifier.size(14.dp))
                        }
                    }
                }

                Spacer(Modifier.height(4.dp))

                val filteredNotes by remember(uiState.notePublished, noteSearchQuery) {
                    derivedStateOf { NoteFilterUtils.filterNotesByQuery(uiState.notePublished, noteSearchQuery) }
                }

                Text(
                    text = "${filteredNotes.size} / ${uiState.notePublished.size}",
                    color = HintC,
                    fontSize = 11.sp
                )
                Spacer(Modifier.height(4.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = AppSpacing.SectionMedium),
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    if (filteredNotes.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("—", color = HintC, fontSize = 14.sp)
                            }
                        }
                    } else {
                        items(filteredNotes, key = { it.id }) { note ->
                            FlatNoteItem(
                                note = note,
                                isSelected = note.id == uiState.dailyQuoteNoteId,
                                onClick = {
                                    taskViewModel.setDailyQuoteFromNote(note.id)
                                    showSaved()
                                }
                            )
                            HorizontalDivider(color = DividerC, thickness = 0.5.dp)
                        }
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = AppSpacing.SectionMedium),
                color = DividerC,
                thickness = 0.5.dp
            )

            // ── 4. Quote library — placeholder entry ──
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFFAFAFA))
                    .padding(horizontal = 12.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = LocalizedStrings.get("quote_settings_from_library"),
                        color = HintC,
                        fontSize = 14.sp
                    )
                    Text(
                        text = LocalizedStrings.get("quote_settings_from_library_desc"),
                        color = HintC.copy(alpha = 0.6f),
                        fontSize = 12.sp
                    )
                }
                Text(
                    text = "Coming soon",
                    color = HintC.copy(alpha = 0.4f),
                    fontSize = 11.sp
                )
            }

            Spacer(Modifier.height(AppSpacing.SectionXLarge))
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Flat note item — no card, just a row with divider
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun FlatNoteItem(
    note: Note,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val displayText = NoteFilterUtils.getNotePreviewText(note)
    val dateStr = remember(note.recordedDate) {
        java.time.format.DateTimeFormatter.ofPattern("yyyy/MM/dd").format(note.recordedDate)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelected) SelectedBg else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = displayText,
                color = if (isSelected) AccentC else TitleC,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "$dateStr · ${note.primaryCategory}",
                color = HintC,
                fontSize = 11.sp
            )
        }
        if (isSelected) {
            Icon(
                Icons.Default.Check,
                contentDescription = null,
                tint = AccentC,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
