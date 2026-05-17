package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** 双引号 + 斜体；字色随壁纸上区亮暗自适应（无 card）。 */
@Composable
fun GlassQuoteSection(
    quote: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    val atmosphere = LocalGlassAtmosphereUi.current
    val colors = atmosphere.topChrome
    val useLightText = !atmosphere.zones.topIsLight
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val quoteStyle = glassChromeTextStyle(
        base = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Italic,
            lineHeight = 22.sp,
        ),
        useLightText = useLightText,
    )

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
        HorizontalDivider(color = atmosphere.quoteDividerColor(), thickness = 1.dp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "\"$quote\"",
            color = colors.primary,
            style = quoteStyle,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = atmosphere.quoteDividerColor(), thickness = 1.dp)
    }
}
