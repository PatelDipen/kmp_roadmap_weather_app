package com.kmp.weather.domain.usecase

import com.kmp.weather.domain.model.CitySuggestion
import com.kmp.weather.domain.repository.LocationRepository

class SearchCitiesUseCase(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(query: String, count: Int = 5): Result<List<CitySuggestion>> {
        if (query.isBlank()) return Result.success(emptyList())
        return locationRepository.searchCities(query.trim(), count)
    }
}

