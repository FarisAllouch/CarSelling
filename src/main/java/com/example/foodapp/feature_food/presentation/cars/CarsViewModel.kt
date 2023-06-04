package com.example.foodapp.feature_food.presentation.cars

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.feature_food.domain.model.Car
import com.example.foodapp.feature_food.domain.use_cases.CarUseCases
import com.example.foodapp.feature_food.domain.util.CarOrder
import com.example.foodapp.feature_food.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val carUseCases: CarUseCases
) : ViewModel(){

    private val _state = mutableStateOf(CarsState())
    val state: State<CarsState> = _state


    //reference to the last deleted note.
    private var recentlyDeletedCar: Car? = null

    private var getCarsJob: Job? = null

    init {
        getCars(CarOrder.Price(OrderType.Ascending))
    }


    fun onEvent(event: CarsEvent){
        when(event){
            is CarsEvent.Order -> {
                if(
                    state.value.carOrder::class == event.carOrder::class &&
                    state.value.carOrder.orderType == event.carOrder.orderType
                ){
                    return
                }
                getCars(carOrder = event.carOrder)
            }
            is CarsEvent.DeleteCar ->{
                viewModelScope.launch {
                    recentlyDeletedCar = event.car
                    carUseCases.deleteCarUseCase(event.car)
                }
            }
            is CarsEvent.RestoreCar -> {
                viewModelScope.launch {
                    carUseCases.addCarUseCase(recentlyDeletedCar ?: return@launch)
                    recentlyDeletedCar = null
                }
            }
            is CarsEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisisble = !state.value.isOrderSectionVisisble
                )
            }
        }
    }



    private fun getCars(carOrder: CarOrder) {
        getCarsJob?.cancel()
        getCarsJob = carUseCases.getCarsUseCase(carOrder = carOrder)
            .onEach { cars ->
                _state.value = state.value.copy(
                    cars = cars,
                    carOrder = carOrder
                )
            }
            .launchIn(viewModelScope)
    }
}