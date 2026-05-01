package com.example.kairosapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppInteraction
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.ui.components.EssayItemCard
import com.example.kairosapplication.ui.components.EssayTopicCapsule
import com.example.taskmodel.model.EssayTopic
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlinx.coroutines.launch

private fun essayTopicEnglish(topic: EssayTopic): String = when (topic) {
    EssayTopic.SELF_AWARENESS -> "Self-awareness"
    EssayTopic.INTERPERSONAL -> "Interpersonal"
    EssayTopic.INTIMACY_FAMILY -> "Intimacy & Family"
    EssayTopic.HEALTH_ENERGY -> "Health & Energy"
    EssayTopic.MEANING_EXPLORATION -> "Meaning"
}

@Composable
private fun EssayCircleIconButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shadowColor = Color.Black.copy(alpha = AppInteraction.ShadowAlpha)
    Box(
        modifier = modifier
            .size(40.dp)
            .shadow(
                elevation = 4.dp,
                shape = CircleShape,
                ambientColor = shadowColor,
                spotColor = shadowColor
            )
            .clip(CircleShape)
            .background(Color.White)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = Color(0xFF1A1A1A),
            modifier = Modifier.size(22.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EssayScreen(
    taskViewModel: TaskViewModel,
    onOpenNew: () -> Unit,
    onOpenEdit: (Long) -> Unit
) {
    val uiState by taskViewModel.uiState.collectAsState()
    val essays = remember(uiState.essays) {
        uiState.essays.sortedByDescending { it.createdAtMillis }
    }
    var topicFilter by remember { mutableStateOf<EssayTopic?>(null) }
    var searchOpen by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var menuOpen by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val filterScroll = rememberScrollState()

    val filtered = remember(essays, topicFilter, searchQuery) {
        essays
            .filter { e -> topicFilter == null || e.topic == topicFilter }
            .filter { e ->
                if (searchQuery.isBlank()) true
                else {
                    val topicEn = essayTopicEnglish(e.topic)
                    e.body.contains(searchQuery, ignoreCase = true) ||
                        topicEn.contains(searchQuery, ignoreCase = true)
                }
            }
    }

    val zone = ZoneId.systemDefault()
    val headerDate = remember {
        DateTimeFormatter.ofPattern("MMMM d", Locale.ENGLISH)
            .format(Instant.now().atZone(zone))
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF9F9F9),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onOpenNew,
                containerColor = Color(0xFF8A7CF8),
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_feather),
                    contentDescription = "New entry",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = AppSpacing.PageHorizontal)
        ) {
            Spacer(modifier = Modifier.height(AppSpacing.SectionSmall))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = headerDate,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1A1A1A)
                    )
                    Text(
                        text = " ▼",
                        fontSize = 14.sp,
                        color = Color(0xFF757575)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    EssayCircleIconButton(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        onClick = { searchOpen = !searchOpen }
                    )
                    Box {
                        EssayCircleIconButton(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options",
                            onClick = { menuOpen = true }
                        )
                        DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
                            DropdownMenuItem(
                                text = { Text("All topics") },
                                onClick = {
                                    topicFilter = null
                                    menuOpen = false
                                }
                            )
                            EssayTopic.entries.forEach { t ->
                                DropdownMenuItem(
                                    text = { Text(essayTopicEnglish(t)) },
                                    onClick = {
                                        topicFilter = t
                                        menuOpen = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
            if (searchOpen) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = AppSpacing.SectionMedium),
                    placeholder = { Text("Search body or topic") },
                    singleLine = true
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(filterScroll)
                    .padding(top = AppSpacing.SectionLarge),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                EssayTopic.entries.forEach { topic ->
                    EssayTopicCapsule(
                        topic = topic,
                        selected = topicFilter == topic,
                        lockedSelected = false,
                        enabled = true,
                        labelOverride = essayTopicEnglish(topic),
                        onClick = {
                            topicFilter = if (topicFilter == topic) null else topic
                        }
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = AppSpacing.BlockGap)
            ) {
                if (filtered.isEmpty()) {
                    item {
                        Text(
                            text = "No entries yet. Tap the button below to write.",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            color = Color(0xFF757575),
                            fontSize = 15.sp
                        )
                    }
                } else {
                    items(filtered, key = { it.id }) { essay ->
                        val day = Instant.ofEpochMilli(essay.createdAtMillis).atZone(zone).dayOfMonth
                        val topicEn = essayTopicEnglish(essay.topic)
                        val titleLine = topicEn + if (essay.isDraft) " · Draft" else ""
                        val dismissState = rememberSwipeToDismissBoxState(
                            confirmValueChange = { value ->
                                if (value == SwipeToDismissBoxValue.EndToStart) {
                                    taskViewModel.deleteEssay(essay)
                                    scope.launch {
                                        val result = snackbarHostState.showSnackbar(
                                            message = "Deleted",
                                            actionLabel = "Undo",
                                            duration = SnackbarDuration.Short
                                        )
                                        if (result == SnackbarResult.ActionPerformed) {
                                            taskViewModel.undoDeleteEssay()
                                        } else {
                                            taskViewModel.clearEssayDeleteUndo()
                                        }
                                    }
                                    true
                                } else {
                                    false
                                }
                            }
                        )
                        SwipeToDismissBox(
                            state = dismissState,
                            enableDismissFromStartToEnd = false,
                            backgroundContent = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color(0xFFE53935))
                                        .padding(horizontal = 24.dp),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Text("Delete", color = Color.White, fontWeight = FontWeight.Medium)
                                }
                            },
                            content = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                        .clickable { onOpenEdit(essay.id) }
                                ) {
                                    EssayItemCard(
                                        essay = essay,
                                        timelineDay = day,
                                        modifier = Modifier.fillMaxWidth(),
                                        topicLineOverride = titleLine,
                                        footerTagOverride = essay.tags.firstOrNull() ?: topicEn,
                                        emptyBodyPlaceholder = "No content yet"
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
