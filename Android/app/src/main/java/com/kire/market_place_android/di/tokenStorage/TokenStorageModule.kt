package com.kire.market_place_android.di.tokenStorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.kire.audio.data.preferencesDataStore.ITokenStorage
import com.kire.market_place_android.data.local.datastore.TokenStorage

import com.kire.market_place_android.data.local.datastore.util.TokenStorageConstants
import dagger.Binds

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

private val Context.tokenStorage by TokenStorageConstants.TOKEN_STORAGE_NAME

@Module
@InstallIn(SingletonComponent::class)
object TokenStorageModule {

    @Provides
    @Singleton
    fun provideTokenDataStore(
        @ApplicationContext applicationContext: Context
    ): DataStore<Preferences> {
        return applicationContext.tokenStorage
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class connectTokenStorageWithInterface {

    @Binds
    @Singleton
    abstract fun connectTokenStorageWithInterface(tokenStorage: TokenStorage): ITokenStorage
}