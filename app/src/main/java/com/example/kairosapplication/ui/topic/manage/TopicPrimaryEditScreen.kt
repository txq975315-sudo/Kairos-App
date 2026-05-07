package com.example.kairosapplication.ui.topic.manage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.topic.EssayCategoryUi
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.taskmodel.model.EssaySecondaryCategoryConfig
import com.example.taskmodel.viewmodel.TaskViewModel
import java.util.UUID

private val TitleColor = Color(0xFF1A1A1A)
private val Muted = Color(0xFF757575)
private val SectionBg = Color.White

/** 仅管理二级课题；主课题名称与引导句由系统固定，不在此编辑。 */
@Composable
fun TopicPrimaryEditScreen(
    primaryKey: String,
    taskViewModel: TaskViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by taskViewModel.uiState.collectAsState()
    val config = uiState.essayCategoryConfig
    val lang = LocalCurrentLanguage.current.value

    var draftSecondaries by remember(primaryKey) {
        mutableStateOf<List<EssaySecondaryCategoryConfig>>(emptyList())
    }
    LaunchedEffect(primaryKey, config) {
        draftSecondaries = config.primary(primaryKey).secondaries.map { it.copy() }
    }

    var showSecDialog by remember { mutableStateOf(false) }
    var editingSecIndex by remember { mutableStateOf<Int?>(null) }
    var secDialogName by remember { mutableStateOf("") }
    var secDialogGuide by remember { mutableStateOf("") }
    var showAdvancedSec by remember { mutableStateOf(false) }

    fun openAddSecondary() {
        editingSecIndex = null
        secDialogName = ""
        secDialogGuide = ""
        showAdvancedSec = false
        showSecDialog = true
    }

    fun openEditSecondary(index: Int, sec: EssaySecondaryCategoryConfig) {
        editingSecIndex = index
        secDialogName = sec.name.ifBlank { sec.id }
        secDialogGuide = sec.guide.orEmpty()
        showAdvancedSec = secDialogGuide.isNotBlank()
        showSecDialog = true
    }

    fun confirmSecondaryDialog() {
        val name = secDialogName.trim()
        if (name.isEmpty()) return
        val guide = secDialogGuide.trim().takeIf { it.isNotEmpty() }
        val idx = editingSecIndex
        val nextSecs = draftSecondaries.toMutableList()
        if (idx == null) {
            if (nextSecs.size >= 8) return
            val id = "custom_${UUID.randomUUID().toString().replace("-", "").take(12)}"
            nextSecs.add(EssaySecondaryCategoryConfig(id = id, name = name, guide = guide))
        } else {
            val old = nextSecs[idx]
            nextSecs[idx] = old.copy(name = name, guide = guide)
        }
        draftSecondaries = nextSecs
        showSecDialog = false
    }

    fun persistSecondariesOnly() {
        taskViewModel.saveEssayCategoryConfig(
            config.updatePrimary(primaryKey) {
                it.copy(
                    displayName = "",
                    emoji = "",
                    summaryGuide = "",
                    bodyPlaceholder = "",
                    secondaries = draftSecondaries,
                )
            },
        )
        onBack()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .statusBarsPadding(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = LocalizedStrings.get("back"), tint = TitleColor)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = EssayCategoryUi.primaryDisplayName(primaryKey, config, lang),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TitleColor,
                )
                Text(
                    text = LocalizedStrings.get("topic_manage_secondary_only_subtitle"),
                    fontSize = 11.sp,
                    color = Muted,
                    lineHeight = 14.sp,
                )
            }
            TextButton(onClick = { persistSecondariesOnly() }) {
                Text(LocalizedStrings.get("save"))
            }
        }
        HorizontalDivider(color = Color(0xFFE0E0E0))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "${LocalizedStrings.get("topic_secondary_manage")} (${draftSecondaries.size}/8)",
                        fontSize = 13.sp,
                        color = Muted,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        text = "+ ${LocalizedStrings.get("topic_secondary_add")}",
                        fontSize = 13.sp,
                        color = Color(0xFF5C6BC0),
                        modifier = Modifier
                            .clickable(enabled = draftSecondaries.size < 8, onClick = { openAddSecondary() })
                            .padding(4.dp),
                    )
                }
            }

            itemsIndexed(draftSecondaries, key = { _, sec -> sec.id }) { index, sec ->
                val label = EssayCategoryUi.secondaryDisplayLabel(primaryKey, sec, lang)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SectionBg, RoundedCornerShape(10.dp))
                        .padding(10.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(label, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = TitleColor)
                            sec.guide?.takeIf { it.isNotBlank() }?.let { g ->
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    "▸ $g",
                                    fontSize = 11.sp,
                                    color = Muted,
                                    lineHeight = 14.sp,
                                )
                            }
                        }
                        IconButton(onClick = { openEditSecondary(index, sec) }) {
                            Icon(Icons.Default.Edit, contentDescription = null, tint = Muted)
                        }
                        IconButton(
                            onClick = {
                                draftSecondaries = draftSecondaries.toMutableList().also { it.removeAt(index) }
                            },
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = null, tint = Muted)
                        }
                    }
                }
            }
        }
    }

    if (showSecDialog) {
        AlertDialog(
            onDismissRequest = { showSecDialog = false },
            title = {
                Text(
                    if (editingSecIndex == null) LocalizedStrings.get("topic_secondary_add")
                    else LocalizedStrings.get("topic_secondary_edit"),
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = secDialogName,
                        onValueChange = { secDialogName = it },
                        label = { Text(LocalizedStrings.get("topic_secondary_name_label")) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        LocalizedStrings.get("topic_advanced_settings"),
                        fontSize = 12.sp,
                        color = Muted,
                        modifier = Modifier
                            .clickable { showAdvancedSec = !showAdvancedSec }
                            .padding(vertical = 4.dp),
                    )
                    if (showAdvancedSec) {
                        OutlinedTextField(
                            value = secDialogGuide,
                            onValueChange = { secDialogGuide = it },
                            label = { Text(LocalizedStrings.get("topic_secondary_guide_hint")) },
                            maxLines = 4,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = { confirmSecondaryDialog() },
                    enabled = secDialogName.trim().isNotEmpty(),
                ) {
                    Text(LocalizedStrings.get("confirm"))
                }
            },
            dismissButton = {
                TextButton(onClick = { showSecDialog = false }) {
                    Text(LocalizedStrings.get("cancel"))
                }
            },
        )
    }
}
