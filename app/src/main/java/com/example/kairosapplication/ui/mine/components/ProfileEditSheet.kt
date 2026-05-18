package com.example.kairosapplication.ui.mine.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.glass.GlassSurface
import com.example.kairosapplication.ui.glass.LocalGlassAtmosphereUi
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import com.example.kairosapplication.ui.glass.glassBubbleBackdrop
import com.example.kairosapplication.ui.glass.glassChromeTextStyle
import com.example.taskmodel.model.LocalProfile

private val ClassicTitleColor = Color(0xFF1A1A1A)
private val ClassicSheetBlue = Color(0xFF2196F3)
private val ClassicEmojiSelectedBg = Color(0xFFE3F2FD)

private val presetEmojis = listOf(
    "😊", "😀", "😎", "🤔", "🥰", "😌", "🤗", "🙃", "😇", "😈",
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditSheet(
    profile: LocalProfile,
    onDismiss: () -> Unit,
    onSave: (LocalProfile) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (LocalAppUiTheme.current) {
        AppUiTheme.Classic -> ClassicProfileEditSheet(
            profile = profile,
            onDismiss = onDismiss,
            onSave = onSave,
            modifier = modifier,
        )
        AppUiTheme.Glass -> GlassProfileEditSheet(
            profile = profile,
            onDismiss = onDismiss,
            onSave = onSave,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClassicProfileEditSheet(
    profile: LocalProfile,
    onDismiss: () -> Unit,
    onSave: (LocalProfile) -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    var nickname by remember { mutableStateOf(profile.displayName) }
    var selectedEmoji by remember { mutableStateOf(profile.avatarEmoji.ifBlank { "😊" }) }
    val maxH = LocalConfiguration.current.screenHeightDp.dp * 0.8f
    val scroll = rememberScrollState()

    LaunchedEffect(profile.displayName, profile.avatarEmoji) {
        nickname = profile.displayName
        selectedEmoji = profile.avatarEmoji.ifBlank { "😊" }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = AppShapes.SheetTopRadius, topEnd = AppShapes.SheetTopRadius),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = maxH)
                .padding(24.dp)
                .verticalScroll(scroll),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = LocalizedStrings.get("edit_profile"),
                color = ClassicTitleColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(20.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(presetEmojis) { emoji ->
                    val selected = emoji == selectedEmoji
                    Text(
                        text = emoji,
                        fontSize = 28.sp,
                        modifier = Modifier
                            .background(
                                if (selected) ClassicEmojiSelectedBg else Color.Transparent,
                                RoundedCornerShape(AppShapes.DenseInsetRadius),
                            )
                            .padding(horizontal = 6.dp, vertical = 4.dp)
                            .clickable { selectedEmoji = emoji },
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            OutlinedTextField(
                value = nickname,
                onValueChange = { if (it.length <= 20) nickname = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = ClassicTitleColor,
                    unfocusedTextColor = ClassicTitleColor,
                ),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            )
            Spacer(Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f),
                ) {
                    Text(LocalizedStrings.get("cancel"), color = Color(0xFF757575))
                }
                Button(
                    onClick = {
                        onSave(
                            LocalProfile(
                                displayName = nickname.ifBlank {
                                    LocalizedStrings.stringFor(lang, "user_nickname", context)
                                },
                                avatarEmoji = selectedEmoji.ifBlank { "😊" },
                                avatarImageUri = profile.avatarImageUri,
                            ),
                        )
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = ClassicSheetBlue),
                ) {
                    Text(LocalizedStrings.get("save"), color = Color.White)
                }
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GlassProfileEditSheet(
    profile: LocalProfile,
    onDismiss: () -> Unit,
    onSave: (LocalProfile) -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val context = LocalContext.current
    val lang = LocalCurrentLanguage.current.value
    var nickname by remember { mutableStateOf(profile.displayName) }
    var selectedEmoji by remember { mutableStateOf(profile.avatarEmoji.ifBlank { "😊" }) }
    val maxH = LocalConfiguration.current.screenHeightDp.dp * 0.55f
    val scroll = rememberScrollState()
    val chrome = LocalGlassAtmosphereUi.current.topChrome
    val useLightChrome = !LocalGlassAtmosphereUi.current.zones.topIsLight
    val cardText = LocalGlassTextColors.current
    val sheetShape = RoundedCornerShape(
        topStart = AppShapes.SheetTopRadius,
        topEnd = AppShapes.SheetTopRadius,
    )
    val sheetFill = Color.Black.copy(alpha = GlassConstants.BottomNavFillAlpha)

    LaunchedEffect(profile.displayName, profile.avatarEmoji) {
        nickname = profile.displayName
        selectedEmoji = profile.avatarEmoji.ifBlank { "😊" }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.Transparent,
        dragHandle = null,
        scrimColor = Color.Black.copy(alpha = 0.48f),
        shape = sheetShape,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(sheetShape)
                .glassBubbleBackdrop()
                .background(sheetFill),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(chrome.primary.copy(alpha = 0.75f)),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = maxH)
                    .padding(horizontal = 20.dp, vertical = 16.dp)
                    .verticalScroll(scroll),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = LocalizedStrings.get("edit_profile"),
                    color = chrome.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(20.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(presetEmojis) { emoji ->
                        val selected = emoji == selectedEmoji
                        Box(
                            modifier = Modifier
                                .background(
                                    if (selected) Color.White.copy(alpha = 0.22f) else Color.Transparent,
                                    RoundedCornerShape(AppShapes.DenseInsetRadius),
                                )
                                .clickable { selectedEmoji = emoji }
                                .padding(horizontal = 6.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(text = emoji, fontSize = 28.sp)
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
                OutlinedTextField(
                    value = nickname,
                    onValueChange = { if (it.length <= 20) nickname = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = cardText.primary,
                        unfocusedTextColor = cardText.primary,
                        focusedBorderColor = chrome.secondary.copy(alpha = 0.5f),
                        unfocusedBorderColor = chrome.secondary.copy(alpha = 0.35f),
                        cursorColor = chrome.primary,
                    ),
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                )
                Spacer(Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(
                            LocalizedStrings.get("cancel"),
                            color = chrome.secondary,
                            style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                        )
                    }
                    GlassSurface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                onSave(
                                    LocalProfile(
                                        displayName = nickname.ifBlank {
                                            LocalizedStrings.stringFor(lang, "user_nickname", context)
                                        },
                                        avatarEmoji = selectedEmoji.ifBlank { "😊" },
                                        avatarImageUri = profile.avatarImageUri,
                                    ),
                                )
                            },
                        shape = RoundedCornerShape(999.dp),
                        fillAlpha = 0.72f,
                        skipHazeBackdrop = true,
                        wrapHazeToContent = true,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = LocalizedStrings.get("save"),
                                color = chrome.primary,
                                fontWeight = FontWeight.SemiBold,
                                style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                            )
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
