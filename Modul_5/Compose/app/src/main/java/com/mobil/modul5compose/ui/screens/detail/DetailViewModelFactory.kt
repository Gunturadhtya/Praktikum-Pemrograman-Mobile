package com.mobil.modul5compose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModelFactory(
    private val problemId: String,
    private val moduleName: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(problemId, moduleName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}