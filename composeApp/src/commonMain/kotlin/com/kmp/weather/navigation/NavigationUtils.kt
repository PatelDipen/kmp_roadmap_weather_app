package com.kmp.weather.navigation

import androidx.navigation.NavController

fun NavController.navigateToForecastList() {
    navigate(Route.ForecastList) {
        popUpTo<Route.Splash> { inclusive = true }
    }
}

fun NavController.navigateToForecastDetail(forecastId: String) {
    navigate(Route.ForecastDetail(forecastId = forecastId))
}

fun NavController.navigateBack() {
    popBackStack()
}
