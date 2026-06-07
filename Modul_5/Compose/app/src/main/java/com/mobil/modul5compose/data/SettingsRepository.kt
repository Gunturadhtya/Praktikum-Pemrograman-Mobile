package com.mobil.modul5compose.data

import android.content.Context
import android.content.SharedPreferences

class SettingsRepository(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getLanguageTag(): String {
        return prefs.getString(KEY_LANGUAGE, "en") ?: "en"
    }

    fun saveLanguageTag(tag: String) {
        prefs.edit().putString(KEY_LANGUAGE, tag).apply()
    }

    companion object {
        private const val PREFS_NAME = "app_settings"
        private const val KEY_LANGUAGE = "language_tag"
    }
}