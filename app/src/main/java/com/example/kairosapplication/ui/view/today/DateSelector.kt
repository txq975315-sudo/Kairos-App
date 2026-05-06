package com.example.kairosapplication.ui.view.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.core.ui.AppScreenHeader
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.ui.view.viewClickable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun DateSelector(
    focusedDate: LocalDate,
    onPreviousDay: () -> Unit,
    onNextDay: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lang = LocalCurrentLanguage.current.value
    val formatter = remember(lang) {
        when (lang) {
            LocalizationManager.Language.ZH ->
                DateTimeFormatter.ofPattern("yyyy年M月d日", Locale.CHINA)
            LocalizationManager.Language.EN ->
                DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy", Locale.ENGLISH)
        }
    }
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = AppColors.CardBackground,
        shadowElevation = 2.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "◀",
                color = AppColors.IconNeutral,
                fontSize = 18.sp,
                modifier = Modifier
                    .viewClickable(onPreviousDay)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            )
            Text(
                text = focusedDate.format(formatter),
                color = AppScreenHeader.titleColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            )
            Text(
                text = "▶",
                color = AppColors.IconNeutral,
                fontSize = 18.sp,
                modifier = Modifier
                    .viewClickable(onNextDay)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            )
        }
    }
}
