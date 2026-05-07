package com.example.kairosapplication.ui.mine.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings

@Composable
fun PrivacySettingsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    var showPolicy by remember { mutableStateOf(false) }
    var showDeleteConfirm by remember { mutableStateOf(false) }

    SettingsL2Scaffold(
        title = LocalizedStrings.get("privacy_settings"),
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
            SettingsGroupCard(title = LocalizedStrings.get("privacy_policy")) {
                Button(
                    onClick = { showPolicy = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(LocalizedStrings.get("view_privacy_policy"))
                }
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = LocalizedStrings.get("delete_account")) {
                Text(
                    LocalizedStrings.get("delete_irreversible_short"),
                    fontSize = 13.sp,
                    color = Color(0xFFD32F2F),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Button(
                    onClick = { showDeleteConfirm = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                ) {
                    Text(LocalizedStrings.get("delete_account"))
                }
            }
            Spacer(Modifier.height(32.dp))
        }
    }

    if (showPolicy) {
        AlertDialog(
            onDismissRequest = { showPolicy = false },
            title = { Text(LocalizedStrings.get("privacy_policy")) },
            text = {
                Text(
                    LocalizedStrings.get("privacy_policy_body"),
                    fontSize = 14.sp
                )
            },
            confirmButton = {
                TextButton(onClick = { showPolicy = false }) { Text(LocalizedStrings.get("close")) }
            }
        )
    }

    if (showDeleteConfirm) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirm = false },
            title = {
                Text(
                    LocalizedStrings.get("delete_account"),
                    fontWeight = FontWeight.SemiBold
                )
            },
            text = {
                Text(LocalizedStrings.get("delete_account_warning"), fontSize = 14.sp)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteConfirm = false
                        Toast.makeText(
                            context,
                            LocalizedStrings.stringFor(lang, "feature_developing", context),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) { Text(LocalizedStrings.get("confirm_delete"), color = Color(0xFFD32F2F)) }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirm = false }) {
                    Text(LocalizedStrings.get("cancel"))
                }
            }
        )
    }
}
