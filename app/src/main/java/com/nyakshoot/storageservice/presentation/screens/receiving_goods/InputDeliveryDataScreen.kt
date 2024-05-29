package com.nyakshoot.storageservice.presentation.screens.receiving_goods

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.nyakshoot.storageservice.R
import com.nyakshoot.storageservice.data.dto.photo.PhotoDTO
import com.nyakshoot.storageservice.presentation.navigation.Screen
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.components.PhotoItem
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.components.TakePhotoItem
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.view_models.InputDeliveryDataViewModel

@SuppressLint("QueryPermissionsNeeded")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InputDeliveryDataScreen(
    navController: NavHostController,
    viewModel: InputDeliveryDataViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val currentPhotoUri by remember { viewModel.currentPhotoUri }

    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            currentPhotoUri?.let { uri ->
                Log.d("uri", uri.toString())
                viewModel.addPhoto(PhotoDTO(uri, "photo_prihod_${viewModel.getCurrentDateTime()}.jpg"))
            }
        } else {
            Toast.makeText(context, "Ошибка при съемке фотографии", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getSuppliers()
    }

    DisposableEffect(Unit) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            if (destination.route == Screen.InputGoodsData.route ||
                destination.route == Screen.DoneReceivingGoods.route
            ) {
                viewModel.saveDeliveryData(context)
            }
        }

        onDispose {navController.addOnDestinationChangedListener(listener)}
    }

    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .padding(10.dp)
        ) {
            TextField(
                value = viewModel.inputDeliveryDataUIState.value.selectedSupplier.value,
                readOnly = true,
                onValueChange = {
                    viewModel.inputDeliveryDataUIState.value.selectedSupplier.value = it
                },
                label = { Text("Поставщик") },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                        contentDescription = "Dropdown Icon"
                    )
                },
                modifier = Modifier.width(275.dp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                viewModel.inputDeliveryDataUIState.value.suppliers.data?.forEach { supplier ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.inputDeliveryDataUIState.value.selectedSupplier.value = supplier.email!!
                            expanded = false
                        }
                    ){
                        Text(text = supplier.email!!)
                    }
                }
            }
        }
        TextField(
            modifier = Modifier
                .padding(10.dp),
            value = viewModel.inputDeliveryDataUIState.value.numberDocument.value,
            onValueChange = { viewModel.inputDeliveryDataUIState.value.numberDocument.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = {
                Text(
                    text = "Номер накладной",
                    color = MaterialTheme.colors.primary
                )
            }
        )
        TextField(
            modifier = Modifier
                .padding(10.dp),
            value = viewModel.inputDeliveryDataUIState.value.deliveryMan.value,
            onValueChange = { viewModel.inputDeliveryDataUIState.value.deliveryMan.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = {
                Text(
                    text = "Отпуск груза разрешил",
                    color = MaterialTheme.colors.primary
                )
            }
        )
        TakePhotoItem("Добавить фото", R.drawable.camera_add) {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
                intent.resolveActivity(context.packageManager)?.let {
                    viewModel.takePhoto(context) // Вызываем функцию для генерации нового URI при вызове камеры
                    resultLauncher.launch(currentPhotoUri!!)
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFF2F2F2))
                .width(275.dp)
                .height(250.dp)
                .padding(8.dp)
        ) {
            LazyColumn {
                items(viewModel.inputDeliveryDataUIState.value.photos.size) { index ->
                    PhotoItem(
                        imageUri = viewModel.inputDeliveryDataUIState.value.photos[index].uri,
                        photoName = viewModel.inputDeliveryDataUIState.value.photos[index].photoName,
                        onDeletePhoto = { viewModel.removePhoto(viewModel.inputDeliveryDataUIState.value.photos[index]) }
                    )
                }
            }
        }
    }
}
