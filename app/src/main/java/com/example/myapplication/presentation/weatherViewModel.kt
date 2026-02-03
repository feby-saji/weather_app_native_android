package com.example.myapplication.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.WeatherApi
import com.google.gson.JsonParseException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException

class WeatherViewModel : ViewModel() {
    companion object {
        private const val TAG = "WeatherViewModel"
    }

    private val _weatherUiState = MutableStateFlow<WeatherUiState>(value = WeatherUiState.IdleState)

    val weatherUiState: StateFlow<WeatherUiState> = _weatherUiState

    private val weatherApi = WeatherApi.create()

    fun fetchWeather(city: String) {
        Log.d(TAG, "fetching the weather data")
        viewModelScope.launch {
            try {
                _weatherUiState.value = WeatherUiState.IsLoading
                val apiKey = BuildConfig.WEATHER_API_KEY
                Log.i("WeatherViewModel api key", "API Key: $apiKey")
                val response = weatherApi.getWeather(city, apikey = apiKey);

                _weatherUiState.value = WeatherUiState.Success(response)

                Log.d(TAG, "fetched weather data : ${weatherUiState.value.toString()}")
            } catch (e: IOException) {
                Log.e(
                    TAG,
                    "error occurred while fetching weather data : ${e}"
                )
                _weatherUiState.value =
                    WeatherUiState.Error("something went wrong ${e.localizedMessage}")
            } catch (e: JsonParseException) {
                Log.e(
                    TAG,
                    "error occurred while fetching weather data : ${e}"
                )
                _weatherUiState.value =
                    WeatherUiState.Error("something went wrong ${e.localizedMessage}")
            } catch (e: Exception) {
                Log.e(
                    TAG,
                    "error occurred while fetching weather data : ${e}"
                )
                _weatherUiState.value =
                    WeatherUiState.Error("something went wrong ${e.localizedMessage}")
            }
        }

    }
}