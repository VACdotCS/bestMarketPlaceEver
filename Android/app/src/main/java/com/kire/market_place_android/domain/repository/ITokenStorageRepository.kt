package com.kire.market_place_android.domain.repository

import com.kire.market_place_android.data.model.auth.Token
import com.kire.market_place_android.domain.model.user.RoleDomain

import kotlinx.coroutines.flow.Flow

interface ITokenStorageRepository {

    fun isAuthenticated(): Flow<Boolean>
    suspend fun isTokenExpired(): Boolean
    suspend fun store(token: Token)
    suspend fun getToken(): Token
    suspend fun getRole(): Flow<RoleDomain>
    suspend fun getUserId(): Flow<Int>
    suspend fun onLogout()
}