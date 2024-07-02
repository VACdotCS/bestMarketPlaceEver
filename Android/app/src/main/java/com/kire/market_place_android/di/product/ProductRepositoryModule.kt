package com.kire.market_place_android.di.product

import com.kire.market_place_android.data.repository.ProductRepository
import com.kire.market_place_android.domain.repository.IProductRepository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * By Michael Gontarev (KiREHwYE)*/
@Module
@InstallIn(SingletonComponent::class)
abstract class ProductRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideProductRepository(productRepository: ProductRepository): IProductRepository
}