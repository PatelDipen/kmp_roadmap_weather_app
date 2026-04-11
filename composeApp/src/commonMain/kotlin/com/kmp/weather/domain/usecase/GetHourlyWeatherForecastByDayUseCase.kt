package com.kmp.weather.domain.usecase

import com.kmp.weather.domain.model.HourlyWeatherForecast
import com.kmp.weather.domain.repository.WeatherRepository

class GetHourlyWeatherForecastByDayUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        latitude: String,
        longitude: String,
        dayKey: String
    ): Result<HourlyWeatherForecast> {
        if (latitude.isBlank() || longitude.isBlank() || dayKey.isBlank()) {
            return Result.failure(IllegalArgumentException("Latitude, longitude, and dayKey cannot be empty"))
        }
        return repository.getHourlyWeatherForecastByDay(latitude, longitude, dayKey)
    }
}

