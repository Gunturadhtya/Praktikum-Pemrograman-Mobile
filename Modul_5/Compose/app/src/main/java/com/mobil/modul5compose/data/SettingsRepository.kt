package com.mobil.modul5compose.data

import android.content.Context
import android.content.SharedPreferences
import com.mobil.modul5compose.ui.screens.language.LanguageModel
import timber.log.Timber
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsRepository(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val _languageTag = MutableStateFlow(getLanguageTag())
    val languageTag: StateFlow<String> = _languageTag.asStateFlow()

    fun getLanguageTag(): String {
        return prefs.getString(KEY_LANGUAGE, "en-US") ?: "en-US"
    }

    fun saveLanguageTag(tag: String) {
        Timber.d("Saving language tag to SharedPreferences: %s", tag)
        prefs.edit().putString(KEY_LANGUAGE, tag).apply()
        _languageTag.value = tag
    }

    fun getAvailableLanguages(): List<LanguageModel> {
        return defaultLanguages()
    }

    private fun defaultLanguages() = listOf(
        LanguageModel("English", "en-US"),
        LanguageModel("Bahasa Indonesia", "id-ID"),
        LanguageModel("Español", "es-ES"),
        LanguageModel("Français", "fr-FR"),
        LanguageModel("Deutsch", "de-DE")
    )

    companion object {
        private const val PREFS_NAME = "app_settings"
        private const val KEY_LANGUAGE = "language_tag"
    }
}