package com.example.kairosapplication.ui.glass

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.kairosapplication.core.ui.AppShapes
import com.example.kairosapplication.core.ui.constants.GlassConstants

private val PickerSelectedBackground = Color.White.copy(alpha = 0.18f)
private val PickerSelectedText = Color.White

@Composable
fun GlassPickerDialog(
    title: String,
    onDismiss: () -> Unit,
    chromePrimary: Color,
    useLightChrome: Boolean,
    content: @Composable ColumnScope.() -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false,
        ),
    ) {
        val dialogView = LocalView.current
        SideEffect {
            val window = (dialogView.parent as? DialogWindowProvider)?.window
            window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            // Single dim layer on the window — avoid stacking with an in-compose scrim (was ~2× dark).
            window?.setDimAmount(GlassConstants.PickerDialogScrimAlpha)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onDismiss),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 400.dp)
                    .fillMaxWidth(0.9f)
                    .wrapContentHeight()
                    .heightIn(max = 520.dp)
                    .clickable(enabled = false, onClick = {}),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                GlassSurface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(AppShapes.CardRadius),
                    fillAlpha = GlassConstants.PickerDialogFillAlpha,
                    wrapHazeToContent = true,
                    skipHazeBackdrop = true,
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color(0xFF1E1E22))
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                    ) {
                        Text(
                            text = title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
                            color = chromePrimary,
                            modifier = Modifier.padding(bottom = 8.dp),
                        )
                        content()
                    }
                }
            }
        }
    }
}

@Composable
fun GlassPickerOptionRow(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    chromePrimary: Color,
    useLightChrome: Boolean,
) {
    val shape = RoundedCornerShape(10.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(
                color = if (selected) PickerSelectedBackground else Color.Transparent,
                shape = shape,
            )
            .padding(vertical = 10.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = if (selected) PickerSelectedText else chromePrimary,
            style = glassChromeTextStyle(TextStyle.Default, useLightChrome),
        )
    }
}
