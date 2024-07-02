package com.kire.market_place_android.di.tokenStorage

import com.kire.market_place_android.data.repository.TokenStorageRepository
import com.kire.market_place_android.domain.repository.ITokenStorageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TokenStorageRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideTokenStorageRepository(tokenStorageRepository: TokenStorageRepository): ITokenStorageRepository
}