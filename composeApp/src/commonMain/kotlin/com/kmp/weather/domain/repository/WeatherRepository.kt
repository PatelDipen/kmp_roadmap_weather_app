package com.kmp.weather.domain.repository

import com.kmp.weather.domain.model.WeatherForecast

interface WeatherRepository {
    suspend fun getWeatherForecast(latitude: String, longitude: String): Result<WeatherForecast>
}

