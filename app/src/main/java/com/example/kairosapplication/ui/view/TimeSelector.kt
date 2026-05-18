package com.example.kairosapplication.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kairosapplication.i18n.LocalizedStrings

@Composable
fun TimeSelector(
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val labels = listOf(
        LocalizedStrings.get("view_tab_today"),
        LocalizedStrings.get("view_tab_week"),
        LocalizedStrings.get("view_tab_month"),
    )
    ChromeSegmentTabRow(
        labels = labels,
        selectedIndex = selectedIndex,
        onSelect = onSelect,
        modifier = modifier,
    )
}
