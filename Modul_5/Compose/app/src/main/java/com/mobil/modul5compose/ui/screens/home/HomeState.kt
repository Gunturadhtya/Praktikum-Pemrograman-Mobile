package com.mobil.modul5compose.ui.screens.home

import com.mobil.modul5compose.data.CodeforcesProblem

data class HomeState(
    val problems: List<CodeforcesProblem> = emptyList(),
    val navigateToDetailEvent: String? = null,
    val openExternalUrlEvent: String? = null
)
