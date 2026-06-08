package com.mobil.modul5compose.ui.screens.home

import com.mobil.modul5compose.data.local.MovieEntity

data class HomeState(
    val movies: List<MovieEntity> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val navigateToDetailEvent: Int? = null,
    val openExternalUrlEvent: String? = null
)