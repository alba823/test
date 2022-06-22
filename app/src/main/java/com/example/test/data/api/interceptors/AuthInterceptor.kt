package com.example.test.data.api.interceptors

import com.example.test.utils.JWTTokenUtil
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal const val AUTH_HEADER = "Authorization"
internal const val HEADER_PREFIX = "Bearer "

class AuthInterceptor @Inject constructor(
    private val tokenUtil: JWTTokenUtil
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader(AUTH_HEADER, HEADER_PREFIX.plus(tokenUtil.generateToken()))
                .build()
        )
    }
}