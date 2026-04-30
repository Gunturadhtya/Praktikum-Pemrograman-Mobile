package com.mobil.modul3compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ProblemRepository = ProblemRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    init {
        loadProblems()
    }

    private fun loadProblems() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getAllProblems()
            _uiState.update { it.copy(problems = list) }
        }
    }
}