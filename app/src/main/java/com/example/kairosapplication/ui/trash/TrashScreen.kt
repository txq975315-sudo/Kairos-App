package com.example.kairosapplication.ui.trash

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.CommonBackButton
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.i18n.UserVisibleStrings
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.topic.TopicDisplayStrings
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.model.Note
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private enum class TrashSortOrder {
    DELETED_NEW,
    DELETED_OLD,
    ALPHABETICAL
}

private val topicEmoji: Map<String, String> = mapOf(
    NotePrimaryCategory.FREESTYLE to "🎨",
    NotePrimaryCategory.SELF_AWARENESS to "🧠",
    NotePrimaryCategory.INTERPERSONAL to "👥",
    NotePrimaryCategory.INTIMACY_FAMILY to "❤️",
    NotePrimaryCategory.SOMATIC_ENERGY to "💪",
    NotePrimaryCategory.MEANING to "✨"
)

private fun trashSortOrderKey(order: TrashSortOrder): String = when (order) {
    TrashSortOrder.DELETED_NEW -> "trash_sort_deleted_new"
    TrashSortOrder.DELETED_OLD -> "trash_sort_deleted_old"
    TrashSortOrder.ALPHABETICAL -> "trash_sort_alphabetical"
}

private fun sortKeyForAlphabetical(note: Note): String =
    note.behaviorSummary.ifBlank { note.body }.trim().lowercase(Locale.US)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrashScreen(
    taskViewModel: TaskViewModel,
    onBackClick: () -> Unit,
    onNoteClick: (Long) -> Unit
) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val trashNotes by taskViewModel.noteTrash.collectAsState()
    var sortOrder by remember { mutableStateOf(TrashSortOrder.DELETED_NEW) }
    var showOverflowMenu by remember { mutableStateOf(false) }
    var showEmptyTrashDialog by remember { mutableStateOf(false) }

    val sortedNotes = remember(trashNotes, sortOrder) {
        when (sortOrder) {
            TrashSortOrder.DELETED_NEW -> trashNotes.sortedByDescending { it.updatedAt }
            TrashSortOrder.DELETED_OLD -> trashNotes.sortedBy { it.updatedAt }
            TrashSortOrder.ALPHABETICAL -> trashNotes.sortedBy { sortKeyForAlphabetical(it) }
        }
    }

    if (showEmptyTrashDialog) {
        AlertDialog(
            onDismissRequest = { showEmptyTrashDialog = false },
            title = {
                Text(
                    LocalizedStrings.stringFor(lang, "trash_empty_dialog_title", context),
                    color = AppColors.PrimaryText
                )
            },
            text = {
                Text(
                    LocalizedStrings.stringFor(lang, "trash_empty_dialog_body", context),
                    color = AppColors.SecondaryText
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        taskViewModel.emptyTrash()
                        showEmptyTrashDialog = false
                    }
                ) {
                    Text(
                        LocalizedStrings.stringFor(lang, "trash_empty_dialog_confirm", context),
                        color = AppColors.Urgent,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showEmptyTrashDialog = false }) {
                    Text(LocalizedStrings.stringFor(lang, "cancel", context), color = AppColors.SecondaryText)
                }
            }
        )
    }

    Scaffold(
        containerColor = AppColors.ScreenBackground,
        topBar = {
            Column {
                TrashTopBar(
                    trashCount = trashNotes.size,
                    sortOrder = sortOrder,
                    lang = lang,
                    context = context,
                    onBackClick = onBackClick,
                    showMenu = showOverflowMenu,
                    onShowMenuChange = { showOverflowMenu = it },
                    onSortChange = { sortOrder = it },
                    onEmptyTrashRequest = {
                        showOverflowMenu = false
                        showEmptyTrashDialog = true
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = AppSpacing.PageHorizontal, vertical = AppSpacing.SectionSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val sortLabel = LocalizedStrings.stringFor(lang, trashSortOrderKey(sortOrder), context)
                    val sortLine = LocalizedStrings.stringFor(lang, "trash_sort_line", context, sortLabel)
                    Text(
                        text = sortLine,
                        fontSize = 13.sp,
                        color = AppColors.SecondaryText
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (sortedNotes.isEmpty()) {
                EmptyTrashState(modifier = Modifier.fillMaxSize())
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = AppSpacing.PageHorizontal,
                        vertical = AppSpacing.BlockGap
                    ),
                    verticalArrangement = Arrangement.spacedBy(AppSpacing.BlockGap)
                ) {
                    items(sortedNotes, key = { it.id }) { note ->
                        TrashNoteCard(
                            note = note,
                            onRestore = { taskViewModel.restoreNote(note.id) },
                            onPermanentDelete = { taskViewModel.permanentlyDeleteNote(note.id) },
                            onOpenDetail = { onNoteClick(note.id) }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TrashTopBar(
    trashCount: Int,
    sortOrder: TrashSortOrder,
    lang: LocalizationManager.Language,
    context: Context,
    onBackClick: () -> Unit,
    showMenu: Boolean,
    onShowMenuChange: (Boolean) -> Unit,
    onSortChange: (TrashSortOrder) -> Unit,
    onEmptyTrashRequest: () -> Unit
) {
    val trashTitle = LocalizedStrings.stringFor(lang, "trash_title", context)
    val countSubtitle = if (trashCount == 1) {
        LocalizedStrings.stringFor(lang, "trash_header_item_one", context)
    } else {
        LocalizedStrings.stringFor(lang, "trash_header_item_many", context, trashCount)
    }
    val cdBack = LocalizedStrings.stringFor(lang, "back", context)
    val cdMenu = LocalizedStrings.stringFor(lang, "cd_menu", context)
    TopAppBar(
        title = {
            Column {
                Text(
                    text = trashTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.PrimaryText
                )
                Text(
                    text = countSubtitle,
                    fontSize = 12.sp,
                    color = AppColors.SecondaryText
                )
            }
        },
        navigationIcon = {
            CommonBackButton(onClick = onBackClick, contentDescription = cdBack)
        },
        actions = {
            Box {
                IconButton(onClick = { onShowMenuChange(true) }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = cdMenu,
                        tint = AppColors.PrimaryText
                    )
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { onShowMenuChange(false) }
                ) {
                    Text(
                        text = LocalizedStrings.stringFor(lang, "trash_sort_menu_title", context),
                        fontSize = 12.sp,
                        color = AppColors.SecondaryText,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                LocalizedStrings.stringFor(lang, "trash_sort_deleted_new", context),
                                color = AppColors.PrimaryText
                            )
                        },
                        onClick = {
                            onSortChange(TrashSortOrder.DELETED_NEW)
                            onShowMenuChange(false)
                        },
                        trailingIcon = {
                            if (sortOrder == TrashSortOrder.DELETED_NEW) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = AppColors.PrimaryText)
                            }
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                LocalizedStrings.stringFor(lang, "trash_sort_deleted_old", context),
                                color = AppColors.PrimaryText
                            )
                        },
                        onClick = {
                            onSortChange(TrashSortOrder.DELETED_OLD)
                            onShowMenuChange(false)
                        },
                        trailingIcon = {
                            if (sortOrder == TrashSortOrder.DELETED_OLD) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = AppColors.PrimaryText)
                            }
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                LocalizedStrings.stringFor(lang, "trash_sort_alphabetical", context),
                                color = AppColors.PrimaryText
                            )
                        },
                        onClick = {
                            onSortChange(TrashSortOrder.ALPHABETICAL)
                            onShowMenuChange(false)
                        },
                        trailingIcon = {
                            if (sortOrder == TrashSortOrder.ALPHABETICAL) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = AppColors.PrimaryText)
                            }
                        }
                    )
                    HorizontalDivider(color = AppColors.Divider)
                    DropdownMenuItem(
                        text = {
                            Text(
                                LocalizedStrings.stringFor(lang, "trash_menu_empty", context),
                                color = AppColors.Urgent
                            )
                        },
                        onClick = { onEmptyTrashRequest() }
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundColor)
    )
}

@Composable
private fun TrashNoteCard(
    note: Note,
    onRestore: () -> Unit,
    onPermanentDelete: () -> Unit,
    onOpenDetail: () -> Unit
) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    var showDeleteConfirm by remember { mutableStateOf(false) }

    if (showDeleteConfirm) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirm = false },
            title = {
                Text(
                    LocalizedStrings.stringFor(lang, "trash_delete_forever_dialog_title", context),
                    color = AppColors.PrimaryText
                )
            },
            text = {
                Text(
                    LocalizedStrings.stringFor(lang, "trash_delete_forever_dialog_body", context),
                    color = AppColors.SecondaryText
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onPermanentDelete()
                        showDeleteConfirm = false
                    }
                ) {
                    Text(
                        LocalizedStrings.stringFor(lang, "trash_delete_forever_confirm", context),
                        color = AppColors.Urgent,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirm = false }) {
                    Text(LocalizedStrings.stringFor(lang, "cancel", context), color = AppColors.SecondaryText)
                }
            }
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = AppColors.CardBackground),
        shape = RoundedCornerShape(AppShapes.InsetContentRadius)
    ) {
        Column(
            modifier = Modifier.padding(AppSpacing.CardHorizontal, AppSpacing.CardVertical),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.SectionSmall)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onOpenDetail)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = topicEmoji[note.primaryCategory] ?: "📝",
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(AppSpacing.CompactGap))
                    Text(
                        text = TopicDisplayStrings.primaryLabel(note.primaryCategory, lang, context),
                        fontSize = 12.sp,
                        color = AppColors.SecondaryText
                    )
                }
                val noSummary = LocalizedStrings.stringFor(lang, "trash_note_no_summary", context)
                val summaryText = note.behaviorSummary.ifBlank {
                    note.body.trim().lineSequence().firstOrNull().orEmpty()
                        .ifBlank { noSummary }
                }
                Text(
                    text = summaryText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.PrimaryText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = LocalizedStrings.stringFor(
                        lang,
                        "trash_deleted_at",
                        context,
                        formatDeletedTime(note.updatedAt, lang, context),
                    ),
                    fontSize = 11.sp,
                    color = AppColors.SecondaryText.copy(alpha = 0.7f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppSpacing.SectionSmall)
            ) {
                OutlinedButton(
                    onClick = onRestore,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Restore,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = AppColors.PrimaryText
                    )
                    Spacer(modifier = Modifier.width(AppSpacing.CompactGap))
                    Text(
                        LocalizedStrings.stringFor(lang, "trash_restore", context),
                        fontSize = 12.sp,
                        color = AppColors.PrimaryText
                    )
                }
                OutlinedButton(
                    onClick = { showDeleteConfirm = true },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = AppColors.Urgent)
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteForever,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = AppColors.Urgent
                    )
                    Spacer(modifier = Modifier.width(AppSpacing.CompactGap))
                    Text(
                        LocalizedStrings.stringFor(lang, "trash_delete_forever_confirm", context),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyTrashState(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "🗑️", fontSize = 64.sp)
            Spacer(modifier = Modifier.height(AppSpacing.SectionXLarge))
            Text(
                text = LocalizedStrings.stringFor(lang, "trash_empty_state_title", context),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = AppColors.PrimaryText
            )
            Spacer(modifier = Modifier.height(AppSpacing.SectionSmall))
            Text(
                text = LocalizedStrings.stringFor(lang, "trash_empty_state_subtitle", context),
                fontSize = 14.sp,
                color = AppColors.SecondaryText
            )
        }
    }
}

private fun formatDeletedTime(
    timestamp: Long,
    lang: LocalizationManager.Language,
    context: Context,
): String {
    val now = System.currentTimeMillis()
    val days = ((now - timestamp) / (24 * 60 * 60 * 1000)).toInt()
    if (days < 30) {
        return UserVisibleStrings.relativeTimeAgo(timestamp, lang, context)
    }
    val date = Instant.ofEpochMilli(timestamp)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    val locale = if (lang == LocalizationManager.Language.ZH) Locale.CHINA else Locale.ENGLISH
    val pattern = if (lang == LocalizationManager.Language.ZH) "yyyy年M月d日" else "MMM d, yyyy"
    return date.format(DateTimeFormatter.ofPattern(pattern, locale))
}
