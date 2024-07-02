package com.kire.market_place_android.di.auth

import com.kire.market_place_android.data.repository.LogInRepository
import com.kire.market_place_android.domain.repository.ILogInRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * By Michael Gontarev (KiREHwYE)*/
@Module
@InstallIn(SingletonComponent::class)
abstract class LogInRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideLogInRepository(logInRepository: LogInRepository): ILogInRepository
}