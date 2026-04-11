package com.kmp.weather.data.repository

import com.kmp.weather.data.remote.api.WeatherApiService
import com.kmp.weather.data.remote.mapper.toDomain
import com.kmp.weather.data.remote.mapper.toHourlyDomain
import com.kmp.weather.domain.model.HourlyWeatherForecast
import com.kmp.weather.domain.model.WeatherForecast
import com.kmp.weather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val apiService: WeatherApiService
) : WeatherRepository {

    override suspend fun getWeatherForecast(latitude: String, longitude: String): Result<WeatherForecast> {
        return runCatching {
            apiService.getForecast(latitude, longitude).toDomain()
        }
    }

    override suspend fun getHourlyWeatherForecastByDay(
        latitude: String,
        longitude: String,
        dayKey: String
    ): Result<HourlyWeatherForecast> {
        return runCatching {
            apiService
                .getHourlyForecastByDay(
                    latitude = latitude,
                    longitude = longitude,
                    startDate = dayKey,
                    endDate = dayKey
                )
                .toHourlyDomain(dayKey)
        }
    }
}

