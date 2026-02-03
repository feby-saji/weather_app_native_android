package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.Main
import com.example.myapplication.data.Weather
import com.example.myapplication.data.WeatherData
import com.example.myapplication.presentation.pages.BuildTextFieldWithButton
import com.example.myapplication.presentation.pages.WeatherDetails
import com.example.myapplication.presentation.pages.WeatherPage
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                WeatherPage()
            }
        }
    }
}


@Preview(
    device = Devices.PIXEL_7,
    showSystemUi = true
)
@Composable
fun PreviewScreen() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            var city by remember { mutableStateOf("city") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                Text(text = "Weather App")
                BuildTextFieldWithButton(
                    city,
                    newValue = { city = it },
                    onBtnClick = { }
                )
                WeatherDetails(weatherData = fakeWeatherData)
            }
        }
    }
}


val fakeWeatherData = WeatherData(
    weather = listOf(
        Weather(
            id = 800,
            main = "Clear",
            description = "clear sky",
            icon = "01d"
        )
    ),
    main = Main(
        temp = 298.15,      // ~25°C
        pressure = 1013,
        humidity = 60
    ),
    timezone = 19800,      // IST
    id = 1277333
)
