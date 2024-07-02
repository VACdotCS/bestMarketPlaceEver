package com.kire.market_place_android.di.auth

import com.kire.market_place_android.data.remote.api.auth.AuthApi
import com.kire.market_place_android.data.remote.api.auth.IAuthApi

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthApiModule {

    @Binds
    @Singleton
    abstract fun provideAuthApi(authService: AuthApi): IAuthApi
}