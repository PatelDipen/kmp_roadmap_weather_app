package com.kmp.weather.domain.usecase

import com.kmp.weather.domain.model.WeatherForecast
import com.kmp.weather.domain.repository.WeatherRepository

class GetWeatherForecastUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Result<WeatherForecast> {
        if (city.isBlank()) return Result.failure(IllegalArgumentException("City name cannot be empty"))
        return repository.getWeatherForecast(city.trim())
    }
}

