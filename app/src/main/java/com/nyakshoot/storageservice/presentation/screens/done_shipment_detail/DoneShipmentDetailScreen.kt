package com.nyakshoot.storageservice.presentation.screens.done_shipment_detail

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nyakshoot.storageservice.data.dto.shipment.ShipmentDTO
import com.nyakshoot.storageservice.presentation.screens.done_shipment_detail.components.DocumentCard
import com.nyakshoot.storageservice.presentation.screens.done_shipment_detail.components.FullScreenPhoto
import com.nyakshoot.storageservice.presentation.screens.done_shipment_detail.components.PhotoGrid
import com.nyakshoot.storageservice.presentation.screens.done_shipment_detail.components.RoundedInfoBox
import com.nyakshoot.storageservice.utils.Resource

@Composable
fun DoneShipmentDetailScreen(
    navController: NavHostController,
    shipment: ShipmentDTO,
    viewModel: DoneShipmentDetailViewModel = hiltViewModel()
) {
    var selectedPhoto by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    LaunchedEffect(shipment) {
        viewModel.getDocumentUrl(shipment.documentId)
        viewModel.getPhotosUrls(shipment.photos.map { it.id })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RoundedInfoBox(shipment)

        DocumentCard(
            state = viewModel.doneShipmentDetailUIState.value.documentUrl.status,
            onDownload = {
                viewModel.doneShipmentDetailUIState.value.documentUrl.data?.let { viewModel.downloadDocument(context, it) }
            }
        )

        Text(
            "Фотографии поставки",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        when (viewModel.doneShipmentDetailUIState.value.photosUrls.status) {
            Resource.Status.LOADING -> { CircularProgressIndicator() }
            Resource.Status.ERROR -> { Text("Ошибка загрузки фотографий") }
            Resource.Status.SUCCESS -> {
                val urls = viewModel.doneShipmentDetailUIState.value.photosUrls.data ?: emptyList()
                val bitmaps = viewModel.doneShipmentDetailUIState.value.downloadedPhotos.data ?: emptyMap()

                PhotoGrid(
                    urls = urls,
                    bitmaps = urls.map { bitmaps[it] },
                    onPhotoClick = { index -> bitmaps[urls[index]]?.let { selectedPhoto = it } },
                    onDownload = { url -> viewModel.downloadPhoto(url) },
                    onRetry = { url -> viewModel.downloadPhoto(url) }
                )
            }
        }
    }

    selectedPhoto?.let { bitmap ->
        FullScreenPhoto(
            bitmap = bitmap,
            onDismiss = { selectedPhoto = null }
        )
    }
}
