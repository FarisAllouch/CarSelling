package com.example.foodapp.feature_food.domain.use_cases

import com.example.foodapp.feature_food.domain.model.Car
import com.example.foodapp.feature_food.domain.repository.CarRepository
import com.example.foodapp.feature_food.domain.util.CarOrder
import com.example.foodapp.feature_food.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCarsUseCase(
    private val repository: CarRepository
) {
    operator fun invoke(
        carOrder: CarOrder = CarOrder.Price(OrderType.Ascending)
    ): Flow<List<Car>>{
        return repository.getCars().map {cars ->
            when(carOrder.orderType){
                is OrderType.Ascending -> {
                    when(carOrder){
                        is CarOrder.Price ->cars.sortedBy { it.price }
                        is CarOrder.TravelledKm ->cars.sortedBy { it.travelledKm }
                        is CarOrder.ManufactureYear ->cars.sortedBy { it.manufactureYear }
                        is CarOrder.Transmission ->cars.sortedBy { it.transmission }
                    }
                }
                is OrderType.Descending -> {
                    when(carOrder){
                        is CarOrder.Price ->cars.sortedByDescending { it.price }
                        is CarOrder.TravelledKm ->cars.sortedByDescending { it.travelledKm }
                        is CarOrder.ManufactureYear ->cars.sortedByDescending { it.manufactureYear }
                        is CarOrder.Transmission ->cars.sortedByDescending { it.transmission }
                    }
                }
            }
        }
    }
}