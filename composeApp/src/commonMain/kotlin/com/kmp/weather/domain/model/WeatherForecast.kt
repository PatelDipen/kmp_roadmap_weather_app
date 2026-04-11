package com.kmp.weather.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecast(
    val items: List<ForecastItem>
)

@Serializable
data class ForecastItem(
    val dateText: String,
    val minTemperature: Double,
    val maxTemperature: Double,
    val weatherCode: Int,
) {
}

