package com.nyakshoot.storageservice.data.repository

import com.nyakshoot.storageservice.data.dto.shipment.ShipmentCreateRequestDTO
import com.nyakshoot.storageservice.data.dto.shipment.ShipmentDTO
import com.nyakshoot.storageservice.data.remote.base_response_wrapper.AbstractBaseClient
import com.nyakshoot.storageservice.data.remote.clients.IShipmentClient
import com.nyakshoot.storageservice.domain.repository.IShipmentRepository
import com.nyakshoot.storageservice.utils.Resource
import javax.inject.Inject

class ShipmentRepositoryImpl @Inject constructor(
    private val iShipmentClient: IShipmentClient,
) : AbstractBaseClient(), IShipmentRepository {

    override suspend fun createShipment(newShipment: ShipmentCreateRequestDTO): Resource<ShipmentDTO> {
        return safeApiCall {
            iShipmentClient.createShipment(
                newShipment
            )
        }
    }

    override suspend fun getDoneShipments(): Resource<List<ShipmentDTO>> {
        return safeApiCall { iShipmentClient.getDoneShipments() }
    }

}