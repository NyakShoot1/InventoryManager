package com.nyakshoot.storageservice.presentation.screens.receiving_goods.view_models

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.data.dto.item.ItemDTO
import com.nyakshoot.storageservice.data.dto.position.PositionRequestDTO
import com.nyakshoot.storageservice.data.dto.shipment.ShipmentCreateRequestDTO
import com.nyakshoot.storageservice.data.local.LocalReceivingDeliveryRepo
import com.nyakshoot.storageservice.domain.repository.IPhotoRepository
import com.nyakshoot.storageservice.domain.repository.IPositionRepository
import com.nyakshoot.storageservice.domain.repository.IShipmentRepository
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.ui_states.DoneReceivingGoodsUIState
import com.nyakshoot.storageservice.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class DoneReceivingGoodsViewModel @Inject constructor(
    private val iPositionRepository: IPositionRepository,
    private val iShipmentRepository: IShipmentRepository,
    private val iPhotoRepository: IPhotoRepository,
    private val localReceivingDeliveryRepo: LocalReceivingDeliveryRepo
) : ViewModel() {

    private val _doneReceivingGoodsUIState = mutableStateOf(DoneReceivingGoodsUIState())
    val doneReceivingGoodsUIState: State<DoneReceivingGoodsUIState> = _doneReceivingGoodsUIState

    private fun updateUIState(update: DoneReceivingGoodsUIState.() -> DoneReceivingGoodsUIState) {
        _doneReceivingGoodsUIState.value = _doneReceivingGoodsUIState.value.update()
    }

    private val _doneRequestState = mutableStateOf(Resource.loading(false))
    val doneRequestState: State<Resource<Boolean>> = _doneRequestState

    private suspend fun uploadPhotos() {
        val uploadPhotos = getPhotos()
        for (photoBody in uploadPhotos) {
            val list: List<MultipartBody.Part> = listOf(photoBody)
            val newPhoto = iPhotoRepository.createPhoto(list)
            if (newPhoto.status == Resource.Status.SUCCESS) {
                newPhoto.data?.let { response ->
                    updateUIState {
                        photosIds.add(response.id)
                        copy(photosIds = photosIds)
                    }
                }
            } else {
                Log.d("JOB_UPLOAD_PHOTO_GG", "Error uploading photo: ${newPhoto.toString()}")
            }
        }
        Log.d("JOB_UPLOAD_PHOTO_GG", _doneReceivingGoodsUIState.value.photosIds.toString())
    }


    private suspend fun createShipment() {

        Log.d("NICE", _doneReceivingGoodsUIState.value.photosIds.toString())
        val items = getItems()
        val positions: MutableList<PositionRequestDTO> = mutableListOf()
        for (item in items) {
            positions.add(PositionRequestDTO(1, itemId = item.id!!))
        }

        val newShipment = ShipmentCreateRequestDTO(
            photos = _doneReceivingGoodsUIState.value.photosIds,
            positions = positions,
            supplierName = getSupplierName(),
            documentNumber = getNumberDocument(),
            deliveryMan = getDeliveryMan(),
            storageMan = "ИВАНОВ И.И."
        )

        try {
            val response = iShipmentRepository.createShipment(newShipment)
        } catch (ex: CancellationException) {
            Log.d("JOB_GG_CancellationException", ex.toString())
            throw ex // Must let the CancellationException propagate
        } catch (ex: Exception) {
            Log.d("JOB_GG", ex.toString())
            // Handle all other exceptions here
        }
    }

    fun doShipmentRequest() = viewModelScope.launch {
        _doneRequestState.value = Resource.loading()
        try {
            uploadPhotos()
            createShipment()
            _doneRequestState.value = Resource.success(true)
        } catch (ex: Exception) {
            _doneRequestState.value = Resource.error(ex.toString(), false)
            Log.d("JOB_GG", ex.toString())
        }
    }

    fun getSupplierName(): String {
        return localReceivingDeliveryRepo.getSupplier()
    }

    fun getDeliveryMan(): String {
        return localReceivingDeliveryRepo.getDeliveryMan()
    }

    fun getNumberDocument(): String {
        return localReceivingDeliveryRepo.getNumberDocument()
    }

    fun getItems(): List<ItemDTO> {
        return localReceivingDeliveryRepo.getItems()
    }

    fun getPhotos(): List<MultipartBody.Part> {
        return localReceivingDeliveryRepo.getPhotos()
    }

    fun getTimeNow(): String {
        return _doneReceivingGoodsUIState.value.date
    }
}