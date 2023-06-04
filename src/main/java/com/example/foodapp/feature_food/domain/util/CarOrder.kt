package com.example.foodapp.feature_food.domain.util

sealed class CarOrder(val orderType: OrderType) {
    class Transmission(orderType: OrderType): CarOrder(orderType = orderType)
    class ManufactureYear(orderType: OrderType): CarOrder(orderType = orderType)
    class Price(orderType: OrderType): CarOrder(orderType = orderType)
    class TravelledKm(orderType: OrderType): CarOrder(orderType = orderType)

    fun copy(orderType: OrderType): CarOrder{
        return when(this){
            is Transmission -> Transmission(orderType = orderType)
            is ManufactureYear -> ManufactureYear(orderType = orderType)
            is Price -> Price(orderType = orderType)
            is TravelledKm -> TravelledKm(orderType = orderType)
        }
    }
}