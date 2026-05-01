package com.example.kairosapplication.ui

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppScreenHeader
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.ui.common.CommonBackButton
import com.example.kairosapplication.ui.components.EssayTopicCapsule
import com.example.taskmodel.constants.EssayConstants
import com.example.taskmodel.model.Essay
import com.example.taskmodel.model.EssayTopic
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private fun essayTopicEnglish(topic: EssayTopic): String = when (topic) {
    EssayTopic.SELF_AWARENESS -> "Self-awareness"
    EssayTopic.INTERPERSONAL -> "Interpersonal"
    EssayTopic.INTIMACY_FAMILY -> "Intimacy & family"
    EssayTopic.HEALTH_ENERGY -> "Health & energy"
    EssayTopic.MEANING_EXPLORATION -> "Meaning"
}

/** English prompt for the summary strip (same intent as EssayConstants.guideFor). */
private fun englishGuideFor(topic: EssayTopic): String = when (topic) {
    EssayTopic.SELF_AWARENESS ->
        "Behavior summary: Did this help me see myself more clearly?"
    EssayTopic.INTERPERSONAL ->
        "Behavior summary: What did I feel, and how did I respond in this interaction?"
    EssayTopic.INTIMACY_FAMILY ->
        "Behavior summary: What matters to me in this relationship?"
    EssayTopic.HEALTH_ENERGY ->
        "Behavior summary: What signals did my body and mood give me?"
    EssayTopic.MEANING_EXPLORATION ->
        "Behavior summary: What does this mean to me?"
}

@Composable
fun EssayEditScreen(
    essayId: Long?,
    taskViewModel: TaskViewModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val essays = taskViewModel.uiState.value.essays
    val existing = essayId?.let { id -> essays.find { it.id == id } }
    val isEditing = existing != null

    var selectedTopic by remember(existing) { mutableStateOf(existing?.topic) }
    var body by remember(existing) { mutableStateOf(existing?.body.orEmpty()) }
    var imageUris by remember(existing) {
        mutableStateOf(existing?.imageUris.orEmpty().toList())
    }
    var weather by remember(existing) { mutableStateOf(existing?.weather.orEmpty()) }
    var mood by remember(existing) { mutableStateOf(existing?.mood.orEmpty()) }
    var locationLabel by remember(existing) { mutableStateOf(existing?.locationLabel.orEmpty()) }
    var envExpanded by remember { mutableStateOf(false) }
    var menuOpen by remember { mutableStateOf(false) }

    val bodyFocusRequester = remember { FocusRequester() }

    val pickImages = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris ->
            if (uris.isEmpty()) return@rememberLauncherForActivityResult
            val merged = (imageUris + uris.map { it.toString() }).distinct()
                .take(EssayConstants.MAX_IMAGES)
            imageUris = merged
        }
    )

    val englishGuide = selectedTopic?.let { englishGuideFor(it) }.orEmpty()
    val behaviorSummaryHint = englishGuide.ifEmpty {
        "Behavior summary: ... (choose a topic above to see the prompt)"
    }
    val headerInstant = remember(essayId, existing?.createdAtMillis) {
        when {
            existing != null -> Instant.ofEpochMilli(existing.createdAtMillis)
            else -> Instant.now()
        }
    }
    val headerZone = remember(headerInstant) { headerInstant.atZone(ZoneId.systemDefault()) }
    val (headerDatePart, headerTimePart) = remember(headerZone) {
        val month = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH).format(headerZone)
        val day = headerZone.dayOfMonth
        val timeWeekday = DateTimeFormatter.ofPattern("HH:mm EEEE", Locale.ENGLISH).format(headerZone)
        Pair("$month $day", " / $timeWeekday")
    }

    fun buildEssay(isDraft: Boolean): Essay? {
        val topic = selectedTopic ?: run {
            Toast.makeText(context, "Please select a topic first.", Toast.LENGTH_SHORT).show()
            return null
        }
        if (!isDraft && body.isBlank()) {
            Toast.makeText(context, "Please add some text before publishing.", Toast.LENGTH_SHORT).show()
            return null
        }
        val id = existing?.id ?: System.currentTimeMillis()
        val created = existing?.createdAtMillis ?: System.currentTimeMillis()
        return Essay(
            id = id,
            topic = topic,
            body = body.trim(),
            imageUris = imageUris,
            tags = listOf(EssayConstants.topicLabel(topic)),
            createdAtMillis = created,
            updatedAtMillis = System.currentTimeMillis(),
            isDraft = isDraft,
            weather = weather.ifBlank { null },
            mood = mood.ifBlank { null },
            locationLabel = locationLabel.ifBlank { null },
            isDailyQuote = essayId?.let { id ->
                taskViewModel.uiState.value.essays.find { it.id == id }?.isDailyQuote == true
            } ?: false
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(AppSpacing.SectionSmall))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 64.dp)
                .padding(
                    horizontal = AppSpacing.PageHorizontal,
                    vertical = AppSpacing.SectionMedium
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommonBackButton(onClick = onBack)
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = headerDatePart,
                    fontSize = AppScreenHeader.titleSp,
                    fontWeight = AppScreenHeader.titleWeight,
                    color = AppScreenHeader.titleColor,
                    maxLines = 1
                )
                Text(
                    text = headerTimePart,
                    fontSize = 12.sp,
                    color = Color(0xFF666666),
                    maxLines = 1
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                val eid = existing?.id
                if (eid != null && existing?.isDraft == false) {
                    Box {
                        IconButton(onClick = { menuOpen = true }) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = "More options",
                                tint = Color(0xFF1A1A1A)
                            )
                        }
                        DropdownMenu(
                            expanded = menuOpen,
                            onDismissRequest = { menuOpen = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Set as daily quote") },
                                onClick = {
                                    menuOpen = false
                                    taskViewModel.setDailyQuoteFromEssay(eid)
                                    Toast.makeText(context, "Saved as daily quote.", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(4.dp))
                IconButton(
                    onClick = {
                        val e = buildEssay(isDraft = false) ?: return@IconButton
                        taskViewModel.saveEssay(e.copy(isDraft = false))
                        onBack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Publish",
                        tint = Color(0xFF8A7CF8),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = AppSpacing.PageHorizontal)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                EssayTopic.entries.forEach { topic ->
                    EssayTopicCapsule(
                        topic = topic,
                        selected = selectedTopic == topic,
                        lockedSelected = false,
                        enabled = true,
                        labelOverride = essayTopicEnglish(topic),
                        onClick = { selectedTopic = topic }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = behaviorSummaryHint,
                fontSize = 13.sp,
                color = Color(0xFF6B5B95),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF3EFFF))
                    .padding(12.dp)
            )
            Spacer(Modifier.height(12.dp))

            BasicTextField(
                value = body,
                onValueChange = { body = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 160.dp)
                    .focusRequester(bodyFocusRequester),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF1A1A1A)
                ),
                decorationBox = { inner ->
                    if (body.isEmpty()) {
                        Text(
                            text = if (selectedTopic == null) {
                                "Select a topic to begin."
                            } else {
                                "What did you do?"
                            },
                            fontSize = 16.sp,
                            color = AppColors.HintText
                        )
                    }
                    inner()
                }
            )

            Spacer(Modifier.height(16.dp))
            Text(
                text = "${imageUris.size}/${EssayConstants.MAX_IMAGES}",
                fontSize = 12.sp,
                color = AppColors.SecondaryText
            )
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                imageUris.forEach { _ ->
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFE8E8E8))
                    )
                }
                if (imageUris.size < EssayConstants.MAX_IMAGES) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFE8E8E8))
                            .clickable { pickImages.launch("image/*") },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add image",
                            tint = AppColors.IconNeutral,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            Text(
                text = if (envExpanded) "Context notes ▲" else "+ Context notes",
                fontSize = 14.sp,
                color = Color(0xFF8A7CF8),
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clickable { envExpanded = !envExpanded }
                    .padding(vertical = 8.dp)
            )
            if (envExpanded) {
                Text("Weather", fontSize = 12.sp, color = AppColors.SecondaryText)
                BasicTextField(
                    value = weather,
                    onValueChange = { weather = it },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    textStyle = TextStyle(fontSize = 15.sp, color = Color(0xFF1A1A1A)),
                    decorationBox = { inner ->
                        if (weather.isEmpty()) {
                            Text("Optional", color = AppColors.HintText, fontSize = 15.sp)
                        }
                        inner()
                    }
                )
                Text("Mood", fontSize = 12.sp, color = AppColors.SecondaryText)
                BasicTextField(
                    value = mood,
                    onValueChange = { mood = it },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    textStyle = TextStyle(fontSize = 15.sp, color = Color(0xFF1A1A1A)),
                    decorationBox = { inner ->
                        if (mood.isEmpty()) {
                            Text("Optional", color = AppColors.HintText, fontSize = 15.sp)
                        }
                        inner()
                    }
                )
                Text("Location", fontSize = 12.sp, color = AppColors.SecondaryText)
                BasicTextField(
                    value = locationLabel,
                    onValueChange = { locationLabel = it },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 15.sp, color = Color(0xFF1A1A1A)),
                    decorationBox = { inner ->
                        if (locationLabel.isEmpty()) {
                            Text("Optional", color = AppColors.HintText, fontSize = 15.sp)
                        }
                        inner()
                    }
                )
            }

            Spacer(Modifier.height(24.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppSpacing.PageHorizontal, vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = {
                    val e = buildEssay(isDraft = true) ?: return@OutlinedButton
                    taskViewModel.saveEssay(e)
                    Toast.makeText(context, "Saved as draft.", Toast.LENGTH_SHORT).show()
                    onBack()
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF1A1A1A))
            ) {
                Text("Save draft")
            }
            Button(
                onClick = {
                    val e = buildEssay(isDraft = false) ?: return@Button
                    taskViewModel.saveEssay(e.copy(isDraft = false))
                    onBack()
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8A7CF8))
            ) {
                Text("Save", color = Color.White)
            }
        }
    }
}
