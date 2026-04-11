package com.kmp.weather.di

import com.kmp.weather.domain.usecase.GetWeatherForecastUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetWeatherForecastUseCase(repository = get())
    }
}

