package com.example.foodapp.feature_food.domain.use_cases

import com.example.foodapp.feature_food.domain.model.Car
import com.example.foodapp.feature_food.domain.model.InvalidCarException
import com.example.foodapp.feature_food.domain.repository.CarRepository
import kotlin.jvm.Throws

class AddCarUseCase(
    private val repository: CarRepository
) {
    @Throws(InvalidCarException::class)
    suspend operator fun invoke(car: Car){
        if(car.brand.isEmpty()){
            throw InvalidCarException("Field is empty.")
        }
        if(car.engineType.isEmpty()){
            throw InvalidCarException("Field is empty.")
        }
        if(car.horsePower.isEmpty()){
            throw InvalidCarException("Field is empty.")
        }
        if(car.manufactureYear.isEmpty()){
            throw InvalidCarException("Field is empty.")
        }
        if(car.price.isEmpty()) {
            throw InvalidCarException("Field is empty.")
        }
        if(car.seats.isEmpty()){
            throw InvalidCarException("Field is empty.")
        }
        if(car.transmission .isEmpty()){
            throw InvalidCarException("Field is empty.")
        }
        if(car.travelledKm .isEmpty()){
            throw InvalidCarException("Field is empty.")
        }
        repository.insertCar(car = car)
    }
}