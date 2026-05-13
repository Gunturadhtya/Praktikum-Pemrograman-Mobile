package com.mobil.modul4compose.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class RoutingNames {
    @Serializable
    object HomeScreen : RoutingNames()

    @Serializable
    data class DetailScreen(
        val problemId: String
    ) : RoutingNames()

    @Serializable
    object LanguageScreen: RoutingNames()
}