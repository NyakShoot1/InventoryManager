package com.nyakshoot.storageservice.presentation.screens.movement_create

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.data.dto.movement.NewMovementRequestDTO
import com.nyakshoot.storageservice.data.dto.place.PlaceWithPositionsDTO
import com.nyakshoot.storageservice.data.dto.storage.StorageDTO
import com.nyakshoot.storageservice.domain.repository.IMovementRepository
import com.nyakshoot.storageservice.domain.repository.IPlaceRepository
import com.nyakshoot.storageservice.domain.repository.IStorageRepository
import com.nyakshoot.storageservice.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovementCreateViewModel @Inject constructor(
    private val iPlaceRepository: IPlaceRepository,
    private val iStorageRepository: IStorageRepository,
    private val iMovementRepository: IMovementRepository
) : ViewModel() {

    private val _movementCreateUIState = mutableStateOf(MovementCreateUIState())
    val movementCreateUIState: State<MovementCreateUIState> = _movementCreateUIState

    private fun updateUIState(update: MovementCreateUIState.() -> MovementCreateUIState) {
        _movementCreateUIState.value = _movementCreateUIState.value.update()
    }

    private val _doneRequestState = mutableStateOf(Resource.loading(false))
    val doneRequestState: State<Resource<Boolean>> = _doneRequestState

    fun getPlaces(storageId: Int) = viewModelScope.launch {
        updateUIState { copy(fromPlaces = Resource.loading()) }
        try {
            val response = iPlaceRepository.getPlacesByStorageId(storageId)
            if (response.data != null) {
                val filteredPlaces = response.data.filter { it.positions.isNotEmpty() }
                updateUIState {
                    copy(
                        fromPlaces = Resource.success(filteredPlaces),
                        wherePlaces = Resource.success(response.data)
                    )
                }
            } else {
                updateUIState {
                    copy(
                        fromPlaces = Resource.error(
                            "Failed to load fromPlaces",
                            null
                        )
                    )
                }
            }
        } catch (e: Exception) {
            updateUIState { copy(fromPlaces = Resource.error("Failed to load fromPlaces", null)) }
        }
    }

    fun createNewMovement() = viewModelScope.launch {
        var where: Int = 0
        val type: Boolean
        if (_movementCreateUIState.value.selectedWhereStorage.value != null){
            where = _movementCreateUIState.value.selectedWhereStorage.value!!.id
            type = false
        }else{
            where = _movementCreateUIState.value.selectedWherePlace.value?.place?.id!!
            type = true
        }
        val newMovement = NewMovementRequestDTO(
            from = _movementCreateUIState.value.selectedFromPlace.value?.place?.id!!,
            where = where,
            type = type,
            userId = 1
        )

        try {
            val response = iMovementRepository.createMovement(newMovement)
            if (response.data != null)
                _doneRequestState.value = Resource.success(true)
        } catch (ex: Exception) {
            Log.d("JOB_GG", ex.toString())
        }
    }

    fun getWhereStorages() = viewModelScope.launch {
        updateUIState { copy(fromPlaces = Resource.loading()) }
        try {
            val response = iStorageRepository.getStorages()
            if (response.data != null) {
                updateUIState {
                    copy(
                        //todo убрать текущий склад из списка
                        whereStorages = Resource.success(response.data)
                    )
                }
            } else {
                updateUIState {
                    copy(
                        fromPlaces = Resource.error(
                            "Failed to load whereStorages",
                            null
                        )
                    )
                }
            }
        } catch (e: Exception) {
            updateUIState {
                copy(
                    fromPlaces = Resource.error(
                        "Failed to load whereStorages",
                        null
                    )
                )
            }
        }
    }

    fun setSelectedWherePlace(place: PlaceWithPositionsDTO?) {
        updateUIState { copy(selectedWherePlace = mutableStateOf(place)) }
    }

    fun setSelectedWhereStorage(storage: StorageDTO?) {
        updateUIState { copy(selectedWhereStorage = mutableStateOf(storage)) }
    }

    fun setSelectedFromPlace(place: PlaceWithPositionsDTO?) {
        updateUIState {
            copy(
                selectedFromPlace = mutableStateOf(place)
            )
        }
    }
}