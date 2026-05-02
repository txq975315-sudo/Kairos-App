package com.example.kairosapplication.ui.inbox

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
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
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.constants.NoteStatus
import com.example.taskmodel.model.Note
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

private enum class InboxSortOrder {
    BY_DEADLINE,
    BY_DATE
}

private val topicEmojis = mapOf(
    NotePrimaryCategory.FREESTYLE to "✨",
    NotePrimaryCategory.SELF_AWARENESS to "🧠",
    NotePrimaryCategory.INTERPERSONAL to "👥",
    NotePrimaryCategory.INTIMACY_FAMILY to "❤️",
    NotePrimaryCategory.SOMATIC_ENERGY to "💪",
    NotePrimaryCategory.MEANING to "✨"
)

private val topicLabels = mapOf(
    NotePrimaryCategory.FREESTYLE to "Freestyle",
    NotePrimaryCategory.SELF_AWARENESS to "Self-Awareness",
    NotePrimaryCategory.INTERPERSONAL to "Interpersonal",
    NotePrimaryCategory.INTIMACY_FAMILY to "Intimacy & Family",
    NotePrimaryCategory.SOMATIC_ENERGY to "Health & Energy",
    NotePrimaryCategory.MEANING to "Meaning"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InboxListScreen(
    taskViewModel: TaskViewModel,
    onBackClick: () -> Unit,
    onNoteClick: (Long) -> Unit,
    onQuickClassify: (Long, String) -> Unit
) {
    val uiState by taskViewModel.uiState.collectAsState()
    var sortOrder by remember { mutableStateOf(InboxSortOrder.BY_DEADLINE) }
    var showMenu by remember { mutableStateOf(false) }

    val inboxNotes = remember(uiState.noteInbox, sortOrder) {
        val filtered = uiState.noteInbox.filter { it.status == NoteStatus.INBOX }
        when (sortOrder) {
            InboxSortOrder.BY_DEADLINE -> filtered.sortedWith(
                compareBy<Note> { it.deadline ?: LocalDate.MAX }
                    .thenByDescending { it.recordedDate }
            )
            InboxSortOrder.BY_DATE -> filtered.sortedByDescending { it.recordedDate }
        }
    }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            InboxTopBar(
                inboxCount = inboxNotes.size,
                onBackClick = onBackClick,
                sortOrder = sortOrder,
                onSortOrderChange = { sortOrder = it },
                showMenu = showMenu,
                onShowMenuChange = { showMenu = it },
                onClearAll = {
                    taskViewModel.clearAllInbox()
                }
            )
        }
    ) { padding ->
        if (inboxNotes.isEmpty()) {
            EmptyInboxState(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Text(
                    text = when (sortOrder) {
                        InboxSortOrder.BY_DEADLINE -> "Sorted by deadline (soonest first)"
                        InboxSortOrder.BY_DATE -> "Sorted by date (newest first)"
                    },
                    fontSize = 12.sp,
                    color = SecondaryTextColor,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = inboxNotes,
                        key = { it.id }
                    ) { note ->
                        val dismissState = rememberSwipeToDismissBoxState(
                            confirmValueChange = { value ->
                                if (value == SwipeToDismissBoxValue.EndToStart) {
                                    taskViewModel.softDeleteRoomNote(note.id)
                                    true
                                } else {
                                    false
                                }
                            }
                        )
                        SwipeToDismissBox(
                            state = dismissState,
                            enableDismissFromStartToEnd = false,
                            enableDismissFromEndToStart = true,
                            backgroundContent = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Red.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Text(
                                        text = "Delete",
                                        color = Color.Red,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            },
                            content = {
                                InboxNoteCard(
                                    note = note,
                                    onClick = { onNoteClick(note.id) },
                                    onQuickClassify = { category ->
                                        onQuickClassify(note.id, category)
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InboxTopBar(
    inboxCount: Int,
    onBackClick: () -> Unit,
    sortOrder: InboxSortOrder,
    onSortOrderChange: (InboxSortOrder) -> Unit,
    showMenu: Boolean,
    onShowMenuChange: (Boolean) -> Unit,
    onClearAll: () -> Unit
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = "Inspiration",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = PrimaryTextColor
                )
                Text(
                    text = "$inboxCount unorganized",
                    fontSize = 12.sp,
                    color = SecondaryTextColor
                )
            }
        },
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
            Box {
                IconButton(onClick = { onShowMenuChange(true) }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Menu",
                        tint = PrimaryTextColor
                    )
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { onShowMenuChange(false) }
                ) {
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("Sort by deadline")
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = if (sortOrder == InboxSortOrder.BY_DEADLINE) "✓" else "",
                                    fontSize = 12.sp
                                )
                            }
                        },
                        onClick = {
                            onSortOrderChange(InboxSortOrder.BY_DEADLINE)
                            onShowMenuChange(false)
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("Sort by date")
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = if (sortOrder == InboxSortOrder.BY_DATE) "✓" else "",
                                    fontSize = 12.sp
                                )
                            }
                        },
                        onClick = {
                            onSortOrderChange(InboxSortOrder.BY_DATE)
                            onShowMenuChange(false)
                        }
                    )
                    HorizontalDivider()
                    DropdownMenuItem(
                        text = {
                            Text("Clear all → Freestyle", color = Color.Red)
                        },
                        onClick = {
                            onClearAll()
                            onShowMenuChange(false)
                        }
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}

@Composable
private fun InboxNoteCard(
    note: Note,
    onClick: () -> Unit,
    onQuickClassify: (String) -> Unit
) {
    var showCategoryPicker by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onClick)
            ) {
            val deadline = note.deadline
            val today = LocalDate.now()
            val daysLeft = deadline?.let { ChronoUnit.DAYS.between(today, it).toInt() }

            val (deadlineText, deadlineColor) = when {
                daysLeft == null -> "No deadline" to SecondaryTextColor
                daysLeft < 0 -> "⚠️ Overdue! Converted ${-daysLeft} day(s) ago" to Color.Red
                daysLeft == 0 -> "⚠️ Due today!" to Color.Red
                daysLeft == 1 -> "⏰ Due tomorrow" to Color(0xFFFF9800)
                else -> "⏰ Auto-converts in $daysLeft days" to SecondaryTextColor
            }

            Text(
                text = deadlineText,
                fontSize = 12.sp,
                color = deadlineColor,
                fontWeight = if (daysLeft != null && daysLeft <= 1) FontWeight.Bold else FontWeight.Normal
            )

            if (note.primaryCategory != NotePrimaryCategory.FREESTYLE && !note.needsManualClassification) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = topicEmojis[note.primaryCategory] ?: "📝",
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = topicLabels[note.primaryCategory] ?: note.primaryCategory,
                        fontSize = 12.sp,
                        color = SecondaryTextColor
                    )
                }
            }

            Text(
                text = note.behaviorSummary.ifBlank { "—" },
                fontSize = 14.sp,
                fontWeight = if (note.needsManualClassification) FontWeight.Bold else FontWeight.Medium,
                color = PrimaryTextColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            if (note.body.isNotBlank()) {
                Text(
                    text = note.body,
                    fontSize = 12.sp,
                    color = SecondaryTextColor,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                text = formatInboxDate(note.recordedDate),
                fontSize = 11.sp,
                color = SecondaryTextColor.copy(alpha = 0.7f)
            )
            }

            if (note.needsManualClassification || note.primaryCategory == NotePrimaryCategory.FREESTYLE) {
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedButton(
                    onClick = { showCategoryPicker = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Quick Classify", fontSize = 12.sp)
                }
            }
        }
    }

    if (showCategoryPicker) {
        CategoryPickerDialog(
            onDismiss = { showCategoryPicker = false },
            onCategorySelected = { category ->
                showCategoryPicker = false
                onQuickClassify(category)
            }
        )
    }
}

@Composable
private fun CategoryPickerDialog(
    onDismiss: () -> Unit,
    onCategorySelected: (String) -> Unit
) {
    val categories = listOf(
        NotePrimaryCategory.SELF_AWARENESS to "Self-Awareness",
        NotePrimaryCategory.INTERPERSONAL to "Interpersonal",
        NotePrimaryCategory.INTIMACY_FAMILY to "Intimacy & Family",
        NotePrimaryCategory.SOMATIC_ENERGY to "Health & Energy",
        NotePrimaryCategory.MEANING to "Meaning",
        NotePrimaryCategory.FREESTYLE to "Freestyle"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select category", color = PrimaryTextColor) },
        text = {
            Column {
                categories.forEach { (key, label) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onCategorySelected(key)
                            }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = topicEmojis[key] ?: "📝", fontSize = 20.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = label, fontSize = 16.sp, color = PrimaryTextColor)
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = SecondaryTextColor)
            }
        }
    )
}

@Composable
private fun EmptyInboxState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "🎉", fontSize = 64.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "All clear!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = PrimaryTextColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "No inspiration waiting to be organized",
                fontSize = 14.sp,
                color = SecondaryTextColor
            )
        }
    }
}

private fun formatInboxDate(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)
    return date.format(formatter)
}
