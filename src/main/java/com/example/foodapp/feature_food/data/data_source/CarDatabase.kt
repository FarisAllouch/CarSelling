package com.example.foodapp.feature_food.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodapp.feature_food.domain.model.Car

@Database(entities = [Car::class], version = 1)
abstract class CarDatabase: RoomDatabase(){
    abstract val carDao: CarDao

    companion object{
        const val DATABASE_NAME = "car_db"
    }
}