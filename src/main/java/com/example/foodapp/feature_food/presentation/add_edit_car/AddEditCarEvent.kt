package com.example.foodapp.feature_food.presentation.add_edit_car

import androidx.compose.ui.focus.FocusState

sealed class AddEditCarEvent {

    data class EnteredBrand(val value: String): AddEditCarEvent()
    data class EnteredPrice(val value: String): AddEditCarEvent()
    data class EnteredManYear(val value: String): AddEditCarEvent()
    data class EnteredTravelledKm(val value: String): AddEditCarEvent()
    data class EnteredEngineType(val value: String): AddEditCarEvent()
    data class EnteredHorsePower(val value: String): AddEditCarEvent()
    data class EnteredTransmission(val value: String): AddEditCarEvent()
    data class EnteredSeats(val value: String): AddEditCarEvent()

    data class ChangeFocusBrand(val focusState: FocusState): AddEditCarEvent()
    data class ChangeFocusPrice(val focusState: FocusState): AddEditCarEvent()
    data class ChangeFocusManYear(val focusState: FocusState): AddEditCarEvent()
    data class ChangeFocusTravelledKm(val focusState: FocusState): AddEditCarEvent()
    data class ChangeFocusEngineType(val focusState: FocusState): AddEditCarEvent()
    data class ChangeFocusHorsePower(val focusState: FocusState): AddEditCarEvent()
    data class ChangeFocusTransmission(val focusState: FocusState): AddEditCarEvent()
    data class ChangeFocusSeats(val focusState: FocusState): AddEditCarEvent()

    data class ChangeColor(val color: Int): AddEditCarEvent()
    object SaveCar: AddEditCarEvent()

}