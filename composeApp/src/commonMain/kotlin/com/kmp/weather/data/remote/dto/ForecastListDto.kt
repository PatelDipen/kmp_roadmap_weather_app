package com.kmp.weather.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastListDto(
    @SerialName("daily")
    val daily: Daily,
    @SerialName("daily_units")
    val dailyUnits: DailyUnits,
    @SerialName("elevation")
    val elevation: Double,
    @SerialName("generationtime_ms")
    val generationtimeMs: Double,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("timezone")
    val timezone: String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)

@Serializable
data class DailyUnits(
    @SerialName("temperature_2m_max")
    val temperature2mMax: String,
    @SerialName("temperature_2m_min")
    val temperature2mMin: String,
    @SerialName("time")
    val time: String,
    @SerialName("weather_code")
    val weatherCode: String
)

@Serializable
data class Daily(
    @SerialName("temperature_2m_max")
    val temperature2mMax: List<Double>,
    @SerialName("temperature_2m_min")
    val temperature2mMin: List<Double>,
    @SerialName("time")
    val time: List<String>,
    @SerialName("weather_code")
    val weatherCode: List<Int>
)
