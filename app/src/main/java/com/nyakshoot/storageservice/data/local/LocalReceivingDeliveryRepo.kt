package com.nyakshoot.storageservice.data.local

import com.nyakshoot.storageservice.data.dto.item.ItemDTO
import okhttp3.MultipartBody
import javax.inject.Singleton

@Singleton
class LocalReceivingDeliveryRepo {
    private var supplier: String = ""
    private var numberDocument: String = ""
    private var deliveryMan: String = ""
    private var photos: List<MultipartBody.Part> = listOf()

    private var items: List<ItemDTO> = listOf()

    fun setDeliveryInfo(
        supplier: String,
        numberDocument: String,
        deliveryMan: String,
        photos: List<MultipartBody.Part>
    ) {
        this.supplier = supplier
        this.numberDocument = numberDocument
        this.deliveryMan = deliveryMan
        this.photos = photos
    }

    fun setItems(items: List<ItemDTO>) {
        this.items = items
    }

    fun setPhotos(photos: List<MultipartBody.Part>) {
        this.photos = photos
    }

    fun getPhotos(): List<MultipartBody.Part> {
        return this.photos
    }

    fun getSupplier(): String {
        return this.supplier
    }

    fun getDeliveryMan(): String {
        return this.deliveryMan
    }

    fun getNumberDocument(): String {
        return this.numberDocument
    }

    fun getItems(): List<ItemDTO> {
        return this.items
    }
}