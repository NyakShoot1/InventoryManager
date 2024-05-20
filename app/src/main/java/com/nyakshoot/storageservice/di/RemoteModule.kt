package com.nyakshoot.storageservice.di

import com.google.gson.Gson
import com.nyakshoot.storageservice.data.remote.clients.IAuthClient
import com.nyakshoot.storageservice.data.remote.clients.IItemClient
import com.nyakshoot.storageservice.data.remote.clients.IPhotoClient
import com.nyakshoot.storageservice.data.remote.clients.IPositionClient
import com.nyakshoot.storageservice.data.remote.clients.IShipmentClient
import com.nyakshoot.storageservice.data.remote.clients.ISupplierClient
import com.nyakshoot.storageservice.data.remote.clients.IUserClient
import com.nyakshoot.storageservice.data.remote.interceptor.AuthInterceptor
import com.nyakshoot.storageservice.data.repository.AuthRepositoryImpl
import com.nyakshoot.storageservice.data.repository.ItemRepositoryImpl
import com.nyakshoot.storageservice.data.repository.PhotoRepositoryImpl
import com.nyakshoot.storageservice.data.repository.PositionRepositoryImpl
import com.nyakshoot.storageservice.data.repository.ShipmentRepositoryImpl
import com.nyakshoot.storageservice.data.repository.SupplierRepositoryImpl
import com.nyakshoot.storageservice.data.repository.UserRepositoryImpl
import com.nyakshoot.storageservice.domain.repository.IAuthRepository
import com.nyakshoot.storageservice.domain.repository.IItemRepository
import com.nyakshoot.storageservice.domain.repository.IPhotoRepository
import com.nyakshoot.storageservice.domain.repository.IPositionRepository
import com.nyakshoot.storageservice.domain.repository.IShipmentRepository
import com.nyakshoot.storageservice.domain.repository.ISupplierRepository
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
    fun provideAuthRepositoryInterface(iAuthClient: IAuthClient): IAuthRepository =
        AuthRepositoryImpl(iAuthClient)

    @Provides
    @Singleton
    fun provideUserClient(retrofit: Retrofit): IUserClient =
        retrofit.create()

    @Provides
    @Singleton
    fun provideUserRepositoryInterface(iUserClient: IUserClient): IUserRepository =
        UserRepositoryImpl(iUserClient)

    @Provides
    @Singleton
    fun provideItemClient(retrofit: Retrofit): IItemClient = retrofit.create()

    @Provides
    @Singleton
    fun providerItemRepositoryInterface(iItemClient: IItemClient): IItemRepository =
        ItemRepositoryImpl(iItemClient)

    @Provides
    @Singleton
    fun provideSupplierClient(retrofit: Retrofit): ISupplierClient = retrofit.create()

    @Provides
    @Singleton
    fun providerSupplierRepositoryInterface(iSupplierClient: ISupplierClient): ISupplierRepository =
        SupplierRepositoryImpl(iSupplierClient)

    @Provides
    @Singleton
    fun providePositionClient(retrofit: Retrofit): IPositionClient = retrofit.create()

    @Provides
    @Singleton
    fun providerPositionRepositoryInterface(iPositionClient: IPositionClient): IPositionRepository =
        PositionRepositoryImpl(iPositionClient)

    @Provides
    @Singleton
    fun provideShipmentClient(retrofit: Retrofit): IShipmentClient = retrofit.create()

    @Provides
    @Singleton
    fun providerShipmentRepositoryInterface(iShipmentClient: IShipmentClient): IShipmentRepository =
        ShipmentRepositoryImpl(iShipmentClient)

    @Provides
    @Singleton
    fun providePhotoClient(retrofit: Retrofit): IPhotoClient = retrofit.create()

    @Provides
    @Singleton
    fun providerPhotoRepositoryInterface(iPhotoClient: IPhotoClient): IPhotoRepository =
        PhotoRepositoryImpl(iPhotoClient)
}