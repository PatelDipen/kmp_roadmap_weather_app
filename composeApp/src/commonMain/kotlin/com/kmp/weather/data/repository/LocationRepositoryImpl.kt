package com.kmp.weather.data.repository

import com.kmp.weather.data.remote.api.GeocodingApiService
import com.kmp.weather.data.remote.mapper.toDomain
import com.kmp.weather.domain.model.CitySuggestion
import com.kmp.weather.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val geoApi: GeocodingApiService
) : LocationRepository {

    override suspend fun searchCities(query: String, count: Int): Result<List<CitySuggestion>> {
        return runCatching {
            geoApi.searchCities(query, count).toDomain()
        }
    }
}

