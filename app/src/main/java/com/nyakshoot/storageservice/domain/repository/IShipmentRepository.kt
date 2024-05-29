package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.shipment.ShipmentCreateRequestDTO
import com.nyakshoot.storageservice.data.dto.shipment.ShipmentDTO
import com.nyakshoot.storageservice.utils.Resource

interface IShipmentRepository {
    suspend fun createShipment(newShipment: ShipmentCreateRequestDTO): Resource<ShipmentDTO>

    suspend fun getDoneShipments(): Resource<List<ShipmentDTO>>
}