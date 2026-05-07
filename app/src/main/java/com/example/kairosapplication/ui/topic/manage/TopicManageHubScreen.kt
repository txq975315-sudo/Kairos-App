package com.example.kairosapplication.ui.topic.manage

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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.topic.EssayCategoryUi
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.taskmodel.model.EssayCategoryConfig
import com.example.taskmodel.viewmodel.TaskViewModel

private val CardBg = Color.White
private val TitleColor = Color(0xFF1A1A1A)
private val Muted = Color(0xFF9E9E9E)
private val LockedBg = Color(0xFFF5F5F5)

@Composable
fun TopicManageHubScreen(
    taskViewModel: TaskViewModel,
    onBack: () -> Unit,
    onEditPrimary: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
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
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(keys, key = { it }) { key ->
                val locked = key == NotePrimaryCategory.FREESTYLE
                val name = EssayCategoryUi.primaryDisplayName(key, config, lang)
                val emoji = EssayCategoryUi.primaryEmoji(key, config)
                val secCount = config.primaryOrNull(key)?.secondaries?.size ?: 0
                TopicManagePrimaryCard(
                    emoji = emoji,
                    title = name,
                    subtitle = if (locked) LocalizedStrings.get("topic_manage_locked_badge") else "$secCount/8",
                    locked = locked,
                    onClick = {
                        if (!locked) onEditPrimary(key)
                    },
                )
            }
        }
        Text(
            text = LocalizedStrings.get("topic_manage_footer_hint"),
            fontSize = 12.sp,
            color = Muted,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
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
    val bg = if (locked) LockedBg else CardBg
    val fg = if (locked) Muted else TitleColor
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(14.dp))
            .background(bg)
            .clickable(enabled = !locked, onClick = onClick)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (locked) {
            Text("🔒", fontSize = 18.sp)
            Spacer(Modifier.height(4.dp))
        } else {
            Text(emoji, fontSize = 26.sp, textAlign = TextAlign.Center, maxLines = 1)
            Spacer(Modifier.height(6.dp))
        }
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = fg,
            textAlign = TextAlign.Center,
            maxLines = 2,
            lineHeight = 14.sp,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = subtitle,
            fontSize = 10.sp,
            color = Muted,
            textAlign = TextAlign.Center,
            maxLines = 1,
        )
    }
}
