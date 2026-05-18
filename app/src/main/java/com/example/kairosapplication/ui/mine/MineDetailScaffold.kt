package com.example.kairosapplication.ui.mine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppUiTheme
import com.example.kairosapplication.core.ui.LocalAppUiTheme
import com.example.kairosapplication.ui.mine.settings.SettingsBackButton
import com.example.kairosapplication.ui.glass.GlassTextColors
import com.example.kairosapplication.ui.glass.LocalGlassAtmosphereUi
import com.example.kairosapplication.ui.glass.LocalGlassTextColors
import com.example.kairosapplication.ui.glass.glassChromeTextStyle
import androidx.compose.ui.graphics.Color
import com.example.kairosapplication.ui.mine.settings.rememberSettingsChrome

/**
 * Full-page mine L2 screens: Classic = solid white; Glass = transparent over atmosphere.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MineDetailScaffold(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val chrome = rememberSettingsChrome()
    val cardText = when (LocalAppUiTheme.current) {
        AppUiTheme.Glass -> LocalGlassAtmosphereUi.current.cardText
        AppUiTheme.Classic -> GlassTextColors.onLightBackground()
    }
    CompositionLocalProvider(LocalGlassTextColors provides cardText) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(chrome.pageBackground),
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        modifier = Modifier.statusBarsPadding(),
                        title = {
                            Text(
                                text = title,
                                color = chrome.title,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 17.sp,
                                style = glassChromeTextStyle(
                                    androidx.compose.ui.text.TextStyle.Default,
                                    chrome.useLightChrome,
                                ),
                            )
                        },
                        navigationIcon = { SettingsBackButton(onClick = onBack) },
                        actions = actions,
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = chrome.pageBackground,
                        ),
                    )
                },
                content = content,
            )
        }
    }
}
