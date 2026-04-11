package com.kmp.weather.navigation

import androidx.navigation.NavController

fun NavController.navigateToForecastList() {
    navigate(Route.ForecastList) {
        popUpTo<Route.Splash> { inclusive = true }
    }
}

fun NavController.navigateToForecastDetail(
    cityName: String,
    country: String,
    forecastDateTime: Long
) {
    navigate(
        Route.ForecastDetail(
            cityName = cityName,
            country = country,
            forecastDateTime = forecastDateTime
        )
    )
}

fun NavController.navigateBack() {
    popBackStack()
}
