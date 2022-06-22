package com.example.test.di

import com.example.test.data.api.BASE_URL
import com.example.test.data.api.SECRET
import com.example.test.data.api.UsersApi
import com.example.test.data.api.interceptors.AuthInterceptor
import com.example.test.data.repository.Repository
import com.example.test.data.repository.RepositoryImpl
import com.example.test.utils.JWTTokenUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideJWTTokenUtil(): JWTTokenUtil =
        JWTTokenUtil(SECRET)

    @Singleton
    @Provides
    fun provideClient(tokenUtil: JWTTokenUtil): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(AuthInterceptor(tokenUtil))
            .build()


    @Singleton
    @Provides
    fun provideUsersApi(client: OkHttpClient): UsersApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(usersApi: UsersApi): Repository =
        RepositoryImpl(usersApi)
}