package com.example.kairosapplication.ui.mine.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.core.ui.AppShapes
import com.example.taskmodel.model.UrgencyConfig
import com.example.taskmodel.model.UrgencyLevelConfig
import com.example.taskmodel.util.ColorUtils.parseHexToArgb

@Composable
fun UrgencySettingsScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val urgencyConfig by viewModel.urgencyConfig.collectAsState()
    var editingLevel by remember { mutableStateOf<UrgencyLevelConfig?>(null) }
    
    SettingsL2Scaffold(
        title = LocalizedStrings.get("urgency_settings_title"),
        onBack = onBack,
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(8.dp))
            
            // Preview bar showing all 4 colors
            UrgencyPreviewBar(config = urgencyConfig)
            
            Spacer(Modifier.height(16.dp))
            
            SettingsGroupCard(title = LocalizedStrings.get("urgency_levels")) {
                urgencyConfig.levels.forEach { level ->
                    UrgencyLevelRow(
                        level = level,
                        onClick = { editingLevel = level }
                    )
                    if (level.level < 3) SettingsGroupDivider()
                }
            }
            
            Spacer(Modifier.height(16.dp))
            
            // Reset button
            Button(
                onClick = { viewModel.resetUrgencyConfig() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE0E0E0),
                    contentColor = Color(0xFF333333)
                )
            ) {
                Text(LocalizedStrings.get("reset_to_default"))
            }
            
            Spacer(Modifier.height(32.dp))
        }
    }
    
    // Edit bottom sheet
    editingLevel?.let { level ->
        UrgencyEditSheet(
            level = level,
            onDismiss = { editingLevel = null },
            onSave = { updated ->
                val newLevels = urgencyConfig.levels.map { 
                    if (it.level == updated.level) updated else it 
                }
                viewModel.setUrgencyConfig(UrgencyConfig(newLevels))
                editingLevel = null
            }
        )
    }
}

@Composable
private fun UrgencyPreviewBar(config: UrgencyConfig) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .clip(RoundedCornerShape(AppShapes.InsetContentRadius))
            .background(Color(0xFFF0F0F0)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        config.levels.forEach { level ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color(parseHexToArgb(level.colorHex)))
            )
        }
    }
}

@Composable
private fun UrgencyLevelRow(
    level: UrgencyLevelConfig,
    onClick: () -> Unit
) {
    val chrome = rememberSettingsChrome()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(Color(parseHexToArgb(level.colorHex)))
        )
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = level.label,
                fontSize = 15.sp,
                color = chrome.title
            )
            if (level.description.isNotBlank()) {
                Text(
                    text = level.description,
                    fontSize = 12.sp,
                    color = chrome.subtitle
                )
            }
        }
        Text("›", fontSize = 18.sp, color = chrome.subtitle)
    }
}

@Composable
private fun UrgencyEditSheet(
    level: UrgencyLevelConfig,
    onDismiss: () -> Unit,
    onSave: (UrgencyLevelConfig) -> Unit
) {
    var label by remember { mutableStateOf(level.label) }
    var description by remember { mutableStateOf(level.description) }
    var colorHex by remember { mutableStateOf(level.colorHex) }
    
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(LocalizedStrings.get("edit_urgency_level")) },
        text = {
            Column {
                OutlinedTextField(
                    value = label,
                    onValueChange = { label = it },
                    label = { Text(LocalizedStrings.get("label")) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(LocalizedStrings.get("description")) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = colorHex,
                    onValueChange = { colorHex = it },
                    label = { Text(LocalizedStrings.get("color_hex")) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(UrgencyLevelConfig(level.level, label, description, colorHex))
            }) {
                Text(LocalizedStrings.get("save"))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(LocalizedStrings.get("cancel"))
            }
        }
    )
}
