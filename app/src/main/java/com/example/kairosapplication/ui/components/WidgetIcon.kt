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

object WidgetIcon {
    fun createImageVector(tint: Color = Color(0xFF666666)): ImageVector {
        return ImageVector.Builder(
            name = "WidgetIcon",
            viewportWidth = 20f,
            viewportHeight = 23f,
            defaultWidth = TODO(),
            defaultHeight = TODO()
        ).apply {
            path(
                fill = null,
                fillAlpha = 1f,
                stroke = SolidColor(tint),
                strokeAlpha = 1f,
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(9.872f, 21.7501f)
                lineTo(0.75f, 16.4836f)
                verticalLineTo(6.0166f)
                lineTo(9.872f, 11.2831f)
                verticalLineTo(21.7501f)
                close()
            }
            path(
                fill = null,
                fillAlpha = 1f,
                stroke = SolidColor(tint),
                strokeAlpha = 1f,
                strokeLineWidth = 1f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(8.732f, 10.6246f)
                lineTo(7.5915f, 9.9666f)
                lineTo(6.4515f, 9.3081f)
                lineTo(5.311f, 8.6496f)
                lineTo(4.171f, 7.9916f)
                lineTo(3.0305f, 7.3331f)
                lineTo(1.8905f, 6.6751f)
                lineTo(0.75f, 6.0166f)
                verticalLineTo(9.9416f)
                lineTo(1.8905f, 10.6001f)
                verticalLineTo(11.9086f)
                lineTo(3.0305f, 12.5666f)
                verticalLineTo(9.9501f)
                lineTo(4.171f, 10.6081f)
                verticalLineTo(9.2996f)
                lineTo(5.311f, 9.9581f)
                verticalLineTo(12.5751f)
                lineTo(6.4515f, 13.2331f)
                verticalLineTo(11.9246f)
                lineTo(7.5915f, 12.5831f)
                verticalLineTo(13.8916f)
                lineTo(8.732f, 14.5496f)
                verticalLineTo(13.2416f)
                lineTo(9.872f, 13.8996f)
                verticalLineTo(11.2831f)
                lineTo(8.732f, 10.6246f)
                close()
            }
            path(
                fill = null,
                fillAlpha = 1f,
                stroke = SolidColor(tint),
                strokeAlpha = 1f,
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(9.872f, 11.283f)
                lineTo(0.75f, 6.0165f)
                lineTo(9.872f, 0.75f)
                lineTo(18.9935f, 6.0165f)
                lineTo(9.872f, 11.283f)
                close()
            }
            path(
                fill = null,
                fillAlpha = 1f,
                stroke = SolidColor(tint),
                strokeAlpha = 1f,
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(18.9934f, 16.4836f)
                lineTo(9.87195f, 21.7501f)
                verticalLineTo(11.2831f)
                lineTo(18.9934f, 6.0166f)
                verticalLineTo(16.4836f)
                close()
            }
            path(
                fill = null,
                fillAlpha = 1f,
                stroke = SolidColor(tint),
                strokeAlpha = 1f,
                strokeLineWidth = 1f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(17.8534f, 6.6751f)
                lineTo(16.7134f, 7.3331f)
                lineTo(15.5729f, 7.9916f)
                lineTo(14.4329f, 8.6496f)
                lineTo(13.2924f, 9.3081f)
                lineTo(12.1524f, 9.9666f)
                lineTo(11.0119f, 10.6246f)
                lineTo(9.87195f, 11.2831f)
                verticalLineTo(15.2081f)
                lineTo(11.0119f, 14.5496f)
                verticalLineTo(15.8581f)
                lineTo(12.1524f, 15.2001f)
                verticalLineTo(12.5831f)
                lineTo(13.2924f, 11.9246f)
                verticalLineTo(10.6166f)
                lineTo(14.4329f, 9.9581f)
                verticalLineTo(12.5751f)
                lineTo(15.5729f, 11.9166f)
                verticalLineTo(10.6081f)
                lineTo(16.7134f, 9.9501f)
                verticalLineTo(11.2581f)
                lineTo(17.8534f, 10.6001f)
                verticalLineTo(9.2916f)
                lineTo(18.9934f, 8.6331f)
                verticalLineTo(6.0166f)
                lineTo(17.8534f, 6.6751f)
                close()
            }
        }.build()
    }
}

@Composable
fun WidgetNavigationIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color(0xFF666666),
    size: Dp = 24.dp
) {
    Icon(
        imageVector = WidgetIcon.createImageVector(tint),
        contentDescription = "Widget",
        modifier = modifier.size(size),
        tint = tint
    )
}
