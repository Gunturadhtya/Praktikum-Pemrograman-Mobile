package com.mobil.modul5compose.ui.screens.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobil.modul5compose.data.SettingsRepository

class LanguageViewModelFactory(
    private val repository: SettingsRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LanguageViewModel::class.java)) {
            return LanguageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}