package com.nyakshoot.storageservice.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nyakshoot.storageservice.data.dto.movement.WhereDTO
import com.nyakshoot.storageservice.data.remote.adapters.WhereDTOAdapter
import com.nyakshoot.storageservice.domain.use_case.ValidateLoginInputUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(WhereDTO::class.java, WhereDTOAdapter())
        .disableHtmlEscaping()
        .create()

    @Provides
    @Singleton
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCase()
    }
}