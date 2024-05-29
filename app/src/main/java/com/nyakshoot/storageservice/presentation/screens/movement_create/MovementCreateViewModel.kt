package com.nyakshoot.storageservice.presentation.screens.movement_create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.data.dto.place.PlaceWithPositionsDTO
import com.nyakshoot.storageservice.data.dto.storage.StorageDTO
import com.nyakshoot.storageservice.domain.repository.IPlaceRepository
import com.nyakshoot.storageservice.domain.repository.IStorageRepository
import com.nyakshoot.storageservice.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovementCreateViewModel @Inject constructor(
    private val iPlaceRepository: IPlaceRepository,
    private val iStorageRepository: IStorageRepository
) : ViewModel() {

    private val _movementCreateUIState = mutableStateOf(MovementCreateUIState())
    val movementCreateUIState: State<MovementCreateUIState> = _movementCreateUIState

    private fun updateUIState(update: MovementCreateUIState.() -> MovementCreateUIState) {
        _movementCreateUIState.value = _movementCreateUIState.value.update()
    }

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
            updateUIState { copy(fromPlaces = Resource.error("Failed to load whereStorages", null)) }
        }
    }

    fun setSelectedWherePlace(place: PlaceWithPositionsDTO?) {
        updateUIState { copy(selectedWherePlace = mutableStateOf(place)) }
    }

    fun setSelectedWhereStorage(storage: StorageDTO?) {
        updateUIState { copy(selectedWhereStorage = mutableStateOf(storage)) }
    }

    fun addSelectedFromPlace(place: PlaceWithPositionsDTO?){
        updateUIState {
            selectedFromPlaces.add(place!!)
            copy(
                selectedFromPlaces = selectedFromPlaces
            )
        }
    }

    fun deleteSelectedFromPlaces(place: PlaceWithPositionsDTO?){
        updateUIState {
            selectedFromPlaces.remove(place)
            copy(
                selectedFromPlaces = selectedFromPlaces
            )
        }
    }
}