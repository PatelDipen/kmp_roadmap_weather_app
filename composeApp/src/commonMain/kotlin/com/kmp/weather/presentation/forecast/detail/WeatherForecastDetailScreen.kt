package com.kmp.weather.presentation.forecast.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.weather.domain.model.HourlyForecastItem
import com.kmp.weather.utils.WeatherCodeMapper
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.round

@Composable
fun WeatherForecastDetailScreen(
    cityName: String,
    country: String,
    dayKey: String,
    dayName: String,
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
            text = dayName,
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
                    WeatherItem(item)
                }
            }
        }

    }
}

@Composable
fun WeatherItem(item: HourlyForecastItem) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.primaryFixedDim)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = WeatherCodeMapper.getIcon(item.weatherCode),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = WeatherCodeMapper.getDescription(item.weatherCode),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = item.dateTime.substringAfter("T", item.dateTime),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "Temperature: ${item.temperatureCelsius.formatOneDecimal()}°C",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}

private fun Double.formatOneDecimal(): String {
    val rounded = round(this * 10) / 10
    return rounded.toString()
}


@Preview(showBackground = false)
@Composable
fun WeatherItemPreview() {
    WeatherItem(
        HourlyForecastItem(
            dateTime = "2024-06-01T15:00:00",
            temperatureCelsius = 22.5,
            weatherCode = 800
        )
    )
}