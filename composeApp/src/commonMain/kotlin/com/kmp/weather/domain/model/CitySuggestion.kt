package com.kmp.weather.domain.model

data class CitySuggestion(
    val name: String,
    val country: String,
    val admin1: String?,
    val latitude: Double,
    val longitude: Double
) {
    val displayName: String
        get() = listOfNotNull(name, admin1, country).joinToString(", ")
}

