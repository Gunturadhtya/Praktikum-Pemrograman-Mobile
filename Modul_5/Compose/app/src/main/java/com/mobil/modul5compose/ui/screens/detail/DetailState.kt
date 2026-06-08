package com.mobil.modul5compose.ui.screens.detail
import com.mobil.modul5compose.data.local.MovieEntity

data class DetailState(
    val movie: MovieEntity? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)