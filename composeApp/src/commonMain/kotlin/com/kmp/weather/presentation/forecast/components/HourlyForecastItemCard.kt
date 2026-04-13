package com.kmp.weather.presentation.forecast.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kmp.weather.domain.model.HourlyForecastItem
import com.kmp.weather.utils.WeatherCodeMapper

@Composable
fun HourlyForecastItemCard(item: HourlyForecastItem) {
	Card(modifier = Modifier.fillMaxWidth()) {
		Row(
			modifier = Modifier
				.background(MaterialTheme.colorScheme.primaryFixedDim)
				.padding(12.dp),
			verticalAlignment = Alignment.CenterVertically,
		) {
			Box(
				modifier = Modifier.size(48.dp),
				contentAlignment = Alignment.Center,
			) {
				Text(
					text = WeatherCodeMapper.getIcon(item.weatherCode),
					style = MaterialTheme.typography.bodyMedium
				)
			}

			Column(modifier = Modifier.fillMaxWidth()) {
				Text(
					text = WeatherCodeMapper.getDescription(item.weatherCode),
					style = MaterialTheme.typography.bodyLarge,
					color = Color.White
				)
				Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
					Text(
						text = item.dateTime.substringAfter("T", item.dateTime),
						style = MaterialTheme.typography.titleSmall
					)
					Text(
						text = "Temperature: ${item.temperatureCelsius.toOneDecimalString()}°C",
						style = MaterialTheme.typography.titleSmall
					)
				}
			}
		}
	}
}

@Preview(showBackground = false)
@Composable
private fun HourlyForecastItemCardPreview() {
	HourlyForecastItemCard(
		HourlyForecastItem(
			dateTime = "2024-06-01T15:00:00",
			temperatureCelsius = 22.5,
			weatherCode = 2
		)
	)
}

