package com.example.kairosapplication.ui.view.month

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable as foundationToggleable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.i18n.LocalizedStrings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthOverviewCustomizeSheet(
    selected: List<MonthOverviewMetric>,
    onDismiss: () -> Unit,
    onConfirm: (List<MonthOverviewMetric>) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var picked by remember { mutableStateOf(selected.toMutableSet()) }

    LaunchedEffect(selected) {
        picked = selected.toMutableSet()
    }

    fun toggle(metric: MonthOverviewMetric) {
        val next = picked.toMutableSet()
        if (metric in next) {
            if (next.size > 1) next.remove(metric)
        } else {
            next.add(metric)
        }
        picked = next
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            Text(
                text = LocalizedStrings.get("view_month_customize_title"),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppColors.PrimaryText,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = LocalizedStrings.get("view_month_customize_subtitle"),
                fontSize = 13.sp,
                color = AppColors.HintText,
            )
            Spacer(modifier = Modifier.height(16.dp))
            enumValues<MonthOverviewMetric>().forEach { metric ->
                val checked = metric in picked
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .foundationToggleable(
                            value = checked,
                            role = Role.Checkbox,
                            onValueChange = { toggle(metric) },
                        )
                        .padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(checked = checked, onCheckedChange = { toggle(metric) })
                    Text(
                        text = LocalizedStrings.get(metric.labelKey()),
                        fontSize = 15.sp,
                        color = AppColors.PrimaryText,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onConfirm(picked.toList()) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(LocalizedStrings.get("view_month_customize_done"))
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
