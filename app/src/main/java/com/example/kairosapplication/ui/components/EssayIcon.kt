package com.example.kairosapplication.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object EssayIcon {
    fun createImageVector(tint: Color = Color(0xFF666666)): ImageVector {
        return ImageVector.Builder(
            name = "EssayIcon",
            viewportWidth = 24f,
            viewportHeight = 24f,
            defaultWidth = TODO(),
            defaultHeight = TODO()
        ).apply {
            path(
                fill = null,
                fillAlpha = 1f,
                stroke = SolidColor(tint),
                strokeAlpha = 1f,
                strokeLineWidth = 2.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(10.55f, 3f)
                lineTo(10.55f, 3f)
                lineTo(3.391f, 4.39f)
                lineTo(2f, 5.783f)
                lineTo(2f, 12.5f)
                lineTo(2f, 16.978f)
                lineTo(2f, 19.217f)
                lineTo(3.391f, 20.609f)
                lineTo(4.783f, 22f)
                lineTo(11.501f, 22f)
                lineTo(15.979f, 22f)
                lineTo(18.218f, 22f)
                lineTo(19.609f, 20.609f)
                lineTo(20.899f, 19.319f)
                lineTo(20.993f, 17.302f)
                lineTo(21f, 13.449f)
            }
            path(
                fill = null,
                fillAlpha = 1f,
                stroke = SolidColor(tint),
                strokeAlpha = 1f,
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(11.056f, 13.0001f)
                lineTo(10.332f, 3.86615f)
                lineTo(16.802f, 1.27615f)
                lineTo(21.98f, 2.16415f)
                lineTo(22.189f, 5.19115f)
                lineTo(20.707f, 6.32415f)
                lineTo(17.887f, 6.84815f)
                lineTo(18.432f, 7.41815f)
                lineTo(19.394f, 8.13415f)
                lineTo(19.29f, 9.02815f)
                lineTo(19.216f, 9.66615f)
                lineTo(18.784f, 9.97815f)
                lineTo(17.918f, 10.6041f)
                lineTo(16.022f, 11.9741f)
                lineTo(13.826f, 12.8381f)
                lineTo(11.056f, 13.0001f)
            }
            path(
                fill = null,
                fillAlpha = 1f,
                stroke = SolidColor(tint),
                strokeAlpha = 1f,
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(9f, 17f)
                lineTo(11f, 11.5f)
                lineTo(12.96f, 9.636f)
                lineTo(15f, 8f)
            }
        }.build()
    }
}

@Composable
fun EssayNavigationIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color(0xFF666666),
    size: Dp = 24.dp
) {
    Icon(
        imageVector = EssayIcon.createImageVector(tint),
        contentDescription = "Essay",
        modifier = modifier.size(size),
        tint = tint
    )
}
