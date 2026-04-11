package com.kmp.weather.domain.model

data class HourlyWeatherForecast(
    val dayKey: String,
    val items: List<HourlyForecastItem>
)

data class HourlyForecastItem(
    val dateTime: String,
    val temperatureCelsius: Double,
    val weatherCode: Int
)

