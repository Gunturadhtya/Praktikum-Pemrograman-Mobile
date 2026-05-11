package com.mobil.modul4xml.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobil.modul4xml.data.ProblemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    //TODO: decouple logic terhubung dengan problem repository
    private val repository: ProblemRepository = ProblemRepository
) : ViewModel() {
// TODO : buat parameter dengan tipe data String di dalam ViewModel
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    init {
        loadProblems()
    }

    private fun loadProblems() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getAllProblems()
            // TODO : Log saat data item masuk ke dalam list (Timber)
            _uiState.update { it.copy(problems = list) }
        }
    }
}