package com.kmp.weather.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyForecastResponseDto(
    @SerialName("hourly")
    val hourly: HourlyDto
)

@Serializable
data class HourlyDto(
    @SerialName("time")
    val time: List<String>,
    @SerialName("temperature_2m")
    val temperature2m: List<Double>,
    @SerialName("weather_code")
    val weatherCode: List<Int>
)

