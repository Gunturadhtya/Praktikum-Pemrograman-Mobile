package com.mobil.modul3xml.ui.fragments.language

data class LanguageState(
    val selectedTag: String = "en",
    val languages: List<LanguageModel> = listOf(
        LanguageModel("English", "en", isSelected = true),
        LanguageModel("Indonesian", "id", isSelected = false)
    )
)