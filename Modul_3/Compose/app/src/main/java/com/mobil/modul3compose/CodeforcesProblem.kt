package com.mobil.modul3compose

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes

data class CodeforcesProblem(
    val problemId: String,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val tags: List<String>,
    val url: String,
    @RawRes val solutionCode: Int,
    @DrawableRes val img: Int,
)