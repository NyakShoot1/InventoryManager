package com.nyakshoot.storageservice.presentation.screens.position_set_place

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nyakshoot.storageservice.presentation.navigation.Screen
import com.nyakshoot.storageservice.presentation.screens.position_set_place.components.PlaceCard
import com.nyakshoot.storageservice.utils.Resource

@Composable
fun PositionSetPlaceScreen(
    navController: NavController,
    positionId: Int,
    viewModel: PositionSetPlaceViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getPlacesByStorage(1)
        viewModel.setSelectedPosition(positionId)
    }

    when (viewModel.positionSetPlaceUIState.value.places.status) {
        Resource.Status.LOADING -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        Resource.Status.SUCCESS -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().weight(0.8f)
                ) {
                    items(viewModel.positionSetPlaceUIState.value.places.data!!) { place ->
                        PlaceCard(
                            place = place,
                            isSelected = viewModel.positionSetPlaceUIState.value.selectedPlace.value == place,
                            onSelected = {
                                if (it) {
                                    viewModel.setSelectedPlace(place)
                                } else {
                                    viewModel.setSelectedPlace(null)
                                }
                            }
                        )
                    }
                }
                Button(onClick = { viewModel.setPlaceToPosition() }) {
                    Text(text = "ГОТОВО", color = MaterialTheme.colors.secondary)
                }
            }

            when (viewModel.doneRequestState.value.status) {
                Resource.Status.SUCCESS -> {
//                    Toast.makeText(context, "Успешно выполнено", Toast.LENGTH_SHORT).show()
                    navController.popBackStack(Screen.PositionSetPlace.route, inclusive = true)
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> { }
            }
        }

        Resource.Status.ERROR -> {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
    }
}