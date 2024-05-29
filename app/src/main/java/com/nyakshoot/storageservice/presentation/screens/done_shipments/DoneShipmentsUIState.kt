package com.nyakshoot.storageservice.presentation.screens.done_shipments

import com.nyakshoot.storageservice.data.dto.shipment.ShipmentDTO
import com.nyakshoot.storageservice.utils.Resource

data class DoneShipmentsUIState(
    val isLoading: Boolean = false,
    val doneShipments: Resource<List<ShipmentDTO>> = Resource.loading()
)
