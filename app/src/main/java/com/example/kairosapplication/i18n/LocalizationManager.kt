package com.example.kairosapplication.i18n

import com.example.kairosapplication.data.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LocalizationManager(
    private val dataStoreManager: DataStoreManager
) {
    private val _currentLanguage = MutableStateFlow(Language.ZH)
    val currentLanguage: StateFlow<Language> = _currentLanguage.asStateFlow()

    suspend fun setLanguage(language: Language) {
        dataStoreManager.setLanguage(language.code)
        _currentLanguage.value = language
        LocaleHelper.setLocale(language.code)
    }

    suspend fun loadLanguage() {
        val lang = Language.fromCode(dataStoreManager.getLanguageValue())
        _currentLanguage.value = lang
        LocaleHelper.setLocale(lang.code)
    }

    enum class Language(val code: String, val displayName: String) {
        ZH("zh", "简体中文"),
        EN("en", "English");

        companion object {
            fun fromCode(code: String): Language = entries.find { it.code == code } ?: ZH
        }
    }
}
