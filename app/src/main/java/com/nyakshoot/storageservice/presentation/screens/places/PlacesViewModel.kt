package com.nyakshoot.storageservice.presentation.screens.places

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.domain.repository.IPlaceRepository
import com.nyakshoot.storageservice.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val iPlaceRepository: IPlaceRepository,
) : ViewModel() {

    private val _placesUIState = mutableStateOf(PlacesUIState())
    val placesUIState: State<PlacesUIState> = _placesUIState

    private fun updateUIState(update: PlacesUIState.() -> PlacesUIState) {
        _placesUIState.value = _placesUIState.value.update()
    }

    fun getPlacesCodes() = viewModelScope.launch {
        updateUIState { copy(codePlaces = Resource.loading()) }
        try {
            val response = iPlaceRepository.getPlacesCodes()
            if (response.data != null){
                updateUIState { copy( codePlaces = Resource.success(response.data)) }
            }else{
                updateUIState { copy(codePlaces = Resource.error("Failed to load codePlaces", null)) }
            }
        } catch (e: Exception) {
            updateUIState { copy(codePlaces = Resource.error("Failed to load codePlaces", null)) }
        }
    }

    fun getPlacesByCode(storageId: Int, placeCode: Char) = viewModelScope.launch {
        updateUIState { copy(places = Resource.loading()) }
        try {
            val response = iPlaceRepository.getPlacesByCode(storageId, placeCode)
            if (response.data != null) {
                updateUIState {
                    copy(
                        places = Resource.success(response.data.toMutableList()),
                        filteredPlaces = response.data.toMutableList()
                    )
                }
                filterPlaces(placesUIState.value.filteredState.value)
            } else {
                updateUIState { copy(places = Resource.error("Failed to load fromPlaces", null)) }
            }
        } catch (e: Exception) {
            updateUIState { copy(places = Resource.error("Failed to load fromPlaces", null)) }
        }
    }

    fun filterPlaces(filter: String) {
        when (filter) {
            "Все" -> updateUIState { copy(filteredPlaces = places.data ?: mutableListOf()) }
            "Занятые" -> updateUIState { copy(filteredPlaces = places.data?.filter { it.busy }!!.toMutableList()) }
            "Пустые" -> updateUIState { copy(filteredPlaces = places.data?.filterNot { it.busy }!!.toMutableList()) }
        }
    }

}