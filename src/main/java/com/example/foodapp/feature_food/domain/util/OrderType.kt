package com.example.foodapp.feature_food.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}