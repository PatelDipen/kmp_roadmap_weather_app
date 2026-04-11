package com.kmp.weather.domain.usecase

import com.kmp.weather.domain.model.WeatherForecast
import com.kmp.weather.domain.repository.WeatherRepository

class GetWeatherForecastUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(latitude: String, longitude: String): Result<WeatherForecast> {
        if (latitude.isBlank() || longitude.isBlank()) return Result.failure(IllegalArgumentException("Lat, Lon cannot be empty"))
        return repository.getWeatherForecast(latitude, longitude)
    }
}

