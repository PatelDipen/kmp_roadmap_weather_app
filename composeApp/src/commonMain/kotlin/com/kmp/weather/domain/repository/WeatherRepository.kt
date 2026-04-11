package com.kmp.weather.domain.repository

import com.kmp.weather.domain.model.HourlyWeatherForecast
import com.kmp.weather.domain.model.WeatherForecast

interface WeatherRepository {
    suspend fun getWeatherForecast(latitude: String, longitude: String): Result<WeatherForecast>

    suspend fun getHourlyWeatherForecastByDay(
        latitude: String,
        longitude: String,
        dayKey: String
    ): Result<HourlyWeatherForecast>
}

