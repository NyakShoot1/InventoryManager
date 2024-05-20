package com.nyakshoot.storageservice.work_manager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.nyakshoot.storageservice.domain.repository.IPhotoRepository
import com.nyakshoot.storageservice.utils.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.UUID

@HiltWorker
class GlbUploadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val iPhotoRepository: IPhotoRepository
): CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val photos = inputData.getMultipartBodyParts("photos") ?: return Result.failure()

        return uploadFiles(photos)
    }

    private suspend fun uploadFiles(photos: List<MultipartBody.Part>): Result {
        return try {
            val response = iPhotoRepository.createPhotos(photos)
            if (response.status == Resource.Status.SUCCESS && response.data != null) {
                val photoIds = response.data.map { it.id }
                Result.success(workDataOf("photoIds" to photoIds))
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }

    companion object {
        fun enqueueUpload(photos: List<MultipartBody.Part>, applicationContext: Context): UUID {
            val workRequest = OneTimeWorkRequestBuilder<GlbUploadWorker>()
                .setInputData(
                    workDataOf(
                        "photos" to photos
                    )
                )
                .build()

            WorkManager.getInstance(applicationContext).enqueue(workRequest)
            return workRequest.id
        }
    }
}

fun Data.getMultipartBodyParts(key: String): List<MultipartBody.Part>? {
    val values = getStringArray(key)
    return values?.mapNotNull { value ->
        val requestBody = value.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        requestBody.let { MultipartBody.Part.createFormData(key, null, it) }
    }
}