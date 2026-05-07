package com.example.kairosapplication.ui.mine.settings

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings
import java.util.Locale

@Composable
fun MiscSettingsScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    val cacheBytes by viewModel.cacheSizeBytes.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.refreshCacheSize()
    }

    val versionName = remember {
        try {
            val ctx = context.applicationContext
            val pi = ctx.packageManager.getPackageInfo(ctx.packageName, 0)
            pi.versionName ?: "1.0.0"
        } catch (_: PackageManager.NameNotFoundException) {
            "1.0.0"
        }
    }

    val mb = cacheBytes / (1024.0 * 1024.0)
    val cacheLabel = String.format(Locale.getDefault(), "%.1f MB", mb)

    SettingsL2Scaffold(
        title = LocalizedStrings.get("other_settings"),
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
            SettingsGroupCard(title = LocalizedStrings.get("clear_cache")) {
                Text(
                    "${LocalizedStrings.stringFor(lang, "cache_size", context)}: $cacheLabel",
                    fontSize = 15.sp,
                    color = SettingsTitleC
                )
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = {
                        viewModel.clearCache()
                        Toast.makeText(
                            context,
                            LocalizedStrings.stringFor(lang, "cache_cleared", context),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = SettingsSwitchBlue)
                ) {
                    Text(LocalizedStrings.stringFor(lang, "clear_cache", context))
                }
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = LocalizedStrings.get("feedback")) {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:feedback@kairos.app?subject=Kairos%20Feedback")
                        }
                        try {
                            context.startActivity(
                                Intent.createChooser(
                                    intent,
                                    LocalizedStrings.stringFor(lang, "feedback", context)
                                )
                            )
                        } catch (_: Exception) {
                            Toast.makeText(
                                context,
                                LocalizedStrings.stringFor(lang, "no_mail_app", context),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(LocalizedStrings.get("feedback"))
                }
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = LocalizedStrings.get("share_app")) {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "${LocalizedStrings.stringFor(lang, "recommend_kairos", context)}${context.packageName}"
                            )
                        }
                        context.startActivity(
                            Intent.createChooser(
                                intent,
                                LocalizedStrings.stringFor(lang, "share_app", context)
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(LocalizedStrings.get("share_app"))
                }
            }
            Spacer(Modifier.height(16.dp))
            SettingsGroupCard(title = LocalizedStrings.get("about")) {
                Text(
                    "${LocalizedStrings.get("version_prefix")}$versionName",
                    fontSize = 15.sp,
                    color = SettingsTitleC
                )
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}
