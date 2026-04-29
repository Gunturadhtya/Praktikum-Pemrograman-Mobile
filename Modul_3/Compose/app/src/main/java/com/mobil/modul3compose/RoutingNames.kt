package com.mobil.modul3compose

import kotlinx.serialization.Serializable

@Serializable
sealed class RoutingNames {
    @Serializable
    object HomeScreen : RoutingNames()

    @Serializable
    data class DetailScreen(
        val problemId: String
    ) : RoutingNames()
}