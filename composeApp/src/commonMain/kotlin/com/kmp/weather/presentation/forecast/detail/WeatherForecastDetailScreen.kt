package com.kmp.weather.presentation.forecast.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.round

@Composable
fun WeatherForecastDetailScreen(
    cityName: String,
    country: String,
    dayKey: String,
    latitude: String,
    longitude: String,
    viewModel: WeatherForecastDetailViewModel = koinViewModel(),
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(dayKey, latitude, longitude) {
        viewModel.loadHourlyForecast(
            dayKey = dayKey,
            latitude = latitude,
            longitude = longitude
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(onClick = onBackClick) {
            Text("Back")
        }

        Text(
            text = "$cityName, $country",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = dayKey,
            style = MaterialTheme.typography.titleMedium
        )

        when {
            uiState.isLoading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            uiState.errorMessage != null -> Text(
                text = uiState.errorMessage.orEmpty(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )

            uiState.hourlyItems.isEmpty() -> Text(
                text = "No hourly forecast data available for this day.",
                style = MaterialTheme.typography.bodyLarge
            )

            else -> LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(uiState.hourlyItems) { item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = item.dateTime.substringAfter("T", item.dateTime),
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                text = "Temperature: ${item.temperatureCelsius.formatOneDecimal()} C",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "Weather code: ${item.weatherCode}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }

    }
}

private fun Double.formatOneDecimal(): String {
    val rounded = round(this * 10) / 10
    return rounded.toString()
}
