package com.kmp.weather.data.remote.mapper

import com.kmp.weather.data.remote.dto.GeocodingResponseDto
import com.kmp.weather.domain.model.CitySuggestion

fun GeocodingResponseDto.toDomain(): List<CitySuggestion> {
    return results.map { result ->
        CitySuggestion(
            name = result.name,
            country = result.country.orEmpty(),
            admin1 = result.admin1,
            latitude = result.latitude,
            longitude = result.longitude
        )
    }
}

