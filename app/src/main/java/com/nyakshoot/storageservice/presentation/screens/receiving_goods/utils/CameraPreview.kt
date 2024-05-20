package com.nyakshoot.storageservice.presentation.screens.receiving_goods.utils

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

private var lastBarcode = "" // КОСТЫЛЬ ЖЕСКИЙ

@Composable
fun CameraPreview(
    onCodeScanned: (String) -> Unit
) {
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val imageAnalysis = ImageAnalysis.Builder()
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build()

    val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(
//            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_EAN_13
        )
        .build()

    val barCodeScanner = BarcodeScanning.getClient(options)

    imageAnalysis.setAnalyzer(
        ContextCompat.getMainExecutor(LocalContext.current)
    ) { imageProxy ->
        processImageProxy(barCodeScanner, imageProxy) { barcodes ->
            if (barcodes.isNotEmpty()) {
                barcodes.forEach { barcode ->
                    barcode.rawValue?.let { code ->
                        if (lastBarcode != code) {
                            onCodeScanned(code)
                            lastBarcode = code
                        }
                    }
                }
            } else {
                Log.d("CameraPreview", "No barcode found")
            }

        }
    }

    AndroidView(
        factory = { context ->
            val previewView = PreviewView(context)
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(lensFacing)
                    .build()

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        context as LifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalysis
                    )
                } catch (e: Exception) {
                    Log.e("CameraPreview", "Failed to bind camera use cases", e)
                }
            }, ContextCompat.getMainExecutor(context))

            previewView
        },
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalGetImage::class)
private fun processImageProxy(
    barCodeScanner: BarcodeScanner,
    imageProxy: ImageProxy,
    onSuccess: (List<Barcode>) -> Unit
) {
    imageProxy.image?.let { image ->
        val inputImage = InputImage.fromMediaImage(
            image,
            imageProxy.imageInfo.rotationDegrees
        )
        barCodeScanner.process(inputImage)
            .addOnSuccessListener { barcodes ->
                onSuccess(barcodes)
            }
            .addOnFailureListener {
                Log.e("CameraPreview", "Failed to process image for barcode scanning", it)
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }
}


