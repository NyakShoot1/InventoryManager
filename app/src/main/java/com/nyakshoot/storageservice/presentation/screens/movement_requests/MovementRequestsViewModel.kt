package com.nyakshoot.storageservice.presentation.screens.movement_requests

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.domain.repository.IMovementRepository
import com.nyakshoot.storageservice.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MovementRequestsViewModel @Inject constructor(
    private val iMovementRepository: IMovementRepository
) : ViewModel() {
    private val _movementRequestsUIState = mutableStateOf(MovementRequestsUIState())
    val movementRequestsUIState: State<MovementRequestsUIState> = _movementRequestsUIState

    private fun updateUIState(update: MovementRequestsUIState.() -> MovementRequestsUIState) {
        _movementRequestsUIState.value = _movementRequestsUIState.value.update()
    }

    fun getMovementRequests() = viewModelScope.launch {
        if (_movementRequestsUIState.value.movements.status != Resource.Status.LOADING){
            updateUIState { copy(movements = _movementRequestsUIState.value.movements) }
        }else {
            updateUIState { copy(movements = Resource.loading()) }
            try {
                val response = iMovementRepository.getMovementRequests()
                Log.d("fdasdfs", response.toString())
                if (response.data != null) {
                    updateUIState { copy(movements = Resource.success(response.data)) }
                } else {
                    updateUIState { copy(movements = Resource.error("Failed to load movements", null)) }
                }
            } catch (e: Exception) {
                updateUIState { copy(movements = Resource.error("Error loading movements", null)) }
            }
        }
        Log.d("dasdas", _movementRequestsUIState.value.movements.toString())
    }

//    fun changeMovementStatus(movementId: Int, newStatus: Boolean) = viewModelScope.launch {
//        try {
//            val response = iMovementRepository.updateMovementStatus(movementId, newStatus)
//            if (response.isSuccessful) {
//                getMovementRequests()  // Обновляем список после изменения
//            } else {
//                // Обработка ошибки
//            }
//        } catch (e: Exception) {
//            // Обработка ошибки
//        }
//    }

    fun toggleExpandedMovement(movementId: Int?) {
        updateUIState {
            copy(expandedMovementId = movementId)
        }
    }
}