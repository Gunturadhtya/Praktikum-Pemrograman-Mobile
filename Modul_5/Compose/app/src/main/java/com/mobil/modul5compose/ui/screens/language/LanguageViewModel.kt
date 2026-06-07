package com.mobil.modul5compose.ui.screens.language

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import com.mobil.modul5compose.data.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LanguageViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LanguageState())
    val uiState: StateFlow<LanguageState> = _uiState.asStateFlow()

    init {
        loadSavedLocale()
    }

    private fun loadSavedLocale() {
        val savedTag = settingsRepository.getLanguageTag()
        val appLocales = LocaleListCompat.forLanguageTags(savedTag)
        AppCompatDelegate.setApplicationLocales(appLocales)

        _uiState.update { it.copy(selectedTag = savedTag) }
    }

    fun onLanguageSelected(tag: String) {
        settingsRepository.saveLanguageTag(tag)

        val appLocale = LocaleListCompat.forLanguageTags(tag)
        AppCompatDelegate.setApplicationLocales(appLocale)
        _uiState.update { it.copy(selectedTag = tag) }
    }
}