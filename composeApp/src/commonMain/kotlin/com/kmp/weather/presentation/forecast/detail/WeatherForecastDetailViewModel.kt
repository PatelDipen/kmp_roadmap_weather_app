package com.kmp.weather.presentation.forecast.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.weather.domain.model.HourlyForecastItem
import com.kmp.weather.domain.usecase.GetHourlyWeatherForecastByDayUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class WeatherForecastDetailUiState(
    val isLoading: Boolean = false,
    val dayKey: String = "",
    val hourlyItems: List<HourlyForecastItem> = emptyList(),
    val errorMessage: String? = null
)

class WeatherForecastDetailViewModel(
    private val getHourlyWeatherForecastByDayUseCase: GetHourlyWeatherForecastByDayUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherForecastDetailUiState())
    val uiState: StateFlow<WeatherForecastDetailUiState> = _uiState.asStateFlow()

    fun loadHourlyForecast(
        dayKey: String,
        latitude: String,
        longitude: String
    ) {
        val current = _uiState.value
        if (current.dayKey == dayKey && current.hourlyItems.isNotEmpty()) return

        viewModelScope.launch {
            _uiState.value = current.copy(
                isLoading = true,
                dayKey = dayKey,
                errorMessage = null,
                hourlyItems = emptyList()
            )

            getHourlyWeatherForecastByDayUseCase(
                latitude = latitude,
                longitude = longitude,
                dayKey = dayKey
            ).onSuccess { hourlyForecast ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    dayKey = hourlyForecast.dayKey,
                    hourlyItems = hourlyForecast.items
                )
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = error.message ?: "Unable to load hourly forecast"
                )
            }
        }
    }
}

