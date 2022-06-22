package com.example.test.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.example.test.data.ListResponseSuccess
import com.example.test.data.models.response.ErrorCode
import com.example.test.data.models.response.ResponseModel
import com.example.test.data.models.response.ResponseState
import com.example.test.rules.TestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest = "src/test/AndroidManifest.xml")
class RetrofitCallExtensionsTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var mockedCall: Call<ResponseModel<List<String>>>
    private lateinit var context: Context

    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun `executeRequest() onResponse() should be successful`() =
        runTest {
            doAnswer { invocation ->
                val callback = invocation.arguments[0] as Callback<ResponseModel<List<String>>>
                callback.onResponse(mockedCall, Response.success(ListResponseSuccess))
            }.`when`(mockedCall).enqueue(any())

            mockedCall.executeRequest().test {
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
    fun `when executeRequest() onResponse() body is null errorCode should be BAD_RESPONSE_CODE`() =
        runTest {
            doAnswer { invocation ->
                val callback = invocation.arguments[0] as Callback<ResponseModel<List<String>>>
                callback.onResponse(mockedCall, Response.success(null))
            }.`when`(mockedCall).enqueue(any())

            mockedCall.executeRequest().test {
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
    fun `when executeRequest() onFailure() errorCode should be FAILURE_CODE`() =
        runTest {
            doAnswer { invocation ->
                val callback = invocation.arguments[0] as Callback<ResponseModel<List<String>>>
                callback.onFailure(mockedCall, Throwable())
            }.`when`(mockedCall).enqueue(any())

            mockedCall.executeRequest().test {
                Assert.assertTrue(awaitItem() is ResponseState.Loading)
                val result = awaitItem()
                Assert.assertTrue(result is ResponseState.Error)
                Assert.assertEquals(
                    ErrorCode.FAILURE_CODE,
                    (result as ResponseState.Error).code
                )
                awaitComplete()
            }
        }
}