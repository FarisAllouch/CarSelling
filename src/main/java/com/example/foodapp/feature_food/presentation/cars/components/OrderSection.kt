package com.example.foodapp.feature_food.presentation.cars.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodapp.feature_food.domain.util.CarOrder
import com.example.foodapp.feature_food.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    carOrder: CarOrder = CarOrder.Price(OrderType.Ascending),
    onOrderChange: (CarOrder) -> Unit,
) {
    Column (
        modifier = modifier
    ){
        
        Row(
            modifier = modifier
        )  {
            DefaultRadioButton(
                text = "Price",
                selected = carOrder is CarOrder.Price,
                onSelect = {
                    onOrderChange(CarOrder.Price(carOrder.orderType))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Transmission",
                selected = carOrder is CarOrder.Transmission,
                onSelect = {
                    onOrderChange(CarOrder.Transmission(carOrder.orderType))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))

        }

        Row(
            modifier = modifier
        )  {
            DefaultRadioButton(
                text = "Manu Year",
                selected = carOrder is CarOrder.ManufactureYear,
                onSelect = {
                    onOrderChange(CarOrder.ManufactureYear(carOrder.orderType))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Travelled km",
                selected = carOrder is CarOrder.TravelledKm,
                onSelect = {
                    onOrderChange(CarOrder.TravelledKm(carOrder.orderType))
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = carOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(carOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = carOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(carOrder.copy(OrderType.Descending))
                }
            )
        }
        
    }
}