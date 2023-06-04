package com.example.foodapp.feature_food.data.repository

import com.example.foodapp.feature_food.data.data_source.CarDao
import com.example.foodapp.feature_food.domain.model.Car
import com.example.foodapp.feature_food.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow

class CarRepositoryImpl(
    private val carDao: CarDao
): CarRepository {

    override fun getCars(): Flow<List<Car>> {
        return carDao.getCars()
    }

    override suspend fun getCarById(id: Int): Car? {
        return carDao.getCarById(id = id)
    }

    override suspend fun insertCar(car: Car) {
        return carDao.insertCar(car = car)
    }

    override suspend fun deleteCar(car: Car) {
        return carDao.deleteCar(car = car)
    }


}