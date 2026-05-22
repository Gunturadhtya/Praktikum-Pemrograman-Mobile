package com.mobil.modul5compose.network

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import timber.log.Timber

abstract class BaseRepository {
    protected suspend inline fun <reified T> safeApiCall(
        call: () -> HttpResponse
    ): Result<T> = runCatching {
        val response = call()
        if (response.status.isSuccess()) {
            response.body<T>()
        } else {
            val errorBody = response.bodyAsText()
            val errorMessage = "Status: ${response.status.value} | Body: $errorBody"

            Timber.tag("Network").e("API Failure: $errorMessage")
            throw Exception("API Error: ${response.status.value}")
        }
    }.onFailure { exception ->
        Timber.tag("Network").e(exception, "Network Failure")
    }
}