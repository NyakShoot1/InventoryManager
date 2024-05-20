package com.nyakshoot.storageservice.presentation.screens.receiving_goods.view_models

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.nyakshoot.storageservice.data.dto.item.ItemDTO
import com.nyakshoot.storageservice.data.dto.position.PositionDTO
import com.nyakshoot.storageservice.data.dto.shipment.ShipmentCreateRequestDTO
import com.nyakshoot.storageservice.data.local.LocalReceivingDeliveryRepo
import com.nyakshoot.storageservice.domain.repository.IPositionRepository
import com.nyakshoot.storageservice.domain.repository.IShipmentRepository
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.ui_states.DoneReceivingGoodsUIState
import com.nyakshoot.storageservice.work_manager.GlbUploadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class DoneReceivingGoodsViewModel @Inject constructor(
    private val iPositionRepository: IPositionRepository,
    private val iShipmentRepository: IShipmentRepository,
    private val localReceivingDeliveryRepo: LocalReceivingDeliveryRepo
) : ViewModel() {

    private val _doneReceivingGoodsUIState = mutableStateOf(DoneReceivingGoodsUIState())
    val doneReceivingGoodsUIState: State<DoneReceivingGoodsUIState> = _doneReceivingGoodsUIState

    private fun updateUIState(update: DoneReceivingGoodsUIState.() -> DoneReceivingGoodsUIState) {
        _doneReceivingGoodsUIState.value = _doneReceivingGoodsUIState.value.update()
    }

    fun uploadPhotos(applicationContext: Context) {
        val photos = getPhotos()

        val data = workDataOf("photos" to photos)

        val workRequest = OneTimeWorkRequestBuilder<GlbUploadWorker>()
            .setInputData(data)
            .build()
        // TODO создание shipment
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
        val workRequestId = GlbUploadWorker.enqueueUpload(photos, applicationContext)
        // Отслеживайте состояние задачи загрузки, как в предыдущем примере
//        val workInfoFlow = WorkManager.getInstance(applicationContext)
//            .getWorkInfoByIdLiveData(workRequestId)
//            .asFlow()
//            .map { workInfo ->
//                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
//                    val outputData = workInfo.outputData
//                    val photoIds = outputData.getStringArray("photoIds")?.toList()
//
//                    if (photoIds != null) {
//                        createShipment()
//                    } else {
//
//                    }
//                } else if (workInfo != null && workInfo.state == WorkInfo.State.RUNNING) {
//
//                } else {
//
//                }
//            }
//            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), )
    }

    fun createShipment() = viewModelScope.launch {
        val positions: MutableList<PositionDTO> = mutableListOf()
        for (item in getItems()) {
            positions.add(PositionDTO(count = 1, itemId = item.id))
        }

        val newShipment = ShipmentCreateRequestDTO(
            photos = getPhotos(),
            positions = positions,
            supplierName = getSupplierName(),
            documentNumber = getNumberDocument(),
            deliveryMan = getDeliveryMan(),
            storageMan = "ИВАНОВ И.И."
        )

        try {
            iShipmentRepository.createShipment(newShipment)
        } catch (ex: CancellationException) {
            Log.d("JOB_GG_CancellationException", ex.toString())
            throw ex // Must let the CancellationException propagate
        } catch (ex: Exception) {
            Log.d("JOB_GG", ex.toString())
            // Handle all other exceptions here
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