package com.example.foodapp.feature_food.domain.use_cases

data class CarUseCases(
    val addCarUseCase: AddCarUseCase,
    val deleteCarUseCase: DeleteCarUseCase,
    val getCarUseCase: GetCarUseCase,
    val getCarsUseCase: GetCarsUseCase,
)
