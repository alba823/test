package com.example.test.data.repository

import com.example.test.data.api.UsersApi
import com.example.test.data.models.response.ResponseModel
import com.example.test.data.models.response.ResponseState
import com.example.test.data.models.User
import com.example.test.utils.executeRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val usersApi: UsersApi
) : Repository {
    override fun getUserIds(): Flow<ResponseState<ResponseModel<List<String>>>> {
        return usersApi.getUserIds().executeRequest()
    }

    override fun getUserById(id: String): Flow<ResponseState<ResponseModel<User>>> {
        return usersApi.getUserById(id).executeRequest()
    }
}