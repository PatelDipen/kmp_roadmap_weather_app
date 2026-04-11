package com.kmp.weather

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.kmp.weather.di.appModules
import com.kmp.weather.navigation.AppNavGraph
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

@Composable
@Preview
fun App() {
    KoinApplication(
        configuration = koinConfiguration(declaration = { modules(appModules) }),
        content = {
            MaterialTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        })
}
