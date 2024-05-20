package com.nyakshoot.storageservice.presentation.screens.receiving_goods.view_models

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.data.local.LocalReceivingDeliveryRepo
import com.nyakshoot.storageservice.domain.repository.IItemRepository
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.ui_states.InputGoodsDataUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputGoodsDataViewModel @Inject constructor(
    private val iItemRepository: IItemRepository,
    private val localReceivingDeliveryRepo: LocalReceivingDeliveryRepo
) : ViewModel() {
    private val _inputGoodsDataUIState = mutableStateOf(InputGoodsDataUIState())
    val inputGoodsDataUIState: State<InputGoodsDataUIState> = _inputGoodsDataUIState

    private fun updateUIState(update: InputGoodsDataUIState.() -> InputGoodsDataUIState) {
        _inputGoodsDataUIState.value = _inputGoodsDataUIState.value.update()
    }

    private val _cameraPermissionGranted = MutableStateFlow(false)
    val cameraPermissionGranted: StateFlow<Boolean> = _cameraPermissionGranted.asStateFlow()

    private val REQUEST_CAMERA_PERMISSION = 100
    fun requestCameraPermission(activity: Activity) {
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        } else {
            _cameraPermissionGranted.value = true
        }
    }

    fun onCodeScanned(code: String) = viewModelScope.launch {
        updateUIState {
            copy(
                isScannerShown = mutableStateOf(false) // Прекращаем сканирование
            )
        }
        val response = iItemRepository.getItemByBarcode(code)
        if (response.data != null) {
            updateUIState {
                currentItems.add(response.data)
                copy(
                    currentItems = currentItems,
                    isOKDialogShown = mutableStateOf(true)
                )
            }
            localReceivingDeliveryRepo.setItems(_inputGoodsDataUIState.value.currentItems)
        } else {
            Log.d("barcode_response", "No data received from backend")
        }
    }

    fun deleteItem(itemId: Int) {
        updateUIState {
            currentItems.removeAt(itemId)
            copy(
                currentItems = currentItems
            )
        }
    }

    fun updateIsOKDialogShown() {
        updateUIState {
            copy(
                isOKDialogShown = mutableStateOf(!isOKDialogShown.value)
            )
        }
    }

    fun updateIsScannerShown() {
        updateUIState {
            copy(
                isScannerShown = mutableStateOf(!isScannerShown.value)
            )
        }
    }

    fun getIsScannerShownState(): Boolean {
        return _inputGoodsDataUIState.value.isScannerShown.value
    }

    fun getIsOKDialogShownState(): Boolean {
        return _inputGoodsDataUIState.value.isOKDialogShown.value
    }
}




