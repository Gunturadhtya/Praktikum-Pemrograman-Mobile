package com.mobil.modul5compose.ui.screens.language

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobil.modul5compose.data.SettingsRepository
import timber.log.Timber
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LanguageViewModel(
    private val repository: SettingsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LanguageState(languages = repository.getAvailableLanguages()))
    val uiState: StateFlow<LanguageState> = _uiState.asStateFlow()

    init {
        observeLanguageChanges()
    }

    private fun observeLanguageChanges() {
        viewModelScope.launch {
            repository.languageTag.collect { tag ->
                Timber.d("LanguageViewModel: Observed language change in repository: %s", tag)
                _uiState.update { it.copy(selectedTag = tag) }
            }
        }
    }

    fun onLanguageSelected(tag: String) {
        Timber.d("LanguageViewModel: User selected tag: %s", tag)
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(tag))
        repository.saveLanguageTag(tag)

        _uiState.update { it.copy(navigateBackEvent = true) }
    }

    fun onNavigateBackHandled() {
        _uiState.update { it.copy(navigateBackEvent = false) }
    }
}
