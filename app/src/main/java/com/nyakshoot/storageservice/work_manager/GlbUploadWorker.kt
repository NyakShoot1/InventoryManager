package com.nyakshoot.storageservice.work_manager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nyakshoot.storageservice.domain.repository.IPhotoRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class GlbUploadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val iPhotoRepository: IPhotoRepository
): CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }
}