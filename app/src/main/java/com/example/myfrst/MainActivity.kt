package com.example.myfrst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "weather") {
                composable("weather") { WeatherScreen(navController) }
                composable("seven_days") { SevenDaysScreen(navController) }
                composable("settings") { SettingsScreen(navController) }
                composable("choose_city") { ChooseCityScreen(navController) }
                composable("search") { SearchScreen(navController) }
            }
        }
    }
}