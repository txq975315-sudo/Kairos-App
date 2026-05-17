package com.example.kairosapplication.ui.glass

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.constants.GlassConstants
import com.example.kairosapplication.i18n.LocalizedStrings

enum class GlassAppTab(val icon: ImageVector) {
    Today(Icons.Default.CalendarToday),
    Essay(Icons.Default.Edit),
    View(Icons.Default.ViewList),
    Widget(Icons.Default.Widgets),
    Mine(Icons.Default.Person),
}

data class GlassBottomNavTab(
    val icon: ImageVector,
    val label: String,
    val contentDescription: String = label,
)

@Composable
fun glassMainNavTabs(): List<GlassBottomNavTab> = listOf(
    GlassBottomNavTab(
        icon = Icons.Default.CalendarToday,
        label = LocalizedStrings.get("nav_todo"),
        contentDescription = LocalizedStrings.get("cd_today"),
    ),
    GlassBottomNavTab(
        icon = Icons.Default.Edit,
        label = LocalizedStrings.get("nav_essay"),
        contentDescription = LocalizedStrings.get("cd_essay"),
    ),
    GlassBottomNavTab(
        icon = Icons.Default.ViewList,
        label = LocalizedStrings.get("nav_view"),
        contentDescription = LocalizedStrings.get("nav_view"),
    ),
    GlassBottomNavTab(
        icon = Icons.Default.Widgets,
        label = LocalizedStrings.get("nav_widget"),
        contentDescription = LocalizedStrings.get("cd_widget"),
    ),
    GlassBottomNavTab(
        icon = Icons.Default.Person,
        label = LocalizedStrings.get("nav_mine"),
        contentDescription = LocalizedStrings.get("cd_mine"),
    ),
)

@Composable
fun GlassBottomNav(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    tabs: List<GlassBottomNavTab>,
    modifier: Modifier = Modifier,
) {
    val barShape = RoundedCornerShape(
        topStart = GlassConstants.CornerRadius,
        topEnd = GlassConstants.CornerRadius,
        bottomStart = 0.dp,
        bottomEnd = 0.dp,
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .clip(barShape),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .blur(GlassConstants.BottomNavBlurRadius)
                .background(Color.White.copy(alpha = GlassConstants.BottomNavFillAlpha), barShape),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = GlassConstants.BottomNavPaddingHorizontal,
                    vertical = GlassConstants.BottomNavPaddingVertical,
                ),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            tabs.forEachIndexed { index, tab ->
                GlassNavItem(
                    tab = tab,
                    selected = index == selectedIndex,
                    onClick = { onTabSelected(index) },
                )
            }
        }
    }
}

@Composable
private fun GlassNavItem(
    tab: GlassBottomNavTab,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val textColor by animateColorAsState(
        targetValue = if (selected) GlassConstants.TextPrimary else GlassConstants.TextMuted,
        label = "navTextColor",
    )
    val chipShape = RoundedCornerShape(GlassConstants.CornerRadius)

    Column(
        modifier = Modifier
            .clip(chipShape)
            .clickable(onClick = onClick)
            .padding(horizontal = 4.dp, vertical = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (selected) {
            GlassSurface(
                shape = chipShape,
                fillAlpha = GlassConstants.BottomNavSelectedFillAlpha,
                blurRadius = GlassConstants.BottomNavSelectedBlurRadius,
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.contentDescription,
                        tint = textColor,
                        modifier = Modifier.size(GlassConstants.IconSize),
                    )
                    Text(
                        text = tab.label,
                        color = textColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = tab.icon,
                    contentDescription = tab.contentDescription,
                    tint = textColor,
                    modifier = Modifier.size(GlassConstants.IconSize),
                )
                Text(
                    text = tab.label,
                    color = textColor,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}
