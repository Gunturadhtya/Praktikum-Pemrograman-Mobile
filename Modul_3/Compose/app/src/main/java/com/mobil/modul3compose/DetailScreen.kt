package com.mobil.modul3compose

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen(problemId: String){
    val problem = ProblemRepository.getProblemById(problemId)
    Row(){
        Text("Detail -> + ${problem?.title}")
    }
}