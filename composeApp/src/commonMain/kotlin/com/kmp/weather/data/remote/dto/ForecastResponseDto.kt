package com.kmp.weather.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponseDto(
    @SerialName("list") val list: List<ForecastItemDto>,
    @SerialName("city") val city: CityDto
)

@Serializable
data class ForecastItemDto(
    @SerialName("dt") val dt: Long,
    @SerialName("main") val main: MainDto,
    @SerialName("weather") val weather: List<WeatherDto>,
    @SerialName("wind") val wind: WindDto,
    @SerialName("dt_txt") val dtTxt: String
)

@Serializable
data class MainDto(
    @SerialName("temp") val temp: Double,
    @SerialName("feels_like") val feelsLike: Double,
    @SerialName("humidity") val humidity: Int
)

@Serializable
data class WeatherDto(
    @SerialName("id") val id: Int,
    @SerialName("main") val main: String,
    @SerialName("description") val description: String,
    @SerialName("icon") val icon: String
)

@Serializable
data class WindDto(
    @SerialName("speed") val speed: Double
)

@Serializable
data class CityDto(
    @SerialName("name") val name: String,
    @SerialName("country") val country: String
)

