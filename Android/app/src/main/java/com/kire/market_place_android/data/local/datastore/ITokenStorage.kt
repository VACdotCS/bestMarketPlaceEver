package com.kire.audio.data.preferencesDataStore

import com.kire.market_place_android.data.model.auth.Role
import com.kire.market_place_android.data.model.auth.Token

import kotlinx.coroutines.flow.Flow

interface ITokenStorage {

    fun isAuthenticated(): Flow<Boolean>
    suspend fun store(token: Token)
    suspend fun isTokenExpired(): Boolean
    suspend fun getToken(): Token
    suspend fun getRole(): Flow<Role>
    suspend fun getUserId(): Flow<Int>
    suspend fun onLogout()
}