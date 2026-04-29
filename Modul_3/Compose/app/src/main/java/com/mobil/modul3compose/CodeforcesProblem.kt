package com.mobil.modul3compose

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
data class CodeforcesProblem(
    val problemId: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    val url: String,
    val solutionCode: String,
    @DrawableRes val img: Int,
)
