package com.nyakshoot.storageservice.presentation.screens.item_base

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
import androidx.navigation.NavController
import com.google.gson.Gson
import com.nyakshoot.storageservice.presentation.navigation.Screen
import com.nyakshoot.storageservice.presentation.screens.item_base.components.ItemCard
import com.nyakshoot.storageservice.utils.Resource

@Composable
fun ItemBaseScreen(
    navController: NavController,
    viewModel: ItemBaseViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getItems()
    }

    when (viewModel.inputItemDataUIState.value.items.status) {
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
                items(viewModel.inputItemDataUIState.value.items.data!!.size) { index ->
                    ItemCard(
                        itemName = viewModel.inputItemDataUIState.value.items.data!![index].name,
                        itemNumber = viewModel.inputItemDataUIState.value.items.data!![index].itemNumber!!,
                        cost = viewModel.inputItemDataUIState.value.items.data!![index].cost!!
                    ) {
                        val itemJson =
                            Gson().toJson(viewModel.inputItemDataUIState.value.items.data!![index])
                        navController.navigate(Screen.ItemDetail.route.replace("{item}", itemJson))
                    }
                }
            }
        }

        Resource.Status.ERROR -> {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
    }


}