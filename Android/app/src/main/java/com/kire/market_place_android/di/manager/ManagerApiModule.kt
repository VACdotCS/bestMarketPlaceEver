package com.kire.market_place_android.di.manager

import com.kire.market_place_android.data.remote.api.manager.IManagerApi
import com.kire.market_place_android.data.remote.api.manager.ManagerApi

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerApiModule {

    @Binds
    @Singleton
    abstract fun provideManagerApi(managerApi: ManagerApi): IManagerApi
}