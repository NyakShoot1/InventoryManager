package com.nyakshoot.storageservice.presentation.screens.movement_requests

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nyakshoot.storageservice.presentation.screens.movement_requests.components.MovementRequestCard
import com.nyakshoot.storageservice.utils.Resource

@Composable
fun MovementRequestsScreen(
    viewModel: MovementRequestsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        viewModel.getMovementRequests()
    }

    when (viewModel.movementRequestsUIState.value.movements.status) {
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
                items(viewModel.movementRequestsUIState.value.movements.data!!) { movement ->
                    MovementRequestCard(
                        movement = movement,
                        expanded = viewModel.movementRequestsUIState.value.expandedMovementId == movement.id,
                        onCardClick = { viewModel.toggleExpandedMovement(movement.id) },
                        onStatusChange = {  }
                    )
                }
            }
        }
        Resource.Status.ERROR -> {
            // Обработка ошибки
        }
    }
}