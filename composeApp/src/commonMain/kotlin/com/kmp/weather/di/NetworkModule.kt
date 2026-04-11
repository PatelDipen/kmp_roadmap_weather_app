package com.kmp.weather.di

import com.kmp.weather.data.remote.api.WeatherApiService
import com.kmp.weather.data.remote.api.GeocodingApiService
import com.kmp.weather.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = false
                })
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.INFO
            }
        }
    }

    single {
        WeatherApiService(
            client = get(),
            apiKey = BuildKonfig.OPEN_WEATHER_API_KEY
        )
    }

    single {
        GeocodingApiService(client = get())
    }
}

