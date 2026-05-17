package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kairosapplication.core.ui.AppSpacing
import com.example.kairosapplication.core.ui.constants.GlassConstants

/** Frosted note row shell — same bubble recipe as [GlassTaskCard]. */
@Composable
fun GlassNoteCardShell(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val shape = RoundedCornerShape(GlassConstants.CornerRadius)
    GlassSurface(
        modifier = modifier.then(
            if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier,
        ),
        shape = shape,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = AppSpacing.CardHorizontal,
                    vertical = AppSpacing.CardVertical,
                ),
            content = content,
        )
    }
}
