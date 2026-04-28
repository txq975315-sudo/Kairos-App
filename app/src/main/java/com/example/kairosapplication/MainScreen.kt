package com.example.kairosapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kairosapplication.ui.common.CommonBackButton
import com.example.kairosapplication.ui.theme.BackgroundColor
import com.example.kairosapplication.ui.theme.PrimaryTextColor
import com.example.kairosapplication.ui.theme.SecondaryTextColor

private enum class AppTab(val label: String, val icon: ImageVector) {
    Today("Today", Icons.Default.CalendarToday),
    Essay("Essay", Icons.Default.Edit),
    View("View", Icons.Default.ViewList),
    Widget("Widget", Icons.Default.Widgets),
    Mine("Mine", Icons.Default.Person)
}

private enum class Overlay { Create, DailyReview }

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(AppTab.Today) }
    var overlay by remember { mutableStateOf<Overlay?>(null) }
    val navController = rememberNavController()

    if (overlay != null) {
        AnimatedContent(
            targetState = overlay,
            transitionSpec = {
                slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
            },
            label = "overlay"
        ) { current ->
            when (current) {
                Overlay.Create -> CreateScreen(onBack = { overlay = null })
                Overlay.DailyReview -> DailyReviewScreen(onBack = { overlay = null })
                null -> Unit
            }
        }
        return
    }

    Scaffold(
        containerColor = BackgroundColor,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 0.dp
            ) {
                AppTab.entries.forEach { tab ->
                    val selected = selectedTab == tab
                    NavigationBarItem(
                        selected = selected,
                        onClick = { selectedTab = tab },
                        icon = {
                            Icon(imageVector = tab.icon, contentDescription = tab.label)
                        },
                        label = {
                            Text(
                                text = tab.label,
                                fontSize = 11.sp,
                                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryTextColor,
                            selectedTextColor = PrimaryTextColor,
                            unselectedIconColor = SecondaryTextColor,
                            unselectedTextColor = SecondaryTextColor,
                            indicatorColor = BackgroundColor
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            if (selectedTab == AppTab.Today) {
                NavHost(
                    navController = navController,
                    startDestination = "today"
                ) {
                    composable("today") {
                        TodayScreen(
                            onCreateClick = { overlay = Overlay.Create },
                            onDailyReviewClick = { overlay = Overlay.DailyReview },
                            onQuoteClick = { navController.navigate("quote_settings") }
                        )
                    }
                    composable("quote_settings") {
                        QuoteSettingScreen(onBack = { navController.popBackStack() })
                    }
                }
            } else {
                PlaceholderScreen(selectedTab.label)
            }
        }
    }
}

@Composable
private fun QuoteSettingScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .statusBarsPadding()
    ) {
        CommonBackButton(onClick = onBack)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Daily Sentence",
                fontSize = 22.sp,
                color = SecondaryTextColor
            )
        }
    }
}

@Composable
private fun PlaceholderScreen(name: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = 24.sp,
            color = SecondaryTextColor
        )
    }
}
