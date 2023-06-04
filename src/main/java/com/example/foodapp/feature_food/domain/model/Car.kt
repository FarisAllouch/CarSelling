package com.example.foodapp.feature_food.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodapp.ui.theme.BabyBlue
import com.example.foodapp.ui.theme.LightGreen
import com.example.foodapp.ui.theme.RedOrange
import com.example.foodapp.ui.theme.RedPink
import com.example.foodapp.ui.theme.Violet

@Entity
data class Car (
    val brand: String,
    val price: String,
    val manufactureYear: String,
    val travelledKm: String,
    val color: Int,
    val engineType: String,
    val horsePower: String,
    val transmission: String, //automatic or manual
    val seats: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
        ){


    companion object{
        val carColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidCarException(message: String): Exception(message)