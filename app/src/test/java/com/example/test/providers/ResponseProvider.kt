package com.example.test.providers

import com.example.test.utils.JsonReader
import okhttp3.mockwebserver.MockResponse

class ResponseProvider(private val jsonReader: JsonReader) {

    fun makeResponse(file: String, code: Int, useFile: Boolean) = MockResponse().apply {
        setBody(if (useFile) jsonReader.loadJSONFromAsset(file) else file)
        setResponseCode(code)
    }
}