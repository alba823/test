package com.example.test.utils

import android.net.Uri
import com.example.test.data.models.User
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = "src/test/AndroidManifest.xml")
class ConvertingTest {

    @Test
    fun `User should be converted to json`() {
        val user = User(
            "test",
            "test",
            "test",
            0,
            "test",
            "test"
        )

        val expectedUserJson = Uri.encode(
            "{\"id\":\"test\",\"firstName\":\"test\",\"lastName\":\"test\",\"age\":0,\"gender\":\"test\",\"country\":\"test\"}"
        )
        val userJson = user.toJson()

        Assert.assertEquals(
            expectedUserJson,
            userJson
        )
    }
}