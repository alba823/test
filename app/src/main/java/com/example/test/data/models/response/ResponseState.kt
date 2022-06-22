package com.example.test.data.models.response

sealed class ResponseState<T> {
    class Loading<T> : ResponseState<T>()
    class Success<T>(val result: T) : ResponseState<T>()
    class Error<T>(val code: Int) : ResponseState<T>()
}