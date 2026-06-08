package com.mobil.modul5compose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobil.modul5compose.data.Resource
import com.mobil.modul5compose.network.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    init { loadMovies() }

    private fun loadMovies() {
        viewModelScope.launch {
            repository.languageTag.collect {
                loadMoviesForCurrentLanguage()
            }
        }
    }

    private var moviesJob: kotlinx.coroutines.Job? = null

    private fun loadMoviesForCurrentLanguage() {
        moviesJob?.cancel()
        moviesJob = viewModelScope.launch {
            repository.getPopularMovies().collect { result ->
                when (result) {
                    is Resource.Loading -> _uiState.update { it.copy(isLoading = true, movies = result.data ?: it.movies) }
                    is Resource.Success -> _uiState.update { it.copy(isLoading = false, movies = result.data, errorMessage = null) }
                    is Resource.Error -> _uiState.update { it.copy(isLoading = false, errorMessage = result.exception.message, movies = result.data ?: it.movies) }
                }
            }
        }
    }

    fun onMovieClicked(movieId: Int) {
        _uiState.update { it.copy(navigateToDetailEvent = movieId) }
    }

    fun onDetailNavigationHandled() {
        _uiState.update { it.copy(navigateToDetailEvent = null) }
    }

    fun onExternalUrlClicked(url: String) {
        _uiState.update { it.copy(openExternalUrlEvent = url) }
    }

    fun onExternalUrlHandled() {
        _uiState.update { it.copy(openExternalUrlEvent = null) }
    }
}