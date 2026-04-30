package com.mobil.modul3compose.ui.screens.language

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LanguageViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LanguageState())
    val uiState: StateFlow<LanguageState> = _uiState.asStateFlow()

    init {
        loadCurrentLocale()
    }

    private fun loadCurrentLocale() {
        val locales = AppCompatDelegate.getApplicationLocales()
        val tag = if (!locales.isEmpty) locales[0]?.language ?: "en" else "en"
        _uiState.update { it.copy(selectedTag = tag) }
    }

    fun onLanguageSelected(tag: String) {
        val appLocale = LocaleListCompat.forLanguageTags(tag)
        AppCompatDelegate.setApplicationLocales(appLocale)
        _uiState.update { it.copy(selectedTag = tag) }
    }
}