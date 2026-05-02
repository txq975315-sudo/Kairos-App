package com.example.kairosapplication.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object ViewIcon {
    fun createImageVector(tint: Color = Color(0xFF666666)): ImageVector {
        return ImageVector.Builder(
            name = "ViewIcon",
            defaultWidth = 24.dp,
            defaultHeight = 21.dp,
            viewportWidth = 24f,
            viewportHeight = 21f
        ).apply {
            path(
                fill = SolidColor(tint),
                pathData =
                    "M0 20.3077V0L6.54545 1.26923V19.0385L0 20.3077ZM8.72727 19.0385V1.26923H15.2727V19.0385H8.72727ZM24 20.3077L17.4545 19.0385V1.26923L24 0V20.3077ZM2.18182 17.2933L4.36364 16.8808V3.42692L2.18182 2.98269V17.2933ZM10.9091 16.5H13.0909V3.80769H10.9091V16.5ZM21.8182 17.325V2.98269L19.6364 3.42692V16.8808L21.8182 17.325Z"
            )
        }.build()
    }

    private fun ImageVector.Builder.path(
        fill: androidx.compose.ui.graphics.SolidColor,
        pathData: String
    ) {
    }
}

@Composable
fun ViewNavigationIcon(
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    Icon(
        imageVector = ViewIcon.createImageVector(Color.Black),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}
