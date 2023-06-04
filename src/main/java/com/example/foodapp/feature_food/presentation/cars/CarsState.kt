package com.example.foodapp.feature_food.presentation.cars

import com.example.foodapp.feature_food.domain.model.Car
import com.example.foodapp.feature_food.domain.util.CarOrder
import com.example.foodapp.feature_food.domain.util.OrderType

data class CarsState (
    val cars: List<Car> = emptyList(),
    val carOrder: CarOrder = CarOrder.Price(OrderType.Ascending),
    val isOrderSectionVisisble: Boolean = false,
    )