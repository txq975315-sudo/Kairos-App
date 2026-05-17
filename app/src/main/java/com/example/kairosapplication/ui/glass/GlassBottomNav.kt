package com.example.kairosapplication.ui.glass

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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

/**
 * Unified bottom dock: frosted plate (route C) + top hairline + fixed-size glass chip on selection.
 */
@Composable
fun GlassBottomNav(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    tabs: List<GlassBottomNavTab>,
    modifier: Modifier = Modifier,
) {
    val chrome = LocalGlassAtmosphereUi.current.bottomChrome
    val flatFrost = GlassConstants.usesBackdropBlur
    val topLineColor = Color.White.copy(alpha = GlassConstants.BottomNavTopBorderAlpha)

    Column(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (flatFrost) {
                        Modifier.glassBottomNavDock()
                    } else {
                        Modifier
                            .shadow(
                                elevation = GlassConstants.GlassShadowElevation,
                                shape = RectangleShape,
                                ambientColor = Color.Black.copy(alpha = GlassConstants.ShadowAmbientAlpha),
                                spotColor = Color.Black.copy(alpha = GlassConstants.ShadowSpotAlpha),
                            )
                            .glassBottomNavDock()
                    },
                ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(GlassConstants.GlassBorderWidth)
                    .background(topLineColor),
            )

            if (flatFrost) {
                Spacer(Modifier.height(GlassConstants.BottomNavDockTopExtension))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(GlassConstants.BottomNavTabSlotHeight)
                    .padding(horizontal = GlassConstants.BottomNavPaddingHorizontal),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                tabs.forEachIndexed { index, tab ->
                    GlassNavItem(
                        tab = tab,
                        selected = index == selectedIndex,
                        chrome = chrome,
                        onClick = { onTabSelected(index) },
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }

        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
    }
}

@Composable
private fun GlassNavItem(
    tab: GlassBottomNavTab,
    selected: Boolean,
    chrome: GlassTextColors,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val textColor by animateColorAsState(
        targetValue = if (selected) chrome.primary else chrome.muted,
        label = "navTextColor",
    )
    val iconColor by animateColorAsState(
        targetValue = if (selected) chrome.primary else chrome.muted,
        label = "navIconColor",
    )
    val chipShape = RoundedCornerShape(GlassConstants.CornerRadius)
    val cardText = LocalGlassTextColors.current

    Box(
        modifier = modifier
            .height(GlassConstants.BottomNavTabSlotHeight)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        if (selected) {
            GlassSurface(
                modifier = Modifier.size(
                    width = GlassConstants.BottomNavSelectedChipWidth,
                    height = GlassConstants.BottomNavSelectedChipHeight,
                ),
                shape = chipShape,
                fillAlpha = GlassConstants.BottomNavSelectedFillAlpha,
                wrapHazeToContent = true,
            ) {
                Column(
                    modifier = Modifier
                        .width(GlassConstants.BottomNavSelectedChipWidth)
                        .padding(vertical = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.contentDescription,
                        tint = cardText.primary,
                        modifier = Modifier.size(GlassConstants.IconSize),
                    )
                    Text(
                        text = tab.label,
                        color = cardText.primary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 4.dp),
                    )
                }
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    imageVector = tab.icon,
                    contentDescription = tab.contentDescription,
                    tint = iconColor,
                    modifier = Modifier.size(GlassConstants.IconSize),
                )
                Text(
                    text = tab.label,
                    color = textColor,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 2.dp),
                )
            }
        }
    }
}
