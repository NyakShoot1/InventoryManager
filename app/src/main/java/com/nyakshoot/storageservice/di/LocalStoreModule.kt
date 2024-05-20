package com.nyakshoot.storageservice.di

import android.content.Context
import com.nyakshoot.storageservice.data.local.LocalReceivingDeliveryRepo
import com.nyakshoot.storageservice.data.local.TokenManager
import com.nyakshoot.storageservice.data.local.UserManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalStoreModule {

    @Singleton
    @Provides
    fun provideTokenStoreManager(@ApplicationContext context: Context): TokenManager =
        TokenManager(context)

    @Singleton
    @Provides
    fun provideLocalReceivingDeliveryRepo(): LocalReceivingDeliveryRepo =
        LocalReceivingDeliveryRepo()

    @Singleton
    @Provides
    fun provideUserStoreManager(@ApplicationContext context: Context): UserManager =
        UserManager(context)
}