package com.example.foodapp.feature_food.domain.repository

import com.example.foodapp.feature_food.domain.model.Car
import kotlinx.coroutines.flow.Flow

interface CarRepository {

    fun getCars(): Flow<List<Car>>
    suspend fun getCarById(id: Int): Car?
    suspend fun insertCar(car: Car)
    suspend fun deleteCar(car: Car)
}