package com.mobil.modul4xml.ui.fragments.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobil.modul4xml.data.CodeforcesProblem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailViewModel(
    private val problem: CodeforcesProblem,
    private val moduleName: String
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    fun loadDetail(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val codeText = context.resources.openRawResource(problem.solutionCode).bufferedReader().use { it.readText() }

            Timber.d("DetailViewModel initialized for module $moduleName : ${problem.title}")

            _state.update {
                it.copy(
                    titleRes = problem.title,
                    descRes = problem.description,
                    imgRes = problem.img,
                    code = codeText,
                )
            }
        }
    }
}