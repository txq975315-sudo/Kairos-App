package com.example.kairosapplication.ui.trash

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppSpacing
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

private val topicEnglishLabel: Map<String, String> = mapOf(
    NotePrimaryCategory.FREESTYLE to "Freestyle",
    NotePrimaryCategory.SELF_AWARENESS to "Self-Awareness",
    NotePrimaryCategory.INTERPERSONAL to "Interpersonal",
    NotePrimaryCategory.INTIMACY_FAMILY to "Intimacy & Family",
    NotePrimaryCategory.SOMATIC_ENERGY to "Health & Energy",
    NotePrimaryCategory.MEANING to "Meaning"
)

private val topicEmoji: Map<String, String> = mapOf(
    NotePrimaryCategory.FREESTYLE to "🎨",
    NotePrimaryCategory.SELF_AWARENESS to "🧠",
    NotePrimaryCategory.INTERPERSONAL to "👥",
    NotePrimaryCategory.INTIMACY_FAMILY to "❤️",
    NotePrimaryCategory.SOMATIC_ENERGY to "💪",
    NotePrimaryCategory.MEANING to "✨"
)

private fun sortOrderLabel(order: TrashSortOrder): String = when (order) {
    TrashSortOrder.DELETED_NEW -> "Deleted date (newest)"
    TrashSortOrder.DELETED_OLD -> "Deleted date (oldest)"
    TrashSortOrder.ALPHABETICAL -> "Alphabetical"
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
            title = { Text("Empty trash?", color = AppColors.PrimaryText) },
            text = {
                Text(
                    "All notes in Trash will be permanently deleted. This cannot be undone.",
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
                    Text("Empty Trash", color = AppColors.Urgent, fontWeight = FontWeight.Medium)
                }
            },
            dismissButton = {
                TextButton(onClick = { showEmptyTrashDialog = false }) {
                    Text("Cancel", color = AppColors.SecondaryText)
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
                    Text(
                        text = "Sort by: ${sortOrderLabel(sortOrder)}",
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
    onBackClick: () -> Unit,
    showMenu: Boolean,
    onShowMenuChange: (Boolean) -> Unit,
    onSortChange: (TrashSortOrder) -> Unit,
    onEmptyTrashRequest: () -> Unit
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = "Trash",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.PrimaryText
                )
                Text(
                    text = if (trashCount == 1) "1 item" else "$trashCount items",
                    fontSize = 12.sp,
                    color = AppColors.SecondaryText
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AppColors.BackIcon
                )
            }
        },
        actions = {
            Box {
                IconButton(onClick = { onShowMenuChange(true) }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Menu",
                        tint = AppColors.PrimaryText
                    )
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { onShowMenuChange(false) }
                ) {
                    Text(
                        text = "Sort by",
                        fontSize = 12.sp,
                        color = AppColors.SecondaryText,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    DropdownMenuItem(
                        text = { Text("Deleted date (newest)", color = AppColors.PrimaryText) },
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
                        text = { Text("Deleted date (oldest)", color = AppColors.PrimaryText) },
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
                        text = { Text("Alphabetical", color = AppColors.PrimaryText) },
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
                        text = { Text("Empty Trash", color = AppColors.Urgent) },
                        onClick = { onEmptyTrashRequest() }
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = AppColors.SurfaceWhite)
    )
}

@Composable
private fun TrashNoteCard(
    note: Note,
    onRestore: () -> Unit,
    onPermanentDelete: () -> Unit,
    onOpenDetail: () -> Unit
) {
    var showDeleteConfirm by remember { mutableStateOf(false) }

    if (showDeleteConfirm) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirm = false },
            title = { Text("Delete forever?", color = AppColors.PrimaryText) },
            text = {
                Text(
                    "This note will be permanently deleted and cannot be recovered.",
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
                    Text("Delete Forever", color = AppColors.Urgent, fontWeight = FontWeight.Medium)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirm = false }) {
                    Text("Cancel", color = AppColors.SecondaryText)
                }
            }
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = AppColors.CardBackground),
        shape = RoundedCornerShape(12.dp)
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
                        text = topicEnglishLabel[note.primaryCategory] ?: note.primaryCategory,
                        fontSize = 12.sp,
                        color = AppColors.SecondaryText
                    )
                }
                val summaryText = note.behaviorSummary.ifBlank {
                    note.body.trim().lineSequence().firstOrNull().orEmpty()
                        .ifBlank { "(No summary)" }
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
                    text = "Deleted ${formatDeletedTime(note.updatedAt)}",
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
                    Text("Restore", fontSize = 12.sp, color = AppColors.PrimaryText)
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
                    Text("Delete Forever", fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
private fun EmptyTrashState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "🗑️", fontSize = 64.sp)
            Spacer(modifier = Modifier.height(AppSpacing.SectionXLarge))
            Text(
                text = "Trash is empty",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = AppColors.PrimaryText
            )
            Spacer(modifier = Modifier.height(AppSpacing.SectionSmall))
            Text(
                text = "Deleted notes will appear here",
                fontSize = 14.sp,
                color = AppColors.SecondaryText
            )
        }
    }
}

private fun formatDeletedTime(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp
    val days = diff / (24 * 60 * 60 * 1000)
    val hours = diff / (60 * 60 * 1000)
    val minutes = diff / (60 * 1000)

    return when {
        minutes < 1 -> "just now"
        minutes == 1L -> "1 minute ago"
        minutes < 60 -> "$minutes minutes ago"
        hours == 1L -> "1 hour ago"
        hours < 24 -> "$hours hours ago"
        days == 1L -> "1 day ago"
        days < 30 -> "$days days ago"
        else -> {
            val date = Instant.ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            date.format(DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH))
        }
    }
}
