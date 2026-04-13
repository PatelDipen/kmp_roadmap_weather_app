package com.kmp.weather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.kmp.weather.presentation.forecast.detail.WeatherForecastDetailScreen
import com.kmp.weather.presentation.forecast.list.WeatherForecastListScreen
import com.kmp.weather.presentation.splash.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Splash
    ) {
        composable<Route.Splash> {
            SplashScreen(
                onSplashComplete = {
                    navController.navigate(Route.ForecastList) {
                        popUpTo<Route.Splash> { inclusive = true }
                    }
                }
            )
        }

        composable<Route.ForecastList> {
            WeatherForecastListScreen(
                onForecastClick = { cityName, country, dayKey, dayName, latitude, longitude ->
                    navController.navigate(
                        Route.ForecastDetail(
                            cityName = cityName,
                            country = country,
                            dayKey = dayKey,
                            dayName = dayName,
                            latitude = latitude,
                            longitude = longitude
                        )
                    )
                }
            )
        }

        composable<Route.ForecastDetail> { backStackEntry ->
            val route: Route.ForecastDetail = backStackEntry.toRoute()
            WeatherForecastDetailScreen(
                cityName = route.cityName,
                country = route.country,
                dayKey = route.dayKey,
                dayName = route.dayName,
                latitude = route.latitude,
                longitude = route.longitude,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
