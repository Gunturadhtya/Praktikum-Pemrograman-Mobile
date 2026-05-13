package com.mobil.modul4xml.ui.fragments.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobil.modul4xml.data.CodeforcesProblem

class DetailViewModelFactory(
    private val problemId: String,
    private val moduleName: String
): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                return DetailViewModel(problemId, moduleName) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}