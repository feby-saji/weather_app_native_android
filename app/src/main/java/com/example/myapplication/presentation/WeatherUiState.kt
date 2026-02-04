package com.example.myapplication.presentation

import WeatherData

sealed interface WeatherUiState {
    object IdleState : WeatherUiState
    object IsLoading : WeatherUiState
    data class Success(val data: WeatherData) : WeatherUiState
    data class Error(val error: String) : WeatherUiState
}