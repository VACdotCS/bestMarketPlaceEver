package com.kire.market_place_android.di.user

import com.kire.market_place_android.data.remote.api.user.IUserApi
import com.kire.market_place_android.data.remote.api.user.UserApi

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserApiModule {

    @Binds
    @Singleton
    abstract fun provideUserApi(userService: UserApi): IUserApi
}