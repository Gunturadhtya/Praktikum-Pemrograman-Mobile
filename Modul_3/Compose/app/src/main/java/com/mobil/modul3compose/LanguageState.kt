package com.mobil.modul3compose

data class LanguageState(
    val selectedTag: String = "en",
    val languages: List<LanguageModel> = listOf(
        LanguageModel("English", "en"),
        LanguageModel("Indonesian", "id")
    )
)