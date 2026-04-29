package com.mobil.modul3compose

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailScreen(problemId: String, modifier: Modifier){
    val problem = ProblemRepository.getProblemById(problemId)
    Row(modifier = modifier){
        Text("Detail -> + ${problem?.title}")

    }
}