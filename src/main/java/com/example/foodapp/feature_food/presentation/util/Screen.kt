package com.example.foodapp.feature_food.presentation.util

sealed class Screen(
    val route: String
    ) {
    object CarsScreen: Screen("cars_screen")
    object AddEditCarScreen: Screen("add_edit_car_screen")
}