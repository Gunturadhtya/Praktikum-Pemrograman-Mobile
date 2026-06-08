package com.mobil.modul5compose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobil.modul5compose.network.MovieRepository

class DetailViewModelFactory(
    private val repository: MovieRepository,
    private val movieId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository, movieId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}