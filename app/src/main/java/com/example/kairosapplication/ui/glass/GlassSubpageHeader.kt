package com.example.kairosapplication.ui.glass

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.CommonBackButton

/** L2 subpage title row — chrome text over global atmosphere (Today-style). */
@Composable
fun GlassSubpageHeader(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    backContentDescription: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val chrome = LocalGlassAtmosphereUi.current.topChrome
    val useLightChrome = !LocalGlassAtmosphereUi.current.zones.topIsLight

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CommonBackButton(
            onClick = onBack,
            contentDescription = backContentDescription,
        )
        Text(
            text = title,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            color = chrome.primary,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
        )
        actions()
    }
}
