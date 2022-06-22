package com.example.test.data.api

import com.example.test.data.models.User
import com.example.test.data.models.response.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal const val BASE_URL = "https://opn-interview-service.nn.r.appspot.com/"
internal const val SECRET = "\$SECRET\$"
private const val ID_PATH = "id"

interface UsersApi {

    @GET("/list")
    fun getUserIds(): Call<ResponseModel<List<String>>>

    @GET("/get/{$ID_PATH}")
    fun getUserById(@Path(ID_PATH) id: String): Call<ResponseModel<User>>
}