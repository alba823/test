package com.example.test.data.models.response

data class ResponseModel<T>(
    val status: String,
    val data: T?
)