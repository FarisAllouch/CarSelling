package com.example.foodapp.feature_food.presentation.cars

import com.example.foodapp.feature_food.domain.model.Car
import com.example.foodapp.feature_food.domain.util.CarOrder

sealed class CarsEvent {
    data class Order(val carOrder: CarOrder): CarsEvent()
    data class DeleteCar(val car: Car): CarsEvent()
    object RestoreCar: CarsEvent()
    object ToggleOrderSection: CarsEvent()
}