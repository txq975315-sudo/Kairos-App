package com.example.kairosapplication.i18n

import android.content.Context
import android.content.res.Configuration
import android.os.LocaleList
import java.util.Locale

/**
 * Resolves `i18n_*` string resources using the app language from [LocalizationManager],
 * independent of the device system locale.
 */
object I18nResources {

    fun applyAppLocale(base: Context, language: LocalizationManager.Language): Context {
        val conf = Configuration(base.resources.configuration)
        val locales = when (language) {
            LocalizationManager.Language.ZH -> LocaleList.forLanguageTags("zh-Hans-CN")
            LocalizationManager.Language.EN -> LocaleList(Locale.ENGLISH)
        }
        conf.setLocales(locales)
        return base.createConfigurationContext(conf)
    }

    /**
     * Loads `i18n_<key>` from resources. Returns [key] if the resource id is missing.
     */
    fun stringForKey(
        base: Context,
        language: LocalizationManager.Language,
        key: String,
        vararg formatArgs: Any,
    ): String {
        val app = base.applicationContext
        val resId = app.resources.getIdentifier("i18n_$key", "string", app.packageName)
        if (resId == 0) return key
        val localized = applyAppLocale(app, language)
        @Suppress("SpreadOperator")
        return if (formatArgs.isEmpty()) localized.getString(resId)
        else localized.getString(resId, *formatArgs)
    }
}
