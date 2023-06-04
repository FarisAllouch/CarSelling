package com.example.foodapp.feature_food.presentation.add_edit_car

import android.app.Instrumentation.ActivityResult
import android.graphics.Picture
import android.net.Uri
import android.widget.Space
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodapp.feature_food.domain.model.Car
import com.example.foodapp.feature_food.presentation.add_edit_car.components.Picture
import com.example.foodapp.feature_food.presentation.add_edit_car.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditCarScreen(
    navController: NavController,
    carColor: Int,
    viewModel: AddEditCarViewModel = hiltViewModel()
){
    val stateBrand = viewModel.carBrand.value
    val statePrice = viewModel.carPrice.value
    val stateManYear = viewModel.carManYear.value
    val stateTravelledKm = viewModel.carTravelledKm.value
    val stateEngineType = viewModel.carEngineType.value
    val stateHorsePower = viewModel.carHorsePower.value
    val stateTransmission = viewModel.carTransmission.value
    val stateSeats = viewModel.carSeats.value
    val scaffoldState = rememberScaffoldState()

    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(
                if (carColor != -1) {
                    carColor
                } else {
                    viewModel.carColor.value
                }
            )
        )
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(
        key1 = true
    ){
        viewModel.eventFlow.collectLatest {event ->
            when(event){
                is AddEditCarViewModel.UiEvent.showSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditCarViewModel.UiEvent.SaveCar -> {
                    navController.navigateUp() //getting back to notes screen.
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditCarEvent.SaveCar)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save car",
                )
            }
        },
        scaffoldState = scaffoldState,
    )  {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Car.carColors.forEach { color -> //making little circle for every color.
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.carColor.value == colorInt) { //if circle is selected its getting black border
                                    Color.Black
                                } else { // else its getting transparent border.
                                    Color.Transparent
                                },
                                shape = CircleShape
                            )
                            .clickable { //if user clicks one of color circles ir will trigget this animation to change background color of screen.
                                scope.launch {
                                    noteBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        ),
                                    )
                                }
                                viewModel.onEvent(AddEditCarEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(82.dp)
            ) {
                TransparentHintTextField(
                    modifier = Modifier.weight(1f),
                    text = stateBrand.text,
                    hint = stateBrand.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditCarEvent.EnteredBrand(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditCarEvent.ChangeFocusBrand(it))
                    },
                    isHintVisible = stateBrand.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5,
                    label = "Brand"
                )

                Spacer(modifier = Modifier.width(8.dp))

                TransparentHintTextField(
                    modifier = Modifier.weight(1f),
                    text = statePrice.text,
                    hint = statePrice.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditCarEvent.EnteredPrice(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditCarEvent.ChangeFocusPrice(it))
                    },
                    isHintVisible = statePrice.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5,
                    label = "Price"
                )
            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(82.dp)
            ) {
                TransparentHintTextField(
                    modifier = Modifier.weight(1f),
                    text = stateManYear.text,
                    hint = stateManYear.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditCarEvent.EnteredManYear(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditCarEvent.ChangeFocusManYear(it))
                    },
                    isHintVisible = stateManYear.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5,
                    label = "ManuYear"
                )

                Spacer(modifier = Modifier.width(8.dp))

                TransparentHintTextField(
                    modifier = Modifier.weight(1f),
                    text = stateTravelledKm.text,
                    hint = stateTravelledKm.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditCarEvent.EnteredTravelledKm(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditCarEvent.ChangeFocusTravelledKm(it))
                    },
                    isHintVisible = stateTravelledKm.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5,
                    label = "Travelled Km",
                )
            }




            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(82.dp)
            ) {
                TransparentHintTextField(
                    modifier = Modifier.weight(1f),
                    text = stateEngineType.text,
                    hint = stateEngineType.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditCarEvent.EnteredEngineType(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditCarEvent.ChangeFocusEngineType(it))
                    },
                    isHintVisible = stateEngineType.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5,
                    label = "Engine type",
                )

                Spacer(modifier = Modifier.width(8.dp))

                TransparentHintTextField(
                    modifier = Modifier.weight(1f),
                    text = stateHorsePower.text,
                    hint = stateHorsePower.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditCarEvent.EnteredHorsePower(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditCarEvent.ChangeFocusHorsePower(it))
                    },
                    isHintVisible = stateHorsePower.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5,
                    label = "Horse power",
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(82.dp)
            ) {
                TransparentHintTextField(
                    modifier = Modifier.weight(1f),
                    text = stateTransmission.text,
                    hint = stateTransmission.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditCarEvent.EnteredTransmission(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditCarEvent.ChangeFocusTransmission(it))
                    },
                    isHintVisible = stateTransmission.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5,
                    label = "Transmission",
                )

                Spacer(modifier = Modifier.width(8.dp))

                TransparentHintTextField(
                    modifier = Modifier.weight(1f),
                    text = stateSeats.text,
                    hint = stateSeats.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditCarEvent.EnteredSeats(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditCarEvent.ChangeFocusSeats(it))
                    },
                    isHintVisible = stateSeats.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5,
                    label = "Seats",
                )
            }

            var selectedImage by remember {
                mutableStateOf<Uri?>(null)
            }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ){ uri ->
                selectedImage = uri
            }
            //SLIKA
            Picture(selectedImage = selectedImage){
                launcher.launch("image/*")
            }
        }
    }
}