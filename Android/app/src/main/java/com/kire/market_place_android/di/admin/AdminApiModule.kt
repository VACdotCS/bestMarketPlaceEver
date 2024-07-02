package com.kire.market_place_android.di.admin

import com.kire.market_place_android.data.remote.api.admin.AdminApi
import com.kire.market_place_android.data.remote.api.admin.IAdminApi

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AdminApiModule {

    @Binds
    @Singleton
    abstract fun provideAdminApi(adminApi: AdminApi): IAdminApi
}