package com.kmp.weather.di

import com.kmp.weather.data.repository.LocationRepositoryImpl
import com.kmp.weather.data.repository.WeatherRepositoryImpl
import com.kmp.weather.domain.repository.LocationRepository
import com.kmp.weather.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<LocationRepository> {
        LocationRepositoryImpl(geoApi = get())
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(apiService = get())
    }
}

