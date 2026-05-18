package com.example.kairosapplication.ui.mine.settings

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.background
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.ui.theme.BackgroundColor

private val CardBg = AppColors.GlassFill
private val DividerC = Color(0xFFE8E5E0)
private val Blue = Color(0xFF2196F3)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataExportScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val counts by viewModel.exportCounts.collectAsState()
    val exportState by viewModel.exportState.collectAsState()

    var includeNotes by remember { mutableStateOf(true) }
    var includeTasks by remember { mutableStateOf(true) }
    var includeMoods by remember { mutableStateOf(true) }
    var includeProfile by remember { mutableStateOf(true) }
    var formatJson by remember { mutableStateOf(true) }
    
    // Export settings dialog state
    var showExportSettings by remember { mutableStateOf(false) }
    var customFileName by remember { mutableStateOf("") }
    var saveToDocuments by remember { mutableStateOf(false) }

    LaunchedEffect(exportState) {
        val langSnap = LocalizationManager.Language.fromCode(
            DataStoreManager(context.applicationContext).getLanguageSync()
        )
        when (val s = exportState) {
            is SettingsViewModel.ExportState.Success -> {
                Toast.makeText(
                    context,
                    LocalizedStrings.stringFor(langSnap, "export_success", context),
                    Toast.LENGTH_SHORT
                ).show()
            }
            is SettingsViewModel.ExportState.Error -> {
                Toast.makeText(context, s.message, Toast.LENGTH_SHORT).show()
                viewModel.clearExportState()
            }
            else -> Unit
        }
    }

    val chrome = rememberSettingsChrome()

    fun shareFile(file: java.io.File, mime: String) {
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = mime
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(
            Intent.createChooser(intent, LocalizedStrings.stringFor(lang, "share", context))
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor),
    ) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = {
                    Text(
                        LocalizedStrings.get("data_export"),
                        color = chrome.title,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    SettingsBackButton(onClick = onBack)
                },
                actions = {
                    TextButton(
                        onClick = {
                            val s = exportState
                            if (s is SettingsViewModel.ExportState.Success) {
                                shareFile(
                                    s.file,
                                    if (formatJson) "application/json" else "text/plain"
                                )
                            } else {
                                Toast.makeText(
                                    context,
                                    LocalizedStrings.stringFor(lang, "export_first", context),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    ) {
                        Text(LocalizedStrings.get("share"), color = Blue, fontSize = 15.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundColor),
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(8.dp))
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(AppShapes.DenseInsetRadius),
                color = CardBg,
                shadowElevation = 2.dp
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        LocalizedStrings.get("export_content"),
                        color = chrome.subtitle,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(Modifier.height(8.dp))
                    HorizontalDivider(thickness = 0.5.dp, color = DividerC)
                    Spacer(Modifier.height(8.dp))
                    ExportCheckRow(
                        label = LocalizedStrings.exportNotesLabel(counts.notes),
                        checked = includeNotes,
                        enabled = formatJson,
                        onCheckedChange = { includeNotes = it }
                    )
                    ExportCheckRow(
                        label = LocalizedStrings.exportTasksLabel(counts.tasks),
                        checked = includeTasks,
                        enabled = formatJson,
                        onCheckedChange = { includeTasks = it }
                    )
                    ExportCheckRow(
                        label = LocalizedStrings.exportMoodsLabel(counts.moods),
                        checked = includeMoods,
                        enabled = formatJson,
                        onCheckedChange = { includeMoods = it }
                    )
                    ExportCheckRow(
                        label = LocalizedStrings.get("include_profile"),
                        checked = includeProfile,
                        enabled = formatJson,
                        onCheckedChange = { includeProfile = it }
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(AppShapes.DenseInsetRadius),
                color = CardBg,
                shadowElevation = 2.dp
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        LocalizedStrings.get("export_format"),
                        color = chrome.subtitle,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(Modifier.height(8.dp))
                    HorizontalDivider(thickness = 0.5.dp, color = DividerC)
                    Spacer(Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(selected = formatJson, onClick = { formatJson = true })
                        Text(LocalizedStrings.get("export_json_desc"), fontSize = 15.sp, color = chrome.title)
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(selected = !formatJson, onClick = { formatJson = false })
                        Text(LocalizedStrings.get("export_txt_desc"), fontSize = 15.sp, color = chrome.title)
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = { showExportSettings = true },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(AppShapes.DenseInsetRadius),
                colors = ButtonDefaults.buttonColors(containerColor = Blue)
            ) {
                Text(LocalizedStrings.get("export_to_file"), fontSize = 16.sp)
            }
            Spacer(Modifier.height(32.dp))
        }

        // Export Settings Bottom Sheet
        if (showExportSettings) {
            ExportSettingsSheet(
                formatJson = formatJson,
                customFileName = customFileName,
                onFileNameChange = { customFileName = it },
                saveToDocuments = saveToDocuments,
                onLocationChange = { saveToDocuments = it },
                onDismiss = { showExportSettings = false },
                onConfirm = {
                    showExportSettings = false
                    if (formatJson) {
                        viewModel.exportJson(
                            includeNotes = includeNotes,
                            includeTasks = includeTasks,
                            includeMoods = includeMoods,
                            includeProfile = includeProfile,
                            customFileName = customFileName.takeIf { it.isNotBlank() },
                            saveToDocuments = saveToDocuments
                        )
                    } else {
                        viewModel.exportTxt(
                            customFileName = customFileName.takeIf { it.isNotBlank() },
                            saveToDocuments = saveToDocuments
                        )
                    }
                }
            )
        }
    }
    }
}

@Composable
private fun ExportCheckRow(
    label: String,
    checked: Boolean,
    enabled: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val chrome = rememberSettingsChrome()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontSize = 15.sp, color = chrome.title)
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExportSettingsSheet(
    formatJson: Boolean,
    customFileName: String,
    onFileNameChange: (String) -> Unit,
    saveToDocuments: Boolean,
    onLocationChange: (Boolean) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val chrome = rememberSettingsChrome()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = AppShapes.SheetTopRadius, topEnd = AppShapes.SheetTopRadius)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Title
            Text(
                text = LocalizedStrings.get("export_settings"),
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = chrome.title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Filename input
            Text(
                text = LocalizedStrings.get("export_filename"),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = chrome.subtitle
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = customFileName,
                onValueChange = onFileNameChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(AppShapes.DenseInsetRadius))
                    .background(Color(0xFFF5F5F5))
                    .padding(12.dp),
                decorationBox = { innerTextField ->
                    if (customFileName.isEmpty()) {
                        Text(
                            text = LocalizedStrings.get("export_custom_name_hint"),
                            color = chrome.subtitle.copy(alpha = 0.6f),
                            fontSize = 15.sp
                        )
                    }
                    innerTextField()
                }
            )
            if (customFileName.isBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = LocalizedStrings.get("export_default_name"),
                    fontSize = 12.sp,
                    color = chrome.subtitle.copy(alpha = 0.7f)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            // Save location
            Text(
                text = LocalizedStrings.get("export_location"),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = chrome.subtitle
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = !saveToDocuments,
                    onClick = { onLocationChange(false) }
                )
                Text(
                    LocalizedStrings.get("export_location_downloads"),
                    fontSize = 15.sp,
                    color = chrome.title
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = saveToDocuments,
                    onClick = { onLocationChange(true) }
                )
                Text(
                    LocalizedStrings.get("export_location_documents"),
                    fontSize = 15.sp,
                    color = chrome.title
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // File extension hint
            val ext = if (formatJson) ".json" else ".txt"
            Text(
                text = "${LocalizedStrings.get("export_format")}: $ext",
                fontSize = 13.sp,
                color = chrome.subtitle,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(LocalizedStrings.get("cancel"), color = chrome.subtitle)
                }
                Button(
                    onClick = onConfirm,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(AppShapes.DenseInsetRadius),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue)
                ) {
                    Text(LocalizedStrings.get("confirm"), fontSize = 16.sp)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
