package com.kmp.weather.presentation.forecast.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.weather.domain.model.ForecastItem
import com.kmp.weather.domain.usecase.GetWeatherForecastUseCase
import com.kmp.weather.presentation.forecast.ForecastSelectionStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class WeatherForecastListUiState(
    val isLoading: Boolean = false,
    val cityName: String = "",
    val country: String = "",
    val forecasts: List<ForecastItem> = emptyList(),
    val errorMessage: String? = null,
    val searchQuery: String = ""
)

class WeatherForecastListViewModel(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherForecastListUiState())
    val uiState: StateFlow<WeatherForecastListUiState> = _uiState.asStateFlow()

    init {
        loadForecast("Riga")
    }

    fun onSearchQueryChange(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun onSearchSubmit() {
        val query = _uiState.value.searchQuery
        if (query.isNotBlank()) loadForecast(query)
    }

    private fun loadForecast(city: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            getWeatherForecastUseCase(city)
                .onSuccess { forecast ->
                    ForecastSelectionStore.cache(
                        cityName = forecast.cityName,
                        country = forecast.country,
                        forecasts = forecast.items
                    )
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        cityName = forecast.cityName,
                        country = forecast.country,
                        forecasts = forecast.items
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Unknown error"
                    )
                }
        }
    }
}

