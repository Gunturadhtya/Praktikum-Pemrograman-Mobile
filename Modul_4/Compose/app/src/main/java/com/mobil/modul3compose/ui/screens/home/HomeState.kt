package com.mobil.modul3compose.ui.screens.home

import com.mobil.modul3compose.data.CodeforcesProblem

data class HomeState(
    val problems: List<CodeforcesProblem> = emptyList()
)
