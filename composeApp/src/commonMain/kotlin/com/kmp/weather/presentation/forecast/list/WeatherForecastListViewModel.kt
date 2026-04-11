package com.kmp.weather.presentation.forecast.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.weather.domain.model.CitySuggestion
import com.kmp.weather.domain.model.ForecastItem
import com.kmp.weather.domain.usecase.GetWeatherForecastUseCase
import com.kmp.weather.domain.usecase.SearchCitiesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class WeatherForecastListUiState(
    val isLoading: Boolean = false,
    val cityName: String = "",
    val country: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val dailyForecasts: List<DailyForecastSummary> = emptyList(),
    val citySuggestions: List<CitySuggestion> = emptyList(),
    val isSuggestionsLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = ""
)

data class DailyForecastSummary(
    val dayKey: String,
    val minTempCelsius: Double,
    val maxTempCelsius: Double,
    val description: String
)

class WeatherForecastListViewModel(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val searchCitiesUseCase: SearchCitiesUseCase
) : ViewModel() {

    private object DefaultForecastLocation {
        const val LATITUDE = "56.95"
        const val LONGITUDE = "24.09"
        const val CITY_NAME = "Riga"
        const val COUNTRY = "Latvia"
    }

    private val _uiState = MutableStateFlow(WeatherForecastListUiState())
    val uiState: StateFlow<WeatherForecastListUiState> = _uiState.asStateFlow()
    private var searchJob: Job? = null

    init {
        loadForecast(
            DefaultForecastLocation.LATITUDE,
            DefaultForecastLocation.LONGITUDE,
            cityName = DefaultForecastLocation.CITY_NAME,
            country = DefaultForecastLocation.COUNTRY
        )
    }

    fun onSearchQueryChange(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)

        searchJob?.cancel()
        if (query.length < 2) {
            _uiState.value = _uiState.value.copy(citySuggestions = emptyList(), isSuggestionsLoading = false)
            return
        }

        searchJob = viewModelScope.launch {
            delay(350)
            loadSuggestions(query)
        }
    }

    fun onSearchSubmit() {
        val suggestion = _uiState.value.citySuggestions.firstOrNull()
        if (suggestion != null) {
            onSuggestionSelected(suggestion)
        }
    }

    fun onSuggestionSelected(suggestion: CitySuggestion) {
        _uiState.value = _uiState.value.copy(
            searchQuery = suggestion.displayName,
            citySuggestions = emptyList(),
            isSuggestionsLoading = false,
        )
        loadForecast(
            latitude = suggestion.latitude.toString(),
            longitude = suggestion.longitude.toString(),
            cityName = suggestion.name,
            country = suggestion.country
        )
    }

    private fun loadSuggestions(query: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isSuggestionsLoading = true)
            searchCitiesUseCase(query = query, count = 3)
                .onSuccess { suggestions ->
                    val currentQuery = _uiState.value.searchQuery
                    if (currentQuery == query) {
                        _uiState.value = _uiState.value.copy(
                            citySuggestions = suggestions,
                            isSuggestionsLoading = false
                        )
                    }
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(
                        citySuggestions = emptyList(),
                        isSuggestionsLoading = false
                    )
                }
        }
    }

    private fun loadForecast(
        latitude: String,
        longitude: String,
        cityName: String = "",
        country: String = ""
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            getWeatherForecastUseCase(latitude, longitude)
                .onSuccess { forecast ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        latitude = latitude,
                        longitude = longitude,
                        cityName = cityName,
                        country = country,
                        dailyForecasts = buildDailyForecasts(forecast.items)
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

    private fun buildDailyForecasts(items: List<ForecastItem>): List<DailyForecastSummary> {
        return items
            .map {
                DailyForecastSummary(
                    dayKey = it.dateText,
                    minTempCelsius = it.minTemperature,
                    maxTempCelsius = it.maxTemperature,
                    description = "Weather code: ${it.weatherCode}"
                )
            }
    }
}

