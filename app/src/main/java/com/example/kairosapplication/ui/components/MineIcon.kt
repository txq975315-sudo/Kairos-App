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

object MineIcon {
    fun createImageVector(tint: Color = Color(0xFF666666)): ImageVector {
        return ImageVector.Builder(
            name = "MineIcon",
            viewportWidth = 23f,
            viewportHeight = 23f,
            defaultWidth = TODO(),
            defaultHeight = TODO()
        ).apply {
            path(
                fill = null,
                fillAlpha = 1f,
                stroke = SolidColor(tint),
                strokeAlpha = 1f,
                strokeLineWidth = 1.8f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(11.3995f, 13.4724f)
                lineTo(11.3995f, 13.4724f)
                lineTo(14.214f, 12.3065f)
                lineTo(14.9605f, 11.5601f)
                lineTo(15.3798f, 10.5476f)
                lineTo(15.3798f, 9.49192f)
                lineTo(15.3798f, 8.43623f)
                lineTo(14.9605f, 7.42378f)
                lineTo(14.214f, 6.6773f)
                lineTo(13.4675f, 5.93082f)
                lineTo(12.4551f, 5.51145f)
                lineTo(11.3995f, 5.51145f)
                lineTo(10.3438f, 5.51145f)
                lineTo(9.33138f, 5.93082f)
                lineTo(8.58492f, 6.6773f)
                lineTo(7.83846f, 7.42378f)
                lineTo(7.41911f, 8.43623f)
                lineTo(7.41911f, 9.49192f)
                lineTo(7.41911f, 10.5476f)
                lineTo(7.83846f, 11.5601f)
                lineTo(8.58492f, 12.3065f)
                lineTo(9.33138f, 13.053f)
                lineTo(10.3438f, 13.4724f)
                lineTo(11.3995f, 13.4724f)
                close()
            }
            path(
                fill = null,
                fillAlpha = 1f,
                stroke = SolidColor(tint),
                strokeAlpha = 1f,
                strokeLineWidth = 1.8f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(11.3995f, 13.4724f)
                lineTo(11.3995f, 13.4725f)
                lineTo(13.0591f, 14.0786f)
                lineTo(14.6615f, 14.0786f)
                lineTo(15.9058f, 15.1768f)
                lineTo(17.1501f, 16.275f)
                lineTo(17.9506f, 17.7898f)
                lineTo(18.1569f, 19.4366f)
            }
            path(
                fill = null,
                fillAlpha = 1f,
                stroke = SolidColor(tint),
                strokeAlpha = 1f,
                strokeLineWidth = 1.8f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(16.2671f, 21.0319f)
                lineTo(13.8726f, 21.9048f)
                lineTo(11.3995f, 21.8999f)
                lineTo(8.92636f, 21.9048f)
                lineTo(6.53176f, 21.0319f)
            }
            path(
                fill = null,
                fillAlpha = 1f,
                stroke = SolidColor(tint),
                strokeAlpha = 1f,
                strokeLineWidth = 1.8f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(11.3995f, 13.4724f)
                lineTo(11.3995f, 13.4725f)
                lineTo(9.73986f, 14.0786f)
                lineTo(8.13741f, 14.0786f)
                lineTo(6.89313f, 15.1768f)
                lineTo(5.64884f, 16.275f)
                lineTo(4.84834f, 17.7898f)
                lineTo(4.642f, 19.4366f)
            }
            path(
                fill = null,
                fillAlpha = 1f,
                stroke = SolidColor(tint),
                strokeAlpha = 1f,
                strokeLineWidth = 1.8f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(4.642f, 19.4366f)
                lineTo(2.99358f, 18.0503f)
                lineTo(1.81134f, 16.1906f)
                lineTo(1.25559f, 14.1096f)
                lineTo(0.699835f, 12.0287f)
                lineTo(0.797455f, 9.82712f)
                lineTo(1.53521f, 7.80352f)
                lineTo(2.27297f, 5.77993f)
                lineTo(3.61516f, 4.03217f)
                lineTo(5.37981f, 2.79722f)
                lineTo(7.14447f, 1.56228f)
                lineTo(9.24619f, 0.899902f)
                lineTo(11.4f, 0.899902f)
                lineTo(13.5539f, 0.899902f)
                lineTo(15.6556f, 1.56228f)
                lineTo(17.4202f, 2.79722f)
                lineTo(19.1849f, 4.03217f)
                lineTo(20.5271f, 5.77993f)
                lineTo(21.2648f, 7.80352f)
                lineTo(22.0026f, 9.82712f)
                lineTo(22.1002f, 12.0287f)
                lineTo(21.5445f, 14.1096f)
                lineTo(20.9887f, 16.1906f)
                lineTo(19.8065f, 18.0503f)
                lineTo(18.1581f, 19.4366f)
            }
        }.build()
    }
}

@Composable
fun MineNavigationIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color(0xFF666666),
    size: Dp = 24.dp
) {
    Icon(
        imageVector = MineIcon.createImageVector(tint),
        contentDescription = "Mine",
        modifier = modifier.size(size),
        tint = tint
    )
}
