package com.nyakshoot.storageservice.presentation.screens.done_shipments

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.domain.repository.IShipmentRepository
import com.nyakshoot.storageservice.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoneShipmentsViewModel @Inject constructor(
    private val iShipmentRepository: IShipmentRepository
) : ViewModel() {
    private val _doneShipmentsUIState = mutableStateOf(DoneShipmentsUIState())
    val doneShipmentsUIState: State<DoneShipmentsUIState> = _doneShipmentsUIState

    private fun updateUIState(update: DoneShipmentsUIState.() -> DoneShipmentsUIState) {
        _doneShipmentsUIState.value = _doneShipmentsUIState.value.update()
    }

    fun getDoneShipments() = viewModelScope.launch {
        if (_doneShipmentsUIState.value.doneShipments.status != Resource.Status.LOADING){
            updateUIState { copy(isLoading = false, doneShipments = _doneShipmentsUIState.value.doneShipments) }
        }else {
            updateUIState { copy(isLoading = true, doneShipments = Resource.loading()) }
            try {
                val response = iShipmentRepository.getDoneShipments()
                if (response.data != null) {
                    updateUIState { copy(isLoading = false, doneShipments = Resource.success(response.data)) }
                } else {
                    updateUIState { copy(isLoading = false, doneShipments = Resource.error("Failed to load shipments", null)) }
                }
            } catch (e: Exception) {
                updateUIState { copy(isLoading = false, doneShipments = Resource.error("Error loading shipments", null)) }
            }
        }
    }
}