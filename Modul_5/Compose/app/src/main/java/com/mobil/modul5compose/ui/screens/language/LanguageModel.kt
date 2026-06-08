package com.mobil.modul5compose.ui.screens.language

import kotlinx.serialization.Serializable

@Serializable
data class LanguageModel(
    val name: String,
    val tag: String
)
