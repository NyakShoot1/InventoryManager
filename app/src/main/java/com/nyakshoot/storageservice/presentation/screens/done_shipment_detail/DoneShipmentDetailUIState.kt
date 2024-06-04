package com.nyakshoot.storageservice.presentation.screens.done_shipment_detail

import android.graphics.Bitmap
import com.nyakshoot.storageservice.utils.Resource

data class DoneShipmentDetailUIState(
    val photosUrls: Resource<MutableList<String>> = Resource.loading(),
    val downloadedPhotos: Resource<Map<String, Bitmap?>> = Resource.success(emptyMap()),
    val documentUrl: Resource<String> = Resource.loading(),
)
