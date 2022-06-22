package com.example.test.utils

import androidx.test.core.app.ApplicationProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = "src/test/AndroidManifest.xml")
class JsonReaderTest {

    @Test
    fun `loadJSONFromAsset should return expected value`() {
        val expectedValue = "{\n" + "    \"status\": \"error\"\n" + "}"
        val assetToGet = "response_error.json"

        val jsonReader = JsonReader(ApplicationProvider.getApplicationContext())

        val value = jsonReader.loadJSONFromAsset(assetToGet)

        Assert.assertEquals(
            expectedValue,
            value
        )
    }
}