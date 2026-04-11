package com.kmp.weather.di

import com.kmp.weather.data.repository.WeatherRepositoryImpl
import com.kmp.weather.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<WeatherRepository> {
        WeatherRepositoryImpl(apiService = get())
    }
}

