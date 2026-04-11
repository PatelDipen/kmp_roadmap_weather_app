package com.kmp.weather.data.remote.mapper

import com.kmp.weather.data.remote.dto.Daily
import com.kmp.weather.data.remote.dto.ForecastListDto
import com.kmp.weather.data.remote.dto.HourlyDto
import com.kmp.weather.data.remote.dto.HourlyForecastResponseDto
import com.kmp.weather.domain.model.ForecastItem
import com.kmp.weather.domain.model.HourlyForecastItem
import com.kmp.weather.domain.model.HourlyWeatherForecast
import com.kmp.weather.domain.model.WeatherForecast

fun ForecastListDto.toDomain(): WeatherForecast {
    return WeatherForecast(
        items = daily.toDomainItems()
    )
}

private fun Daily.toDomainItems(): List<ForecastItem> {
    val size = minOf(time.size, temperature2mMin.size, temperature2mMax.size, weatherCode.size)

    return List(size) { index ->
        ForecastItem(
            dateText = time[index],
            minTemperature = temperature2mMin[index],
            maxTemperature = temperature2mMax[index],
            weatherCode = weatherCode[index]
        )
    }
}

fun HourlyForecastResponseDto.toHourlyDomain(dayKey: String): HourlyWeatherForecast {
    return HourlyWeatherForecast(
        dayKey = dayKey,
        items = hourly.toDomainItems()
    )
}

private fun HourlyDto.toDomainItems(): List<HourlyForecastItem> {
    val size = minOf(time.size, temperature2m.size, weatherCode.size)

    return List(size) { index ->
        HourlyForecastItem(
            dateTime = time[index],
            temperatureCelsius = temperature2m[index],
            weatherCode = weatherCode[index]
        )
    }
}

