package com.nyakshoot.storageservice.presentation.screens.receiving_goods

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.components.ItemDataCard
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.utils.CameraPreview
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.view_models.InputGoodsDataViewModel

@Composable
fun InputGoodsDataScreen(
    navController: NavHostController,
    viewModel: InputGoodsDataViewModel = hiltViewModel()
) {
    val cameraPermissionGranted by viewModel.cameraPermissionGranted.collectAsState()
    val activity = (LocalContext.current as? Activity)

    DisposableEffect(Unit) {
        viewModel.requestCameraPermission(activity!!)
        onDispose {}
    }

    if (viewModel.getIsOKDialogShownState()) {
        Log.d("DIALOG", viewModel.getIsOKDialogShownState().toString())
        Dialog(onDismissRequest = {
//                viewModel.toggleScanner()
        }) {
            Column {
                Button(
                    onClick = {
                        viewModel.updateIsScannerShown()
                        viewModel.updateIsOKDialogShown()
                    },
                    modifier = Modifier.width(150.dp)
                ) {
                    Text("Продолжить")
                }
                Button(
                    onClick = {
                        viewModel.updateIsOKDialogShown()
                    },
                    modifier = Modifier.width(150.dp)
                ) {
                    Text("Отмена")
                }
            }

        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                viewModel.updateIsScannerShown()
            },
            modifier = Modifier.padding(16.dp)
        ) {
            if (!viewModel.getIsScannerShownState())
                Text("Сканировать")
            else
                Text("Отмена")
        }

        if (viewModel.getIsScannerShownState() && cameraPermissionGranted) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                color = Color.Black.copy(alpha = 0.5f)
            ) {
                CameraPreview(onCodeScanned = viewModel::onCodeScanned)
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.inputGoodsDataUIState.value.currentItems.size) { index ->
                val item = viewModel.inputGoodsDataUIState.value.currentItems[index]
                ItemDataCard(
                    itemNumber = (index + 1).toString(),
                    itemName = item.name,
                    itemQuantity = 1,
                    onDeleteClick = {
                        viewModel.deleteItem(index)
                    }
                )
            }
        }
    }
}