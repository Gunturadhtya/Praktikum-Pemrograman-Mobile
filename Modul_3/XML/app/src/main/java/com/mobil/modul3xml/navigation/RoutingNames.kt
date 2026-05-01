package com.mobil.modul3xml.navigation

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