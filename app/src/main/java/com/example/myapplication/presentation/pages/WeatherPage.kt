package com.example.myapplication.presentation.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.myapplication.data.WeatherData
import com.example.myapplication.presentation.WeatherUiState
import com.example.myapplication.presentation.WeatherViewModel


@Composable
fun WeatherPage(
    modifier: Modifier = Modifier,
) {
    val viewModel: WeatherViewModel = viewModel()
    val weatherUiState by viewModel.weatherUiState.collectAsState()
    var city by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )

        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Weather App")
            Spacer(modifier = Modifier.height(30.dp))
            BuildTextFieldWithButton(
                city,
                newValue = { city = it },
                onBtnClick = { viewModel.fetchWeather(city) }
            )
            Spacer(modifier = Modifier.height(30.dp))


            when (weatherUiState) {
                is WeatherUiState.IsLoading -> CircularProgressIndicator()
                is WeatherUiState.Error -> Text(text = (weatherUiState as WeatherUiState.Error).error)
                is WeatherUiState.Success -> WeatherDetails((weatherUiState as WeatherUiState.Success).data)
                WeatherUiState.IdleState -> {}
            }
        }
    }
}

@Composable
fun WeatherDetails(weatherData: WeatherData) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly, // Space between cards in this row
    ) {
        WeatherDetailsCard(
            title = weatherData.weather[0].main.toString(),
            icon = weatherData.weather[0].icon.toString(),
            subText = weatherData.weather[0].icon.toString(),
        )
        WeatherDetailsCard(
            title = weatherData.weather[0].main.toString(),
            icon = weatherData.weather[0].icon.toString(),
            subText = weatherData.weather[0].icon.toString(),
        )
    }

    Spacer(Modifier.height(20.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        WeatherDetailsCard(
            title = weatherData.weather[0].main.toString(),
            icon = weatherData.weather[0].icon.toString(),
            subText = weatherData.weather[0].icon.toString(),
        )
        WeatherDetailsCard(
            title = weatherData.weather[0].main.toString(),
            icon = weatherData.weather[0].icon.toString(),
            subText = weatherData.weather[0].icon.toString(),
        )
    }
}

@Composable
fun WeatherDetailsCard(title: String, icon: String, subText: String) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column() {
            Text(text = title)
            Log.d("WeatherIcon", icon.toString())
            AsyncImage(
                model = "https://openweathermap.org/img/wn/$icon@2x.png\n",
                contentDescription = null,
                modifier = Modifier
            )
            Text(text = subText)
        }
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



