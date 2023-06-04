package com.example.foodapp.feature_food.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodapp.feature_food.presentation.add_edit_car.AddEditCarScreen
import com.example.foodapp.feature_food.presentation.cars.CarsScreen
import com.example.foodapp.feature_food.presentation.util.Screen
import com.example.foodapp.ui.theme.FoodAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                )  {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CarsScreen.route,
                    ){

                        composable(route = Screen.CarsScreen.route){
                            CarsScreen(navController = navController)
                        }

                        composable(
                            route = Screen.AddEditCarScreen.route +
                                    "?carId={carId}&carColor={carColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "carId"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                },

                                navArgument(
                                    name = "carColor"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ){
                            val color = it.arguments?.getInt("carColor") ?: -1
                            AddEditCarScreen(
                                navController = navController,
                                carColor = color,
                            )
                        }
                    }
                }
            }
        }
    }
}
