package com.kmp.weather.data.remote.mapper

import com.kmp.weather.data.remote.dto.ForecastResponseDto
import com.kmp.weather.domain.model.ForecastItem
import com.kmp.weather.domain.model.WeatherForecast

fun ForecastResponseDto.toDomain(): WeatherForecast {
    return WeatherForecast(
        cityName = city.name,
        country = city.country,
        items = list.map { item ->
            ForecastItem(
                dateTime = item.dt,
                dateTimeText = item.dtTxt,
                tempCelsius = item.main.temp,
                feelsLikeCelsius = item.main.feelsLike,
                humidity = item.main.humidity,
                description = item.weather.firstOrNull()?.description.orEmpty(),
                iconCode = item.weather.firstOrNull()?.icon.orEmpty(),
                windSpeed = item.wind.speed
            )
        }
    )
}

