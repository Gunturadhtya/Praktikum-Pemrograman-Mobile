package com.mobil.modul4xml.ui.fragments.home

import com.mobil.modul4xml.data.CodeforcesProblem

data class HomeState(
    val problems: List<CodeforcesProblem> = emptyList(),
    val navigateToDetailEvent: String? = null,
    val openExternalUrlEvent: String? = null
)
