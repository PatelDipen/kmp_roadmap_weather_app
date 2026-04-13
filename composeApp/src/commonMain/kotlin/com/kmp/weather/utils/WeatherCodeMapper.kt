package com.kmp.weather.utils

/**
 * Maps WMO weather codes to descriptions and icons
 * Reference: https://www.open-meteo.com/en/docs
 */
object WeatherCodeMapper {

    data class WeatherInfo(
        val description: String,
        val icon: String, // emoji or icon name
        val condition: String
    )

    private val weatherCodeMap = mapOf(
        // Clear sky
        0 to WeatherInfo("Clear sky", "☀️", "Sunny"),
        1 to WeatherInfo("Mainly clear", "🌤️", "Mostly Sunny"),
        2 to WeatherInfo("Partly cloudy", "⛅", "Partly Cloudy"),
        3 to WeatherInfo("Overcast", "☁️", "Cloudy"),

        // Fog and depositing rime fog
        45 to WeatherInfo("Foggy", "🌫️", "Fog"),
        48 to WeatherInfo("Depositing rime fog", "🌫️", "Fog"),

        // Drizzle
        51 to WeatherInfo("Light drizzle", "🌧️", "Drizzle"),
        53 to WeatherInfo("Moderate drizzle", "🌧️", "Drizzle"),
        55 to WeatherInfo("Dense drizzle", "🌧️", "Drizzle"),

        // Freezing Drizzle
        56 to WeatherInfo("Light freezing drizzle", "🥶", "Freezing Drizzle"),
        57 to WeatherInfo("Dense freezing drizzle", "🥶", "Freezing Drizzle"),

        // Rain
        61 to WeatherInfo("Slight rain", "🌧️", "Light Rain"),
        63 to WeatherInfo("Moderate rain", "🌧️", "Rain"),
        65 to WeatherInfo("Heavy rain", "⛈️", "Heavy Rain"),

        // Freezing Rain
        66 to WeatherInfo("Light freezing rain", "🥶", "Freezing Rain"),
        67 to WeatherInfo("Heavy freezing rain", "🥶", "Freezing Rain"),

        // Snow
        71 to WeatherInfo("Slight snow", "❄️", "Light Snow"),
        73 to WeatherInfo("Moderate snow", "❄️", "Snow"),
        75 to WeatherInfo("Heavy snow", "❄️", "Heavy Snow"),
        77 to WeatherInfo("Snow grains", "❄️", "Snow"),

        // Rain and snow mixed
        80 to WeatherInfo("Slight rain showers", "🌦️", "Light Showers"),
        81 to WeatherInfo("Moderate rain showers", "🌦️", "Showers"),
        82 to WeatherInfo("Violent rain showers", "⛈️", "Violent Showers"),

        // Snow showers
        85 to WeatherInfo("Slight snow showers", "❄️", "Light Snow Showers"),
        86 to WeatherInfo("Heavy snow showers", "❄️", "Heavy Snow Showers"),

        // Thunderstorm
        80 to WeatherInfo("Thunderstorm", "⛈️", "Thunderstorm"),
        81 to WeatherInfo("Thunderstorm with slight hail", "⛈️", "Thunderstorm"),
        82 to WeatherInfo("Thunderstorm with heavy hail", "⛈️", "Thunderstorm"),
    )

    fun getWeatherInfo(code: Int): WeatherInfo {
        return weatherCodeMap[code] ?: WeatherInfo(
            description = "Unknown (Code: $code)",
            icon = "❓",
            condition = "Unknown"
        )
    }

    fun getDescription(code: Int): String = getWeatherInfo(code).description

    fun getIcon(code: Int): String = getWeatherInfo(code).icon
}

