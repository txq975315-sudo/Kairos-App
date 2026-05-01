package com.example.kairosapplication.ui.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import android.graphics.Bitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.graphics.BitmapFactory
import com.example.kairosapplication.core.ui.AppColors
import com.example.taskmodel.constants.EssayConstants
import com.example.taskmodel.model.Essay
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun EssayItemCard(
    essay: Essay,
    timelineDay: Int,
    modifier: Modifier = Modifier,
    showTimelineRail: Boolean = true,
    /** 非空时替代课题标题行（可含 Draft 后缀） */
    topicLineOverride: String? = null,
    /** 非空时替代底栏左侧标签 */
    footerTagOverride: String? = null,
    /** 非空时替代正文为空时的占位 */
    emptyBodyPlaceholder: String? = null
) {
    val zone = ZoneId.systemDefault()
    val created = remember(essay.createdAtMillis) {
        Instant.ofEpochMilli(essay.createdAtMillis).atZone(zone)
    }
    val headerDate = remember(created) {
        val fmt = DateTimeFormatter.ofPattern("MMMM / EEEE yyyy", Locale.ENGLISH)
        created.format(fmt)
    }
    val timeStr = remember(created) {
        DateTimeFormatter.ofPattern("HH:mm").format(created)
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        if (showTimelineRail) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(36.dp)
            ) {
                Text(
                    text = timelineDay.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A1A)
                )
                Spacer(Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(120.dp)
                        .background(Color(0xFFE0E0E0))
                )
            }
            Spacer(Modifier.width(8.dp))
        }
        Card(
            modifier = Modifier
                .weight(1f)
                .shadow(4.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = headerDate,
                        fontSize = 12.sp,
                        color = AppColors.SecondaryText,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = timeStr,
                        fontSize = 12.sp,
                        color = AppColors.SecondaryText
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = topicLineOverride ?: (
                        EssayConstants.topicLabel(essay.topic) +
                            if (essay.isDraft) " · 草稿" else ""
                        ),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A1A)
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = essay.body.ifBlank { emptyBodyPlaceholder ?: "（无正文）" },
                    fontSize = 14.sp,
                    color = AppColors.PrimaryText,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 20.sp
                )
                if (essay.imageUris.isNotEmpty()) {
                    Spacer(Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        essay.imageUris.take(4).forEach { uri ->
                            EssayImageThumb(uri = uri, modifier = Modifier.size(56.dp))
                        }
                    }
                }
                Spacer(Modifier.height(12.dp))
                HorizontalDivider(color = Color(0xFFF0F0F0))
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = footerTagOverride ?: essay.tags.firstOrNull()
                            ?: EssayConstants.topicLabel(essay.topic),
                        fontSize = 12.sp,
                        color = AppColors.SecondaryText
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ChatBubbleOutline,
                            contentDescription = null,
                            tint = AppColors.IconNeutral,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "+${essay.imageUris.size.coerceAtLeast(0)}",
                            fontSize = 12.sp,
                            color = AppColors.SecondaryText
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EssayImageThumb(uri: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var bitmap by remember(uri) { mutableStateOf<Bitmap?>(null) }
    LaunchedEffect(uri) {
        bitmap = withContext(Dispatchers.IO) {
            runCatching {
                context.contentResolver.openInputStream(Uri.parse(uri))?.use { stream ->
                    BitmapFactory.decodeStream(stream)
                }
            }.getOrNull()
        }
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
    ) {
        val bmp = bitmap
        if (bmp != null) {
            Image(
                bitmap = bmp.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
