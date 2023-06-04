package com.example.foodapp.feature_food.domain.use_cases

import com.example.foodapp.feature_food.domain.model.Car
import com.example.foodapp.feature_food.domain.repository.CarRepository

class GetCarUseCase (
    private val repository: CarRepository
){
    suspend operator fun invoke(id: Int): Car?{
        return repository.getCarById(id = id)
    }
}