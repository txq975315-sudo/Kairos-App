package com.example.kairosapplication.ui.topic.manage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import com.example.kairosapplication.ui.glass.glassChromeTextStyle
import com.example.kairosapplication.ui.mine.MineDetailScaffold
import com.example.kairosapplication.ui.mine.components.MineCardShell
import com.example.kairosapplication.ui.mine.settings.rememberSettingsChrome
import com.example.kairosapplication.ui.topic.EssayCategoryUi
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.model.EssayCategoryConfig
import com.example.taskmodel.viewmodel.TaskViewModel

private val ClassicCardBg = Color(0xFFFAFAFA)
private val ClassicTitleColor = Color(0xFF1A1A1A)
private val ClassicMuted = Color(0xFF9E9E9E)
private val ClassicLockedFg = Color(0xFF9E9E9E)

@Composable
fun TopicManageHubScreen(
    taskViewModel: TaskViewModel,
    onBack: () -> Unit,
    onEditPrimary: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val uiState by taskViewModel.uiState.collectAsState()
    val config = uiState.essayCategoryConfig
    val lang = LocalCurrentLanguage.current.value
    val keys = EssayCategoryConfig.PRIMARY_KEYS_ORDER
    val chrome = rememberSettingsChrome()

    MineDetailScaffold(
        title = LocalizedStrings.get("topic_manage"),
        onBack = onBack,
        modifier = modifier,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(keys, key = { it }) { key ->
                    val locked = key == NotePrimaryCategory.FREESTYLE
                    val name = EssayCategoryUi.primaryDisplayName(key, config, lang, context)
                    val emoji = EssayCategoryUi.primaryEmoji(key, config)
                    val secCount = config.primaryOrNull(key)?.secondaries?.size ?: 0
                    TopicManagePrimaryCard(
                        emoji = emoji,
                        title = name,
                        subtitle = if (locked) {
                            LocalizedStrings.get("topic_manage_locked_badge")
                        } else {
                            "$secCount/8"
                        },
                        locked = locked,
                        onClick = {
                            if (locked) {
                                val msg = LocalizedStrings.stringFor(
                                    lang,
                                    "topic_manage_freestyle_toast",
                                    context,
                                )
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            } else {
                                onEditPrimary(key)
                            }
                        },
                    )
                }
            }
            Text(
                text = LocalizedStrings.get("topic_manage_footer_hint"),
                fontSize = 12.sp,
                color = chrome.subtitle,
                style = glassChromeTextStyle(TextStyle.Default, chrome.useLightChrome),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                lineHeight = 16.sp,
            )
        }
    }
}

@Composable
private fun TopicManagePrimaryCard(
    emoji: String,
    title: String,
    subtitle: String,
    locked: Boolean,
    onClick: () -> Unit,
) {
    val isGlass = LocalAppUiTheme.current == AppUiTheme.Glass
    val cardText = LocalGlassTextColors.current
    val fg = if (locked) {
        if (isGlass) cardText.muted else ClassicLockedFg
    } else {
        if (isGlass) cardText.primary else ClassicTitleColor
    }
    val subtitleColor = if (isGlass) cardText.muted else ClassicMuted

    val cardContent: @Composable () -> Unit = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (locked) {
                Text("🔒", fontSize = 20.sp)
                Spacer(Modifier.height(6.dp))
            } else {
                Text(emoji, fontSize = 28.sp, textAlign = TextAlign.Center, maxLines = 1)
                Spacer(Modifier.height(8.dp))
            }
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = fg,
                textAlign = TextAlign.Center,
                maxLines = 2,
                lineHeight = 16.sp,
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = subtitleColor,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
        }
    }

    if (isGlass) {
        MineCardShell(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick),
            shape = RoundedCornerShape(AppShapes.DenseInsetRadius),
        ) {
            cardContent()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(AppShapes.DenseInsetRadius))
                .background(ClassicCardBg)
                .clickable(onClick = onClick),
            content = { cardContent() },
        )
    }
}
