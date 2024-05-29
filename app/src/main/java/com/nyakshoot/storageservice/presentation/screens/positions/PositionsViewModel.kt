package com.nyakshoot.storageservice.presentation.screens.positions

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.domain.repository.IPositionRepository
import com.nyakshoot.storageservice.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PositionsViewModel @Inject constructor(
    private val iPositionRepository: IPositionRepository
) : ViewModel() {

    private val _positionsUIState = mutableStateOf(PositionsUIState())
    val positionsUIState: State<PositionsUIState> = _positionsUIState

    private fun updateUIState(update: PositionsUIState.() -> PositionsUIState) {
        _positionsUIState.value = _positionsUIState.value.update()
    }

    fun getPositionsWithItemAndPlace() = viewModelScope.launch {
        updateUIState { copy(positions = Resource.loading()) }
        try {
            val response = iPositionRepository.getPositionsWithItemAndPlace()
            if (response.data != null) {
                updateUIState {
                    copy(
                        positions = Resource.success(response.data.toMutableList()),
                        filteredPositions = response.data.toMutableList()
                    )
                }
            } else {
                updateUIState {
                    copy(
                        positions = Resource.error(
                            "Failed to load positions",
                            null
                        )
                    )
                }
            }
        } catch (e: Exception) {
            updateUIState { copy(positions = Resource.error("Failed to load positions", null)) }
        }
    }

    fun filterPositions(filter: String) {
        when (filter) {
            "Все" -> updateUIState { copy(filteredPositions = positions.data ?: mutableListOf()) }
            "На складе" -> updateUIState {
                copy(
                    filteredPositions = positions.data?.filter { it.place != null }!!
                        .toMutableList()
                )
            }

            "Новые" -> updateUIState {
                copy(
                    filteredPositions = positions.data?.filterNot { it.place != null }!!
                        .toMutableList()
                )
            }
        }
    }
}