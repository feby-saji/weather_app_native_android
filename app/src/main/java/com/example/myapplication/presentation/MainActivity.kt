package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myapplication.presentation.pages.WeatherPage
import com.example.myapplication.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                WeatherPage()
            }
        }
    }
}


//@Preview(
//    device = Devices.PIXEL_7,
//    showSystemUi = true
//)
//@Composable
//fun PreviewScreen() {
//    MyApplicationTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = Color.White
//        ) {
//            var city by remember { mutableStateOf("city") }
//
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Top
//            ) {
//                Spacer(modifier = Modifier.height(100.dp))
//                Text(text = "Weather App")
//                BuildTextFieldWithButton(
//                    city,
//                    newValue = { city = it },
//                    onBtnClick = { }
//                )
//                WeatherDetails(weatherData = fakeWeatherData)
//            }
//        }
//    }
//}


//val fakeWeatherData = WeatherData(
//    weather = listOf(
//        Weather(
//            id = 800,
//            main = "Clear",
//            description = "clear sky",
//            icon = "01d"
//        )
//    ),
//    main = Main(
//        temp = 298.15,      // ~25°C
//        pressure = 1013,
//        humidity = 60
//    ),
//    timezone = 19800,      // IST
//    id = 1277333
//)
