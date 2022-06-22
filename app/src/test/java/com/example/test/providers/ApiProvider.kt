package com.example.test.providers

import com.example.test.data.api.SECRET
import com.example.test.data.api.UsersApi
import com.example.test.data.api.interceptors.AuthInterceptor
import com.example.test.utils.JWTTokenUtil
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {

    private fun provideClient(): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(AuthInterceptor(JWTTokenUtil(SECRET)))
            .build()

    fun provideUsersApi(
        client: OkHttpClient = provideClient(),
        mockWebServer: MockWebServer
    ): UsersApi =
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApi::class.java)
}