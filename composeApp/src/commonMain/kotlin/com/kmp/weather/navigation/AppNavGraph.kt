package com.kmp.weather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.kmp.weather.presentation.forecast.ForecastSelectionStore
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
                onForecastClick = { cityName, country, forecastItem ->
                    navController.navigate(
                        Route.ForecastDetail(
                            cityName = cityName,
                            country = country,
                            forecastDateTime = forecastItem.dateTime
                        )
                    )
                }
            )
        }

        composable<Route.ForecastDetail> { backStackEntry ->
            val route: Route.ForecastDetail = backStackEntry.toRoute()
            val forecastItem = ForecastSelectionStore.findByDateTime(route.forecastDateTime)
            WeatherForecastDetailScreen(
                cityName = route.cityName,
                country = route.country,
                forecastItem = forecastItem,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
