package com.example.myapplication.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val WeatherColorScheme = darkColorScheme(
    primary = WeatherWhite,
    onPrimary = WeatherNavy,

    secondary = WeatherWhiteSoft,
    onSecondary = WeatherNavy,

    tertiary = WeatherWhiteFaint,
    onTertiary = WeatherNavy,

    background = Color.Transparent,
    onBackground = WeatherWhite,

    surface = Color.Transparent,
    onSurface = WeatherWhite,

    surfaceVariant = WeatherBlue,
    onSurfaceVariant = WeatherWhiteSoft,

    outline = WeatherDividerAlpha,
    outlineVariant = WeatherDividerAlpha,

    error = Color(0xFFCF6679),
    onError = WeatherWhite
)

@Composable
fun WeatherAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = WeatherColorScheme,
        typography = Typography,
        content = content
    )
}