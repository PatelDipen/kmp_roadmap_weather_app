package com.kmp.weather.presentation.forecast

import com.kmp.weather.domain.model.ForecastItem

object ForecastSelectionStore {
    private var cityName: String = ""
    private var country: String = ""
    private var forecasts: List<ForecastItem> = emptyList()

    fun cache(
        cityName: String,
        country: String,
        forecasts: List<ForecastItem>
    ) {
        this.cityName = cityName
        this.country = country
        this.forecasts = forecasts
    }

    fun findByDateTime(dateTime: Long): ForecastItem? {
        return forecasts.firstOrNull { it.dateTime == dateTime }
    }
}

