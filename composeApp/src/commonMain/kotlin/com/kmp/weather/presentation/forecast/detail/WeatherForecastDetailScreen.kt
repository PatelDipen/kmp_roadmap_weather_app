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
import kotlin.math.round

@Composable
fun WeatherForecastDetailScreen(
    cityName: String,
    country: String,
    dayKey: String,
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
        Text(
            text = dayKey,
            style = MaterialTheme.typography.titleMedium
        )

    }
}

private fun Double.formatOneDecimal(): String {
    val rounded = round(this * 10) / 10
    return rounded.toString()
}
