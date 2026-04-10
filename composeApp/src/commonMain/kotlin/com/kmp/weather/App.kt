package com.kmp.weather

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.kmp.weather.presentation.main.MainScreen
import com.kmp.weather.presentation.splash.SplashScreen
import kotlinx.coroutines.delay

@Composable
@Preview
fun App() {
    MaterialTheme {
        var isStartupComplete by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            delay(2000)
            isStartupComplete = true
        }

        if (isStartupComplete) {
            MainScreen()
        } else {
            SplashScreen()
        }
    }
}
