package com.example.kairosapplication.ui.mine.settings

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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
import androidx.core.content.FileProvider
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.theme.BackgroundColor

private val CardBg = Color.White
private val DividerC = Color(0xFFE8E5E0)
private val TitleC = Color(0xFF1A1A1A)
private val SubC = Color(0xFF9E9E9E)
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

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        LocalizedStrings.get("data_export"),
                        color = TitleC,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = TitleC)
                    }
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
                Column(Modifier.padding(16.dp)) {
                    Text(
                        LocalizedStrings.get("export_content"),
                        color = SubC,
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
                shape = RoundedCornerShape(8.dp),
                color = CardBg,
                shadowElevation = 2.dp
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        LocalizedStrings.get("export_format"),
                        color = SubC,
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
                        Text(LocalizedStrings.get("export_json_desc"), fontSize = 15.sp, color = TitleC)
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(selected = !formatJson, onClick = { formatJson = false })
                        Text(LocalizedStrings.get("export_txt_desc"), fontSize = 15.sp, color = TitleC)
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = {
                    if (formatJson) {
                        viewModel.exportJson(
                            includeNotes = includeNotes,
                            includeTasks = includeTasks,
                            includeMoods = includeMoods,
                            includeProfile = includeProfile
                        )
                    } else {
                        viewModel.exportTxt()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Blue)
            ) {
                Text(LocalizedStrings.get("export_to_file"), fontSize = 16.sp)
            }
            Spacer(Modifier.height(32.dp))
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontSize = 15.sp, color = TitleC)
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled
        )
    }
}
