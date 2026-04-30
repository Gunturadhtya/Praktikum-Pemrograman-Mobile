package com.mobil.modul3xml.ui.screens.home

import com.mobil.modul3xml.data.CodeforcesProblem

data class HomeState(
    val problems: List<CodeforcesProblem> = emptyList()
)
