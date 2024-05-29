package com.nyakshoot.storageservice.presentation.screens.done_shipment_detail

import androidx.lifecycle.ViewModel
import com.nyakshoot.storageservice.domain.repository.IPhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoneShipmentDetailViewModel @Inject constructor(
    private val iPhotoRepository: IPhotoRepository
):ViewModel() {

}