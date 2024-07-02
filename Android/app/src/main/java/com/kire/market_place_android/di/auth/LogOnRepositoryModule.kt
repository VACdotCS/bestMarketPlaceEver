package com.kire.market_place_android.di.auth

import com.kire.market_place_android.data.repository.LogOnRepository
import com.kire.market_place_android.domain.repository.ILogOnRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * By Michael Gontarev (KiREHwYE)*/
@Module
@InstallIn(SingletonComponent::class)
abstract class LogOnRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideLogOnRepository(logOnRepository: LogOnRepository): ILogOnRepository
}