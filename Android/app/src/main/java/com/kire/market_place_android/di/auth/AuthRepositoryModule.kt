package com.kire.market_place_android.di.auth

import com.kire.market_place_android.data.repository.AuthRepository
import com.kire.market_place_android.domain.repository.IAuthRepository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideAuthRepository(authRepository: AuthRepository): IAuthRepository
}