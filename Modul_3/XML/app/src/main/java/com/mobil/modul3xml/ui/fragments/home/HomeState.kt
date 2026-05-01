package com.mobil.modul3xml.ui.fragments.home

import com.mobil.modul3xml.data.CodeforcesProblem

data class HomeState(
    val problems: List<CodeforcesProblem> = emptyList()
)
