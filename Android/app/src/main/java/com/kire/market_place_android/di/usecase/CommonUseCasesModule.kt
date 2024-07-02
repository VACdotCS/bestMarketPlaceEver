package com.kire.market_place_android.di.usecase

import com.kire.market_place_android.domain.use_case.common.util.CommonUseCases
import com.kire.market_place_android.domain.use_case.common.util.ICommonUseCases

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonUseCasesModule {

    @Binds
    @Singleton
    abstract fun provideCommonUseCases(commonUseCases: CommonUseCases): ICommonUseCases
}