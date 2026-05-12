package com.example.kairosapplication.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppShapes
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.taskmodel.constants.TaskConstants
import com.example.taskmodel.model.Task

private fun weekdayKeyForRepeatSuffix(suffix: String): String = when (suffix) {
    "MON" -> "calendar_week_mon"
    "TUE" -> "calendar_week_tue"
    "WED" -> "calendar_week_wed"
    "THU" -> "calendar_week_thu"
    "FRI" -> "calendar_week_fri"
    "SAT" -> "calendar_week_sat"
    "SUN" -> "calendar_week_sun"
    else -> "calendar_week_mon"
}

private fun formatRepeatRuleLocalized(
    lang: LocalizationManager.Language,
    context: Context,
    repeatRule: String,
): String {
    val r = repeatRule.trim()
    if (r.isBlank() || r.uppercase() == TaskConstants.REPEAT_RULE_NONE) {
        return LocalizedStrings.stringFor(lang, "task_create_repeat_rule_none_short", context)
    }
    if (r.startsWith("WEEKLY_")) {
        val suffix = r.removePrefix("WEEKLY_")
        val weekday = LocalizedStrings.stringFor(lang, weekdayKeyForRepeatSuffix(suffix), context)
        return LocalizedStrings.stringFor(lang, "task_create_weekly_pattern", context, weekday)
    }
    return r
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailBottomSheet(
    task: Task,
    onDismiss: () -> Unit,
    onCompleteToday: () -> Unit,
    onCloseAll: () -> Unit,
    onStopRepeat: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = AppShapes.SheetTopRadius, topEnd = AppShapes.SheetTopRadius),
        dragHandle = null
    ) {
        CompositionLocalProvider(LocalCurrentLanguage provides LocalCurrentLanguage.current) {
            val lang = LocalCurrentLanguage.current.value
            val context = LocalContext.current
            val repeatText = formatRepeatRuleLocalized(lang, context, task.repeatRule)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = task.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (task.isCompleted) AppColors.HintText else Color(0xFF1A1A1A),
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null,
                )
                if (task.description.isNotBlank()) {
                    Text(
                        text = task.description,
                        fontSize = 14.sp,
                        color = Color(0xFF616161)
                    )
                }
                Text(
                    text = LocalizedStrings.stringFor(lang, "task_detail_repeat_label", context, repeatText),
                    fontSize = 13.sp,
                    color = Color(0xFF757575)
                )
                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    onClick = {
                        onCompleteToday()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(LocalizedStrings.stringFor(lang, "task_detail_complete_today", context))
                }
                Button(
                    onClick = { onCloseAll() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF455A64))
                ) {
                    Text(LocalizedStrings.stringFor(lang, "task_detail_close_all", context))
                }
                Button(
                    onClick = {
                        onStopRepeat()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD84315))
                ) {
                    Text(LocalizedStrings.stringFor(lang, "task_detail_stop_repeat", context))
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
