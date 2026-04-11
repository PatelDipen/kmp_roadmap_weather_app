package com.kmp.weather.domain.repository

import com.kmp.weather.domain.model.CitySuggestion

interface LocationRepository {
    suspend fun searchCities(query: String, count: Int = 5): Result<List<CitySuggestion>>
}

