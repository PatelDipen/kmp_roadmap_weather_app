package com.kmp.weather.presentation.forecast.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.round

@Composable
fun WeatherForecastListScreen(
    onForecastClick: (cityName: String, country: String, dayKey: String,dayName: String, latitude: String, longitude: String) -> Unit = { _, _, _,_, _, _ -> },
    viewModel: WeatherForecastListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var searchFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = uiState.searchQuery,
                selection = TextRange(uiState.searchQuery.length)
            )
        )
    }

    LaunchedEffect(uiState.searchQuery) {
        if (searchFieldValue.text != uiState.searchQuery) {
            searchFieldValue = TextFieldValue(
                text = uiState.searchQuery,
                selection = TextRange(uiState.searchQuery.length)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchFieldValue,
            onValueChange = { value ->
                searchFieldValue = value
                viewModel.onSearchQueryChange(value.text)
            },
            label = { Text("Search city") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { viewModel.onSearchSubmit() })
        )

        if (uiState.isSuggestionsLoading) {
            Text(
                text = "Searching...",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 6.dp)
            )
        }

        if (uiState.citySuggestions.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                LazyColumn {
                    items(uiState.citySuggestions) { suggestion ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    searchFieldValue = TextFieldValue(
                                        text = suggestion.displayName,
                                        selection = TextRange(suggestion.displayName.length)
                                    )
                                    viewModel.onSuggestionSelected(suggestion)
                                }
                                .padding(horizontal = 12.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = suggestion.name,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = listOfNotNull(
                                    suggestion.admin1,
                                    suggestion.country
                                ).joinToString(", "),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (uiState.cityName.isNotEmpty()) {
            Text(
                text = "${uiState.cityName}, ${uiState.country}",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }

        when {
            uiState.isLoading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            uiState.errorMessage != null -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.errorMessage.orEmpty(),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            else -> LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(uiState.dailyForecasts) { item ->
                    ForecastListItem(
                        item = item,
                        onClick = {
                            onForecastClick(
                                uiState.cityName,
                                uiState.country,
                                item.dayKey,
                                item.dayName,
                                uiState.latitude,
                                uiState.longitude
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ForecastListItem(
    item: DailyForecastSummary,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryFixedDim)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.weatherIcon,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp).padding(end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = item.dayName,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = item.description.replaceFirstChar { it.uppercaseChar() },
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
                Text(
                    text = "${item.minTempCelsius.formatOneDecimal()}° / ${item.maxTempCelsius.formatOneDecimal()}°",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

private fun Double.formatOneDecimal(): String {
    val rounded = round(this * 10) / 10
    return rounded.toString()
}

@Preview(showBackground = true)
@Composable
fun WeatherForecastListScreenPreview() {
    ForecastListItem(
        item = DailyForecastSummary(
            dayKey = "2026-04-13",
            dayName = "Today",
            minTempCelsius = 12.3,
            maxTempCelsius = 18.7,
            description = "Partly cloudy",
            weatherIcon = "⛅",
            weatherCode = 2
        ),
        onClick = {}
    )
}
