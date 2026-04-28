package com.example.kairosapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmodel.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailBottomSheet(
    task: Task,
    onDismiss: () -> Unit,
    onCompleteToday: () -> Unit,
    onCloseAll: () -> Unit,
    onStopRepeat: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = task.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A1A)
            )
            if (task.description.isNotBlank()) {
                Text(
                    text = task.description,
                    fontSize = 14.sp,
                    color = Color(0xFF616161)
                )
            }
            Text(
                text = "重复规则：${formatRepeatRule(task.repeatRule)}",
                fontSize = 13.sp,
                color = Color(0xFF757575)
            )
            Spacer(modifier = Modifier.height(4.dp))

            Button(
                onClick = onCompleteToday,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("完成今天")
            }
            Button(
                onClick = onCloseAll,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF455A64))
            ) {
                Text("关闭全部")
            }
            Button(
                onClick = onStopRepeat,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD84315))
            ) {
                Text("停止重复")
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

private fun formatRepeatRule(repeatRule: String): String {
    return when {
        repeatRule.startsWith("WEEKLY_") -> {
            val day = repeatRule.removePrefix("WEEKLY_")
            "每周$day，持续4周"
        }
        repeatRule == "NONE" -> "不重复"
        repeatRule.isBlank() -> "不重复"
        else -> repeatRule
    }
}
