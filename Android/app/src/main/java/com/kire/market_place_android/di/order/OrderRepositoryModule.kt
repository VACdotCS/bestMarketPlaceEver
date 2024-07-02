package com.kire.market_place_android.di.order

import com.kire.market_place_android.data.repository.OrderRepository
import com.kire.market_place_android.domain.repository.IOrderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * By Michael Gontarev (KiREHwYE)*/
@Module
@InstallIn(SingletonComponent::class)
abstract class OrderRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideOrderRepository(orderRepository: OrderRepository): IOrderRepository
}