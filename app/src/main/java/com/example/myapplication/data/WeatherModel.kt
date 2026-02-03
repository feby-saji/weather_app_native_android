package com.example.myapplication.data


data class WeatherData(
    val weather: List<Weather>,
    val main: Main,
    val timezone: Long,
    val id: Long,
)
data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
)
data class Main(
    val temp: Double,
    val pressure: Long,
    val humidity: Long,
)
