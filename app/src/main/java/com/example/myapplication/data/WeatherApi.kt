package com.example.myapplication.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apikey: String,
        @Query("units") units: String = "metric",
    ): WeatherData

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

        fun create(): WeatherApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(WeatherApi::class.java)
        }
    }
}