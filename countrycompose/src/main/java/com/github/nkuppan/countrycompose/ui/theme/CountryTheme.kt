package com.github.nkuppan.countrycompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.github.nkuppan.country.core.R

private val DarkColors = darkColors(
    primary = Color(R.color.weather_green_300),
    primaryVariant = Color(R.color.weather_green_700),
    onPrimary = Color(R.color.black),
    secondary = Color(R.color.weather_blue_300),
    secondaryVariant = Color(R.color.weather_blue_300),
    onSecondary = Color(R.color.black),
    error = Color(R.color.weather_red_300),
    onError = Color(R.color.black),
)

private val LightColors = lightColors(
    primary = Color(R.color.weather_green_700),
    primaryVariant = Color(R.color.weather_green_900),
    onPrimary = Color(R.color.white),
    secondary = Color(R.color.weather_blue_900),
    secondaryVariant = Color(R.color.weather_blue_900),
    onSecondary = Color(R.color.white),
    error = Color(R.color.weather_red_600),
    onError = Color(R.color.white),
)

@Composable
fun CountryAppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (isDarkTheme) DarkColors else LightColors

    MaterialTheme(
        colors = colors,
        content = content
    )
}