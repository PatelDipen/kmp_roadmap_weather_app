package com.kmp.weather.data.remote.api

import com.kmp.weather.data.remote.dto.GeocodingResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GeocodingApiService(
    private val client: HttpClient
) {
    companion object {
        private const val BASE_URL = "https://geocoding-api.open-meteo.com"
    }

    suspend fun searchCities(query: String, count: Int = 3): GeocodingResponseDto {
        return client.get("$BASE_URL/v1/search") {
            parameter("name", query)
            parameter("count", count)
        }.body()
    }
}

