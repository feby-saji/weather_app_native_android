package com.example.myapplication.presentation.pages

import ForecastItem
import WeatherData
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.myapplication.dayFromDt
import com.example.myapplication.getDailyForecast
import com.example.myapplication.getWeekDay
import com.example.myapplication.presentation.LocationPermissionWrapper
import com.example.myapplication.presentation.WeatherUiState
import com.example.myapplication.presentation.WeatherViewModel
import com.example.myapplication.ui.theme.WeatherGradientBottom
import com.example.myapplication.ui.theme.WeatherGradientMid
import com.example.myapplication.ui.theme.WeatherGradientTop

@Composable
fun WeatherPage(
    modifier: Modifier = Modifier,
) {
    val viewModel: WeatherViewModel = viewModel()
    val weatherUiState by viewModel.weatherUiState.collectAsState()
    var city: String by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        containerColor = Color.Transparent,
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            WeatherGradientTop,
                            WeatherGradientMid,
                            WeatherGradientBottom
                        )
                    )
                )
        ) {
            LocationPermissionWrapper {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(text = "Weather App")

                    @SuppressLint("MissingPermission")
                    viewModel.fetchLocation(context = LocalContext.current)

                    when (weatherUiState) {
                        is WeatherUiState.IsLoading -> CircularProgressIndicator()
                        is WeatherUiState.Error -> Text(text = (weatherUiState as WeatherUiState.Error).error)
                        is WeatherUiState.Success -> WeatherDetails((weatherUiState as WeatherUiState.Success).data)
                        WeatherUiState.IdleState -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherDetails(weatherData: WeatherData) {
    WeatherCard(weatherData)

    Text("5-Day Forecast", style = MaterialTheme.typography.titleLarge)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(getDailyForecast(weatherData.list)) { dayForecast ->
            WeatherForecastCard(dayForecast)
        }
    }
}

@Composable
fun WeatherCard(weatherData: WeatherData) {
    Spacer(modifier = Modifier.height(20.dp))

    Text(
        color = Color.White,
        text = "${weatherData.list[0].main.temp.toInt()}°",
        style = MaterialTheme.typography.displayLarge,
        fontWeight = FontWeight.Bold
    )

    Text(
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        text = getWeekDay(weatherData.list[0].dt)
    )

    val iconCode = weatherData.list[0].weather[0].icon
    val iconUrl = "https://openweathermap.org/img/wn/$iconCode@4x.png"
    AsyncImage(
        model = iconUrl,
        contentDescription = "weather icon",
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun WeatherForecastCard(dayForecast: ForecastItem) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.width(50.dp),
                text = dayFromDt(dayForecast.dt)
            )

            AsyncImage(
                model = "https://openweathermap.org/img/wn/${dayForecast.weather[0].icon}@4x.png",
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.weight(1f)) // ← NOW WORKS

            Text("${dayForecast.main.tempMin.toInt()}°")
            Spacer(modifier = Modifier.width(50.dp))
            Text("${dayForecast.main.tempMax.toInt()}°")
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            thickness = 0.5.dp,
            color = Color.White.copy(alpha = 0.1f)
        )
    }
}


@Composable
fun BuildTextFieldWithButton(value: String, newValue: (String) -> Unit, onBtnClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = newValue
        )
        Button(onClick = onBtnClick) { Text(text = "Fetch Weather") }
    }
}



