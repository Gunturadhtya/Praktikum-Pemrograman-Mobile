package com.mobil.modul4xml.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobil.modul4xml.data.ProblemRepository

class HomeViewModelFactory(
    private val repository: ProblemRepository,
    private val moduleName: String
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository, moduleName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}