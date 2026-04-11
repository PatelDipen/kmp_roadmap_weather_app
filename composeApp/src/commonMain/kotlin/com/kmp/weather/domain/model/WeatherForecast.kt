package com.kmp.weather.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecast(
    val cityName: String,
    val country: String,
    val items: List<ForecastItem>
)

@Serializable
data class ForecastItem(
    val dateTime: Long,
    val dateTimeText: String,
    val tempCelsius: Double,
    val feelsLikeCelsius: Double,
    val humidity: Int,
    val description: String,
    val iconCode: String,
    val windSpeed: Double
) {
    val iconUrl: String
        get() = "https://openweathermap.org/img/wn/${iconCode}@2x.png"
}

