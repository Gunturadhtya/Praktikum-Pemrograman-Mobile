package com.mobil.modul5compose.data

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes

data class CodeforcesProblem(
    val problemId: String,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val url: String,
    @RawRes val solutionCode: Int,
    @DrawableRes val img: Int,
)