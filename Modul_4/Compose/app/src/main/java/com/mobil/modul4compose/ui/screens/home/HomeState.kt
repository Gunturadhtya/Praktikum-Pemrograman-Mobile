package com.mobil.modul4compose.ui.screens.home

import com.mobil.modul4compose.data.CodeforcesProblem

data class HomeState(
    val problems: List<CodeforcesProblem> = emptyList(),
    val navigateToDetailEvent: String? = null,
    val openExternalUrlEvent: String? = null
)
