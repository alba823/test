package com.example.test.data.repository

import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.example.test.data.GetUserResponseSuccess
import com.example.test.data.ID_FOR_GET_USER_REQUEST
import com.example.test.data.ListResponseSuccess
import com.example.test.data.api.UsersApi
import com.example.test.data.models.response.ErrorCode
import com.example.test.data.models.response.ResponseState
import com.example.test.providers.ApiProvider
import com.example.test.providers.ResponseProvider
import com.example.test.rules.TestDispatcherRule
import com.example.test.utils.JsonReader
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest = "src/test/AndroidManifest.xml")
class RepositoryTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var repository: Repository
    private lateinit var usersApi: UsersApi
    private val mockWebServer = MockWebServer()
    private var _responseProvider: ResponseProvider? = null
    private val responseProvider get() = _responseProvider!!

    @Before
    fun setUp() {
        _responseProvider =
            ResponseProvider(JsonReader(ApplicationProvider.getApplicationContext()))
        usersApi = ApiProvider.provideUsersApi(mockWebServer = mockWebServer)
        repository = RepositoryImpl(usersApi)
    }

    @Test
    fun `getUserIds() response should be successful`() = runTest {
        val response = responseProvider.makeResponse("list_response_success.json", 200, true)
        mockWebServer.enqueue(response)

        repository.getUserIds().test {
            Assert.assertTrue(awaitItem() is ResponseState.Loading)
            val result = awaitItem()
            Assert.assertTrue(result is ResponseState.Success)
            Assert.assertEquals(
                ListResponseSuccess,
                (result as ResponseState.Success).result
            )
            awaitComplete()
        }
    }

    @Test
    fun `getUserIds() response should be error`() = runTest {
        val response = responseProvider.makeResponse("response_error.json", 500, true)
        mockWebServer.enqueue(response)

        repository.getUserIds().test {
            Assert.assertTrue(awaitItem() is ResponseState.Loading)
            val result = awaitItem()
            Assert.assertTrue(result is ResponseState.Error)
            Assert.assertEquals(
                ErrorCode.BAD_RESPONSE_CODE,
                (result as ResponseState.Error).code
            )
            awaitComplete()
        }
    }

    @Test
    fun `getUserById() response should be successful`() = runTest {
        val response = responseProvider.makeResponse("get_response_success.json", 200, true)
        mockWebServer.enqueue(response)

        repository.getUserById(ID_FOR_GET_USER_REQUEST).test {
            Assert.assertTrue(awaitItem() is ResponseState.Loading)
            val result = awaitItem()
            Assert.assertTrue(result is ResponseState.Success)
            Assert.assertEquals(
                GetUserResponseSuccess,
                (result as ResponseState.Success).result
            )
            awaitComplete()
        }
    }

    @Test
    fun `getUserById() response should be error`() = runTest {
        val response = responseProvider.makeResponse("response_error.json", 500, true)
        mockWebServer.enqueue(response)

        repository.getUserById(ID_FOR_GET_USER_REQUEST).test {
            Assert.assertTrue(awaitItem() is ResponseState.Loading)
            val result = awaitItem()
            Assert.assertTrue(result is ResponseState.Error)
            Assert.assertEquals(
                ErrorCode.BAD_RESPONSE_CODE,
                (result as ResponseState.Error).code
            )
            awaitComplete()
        }
    }

    @After
    fun tearDown() {
        _responseProvider = null
    }
}