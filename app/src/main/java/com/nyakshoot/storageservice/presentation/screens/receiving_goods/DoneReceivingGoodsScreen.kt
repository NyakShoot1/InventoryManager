package com.nyakshoot.storageservice.presentation.screens.receiving_goods


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.view_models.DoneReceivingGoodsViewModel

@Composable
fun DoneReceivingGoodsScreen(
    navController: NavHostController,
    viewModel: DoneReceivingGoodsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFF2F2F2), shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Text("Поствщик: ${viewModel.getSupplierName()}", fontSize = 15.sp)
                Text("Номер накладной: ${viewModel.getNumberDocument()}", fontSize = 15.sp)
                Text("Отпуск груза разрешил: ${viewModel.getDeliveryMan()}", fontSize = 15.sp)
                Text("Дата: ${viewModel.getTimeNow()}", fontSize = 15.sp)
                Text("Склад: ", fontSize = 15.sp)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFF2F2F2), shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Text("ИТОГО: ", fontSize = 15.sp)
                Text("Кол-во товаров: ${viewModel.getItems().size}", fontSize = 15.sp)
                Text("Общая стоимость: 1000 руб.", fontSize = 15.sp)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(16.dp),
                    clip = true
                )
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .height(250.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                items(viewModel.getItems().size) { index ->
                    Text(viewModel.getItems()[index].name, modifier = Modifier.padding(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                viewModel.createShipment()
                navController.popBackStack()
                navController.popBackStack()

            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF242423))
        ) {
            Text("Завершить", fontSize = 24.sp, color = Color.White)
        }

    }
}