package com.nyakshoot.storageservice.presentation.screens.done_shipments

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.nyakshoot.storageservice.presentation.navigation.Screen
import com.nyakshoot.storageservice.presentation.screens.done_shipments.components.DoneShipmentItem
import com.nyakshoot.storageservice.utils.Resource
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DoneShipmentsScreen(
    navController: NavHostController,
    viewModel: DoneShipmentsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getDoneShipments()
    }

    when (viewModel.doneShipmentsUIState.value.doneShipments.status) {
        Resource.Status.LOADING -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        Resource.Status.SUCCESS -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.doneShipmentsUIState.value.doneShipments.data!!.size) { index ->
                    val dateTimeString = parseDateTime(viewModel.doneShipmentsUIState.value.doneShipments.data!![index].registerAt)
                    DoneShipmentItem(
                        shipmentNumber = (index+1).toString(),
                        shipmentDate = dateTimeString.first,
                        shipmentTime = dateTimeString.second
                    ) {
                        val itemJson =
                            Gson().toJson(viewModel.doneShipmentsUIState.value.doneShipments.data!![index])
                        navController.navigate(Screen.DoneShipmentDetail.route.replace("{shipment}", itemJson))
                    }
                }
            }
        }

        Resource.Status.ERROR -> {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
    }
}

fun parseDateTime(dateTimeString: String): Pair<String, String> {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    val dateTime = LocalDateTime.parse(dateTimeString, formatter)

    val formattedDate = dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    val formattedTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))

    return Pair(formattedDate, formattedTime)
}