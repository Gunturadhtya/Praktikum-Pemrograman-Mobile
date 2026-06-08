package com.mobil.modul5compose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobil.modul5compose.data.Resource
import com.mobil.modul5compose.network.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MovieRepository,
    private val movieId: Int
) : ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    init { loadMovie() }

    private fun loadMovie() {
        viewModelScope.launch {
            repository.getMovie(movieId).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true, movie = result.data ?: it.movie) }
                    is Resource.Success -> _state.update { it.copy(isLoading = false, movie = result.data, errorMessage = null) }
                    is Resource.Error -> _state.update { it.copy(isLoading = false, errorMessage = result.exception.message, movie = result.data ?: it.movie) }
                }
            }
        }
    }
}