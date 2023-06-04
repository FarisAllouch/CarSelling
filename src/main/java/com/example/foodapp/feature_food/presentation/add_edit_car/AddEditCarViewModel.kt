package com.example.foodapp.feature_food.presentation.add_edit_car

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.feature_food.domain.model.Car
import com.example.foodapp.feature_food.domain.model.InvalidCarException
import com.example.foodapp.feature_food.domain.use_cases.CarUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCarViewModel @Inject constructor(
    private val carUseCases: CarUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _carBrand = mutableStateOf(CarTextFieldState(
        hint = "Brand"
    ))
    val carBrand: State<CarTextFieldState> = _carBrand

    private val _carPrice = mutableStateOf(CarTextFieldState(
        hint = "Price"
    ))
    val carPrice: State<CarTextFieldState> = _carPrice

    private val _carManYear = mutableStateOf(CarTextFieldState(
        hint = "Manu year"
    ))
    val carManYear: State<CarTextFieldState> = _carManYear

    private val _carTravelledKm = mutableStateOf(CarTextFieldState(
        hint = "Travelled km..."
    ))
    val carTravelledKm: State<CarTextFieldState> = _carTravelledKm

    private val _carEngineType= mutableStateOf(CarTextFieldState(
        hint = "Engine type"
    ))
    val carEngineType: State<CarTextFieldState> = _carEngineType

    private val _carHorsePower= mutableStateOf(CarTextFieldState(
        hint = "HorsePower"
    ))
    val carHorsePower: State<CarTextFieldState> = _carHorsePower

    private val _carTransmission = mutableStateOf(CarTextFieldState(
        hint = "Transmission"
    ))
    val carTransmission: State<CarTextFieldState> = _carTransmission

    private val _carSeats = mutableStateOf(CarTextFieldState(
        hint = "Seats"
    ))
    val carSeats: State<CarTextFieldState> = _carSeats

    private val _carColor = mutableStateOf(Car.carColors.random().toArgb())
    val carColor: State<Int> = _carColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentCarId: Int? = null

    init {
        savedStateHandle.get<Int>("carId")?.let {carId: Int ->
            if(carId != -1) {//default we use when we click on floating button
                viewModelScope.launch {
                    carUseCases.getCarUseCase(id = carId)?.also {car ->
                        currentCarId = car.id

                        _carBrand.value = carBrand.value.copy(
                            text = car.brand,
                            isHintVisible = false,
                        )

                        _carPrice.value = carPrice.value.copy(
                            text = car.price,
                            isHintVisible = false,
                        )

                        _carSeats.value = carSeats.value.copy(
                            text = car.seats,
                            isHintVisible = false,
                        )

                        _carEngineType.value = carEngineType.value.copy(
                            text = car.engineType,
                            isHintVisible = false,
                        )

                        _carHorsePower.value = carHorsePower.value.copy(
                            text = car.horsePower,
                            isHintVisible = false,
                        )

                        _carManYear.value = carManYear.value.copy(
                            text = car.manufactureYear,
                            isHintVisible = false,
                        )

                        _carTransmission.value = carTransmission.value.copy(
                            text = car.transmission,
                            isHintVisible = false,
                        )

                        _carTravelledKm.value = carTravelledKm.value.copy(
                            text = car.travelledKm,
                            isHintVisible = false,
                        )

                        _carColor.value = car.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditCarEvent) {
        when (event) {
            is AddEditCarEvent.EnteredBrand -> {
                _carBrand.value = carBrand.value.copy(
                    text = event.value
                )
            }

            is AddEditCarEvent.EnteredPrice -> {
                _carPrice.value = carPrice.value.copy(
                    text = event.value
                )
            }

            is AddEditCarEvent.EnteredManYear -> {
                _carManYear.value = carManYear.value.copy(
                    text = event.value
                )
            }

            is AddEditCarEvent.EnteredTravelledKm -> {
                _carTravelledKm.value = carTravelledKm.value.copy(
                    text = event.value
                )
            }

            is AddEditCarEvent.EnteredEngineType -> {
                _carEngineType.value = carEngineType.value.copy(
                    text = event.value
                )
            }

            is AddEditCarEvent.EnteredHorsePower -> {
                _carHorsePower.value = carHorsePower.value.copy(
                    text = event.value
                )
            }

            is AddEditCarEvent.EnteredTransmission -> {
                _carTransmission.value = carTransmission.value.copy(
                    text = event.value
                )
            }

            is AddEditCarEvent.EnteredSeats -> {
                _carSeats.value = carSeats.value.copy(
                    text = event.value
                )
            }


            is AddEditCarEvent.ChangeFocusBrand -> {
                _carBrand.value = carBrand.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            carBrand.value.text.isBlank()
                )
            }

            is AddEditCarEvent.ChangeFocusPrice -> {
                _carPrice.value = carPrice.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            carPrice.value.text.isBlank()
                )
            }

            is AddEditCarEvent.ChangeFocusManYear -> {
                _carManYear.value = carManYear.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            carManYear.value.text.isBlank()
                )
            }

            is AddEditCarEvent.ChangeFocusTravelledKm -> {
                _carTravelledKm.value = carTravelledKm.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            carTravelledKm.value.text.isBlank()
                )
            }

            is AddEditCarEvent.ChangeFocusEngineType -> {
                _carEngineType.value = carEngineType.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            carEngineType.value.text.isBlank()
                )
            }

            is AddEditCarEvent.ChangeFocusHorsePower -> {
                _carHorsePower.value = carHorsePower.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            carHorsePower.value.text.isBlank()
                )
            }

            is AddEditCarEvent.ChangeFocusTransmission -> {
                _carTransmission.value = carTransmission.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            carTransmission.value.text.isBlank()
                )
            }

            is AddEditCarEvent.ChangeFocusSeats -> {
                _carSeats.value = carSeats.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            carSeats.value.text.isBlank()
                )
            }

            is AddEditCarEvent.ChangeColor -> {
                _carColor.value = event.color
            }

            is AddEditCarEvent.SaveCar -> {
                viewModelScope.launch {
                    try {
                        carUseCases.addCarUseCase(
                            Car(
                                brand = carBrand.value.text,
                                price = carPrice.value.text,
                                manufactureYear = carManYear.value.text,
                                travelledKm = carTravelledKm.value.text,
                                engineType = carEngineType.value.text,
                                horsePower = carHorsePower.value.text,
                                transmission = carTransmission.value.text,
                                seats = carSeats.value.text,
                                color = carColor.value
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveCar)
                    } catch (e: InvalidCarException) {
                        _eventFlow.emit(
                            UiEvent.showSnackbar(
                                message = e.message ?: "Couldn't save car."
                            )
                        )
                    }
                }
            }
        }
    }


    sealed class UiEvent{
        data class showSnackbar(val message: String): UiEvent()
        object SaveCar: UiEvent()
    }
}