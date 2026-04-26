package com.mobil.modul3compose

data class CodeforcesProblem(
    val problemId: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    val url: String,
    val solutionCode: String
)
