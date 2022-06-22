package com.example.test.data.api.interceptors

import androidx.test.core.app.ApplicationProvider
import com.example.test.data.api.SECRET
import com.example.test.data.api.UsersApi
import com.example.test.providers.ApiProvider
import com.example.test.providers.ResponseProvider
import com.example.test.utils.JWTTokenUtil
import com.example.test.utils.JsonReader
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = "src/test/AndroidManifest.xml")
class AuthInterceptorTest {

    private lateinit var usersApi: UsersApi
    private val mockWebServer = MockWebServer()
    private var _responseProvider: ResponseProvider? = null
    private val responseProvider get() = _responseProvider!!
    private val tokenUtil = JWTTokenUtil(SECRET)

    @Before
    fun setUp() {
        _responseProvider =
            ResponseProvider(JsonReader(ApplicationProvider.getApplicationContext()))
        usersApi = ApiProvider.provideUsersApi(mockWebServer = mockWebServer)
    }

    @Test
    fun `AuthInterceptor should add Authorization header`() {
        val response = responseProvider.makeResponse("{}", 200, false)
        mockWebServer.enqueue(response)

        usersApi.getUserIds().execute()
        val request = mockWebServer.takeRequest()
        val header = request.getHeader(AUTH_HEADER)
        Assert.assertEquals(
            HEADER_PREFIX.plus(tokenUtil.generateToken()),
            header
        )
    }

    @After
    fun tearDown() {
        _responseProvider = null
    }
}