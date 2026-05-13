package com.mobil.modul4compose.ui.screens.detail

data class DetailState(
    val titleRes: Int = 0,
    val descRes: Int = 0,
    val imgRes: Int = 0,
    val code: String = "",
    val isNotFound: Boolean = false
)