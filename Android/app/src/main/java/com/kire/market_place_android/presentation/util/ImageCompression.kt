package com.kire.market_place_android.presentation.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.InputStream

/**
 * Функция конвертации входящего потока байт в массив
 *
 * @param inputStream поток байт
 *
 * @author Michael Gontarev (KiREHwYE)*/
suspend fun compressImage(inputStream: InputStream): ByteArray {
    return withContext(Dispatchers.IO) {

        // Decode the input stream into a Bitmap
        val bitmap = BitmapFactory.decodeStream(inputStream)
            ?: return@withContext ByteArray(0)

        // Initialize variables for compression
        var quality = 100
        var byteArray: ByteArray
        val byteBuffer = ByteArrayOutputStream()

        // Compress the Bitmap and check the size
        do {
            byteBuffer.reset() // Clear the buffer
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteBuffer)
            byteArray = byteBuffer.toByteArray()
            quality -= 5 // Decrease the quality by 5 for the next iteration
        } while (byteArray.size > 10000 && quality > 0)

        // Release the bitmap to free up memory
        bitmap.recycle()

        byteArray
    }
}