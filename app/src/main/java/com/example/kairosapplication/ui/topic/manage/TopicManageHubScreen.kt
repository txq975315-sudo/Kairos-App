package com.example.kairosapplication.ui.topic.manage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.topic.EssayCategoryUi
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.model.EssayCategoryConfig
import com.example.taskmodel.viewmodel.TaskViewModel

private val CardBg = Color(0xFFFAFAFA)
private val TitleColor = Color(0xFF1A1A1A)
private val Muted = Color(0xFF9E9E9E)
private val LockedFg = Color(0xFF9E9E9E)

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

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .statusBarsPadding(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = LocalizedStrings.get("back"), tint = TitleColor)
            }
            Text(
                text = LocalizedStrings.get("topic_manage"),
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = TitleColor,
                modifier = Modifier.weight(1f),
            )
        }
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
            color = Muted,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            lineHeight = 16.sp,
        )
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
    val fg = if (locked) LockedFg else TitleColor
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(CardBg)
            .clickable(onClick = onClick)
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
            color = Muted,
            textAlign = TextAlign.Center,
            maxLines = 1,
        )
    }
}
