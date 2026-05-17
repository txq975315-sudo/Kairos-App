package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.constants.GlassConstants

/** B 版式（引号 + 斜体居中）+ A 字色（深绿灰，绿底可读） */
@Composable
fun GlassQuoteSection(
    quote: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .alpha(if (isPressed) 0.85f else 1f)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClick,
                    )
                } else {
                    Modifier
                },
            )
            .padding(horizontal = 2.dp, vertical = 4.dp),
    ) {
        HorizontalDivider(color = GlassConstants.QuoteDivider, thickness = 1.dp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        ) {
            Icon(
                imageVector = Icons.Default.FormatQuote,
                contentDescription = null,
                tint = GlassConstants.QuoteAccent,
                modifier = Modifier.size(16.dp),
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "\"$quote\"",
                color = GlassConstants.QuoteText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = GlassConstants.QuoteDivider, thickness = 1.dp)
    }
}
