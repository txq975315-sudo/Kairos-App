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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppScreenHeader
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.ui.mine.settings.SettingsBackButton
import com.example.kairosapplication.ui.mine.settings.rememberSettingsChrome
import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.glass.GlassQuoteSection
import com.example.kairosapplication.ui.glass.GlassSurface
import com.example.kairosapplication.ui.glass.LocalGlassAtmosphereUi
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import com.example.kairosapplication.ui.glass.glassChromeTextStyle
import com.example.kairosapplication.ui.glass.quoteDividerColor
import com.example.taskmodel.model.Note
import com.example.taskmodel.util.NoteFilterUtils
import com.example.taskmodel.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

private val AccentC = Color(0xFF90CAF9)
private val DangerC = Color(0xFFFF6B6B)
private val SelectAccent = Color(0xFF8A7CF8)

@Composable
fun QuoteSettingScreen(
    taskViewModel: TaskViewModel,
    onBack: () -> Unit,
) {
    val uiState by taskViewModel.uiState.collectAsState()
    val lang = LocalCurrentLanguage.current.value
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val chrome = LocalGlassAtmosphereUi.current.topChrome
    val useLightChrome = !LocalGlassAtmosphereUi.current.zones.topIsLight
    val atmosphere = LocalGlassAtmosphereUi.current
    val cardText = LocalGlassTextColors.current
    val sectionShape = RoundedCornerShape(GlassConstants.CornerRadius)
    val dividerColor = atmosphere.quoteDividerColor()

    var customText by remember { mutableStateOf("") }
    var showOptionsDropdown by remember { mutableStateOf(false) }
    var noteSearchQuery by remember { mutableStateOf("") }

    LaunchedEffect(uiState.dailyQuoteCustomText) {
        if (customText.isEmpty() && uiState.dailyQuoteCustomText.isNotEmpty()) {
            customText = uiState.dailyQuoteCustomText
        }
    }

    val currentQuoteText = taskViewModel.dailyQuoteDisplayText(
        LocalizedStrings.get("widget_quote_default"),
    )
    val sourceType = taskViewModel.dailyQuoteSourceType()
    val sourceLabel = when (sourceType) {
        "custom" -> LocalizedStrings.get("quote_settings_source_custom")
        "essay", "note" -> LocalizedStrings.get("quote_settings_source_note")
        else -> LocalizedStrings.get("quote_settings_source_default")
    }

    val filteredNotes by remember(uiState.notePublished, noteSearchQuery) {
        derivedStateOf { NoteFilterUtils.filterNotesByQuery(uiState.notePublished, noteSearchQuery) }
    }

    fun showSaved() {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = LocalizedStrings.stringFor(lang, "quote_settings_saved", context),
                duration = SnackbarDuration.Short,
            )
        }
    }

    val settingsChrome = rememberSettingsChrome()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(settingsChrome.pageBackground),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacing.PageHorizontal),
        ) {
            Spacer(Modifier.height(AppSpacing.SectionSmall))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SettingsBackButton(onClick = onBack)
                Text(
                    text = LocalizedStrings.get("quote_settings_title"),
                    fontSize = AppScreenHeader.titleSp,
                    fontWeight = AppScreenHeader.titleWeight,
                    color = chrome.primary,
                    style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                )
                Box {
                    IconButton(onClick = { showOptionsDropdown = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = LocalizedStrings.get("quote_settings_options_menu"),
                            tint = chrome.primary,
                        )
                    }
                    DropdownMenu(
                        expanded = showOptionsDropdown,
                        onDismissRequest = { showOptionsDropdown = false },
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    LocalizedStrings.get("quote_settings_options_reset"),
                                    color = DangerC,
                                )
                            },
                            onClick = {
                                showOptionsDropdown = false
                                taskViewModel.resetDailyQuote()
                                customText = ""
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = LocalizedStrings.stringFor(
                                            lang,
                                            "quote_settings_cleared",
                                            context,
                                        ),
                                        duration = SnackbarDuration.Short,
                                    )
                                }
                            },
                        )
                    }
                }
            }

            Spacer(Modifier.height(AppSpacing.SectionMedium))
            GlassQuoteSection(quote = currentQuoteText)
            Spacer(Modifier.height(6.dp))
            Text(
                text = "— $sourceLabel",
                color = chrome.secondary,
                fontSize = 12.sp,
                style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(AppSpacing.SectionMedium))

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(AppSpacing.BlockGap),
            ) {
                item {
                    GlassSurface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = sectionShape,
                        fillAlpha = GlassConstants.TimeBlockFillAlpha,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(
                                    text = LocalizedStrings.get("quote_settings_custom"),
                                    color = cardText.primary,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium,
                                )
                                if (sourceType == "custom") {
                                    Text(
                                        "✓",
                                        color = SelectAccent,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                            }
                            Spacer(Modifier.height(AppSpacing.SectionSmall))
                            BasicTextField(
                                value = customText,
                                onValueChange = { customText = it },
                                modifier = Modifier.fillMaxWidth(),
                                textStyle = MaterialTheme.typography.bodyMedium.copy(
                                    color = cardText.primary,
                                    fontSize = 15.sp,
                                    lineHeight = 22.sp,
                                ),
                                cursorBrush = SolidColor(AccentC),
                                maxLines = 3,
                            )
                            if (customText.isEmpty()) {
                                Text(
                                    text = LocalizedStrings.get("quote_settings_custom_hint"),
                                    color = cardText.muted,
                                    fontSize = 15.sp,
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                            HorizontalDivider(color = dividerColor.copy(alpha = 0.5f), thickness = 0.5.dp)
                            Spacer(Modifier.height(AppSpacing.SectionSmall))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                            ) {
                                Text(
                                    text = LocalizedStrings.get("quote_settings_save"),
                                    color = if (customText.trim().isNotEmpty()) AccentC else cardText.muted,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.clickable(
                                        enabled = customText.trim().isNotEmpty(),
                                    ) {
                                        val trimmed = customText.trim()
                                        if (trimmed.isNotEmpty()) {
                                            taskViewModel.setDailyQuoteCustomText(trimmed)
                                            showSaved()
                                        }
                                    },
                                )
                            }
                        }
                    }
                }

                item {
                    GlassSurface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = sectionShape,
                        fillAlpha = GlassConstants.TimeBlockFillAlpha,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(
                                    text = LocalizedStrings.get("quote_settings_from_note"),
                                    color = cardText.primary,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium,
                                )
                                if (sourceType == "note") {
                                    Text(
                                        "✓",
                                        color = SelectAccent,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                            }

                            if (uiState.notePublished.isEmpty()) {
                                Spacer(Modifier.height(AppSpacing.SectionMedium))
                                Text(
                                    text = LocalizedStrings.get("quote_settings_from_note_empty"),
                                    color = cardText.muted,
                                    fontSize = 13.sp,
                                )
                            } else {
                                Spacer(Modifier.height(AppSpacing.SectionSmall))
                                GlassSurface(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(GlassConstants.CornerRadius),
                                    fillAlpha = GlassConstants.PillFillAlpha,
                                    wrapHazeToContent = true,
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 10.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = null,
                                            tint = cardText.secondary,
                                            modifier = Modifier.size(16.dp),
                                        )
                                        Spacer(Modifier.width(8.dp))
                                        BasicTextField(
                                            value = noteSearchQuery,
                                            onValueChange = { noteSearchQuery = it },
                                            modifier = Modifier.weight(1f),
                                            textStyle = MaterialTheme.typography.bodySmall.copy(
                                                color = cardText.primary,
                                                fontSize = 13.sp,
                                            ),
                                            cursorBrush = SolidColor(AccentC),
                                            singleLine = true,
                                        )
                                        if (noteSearchQuery.isNotEmpty()) {
                                            IconButton(
                                                onClick = { noteSearchQuery = "" },
                                                modifier = Modifier.size(20.dp),
                                            ) {
                                                Icon(
                                                    Icons.Default.Close,
                                                    contentDescription = null,
                                                    tint = cardText.secondary,
                                                    modifier = Modifier.size(14.dp),
                                                )
                                            }
                                        }
                                    }
                                }
                                Spacer(Modifier.height(6.dp))
                                Text(
                                    text = "${filteredNotes.size} / ${uiState.notePublished.size}",
                                    color = cardText.muted,
                                    fontSize = 11.sp,
                                )
                                Spacer(Modifier.height(6.dp))

                                if (filteredNotes.isEmpty()) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 20.dp),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Text("—", color = cardText.muted, fontSize = 14.sp)
                                    }
                                } else {
                                    filteredNotes.forEachIndexed { index, note ->
                                        if (index > 0) {
                                            HorizontalDivider(
                                                color = dividerColor.copy(alpha = 0.35f),
                                                thickness = 0.5.dp,
                                            )
                                        }
                                        FlatNoteItem(
                                            note = note,
                                            isSelected = note.id == uiState.dailyQuoteNoteId,
                                            onClick = {
                                                taskViewModel.setDailyQuoteFromNote(note.id)
                                                showSaved()
                                            },
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                item {
                    GlassSurface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = sectionShape,
                        fillAlpha = GlassConstants.PillFillAlpha * 0.85f,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = LocalizedStrings.get("quote_settings_from_library"),
                                    color = cardText.secondary,
                                    fontSize = 14.sp,
                                )
                                Text(
                                    text = LocalizedStrings.get("quote_settings_from_library_desc"),
                                    color = cardText.muted,
                                    fontSize = 12.sp,
                                )
                            }
                            Text(
                                text = "Coming soon",
                                color = cardText.muted.copy(alpha = 0.7f),
                                fontSize = 11.sp,
                            )
                        }
                    }
                }

                item {
                    Spacer(Modifier.height(AppSpacing.SectionXLarge))
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
        )
    }
}

@Composable
private fun FlatNoteItem(
    note: Note,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val cardText = LocalGlassTextColors.current
    val displayText = NoteFilterUtils.getNotePreviewText(note)
    val dateStr = remember(note.recordedDate) {
        java.time.format.DateTimeFormatter.ofPattern("yyyy/MM/dd").format(note.recordedDate)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isSelected) SelectAccent.copy(alpha = 0.12f) else Color.Transparent,
            )
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = displayText,
                color = if (isSelected) SelectAccent else cardText.primary,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "$dateStr · ${note.primaryCategory}",
                color = cardText.muted,
                fontSize = 11.sp,
            )
        }
        if (isSelected) {
            Icon(
                Icons.Default.Check,
                contentDescription = null,
                tint = SelectAccent,
                modifier = Modifier.size(18.dp),
            )
        }
    }
}
