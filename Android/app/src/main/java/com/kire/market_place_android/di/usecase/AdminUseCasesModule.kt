package com.kire.market_place_android.di.usecase

import com.kire.market_place_android.domain.use_case.admin.util.AdminUseCases
import com.kire.market_place_android.domain.use_case.admin.util.IAdminUseCases

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AdminUseCasesModule {

    @Binds
    @Singleton
    abstract fun provideAdminUseCases(adminUseCases: AdminUseCases): IAdminUseCases
}