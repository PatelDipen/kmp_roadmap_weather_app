package com.kmp.weather.data.remote.mapper

import com.kmp.weather.data.remote.dto.Daily
import com.kmp.weather.data.remote.dto.ForecastListDto
import com.kmp.weather.domain.model.ForecastItem
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

