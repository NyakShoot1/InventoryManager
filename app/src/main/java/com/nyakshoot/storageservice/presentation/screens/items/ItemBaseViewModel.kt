package com.nyakshoot.storageservice.presentation.screens.items

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.domain.repository.IItemRepository
import com.nyakshoot.storageservice.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemBaseViewModel@Inject constructor(
    private val iItemRepository: IItemRepository
) : ViewModel() {

    private val _inputItemDataUIState = mutableStateOf(ItemBaseUIState())
    val inputItemDataUIState: State<ItemBaseUIState> = _inputItemDataUIState

    private fun updateUIState(update: ItemBaseUIState.() -> ItemBaseUIState) {
        _inputItemDataUIState.value = _inputItemDataUIState.value.update()
    }

    fun getItems() = viewModelScope.launch {
        if (_inputItemDataUIState.value.items.status != Resource.Status.LOADING){
            updateUIState { copy(isLoading = false, items = _inputItemDataUIState.value.items) }
        }else {
            updateUIState { copy(isLoading = true, items = Resource.loading()) }
            try {
                val response = iItemRepository.getItems()
                if (response.data != null) {
                    updateUIState { copy(isLoading = false, items = Resource.success(response.data)) }
                } else {
                    updateUIState { copy(isLoading = false, items = Resource.error("Failed to load items", null)) }
                }
            } catch (e: Exception) {
                updateUIState { copy(isLoading = false, items = Resource.error("Error loading items", null)) }
            }
        }
    }
}