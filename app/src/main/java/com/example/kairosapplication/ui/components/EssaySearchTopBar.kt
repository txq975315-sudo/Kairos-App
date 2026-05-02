package com.example.kairosapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Essay 搜索顶栏：对齐 Figma「返回 + 渐变胶囊输入 + 右侧放大镜」布局。
 * 颜色与高度均可通过参数调整；输入区为真实文本框（非 SVG 轮廓字），便于无障碍与输入法。
 */
@Composable
fun EssaySearchTopBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    barHeight: Dp = 40.dp,
    gradientStart: Color = Color(0xFFA08DF8),
    gradientEnd: Color = Color(0xFFE2DEF8),
    capsuleGradientAlpha: Float = 0.35f,
    capsuleBorderColor: Color = Color.Black.copy(alpha = 0.12f),
    queryColor: Color = Color.White,
    placeholderColor: Color = Color.White.copy(alpha = 0.85f),
    placeholderText: String = "Search body or topic",
    searchWellColor: Color = Color(0xFF756767).copy(alpha = 0.1f),
    searchIconTint: Color = Color(0xFF1A1A1A),
    backIconTint: Color = Color(0xFF1A1A1A),
    onSearchImeClick: () -> Unit = {},
    onSearchButtonClick: () -> Unit = {},
    focusRequester: FocusRequester? = null,
) {
    val capsuleShape = RoundedCornerShape(barHeight / 2)
    val brush = Brush.horizontalGradient(
        colors = listOf(
            gradientStart.copy(alpha = capsuleGradientAlpha),
            gradientEnd.copy(alpha = capsuleGradientAlpha),
        )
    )
    val innerVerticalPad = 4.dp
    val innerRowHeight = barHeight - innerVerticalPad * 2

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(barHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            ArrowButton(
                onClick = onBackClick,
                direction = ArrowDirection.LEFT,
                size = 40.dp,
                tint = backIconTint,
                contentDescription = "Back"
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(barHeight)
                .clip(capsuleShape)
                .background(brush)
                .border(width = 1.dp, color = capsuleBorderColor, shape = capsuleShape)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 14.dp, vertical = innerVerticalPad),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    BasicTextField(
                        value = query,
                        onValueChange = onQueryChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .then(
                                if (focusRequester != null) Modifier.focusRequester(focusRequester)
                                else Modifier
                            ),
                        textStyle = LocalTextStyle.current.merge(
                            TextStyle(
                                color = queryColor,
                                fontSize = 15.sp,
                                lineHeight = 20.sp
                            )
                        ),
                        singleLine = true,
                        cursorBrush = SolidColor(queryColor),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = { onSearchImeClick() }),
                        decorationBox = { inner ->
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (query.isEmpty()) {
                                    Text(
                                        text = placeholderText,
                                        style = TextStyle(
                                            color = placeholderColor,
                                            fontSize = 15.sp,
                                            lineHeight = 20.sp
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                inner()
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .height(innerRowHeight)
                        .width(46.dp)
                        .clip(RoundedCornerShape(percent = 50))
                        .background(searchWellColor)
                        .clickable(onClick = onSearchButtonClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = searchIconTint,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
    }
}

/**
 * 搜索历史：横向标签；长按任一标签进入编辑模式，可点按删除移除记录。
 */
@Composable
fun EssaySearchHistorySection(
    history: List<String>,
    historyEditMode: Boolean,
    onHistoryEditModeChange: (Boolean) -> Unit,
    onSelectHistory: (String) -> Unit,
    onRemoveHistory: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (history.isEmpty() && !historyEditMode) return

    // 原 8dp 圆角，整体缩小约 0.6 后 ≈5dp
    val chipShape = RoundedCornerShape(5.dp)
    val scroll = rememberScrollState()

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "历史记录:",
                fontSize = 14.sp,
                color = Color(0xFF757575),
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(onLongPress = { onHistoryEditModeChange(true) })
                    }
            )
            if (historyEditMode) {
                Text(
                    text = "完成",
                    fontSize = 14.sp,
                    color = Color(0xFF8A7CF8),
                    modifier = Modifier.clickable { onHistoryEditModeChange(false) }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scroll),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            history.forEach { entry ->
                HistoryChip(
                    text = entry,
                    editMode = historyEditMode,
                    shape = chipShape,
                    onTap = { onSelectHistory(entry) },
                    onLongPress = { onHistoryEditModeChange(true) },
                    onDelete = { onRemoveHistory(entry) }
                )
            }
        }
    }
}

/**
 * Uiverse 风格；尺寸：先将上下内边距相对原 12dp 减半为 6dp，再整体按 [historyChipVisualScale]
 *（约 0.6，即缩小约 40%，落在缩小 1/3～1/2 之间）等比例缩小文字、圆角、图标与内边距。
 */
private const val historyChipVisualScale = 0.6f

@Composable
private fun HistoryChip(
    text: String,
    editMode: Boolean,
    shape: RoundedCornerShape,
    onTap: () -> Unit,
    onLongPress: () -> Unit,
    onDelete: () -> Unit,
) {
    val s = historyChipVisualScale
    // 原 12dp 垂直内边距先减半 → 6dp，再乘 s
    val padV = (6f * s).dp
    val padH = (16f * s).dp
    val gap = (8f * s).dp
    val fontSize = (14f * s).sp
    val lineHeight = (20f * s).sp
    val iconSize = (20f * s).dp
    val maxTextWidth = (160f * s).dp
    val shadowElev = (1f * s).dp.coerceAtLeast(0.5.dp)

    val borderColor = Color(0xFFD1D5DB) // rgb(209, 213, 219)
    val fg = Color(0xFF111827)
    Row(
        modifier = Modifier
            .shadow(
                elevation = shadowElev,
                shape = shape,
                ambientColor = Color.Black.copy(alpha = 0.05f),
                spotColor = Color.Black.copy(alpha = 0.05f),
            )
            .background(Color.White, shape)
            .border(1.dp, borderColor, shape)
            .then(
                if (!editMode) {
                    Modifier.pointerInput(text) {
                        detectTapGestures(
                            onTap = { onTap() },
                            onLongPress = { onLongPress() }
                        )
                    }
                } else {
                    Modifier
                }
            )
            .padding(horizontal = padH, vertical = padV),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(gap)
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = fg,
                fontSize = fontSize,
                lineHeight = lineHeight,
                fontWeight = FontWeight.SemiBold,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.widthIn(max = maxTextWidth)
        )
        if (editMode) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove",
                tint = fg,
                modifier = Modifier
                    .size(iconSize)
                    .clickable(onClick = onDelete)
            )
        }
    }
}
