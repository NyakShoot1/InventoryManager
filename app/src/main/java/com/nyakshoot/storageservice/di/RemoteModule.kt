package com.nyakshoot.storageservice.di

import com.google.gson.Gson
import com.nyakshoot.storageservice.data.remote.IAuthClient
import com.nyakshoot.storageservice.data.remote.IUserClient
import com.nyakshoot.storageservice.data.remote.interceptor.AuthInterceptor
import com.nyakshoot.storageservice.data.repository.AuthRepositoryImpl
import com.nyakshoot.storageservice.data.repository.UserRepositoryImpl
import com.nyakshoot.storageservice.domain.repository.IAuthRepository
import com.nyakshoot.storageservice.domain.repository.IUserRepository
import com.nyakshoot.storageservice.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Provides
    @Singleton
    fun provideAuthClient(retrofit: Retrofit): IAuthClient =
        retrofit.create()

    @Provides
    @Singleton
    fun provideAuthRepositoryInterface(iAuthClient: IAuthClient): IAuthRepository = AuthRepositoryImpl(iAuthClient)

    @Provides
    @Singleton
    fun provideUserClient(retrofit: Retrofit): IUserClient =
        retrofit.create()

    @Provides
    @Singleton
    fun provideUserRepositoryInterface(iUserClient: IUserClient): IUserRepository = UserRepositoryImpl(iUserClient)

}