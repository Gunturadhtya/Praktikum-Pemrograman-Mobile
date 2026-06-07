package com.mobil.modul5compose.data

sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error<T>(val exception: Throwable, val data: T? = null) : Resource<T>
    data class Loading<T>(val data: T? = null) : Resource<T>
}