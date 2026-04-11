package com.kmp.weather.presentation.forecast.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kmp.weather.domain.model.ForecastItem
import kotlin.math.round

@Composable
fun WeatherForecastDetailScreen(
    cityName: String,
    country: String,
    forecastItem: ForecastItem?,
    onBackClick: () -> Unit = {}
) {
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

        if (forecastItem == null) {
            Text(
                text = "Forecast details are no longer available. Please go back and select an item again.",
                style = MaterialTheme.typography.bodyLarge
            )
            return@Column
        }

        Text(
            text = forecastItem.dateTimeText,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = forecastItem.description.replaceFirstChar { it.uppercaseChar() },
            style = MaterialTheme.typography.bodyLarge
        )
        Text("Temperature: ${forecastItem.tempCelsius.formatOneDecimal()}°C")
        Text("Feels like: ${forecastItem.feelsLikeCelsius.formatOneDecimal()}°C")
        Text("Humidity: ${forecastItem.humidity}%")
        Text("Wind speed: ${forecastItem.windSpeed.formatOneDecimal()} m/s")
        Text("Icon code: ${forecastItem.iconCode}")
    }
}

private fun Double.formatOneDecimal(): String {
    val rounded = round(this * 10) / 10
    return rounded.toString()
}
