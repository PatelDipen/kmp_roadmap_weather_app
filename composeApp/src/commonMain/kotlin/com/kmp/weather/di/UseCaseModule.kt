package com.kmp.weather.di

import com.kmp.weather.domain.usecase.GetWeatherForecastUseCase
import com.kmp.weather.domain.usecase.GetHourlyWeatherForecastByDayUseCase
import com.kmp.weather.domain.usecase.SearchCitiesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetWeatherForecastUseCase(repository = get())
    }
    factory {
        GetHourlyWeatherForecastByDayUseCase(repository = get())
    }
    factory {
        SearchCitiesUseCase(locationRepository = get())
    }
}

