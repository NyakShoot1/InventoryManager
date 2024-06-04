package com.nyakshoot.storageservice.presentation.screens.position_set_place

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.data.dto.place.PlaceWithPositionsDTO
import com.nyakshoot.storageservice.data.dto.position.UpdatePlacePositionDTO
import com.nyakshoot.storageservice.domain.repository.IPlaceRepository
import com.nyakshoot.storageservice.domain.repository.IPositionRepository
import com.nyakshoot.storageservice.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PositionSetPlaceViewModel @Inject constructor(
    private val iPlaceRepository: IPlaceRepository,
    private val iPositionRepository: IPositionRepository
) : ViewModel() {
    private val _positionSetPlaceUIState = mutableStateOf(PositionSetPlaceUIState())
    val positionSetPlaceUIState: State<PositionSetPlaceUIState> = _positionSetPlaceUIState

    private fun updateUIState(update: PositionSetPlaceUIState.() -> PositionSetPlaceUIState) {
        _positionSetPlaceUIState.value = _positionSetPlaceUIState.value.update()
    }

    fun getPlacesByStorage(storageId: Int) = viewModelScope.launch {
        if (_positionSetPlaceUIState.value.places.status != Resource.Status.LOADING) {
            updateUIState { copy(places = _positionSetPlaceUIState.value.places) }
        } else {
            updateUIState { copy(places = Resource.loading()) }
            try {
                val response = iPlaceRepository.getPlacesByStorageId(storageId)
                if (response.data != null) {
                    updateUIState { copy(places = Resource.success(response.data)) }
                } else {
                    updateUIState { copy(places = Resource.error("Failed to load places", null)) }
                }
            } catch (e: Exception) {
                updateUIState { copy(places = Resource.error("Error loading places", null)) }
            }
        }
    }

    private val _doneRequestState = mutableStateOf(Resource.loading(false))
    val doneRequestState: State<Resource<Boolean>> = _doneRequestState
    fun setPlaceToPosition() = viewModelScope.launch {
        val newPlace = UpdatePlacePositionDTO(
            positionId = _positionSetPlaceUIState.value.selectedPosition,
            storageId = 1,
            placeCode = _positionSetPlaceUIState.value.selectedPlace.value!!.place.placeCode,
            placeNumber = _positionSetPlaceUIState.value.selectedPlace.value!!.place.placeNumber
        )
        try {
            val response = iPositionRepository.setPlaceToPosition(newPlace)
            if (response.data != null)
                _doneRequestState.value = Resource.success(true)
        } catch (e: Exception) {
            updateUIState { copy(places = Resource.error("Error loading places", null)) }
        }
    }

    fun setSelectedPosition(positionId: Int) {
        _positionSetPlaceUIState.value.selectedPosition = positionId
    }

    fun setSelectedPlace(place: PlaceWithPositionsDTO?) {
        updateUIState {
            copy(
                selectedPlace = mutableStateOf(place)
            )
        }
    }
}