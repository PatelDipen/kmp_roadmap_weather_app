package com.kmp.weather.data.remote.api

import com.kmp.weather.data.remote.dto.ForecastResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherApiService(
    private val client: HttpClient,
    private val apiKey: String
) {
    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5"
    }

    suspend fun getForecast(city: String): ForecastResponseDto {
        require(apiKey.isNotBlank()) { "OpenWeather API key is missing. Set OPEN_WEATHER_API_KEY in local.properties." }

        return client.get("$BASE_URL/forecast") {
            parameter("q", city)
            parameter("appid", apiKey)
            parameter("units", "metric")
        }.body()
    }
}

