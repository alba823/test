package com.example.test.data.repository

import com.example.test.data.models.User
import com.example.test.data.models.response.ResponseModel
import com.example.test.data.models.response.ResponseState
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getUserIds(): Flow<ResponseState<ResponseModel<List<String>>>>
    fun getUserById(id: String): Flow<ResponseState<ResponseModel<User>>>
}