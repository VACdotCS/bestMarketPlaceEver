package com.kire.market_place_android.di.usecase

import com.kire.market_place_android.domain.use_case.common.util.AuthUseCases
import com.kire.market_place_android.domain.use_case.common.util.IAuthUseCases

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthUseCasesModule {

    @Binds
    @Singleton
    abstract fun provideAuthUseCases(authUseCases: AuthUseCases): IAuthUseCases
}