package com.kmp.weather.data.remote.api

import com.kmp.weather.data.remote.dto.ForecastListDto
import com.kmp.weather.data.remote.dto.HourlyForecastResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherApiService(
    private val client: HttpClient,
    @Suppress("unused") private val apiKey: String
) {
    companion object {
        private const val BASE_URL = "https://api.open-meteo.com/"
    }

    suspend fun getForecast(latitude: String, longitude: String): ForecastListDto {

        return client.get("$BASE_URL/v1/forecast") {
            parameter("latitude", latitude)
            parameter("longitude", longitude)
            parameter("daily", "temperature_2m_max,temperature_2m_min,weather_code")
            parameter("timezone", "auto")
            parameter("forecast_days", 10)
        }.body()
    }

    suspend fun getHourlyForecastByDay(
        latitude: String,
        longitude: String,
        startDate: String,
        endDate: String
    ): HourlyForecastResponseDto {
        return client.get("$BASE_URL/v1/forecast") {
            parameter("latitude", latitude)
            parameter("longitude", longitude)
            parameter("hourly", "temperature_2m,weather_code")
            parameter("start_date", startDate)
            parameter("end_date", endDate)
            parameter("timezone", "auto")
        }.body()
    }
}

