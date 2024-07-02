package com.kire.market_place_android.di.pick_up_point

import com.kire.market_place_android.data.repository.PickUpPointRepository
import com.kire.market_place_android.domain.repository.IPickUpPointRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * By Michael Gontarev (KiREHwYE)*/
@Module
@InstallIn(SingletonComponent::class)
abstract class PickUpPointRepositoryModule {

    @Binds
    @Singleton
    abstract fun providePickUpPointRepository(pickUpPointRepository: PickUpPointRepository): IPickUpPointRepository
}