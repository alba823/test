package com.example.test.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream

class JsonReader(private val context: Context) {

    fun loadJSONFromAsset(fileName: String): String {
        var string = ""
        try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            string = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return string
    }
}