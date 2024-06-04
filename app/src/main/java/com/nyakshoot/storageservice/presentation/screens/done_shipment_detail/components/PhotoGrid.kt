package com.nyakshoot.storageservice.presentation.screens.done_shipment_detail.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PhotoGrid(
    urls: List<String>,
    bitmaps: List<Bitmap?>,
    onPhotoClick: (Int) -> Unit,
    onDownload: (String) -> Unit,
    onRetry: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyGridState()
    val columns = GridCells.Adaptive(minSize = 128.dp)

    LazyVerticalGrid(
        columns = columns,
        modifier = modifier.fillMaxWidth(),
        state = listState,
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(urls.size) { index ->
            PhotoCard(
                bitmap = bitmaps[index],
                onClick = { onPhotoClick(index) },
                onDownload = { onDownload(urls[index]) },
                onRetry = { onRetry(urls[index]) }
            )
        }
    }
}