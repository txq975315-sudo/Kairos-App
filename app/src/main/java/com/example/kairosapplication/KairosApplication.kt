package com.example.kairosapplication

import android.app.Application
import com.example.kairosapplication.i18n.LocaleHelper

class KairosApplication : Application() {

    override fun onCreate() {
        LocaleHelper.applyPersistedLocales(this)
        super.onCreate()
    }
}
