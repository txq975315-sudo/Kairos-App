package com.example.kairosapplication.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.i18n.LocalizedStrings

/**
 * Back control aligned with the main app chrome: thin chevron, no circular surface behind the icon.
 */
@Composable
fun CommonBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val cd = contentDescription ?: LocalizedStrings.get("back")
    Box(
        modifier = modifier
            .size(44.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = cd,
            tint = AppColors.BackIcon,
            modifier = Modifier.size(20.dp),
        )
    }
}
