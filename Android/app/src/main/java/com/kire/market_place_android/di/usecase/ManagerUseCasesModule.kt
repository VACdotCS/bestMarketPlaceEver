package com.kire.market_place_android.di.usecase

import com.kire.market_place_android.domain.use_case.manager.util.IManagerUseCases
import com.kire.market_place_android.domain.use_case.manager.util.ManagerUseCases

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerUseCasesModule {

    @Binds
    @Singleton
    abstract fun provideManagerUseCases(managerUseCases: ManagerUseCases): IManagerUseCases
}