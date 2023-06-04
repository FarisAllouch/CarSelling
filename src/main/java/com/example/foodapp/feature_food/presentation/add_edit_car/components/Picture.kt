package com.example.foodapp.feature_food.presentation.add_edit_car.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun Picture(
    selectedImage: Uri? = null,
    onImageClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    )  {
        if(selectedImage != null){
            Image(
                painter = rememberImagePainter(data = selectedImage),
                contentDescription = "Selected image",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onImageClick
                    }
            )
        }else{
            OutlinedButton(onClick = onImageClick) {
                Text(text = "Choose image")
            }
        }
    }
}
