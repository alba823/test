package com.example.test.utils

import com.example.test.data.models.response.ErrorCode
import com.example.test.data.models.response.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume

fun <T> Call<T>.executeRequest(): Flow<ResponseState<T>> {
    return flow {
        emit(ResponseState.Loading())
        val state = suspendCancellableCoroutine<ResponseState<T>> {
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.body() != null) it.resume(ResponseState.Success(response.body()!!))
                    else it.resume(ResponseState.Error(ErrorCode.BAD_RESPONSE_CODE))
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resume(
                        ResponseState.Error(ErrorCode.FAILURE_CODE)
                    )
                }
            })
        }
        emit(state)
    }
}