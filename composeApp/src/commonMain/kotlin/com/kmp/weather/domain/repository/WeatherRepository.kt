package com.kmp.weather.domain.repository

import com.kmp.weather.domain.model.WeatherForecast

interface WeatherRepository {
    suspend fun getWeatherForecast(city: String): Result<WeatherForecast>
}

