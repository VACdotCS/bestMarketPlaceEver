package com.kire.market_place_android.di.product

import com.kire.market_place_android.data.remote.api.product.IProductApi
import com.kire.market_place_android.data.remote.api.product.ProductApi

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductApiModule {

    @Binds
    @Singleton
    abstract fun provideProductApi(productService: ProductApi): IProductApi
}