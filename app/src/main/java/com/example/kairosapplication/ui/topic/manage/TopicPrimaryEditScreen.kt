package com.example.kairosapplication.ui.topic.manage

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import com.example.kairosapplication.ui.glass.glassChromeTextStyle
import com.example.kairosapplication.ui.mine.MineDetailScaffold
import com.example.kairosapplication.ui.mine.components.MineCardShell
import com.example.kairosapplication.ui.mine.settings.rememberSettingsChrome
import com.example.kairosapplication.ui.topic.EssayCategoryUi
import androidx.compose.ui.text.TextStyle
import com.example.taskmodel.model.EssaySecondaryCategoryConfig
import com.example.taskmodel.viewmodel.TaskViewModel
import java.util.UUID

private val ClassicTitleColor = Color(0xFF1A1A1A)
private val ClassicMuted = Color(0xFF757575)
private val ClassicSectionBg = Color(0xFFFAFAFA)
private val LinkAccent = Color(0xFF5C6BC0)

/** 仅管理二级课题；主课题名称与引导句由系统固定，不在此编辑。 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicPrimaryEditScreen(
    primaryKey: String,
    taskViewModel: TaskViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val uiState by taskViewModel.uiState.collectAsState()
    val config = uiState.essayCategoryConfig
    val lang = LocalCurrentLanguage.current.value
    var draftSecondaries by remember(primaryKey) {
        mutableStateOf<List<EssaySecondaryCategoryConfig>>(emptyList())
    }
    LaunchedEffect(primaryKey, config) {
        draftSecondaries = config.primary(primaryKey).secondaries.map { it.copy() }
    }

    var showSecSheet by remember { mutableStateOf(false) }
    var editingSecIndex by remember { mutableStateOf<Int?>(null) }
    var secSheetName by remember { mutableStateOf("") }
    var secSheetGuide by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    fun secondaryResolvedLabel(sec: EssaySecondaryCategoryConfig): String =
        EssayCategoryUi.secondaryDisplayLabel(primaryKey, sec, lang, context).trim()

    fun hasDuplicateNames(list: List<EssaySecondaryCategoryConfig>): Boolean {
        val labels = list.map { secondaryResolvedLabel(it).lowercase() }.filter { it.isNotEmpty() }
        return labels.size != labels.toSet().size
    }

    fun openAddSecondary() {
        editingSecIndex = null
        secSheetName = ""
        secSheetGuide = ""
        showSecSheet = true
    }

    fun openEditSecondary(index: Int, sec: EssaySecondaryCategoryConfig) {
        editingSecIndex = index
        secSheetName = sec.name.ifBlank { sec.id }
        secSheetGuide = sec.guide.orEmpty()
        showSecSheet = true
    }

    fun confirmSecondarySheet() {
        val name = secSheetName.trim()
        if (name.isEmpty()) return
        val guide = secSheetGuide.trim().takeIf { it.isNotEmpty() }
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
        val trial = if (idx == null) nextSecs else nextSecs
        if (hasDuplicateNames(nextSecs)) {
            Toast.makeText(
                context,
                LocalizedStrings.stringFor(lang, "topic_secondary_name_duplicate", context),
                Toast.LENGTH_SHORT,
            ).show()
            return
        }
        draftSecondaries = nextSecs
        showSecSheet = false
    }

    fun persistSecondariesOnly() {
        if (hasDuplicateNames(draftSecondaries)) {
            Toast.makeText(
                context,
                LocalizedStrings.stringFor(lang, "topic_secondary_name_duplicate", context),
                Toast.LENGTH_SHORT,
            ).show()
            return
        }
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

    val chrome = rememberSettingsChrome()
    val isGlass = LocalAppUiTheme.current == AppUiTheme.Glass
    val cardText = LocalGlassTextColors.current
    val titleColor = if (isGlass) cardText.primary else ClassicTitleColor
    val mutedColor = if (isGlass) cardText.muted else ClassicMuted

    MineDetailScaffold(
        title = EssayCategoryUi.primaryDisplayName(primaryKey, config, lang, context),
        onBack = onBack,
        modifier = modifier,
        actions = {
            TextButton(onClick = { persistSecondariesOnly() }) {
                Text(
                    LocalizedStrings.get("save"),
                    color = chrome.title,
                    style = glassChromeTextStyle(TextStyle.Default, chrome.useLightChrome),
                )
            }
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Text(
                    text = LocalizedStrings.get("topic_manage_secondary_only_subtitle"),
                    fontSize = 11.sp,
                    color = chrome.subtitle,
                    style = glassChromeTextStyle(TextStyle.Default, chrome.useLightChrome),
                    modifier = Modifier.padding(bottom = 4.dp),
                )
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "${LocalizedStrings.get("topic_secondary_manage")} (${draftSecondaries.size}/8)",
                        fontSize = 13.sp,
                        color = chrome.subtitle,
                        fontWeight = FontWeight.Medium,
                        style = glassChromeTextStyle(TextStyle.Default, chrome.useLightChrome),
                    )
                    Text(
                        text = "+ ${LocalizedStrings.get("topic_secondary_add")}",
                        fontSize = 13.sp,
                        color = LinkAccent,
                        modifier = Modifier
                            .clickable(enabled = draftSecondaries.size < 8, onClick = { openAddSecondary() })
                            .padding(4.dp),
                    )
                }
            }

            itemsIndexed(draftSecondaries, key = { _, sec -> sec.id }) { index, sec ->
                key(sec.id, sec.guide) {
                    var guideExpanded by remember(sec.id, draftSecondaries.size) {
                        mutableStateOf(sec.guide?.isNotBlank() == true)
                    }
                    val guideText = sec.guide?.takeIf { it.isNotBlank() }
                    SecondaryTopicRow(
                        isGlass = isGlass,
                        titleColor = titleColor,
                        mutedColor = mutedColor,
                        primaryKey = primaryKey,
                        sec = sec,
                        lang = lang,
                        context = context,
                        guideText = guideText,
                        guideExpanded = guideExpanded,
                        onGuideExpandedToggle = { guideExpanded = !guideExpanded },
                        canDelete = draftSecondaries.size > 1,
                        onEdit = { openEditSecondary(index, sec) },
                        onDelete = {
                            draftSecondaries = draftSecondaries.toMutableList().also { it.removeAt(index) }
                        },
                    )
                }
            }
        }
    }

    if (showSecSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSecSheet = false },
            sheetState = sheetState,
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
            ) {
                Text(
                    text = if (editingSecIndex == null) {
                        LocalizedStrings.get("topic_secondary_add")
                    } else {
                        LocalizedStrings.get("topic_secondary_edit")
                    },
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = titleColor,
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = secSheetName,
                    onValueChange = { secSheetName = it },
                    label = { Text(LocalizedStrings.get("topic_secondary_name_label")) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(10.dp))
                OutlinedTextField(
                    value = secSheetGuide,
                    onValueChange = { secSheetGuide = it },
                    label = { Text(LocalizedStrings.get("topic_secondary_guide_hint")) },
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(onClick = { showSecSheet = false }) {
                        Text(LocalizedStrings.get("cancel"))
                    }
                    TextButton(
                        onClick = { confirmSecondarySheet() },
                        enabled = secSheetName.trim().isNotEmpty(),
                    ) {
                        Text(LocalizedStrings.get("confirm"))
                    }
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun SecondaryTopicRow(
    isGlass: Boolean,
    titleColor: Color,
    mutedColor: Color,
    primaryKey: String,
    sec: EssaySecondaryCategoryConfig,
    lang: LocalizationManager.Language,
    context: Context,
    guideText: String?,
    guideExpanded: Boolean,
    onGuideExpandedToggle: () -> Unit,
    canDelete: Boolean,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    val rowContent: @Composable () -> Unit = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    EssayCategoryUi.secondaryDisplayLabel(primaryKey, sec, lang, context),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = titleColor,
                )
                if (guideText != null) {
                    Spacer(Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable(onClick = onGuideExpandedToggle)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = LocalizedStrings.get("topic_secondary_guide_fold_hint"),
                            fontSize = 11.sp,
                            color = mutedColor,
                            modifier = Modifier.weight(1f),
                        )
                        Icon(
                            imageVector = if (guideExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = mutedColor,
                        )
                    }
                    if (guideExpanded) {
                        Text(
                            text = "▸ $guideText",
                            fontSize = 11.sp,
                            color = mutedColor,
                            lineHeight = 14.sp,
                        )
                    }
                }
            }
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = null, tint = mutedColor)
            }
            IconButton(onClick = onDelete, enabled = canDelete) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null,
                    tint = if (canDelete) mutedColor else mutedColor.copy(alpha = 0.35f),
                )
            }
        }
    }

    if (isGlass) {
        MineCardShell(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(AppShapes.DenseInsetRadius),
        ) {
            rowContent()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ClassicSectionBg, RoundedCornerShape(AppShapes.DenseInsetRadius)),
        ) {
            rowContent()
        }
    }
}
