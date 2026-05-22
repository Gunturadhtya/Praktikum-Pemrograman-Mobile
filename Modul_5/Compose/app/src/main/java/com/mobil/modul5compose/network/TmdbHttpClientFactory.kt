package com.mobil.modul5compose.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class TmdbHttpClientFactory(
    private val apiKey: String
) : HttpClientFactory {
    override fun create(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation){
                json(Json{
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }

            defaultRequest {
                url("https://api.themoviedb.org/3/")
                header(HttpHeaders.Authorization, "Bearer $apiKey")
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }
}