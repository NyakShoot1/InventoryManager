package com.nyakshoot.storageservice.presentation.screens.receiving_goods.view_models

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.data.dto.photo.PhotoDTO
import com.nyakshoot.storageservice.data.local.LocalReceivingDeliveryRepo
import com.nyakshoot.storageservice.domain.repository.ISupplierRepository
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.ui_states.InputDeliveryDataUIState
import com.nyakshoot.storageservice.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class InputDeliveryDataViewModel @Inject constructor(
    private val iSupplierRepository: ISupplierRepository,
    private val localReceivingDeliveryRepo: LocalReceivingDeliveryRepo
) : ViewModel() {

    private val _inputDeliveryDataUIState = mutableStateOf(InputDeliveryDataUIState())
    val inputDeliveryDataUIState: State<InputDeliveryDataUIState> = _inputDeliveryDataUIState

    private fun updateUIState(update: InputDeliveryDataUIState.() -> InputDeliveryDataUIState) {
        _inputDeliveryDataUIState.value = _inputDeliveryDataUIState.value.update()
    }

    fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("dd_MM_yyyy__HH_mm_ss", Locale.getDefault())
        return dateFormat.format(Date())
    }

    fun getSuppliers() = viewModelScope.launch {
        updateUIState { copy(suppliers = Resource.loading()) }
        try {
            val response = iSupplierRepository.getSuppliers()
            if (response.data != null) {
                updateUIState { copy(suppliers = Resource.success(response.data)) }
            } else {
                updateUIState { copy(suppliers = Resource.error("Failed to load suppliers", null)) }
            }
        } catch (e: Exception) {
            updateUIState { copy(suppliers = Resource.error("Error loading suppliers", null)) }
        }
    }


    fun getInfoState() {

    }

    fun addPhoto(photo: PhotoDTO) {
        updateUIState {
            photos.add(photo)
            copy(
                photos = photos
            )
        }
    }

    fun removePhoto(photo: PhotoDTO) {
        updateUIState {
            photos.remove(photo)
            copy(
                photos = photos
            )
        }
    }

    @SuppressLint("Recycle")
    fun saveDeliveryData(context: Context) {
        val photosParts = _inputDeliveryDataUIState.value.photos.map { photoDTO ->
            val uri = photoDTO.uri
            val inputStream = context.contentResolver.openInputStream(uri)
            val requestBody = inputStream?.readBytes()?.toRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("photos", photoDTO.photoName, requestBody!!)
        }

        localReceivingDeliveryRepo.setDeliveryInfo(
            _inputDeliveryDataUIState.value.selectedSupplier.value,
            _inputDeliveryDataUIState.value.numberDocument.value,
            _inputDeliveryDataUIState.value.deliveryMan.value,
            photos = photosParts
        )
    }

    private val _currentPhotoUri = mutableStateOf<Uri?>(null)
    val currentPhotoUri: State<Uri?> = _currentPhotoUri

    // Остальной код вьюмодели...

    fun takePhoto(context: Context) {
        _currentPhotoUri.value = createImageFile(context)
    }

    fun createImageFile(context: Context): Uri? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.getDefault()).format(Date())
        val randomSuffix = UUID.randomUUID().toString().substring(0, 4) // Генерируем случайный суффикс из UUID
        val fileName = "JPEG_${timeStamp}_${randomSuffix}"
        val mimeType = "image/jpeg"

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, mimeType)
            put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
        }

        val contentResolver = context.contentResolver
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }
}