package com.nyakshoot.storageservice.data.remote.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.nyakshoot.storageservice.data.dto.movement.PlaceDTO
import com.nyakshoot.storageservice.data.dto.movement.StorageDTO
import com.nyakshoot.storageservice.data.dto.movement.WhereDTO
import java.lang.reflect.Type
class WhereDTOAdapter : JsonDeserializer<WhereDTO> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): WhereDTO {
        val jsonObject = json.asJsonObject
        return if (jsonObject.has("place_code") && jsonObject.has("place_number")) {
            context.deserialize<PlaceDTO>(jsonObject, PlaceDTO::class.java)
        } else if (jsonObject.has("address")) {
            context.deserialize<StorageDTO>(jsonObject, StorageDTO::class.java)
        } else {
            throw JsonParseException("Unknown WhereDTO type")
        }
    }
}