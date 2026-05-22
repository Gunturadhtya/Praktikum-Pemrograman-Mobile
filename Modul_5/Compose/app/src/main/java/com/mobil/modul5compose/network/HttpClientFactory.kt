package com.mobil.modul5compose.network

import io.ktor.client.HttpClient

interface HttpClientFactory {
    fun create(): HttpClient
}