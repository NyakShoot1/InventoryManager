package com.nyakshoot.storageservice.presentation.screens.done_shipment_detail

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.webkit.URLUtil
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.domain.repository.IDocumentRepository
import com.nyakshoot.storageservice.domain.repository.IPhotoRepository
import com.nyakshoot.storageservice.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DoneShipmentDetailViewModel @Inject constructor(
    private val iPhotoRepository: IPhotoRepository,
    private val iDocumentRepository: IDocumentRepository
) : ViewModel() {
    private val _doneShipmentDetailUIState = mutableStateOf(DoneShipmentDetailUIState())
    val doneShipmentDetailUIState: State<DoneShipmentDetailUIState> = _doneShipmentDetailUIState

    private fun updateUIState(update: DoneShipmentDetailUIState.() -> DoneShipmentDetailUIState) {
        _doneShipmentDetailUIState.value = _doneShipmentDetailUIState.value.update()
    }

    fun getDocumentUrl(documentId: Int) = viewModelScope.launch {
        updateUIState { copy(documentUrl = Resource.loading()) }
        try {
            val response = iDocumentRepository.getDocumentUrl(documentId)
            if (response.data != null) {
                updateUIState { copy(documentUrl = Resource.success(response.data)) }
            } else {
                updateUIState {
                    copy(
                        documentUrl = Resource.error(
                            "Failed to load documentURL",
                            null
                        )
                    )
                }
            }
        } catch (e: Exception) {
            updateUIState {
                copy(
                    documentUrl = Resource.error(
                        "Error loading documentURL",
                        null
                    )
                )
            }
        }
    }

    fun getPhotosUrls(photosIds: List<Int>) = viewModelScope.launch {
        updateUIState { copy(photosUrls = Resource.loading()) }
        try {
            val urls = mutableListOf<String>()
            for (photoId in photosIds) {
                val response = iPhotoRepository.getPhotoURL(photoId)
                response.data?.let { urls.add(it) }
            }
            updateUIState {
                copy(
                    photosUrls = Resource.success(urls),
                    downloadedPhotos = Resource.success(urls.associateWith { null })
                )
            }
        } catch (e: Exception) {
            updateUIState {
                copy(photosUrls = Resource.error("Error loading photosUrls", null))
            }
        }
    }

    fun downloadDocument(context: Context, url: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            // Проверяем разрешение для Android 9 и ниже
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
                if (ContextCompat.checkSelfPermission(
                        context,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "Требуется разрешение на запись файлов",
                            Toast.LENGTH_SHORT
                        ).show()
                        // Запрашиваем разрешение (необходимо обработать результат в Activity или Fragment)
                        ActivityCompat.requestPermissions(
                            context as Activity,
                            arrayOf(permission),
                            1000
                        )
                    }
                    return@launch
                }
            }

            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Download failed: $response")

                val downloadedFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // Используем MediaStore для Android 10 и выше
                    val values = ContentValues().apply {
                        put(
                            MediaStore.Downloads.DISPLAY_NAME,
                            URLUtil.guessFileName(
                                url,
                                null,
                                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                            )
                        )
                        put(
                            MediaStore.Downloads.MIME_TYPE,
                            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                        )
                        put(MediaStore.Downloads.IS_PENDING, 1)
                    }
                    val downloadsUri =
                        MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL)
                    val itemUri = context.contentResolver.insert(downloadsUri, values)

                    itemUri?.let {
                        context.contentResolver.openOutputStream(it)?.use { output ->
                            response.body.byteStream().use { input ->
                                input.copyTo(output)
                            }
                        }
                        values.clear()
                        values.put(MediaStore.Downloads.IS_PENDING, 0)
                        context.contentResolver.update(it, values, null, null)
                        it
                    }
                } else {
                    // Прямой доступ для Android 9 и ниже
                    val downloadsDir =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    val fileName = URLUtil.guessFileName(
                        url,
                        null,
                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                    )
                    val file = File(downloadsDir, fileName)
                    file.outputStream().use { output ->
                        response.body.byteStream().use { input ->
                            input.copyTo(output)
                        }
                    }
                    // Обновляем галерею
                    MediaScannerConnection.scanFile(context, arrayOf(file.absolutePath), null, null)
                    FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.file-provider",
                        file
                    )
                }

                val fileUri = downloadedFile ?: throw IOException("Failed to save file")

                withContext(Dispatchers.Main) {
                    openDocument(context, fileUri)
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Log.e("DownloadError", "Error downloading document", e)
                when (e) {
                    is IOException -> Toast.makeText(
                        context,
                        "Ошибка загрузки документа: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()

                    is SecurityException -> Toast.makeText(
                        context,
                        "Ошибка доступа к файлу: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()

                    else -> Toast.makeText(
                        context,
                        "Неизвестная ошибка: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun openDocument(context: Context, uri: Uri) {
        Log.d("DocumentURI", uri.toString())

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(
                uri,
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
            )
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
//
        }
    }

    fun downloadPhoto(url: String) = viewModelScope.launch {
        try {
            val bitmap = withContext(Dispatchers.IO) {
                val client = OkHttpClient()
                val request = Request.Builder().url(url).build()
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) throw IOException("Download failed: $response")
                    BitmapFactory.decodeStream(response.body.byteStream())
                }
            }
            updateUIState {
                val updatedMap = downloadedPhotos.data?.toMutableMap() ?: mutableMapOf()
                updatedMap[url] = bitmap
                copy(downloadedPhotos = Resource.success(updatedMap))
            }
        } catch (e: Exception) {
            Log.e("DownloadError", "Error downloading photo", e)
        }
    }
}