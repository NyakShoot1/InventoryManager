package com.nyakshoot.storageservice.presentation.screens.receiving_goods.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nyakshoot.storageservice.R
import java.io.IOException

@Composable
fun PhotoItem(
    imageUri: Uri?,
    photoName: String,
    onDeletePhoto: () -> Unit
) {
    val context = LocalContext.current
    val bitmap = produceState<Bitmap?>(initialValue = null) {
        value = try {
            val inputStream = imageUri?.let { context.contentResolver.openInputStream(it) }
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        border = BorderStroke(2.dp, MaterialTheme.colors.primary),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            bitmap.value?.let { bitmapValue ->
                Image(
                    bitmap = bitmapValue.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
            Text(text = photoName)
            IconButton(onClick = onDeletePhoto) {
                Icon(
                    painter = painterResource(id = R.drawable.trash),
                    contentDescription = "Delete Photo"
                )
            }
        }
    }
}