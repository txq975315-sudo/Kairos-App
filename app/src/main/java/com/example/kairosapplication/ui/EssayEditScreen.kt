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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppScreenHeader
import com.example.kairosapplication.ui.components.EssayTopicCapsule
import com.example.taskmodel.constants.EssayConstants
import com.example.taskmodel.model.Essay
import com.example.taskmodel.model.EssayTopic
import com.example.taskmodel.viewmodel.TaskViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

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
    var topicLocked by remember(isEditing) { mutableStateOf(isEditing) }
    var body by remember(existing) { mutableStateOf(existing?.body.orEmpty()) }
    var imageUris by remember(existing) {
        mutableStateOf(existing?.imageUris.orEmpty().toList())
    }
    var weather by remember(existing) { mutableStateOf(existing?.weather.orEmpty()) }
    var mood by remember(existing) { mutableStateOf(existing?.mood.orEmpty()) }
    var locationLabel by remember(existing) { mutableStateOf(existing?.locationLabel.orEmpty()) }
    var envExpanded by remember { mutableStateOf(false) }
    var menuOpen by remember { mutableStateOf(false) }

    val keyboard = LocalSoftwareKeyboardController.current
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

    LaunchedEffect(selectedTopic) {
        if (selectedTopic != null && !isEditing) {
            bodyFocusRequester.requestFocus()
            keyboard?.show()
        }
    }

    val guide = selectedTopic?.let { EssayConstants.guideFor(it) }.orEmpty()
    val now = remember { Instant.now().atZone(ZoneId.systemDefault()) }
    val headerDate = remember {
        DateTimeFormatter.ofPattern("MMMM d", Locale.ENGLISH).format(now)
    }
    val subTime = remember {
        DateTimeFormatter.ofPattern("HH:mm EEEE", Locale.ENGLISH).format(now)
    }

    fun buildEssay(isDraft: Boolean): Essay? {
        val topic = selectedTopic ?: run {
            Toast.makeText(context, "请先选择课题", Toast.LENGTH_SHORT).show()
            return null
        }
        if (!isDraft && body.isBlank()) {
            Toast.makeText(context, "请填写正文后再保存", Toast.LENGTH_SHORT).show()
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
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "返回",
                    tint = Color(0xFF1A1A1A)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = headerDate,
                    fontSize = AppScreenHeader.titleSp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A1A)
                )
                Text(
                    text = "/ $subTime",
                    fontSize = 12.sp,
                    color = AppColors.SecondaryText
                )
            }
            Box {
                IconButton(onClick = { menuOpen = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "更多", tint = Color(0xFF1A1A1A))
                }
                DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
                    val eid = existing?.id
                    if (eid != null && existing?.isDraft == false) {
                        DropdownMenuItem(
                            text = { Text("设为每日一句") },
                            onClick = {
                                menuOpen = false
                                taskViewModel.setDailyQuoteFromEssay(eid)
                                Toast.makeText(context, "已设为每日一句", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                EssayTopic.entries.forEach { topic ->
                    val lockedNew = topicLocked && !isEditing
                    EssayTopicCapsule(
                        topic = topic,
                        selected = selectedTopic == topic,
                        lockedSelected = lockedNew,
                        enabled = isEditing || !lockedNew || selectedTopic == topic,
                        onClick = {
                            if (isEditing) {
                                selectedTopic = topic
                            } else {
                                selectedTopic = topic
                                topicLocked = true
                            }
                        }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            if (guide.isNotEmpty()) {
                Text(
                    text = guide,
                    fontSize = 13.sp,
                    color = Color(0xFF6B5B95),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF3EFFF))
                        .padding(12.dp)
                )
                Spacer(Modifier.height(12.dp))
            }

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
                    if (body.isEmpty() && guide.isNotEmpty()) {
                        Text(
                            text = "我做了什么……",
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
                            contentDescription = "添加图片",
                            tint = AppColors.IconNeutral,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            Text(
                text = if (envExpanded) "环境记录 ▲" else "+ 环境记录",
                fontSize = 14.sp,
                color = Color(0xFF8A7CF8),
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clickable { envExpanded = !envExpanded }
                    .padding(vertical = 8.dp)
            )
            if (envExpanded) {
                Text("天气", fontSize = 12.sp, color = AppColors.SecondaryText)
                BasicTextField(
                    value = weather,
                    onValueChange = { weather = it },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    textStyle = TextStyle(fontSize = 15.sp, color = Color(0xFF1A1A1A)),
                    decorationBox = { inner ->
                        if (weather.isEmpty()) {
                            Text("选择天气", color = AppColors.HintText, fontSize = 15.sp)
                        }
                        inner()
                    }
                )
                Text("心情", fontSize = 12.sp, color = AppColors.SecondaryText)
                BasicTextField(
                    value = mood,
                    onValueChange = { mood = it },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    textStyle = TextStyle(fontSize = 15.sp, color = Color(0xFF1A1A1A)),
                    decorationBox = { inner ->
                        if (mood.isEmpty()) {
                            Text("选择心情", color = AppColors.HintText, fontSize = 15.sp)
                        }
                        inner()
                    }
                )
                Text("位置", fontSize = 12.sp, color = AppColors.SecondaryText)
                BasicTextField(
                    value = locationLabel,
                    onValueChange = { locationLabel = it },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 15.sp, color = Color(0xFF1A1A1A)),
                    decorationBox = { inner ->
                        if (locationLabel.isEmpty()) {
                            Text("选择位置", color = AppColors.HintText, fontSize = 15.sp)
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
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = {
                    val e = buildEssay(isDraft = true) ?: return@OutlinedButton
                    taskViewModel.saveEssay(e)
                    Toast.makeText(context, "已存为草稿", Toast.LENGTH_SHORT).show()
                    onBack()
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF1A1A1A))
            ) {
                Text("存为草稿")
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
                Text("保存", color = Color.White)
            }
        }
    }
}
