package com.mobil.modul5compose.ui.screens.language

data class LanguageState(
    val selectedTag: String = "en-US",
    val languages: List<LanguageModel> = emptyList(),
    val navigateBackEvent: Boolean = false
)