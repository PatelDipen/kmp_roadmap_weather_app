package com.kmp.weather.di

import com.kmp.weather.presentation.forecast.list.WeatherForecastListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        WeatherForecastListViewModel(getWeatherForecastUseCase = get())
    }
}

