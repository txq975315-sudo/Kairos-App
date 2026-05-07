package com.example.kairosapplication

import android.content.Context
import androidx.activity.ComponentActivity
import com.example.kairosapplication.i18n.LocaleHelper

open class BaseLocaleActivity : ComponentActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.wrap(newBase))
    }
}
