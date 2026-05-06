package com.example.kairosapplication

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.kairosapplication.widget.WidgetClickHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class WidgetNavPayload(val target: String?, val taskId: Int = -1)

class MainWidgetIntentViewModel : ViewModel() {
    private val _widgetNav = MutableStateFlow(0 to WidgetNavPayload(null))
    val widgetNav: StateFlow<Pair<Int, WidgetNavPayload>> = _widgetNav.asStateFlow()

    fun resetFromCreate(intent: Intent) {
        _widgetNav.value = 0 to intent.toWidgetPayload()
    }

    fun pushFromNewIntent(intent: Intent) {
        val cur = _widgetNav.value
        _widgetNav.value = cur.first + 1 to intent.toWidgetPayload()
    }

    private fun Intent.toWidgetPayload(): WidgetNavPayload =
        WidgetNavPayload(
            target = getStringExtra(WidgetClickHandler.EXTRA_TARGET_PAGE),
            taskId = getIntExtra(WidgetClickHandler.EXTRA_TASK_ID, -1)
        )
}
