package com.kire.market_place_android.di.order

import com.kire.market_place_android.data.remote.api.order.IOrderApi
import com.kire.market_place_android.data.remote.api.order.OrderApi

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OrderApiModule {

    @Binds
    @Singleton
    abstract fun provideOrderApi(orderService: OrderApi): IOrderApi
}