package com.mobil.modul4compose.ui.screens.language

data class LanguageState(
    val selectedTag: String = "en",
    val languages: List<LanguageModel> = listOf(
        LanguageModel("English", "en"),
        LanguageModel("Indonesian", "id")
    )
)