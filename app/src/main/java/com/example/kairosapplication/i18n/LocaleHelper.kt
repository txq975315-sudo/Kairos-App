package com.example.kairosapplication.i18n

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.kairosapplication.data.DataStoreManager

object LocaleHelper {

    fun languageTagForCode(languageCode: String): String =
        when (languageCode) {
            "en" -> "en"
            else -> "zh-Hans-CN"
        }

    fun localeListCompatForCode(languageCode: String): LocaleListCompat =
        LocaleListCompat.forLanguageTags(languageTagForCode(languageCode))

    /** Sync persisted preference to AppCompat application locales (framework `stringResource`, etc.). */
    fun applyPersistedLocales(application: Application) {
        val code = runCatching { DataStoreManager(application).getLanguageSync() }.getOrElse { "zh" }
        setLocale(code)
    }

    fun setLocale(languageCode: String) {
        AppCompatDelegate.setApplicationLocales(localeListCompatForCode(languageCode))
    }

    /**
     * Applies persisted language to [base] before the activity attaches resources.
     * Use from [android.app.Activity.attachBaseContext].
     */
    fun wrap(base: Context): Context {
        val code = runCatching { DataStoreManager(base.applicationContext).getLanguageSync() }.getOrElse { "zh" }
        val config = Configuration(base.resources.configuration)
        config.setLocales(LocaleList.forLanguageTags(languageTagForCode(code)))
        return base.createConfigurationContext(config)
    }
}

tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
