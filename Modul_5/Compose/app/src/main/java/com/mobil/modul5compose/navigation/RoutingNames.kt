package com.mobil.modul5compose.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class RoutingNames {
    @Serializable
    object HomeScreen : RoutingNames()

    @Serializable
    data class DetailScreen(
        val movieId: Int
    ) : RoutingNames()

    @Serializable
    object LanguageScreen: RoutingNames()
}