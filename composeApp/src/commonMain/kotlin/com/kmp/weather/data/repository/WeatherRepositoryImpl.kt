package com.kmp.weather.data.repository

import com.kmp.weather.data.remote.api.WeatherApiService
import com.kmp.weather.data.remote.mapper.toDomain
import com.kmp.weather.domain.model.WeatherForecast
import com.kmp.weather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val apiService: WeatherApiService
) : WeatherRepository {

    override suspend fun getWeatherForecast(city: String): Result<WeatherForecast> {
        return runCatching {
            apiService.getForecast(city).toDomain()
        }
    }
}

