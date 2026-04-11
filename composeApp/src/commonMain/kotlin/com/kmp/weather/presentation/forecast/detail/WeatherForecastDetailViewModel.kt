package com.kmp.weather.presentation.forecast.detail

import androidx.lifecycle.ViewModel

data class WeatherForecastDetailUiState(
    val isLoading: Boolean = false
)

class WeatherForecastDetailViewModel : ViewModel()

