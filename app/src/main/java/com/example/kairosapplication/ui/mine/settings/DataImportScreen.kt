package com.example.kairosapplication.ui.mine.settings

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
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
import com.example.kairosapplication.data.DataImporter
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.theme.BackgroundColor
import java.io.BufferedReader
import java.io.InputStreamReader

private val CardBg = Color.White
private val DividerC = Color(0xFFE8E5E0)
private val TitleC = Color(0xFF1A1A1A)
private val SubC = Color(0xFF9E9E9E)
private val Blue = Color(0xFF2196F3)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataImportScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val preview by viewModel.importPreview.collectAsState()
    val importState by viewModel.importState.collectAsState()

    var mergeMode by remember { mutableStateOf(true) }
    var showOverwriteConfirm by remember { mutableStateOf(false) }

    val openDoc = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri == null) return@rememberLauncherForActivityResult
        runCatching {
            context.contentResolver.openInputStream(uri)?.use { ins ->
                val text = BufferedReader(InputStreamReader(ins, Charsets.UTF_8)).readText()
                viewModel.parseImportJson(text) { r ->
                    r.onFailure {
                        Toast.makeText(
                            context,
                            LocalizedStrings.stringFor(lang, "parse_failed"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } ?: Toast.makeText(
                context,
                LocalizedStrings.stringFor(lang, "cannot_read_file"),
                Toast.LENGTH_SHORT
            ).show()
        }.onFailure {
            Toast.makeText(
                context,
                LocalizedStrings.stringFor(lang, "cannot_read_file"),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    LaunchedEffect(importState) {
        val langSnap = LocalizationManager.Language.fromCode(
            DataStoreManager(context.applicationContext).getLanguageSync()
        )
        when (val s = importState) {
            is SettingsViewModel.ImportState.Success -> {
                Toast.makeText(
                    context,
                    LocalizedStrings.importSuccessMessageFor(
                        langSnap,
                        s.result.newNotes,
                        s.result.newTasks,
                        s.result.newMoods,
                        s.result.conflicts
                    ),
                    Toast.LENGTH_LONG
                ).show()
                viewModel.clearImportState()
            }
            is SettingsViewModel.ImportState.Error -> {
                Toast.makeText(context, s.message, Toast.LENGTH_SHORT).show()
                viewModel.clearImportState()
            }
            else -> Unit
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        LocalizedStrings.get("data_import"),
                        color = TitleC,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = TitleC)
                    }
                }
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
                shape = RoundedCornerShape(8.dp),
                color = CardBg,
                shadowElevation = 2.dp
            ) {
                Column(
                    Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("📁", fontSize = 40.sp)
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = {
                            openDoc.launch(arrayOf("application/json", "application/*", "*/*"))
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Blue),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(LocalizedStrings.get("select_file_import"))
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(LocalizedStrings.get("support_json"), color = SubC, fontSize = 13.sp)
                }
            }
            Spacer(Modifier.height(16.dp))
            preview?.let { data ->
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    color = CardBg,
                    shadowElevation = 2.dp
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            LocalizedStrings.get("file_info"),
                            color = SubC,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(Modifier.height(8.dp))
                        HorizontalDivider(thickness = 0.5.dp, color = DividerC)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "${LocalizedStrings.get("export_date_label")}${data.exportDate}",
                            fontSize = 14.sp,
                            color = TitleC
                        )
                        Text(
                            "${LocalizedStrings.get("app_version_label")}${data.appVersion}",
                            fontSize = 14.sp,
                            color = TitleC
                        )
                        Spacer(Modifier.height(8.dp))
                        HorizontalDivider(thickness = 0.5.dp, color = DividerC)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "${LocalizedStrings.get("notes_count_label")}${data.notes.size}${LocalizedStrings.get("notes_count_suffix")}",
                            fontSize = 14.sp,
                            color = TitleC
                        )
                        Text(
                            "${LocalizedStrings.get("tasks_count_label")}${data.tasks.size}${LocalizedStrings.get("tasks_count_suffix")}",
                            fontSize = 14.sp,
                            color = TitleC
                        )
                        Text(
                            "${LocalizedStrings.get("moods_count_label")}${data.moods.size}${LocalizedStrings.get("moods_count_suffix")}",
                            fontSize = 14.sp,
                            color = TitleC
                        )
                        Spacer(Modifier.height(12.dp))
                        HorizontalDivider(thickness = 0.5.dp, color = DividerC)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            LocalizedStrings.get("import_mode_label"),
                            fontSize = 14.sp,
                            color = TitleC,
                            fontWeight = FontWeight.Medium
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(selected = mergeMode, onClick = { mergeMode = true })
                            Text(LocalizedStrings.get("merge_mode_desc"), fontSize = 14.sp)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(selected = !mergeMode, onClick = { mergeMode = false })
                            Text(
                                LocalizedStrings.get("overwrite_mode_desc"),
                                fontSize = 14.sp,
                                color = Color(0xFFD32F2F)
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = {
                                if (mergeMode) {
                                    viewModel.importData(data, DataImporter.ImportMode.MERGE)
                                } else {
                                    showOverwriteConfirm = true
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Blue)
                        ) {
                            Text(LocalizedStrings.get("start_import"))
                        }
                    }
                }
            }
            Spacer(Modifier.height(32.dp))
        }
    }

    if (showOverwriteConfirm) {
        val data = preview
        AlertDialog(
            onDismissRequest = { showOverwriteConfirm = false },
            title = { Text(LocalizedStrings.get("overwrite_dialog_title")) },
            text = { Text(LocalizedStrings.get("overwrite_dialog_body")) },
            confirmButton = {
                TextButton(
                    onClick = {
                        showOverwriteConfirm = false
                        if (data != null) {
                            viewModel.importData(data, DataImporter.ImportMode.OVERWRITE)
                        }
                    }
                ) { Text(LocalizedStrings.get("confirm_overwrite"), color = Color(0xFFD32F2F)) }
            },
            dismissButton = {
                TextButton(onClick = { showOverwriteConfirm = false }) {
                    Text(LocalizedStrings.get("cancel"))
                }
            }
        )
    }
}
