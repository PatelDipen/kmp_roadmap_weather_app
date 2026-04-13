package com.kmp.weather.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Splash : Route

    @Serializable
    data object ForecastList : Route

    @Serializable
    data class ForecastDetail(
        val cityName: String,
        val country: String,
        val dayKey: String,
        val dayName: String,
        val latitude: String,
        val longitude: String
    ) : Route
}
