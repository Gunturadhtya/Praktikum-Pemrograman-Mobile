package com.mobil.modul3xml.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobil.modul3xml.data.ProblemRepository
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